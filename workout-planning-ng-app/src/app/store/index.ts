import { ActionReducerMap } from "@ngrx/store";
import * as fromAuth from "src/app/authorization/store/reducers/authorization.reducers";
import * as fromDictionary from "src/app/store/reducers/directory.reducers";
import { AppState } from "src/app/store/state/app.state";

export const reducers = {
  authState: fromAuth.reducer,
  exercisesDirectory: fromDictionary.reducer
};
