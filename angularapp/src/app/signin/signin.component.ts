import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subject, Subscription } from 'rxjs';
import { UserModel } from '../auth/user.model';
import { OtpServices } from '../services/otp.service';
import { RestServices } from '../services/rest.service';
import { SigninService } from '../services/signin.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnDestroy {

  subscription: Subscription;

  constructor(
    private signInService: SigninService,
    private restService: RestServices,
    private otpService: OtpServices,
    private userModel: UserModel,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.subscription = this.otpService.otpVerifiedEventEmitter.subscribe(updatedValue => {
      this.emailVerified = updatedValue;
    })
  }


  validForm: boolean = false;
  validEmail: boolean = false;
  validPassword: boolean = false;
  emailVerified: boolean = this.otpService.otpVerified;




  emailValidity(email: any) {
    this.signInService.setEmail(email);
    return this.signInService.checkValidityOfEmail();
  }

  passwordValidity(password: any) {
    this.signInService.setPassword(password);
    return this.signInService.checkValidityOfPassword();
  }

  onSubmit(formData: NgForm) {

    this.signInService.setEmail(formData.value.username);
    this.signInService.setPassword(formData.value.password);
    this.validEmail = this.signInService.checkValidityOfEmail();
    this.validPassword = this.signInService.checkValidityOfPassword();
    this.validForm = this.validEmail && this.validPassword;
    if (this.emailValidity(formData.value.username) && this.passwordValidity(formData.value.password)) {
      this.restService.postUserLoginData({
        email: formData.value.username,
        password: formData.value.password
      }).subscribe((responseData: any) => {
        if (responseData != false) {
          this.userModel.setUserValidationStatus(responseData.validUser);
          this.userModel.setUserId(responseData.userId);
          if(responseData.role == "admin") {
            this.userModel.setRole("admin");
            this.router.navigate(['/admin','dashboard']);
          }
          else this.router.navigate(['/users/homepage']);  // routes to the next component
        } else {
          this.userModel.setUserValidationStatus(false);
          this.userModel.setUserId("");
          this.toastr.error('In valid login credentials', 'Login Failed', { timeOut: 3000})
        }
      }, error => {
        this.toastr.error('In valid login credentials', 'Login Failed', { timeOut: 3000})
        console.log("error occured while connecting to server")
      })
    }

    formData.reset()
  }

  testValues() {
    console.log(this.emailVerified);
    console.log(this.otpService.otpVerified);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}

//[ngClass]="{'is-invalid': signInForm.controls.username.touched ? !validEmail : false}"
//  @ViewChild('f') signInForm :NgForm | undefined;