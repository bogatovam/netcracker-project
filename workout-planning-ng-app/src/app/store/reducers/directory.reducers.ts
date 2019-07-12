import { DirectoryActions, DirectoryActionsTypes, GetAllExercisesSuccess } from "src/app/store/actions/directory.actions";
import { DirectoryState, initialDirectoryState } from "src/app/store/state/directory.state";

export function reducer(state: DirectoryState = initialDirectoryState, action: DirectoryActions): DirectoryState {
  switch (action.type) {
    case DirectoryActionsTypes.GET_ALL_EXERCISES_SUCCESS: {
      return {
        ...state,
        exercises: action.payload
      };
    }
    case DirectoryActionsTypes.GET_MUSCLE_LOAD_SUCCESS: {
      return {
        ...state,
        muscleLoad: action.payload
      };
    }
    case DirectoryActionsTypes.SWITCH_TO_CARD: {
      return {
        ...state,
        displayedStyle: 'card',
        groupedBy: null
      };
    }
    case DirectoryActionsTypes.SWITCH_TO_TABLE: {
      return {
        ...state,
        displayedStyle: 'table',
        groupedBy:  null
      };
    }
    case DirectoryActionsTypes.SET_GROUPED_BY: {
      return {
        ...state,
        groupedBy: action.payload
      };
    }
    case DirectoryActionsTypes.SET_DISPLAYED_STYLE: {
      return {
        ...state,
        displayedStyle: action.payload
      };
    }
    case DirectoryActionsTypes.SET_SELECTED_EXERCISES: {
      return {
        ...state,
        selected: action.payload
      };
    }
    default: {
      return state;
    }
  }
}
