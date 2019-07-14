import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { Observable, of } from "rxjs";
import { catchError, exhaustMap, map, switchMap, tap } from "rxjs/operators";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";
import { ApiService } from "src/app/services/api.service";
import * as fromWorkout from "src/app/store/actions/workout.actions";

@Injectable()
export class WorkoutEffects {
  constructor(
    private actions: Actions,
    private router: Router,
    private apiService: ApiService
  ) {
  }

  @Effect()
  LoadWorkout: Observable<fromWorkout.LoadWorkoutSuccess | fromWorkout.LoadWorkoutFailure> =
    this.actions.pipe(
      ofType(fromWorkout.WorkoutActionsTypes.LOAD_WORKOUT),
      map((action: fromWorkout.LoadWorkout) => action.payload),
      switchMap((workoutId) => {
        return this.apiService.getWorkout(workoutId).pipe(
          switchMap((workout) => {
            console.log(workout);
            return this.apiService.getSourceWorkoutComplex(workout).pipe(
              map((workoutComplex) => new fromWorkout.LoadWorkoutSuccess({
                workout: workout,
                workoutComplex: workoutComplex
              })),
              catchError((error) => {
                if (error.error_description) {
                  return of(new fromWorkout.LoadWorkoutFailure(error.error_description));
                } else {
                  return of(new fromWorkout.LoadWorkoutFailure(JSON.stringify(error)));
                }
              })
            );
          }),
          catchError((error) => {
            if (error.error_description) {
              return of(new fromWorkout.LoadWorkoutFailure(error.error_description));
            } else {
              return of(new fromWorkout.LoadWorkoutFailure(JSON.stringify(error)));
            }
          })
        );
      })
    );

  @Effect()
  LoadSourceWorkoutComplex: Observable<fromWorkout.LoadSourceWorkoutComplexSuccess | fromWorkout.LoadSourceWorkoutComplexFailure> =
    this.actions.pipe(
      ofType(fromWorkout.WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX),
      map((action: fromWorkout.LoadSourceWorkoutComplex) => action.payload),
      exhaustMap((workoutComplexId) => {
        console.log('here');
        return this.apiService.getWorkoutComplex(workoutComplexId).pipe(
          map((workoutComplex) => {
            console.log(workoutComplex);
            return new fromWorkout.LoadSourceWorkoutComplexSuccess(workoutComplex);
          })
        );
      }),
      catchError((error) => {
        if (error.error_description) {
          return of(new fromWorkout.LoadSourceWorkoutComplexFailure(error.error_description));
        } else {
          return of(new fromWorkout.LoadSourceWorkoutComplexFailure(JSON.stringify(error)));
        }
      })
    );


  @Effect()
  DeleteWorkout: Observable<fromWorkout.DeleteWorkoutSuccess | fromWorkout.DeleteWorkoutFailure> =
    this.actions.pipe(
      ofType(fromWorkout.WorkoutActionsTypes.DELETE_WORKOUT),
      map((action: fromWorkout.DeleteWorkout) => action.payload),
      exhaustMap((workout) => {
        return this.apiService.deleteWorkout(workout).pipe(
          map((deleted) => {
            console.log(deleted);
            return new fromWorkout.DeleteWorkoutSuccess(deleted);
          })
        );
      }),
      catchError((error) => {
        if (error.error_description) {
          return of(new fromWorkout.DeleteWorkoutFailure(error.error_description));
        } else {
          return of(new fromWorkout.DeleteWorkoutFailure(JSON.stringify(error)));
        }
      })
    );

  @Effect({dispatch: false})
  DeleteWorkoutSuccess: Observable<void> =
    this.actions.pipe(
      ofType(fromWorkout.WorkoutActionsTypes.DELETE_WORKOUT_SUCCESS),
      tap((deleted) => this.router.navigateByUrl('workout-complex'))
    );

  @Effect()
  CreateWorkout: Observable<fromWorkout.CreateWorkoutSuccess | fromWorkout.CreateWorkoutFailure> =
    this.actions.pipe(
      ofType(fromWorkout.WorkoutActionsTypes.CREATE_WORKOUT),
      map((action: fromWorkout.CreateWorkout) => action.payload),
      exhaustMap((payload) => {
        return this.apiService.createWorkout(payload.workout, payload.workoutComplex).pipe(
          map((createdWorkout) => {
            console.log(createdWorkout);
            return new fromWorkout.CreateWorkoutSuccess({ workout: createdWorkout, workoutComplex: payload.workoutComplex}, );
          })
        );
      }),
      catchError((error) => {
        if (error.error_description) {
          return of(new fromWorkout.CreateWorkoutFailure(error.error_description));
        } else {
          return of(new fromWorkout.CreateWorkoutFailure(JSON.stringify(error)));
        }
      })
    );

  @Effect()
  UpdateWorkout: Observable<fromWorkout.UpdateWorkoutFailure | fromWorkout.UpdateWorkoutSuccess> =
    this.actions.pipe(
      ofType(fromWorkout.WorkoutActionsTypes.UPDATE_WORKOUT),
      map((action: fromWorkout.UpdateWorkout) => action.payload),
      exhaustMap((workout) => {
        return this.apiService.updateWorkout(workout).pipe(
          map((updatedWorkout) => {
            console.log(updatedWorkout);
            return new fromWorkout.UpdateWorkoutSuccess(updatedWorkout );
          })
        );
      }),
      catchError((error) => {
        if (error.error_description) {
          return of(new fromWorkout.UpdateWorkoutFailure(error.error_description));
        } else {
          return of(new fromWorkout.UpdateWorkoutFailure(JSON.stringify(error)));
        }
      })
    );
  @Effect()
  ChangeSourceWorkoutComplex: Observable<fromWorkout.ChangeSourceWorkoutComplexSuccess | fromWorkout.ChangeSourceWorkoutComplexFailure> =
    this.actions.pipe(
      ofType(fromWorkout.WorkoutActionsTypes.CHANGE_SOURCE_WORKOUT_COMPLEX),
      map((action: fromWorkout.ChangeSourceWorkoutComplex) => action.payload),
      switchMap((payload) => {
        return this.apiService.changeWorkoutComplex(payload.workoutId, payload.oldWorkoutComplexId, payload.newWorkoutComplexId).pipe(
          map((newWorkoutComplex) => {
            console.log(newWorkoutComplex);
            return new fromWorkout.ChangeSourceWorkoutComplexSuccess(newWorkoutComplex );
          })
        );
      }),
      catchError((error) => {
        if (error.error_description) {
          return of(new fromWorkout.ChangeSourceWorkoutComplexFailure(error.error_description));
        } else {
          return of(new fromWorkout.ChangeSourceWorkoutComplexFailure(JSON.stringify(error)));
        }
      })
    );
}
