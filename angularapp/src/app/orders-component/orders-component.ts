import { Component, OnInit } from '@angular/core';
import { UserModel } from '../auth/user.model';
import { OrderServices } from '../services/order.service';

@Component({
  selector: 'app-orders-component',
  templateUrl: './orders-component.html',
  styleUrls: ['./orders-component.css']
})
export class OrdersComponent implements OnInit {

  ordersInformation: any[] = [];
  currentPage: number = 1;

  constructor(private ordersService: OrderServices, private userModel: UserModel) { 
    this.ordersService.getAllOrdersByUserId(this.userModel.getUserId()).subscribe({
      next: (responseData: any) => {
        this.ordersInformation = responseData;
        console.log(this.ordersInformation)
      }, error: (e: any) => {
        console.log(e)
      } 
    })
   }

  ngOnInit(): void {

  }

}
