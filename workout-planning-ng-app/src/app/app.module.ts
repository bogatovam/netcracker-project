import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
// import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {Router, RouterModule, Routes} from "@angular/router";
import {UserComponent} from './user/user.component';
import {NavigationComponent} from './navigation/navigation.component';
import {LoginComponent} from './authorization/login/login.component';
import {SignupComponent} from './authorization/signup/signup.component';
import { HomeComponent } from './home/home.component';
import {HttpClient} from './authorization/http-client.service';

import { HttpModule } from '@angular/http';
import {HttpClientModule} from "@angular/common/http";
import {platformBrowserDynamic} from "@angular/platform-browser-dynamic";

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
    path: 'signup',
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
    SignupComponent,
    NavigationComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    RouterModule.forRoot(routes)
  ],
  providers: [{
    provide: HttpClient,
    useClass: HttpClient
  }],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
platformBrowserDynamic().bootstrapModule(AppModule);

