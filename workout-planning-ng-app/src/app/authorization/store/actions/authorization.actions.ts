import { Action } from '@ngrx/store';
import { User } from "src/app/authorization/models/user";


export enum AuthorizationActionTypes {
  LOGIN = '[Auth] Login',
  LOGIN_SUCCESS = '[Auth] Login Success',
  LOGIN_FAILURE = '[Auth] Login Failure',
  SIGNUP = '[Auth] Signup',
  SIGNUP_SUCCESS = '[Auth] Signup Success',
  SIGNUP_FAILURE = '[Auth] Signup Failure',
  LOGOUT = '[Auth] Logout',
  LOGOUT_CONFIRMED = '[Auth] Logout confirmed',
  LOGOUT_CANCELLED = '[Auth] Logout cancelled'
}

export class LogIn implements Action {
  readonly type = AuthorizationActionTypes.LOGIN;

  constructor(public payload: User) {
  }
}

export class LogInSuccess implements Action {
  readonly type = AuthorizationActionTypes.LOGIN_SUCCESS;

  constructor(public payload: User) {
  }
}

export class LogInFailure implements Action {
  readonly type = AuthorizationActionTypes.LOGIN_FAILURE;

  constructor(public payload: string) {
  }
}

export class SignUp implements Action {
  readonly type = AuthorizationActionTypes.SIGNUP;

  constructor(public payload: User) {
  }
}

export class SignUpSuccess implements Action {
  readonly type = AuthorizationActionTypes.SIGNUP_SUCCESS;

  constructor(public payload: string) {
  }
}

export class SignUpFailure implements Action {
  readonly type = AuthorizationActionTypes.SIGNUP_FAILURE;

  constructor(public payload: any) {
  }
}

export class LogOut implements Action {
  readonly type = AuthorizationActionTypes.LOGOUT;
}

export class LogOutConfirmed implements Action {
  readonly type = AuthorizationActionTypes.LOGOUT_CONFIRMED;
}

export class LogOutCancelled implements Action {
  readonly type = AuthorizationActionTypes.LOGOUT_CANCELLED;
}

export type AuthorizationActions =
  | LogIn
  | LogInSuccess
  | LogInFailure
  | SignUp
  | SignUpSuccess
  | SignUpFailure
  | LogOut
  | LogOutCancelled
  | LogOutConfirmed;