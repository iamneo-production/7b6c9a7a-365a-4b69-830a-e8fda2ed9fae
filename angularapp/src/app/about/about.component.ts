import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit(): void {
  }
 
  back()
  {
    this.route.navigate(['users/homepage']) //needs to be filled deva
  }
}