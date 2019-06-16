import { Component, OnInit } from '@angular/core';
import {WorkoutComplex} from '../shared/model/workout-complex';
import {Workout} from '../shared/model/workout';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {AuthorizationService} from '../authorization/authorization.service';

@Component({
  selector: 'app-workout-complex',
  templateUrl: './workout-complex.component.html',
  styleUrls: ['./workout-complex.component.css']
})
export class WorkoutComplexComponent implements OnInit {
  editableFlag = false;
  workoutComplexes: WorkoutComplex[] = [];
  workouts: Workout[] = [];
  selectedWorkoutComplex: WorkoutComplex;
  selectedWorkout: Workout;
  errorMessage: string;

  constructor(private apiService: ApiService, private authService: AuthorizationService,
              private router: Router) {
  }

  ngOnInit() {
    this.setAllWorkoutComplex();
  }

  setEditable(flag: boolean): void  {
    this.editableFlag = flag;
  }

  setAllWorkoutComplex() {
    this.apiService.getAllWorkoutComplex().subscribe(
      result => {
        console.log(result);
        this.workoutComplexes = result;
      },
      error => {
        this.errorMessage = error;
      }
    );
  }

  setAllWorkout() {
    this.apiService.getAllWorkout().subscribe(
      result => {
        console.log(result);
        this.workouts = result;
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
  }

  selectWorkoutComplex(workoutComplex: WorkoutComplex): void  {
    this.selectedWorkoutComplex = workoutComplex;
    this.workouts = workoutComplex.workouts;
    this.selectedWorkout = null;
  }

  selectWorkout(workout: Workout): void  {
    this.selectedWorkout = workout;
  }

  updateWorkoutComplex(workoutComplex: WorkoutComplex): void {
  }

  deleteWorkoutComplex(workoutComplex: WorkoutComplex): void {

  }

  createWorkoutComplex(): void {

  }

  createWorkout(id: string): void {

  }

  updateWorkout($event): void {

  }

  deleteWorkout($event): void {

  }
}
