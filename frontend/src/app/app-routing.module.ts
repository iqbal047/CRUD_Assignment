import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeFormComponent } from './components/employee/employee-form/employee-form.component';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { LoginComponent } from './components/layout/login-page/login/login.component';
import { canActivate, canActivateChild } from './services/auth/auth-guard';
import { AuthInterceptor } from './services/interceptors/auth-interceptor.service';
import { HomeComponent } from './components/layout/home/home.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [canActivate],
    canActivateChild: [canActivateChild],
    children: [
      { path: 'emp-form', component: EmployeeFormComponent },
      { path: 'emp-list', component: EmployeeListComponent },
    ],
  },
  {
    path: 'login',
    component: LoginComponent,
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
})
export class AppRoutingModule {}
