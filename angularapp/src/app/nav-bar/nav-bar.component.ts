import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from '../auth/user.model';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private userModel: UserModel, private router: Router) { }

  ngOnInit(): void {
  }

  executeLogOut() {
    
    this.userModel.setUserValidationStatus(false);
    this.userModel.setUserId("");
    this.router.navigate(['/users', 'login']); 
 
  }
}
