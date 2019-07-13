import { Exercise } from "src/app/models/exercise";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";

export interface WorkoutState {
  workout: Workout;
  exercises: Exercise[];
  sourceWorkoutComplex: WorkoutComplex;
  isEditable: boolean;
  isLoaded: boolean;
  errorMessage: string;
}
export const initialWorkoutState: WorkoutState = {
  workout: null,
  exercises: [],
  sourceWorkoutComplex: null,
  isEditable: false,
  isLoaded: false,
  errorMessage: null
};
