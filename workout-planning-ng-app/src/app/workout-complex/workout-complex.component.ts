import {Component, OnInit} from '@angular/core';
import {WorkoutComplex} from '../shared/model/workout-complex';
import {Workout} from '../shared/model/workout';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {AuthorizationService} from '../authorization/authorization.service';
import {angularCoreEnv} from "@angular/core/src/render3/jit/environment";

@Component({
  selector: 'app-workout-complex',
  templateUrl: './workout-complex.component.html',
  styleUrls: ['./workout-complex.component.css']
})
export class WorkoutComplexComponent implements OnInit {
  workoutComplexes: WorkoutComplex[] = [];
  workouts: Workout[] = [];
  selectedWorkoutComplex: WorkoutComplex;
  selectedWorkout: Workout = null;
  errorMessage: string;
  searchText: string;

  isWorkoutComplexEditable = false;
  isWorkoutEditableFlag = false;

  constructor(private apiService: ApiService, private authService: AuthorizationService,
              private router: Router) {
  }

  ngOnInit() {
    this.setAllWorkoutComplex();
    this.selectAllWorkouts();
  }

  setWorkoutEditable(flag: boolean): void {
    this.isWorkoutEditableFlag = flag;
  }

  setWorkoutComplexEditable(flag: boolean): void {
    this.isWorkoutComplexEditable = flag;
  }

  createWorkoutComplex(): void {
    let workoutComplex = new WorkoutComplex('', '', '', []);
    workoutComplex.description = '';
    workoutComplex.workouts = [];
    this.selectWorkoutComplex(workoutComplex);

    this.workouts = [];
    this.workoutComplexes.push(this.selectedWorkoutComplex);
    this.setWorkoutComplexEditable(true);
  }

  saveWorkoutComplex(): void {
    this.setWorkoutComplexEditable(false);
   // if (this.selectedWorkoutComplex.id === null) {
      this.apiService.createWorkoutComplex(this.selectedWorkoutComplex).subscribe(
        result => {
          this.selectedWorkoutComplex.id = result.id;
        }
      );
   /// } else {
   //   this.apiService.updateWorkoutComplex(this.selectedWorkoutComplex);
  //  }
  }

  deleteWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.workoutComplexes.splice(this.workoutComplexes.findIndex((v, n, o) => {
      return v.id == workoutComplex.id;
    }), 1);
    if (this.selectedWorkoutComplex !== null && this.selectedWorkoutComplex.id == workoutComplex.id)
      this.selectedWorkoutComplex = null;
    this.apiService.deleteWorkoutComplex(workoutComplex).subscribe();
  }

  setAllWorkoutComplex() {
    this.apiService.getAllWorkoutComplex().subscribe(
      result => {
        this.workoutComplexes = result;
      },
      error => {
        this.errorMessage = error;
      }
    );
  }

  selectAllWorkouts(): void {
    this.selectedWorkoutComplex = null;
    this.selectedWorkout = null;
    this.workouts = [];

    this.workoutComplexes.forEach((v, n, o) => {
      v.workouts.forEach((w, n, o) => {
        this.workouts.push(w);
      });
    });
  }

  selectWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.selectedWorkoutComplex = workoutComplex;
    this.workouts = workoutComplex.workouts;
    this.selectedWorkout = null;
    if (this.isWorkoutComplexEditable) this.setWorkoutComplexEditable(false);
  }

  unselectWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.selectedWorkoutComplex = null;
    this.selectedWorkout = null;

    this.selectAllWorkouts();

    if (this.isWorkoutComplexEditable) this.setWorkoutComplexEditable(false);
  }

  selectWorkout(workout: Workout): void {
    this.selectedWorkout = workout;
  }

  createWorkout(): void {
    this.selectedWorkout = new Workout('', '', '', []);
    this.selectedWorkout.exercises = [];
    this.setWorkoutEditable(true);
  }

  saveWorkout(): void {
    this.selectedWorkout = new Workout('', '', '', []);
    this.selectedWorkout.exercises = [];
    this.setWorkoutEditable(true);
  }

  updateWorkout(workout: Workout): void {

  }

  deleteWorkout(workout: Workout): void {
    this.workouts.splice(this.workouts.findIndex((v, n, o) => {
      return v.id == workout.id;
    }), 1);
    if (this.selectedWorkout !== null && this.selectedWorkout.id == workout.id)
      this.selectedWorkout = null;
  }

  getMuscleLoad(w: Workout) {
    return [];
  }

  getComplexity(w: Workout) {
    return [];
  }

  submit(): void {
    this.setWorkoutEditable(false);
  }

  cancel(): void {
    this.setWorkoutEditable(false);
    this.setWorkoutComplexEditable(false);
    if (this.selectedWorkoutComplex.id === null) {
      this.workoutComplexes.pop();
      this.selectedWorkoutComplex = null;
    }
  }
}
