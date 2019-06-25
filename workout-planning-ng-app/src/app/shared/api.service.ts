import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {WorkoutComplex} from './model/workout-complex';
import {Workout} from './model/workout';
import {Exercise} from './model/exercise';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private BASE_URL = 'http://localhost:8080';

  private GET_ALL_EXERCISES = 'http://localhost:8080/free/exercises/all';
  private GET_ALL_EXERCISES_LOAD = 'http://localhost:8080/free/exercises/load';
  private GET_ALL_WORKOUT = 'http://localhost:8080/workout/all';
  private GET_ALL_WORKOUT_COMPLEXES = 'http://localhost:8080/workout-complex/all';
  private CREATE_WORKOUT_COMPLEX = this.BASE_URL + "/workout-complex/create";
  private WORKOUT_COMPLEX_BY_ID = this.BASE_URL + "/workout-complex/";

  constructor(private http: HttpClient) {
  }

  getAllWorkoutComplex(): Observable<WorkoutComplex[]> {
    return this.http.get<WorkoutComplex[]>(this.GET_ALL_WORKOUT_COMPLEXES);
  }

  getAllWorkout(): Observable<Workout[]> {
    return this.http.get<Workout[]>(this.GET_ALL_WORKOUT);
  }

  getExercises(id: string): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.BASE_URL + 'workout/' + id + '/exercises');
  }

  getAllExercises(): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.GET_ALL_EXERCISES);
  }

  getMuscleLoad(): Observable<string[]> {
    return this.http.get<string[]>(this.GET_ALL_EXERCISES_LOAD);
  }

  createWorkoutComplex(workoutComplex: WorkoutComplex): Observable<WorkoutComplex> {
    return this.http.post<WorkoutComplex>(this.CREATE_WORKOUT_COMPLEX, workoutComplex);
  }

  deleteWorkoutComplex(workoutComplex: WorkoutComplex): Observable<WorkoutComplex> {
    return this.http.delete<WorkoutComplex>(this.WORKOUT_COMPLEX_BY_ID
      + workoutComplex.id + "/delete");
  }

  updateWorkoutComplex(workoutComplex: WorkoutComplex): void {
    this.http.put<WorkoutComplex>(this.WORKOUT_COMPLEX_BY_ID
      + workoutComplex.id + "/name",workoutComplex.name).subscribe();

    this.http.put<WorkoutComplex>(this.WORKOUT_COMPLEX_BY_ID
      + workoutComplex.id + "/description",workoutComplex.description).subscribe();
  }


}
