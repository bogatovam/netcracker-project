import { WorkoutActions, WorkoutActionsTypes } from "src/app/store/actions/workout.actions";
import { initialWorkoutState, WorkoutState } from "src/app/store/state/workout.state";

export function reducer(state: WorkoutState = initialWorkoutState, action: WorkoutActions): WorkoutState {
  switch (action.type) {
    case WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX: {
      console.log('false');
      return {
        ...state,
        isLoaded: false
      };
    }
    case WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX_SUCCESS: {
      console.log('true');
      return {
        ...state,
        isLoaded: true,
        sourceWorkoutComplex: action.payload
      };
    }
    case WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX_FAILURE: {
      console.log('true');

      return {
        ...state,
        isLoaded: true,
        errorMessage: action.payload
      };
    }
    case WorkoutActionsTypes.LOAD_WORKOUT: {
      console.log('false');

      return {
        ...state,
        isLoaded: false
      };
    }
    case WorkoutActionsTypes.LOAD_WORKOUT_SUCCESS: {
      console.log('true');

      return {
        ...state,
        workout: action.payload.workout,
        sourceWorkoutComplex: action.payload.workoutComplex,
        isLoaded: true
      };
    }
    case WorkoutActionsTypes.LOAD_WORKOUT_FAILURE: {
      return {
        ...state,
        isLoaded: true,
        errorMessage: action.payload
      };
    }
    case WorkoutActionsTypes.SET_EDITABLE : {
      return {
        ...state,
        isEditable: true
      };
    }
    case WorkoutActionsTypes.DELETE_WORKOUT_SUCCESS : {
      return initialWorkoutState;
    }
    case WorkoutActionsTypes.DELETE_WORKOUT_FAILURE : {
      return {
        ...state,
        errorMessage: action.payload
      };
    }
    case WorkoutActionsTypes.CREATE_WORKOUT_SUCCESS: {
      return  {
        ...state,
        isEditable: false,
        workout: action.payload.workout,
        sourceWorkoutComplex: action.payload.workoutComplex,
        errorMessage: null
      };
    }
    case WorkoutActionsTypes.CREATE_WORKOUT_FAILURE: {
      return  {
        ...state,
        errorMessage: action.payload,
        isEditable: false
      };
    }

    case WorkoutActionsTypes.UPDATE_WORKOUT_SUCCESS: {
      return  {
        ...state,
        isEditable: false,
        workout: action.payload,
        errorMessage: null
      };
    }
    case WorkoutActionsTypes.UPDATE_WORKOUT_FAILURE: {
      return  {
        ...state,
        errorMessage: action.payload,
        isEditable: false
      };
    }

    case WorkoutActionsTypes.CHANGE_SOURCE_WORKOUT_COMPLEX_SUCCESS: {
      return  {
        ...state,
        isEditable: false,
        sourceWorkoutComplex: action.payload,
        errorMessage: null
      };
    }
    case WorkoutActionsTypes.CHANGE_SOURCE_WORKOUT_COMPLEX_FAILURE: {
      return  {
        ...state,
        errorMessage: action.payload,
        isEditable: false
      };
    }
    default:
      return state;
  }
}
