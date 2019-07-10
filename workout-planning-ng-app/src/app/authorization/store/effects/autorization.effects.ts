import { Injectable } from '@angular/core';
import { MatDialog } from "@angular/material";
import { Router } from '@angular/router';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Observable, of } from 'rxjs';
import { catchError, exhaustMap, map, switchMap, tap } from 'rxjs/operators';
import { LogoutComponent } from "src/app/authorization/components/logout/logout.component";
import { AuthorizationService } from "src/app/authorization/services/authorization.service";
import { TokenStorageService } from "src/app/authorization/services/token-storage.service";
import * as fromAuth from '../actions/authorization.actions';


@Injectable()
export class AuthorizationEffects {

  constructor(
    private actions: Actions,
    private authService: AuthorizationService,
    private router: Router,
    private dialogService: MatDialog
  ) {
  }

  @Effect()
  LogIn: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.LOGIN),
    map((action: fromAuth.LogIn) => action.payload),
    switchMap(payload => {
      return this.authService.logIn(payload).pipe(
        map((user) => {
          console.log(user.workoutsComplexes);
          return new fromAuth.LogInSuccess(user);
        }),
        catchError((error) => {
          if (error.error_description) {
            return of(new fromAuth.LogInFailure(error.error_description));
          } else {
            return of(new fromAuth.LogInFailure(JSON.stringify(error)));
          }
        }));
    }));


  @Effect({dispatch: false})
  LogInSuccess: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.LOGIN_SUCCESS),
    tap((user) => {
      TokenStorageService.saveToken(user.token);
      TokenStorageService.saveLogin(user.login);
      this.router.navigate([this.authService.authorizationSuccessUrl]);
    })
  );

  @Effect({dispatch: false})
  LogInFailure: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.LOGIN_FAILURE),
    map((action: fromAuth.LogInFailure) => action.payload),
    tap((error) => {
      console.error(`Error: ${error}`);
      this.router.navigate([this.authService.authorizationFailureUrl]);
    })
  );

  @Effect()
  SignUp: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.SIGNUP),
    map((action: fromAuth.SignUp) => action.payload),
    switchMap(payload => {
      return this.authService.signUp(payload).pipe(
        map((message) => {
          return new fromAuth.SignUpSuccess(message);
        }),
        catchError((error) => {
          if (error.error_description) {
            return of(new fromAuth.SignUpFailure(error.error_description));
          } else {
            return of(new fromAuth.SignUpFailure(JSON.stringify(error)));
          }
        })
      );
    })
  );

  @Effect({dispatch: false})
  SignUpSuccess: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.SIGNUP_SUCCESS),
    tap((message) => {
      console.log(message);
      this.router.navigate([this.authService.signUpSuccessUrl]);
    })
  );

  @Effect({dispatch: false})
  SignUpFailure: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.SIGNUP_FAILURE),
    map((action: fromAuth.LogInFailure) => action.payload),
    tap((error) => {
        console.error(`Error: ${error}`);
        this.router.navigate([this.authService.signUpFailureUrl]);
      }
    )
  );

  @Effect()
  public LogOut: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.LOGOUT),
    exhaustMap(() =>
      this.dialogService
        .open(LogoutComponent)
        .afterClosed()
        .pipe(
          map(confirmed => {
            if (confirmed) {
              return new fromAuth.LogOutConfirmed();
            } else {
              return new fromAuth.LogOutCancelled();
            }
          })
        )
    )
  );

  @Effect({dispatch: false})
  public LogOutConfirmed: Observable<any> = this.actions.pipe(
    ofType(fromAuth.AuthorizationActionTypes.LOGOUT_CONFIRMED),
    tap(() => {
      TokenStorageService.logOut();
       this.router.navigate([this.authService.authorizationFailureUrl]);
    })
  );
}
