import * as fromAuth from "src/app/authorization/store/reducers/authorization.reducers";
import { DirectoryState } from "src/app/store/state/directory.state";
import { WorkoutComplexState } from "src/app/store/state/workout-complex.state";

export interface AppState {
  authState: fromAuth.State;
  exercisesDirectory: DirectoryState;
  workoutComplexPanel: WorkoutComplexState;
}
