import { Injectable } from '@angular/core';

export class Paths {
  public static BASE_URL = 'http://localhost:8080';

  public static EXERCISE_BY_ID = Paths.BASE_URL + "/free/exercise/";
  public static GET_ALL_EXERCISES = Paths.BASE_URL + '/free/exercises/all';
  public static GET_ALL_EXERCISES_LOAD = Paths.BASE_URL + '/free/exercises/load';

  public static WORKOUT_BY_ID = Paths.BASE_URL + "/workout/";
  public static CREATE_WORKOUT = Paths.BASE_URL + "/workout/create";
  public static GET_ALL_WORKOUT = Paths.BASE_URL + '/workout/all';
  public static UPDATE_WORKOUT = Paths.BASE_URL + "/workout/update";
  public static CHANGE_SOURCE_WORKOUT_COMPLEX = Paths.BASE_URL + "/workout/change.";
  public static DELETE_WORKOUT_BY_ID = Paths.BASE_URL + "/workout/delete/";

  public static WORKOUT_COMPLEX_BY_ID = Paths.BASE_URL + "/workout-complex/";
  public static CREATE_WORKOUT_COMPLEX = Paths.BASE_URL + "/workout-complex/create";
  public static GET_ALL_WORKOUT_COMPLEXES = Paths.BASE_URL + '/workout-complex/all';
  public static DELETE_WORKOUT_COMPLEX_BY_ID = "/workout-complex/delete/";

}

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor() {
  }
}
