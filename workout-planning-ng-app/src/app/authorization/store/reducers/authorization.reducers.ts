import { User } from "src/app/authorization/models/user";
import { TokenStorageService } from "src/app/authorization/services/token-storage.service";
import { AuthorizationActions, AuthorizationActionTypes } from '../actions/authorization.actions';

export interface State {
  // is a user authenticated?
  isLogin: boolean;
  // if authenticated, there should be a user object
  user: User | null;
  // error message
  errorMessage: string | null;
}

export const initialState: State = {
  isLogin: TokenStorageService.getToken() !== null,
  user: null,
  errorMessage: null
};

export function reducer(state: State = initialState, action: AuthorizationActions): State {
  switch (action.type) {
    case AuthorizationActionTypes.LOGIN_SUCCESS: {
      return {
        ...state,
        isLogin: true,
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
        errorMessage: 'Некорректный логин или пароль'
      };
    }
    case AuthorizationActionTypes.SIGNUP_SUCCESS: {
      return {
        ...state,
        errorMessage: null
      };
    }
    case AuthorizationActionTypes.SIGNUP_FAILURE: {
      return {
        ...state,
        errorMessage: 'Пользователь с таким логином уже существует'
      };
    }
    case AuthorizationActionTypes.LOGOUT_CONFIRMED: {
      return {
        ...state,
        isLogin: false,
        user: null,
        errorMessage: null
      };
    }
    case AuthorizationActionTypes.SET_USER: {
      return {
        ...state,
        isLogin: true,
        user: action.payload,
        errorMessage: null
      };
    }
    default: {
      return state;
    }
  }
}
export const selectIsLoggedIn = (state: State) => state.isLogin;
export const selectUser = (state: State) => state.user;
export const selectError = (state: State) => state.errorMessage;
