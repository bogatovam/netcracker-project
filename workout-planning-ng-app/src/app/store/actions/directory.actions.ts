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
  UNSELECT_EXERCISE = '[Directory] Unselect exercise',
  SWITCH_TO_CARD = '[Directory] Switch to card',
  SWITCH_TO_TABLE = '[Directory] Switch to table',
  SET_GROUPED_BY = '[Directory] Set grouped by',
  SET_TABLE_DISPLAYED_STYLE = '[Directory] Set table displayed style',
  SET_CARD_DISPLAYED_STYLE = '[Directory] Set card displayed style',
  APPLY_GROUP_FILTER = '[Directory] Apply group filter',
}

export class GetAllExercises implements Action {
  readonly type = DirectoryActionsTypes.GET_ALL_EXERCISES;
}

export class GetAllExercisesSuccess implements Action {
  readonly type = DirectoryActionsTypes.GET_ALL_EXERCISES_SUCCESS;

  constructor(public payload: Exercise[]) { }
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

  constructor(public payload: Exercise[]) { }
}

export class SelectExercise implements Action {
  readonly type = DirectoryActionsTypes.SELECT_EXERCISE;

  constructor(public payload: Exercise) { }
}

export class UnselectExercise implements Action {
  readonly type = DirectoryActionsTypes.UNSELECT_EXERCISE;

  constructor(public payload: Exercise) { }
}

export class SwitchToCard implements Action {
  readonly type = DirectoryActionsTypes.SWITCH_TO_CARD;
}

export class SwitchToTable implements Action {
  readonly type = DirectoryActionsTypes.SWITCH_TO_TABLE;
}

export class SetGroupedBy implements Action {
  readonly type = DirectoryActionsTypes.SET_GROUPED_BY;
  constructor(public  payload: string) {}
}

export class SetTableDisplayedStyle implements Action {
  readonly type = DirectoryActionsTypes.SET_TABLE_DISPLAYED_STYLE;
}

export class SetCardDisplayedStyle implements Action {
  readonly type = DirectoryActionsTypes.SET_CARD_DISPLAYED_STYLE;
}

export class ApplyGroupFilter implements Action {
  readonly type = DirectoryActionsTypes.APPLY_GROUP_FILTER;
  constructor(public  payload: string) {}
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
  | UnselectExercise
  | SwitchToCard
  | SwitchToTable
  | SetTableDisplayedStyle
  | SetCardDisplayedStyle
  | ApplyGroupFilter
  | SetGroupedBy;
