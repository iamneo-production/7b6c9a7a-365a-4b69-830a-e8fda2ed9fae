import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { UserModel } from "./user.model";

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private userModel: UserModel) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.userModel.getUserValidationStatus()) return true;
    else return this.router.createUrlTree(['/'])
  }
}