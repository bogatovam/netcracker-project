import { Action } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";

export enum WorkoutActionsTypes {
  SELECT_EXERCISE = '[Workout] Select exercise',
  UNSELECT_EXERCISE = '[Workout] Unselect exercise'
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
  | UnselectExercise;
