import { createFeatureSelector, createSelector } from "@ngrx/store";
import { DirectoryState } from "src/app/store/state/directory.state";

export const selectDirectoryState =
  createFeatureSelector<DirectoryState>('exercisesDirectory');

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

export const selectSelectedExercises = createSelector(
  selectDirectoryState,
  (state: DirectoryState) => state.selected
);

export const selectMuscleLoad = createSelector(
  selectDirectoryState,
  (state: DirectoryState) => state.muscleLoad
);

export const selectGroupedBy = createSelector(
  selectDirectoryState,
  (state: DirectoryState) => state.groupedBy
);
