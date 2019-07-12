import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";
import { WorkoutComplexActions, WorkoutComplexActionsTypes } from "src/app/store/actions/workout-complex.actions";
import { initialWorkoutComplexState, WorkoutComplexState } from "src/app/store/state/workout-complex.state";

export function reducer(state: WorkoutComplexState = initialWorkoutComplexState, action: WorkoutComplexActions): WorkoutComplexState {
  switch (action.type) {
    case WorkoutComplexActionsTypes.SELECT_WORKOUT_COMPLEX: {
      return {
        ...state,
        selectedWorkoutComplex: action.payload,
        workouts: action.payload.workouts,
        selectedWorkout: null,
        isWorkoutComplexEditable: false
      };
    }
    case WorkoutComplexActionsTypes.UNSELECT_WORKOUT_COMPLEX: {
      return {
        ...state,
        selectedWorkoutComplex: null,
        selectedWorkout: null,
        isWorkoutComplexEditable: false
      };
    }

    case WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX_FAILURE: {
      return {
        ...state,
        errorMessage: state.errorMessage
      };
    }
    case WorkoutComplexActionsTypes.GET_ALL_WORKOUT_COMPLEX_SUCCESS: {
      if (state.selectedWorkoutComplex !== null) {
        state.selectedWorkoutComplex = action.payload.find((w) => w.id === this.selectedWorkoutComplex.id);
        this.workouts = this.selectedWorkoutComplex.workouts;
      }
      return {
        ...state,
        workoutComplexes: action.payload
      };
    }
    case WorkoutComplexActionsTypes.GET_ALL_WORKOUT: {
      return {
        ...state,
        selectedWorkoutComplex: null,
        selectedWorkout: null,
        workouts: []
      };
    }
    case WorkoutComplexActionsTypes.GET_ALL_WORKOUT_SUCCESS: {
      return {
        ...state,
        workouts: action.payload
      };
    }
    case WorkoutComplexActionsTypes.GET_ALL_WORKOUT_FAILURE: {
      return {
        ...state,
        errorMessage: action.payload
      };
    }
    case WorkoutComplexActionsTypes.CREATE_WORKOUT_COMPLEX_FAILURE: {
      return {
        ...state,
        errorMessage: action.payload
      };
    }
    case WorkoutComplexActionsTypes.UPDATE_WORKOUT_COMPLEX_SUCCESS: {
      const i = state.workoutComplexes.findIndex((v) => v.id === action.payload.id);
      if (i !== -1) {
        state.workoutComplexes[i] = action.payload;
      }
      return {
        ...state,
        errorMessage: null
      };
    }
    case WorkoutComplexActionsTypes.UPDATE_WORKOUT_COMPLEX_FAILURE: {
      return {
        ...state,
        errorMessage: action.payload
      };
    }
    case WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX_FAILURE: {
      return {
        ...state,
        errorMessage: action.payload
      };
    }
    case WorkoutComplexActionsTypes.DELETE_WORKOUT_COMPLEX_SUCCESS: {
      state.workoutComplexes.splice(state.workoutComplexes.findIndex((v) => v.id === action.payload.id), 1);
      if (state.selectedWorkoutComplex !== null && state.selectedWorkoutComplex.id === action.payload.id) {
        state.selectedWorkoutComplex = null;
      }
      return {
        ...state,
      };
    }
    case WorkoutComplexActionsTypes.DELETE_WORKOUT_FAILURE: {
      return {
        ...state,
        errorMessage: action.payload
      };
    }
    case WorkoutComplexActionsTypes.DELETE_WORKOUT_SUCCESS: {
      state.workouts.splice(state.workouts.findIndex((v) => v.id === action.payload.id), 1);
      if (state.selectedWorkout !== null && state.selectedWorkout.id === action.payload.id) {
        state.selectedWorkout = null;
      }
      return {
        ...state,
      };
    }
    case WorkoutComplexActionsTypes.CANCEL_WORKOUT_COMPLEX_EDITABLE: {
      return {
        ...state,
        isWorkoutComplexEditable: false,
      };
    }
    case WorkoutComplexActionsTypes.CREATE_WORKOUT_COMPLEX_SUCCESS: {
      return {
        ...state,
        workoutComplexes: [...state.workoutComplexes, action.payload],
        selectedWorkoutComplex: action.payload,
        errorMessage: null
      };
    }
    case WorkoutComplexActionsTypes.ADD_TEMPLATE_TO_CREATING_WORKOUT_COMPLEX: {
      const template = new WorkoutComplex(null, '', '', []);
      return {
        ...state,
        // workoutComplexes: [...state.workoutComplexes, template],
        workouts: [],
        isWorkoutComplexEditable: true,
        selectedWorkoutComplex: template
      };
    }
    case WorkoutComplexActionsTypes.ADD_TEMPLATE_TO_CREATING_WORKOUT: {
      const template = new Workout(null);
      return {
        ...state,
        workouts: [],
        isWorkoutComplexEditable: true,
        selectedWorkout: template
      };
    }
    case WorkoutComplexActionsTypes.SAVE_WORKOUT_COMPLEX: {
      return {
        ...state,
        isWorkoutComplexEditable: false
      };
    }
    default:
      return state;
  }
}
