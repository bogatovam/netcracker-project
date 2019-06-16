import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {LoginComponent} from './authorization/login/login.component';

import {httpInterceptorProviders} from './authorization/authorization-interceptor';
import {RouterModule, Routes} from '@angular/router';
import {SignupComponent} from './authorization/signup/signup.component';
import {NavigationComponent} from './navigation/navigation.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {WorkoutComponent} from './workout-complex/workout/workout.component';
import {DirectoryComponent} from './directory/directory.component';
import {WorkoutComplexComponent} from './workout-complex/workout-complex.component';
import {ExerciseComponent} from './directory/exercise/exercise.component';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
} from '@angular/material';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CdkDetailRowDirective} from './directory/cdk-detail-row.directive';

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

const modules = [
  CdkTableModule,
  CdkTreeModule,
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatStepperModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule
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
    ExerciseComponent,
    CdkDetailRowDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    NgbModule,
    BrowserAnimationsModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    modules
  ],
  exports: [modules],

  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}

platformBrowserDynamic().bootstrapModule(AppModule);
