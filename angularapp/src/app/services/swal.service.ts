import { Injectable } from "@angular/core";
import Swal from 'sweetalert2'
import { UserModel } from "../auth/user.model";
import { CartService } from "./cart.service";


@Injectable()
export class SwalServices {

  constructor(private cartService: CartService, private userModel: UserModel) {}

 

  showSwalConfirmation(swalConfiguration: {title: string; text: string; icon: string }) {
    
    return new Promise((resolve, reject) => {
      Swal.fire({
        title: 'Unable to order',
        text: 'reset cart quantity to max stock?',
        icon: 'error',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'rest cart quantity'
      }).then((result) => {
        if (result.isConfirmed) {
          this.cartService.resetCartItemsQuantity(this.userModel.getUserId()).subscribe({
            next: (responseData: any) => {
              if (responseData) {
                Swal.fire(
                  'Operation Successfull',
                  'Cart quantity are reset',
                  'success' 
                ).then(() => { resolve(true)} )
              } else {
                Swal.fire('Operation failed', 'Cart items are not set', 'error').then(() => { reject(true) })
              }
            } 
          })
        }
      })
    })
    
    
  }
}