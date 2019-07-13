import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DirectoryComponent } from "src/app/components/directory/directory.component";
import { WorkoutComplexComponent } from "src/app/components/workout-complex/workout-complex.component";
import { WorkoutComponent } from "src/app/components/workout/workout.component";

const APP_ROUTES = [
  {path: '', pathMatch: 'full', redirectTo: '/home'},
  {path: 'directory', component: DirectoryComponent},
  {path: 'workout-complex', component: WorkoutComplexComponent},
  {path: 'workout-complex/:workoutComplexId/workout/:id', component: WorkoutComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(APP_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
