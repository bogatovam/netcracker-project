import {Component, OnInit} from '@angular/core';
import {WorkoutComplex} from '../shared/model/workout-complex';
import {Workout} from '../shared/model/workout';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {AuthorizationService} from '../authorization/authorization.service';

const workoutComplexes: WorkoutComplex[] = [{
  id: "1",
  name: "workout complex 1",
  description: "dscr1",
  workouts: [
    {
      id: "workout1",
      name: "name1",
      description: "Описание Описание Описание Описание Описание Описание Описание",
      exercises: [

        {
          id: 'id1',
          name: 'name',
          description: {technique: 'description1', features: ''},
          measureList: ['measure'],
          infForRecommendation: {
            complexity: 0.55,
            muscleLoad: new Map([
              ['hips', 0.3],
              ['biceps', 0.3],
              ['abs', 0.3],
              ['chest', 0.5],
              ['shoulders', 0.3],
              ['back', 0.3]
            ]),
          }
        },
        {
          id: 'id2',
          name: 'name2',
          description: {technique: 'description2', features: ''},
          measureList: [''],
          infForRecommendation: {
            complexity: 0.55,
            muscleLoad: new Map([
              ['hips', 0.3],
              ['biceps', 0.5],
              ['abs', 0.3],
              ['chest', 0.3],
              ['shoulders', 0.3],
              ['back', 0.3]
            ]),
          }
        },
        {
          id: 'id3',
          name: 'name3',
          description: {technique: 'description3', features: ''},
          measureList: [''],
          infForRecommendation: {
            complexity: 0.35,
            muscleLoad: new Map([
              ['hips', 0.5],
              ['biceps', 0.3],
              ['abs', 0.3],
              ['chest', 0.3],
              ['shoulders', 0.3],
              ['back', 0.3]
            ]),
          }
        }
      ]
    },{
      id: "workout2",
      name: "name2",
      description: "",
      exercises: []
    },{
      id: "workout1",
      name: "name3",
      description: "",
      exercises: []
    },{
      id: "workout1",
      name: "name4",
      description: "",
      exercises: []
    }
  ]
}];

@Component({
  selector: 'app-workout-complex',
  templateUrl: './workout-complex.component.html',
  styleUrls: ['./workout-complex.component.css']
})
export class WorkoutComplexComponent implements OnInit {
  editableFlag = false;
  workoutComplexes: WorkoutComplex[] = workoutComplexes;
  workouts: Workout[] = [];
  selectedWorkoutComplex: WorkoutComplex;
  selectedWorkout: Workout = null;
  errorMessage: string;

  isEditable:boolean=false;

  constructor(private apiService: ApiService, private authService: AuthorizationService,
              private router: Router) {
  }

  ngOnInit() {
    //this.setAllWorkoutComplex();
  }

  setEditable(flag: boolean): void {
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

  selectWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.selectedWorkoutComplex = workoutComplex;
    this.workouts = workoutComplex.workouts;
    this.selectedWorkout = null;
  }

  selectWorkout(workout: Workout): void {
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

  getMuscleLoad(w: Workout) {
    return [];
  }

  getComplexity(w: Workout) {
    return [];
  }
}
