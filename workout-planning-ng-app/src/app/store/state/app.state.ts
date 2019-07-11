import * as fromAuth from "src/app/authorization/store/reducers/authorization.reducers";
import { DirectoryState } from "src/app/store/state/directory.state";

export interface AppState {
  authState: fromAuth.State;
  exercisesDirectory: DirectoryState;
}

export interface AppState {
  authState: fromAuth.State;
}
