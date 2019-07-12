import { Action } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";
import { Workout } from "src/app/models/workout";
import {WorkoutComplex} from "src/app/models/workout-complex";

export enum WorkoutActionsTypes {
  LOAD_WORKOUT = '[Workout] Load workout',
  LOAD_WORKOUT_SUCCESS = '[Workout] Load workout success',
  LOAD_WORKOUT_FAILURE = '[Workout] Load workout failure',
  CREATE_WORKOUT = '[Workout] Create workout ',
  CREATE_WORKOUT_SUCCESS = '[Workout] Create workout success',
  CREATE_WORKOUT_FAILURE = '[Workout] Create workout failure',
  UPDATE_WORKOUT = '[Workout] Update workout ',
  UPDATE_WORKOUT_SUCCESS = '[Workout] Update workout success',
  UPDATE_WORKOUT_FAILURE = '[Workout] Update workout failure',
  DELETE_WORKOUT = '[Workout] Delete workout ',
  DELETE_WORKOUT_SUCCESS = '[Workout] Delete workout success',
  DELETE_WORKOUT_FAILURE = '[Workout] Delete workout failure',
  SELECT_EXERCISE = '[Workout] Select exercise',
  UNSELECT_EXERCISE = '[Workout] Unselect exercise'
}

export class LoadWorkout implements Action {
  readonly type: WorkoutActionsTypes.LOAD_WORKOUT;
  constructor(public payload: string) { }
}

export class LoadWorkoutSuccess implements Action {
  readonly type: WorkoutActionsTypes.LOAD_WORKOUT_SUCCESS;
  constructor(public payload: { workout: Workout, workoutComplex: WorkoutComplex}) { }
}

export class LoadWorkoutFailure implements Action {
  readonly type: WorkoutActionsTypes.LOAD_WORKOUT_FAILURE;
  constructor(public payload: string) { }
}

export class SelectExercise implements Action {
  readonly type: WorkoutActionsTypes.SELECT_EXERCISE;
  constructor(public payload: Exercise) { }
}

export class UnselectExercise implements Action {
  readonly type: WorkoutActionsTypes.UNSELECT_EXERCISE;
  constructor(public payload: Exercise) { }
}

export type WorkoutActions =
    SelectExercise
  | UnselectExercise
  | LoadWorkout
  | LoadWorkoutSuccess
  | LoadWorkoutFailure
  ;
