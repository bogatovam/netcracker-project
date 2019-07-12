import { Component, OnInit } from '@angular/core';
import { FormControl } from "@angular/forms";
import { Store } from "@ngrx/store";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";
import {
  selectIsWorkoutComplexEditable,
  selectWorkout,
  selectWorkoutComplex,
  selectWorkoutComplexes,
  selectWorkouts
} from "src/app/store/selectors/workout-complex.selector";
import { AppState } from "src/app/store/state/app.state";

import * as fromWorkoutComplex from "src/app/store/actions/workout-complex.actions";

@Component({
  selector: 'app-workout-complex',
  templateUrl: './workout-complex.component.html',
  styleUrls: ['./workout-complex.component.css']
})
export class WorkoutComplexComponent implements OnInit {
  workoutComplexes: WorkoutComplex[];
  workouts: Workout[];

  selectedWorkoutComplex: WorkoutComplex;
  selectedWorkout: Workout;

  isWorkoutComplexEditable = false;

  errorMessage: string;

  nameWorkoutComplexControl: FormControl;
  descriptionWorkoutComplexControl: FormControl;
  searchTextControl: FormControl = new FormControl('');

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {
    this.store.dispatch<fromWorkoutComplex.GetAllWorkoutComplex>(new fromWorkoutComplex.GetAllWorkoutComplex());
    this.selectAllWorkouts();
    this.store.select(selectWorkoutComplexes).subscribe(workoutComplexes => this.workoutComplexes = workoutComplexes);
    this.store.select(selectWorkouts).subscribe(workouts => this.workouts = workouts);
    this.store.select(selectWorkoutComplex).subscribe(workoutComplex => this.selectedWorkoutComplex = workoutComplex);
    this.store.select(selectWorkout).subscribe(workout => {
      console.log(workout);
      this.selectedWorkout = workout;
    });
    this.store.select(selectIsWorkoutComplexEditable).subscribe(flag => this.isWorkoutComplexEditable = flag);
  }

  openFormToCreatingWorkoutComplex(): void {
    this.store.dispatch(new fromWorkoutComplex.AddTemplateToCreatingWorkoutComplex());
    this.nameWorkoutComplexControl = new FormControl(this.selectedWorkoutComplex.name);
    this.descriptionWorkoutComplexControl = new FormControl(this.selectedWorkoutComplex.description);
  }

  openFormToEditingWorkoutComplex(): void {
    this.store.dispatch(new fromWorkoutComplex.AddTemplateToEditingWorkoutComplex());
    this.nameWorkoutComplexControl = new FormControl(this.selectedWorkoutComplex.name);
    this.descriptionWorkoutComplexControl = new FormControl(this.selectedWorkoutComplex.description);
  }

  saveEditedOrCreatedWorkoutComplex(): void {
    const newWorkoutComplex = new WorkoutComplex(this.selectedWorkoutComplex.id,
      this.nameWorkoutComplexControl.value,
      this.descriptionWorkoutComplexControl.value, []);
    this.store.dispatch(new fromWorkoutComplex.SaveWorkoutComplex(newWorkoutComplex));
  }

  deleteWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.store.dispatch(new fromWorkoutComplex.DeleteWorkoutComplex(workoutComplex));
    if (this.selectedWorkoutComplex !== null && this.selectedWorkoutComplex.id === workoutComplex.id) {
      this.selectAllWorkouts();
    }
  }

  selectAllWorkouts(): void {
    this.store.dispatch<fromWorkoutComplex.GetAllWorkout>(new fromWorkoutComplex.GetAllWorkout());
  }

  selectWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.store.dispatch<fromWorkoutComplex.SelectWorkoutComplex>(new fromWorkoutComplex.SelectWorkoutComplex(workoutComplex));
  }

  selectWorkout(workout: Workout): void {
    this.selectedWorkout = workout;
  }

  openFormToCreatingWorkout(): void {
    this.selectedWorkout = new Workout(null, '', '', []);
    this.selectedWorkout.exercises = [];
  }

  deleteWorkout(workout: Workout): void {
    this.store.dispatch(new fromWorkoutComplex.DeleteWorkout(workout));
  }

  getMuscleLoad(w: Workout): string[] {
    return [];
  }

  getComplexity(w: Workout): string {
    return '';
  }

  cancel(): void {
    this.store.dispatch(new fromWorkoutComplex.CancelEditOrCreateWorkoutComplex());
    if (this.selectedWorkoutComplex === null) {
      this.workouts = [];

      this.workoutComplexes.forEach((v) => {
        v.workouts.forEach((w) => {
          this.workouts.push(w);
        });
      });
    }
  }
}
