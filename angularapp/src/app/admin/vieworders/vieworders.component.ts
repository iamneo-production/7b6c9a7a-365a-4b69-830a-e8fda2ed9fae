import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AdminServices } from 'src/app/services/admin.service';

@Component({
  selector: 'app-vieworders',
  templateUrl: './vieworders.component.html',
  styleUrls: ['./vieworders.component.css']
})
export class ViewordersComponent implements OnInit {

  orderInformation: any[] = [];
  editStatus = true;

  constructor(private adminServices: AdminServices, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.adminServices.fetchAllOrders().subscribe({
      next: (responseData: any) => {
        if (responseData != false) {
          console.log(responseData);
          this.orderInformation = responseData;
        } else {
          this.toastr.error('Server error', 'Fetch Failed', {timeOut: 3000});
        }
      }, error: (e) => {
        this.toastr.error('Http Error', 'Fetch Failed', {timeOut: 3000});
      }
    })
  }

}
