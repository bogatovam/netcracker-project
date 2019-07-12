import { createFeatureSelector, createSelector } from "@ngrx/store";
import { WorkoutState } from "src/app/store/state/workout.state";

export const selectWorkoutState =
  createFeatureSelector<WorkoutState>('workout');

export const selectWorkout = createSelector(
  selectWorkoutState,
  (state: WorkoutState) => state.workout
);

export const selectSourceWorkoutComplex = createSelector(
  selectWorkoutState,
  (state: WorkoutState) => state.sourceWorkoutComplex
);

export const selectIsEditable = createSelector(
  selectWorkoutState,
  (state: WorkoutState) => state.isEditable
);

export const selectIsLoaded = createSelector(
  selectWorkoutState,
  (state: WorkoutState) => state.isLoaded
);
export const selectError = createSelector(
  selectWorkoutState,
  (state: WorkoutState) => state.errorMessage
);
