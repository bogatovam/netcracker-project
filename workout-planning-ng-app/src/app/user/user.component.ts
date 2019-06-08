import {Component, OnInit} from '@angular/core';
import {UserService} from '../shared/user.service';
import {WorkoutComplex} from "../shared/model/workout-complex";
import {Workout} from "../shared/model/workout";
import {TokenStorageService} from "../authorization/token-storage.service";
import {Router} from "@angular/router";
import {AuthorizationService} from "../authorization/authorization.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  editableFlag = false;
  workoutComplexes: WorkoutComplex[] = [];
  workouts: Workout[] = [];
  selectedWorkoutComplex: WorkoutComplex;
  selectedWorkout: Workout;
  searchText: string;
  errorMessage: string;

  constructor(private userService: UserService, private tokenStorage: TokenStorageService,
              private router : Router,  private authService: AuthorizationService) {
  }

  setEditable(flag: boolean){
    this.editableFlag = flag;
  }

  getAllWorkoutComplex() {
    this.userService.getAllWorkoutComplex().subscribe(
      result => {
        console.log(result);
        this.workoutComplexes = result;
      },
      error => {
        this.errorMessage = error;
      }
    );
  }

  getAllWorkout() {
    this.userService.getAllWorkout().subscribe(
      result => {
        console.log(result);
        this.workouts = result;
      },
      error => {
        this.errorMessage = error;
      }
    );
  }

  ngOnInit() {
   // this.getAllWorkout();
    this.getAllWorkoutComplex();
  }

  selectAllWorkouts() {
    this.selectedWorkoutComplex = null;
    this.workouts = [];
  }

  selectWorkoutComplex(workoutComplex: WorkoutComplex) {
    this.selectedWorkoutComplex = workoutComplex;
    this.workouts = workoutComplex.workouts;
  }
  selectWorkout(workout: Workout) {
    this.selectedWorkout = workout;
  }
  updateWorkoutComplex(workoutComplex: WorkoutComplex) {
    console.log("update!");
  }

  deleteWorkoutComplex(workoutComplex: WorkoutComplex) {

  }

  createWorkoutComplex() {

  }

  createWorkout(id: string) {

  }

    updateWorkout($event) {

    }

    deleteWorkout($event) {

    }
}
