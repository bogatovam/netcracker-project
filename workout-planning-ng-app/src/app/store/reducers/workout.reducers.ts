import { WorkoutActions, WorkoutActionsTypes } from "src/app/store/actions/workout.actions";
import { initialWorkoutState, WorkoutState } from "src/app/store/state/workout.state";

export function reducer(state: WorkoutState = initialWorkoutState, action: WorkoutActions): WorkoutState {
  switch (action.type) {
    case WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX: {
      return {
        ...state,
        isLoaded: false
      };
    }
    case WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX_SUCCESS: {
      return {
        ...state,
        isLoaded: true,
        sourceWorkoutComplex: action.payload
      };
    }
    case WorkoutActionsTypes.LOAD_SOURCE_WORKOUT_COMPLEX_FAILURE: {
      return {
        ...state,
        isLoaded: true,
        errorMessage: action.payload
      };
    }
    case WorkoutActionsTypes.LOAD_WORKOUT: {
      return {
        ...state,
        isLoaded: false
      };
    }
    case WorkoutActionsTypes.LOAD_WORKOUT_SUCCESS: {
      return {
        ...state,
        workout: action.payload.workout,
        exercises: action.payload.workout.exercises,
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
        isEditable: action.payload
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
        exercises: action.payload.workout.exercises,
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
        exercises: action.payload.exercises,
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
    case WorkoutActionsTypes.SELECT_EXERCISE: {
      state.exercises = [...state.exercises, action.payload];
      return  {
        ...state
      };
    }
    case WorkoutActionsTypes.UNSELECT_EXERCISE: {
      state.exercises.splice(state.exercises
        .findIndex((v) => v.id === action.payload.id), 1);
      return  {
        ...state,
        exercises: [...state.exercises]
      };
    }
    case WorkoutActionsTypes.UNSELECT_ALL_EXERCISES: {
      return  {
        ...state,
        exercises: []
      };
    }
    case WorkoutActionsTypes.ADD_TEMPLATE_TO_CREATING_WORKOUT: {
      return  {
        ...initialWorkoutState
      };
    }
    default:
      return state;
  }
}
