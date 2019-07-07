import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Action } from '@ngrx/store';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import { tap } from 'rxjs/operators';
import { AuthorizationService } from "src/app/authorization/services/authorization.service";

import {
  AuthActionTypes,
  LogIn, LogInFailure, LogInSuccess,
  LogOut, SignUp, SignUpFailure,
  SignUpSuccess,
} from '../actions/auth.actions';


@Injectable()
export class AuthorizationEffects {

  constructor(
    private actions: Actions,
    private authService: AuthorizationService,
    private router: Router,
  ) {}

  @Effect()
  LogIn: Observable<any> = this.actions
    .ofType(AuthActionTypes.LOGIN)
    .map((action: LogIn) => action.payload)
    .switchMap(payload => {
      return this.authService.logIn(payload.email, payload.password)
        .map((user) => {
          return new LogInSuccess({token: user.token, email: payload.email});
        })
        .catch((error) => {
          return Observable.of(new LogInFailure({ error: error }));
        });
    });


  @Effect({ dispatch: false })
  LogInSuccess: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.LOGIN_SUCCESS),
    tap((user) => {
      localStorage.setItem('token', user.payload.token);
      this.router.navigateByUrl('/');
    })
  );

  @Effect({ dispatch: false })
  LogInFailure: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.LOGIN_FAILURE)
  );

  @Effect()
  SignUp: Observable<any> = this.actions
    .ofType(AuthActionTypes.SIGNUP)
    .map((action: SignUp) => action.payload)
    .switchMap(payload => {
      return this.authService.signUp(payload.email, payload.password)
        .map((user) => {
          return new SignUpSuccess({token: user.token, email: payload.email});
        })
        .catch((error) => {
          return Observable.of(new SignUpFailure({ error: error }));
        });
    });

  @Effect({ dispatch: false })
  SignUpSuccess: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.SIGNUP_SUCCESS),
    tap((user) => {
      localStorage.setItem('token', user.payload.token);
      this.router.navigateByUrl('/');
    })
  );

  @Effect({ dispatch: false })
  SignUpFailure: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.SIGNUP_FAILURE)
  );

  @Effect({ dispatch: false })
  public LogOut: Observable<any> = this.actions.pipe(
    ofType(AuthActionTypes.LOGOUT),
    tap((user) => {
      localStorage.removeItem('token');
    })
  );

  @Effect({ dispatch: false })
  GetStatus: Observable<any> = this.actions
    .ofType(AuthActionTypes.GET_STATUS)
    .switchMap(payload => {
      return this.authService.getStatus();
    });

}
