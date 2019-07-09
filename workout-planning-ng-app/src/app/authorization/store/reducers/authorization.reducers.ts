import { User } from "src/app/authorization/models/user";
import { AuthorizationActions, AuthorizationActionTypes } from '../actions/authorization.actions';

export interface State {
  // is a user authenticated?
  isAuthenticated: boolean;
  // if authenticated, there should be a user object
  user: User | null;
  // error message
  errorMessage: string | null;
}

export const initialState: State = {
  isAuthenticated: false,
  user: null,
  errorMessage: null
};

export function reducer(state: State = initialState, action: AuthorizationActions): State {
  switch (action.type) {
    case AuthorizationActionTypes.LOGIN_SUCCESS: {
      return {
        ...state,
        isAuthenticated: true,
        user: {
          token: action.payload.token,
          login: action.payload.login
        },
        errorMessage: null
      };
    }
    case AuthorizationActionTypes.LOGIN_FAILURE: {
      return {
        ...state,
        errorMessage: 'Incorrect email and/or password.'
      };
    }
    case AuthorizationActionTypes.SIGNUP_SUCCESS: {
      return {
        ...state
      };
    }
    case AuthorizationActionTypes.SIGNUP_FAILURE: {
      return {
        ...state,
        errorMessage: 'That email is already in use.'
      };
    }
    case AuthorizationActionTypes.LOGOUT: {
      return initialState;
    }
    default: {
      return state;
    }
  }
}
