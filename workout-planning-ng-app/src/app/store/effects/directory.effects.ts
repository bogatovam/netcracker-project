import { Injectable } from "@angular/core";
import { MatTableDataSource } from "@angular/material";
import { Router } from "@angular/router";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { Observable, of } from "rxjs";
import { catchError, map, switchMap, tap } from "rxjs/operators";
import { Exercise } from "src/app/models/exercise";
import { ApiService } from "src/app/services/api.service";
import * as fromDirectory from "src/app/store/actions/directory.actions";

@Injectable()
export class DirectoryEffects {
  constructor(
    private actions: Actions,
    private router: Router,
    private apiService: ApiService
  ) { }

  @Effect()
  GetAllExercise: Observable<fromDirectory.GetAllExercisesSuccess | fromDirectory.GetAllExercisesFailure> = this.actions.pipe(
    ofType(fromDirectory.DirectoryActionsTypes.GET_ALL_EXERCISES),
    switchMap((n) => {
      return this.apiService.getAllExercises().pipe(
        map((exercises) => {
          return new fromDirectory.GetAllExercisesSuccess(exercises);
        }),
        catchError((error) => {
          if (error.error_description) {
            return of(new fromDirectory.GetAllExercisesFailure(error.error_description));
          } else {
            return of(new fromDirectory.GetAllExercisesFailure(JSON.stringify(error)));
          }
        }));
    }));


  @Effect({dispatch: false})
  GetAllExerciseFailure: Observable<string> = this.actions.pipe(
    ofType(fromDirectory.DirectoryActionsTypes.GET_ALL_EXERCISES_FAILURE),
    map((action: fromDirectory.GetAllExercisesFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );

  @Effect()
  GetMuscleLoad: Observable<fromDirectory.GetMuscleLoadSuccess | fromDirectory.GetMuscleLoadFailure> = this.actions.pipe(
    ofType(fromDirectory.DirectoryActionsTypes.GET_MUSCLE_LOAD),
    switchMap(() => {
      return this.apiService.getMuscleLoad().pipe(
        map((load) => {
          return new fromDirectory.GetMuscleLoadSuccess(load);
        }),
        catchError((error) => {
          if (error.error_description) {
            return of(new fromDirectory.GetMuscleLoadFailure(error.error_description));
          } else {
            return of(new fromDirectory.GetMuscleLoadFailure(JSON.stringify(error)));
          }
        }));
    }));


  @Effect({dispatch: false})
  GetMuscleLoadFailure: Observable<string> = this.actions.pipe(
    ofType(fromDirectory.DirectoryActionsTypes.GET_MUSCLE_LOAD_FAILURE),
    map((action: fromDirectory.GetMuscleLoadFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
    })
  );
}
