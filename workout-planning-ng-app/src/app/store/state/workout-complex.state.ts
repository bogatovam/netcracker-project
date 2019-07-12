import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";

export interface WorkoutComplexState {
  workoutComplexes: WorkoutComplex[];
  workouts: Workout[];
  selectedWorkoutComplex: WorkoutComplex;
  selectedWorkout: Workout;
  isWorkoutComplexEditable: boolean;
//  isWorkoutEditable: boolean;
  errorMessage: string;
}

export const initialWorkoutComplexState = {
  workoutComplexes: [],
  workouts: [],
  selectedWorkoutComplex:  null,
  selectedWorkout: null,
  isWorkoutComplexEditable: false,
//  isWorkoutEditable: false,
  errorMessage: null
};
