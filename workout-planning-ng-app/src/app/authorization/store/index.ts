import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromAuth from './reducers/authorization.reducers';

export const reducers = {
  auth: fromAuth.reducer
};

export const selectAuthState = createFeatureSelector<fromAuth.State>('auth');

export const selectIsLoggedIn = createSelector(
  selectAuthState,
  fromAuth.selectIsLoggedIn
);

export const selectUser = createSelector(
  selectAuthState,
  fromAuth.selectUser
);

export const selectError = createSelector(
  selectAuthState,
  fromAuth.selectError
);
