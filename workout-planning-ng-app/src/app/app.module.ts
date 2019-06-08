import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './authorization/login/login.component';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';

import { httpInterceptorProviders } from './authorization/authorization-interceptor';
import {RouterModule, Routes} from "@angular/router";
import {SignupComponent} from "./authorization/signup/signup.component";
import {NavigationComponent} from "./navigation/navigation.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { WorkoutComponent } from './workout/workout.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'auth/login',
    component: LoginComponent
  },
  {
    path: 'auth/signup',
    component: SignupComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    HomeComponent,
    SignupComponent,
    NavigationComponent,
    WorkoutComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    NgbModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }

