import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { UserModel } from "./user.model";


@Injectable()
export class AdminAuthGuard implements CanActivate {

  constructor(private userModel: UserModel, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): 
  boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.userModel.getRole() === "admin" && this.userModel.getUserValidationStatus()) {
      return true;
    } return this.router.createUrlTree(['/']);
  }

}