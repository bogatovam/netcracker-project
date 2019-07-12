import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { Observable, of } from "rxjs";
import { catchError, map, switchMap, tap } from "rxjs/operators";
import { ApiService } from "src/app/services/api.service";
import * as fromWorkoutComplex from "src/app/store/actions/workout-complex.actions";

@Injectable()
export class WorkoutComplexEffects {
  constructor(
    private actions: Actions,
    private router: Router,
    private apiService: ApiService
  ) { }

  @Effect()
  GetAllWorkoutComplexes: Observable<fromWorkoutComplex.GetAllWorkoutComplexSuccess | fromWorkoutComplex.GetAllWorkoutComplexFailure> =
    this.actions.pipe(
      ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX),
      switchMap((workoutComplex) => {
        return this.apiService.getAllWorkoutComplex().pipe(
          map(list =>
            new fromWorkoutComplex.GetAllWorkoutComplexSuccess(list)),
          catchError((error) => {
            if (error.error_description) {
              return of(new fromWorkoutComplex.GetAllWorkoutComplexFailure(error.error_description));
            } else {
              return of(new fromWorkoutComplex.GetAllWorkoutComplexFailure(JSON.stringify(error)));
            }
          })
        );
      })
    );

  @Effect({dispatch: false})
  GetAllWorkoutComplexesFailure: Observable<string> = this.actions.pipe(
    ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX_FAILURE),
    map((action: fromWorkoutComplex.GetAllWorkoutComplexFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );

  @Effect()
  GetAllWorkoutCs: Observable<fromWorkoutComplex.GetAllWorkoutComplexSuccess | fromWorkoutComplex.GetAllWorkoutComplexFailure> =
    this.actions.pipe(
      ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.GET_ALL_WORKOUT),
      switchMap((workoutComplex) => {
        return this.apiService.getAllWorkoutComplex().pipe(
          map(list =>
            new fromWorkoutComplex.GetAllWorkoutComplexSuccess(list)),
          catchError((error) => {
            if (error.error_description) {
              return of(new fromWorkoutComplex.GetAllWorkoutComplexFailure(error.error_description));
            } else {
              return of(new fromWorkoutComplex.GetAllWorkoutComplexFailure(JSON.stringify(error)));
            }
          })
        );
      })
    );

  @Effect({dispatch: false})
  GetAllWorkoutsFailure: Observable<string> = this.actions.pipe(
    ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX_FAILURE),
    map((action: fromWorkoutComplex.GetAllWorkoutFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );

  @Effect()
  SaveWorkoutComplex: Observable<fromWorkoutComplex.CreateWorkoutComplex | fromWorkoutComplex.UpdateWorkoutComplex> =
    this.actions.pipe(
      ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.SAVE_WORKOUT_COMPLEX),
      map((action: fromWorkoutComplex.SaveWorkoutComplex) => action.payload),
      map((workoutComplex) => {
        if (workoutComplex.id === null) {
          return new fromWorkoutComplex.CreateWorkoutComplex(workoutComplex);
        } else {
          return new fromWorkoutComplex.UpdateWorkoutComplex(workoutComplex);
        }
      })
    );
  @Effect()
  CreateWorkoutComplex: Observable<fromWorkoutComplex.CreateWorkoutComplexSuccess | fromWorkoutComplex.CreateWorkoutComplexFailure> =
    this.actions.pipe(
      ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.SAVE_WORKOUT_COMPLEX),
      map((action: fromWorkoutComplex.SaveWorkoutComplex) => action.payload),
      switchMap((workoutComplex) => {
        return this.apiService.createWorkoutComplex(workoutComplex).pipe(
          map(newWorkoutComplex =>
            new fromWorkoutComplex.CreateWorkoutComplexSuccess(newWorkoutComplex)),
          catchError((error) => {
            if (error.error_description) {
              return of(new fromWorkoutComplex.CreateWorkoutComplexFailure(error.error_description));
            } else {
              return of(new fromWorkoutComplex.CreateWorkoutComplexFailure(JSON.stringify(error)));
            }
          })
        );
      })
    );

  @Effect({dispatch: false})
  CreateWorkoutComplexFailure: Observable<string> = this.actions.pipe(
    ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.CREATE_WORKOUT_COMPLEX_FAILURE),
    map((action: fromWorkoutComplex.CreateWorkoutComplexFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );

  @Effect()
  UpdateWorkoutComplex: Observable<fromWorkoutComplex.UpdateWorkoutComplexSuccess | fromWorkoutComplex.UpdateWorkoutComplexFailure> =
    this.actions.pipe(
      ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.SAVE_WORKOUT_COMPLEX),
      map((action: fromWorkoutComplex.SaveWorkoutComplex) => action.payload),
      switchMap((workoutComplex) => {
        return this.apiService.updateWorkoutComplex(workoutComplex).pipe(
          map(updateWorkoutComplex =>
            new fromWorkoutComplex.UpdateWorkoutComplexSuccess(updateWorkoutComplex)),
          catchError((error) => {
            if (error.error_description) {
              return of(new fromWorkoutComplex.UpdateWorkoutComplexFailure(error.error_description));
            } else {
              return of(new fromWorkoutComplex.UpdateWorkoutComplexFailure(JSON.stringify(error)));
            }
          })
        );
      })
    );

  @Effect({dispatch: false})
  UpdateWorkoutComplexFailure: Observable<string> = this.actions.pipe(
    ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.UPDATE_WORKOUT_COMPLEX_FAILURE),
    map((action: fromWorkoutComplex.UpdateWorkoutComplexFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );

  @Effect()
  DeleteWorkoutComplex: Observable<fromWorkoutComplex.DeleteWorkoutComplexSuccess | fromWorkoutComplex.DeleteWorkoutComplexFailure> =
    this.actions.pipe(
      ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX),
      map((action: fromWorkoutComplex.SaveWorkoutComplex) => action.payload),
      switchMap((workoutComplex) => {
        return this.apiService.deleteWorkoutComplex(workoutComplex).pipe(
          map(deletedWorkoutComplex =>
            new fromWorkoutComplex.DeleteWorkoutComplexSuccess(deletedWorkoutComplex)),
          catchError((error) => {
            if (error.error_description) {
              return of(new fromWorkoutComplex.DeleteWorkoutComplexFailure(error.error_description));
            } else {
              return of(new fromWorkoutComplex.DeleteWorkoutComplexFailure(JSON.stringify(error)));
            }
          })
        );
      })
    );

  @Effect({dispatch: false})
  DeleteWorkoutComplexFailure: Observable<string> = this.actions.pipe(
    ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX_FAILURE),
    map((action: fromWorkoutComplex.DeleteWorkoutComplexFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );


  @Effect()
  DeleteWorkout: Observable<fromWorkoutComplex.DeleteWorkoutSuccess | fromWorkoutComplex.DeleteWorkoutFailure> =
    this.actions.pipe(
      ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX),
      map((action: fromWorkoutComplex.SaveWorkoutComplex) => action.payload),
      switchMap((workoutComplex) => {
        return this.apiService.deleteWorkout(workoutComplex).pipe(
          map(deletedWorkout =>
            new fromWorkoutComplex.DeleteWorkoutSuccess(deletedWorkout)),
          catchError((error) => {
            if (error.error_description) {
              return of(new fromWorkoutComplex.DeleteWorkoutFailure(error.error_description));
            } else {
              return of(new fromWorkoutComplex.DeleteWorkoutFailure(JSON.stringify(error)));
            }
          })
        );
      })
    );

  @Effect({dispatch: false})
  DeleteWorkoutFailure: Observable<string> = this.actions.pipe(
    ofType(fromWorkoutComplex.WorkoutComplexActionsTypes.DELETE_WORKOUT_FAILURE),
    map((action: fromWorkoutComplex.DeleteWorkoutFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );

}
