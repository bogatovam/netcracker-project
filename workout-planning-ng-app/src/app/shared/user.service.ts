import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {WorkoutComplex} from "./model/workout-complex";
import {Workout} from "./model/workout";
import {stringify} from "querystring";
import {Exercise} from "./model/exercise";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private GET_ALL_WORKOUT = 'https://localhost:8443/workout-complex/{id}/workouts';
  private GET_ALL_WORKOUT_COMPLEXES = 'https://localhost:8443/workout-complex/all';

  private BASE_URL = 'https://localhost:8443/';

  constructor(private http: HttpClient) {
  }

  getAllWorkoutComplex(): Observable<WorkoutComplex[]> {
    return this.http.get<WorkoutComplex[]>(this.GET_ALL_WORKOUT_COMPLEXES);
  }

  getAllWorkout(): Observable<Workout[]> {
    return this.http.get<Workout[]>(this.GET_ALL_WORKOUT);
  }

  getExercises(id: string): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.BASE_URL + "workout/" + id + "/exercises");
  }
}
