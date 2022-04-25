import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";


@Injectable()
export class AdminServices {

  constructor(private http: HttpClient) {}

  fetchAllProducts() {
    return this.http.get('http://localhost:8080/admin');
  }

  addNewProduct(productData: any) {
    return this.http.post('http://localhost:8080/admin/addProduct', productData);
  }

  updateProduct(productData: any, userId: any) {
    return this.http.post('http://localhost:8080/admin/saveProduct/' + userId, productData);
  }

  deleteProduct(productId: any) {
    return this.http.get('http://localhost:8080/admin/delete/' + productId);
  }

  fetchAllOrders() {
    return this.http.get('http://localhost:8080/admin/orders');
  }
}