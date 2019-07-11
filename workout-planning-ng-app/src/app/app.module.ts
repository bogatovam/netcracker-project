import { DragDropModule } from '@angular/cdk/drag-drop';
import { CdkTableModule } from "@angular/cdk/table";
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatDialogModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatPaginatorModule,
  MatSelectModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTreeModule,
} from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { EffectsModule } from "@ngrx/effects";
import { StoreModule } from "@ngrx/store";
import { AppRoutingModule } from "src/app/app-routing.module";
import { AuthorizationModule } from "src/app/authorization/authorization.module";
import { LogoutComponent } from "src/app/authorization/components/logout/logout.component";
import { reducers } from "src/app/authorization/store";
import { ApiService } from "src/app/services/api.service";

import { AppComponent } from './app.component';
import { DirectoryComponent } from './components/directory/directory.component';
import { ExerciseComponent } from './components/exercise/exercise.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { WorkoutComplexComponent } from './components/workout-complex/workout-complex.component';
import { WorkoutComponent } from './components/workout/workout.component';
import { CdkDetailRowDirective } from './shared/directives/cdk-detail-row.directive';
import { WorkoutFilterPipe } from './shared/pipes/workout-filter.pipe';

const MAT_MODULES = [
  DragDropModule,
  CdkTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatInputModule,
  MatCheckboxModule
];

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
    MAT_MODULES
  ],
  declarations: [
    AppComponent,
    NavigationComponent,
    DirectoryComponent,
    WorkoutComponent,
    WorkoutComplexComponent,
    ExerciseComponent,
    CdkDetailRowDirective,
    WorkoutFilterPipe
  ],
  exports: [MAT_MODULES],
  providers: [ApiService],
  bootstrap: [AppComponent],
  entryComponents: [LogoutComponent]
})
export class AppModule {
}
