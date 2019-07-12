import { WorkoutActions, WorkoutActionsTypes } from "src/app/store/actions/workout.actions";
import { initialWorkoutState, WorkoutState } from "src/app/store/state/workout.state";

export function reducer(state: WorkoutState = initialWorkoutState, action: WorkoutActions): WorkoutState {
  switch (action.type) {
    case WorkoutActionsTypes.SELECT_EXERCISE:
      break;
    case WorkoutActionsTypes.UNSELECT_EXERCISE:
      break;
    case WorkoutActionsTypes.LOAD_WORKOUT: {
      return {
        ...state,
        isLoaded: true
      };
    }
    case WorkoutActionsTypes.LOAD_WORKOUT_SUCCESS:{
      return {
        ...state,
        workout: action.payload.workout,
        sourceWorkoutComplex: action.payload.workoutComplex,
        isLoaded: false
      };
    }
    case WorkoutActionsTypes.LOAD_WORKOUT_FAILURE:{
      return {
        ...state,
        isLoaded: false,
        errorMessage: action.payload
      };
    }

    default: return state;
  }
}
