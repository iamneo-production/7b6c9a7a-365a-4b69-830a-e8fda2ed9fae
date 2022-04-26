import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AdminServices } from 'src/app/services/admin.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  productsInformation: any[] = [];
  currentPage = 1;
  showAddProductForm = true;
  @ViewChild('f') form: NgForm | undefined;
  price: string  = "";
  url: string = "";
  name: string = "";
  stock: string = "";
  productId: string = "";

  constructor(private adminServices: AdminServices, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.adminServices.fetchAllProducts().subscribe({
      next: (responseData: any) => {
        console.log(responseData);
        this.productsInformation = responseData;
      },  error: (e) => {
        console.log("error occured");
      }
    })
  }


  onSubmit(formData: NgForm) {
    if (this.showAddProductForm) {
      this.adminServices.addNewProduct(formData.value).subscribe({
        next: (responseData: any) => {
          if (responseData) {
            this.toastr.success('Successfull', 'Product added', {timeOut: 3000});
            this.adminServices.fetchAllProducts().subscribe({
              next: (responseData: any) => {
                console.log(responseData);
                this.productsInformation = responseData;
              },  error: (e) => {
                console.log("error occured");
              }
            })
          } else {
            this.toastr.error('Invalid Data', 'Try Again', {timeOut: 3000});
          }
        }, error: (e) => {
          console.log(e);
          this.toastr.error('Invalid Data', 'Try Again', {timeOut: 3000});
        }
      })
    } else if (this.showAddProductForm == false) {
      // here passing id is useless. check the backend api endpoint
      console.log(this.productId + "is the product id");
      this.adminServices.updateProduct({...formData.value, product_id: this.productId}, 2).subscribe({
        next: (responseData: any) => {
          if (responseData) {
            this.toastr.success('Successfull', 'Product Updated', {timeOut: 3000});
            this.adminServices.fetchAllProducts().subscribe({
              next: (responseData: any) => {
                console.log(responseData);
                this.productsInformation = responseData;
              },  error: (e) => {
                console.log("error occured");
              }
            })
          } else {
            this.toastr.error('Invalid Data', 'Try Again', {timeOut: 3000});
          }
        }, error: (e) => {
          console.log(e);
          this.toastr.error('Invalid Data', 'Try Again', {timeOut: 3000});
        }
      })
    }
    
    formData.reset();
  }

  modifyClickEvent(productIndex: any) {
    this.showAddProductForm = false;
    this.price = this.productsInformation[productIndex].price;
    this.url = this.productsInformation[productIndex].productImageUrl;
    this.name = this.productsInformation[productIndex].productName;
    this.stock = this.productsInformation[productIndex].stockQuantity;
    this.productId = this.productsInformation[productIndex].product_id;
  }

  deleteClickEvent(productIndex: any) {
    this.adminServices.deleteProduct(this.productsInformation[productIndex].product_id).subscribe({
      next: (responseData: any) => {
        if (responseData) {
          this.toastr.success('Successfull', 'Product Deleted', {timeOut: 3000});
          this.adminServices.fetchAllProducts().subscribe({
            next: (responseData: any) => {
              console.log(responseData);
              this.productsInformation = responseData;
            },  error: (e) => {
              console.log("error occured");
            }
          })
        } else {
          this.toastr.error('Failed', 'Deletion error', {timeOut: 3000});
        }
      }, error: (e) => {
        this.toastr.error('Failed', 'Deletion error', {timeOut: 3000});
      }
    })
  }
}
