import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserModel } from '../auth/user.model';
import { CartService } from '../services/cart.service';
import Swal from 'sweetalert2'
import { SwalServices } from '../services/swal.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];
  totalAmount: number = 0;
  currentPage: number = 1;

  constructor(
    private cartService: CartService,
    private userModelService: UserModel,
    private toastr: ToastrService,
    private router: Router,
    private swalServices: SwalServices) {
    this.totalAmount = 0;
    cartService.getCartItemsByUserId(userModelService.getUserId()).subscribe({
      next: (responseData: any) => {
        console.log(responseData);
        this.cartItems = responseData;
        this.totalAmount = 0;
        responseData.forEach((element: any) => {

          this.totalAmount += (element.product.price * element.quantity)
        });

      },
      error: (e: any) => {
        console.log("error occured");
      }
    })
  }

  ngOnInit(): void {
  }


  deleteCartItem(cartId: string): any {
    this.cartService.deleteCartItem(this.userModelService.getUserId(), cartId).subscribe({
      next: (responseData: any) => {
        if (responseData) {
          this.toastr.success(undefined, 'Deleted Successfully', { timeOut: 1500 });
          this.totalAmount = 0;
          responseData.forEach((element: any) => {
            this.totalAmount += (element.product.price * element.quantity)
          });
        }
        this.cartItems = responseData

      }, error: () => {
        this.toastr.error(undefined, 'Failed', { timeOut: 1500 })
      }
    })
  }

  addItemToCart(cartId: string) {
    this.cartService.addCartItemInstance(cartId).subscribe({
      next: (responseData: any) => {
        if (responseData != false) {
          this.toastr.success(undefined, 'Added Successfully', { timeOut: 1500 });
          this.cartItems = responseData;
          this.totalAmount = 0;
          responseData.forEach((element: any) => {

            this.totalAmount += (element.product.price * element.quantity)
          });
        }
        else this.toastr.error(undefined, 'Cart Updation Failed', { timeOut: 1500 });
      }, error: (e) => {
        this.toastr.error(undefined, 'Cart Updation Failed', { timeOut: 1500 })
      }
    })
  }

  removeItemFromCart(cartId: string) {
    this.cartService.deleteCartItemInstance(cartId).subscribe({
      next: (responseData: any) => {
        if (responseData != false) {
          this.toastr.success(undefined, 'Deleted Successfully', { timeOut: 1500 });
          this.cartItems = responseData;
          this.totalAmount = 0;
          responseData.forEach((element: any) => {

            this.totalAmount += (element.product.price * element.quantity)
          });
        }
        else this.toastr.error(undefined, 'Cart Updation Failed', { timeOut: 1500 });

      }, error: (e) => {
        console.log(e);
        this.toastr.error(undefined, 'Cart Updation Failed', { timeOut: 1500 })
      }
    })
  }

  executeOrderItems = () => {
    this.cartService.orderItemsFromCart(this.userModelService.getUserId()).subscribe({
      next: (responseData) => {
        if (responseData) {
          this.router.navigate(['/users/orders']);
        } else {
          let data = this.swalServices.showSwalConfirmation({
            title: 'Unable to order',
            text: 'reset cart quantity to max stock?',
            icon: 'error'
          }).then(data => {
            if (data == true) {
              this.cartService.getCartItemsByUserId(this.userModelService.getUserId()).subscribe({
              next: (responseData: any) => {
                console.log(responseData);
                this.cartItems = responseData;
                this.totalAmount = 0;
                responseData.forEach((element: any) => {
                  this.totalAmount += (element.product.price * element.quantity)
                });
                
              },
              error: (e: any) => {
                console.log("error occured");
              }
            })
            }
            
          }).catch(data => {
            console.log("error occured");
          })
        }
      }
    })
  }
}
