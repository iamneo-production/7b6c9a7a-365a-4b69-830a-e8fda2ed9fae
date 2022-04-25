import { Injectable } from "@angular/core";

@Injectable()
export class UserModel {
  private userValidated: boolean = false;
  private userId: string = "";
  private role: string = "";

  getUserValidationStatus(): boolean {
    return this.userValidated;
  }

  setUserValidationStatus(newStatus: boolean) {
    this.userValidated = newStatus;
  }


  getUserId(): string {
    return this.userId;
  }

  setUserId(userId: string) {
    this.userId = userId;
  }

  setRole(role: string) { this.role = role; }
  getRole(): string { return this.role; }
}