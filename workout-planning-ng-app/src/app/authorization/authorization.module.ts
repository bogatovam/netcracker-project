import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule, MatCardModule, MatDialogModule, MatInputModule } from "@angular/material";
import { EffectsModule } from "@ngrx/effects";
import { AuthorizationGuardService } from "src/app/authorization/services/authorization-guard.service";
import { httpInterceptorProviders } from "src/app/authorization/services/authorization-interceptor";
import { AuthorizationService } from "src/app/authorization/services/authorization.service";
import { AuthorizationEffects } from "src/app/authorization/store/effects/autorization.effects";
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';

const AUTH_ROUTES = [
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
];

@NgModule({
  declarations: [LoginComponent, SignupComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule,
    EffectsModule.forFeature([AuthorizationEffects])
  ],
  providers: [httpInterceptorProviders, AuthorizationService, AuthorizationGuardService]
})
export class AuthorizationModule {
}
