import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HomeComponent } from "src/app/authorization/components/home/home.component";
import { LoginComponent } from "src/app/authorization/components/login/login.component";
import { SignupComponent } from "src/app/authorization/components/signup/signup.component";
import { AuthorizationGuardService } from "src/app/authorization/services/authorization-guard.service";

const AUTH_ROUTES = [
  {path: 'authentication/login', component: LoginComponent},
  {path: 'authentication/signup', component: SignupComponent},
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthorizationGuardService]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(AUTH_ROUTES)
  ],
  exports: [RouterModule]
})
export class AuthRoutingModule {}
