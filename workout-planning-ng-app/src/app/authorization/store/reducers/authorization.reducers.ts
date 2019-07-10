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
          id: action.payload.id,
          fullName: action.payload.fullName,
          email: action.payload.email,
          login: action.payload.login,
          password: action.payload.password,
          roles: action.payload.roles,
          state: action.payload.state,
          gender: action.payload.gender,
          dateOfBirth: action.payload.dateOfBirth,
          weight: action.payload.weight,
          growth: action.payload.growth,
          workoutsGoal: action.payload.workoutsGoal,
          token: action.payload.token,
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
    case AuthorizationActionTypes.LOGOUT_CONFIRMED: {
      return initialState;
    }
    default: {
      return state;
    }
  }
}
export const selectIsLoggedIn = (state: State) => state.isAuthenticated;
export const selectUser = (state: State) => state.user;
