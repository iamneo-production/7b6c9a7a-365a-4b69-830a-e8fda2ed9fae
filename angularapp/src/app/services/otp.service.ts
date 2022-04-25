import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs";


@Injectable()
export class OtpServices {


  constructor(private http: HttpClient) { }

  otpToken: string = "";
  public otpSent: boolean = false;
  public otpVerified: boolean = false;

  otpVerifiedEventEmitter = new Subject<boolean>();

  requestOtp(email: string) {
    return this.http.post('http://localhost:8080/user/requestOtp', email);
  }


  verifyOtp(otp: string) {
    return this.http.post(
      'http://localhost:8080/user/verifyOtp', 
      otp,
      {
        responseType: 'json',
        headers: new HttpHeaders({ 'Authorization': 'Bearer ' + this.otpToken })
      });
  }
}