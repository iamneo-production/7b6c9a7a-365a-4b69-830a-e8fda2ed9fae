import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

interface ProductSchema {
  product_id: number;
  productName: string;
  price: number;
  productImageUrl: string;
  productQuantity: number;
}

@Injectable()
export class ProductServices {

  constructor(private http: HttpClient) { }

  // productsInformation = new Subject<ProductSchema>();


  getAllProducts() {
    return this.http.get('http://localhost:8080/getAllProducts');
  }

  addProductToCart(productData: any, userId: any) {
    return this.http.post<any>('http://localhost:8080/home/cart/' + userId, productData, 
    {responseType: 'json'});
  }

}