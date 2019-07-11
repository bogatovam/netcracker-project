import { createSelector } from "@ngrx/store";
import { AppState } from "src/app/store/state/app.state";
import { DirectoryState } from "src/app/store/state/directory.state";

export const selectDirectoryState = (state: AppState) => state.exercisesDirectory;

export const selectExercises = createSelector(
  selectDirectoryState,
  (state: DirectoryState) => state.exercises
);

export const selectIsEditable = createSelector(
  selectDirectoryState,
  (state: DirectoryState) => state.isEditable
);

export const selectIsEmbeddable = createSelector(
  selectDirectoryState,
  (state: DirectoryState) => state.isEmbeddable
);

export const selectDisplayedStyle = createSelector(
  selectDirectoryState,
  (state: DirectoryState) => state.displayedStyle
);
