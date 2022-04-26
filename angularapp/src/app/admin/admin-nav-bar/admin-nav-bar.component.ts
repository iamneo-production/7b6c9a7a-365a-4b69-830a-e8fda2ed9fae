import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/auth/user.model';

@Component({
  selector: 'app-admin-nav-bar',
  templateUrl: './admin-nav-bar.component.html',
  styleUrls: ['./admin-nav-bar.component.css']
})
export class AdminNavBarComponent implements OnInit {

  constructor(private router: Router, private userModel: UserModel) { }

  ngOnInit(): void {
  }

  logout() {
    this.userModel.setRole("");
    this.userModel.setUserId("");
    this.userModel.setUserValidationStatus(false);
    this.router.navigate(['/']);
  }
}
