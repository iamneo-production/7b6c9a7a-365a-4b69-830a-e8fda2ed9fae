import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable()
export class CartService {

  constructor(private http: HttpClient) {}

  getCartItemsByUserId(userId: any): any {
    return this.http.get('http://localhost:8080/cart/' + userId);
  }

  deleteCartItem(userId: any, cartId: any): any {
    let data = {
      userId: userId,
      cartId: cartId
    }
    return this.http.delete('http://localhost:8080/cart/delete', {body: data});
  }


  addCartItemInstance(cartId: string) {
    return this.http.get('http://localhost:8080/cart/addInstance/' + cartId);
  } 

  deleteCartItemInstance(cartId: string) {
    return this.http.get('http://localhost:8080/cart/deleteInstance/' + cartId);
  }

  orderItemsFromCart(userId: string) {
    return this.http.post('http://localhost:8080/saveOrder', { userId: userId });
  }

  resetCartItemsQuantity(userId: string) {
    return this.http.post('http://localhost:8080/resetCart', { userId: userId });
  }
}