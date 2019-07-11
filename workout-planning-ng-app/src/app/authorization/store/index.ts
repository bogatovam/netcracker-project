import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromAuth from './reducers/authorization.reducers';

export const selectAuthState = createFeatureSelector<fromAuth.State>('authState');

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
