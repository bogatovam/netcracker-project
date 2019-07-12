import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { Observable, of } from "rxjs";
import { catchError, map, switchMap } from "rxjs/operators";
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
              map((workoutComplex) => new fromWorkout.LoadWorkoutSuccess({workout: workout, workoutComplex: workoutComplex})),
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
}
