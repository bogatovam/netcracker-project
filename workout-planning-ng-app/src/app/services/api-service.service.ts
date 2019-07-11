import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { Exercise } from "src/app/models/exercise";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";

export class Paths {
  public static BASE_URL = 'http://localhost:8080';

  public static EXERCISE_BY_ID = Paths.BASE_URL + "/free/exercise/";
  public static GET_ALL_EXERCISES = Paths.BASE_URL + '/free/exercises/all';
  public static GET_ALL_EXERCISES_LOAD = Paths.BASE_URL + '/free/exercises/load';

  public static WORKOUT_BY_ID = Paths.BASE_URL + "/workout/";
  public static CREATE_WORKOUT = Paths.BASE_URL + "/workout/create/";
  public static GET_ALL_WORKOUT = Paths.BASE_URL + '/workout/all';
  public static GET_SOURCE_WORKOUT_COMPLEX_BY_ID = Paths.BASE_URL + "/workout/complex/";
  public static GET_EXERCISES_BY_ID = Paths.BASE_URL + "/workout/exercises/";

  public static UPDATE_WORKOUT = Paths.BASE_URL + "/workout/update";
  public static CHANGE_SOURCE_WORKOUT_COMPLEX = Paths.BASE_URL + "/workout/change/";
  public static DELETE_WORKOUT_BY_ID = Paths.BASE_URL + "/workout/delete/";

  public static WORKOUT_COMPLEX_BY_ID = Paths.BASE_URL + "/workout-complex/";
  public static CREATE_WORKOUT_COMPLEX = Paths.BASE_URL + "/workout-complex/create";
  public static GET_ALL_WORKOUT_COMPLEXES = Paths.BASE_URL + '/workout-complex/all';
  public static UPDATE_WORKOUT_COMPLEX = Paths.BASE_URL + "/workout-complex/update";
  public static DELETE_WORKOUT_COMPLEX_BY_ID = "/workout-complex/delete/";
}

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor(private http: HttpClient) {
  }

  getAllWorkoutComplex(): Observable<WorkoutComplex[]> {
    return this.http.get<WorkoutComplex[]>(Paths.GET_ALL_WORKOUT_COMPLEXES);
  }

  getAllWorkout(): Observable<Workout[]> {
    return this.http.get<Workout[]>(Paths.GET_ALL_WORKOUT);
  }

  getWorkoutExercises(id: string): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(Paths.GET_EXERCISES_BY_ID + id);
  }

  getAllExercises(): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(Paths.GET_ALL_EXERCISES);
  }

  getMuscleLoad(): Observable<string[]> {
    return this.http.get<string[]>(Paths.GET_ALL_EXERCISES_LOAD);
  }

  createWorkoutComplex(workoutComplex: WorkoutComplex): Observable<WorkoutComplex> {
    return this.http.post<WorkoutComplex>(Paths.CREATE_WORKOUT_COMPLEX, workoutComplex);
  }

  deleteWorkoutComplex(workoutComplex: WorkoutComplex): Observable<WorkoutComplex> {
    return this.http.delete<WorkoutComplex>(Paths.DELETE_WORKOUT_COMPLEX_BY_ID + workoutComplex.id);
  }

  updateWorkoutComplex(workoutComplex: WorkoutComplex): Observable<WorkoutComplex> {
    return this.http.put<WorkoutComplex>(Paths.UPDATE_WORKOUT_COMPLEX, workoutComplex);
  }

  createWorkout(workout: Workout, workoutComplex: WorkoutComplex): Observable<Workout> {
    return this.http.post<Workout>(Paths.CREATE_WORKOUT + workoutComplex.id, workout);
  }

  deleteWorkout(workout: Workout): Observable<Workout> {
    return this.http.delete<Workout>(Paths.DELETE_WORKOUT_BY_ID + workout.id);
  }

  changeWorkoutComplex(workout: Workout, oldWorkoutComplex: WorkoutComplex, newWorkoutComplex: WorkoutComplex)
    : Observable<Workout> {
    return this.http.get<Workout>(Paths.CHANGE_SOURCE_WORKOUT_COMPLEX +
      workout.id + '/' + oldWorkoutComplex.id + '/' + newWorkoutComplex.id);
  }

  updateWorkout(workout: Workout): Observable<Workout> {
    return this.http.post<Workout>(Paths.UPDATE_WORKOUT, workout);
  }

  getSourceWorkoutComplex(workout: Workout): Observable<WorkoutComplex> {
    return this.http.get<WorkoutComplex>(Paths.GET_SOURCE_WORKOUT_COMPLEX_BY_ID + workout.id);
  }
}
