import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";


@Injectable()
export class OrderServices {

  constructor(private http: HttpClient) {}
  getAllOrdersByUserId(userId: string) {
    return this.http.post('http://localhost:8080/getAllOrders', { userId: userId })
  }

  
}