import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from "@angular/forms";
import {
  MatButtonModule,
  MatCardModule,
  MatDatepickerModule,
  MatDialogModule,
  MatInputModule, MatNativeDateModule,
  MatRadioModule, MatSelectModule
} from "@angular/material";
import { RouterModule } from "@angular/router";
import { EffectsModule } from "@ngrx/effects";
import { AuthRoutingModule } from "src/app/authorization/auth-routing.module";
import { AuthorizationGuardService } from "src/app/authorization/services/authorization-guard.service";
import { httpInterceptorProviders } from "src/app/authorization/services/authorization-interceptor";
import { AuthorizationService } from "src/app/authorization/services/authorization.service";
import { AuthorizationEffects } from "src/app/authorization/store/effects/autorization.effects";
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { SignupComponent } from './components/signup/signup.component';

@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    HomeComponent,
    LogoutComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    ReactiveFormsModule,
    AuthRoutingModule,
    EffectsModule.forFeature([AuthorizationEffects]),
  ],
  providers: [httpInterceptorProviders, AuthorizationService, AuthorizationGuardService]
})
export class AuthorizationModule {
}
