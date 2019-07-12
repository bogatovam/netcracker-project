import * as fromAuth from "src/app/authorization/store/reducers/authorization.reducers";
import * as fromDictionary from "src/app/store/reducers/directory.reducers";
import * as fromWorkoutComplex from "src/app/store/reducers/workout-complex.reducers";

export const reducers = {
  authState: fromAuth.reducer,
  exercisesDirectory: fromDictionary.reducer,
  workoutComplexPanel: fromWorkoutComplex.reducer
};
