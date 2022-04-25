import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { OtpServices } from '../services/otp.service';

@Component({
  selector: 'app-otp',
  templateUrl: './otp.component.html',
  styleUrls: ['./otp.component.css']
})
export class OtpComponent implements OnInit {

  constructor(private otpService: OtpServices) { }

  otpSent: boolean = this.otpService.otpSent;
  otpVerified: boolean = this.otpService.otpVerified;
  otpVerificationFailed: boolean = false;

  ngOnInit(): void {
    this.otpSent = this.otpService.otpSent;
    this.otpVerified = this.otpService.otpVerified;
  }


  sendOtpToEmail(formData: NgForm) {
    this.otpSent = true;
    this.otpService.requestOtp(formData.value.email).subscribe((responseData: any) => {
      if (responseData.error == null) {
        this.otpService.otpSent = true
        this.otpService.otpToken = responseData.token;
        this.otpSent = this.otpService.otpSent;
      }
    }, error => {
      this.otpService.otpSent = false;
      this.otpSent = false;
      console.log("error occured white generating otp" + this.otpSent);
    })
  }

  verifyUserOtp(formData: NgForm) {
    this.otpService.verifyOtp(formData.value.otp).subscribe((responseData: any) => {
      if (responseData.verified == true) {
        this.otpSent = true;
        this.otpVerified = true;
        this.otpService.otpSent = this.otpSent;
        this.otpService.otpVerified = this.otpVerified;
        this.otpService.otpVerifiedEventEmitter.next(true);
      } else if (responseData.error != null) {
        this.otpVerificationFailed = true;
        // this.resetToInitial();
      }
    }, error => {
      this.resetToInitial();
    })
  }

  resetToInitial() {
    this.otpSent = false;
    this.otpVerified = false;
    this.otpService.otpSent = this.otpSent;
    this.otpService.otpVerified = this.otpVerified;
    this.otpService.otpToken = "";
    this.otpVerificationFailed = false;
  }
}
