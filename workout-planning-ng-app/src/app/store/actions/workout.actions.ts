import { Action } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";

export enum WorkoutActionsTypes {
  LOAD_WORKOUT = '[Workout] Load workout',
  LOAD_WORKOUT_SUCCESS = '[Workout] Load workout success',
  LOAD_WORKOUT_FAILURE = '[Workout] Load workout failure',
  LOAD_SOURCE_WORKOUT_COMPLEX = '[Workout] Load source workout complex',
  LOAD_SOURCE_WORKOUT_COMPLEX_SUCCESS = '[Workout] Load source workout complex success',
  LOAD_SOURCE_WORKOUT_COMPLEX_FAILURE = '[Workout] Load source workout complex failure',
  CREATE_WORKOUT = '[Workout] Create workout ',
  CREATE_WORKOUT_SUCCESS = '[Workout] Create workout success',
  CREATE_WORKOUT_FAILURE = '[Workout] Create workout failure',
  UPDATE_WORKOUT = '[Workout] Update workout ',
  UPDATE_WORKOUT_SUCCESS = '[Workout] Update workout success',
  UPDATE_WORKOUT_FAILURE = '[Workout] Update workout failure',
  DELETE_WORKOUT = '[Workout] Delete workout ',
  DELETE_WORKOUT_SUCCESS = '[Workout] Delete workout success',
  DELETE_WORKOUT_FAILURE = '[Workout] Delete workout failure',
  CHANGE_SOURCE_WORKOUT_COMPLEX = '[Workout] Change source workout complex',
  CHANGE_SOURCE_WORKOUT_COMPLEX_SUCCESS = '[Workout] Change source workout complex success',
  CHANGE_SOURCE_WORKOUT_COMPLEX_FAILURE = '[Workout] Change source workout complex failure',
  SET_EDITABLE = '[Workout] Set editable',
  SELECT_EXERCISE = '[Workout] Select exercise',
  UNSELECT_EXERCISE = '[Workout] Unselect exercise'
}

export class LoadWorkout implements Action {
  readonly type = WorkoutActionsTypes.LOAD_WORKOUT;
  constructor(public payload: string) { }
}

export class LoadWorkoutSuccess implements Action {
  readonly type = WorkoutActionsTypes.LOAD_WORKOUT_SUCCESS;
  constructor(public payload: { workout: Workout, workoutComplex: WorkoutComplex}) { }
}

export class LoadWorkoutFailure implements Action {
  readonly type = WorkoutActionsTypes.LOAD_WORKOUT_FAILURE;
  constructor(public payload: string) { }
}

export class LoadSourceWorkoutComplex implements Action {
  readonly type = WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX;
  constructor(public payload: string) { }
}

export class LoadSourceWorkoutComplexSuccess implements Action {
  readonly type = WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX_SUCCESS;
  constructor(public payload: WorkoutComplex) { }
}

export class LoadSourceWorkoutComplexFailure implements Action {
  readonly type = WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX_FAILURE;
  constructor(public payload: string) { }
}
export class ChangeSourceWorkoutComplex implements Action {
  readonly type = WorkoutActionsTypes.CHANGE_SOURCE_WORKOUT_COMPLEX;
  constructor(public payload: {
    oldWorkoutComplexId: string,
    newWorkoutComplexId: string,
    workoutId: string }) { }
}

export class ChangeSourceWorkoutComplexSuccess implements Action {
  readonly type = WorkoutActionsTypes.CHANGE_SOURCE_WORKOUT_COMPLEX_SUCCESS;
  constructor(public payload: WorkoutComplex) { }
}

export class ChangeSourceWorkoutComplexFailure implements Action {
  readonly type = WorkoutActionsTypes.CHANGE_SOURCE_WORKOUT_COMPLEX_FAILURE;
  constructor(public payload: string) { }
}

export class SelectExercise implements Action {
  readonly type = WorkoutActionsTypes.SELECT_EXERCISE;
  constructor(public payload: Exercise) { }
}

export class UnselectExercise implements Action {
  readonly type = WorkoutActionsTypes.UNSELECT_EXERCISE;
  constructor(public payload: Exercise) { }
}
export class SetEditable implements Action {
  readonly type = WorkoutActionsTypes.SET_EDITABLE;
  constructor(public payload: boolean) { }
}

export class DeleteWorkout implements  Action {
  public readonly type = WorkoutActionsTypes.DELETE_WORKOUT;
  constructor(public payload: Workout) {}
}

export class DeleteWorkoutSuccess implements  Action {
  public readonly type = WorkoutActionsTypes.DELETE_WORKOUT_SUCCESS;
  constructor(public payload: Workout) {}
}

export class DeleteWorkoutFailure implements  Action {
  public readonly type = WorkoutActionsTypes.DELETE_WORKOUT_FAILURE;
  constructor(public payload: string) {}
}
export class CreateWorkout implements  Action {
  public readonly type = WorkoutActionsTypes.CREATE_WORKOUT;
  constructor(public payload: { workout: Workout, workoutComplex: WorkoutComplex} ) {}
}

export class CreateWorkoutSuccess implements  Action {
  public readonly type = WorkoutActionsTypes.CREATE_WORKOUT_SUCCESS;
  constructor(public payload: { workout: Workout, workoutComplex: WorkoutComplex}) {}
}

export class CreateWorkoutFailure implements  Action {
  public readonly type = WorkoutActionsTypes.CREATE_WORKOUT_FAILURE;
  constructor(public payload: string) {}
}
export class UpdateWorkout implements  Action {
  public readonly type = WorkoutActionsTypes.UPDATE_WORKOUT;
  constructor(public payload: Workout) {}
}

export class UpdateWorkoutSuccess implements  Action {
  public readonly type = WorkoutActionsTypes.UPDATE_WORKOUT_SUCCESS;
  constructor(public payload: Workout) {}
}

export class UpdateWorkoutFailure implements  Action {
  public readonly type = WorkoutActionsTypes.UPDATE_WORKOUT_FAILURE;
  constructor(public payload: string) {}
}
export type WorkoutActions =
    SelectExercise
  | UnselectExercise
  | LoadWorkout
  | LoadWorkoutSuccess
  | LoadWorkoutFailure
  | LoadSourceWorkoutComplex
  | LoadSourceWorkoutComplexSuccess
  | LoadSourceWorkoutComplexFailure
  | SetEditable
  | DeleteWorkout
  | DeleteWorkoutSuccess
  | DeleteWorkoutFailure
  | CreateWorkout
  | CreateWorkoutSuccess
  | CreateWorkoutFailure
  | UpdateWorkout
  | UpdateWorkoutSuccess
  | UpdateWorkoutFailure
  | ChangeSourceWorkoutComplex
  | ChangeSourceWorkoutComplexSuccess
  | ChangeSourceWorkoutComplexFailure
  ;
