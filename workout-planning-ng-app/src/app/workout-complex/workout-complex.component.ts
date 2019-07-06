import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthorizationService } from '../authorization/authorization.service';
import { ApiService } from '../shared/api.service';
import { Workout } from '../shared/model/workout';
import { WorkoutComplex } from '../shared/model/workout-complex';

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

  ngOnInit(): void {
    this.setAllWorkoutComplex();
  }

  setWorkoutEditable(flag: boolean): void {
    this.isWorkoutEditableFlag = flag;
  }

  setWorkoutComplexEditable(flag: boolean): void {
    this.isWorkoutComplexEditable = flag;
  }

  createWorkoutComplex(): void {
    this.selectWorkoutComplex(new WorkoutComplex(null, '', '', []));

    this.workouts = [];
    this.workoutComplexes.push(this.selectedWorkoutComplex);
    this.setWorkoutComplexEditable(true);
  }

  saveWorkoutComplex(): void {
    this.setWorkoutComplexEditable(false);
    if (this.selectedWorkoutComplex.id === null) {
      this.apiService.createWorkoutComplex(this.selectedWorkoutComplex).subscribe(
        result => {
          this.selectedWorkoutComplex.id = result.id;
        }
      );
    } else {
      this.apiService.updateWorkoutComplex(this.selectedWorkoutComplex);
    }
  }

  deleteWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.workoutComplexes.splice(this.workoutComplexes.findIndex((v, n, o) => {
      return v.id === workoutComplex.id;
    }), 1);
    if (this.selectedWorkoutComplex !== null && this.selectedWorkoutComplex.id === workoutComplex.id) {
      this.selectedWorkoutComplex = null;
    }
    this.apiService.deleteWorkoutComplex(workoutComplex).subscribe();
    this.selectAllWorkouts();
  }

  setAllWorkoutComplex(): void {
    this.apiService.getAllWorkoutComplex().subscribe(
      result => {
        this.workoutComplexes = result;
        this.selectAllWorkouts();
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

    this.workoutComplexes.forEach((v) => {
      v.workouts.forEach((w) => {
        this.workouts.push(w);
      });
    });
  }

  selectWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.selectedWorkoutComplex = workoutComplex;
    this.workouts = workoutComplex.workouts;
    this.selectedWorkout = null;
    if (this.isWorkoutComplexEditable) {
      this.setWorkoutComplexEditable(false);
    }
  }

  unselectWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.selectedWorkoutComplex = null;
    this.selectedWorkout = null;

    this.selectAllWorkouts();

    if (this.isWorkoutComplexEditable) { this.setWorkoutComplexEditable(false); }
  }

  selectWorkout(workout: Workout): void {
    this.selectedWorkout = workout;
  }

  createWorkout(): void {
    this.selectedWorkout = new Workout(null, '', '', []);
    this.selectedWorkout.exercises = [];
    this.setWorkoutEditable(true);
  }

  saveWorkout(): void {
    this.setWorkoutEditable(false);

    if (this.selectedWorkout.id === null) {
      this.apiService.createWorkout(this.selectedWorkout, this.selectedWorkoutComplex).subscribe(
        result => {
          this.selectedWorkout.id = result.id;
          this.updateData();
        }
      );
    } else {
      if (this.selectedWorkoutComplex.workouts.find(w => {
        return w.id === this.selectedWorkout.id;
      }) === undefined) {
       console.log(' if source workout complex changed');
        this.apiService.changeWorkoutComplex(this.selectedWorkout, this.selectedWorkoutComplex, this.selectedWorkoutComplex);
      }
      this.apiService.updateWorkout(this.selectedWorkout).subscribe(
        result=>{
          this.updateData();
        }
      );
    }
  }

  deleteWorkout(workout: Workout): void {

    this.apiService.deleteWorkout(workout).subscribe(
      result => {
        this.updateData();
      }
    );
    this.workouts.splice(this.workouts.findIndex((v, n, o) => {
      return v.id === workout.id;
    }), 1);
    if (this.selectedWorkout !== null && this.selectedWorkout.id === workout.id) {
      this.selectedWorkout = null;
    }
  }

  getMuscleLoad(w: Workout): string[] {
    return [];
  }

  getComplexity(w: Workout): string {
    return '';
  }

  cancel(): void {
    this.setWorkoutEditable(false);
    this.setWorkoutComplexEditable(false);

    if (this.selectedWorkoutComplex.id === null) {
      this.workoutComplexes.pop();
      this.selectedWorkoutComplex = null;
    }

    this.selectedWorkout = null;
  }

   updateData(): void {
    this.apiService.getAllWorkoutComplex().subscribe(
      result => {
        console.log("I done!");
        this.workoutComplexes = result;
        if(this.selectedWorkoutComplex!==null) {
          this.selectedWorkoutComplex = this.workoutComplexes.find((w) => {
            return w.id === this.selectedWorkoutComplex.id;
          });
          this.workouts = this.selectedWorkoutComplex.workouts;
        } else {
          this.selectAllWorkouts();
        }
      },
      error => {
        this.errorMessage = error;
      }
    );
  }
 }
