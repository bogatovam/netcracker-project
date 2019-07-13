import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";

export interface WorkoutComplexState {
  workoutComplexes: WorkoutComplex[];
  workouts: Workout[];
  selectedWorkoutComplex: WorkoutComplex;
  isWorkoutComplexEditable: boolean;
  errorMessage: string;
}

export const initialWorkoutComplexState = {
  workoutComplexes: [],
  workouts: [],
  selectedWorkoutComplex:  null,
  isWorkoutComplexEditable: false,
  errorMessage: null
};
