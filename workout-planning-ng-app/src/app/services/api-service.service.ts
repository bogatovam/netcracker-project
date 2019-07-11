import { Injectable } from '@angular/core';

export class Paths {
  private static BASE_URL = 'http://localhost:8080';
  private static GET_ALL_EXERCISES = 'http://localhost:8080/free/exercises/all';
  private static GET_ALL_EXERCISES_LOAD = 'http://localhost:8080/free/exercises/load';
  private static GET_ALL_WORKOUT = 'http://localhost:8080/workout/all';
  private static GET_ALL_WORKOUT_COMPLEXES = 'http://localhost:8080/workout-complex/all';
  private static CREATE_WORKOUT_COMPLEX = Paths.BASE_URL + "/workout-complex/create";
  private static CREATE_WORKOUT = Paths.BASE_URL + "/workout/create";

  private static WORKOUT_COMPLEX_BY_ID = Paths.BASE_URL + "/workout-complex/";
  private static WORKOUT_BY_ID  = Paths.BASE_URL + "/workout/";
  private static EXERCISE_BY_ID = Paths.BASE_URL + "/workout/";

}

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor() {
  }
}
