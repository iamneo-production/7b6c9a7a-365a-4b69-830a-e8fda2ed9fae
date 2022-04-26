import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { take } from 'rxjs';
import { UserModel } from '../auth/user.model';
import { ProductServices } from '../services/product.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  productsInformation: {
    product_id: number,
    productName: string,
    price: number,
    productImageUrl: string,
    stockQuantity: number,
  }[] = [];
  
  constructor(
    private productServices: ProductServices, 
    private router: Router, 
    private userModel: UserModel,
    private toastr: ToastrService) { }

  userid: string = "";
  ngOnInit(): void {
    this.productServices.getAllProducts().subscribe( (responseData: any) => {
      if (!!responseData) {
        console.log(responseData)
        this.productsInformation = responseData;
        this.userid = this.userModel.getUserId();
      }
    }, error => {
      console.log(error)
    })

    // this.productServices.getAllProducts().subscribe({next: () => {}, error: () => {}})
  }
  
  addProductToCart(index: string, product: any) {
    let productClone = {...product};
    productClone.productQuantity = 1;
    this.productServices.addProductToCart(productClone, index).subscribe({
      next: (responseData) => {
        if (!!responseData) {
          this.toastr.success('click here to go to cart', 'Product Added', {timeOut: 3000})
          .onTap.pipe(take(1))
          .subscribe(() => {
            this.router.navigate(['/users/cart'])
          }
        );
          
        }
        else {
          this.toastr.error(undefined, 'Out of stock', {timeOut: 3000})
        }
      }, error: (e) => {
        console.log("error occured")
        console.log(e);
      }
    }
  )
    
  }

}