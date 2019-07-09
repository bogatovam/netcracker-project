import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromAuth from './reducers/authorization.reducers';


export interface AppState {
  authState: fromAuth.State;
}

export const reducers = {
  auth: fromAuth.reducer
};

export const selectAuthState = createFeatureSelector<fromAuth.State>('auth');
export const selectIsLoggedIn = createSelector(
  selectAuthState,
  fromAuth.selectIsLoggedIn
);
