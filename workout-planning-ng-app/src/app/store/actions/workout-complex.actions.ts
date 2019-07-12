import { Action } from "@ngrx/store";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";

export enum WorkoutComplexActionsTypes {
  GET_ALL_WORKOUT_COMPLEX = '[WorkoutComplex] Get all workout complex',
  GET_ALL_WORKOUT_COMPLEX_SUCCESS = '[WorkoutComplex] Get all workout complex success',
  GET_ALL_WORKOUT_COMPLEX_FAILURE = '[WorkoutComplex] Get all workout complex failure',
  GET_ALL_WORKOUT = '[WorkoutComplex] Get all workout ',
  GET_ALL_WORKOUT_SUCCESS = '[WorkoutComplex] Get all workout success',
  GET_ALL_WORKOUT_FAILURE = '[WorkoutComplex] Get all workout failure',
  CREATE_WORKOUT_COMPLEX = '[WorkoutComplex] Create workout complex ',
  CREATE_WORKOUT_COMPLEX_SUCCESS = '[WorkoutComplex] Create workout complex success',
  CREATE_WORKOUT_COMPLEX_FAILURE = '[WorkoutComplex] Create workout complex failure',
  UPDATE_WORKOUT_COMPLEX = '[WorkoutComplex] Update workout complex ',
  UPDATE_WORKOUT_COMPLEX_SUCCESS = '[WorkoutComplex] Update workout complex success',
  UPDATE_WORKOUT_COMPLEX_FAILURE = '[WorkoutComplex] Update workout complex failure',
  DELETE_WORKOUT_COMPLEX = '[WorkoutComplex] Delete workout complex ',
  DELETE_WORKOUT_COMPLEX_SUCCESS = '[WorkoutComplex] Delete workout complex success',
  DELETE_WORKOUT_COMPLEX_FAILURE = '[WorkoutComplex] Delete workout complex failure',
  CREATE_WORKOUT = '[WorkoutComplex] Create workout ',
  CREATE_WORKOUT_SUCCESS = '[WorkoutComplex] Create workout success',
  CREATE_WORKOUT_FAILURE = '[WorkoutComplex] Create workout failure',
  DELETE_WORKOUT = '[WorkoutComplex] Delete workout ',
  DELETE_WORKOUT_SUCCESS = '[WorkoutComplex] Delete workout success',
  DELETE_WORKOUT_FAILURE = '[WorkoutComplex] Delete workout failure',
  SELECT_WORKOUT_COMPLEX = '[WorkoutComplex] Select workout Complex',
  UNSELECT_WORKOUT_COMPLEX = '[WorkoutComplex] Unselect workout Complex',
  SELECT_WORKOUT = '[WorkoutComplex] Select workout ',
  UNSELECT_WORKOUT = '[WorkoutComplex] Unselect workout',
  SET_IS_WORKOUT_COMPLEX_EDITABLE = '[WorkoutComplex] Set is workout Complex editable',
  ADD_TEMPLATE_TO_CREATING_WORKOUT_COMPLEX = '[WorkoutComplex] Add template to creating workout complex',
  ADD_TEMPLATE_TO_CREATING_WORKOUT = '[WorkoutComplex] Add template to creating workout',
  SAVE_WORKOUT_COMPLEX = '[WorkoutComplex] Save workout complex',
  CANCEL_WORKOUT_COMPLEX_EDITABLE = '[WorkoutComplex] Set is workout Complex editable',
}

export class GetAllWorkoutComplex implements  Action {
  public readonly type = WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX;
}

export class GetAllWorkoutComplexSuccess implements  Action {
  public readonly type = WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX_SUCCESS;
  constructor(public payload: WorkoutComplex[]) {}
}

export class GetAllWorkoutComplexFailure implements  Action {
  public readonly type = WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX_FAILURE;
  constructor(public payload: string) {}
}

export class GetAllWorkout implements  Action {
  public readonly type = WorkoutComplexActionsTypes.GET_ALL_WORKOUT;
}

export class GetAllWorkoutSuccess implements  Action {
  public readonly type = WorkoutComplexActionsTypes.GET_ALL_WORKOUT_SUCCESS;
  constructor(public payload: Workout[]) {}
}

export class GetAllWorkoutFailure implements  Action {
  public readonly type = WorkoutComplexActionsTypes.GET_ALL_WORKOUT_FAILURE;
  constructor(public payload: string) {}
}

export class CreateWorkoutComplex implements  Action {
  public readonly type = WorkoutComplexActionsTypes.CREATE_WORKOUT_COMPLEX;
  constructor(public payload: WorkoutComplex) {}
}

export class CreateWorkoutComplexSuccess implements  Action {
  public readonly type = WorkoutComplexActionsTypes.CREATE_WORKOUT_COMPLEX_SUCCESS;
  constructor(public payload: WorkoutComplex) {}
}

export class CreateWorkoutComplexFailure implements  Action {
  public readonly type = WorkoutComplexActionsTypes.CREATE_WORKOUT_COMPLEX_FAILURE;
  constructor(public payload: string) {}
}

export class UpdateWorkoutComplex implements  Action {
  public readonly type = WorkoutComplexActionsTypes.UPDATE_WORKOUT_COMPLEX;
  constructor(public payload: WorkoutComplex) {}
}

export class UpdateWorkoutComplexSuccess implements  Action {
  public readonly type = WorkoutComplexActionsTypes.UPDATE_WORKOUT_COMPLEX_SUCCESS;
  constructor(public payload: WorkoutComplex) {}
}

export class UpdateWorkoutComplexFailure implements  Action {
  public readonly type = WorkoutComplexActionsTypes.UPDATE_WORKOUT_COMPLEX_FAILURE;
  constructor(public payload: string) {}
}

export class DeleteWorkoutComplex implements  Action {
  public readonly type = WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX;
  constructor(public payload: WorkoutComplex) {}
}

export class DeleteWorkoutComplexSuccess implements  Action {
  public readonly type = WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX_SUCCESS;
  constructor(public payload: WorkoutComplex) {}
}

export class DeleteWorkoutComplexFailure implements  Action {
  public readonly type = WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX_FAILURE;
  constructor(public payload: string) {}
}

export class DeleteWorkout implements  Action {
  public readonly type = WorkoutComplexActionsTypes.DELETE_WORKOUT;
  constructor(public payload: Workout) {}
}

export class DeleteWorkoutSuccess implements  Action {
  public readonly type = WorkoutComplexActionsTypes.DELETE_WORKOUT_SUCCESS;
  constructor(public payload: Workout) {}
}

export class DeleteWorkoutFailure implements  Action {
  public readonly type = WorkoutComplexActionsTypes.DELETE_WORKOUT_FAILURE;
  constructor(public payload: string) {}
}

export class SetIsWorkoutComplexEditable implements Action {
  readonly type = WorkoutComplexActionsTypes.SET_IS_WORKOUT_COMPLEX_EDITABLE;
  constructor(public  payload: boolean) {}
}

export class AddTemplateToCreatingWorkoutComplex implements Action {
  readonly type = WorkoutComplexActionsTypes.ADD_TEMPLATE_TO_CREATING_WORKOUT_COMPLEX;
}

export class AddTemplateToCreatingWorkout implements Action {
  readonly type = WorkoutComplexActionsTypes.ADD_TEMPLATE_TO_CREATING_WORKOUT;
}

export class SaveWorkoutComplex implements Action {
  readonly type = WorkoutComplexActionsTypes.SAVE_WORKOUT_COMPLEX;
  constructor(public  payload: WorkoutComplex) {}
}

export class CancelEditOrCreateWorkoutComplex implements Action {
  readonly type = WorkoutComplexActionsTypes.CANCEL_WORKOUT_COMPLEX_EDITABLE;
}

export class SelectWorkoutComplex implements Action {
  readonly type = WorkoutComplexActionsTypes.SELECT_WORKOUT_COMPLEX;
  constructor(public  payload: WorkoutComplex) {}
}

export class UnselectWorkoutComplex implements Action {
  readonly type = WorkoutComplexActionsTypes.UNSELECT_WORKOUT_COMPLEX;
}

export type WorkoutComplexActions =
   GetAllWorkoutComplex
  | GetAllWorkoutComplexFailure
  | GetAllWorkoutComplexSuccess
  | GetAllWorkout
  | GetAllWorkoutSuccess
  | GetAllWorkoutFailure
  | CreateWorkoutComplex
  | CreateWorkoutComplexSuccess
  | CreateWorkoutComplexFailure
  | UpdateWorkoutComplex
  | UpdateWorkoutComplexSuccess
  | UpdateWorkoutComplexFailure
  | DeleteWorkoutComplex
  | DeleteWorkoutComplexSuccess
  | DeleteWorkoutComplexFailure
  | DeleteWorkout
  | DeleteWorkoutSuccess
  | DeleteWorkoutFailure
  | AddTemplateToCreatingWorkoutComplex
  | AddTemplateToCreatingWorkout
  | SaveWorkoutComplex
  | CancelEditOrCreateWorkoutComplex
  | SelectWorkoutComplex
  | UnselectWorkoutComplex
  ;
