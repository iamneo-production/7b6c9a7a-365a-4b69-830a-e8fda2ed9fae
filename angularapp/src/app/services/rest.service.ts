import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class RestServices {

  constructor(private http: HttpClient) {}

  postUserLoginData(userObject: {email: string, password: string}) {
    return this.http.post('http://localhost:8080/user/login', userObject);
  }


  postUserSignUpData(userObject: any) {
    return this.http.post('http://localhost:8080/user/signup', userObject);
  }
}