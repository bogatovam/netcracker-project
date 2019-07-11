import { HttpClientModule } from "@angular/common/http";
import { NgModule } from '@angular/core';
import { MatButtonModule, MatMenuModule, MatToolbarModule } from "@angular/material";
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { EffectsModule } from "@ngrx/effects";
import { StoreModule } from "@ngrx/store";
import { AppRoutingModule } from "src/app/app-routing.module";
import { AuthorizationModule } from "src/app/authorization/authorization.module";
import { LogoutComponent } from "src/app/authorization/components/logout/logout.component";
import { reducers } from "src/app/authorization/store";

import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { DirectoryComponent } from './components/directory/directory.component';
import { WorkoutComponent } from './components/workout/workout.component';
import { WorkoutComplexComponent } from './components/workout-complex/workout-complex.component';
import { ExerciseComponent } from './components/exercise/exercise.component';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot([]),
    MatMenuModule,
    MatToolbarModule,
    MatButtonModule,
    AuthorizationModule,
    AppRoutingModule,
  ],
  declarations: [
    AppComponent,
    NavigationComponent,
    DirectoryComponent,
    WorkoutComponent,
    WorkoutComplexComponent,
    ExerciseComponent
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [LogoutComponent]
})
export class AppModule {
}
