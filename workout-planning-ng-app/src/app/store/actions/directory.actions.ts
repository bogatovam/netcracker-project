import { SelectionModel } from "@angular/cdk/collections";
import { MatTableDataSource } from "@angular/material";
import { Action } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";

export enum DirectoryActionsTypes {
  GET_ALL_EXERCISES = '[Directory] Get all exercise',
  GET_ALL_EXERCISES_SUCCESS = '[Directory] Get all exercise success',
  GET_ALL_EXERCISES_FAILURE = '[Directory] Get all exercise failure',
  GET_MUSCLE_LOAD = '[Directory] Get muscle load',
  GET_MUSCLE_LOAD_SUCCESS = '[Directory] Get muscle load success',
  GET_MUSCLE_LOAD_FAILURE = '[Directory] Get muscle load failure',
  SET_SELECTED_EXERCISES = '[Directory] Set selected exercises',
  SELECT_EXERCISE = '[Directory] Select exercise',
  UNSELECT_EXERCISE = '[Directory] Unselect exercise'
}

export class GetAllExercises implements Action {
  readonly type = DirectoryActionsTypes.GET_ALL_EXERCISES;
}

export class GetAllExercisesSuccess implements Action {
  readonly type = DirectoryActionsTypes.GET_ALL_EXERCISES_SUCCESS;

  constructor(public payload: MatTableDataSource<Exercise>) { }
}

export class GetAllExercisesFailure implements Action {
  readonly type = DirectoryActionsTypes.GET_ALL_EXERCISES_FAILURE;

  constructor(public payload: string) { }
}

export class GetMuscleLoad implements Action {
  readonly type = DirectoryActionsTypes.GET_MUSCLE_LOAD;
}

export class GetMuscleLoadSuccess implements Action {
  readonly type = DirectoryActionsTypes.GET_MUSCLE_LOAD_SUCCESS;

  constructor(public payload: string[]) { }
}

export class GetMuscleLoadFailure implements Action {
  readonly type = DirectoryActionsTypes.GET_MUSCLE_LOAD_FAILURE;

  constructor(public payload: string) { }
}
export class SetSelectedExercises implements Action {
  readonly type = DirectoryActionsTypes.SET_SELECTED_EXERCISES;

  constructor(public payload: SelectionModel<Exercise>) { }
}

export class SelectExercise implements Action {
  readonly type = DirectoryActionsTypes.SELECT_EXERCISE;

  constructor(public payload: Exercise) { }
}

export class UnselectExercise implements Action {
  readonly type = DirectoryActionsTypes.UNSELECT_EXERCISE;

  constructor(public payload: Exercise) { }
}

export type DirectoryActions =
  GetAllExercisesSuccess
  | GetAllExercises
  | GetAllExercisesFailure
  | GetMuscleLoad
  | GetMuscleLoadSuccess
  | GetMuscleLoadFailure
  | SetSelectedExercises
  | SelectExercise
  | UnselectExercise;
