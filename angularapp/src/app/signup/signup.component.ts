import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { UserModel } from '../auth/user.model';
import { OtpServices } from '../services/otp.service';
import { RestServices } from '../services/rest.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  subscription: Subscription;

  constructor(
    private otpService: OtpServices, 
    private restService: RestServices, 
    private router: Router, 
    private userModel: UserModel,
    private toastr: ToastrService) {
    this.subscription = this.otpService.otpVerifiedEventEmitter.subscribe(updatedValue => {
      this.emailVerified = updatedValue;
    })
  }

  ngOnInit(): void {
  }

  genders: string[] = ['Male', 'Female']
  // emailVerified: boolean = this.otpService.otpVerified;
  emailVerified: boolean = true;

  validateEmail(email: string) {
    var regexp = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$');
    return regexp.test(email);
  }


  validatePassword(password: string) {
    var regexp = new RegExp('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$');
    return regexp.test(password);
  }

  validateConformPassword(password: string, conformPassword: string) {
    if (password != conformPassword) {
      return false;
    }

    return true;
  }

  validateContact(contact: string) {
    var regexp = new RegExp('^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$');
    return regexp.test(contact);
  }

  validateUserName(username: string) {
    if (username == "") {
      return false;
    }
    return true;
  }


  validateEntireForm(formData: NgForm) {
    if (this.validateEmail(formData.value.email)
      && this.validatePassword(formData.value.password)
      && this.validateConformPassword(formData.value.password, formData.value.conformPassword)
      && this.validateContact(formData.value.contact)
      && this.validateUserName(formData.value.username)
      && formData.value.gender != ""
    ) {
      return true;
    } else {
      return false;
    }
  }


  formSubmitted(formData: NgForm) {
    if (this.validateEntireForm(formData)) {
      this.restService.postUserSignUpData(formData.value).subscribe((responseData: any) => {
        if (responseData != false) {
          this.userModel.setUserValidationStatus(responseData.validUser);
          this.userModel.setUserId(responseData.userId);
          this.router.navigate(['users/login']);
        }
        else {
          this.userModel.setUserValidationStatus(false);
          this.userModel.setUserId("");
          this.toastr.error('In valid SignUp credentials', 'Signup Failed', { timeOut: 3000})
        }
      }, error => {
        this.userModel.setUserValidationStatus(false);
        this.userModel.setUserId("");
        this.toastr.error('In valid SignUp credentials', 'Signup Failed', { timeOut: 3000})
      })
      formData.reset();
    } else {
      formData.reset();
    }
  }
}
