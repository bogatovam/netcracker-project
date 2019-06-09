import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './authorization/login/login.component';

import { httpInterceptorProviders } from './authorization/authorization-interceptor';
import {RouterModule, Routes} from "@angular/router";
import {SignupComponent} from "./authorization/signup/signup.component";
import {NavigationComponent} from "./navigation/navigation.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { WorkoutComponent } from './workout-complex/workout/workout.component';
import { DirectoryComponent } from './directory/directory.component';
import { WorkoutComplexComponent } from './workout-complex/workout-complex.component';
import { ExerciseComponent } from './directory/exercise/exercise.component';

const routes: Routes = [
  {
    path: 'home',
    component: DirectoryComponent
  },
  {
    path: 'workout-complex',
    component: WorkoutComplexComponent
  },
  {
    path: 'authentication/signin',
    component: LoginComponent
  },
  {
    path: 'authentication/signup',
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
    SignupComponent,
    NavigationComponent,
    WorkoutComponent,
    DirectoryComponent,
    WorkoutComplexComponent,
    ExerciseComponent
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

