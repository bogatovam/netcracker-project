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
  MatPaginatorModule, MatRippleModule,
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
import { ApiService } from "src/app/services/api.service";
import { CdkDetailRowDirective } from "src/app/shared/directives/cdk-detail-row.directive";
import {reducers} from "src/app/store";
import {DirectoryEffects} from "src/app/store/effects/directory.effects";

import { AppComponent } from './app.component';
import { DirectoryComponent } from './components/directory/directory.component';
import { ExerciseComponent } from './components/exercise/exercise.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { WorkoutComplexComponent } from './components/workout-complex/workout-complex.component';
import { WorkoutComponent } from './components/workout/workout.component';
import { WorkoutFilterPipe } from './shared/pipes/workout-filter.pipe';

const MAT_MODULES = [
  DragDropModule,
  CdkTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule,
  MatInputModule,
  MatCheckboxModule,
  MatRippleModule
];

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot([DirectoryEffects]),
    MatMenuModule,
    MatToolbarModule,
    MatButtonModule,
    AuthorizationModule,
    AppRoutingModule,
    BrowserAnimationsModule,
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
