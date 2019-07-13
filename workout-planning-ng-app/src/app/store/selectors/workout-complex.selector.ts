import { createFeatureSelector, createSelector } from "@ngrx/store";
import { WorkoutComplexState } from "src/app/store/state/workout-complex.state";

export const selectWorkoutComplexState =
  createFeatureSelector<WorkoutComplexState>('workoutComplexPanel');

export const selectWorkoutComplexes = createSelector(
  selectWorkoutComplexState,
  (state: WorkoutComplexState) => state.workoutComplexes
);
export const selectWorkouts = createSelector(
  selectWorkoutComplexState,
  (state: WorkoutComplexState) => state.workouts
);
export const selectWorkoutComplex = createSelector(
  selectWorkoutComplexState,
  (state: WorkoutComplexState) => state.selectedWorkoutComplex
);
export const selectIsWorkoutComplexEditable = createSelector(
  selectWorkoutComplexState,
  (state: WorkoutComplexState) => state.isWorkoutComplexEditable
);
//
// export const selectIsWorkoutEditable = createSelector(
//   selectWorkoutComplexState,
//   (state: WorkoutComplexState) => state.isWorkoutEditable
// );
