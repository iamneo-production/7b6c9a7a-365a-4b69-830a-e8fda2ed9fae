import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninService } from './services/signin.service';
import { SigninComponent } from './signin/signin.component';
import { RestServices } from './services/rest.service';
// import { SignupComponent } from './signup/signup.component';
import { OtpComponent } from './otp/otp.component';
import { OtpServices } from './services/otp.service';
// import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
// import { NavBarComponent } from './nav-bar/nav-bar.component';
// import { ProductServices } from './services/product.service';
import { AuthGuardService } from './auth/auth.guard';
import { UserModel } from './auth/user.model';
import { LoginActivate } from './auth/autologin.guard';
// import { FooterComponent } from './footer/footer.component';
// import { CartComponent } from './cart/cart.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { CartService } from './services/cart.service';
import { NgxPaginationModule} from 'ngx-pagination';
// import { OrdersComponent } from './orders-component/orders-component';
import { OrderServices } from './services/order.service';
// import { SwalServices } from './services/swal.service';
// import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { AdminServices } from './services/admin.service';
// import { AdminNavBarComponent } from './admin/admin-nav-bar/admin-nav-bar.component';
import { AdminAuthGuard } from './auth/adminAuth.guard';
import { ViewordersComponent } from './admin/vieworders/vieworders.component';
// import { DialogComponent } from './dialog/dialog.component';
// import { FeedbackComponent } from './admin/feedback/feedback.component';


@NgModule({
  declarations: [
    // AppComponent,
    SigninComponent,
    // SignupComponent,
    OtpComponent,
    // UserDashboardComponent,
    // NavBarComponent,
    // FooterComponent,
    // CartComponent,
    // OrdersComponent,
    // DashboardComponent,
    // AdminNavBarComponent,
    ViewordersComponent,
    // DialogComponent,
    // FeedbackComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule, 
    NgxPaginationModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      iconClasses: {
        error: 'toast-error',
        info: 'toast-info',
        success: 'toast-success',
        warning: 'toast-warning',
      }
    })
  ],
  providers: [
    SigninService,
    RestServices,
    OtpServices,
    // ProductServices,
    AuthGuardService,
    // CartService,
    OrderServices,
    // SwalServices,
    UserModel,
    LoginActivate,
    AdminAuthGuard,

    AdminServices
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
