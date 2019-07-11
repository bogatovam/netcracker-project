import { Action } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";

export enum DirectoryActionsTypes {
  GET_ALL_EXERCISES = '[Directory] Get all exercise',
  GET_ALL_EXERCISES_SUCCESS = '[Directory] Get all exercise success',
  GET_ALL_EXERCISES_FAILURE = '[Directory] Get all exercise failure'
}

export class GetAllExercises implements Action {
  readonly type: DirectoryActionsTypes.GET_ALL_EXERCISES;
}

export class GetAllExercisesSuccess implements Action {
  readonly type: DirectoryActionsTypes.GET_ALL_EXERCISES_SUCCESS;
  constructor(public payload: Exercise[]) {}
}

export class GetAllExercisesFailure implements Action {
  readonly type: DirectoryActionsTypes.GET_ALL_EXERCISES_FAILURE;
}

export type DirectoryActions = GetAllExercisesSuccess | GetAllExercises | GetAllExercisesFailure;
