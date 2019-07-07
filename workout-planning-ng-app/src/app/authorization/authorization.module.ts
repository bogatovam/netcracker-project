import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from "@angular/forms";
import { EffectsModule } from "@ngrx/effects";
import { AuthorizationGuardService } from "src/app/authorization/services/authorization-guard.service";
import { httpInterceptorProviders } from "src/app/authorization/services/authorization-interceptor";
import { AuthorizationService } from "src/app/authorization/services/authorization.service";
import { AuthorizationEffects } from "src/app/authorization/store/effects/autorization.effects";
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';

const AUTH_ROUTES = [
  {path: 'log-in', component: LoginComponent},
  {path: 'sign-up', component: SignupComponent},
];

@NgModule({
  declarations: [LoginComponent, SignupComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    EffectsModule.forFeature([AuthorizationEffects])
  ],
  providers: [httpInterceptorProviders, AuthorizationService, AuthorizationGuardService]
})
export class AuthorizationModule {
}
