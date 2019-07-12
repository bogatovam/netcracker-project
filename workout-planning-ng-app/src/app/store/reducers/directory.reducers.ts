import { DirectoryActions, DirectoryActionsTypes, GetAllExercisesSuccess } from "src/app/store/actions/directory.actions";
import { DirectoryState, initialDirectoryState } from "src/app/store/state/directory.state";

export function reducer(state: DirectoryState = initialDirectoryState, action: DirectoryActions): DirectoryState {
  switch (action.type) {
    case DirectoryActionsTypes.GET_ALL_EXERCISES_SUCCESS: {
      return {
        ...state,
        exercises: action.payload,
        errorMessage: null
      };
    }
    case DirectoryActionsTypes.GET_MUSCLE_LOAD_SUCCESS: {
      return {
        ...state,
        muscleLoad: action.payload,
        errorMessage: null
      };
    }
    case DirectoryActionsTypes.GET_ALL_EXERCISES_FAILURE: {
      return {
        ...state,
        errorMessage: action.payload
      };
    }
    case DirectoryActionsTypes.GET_MUSCLE_LOAD_FAILURE: {
      return {
        ...state,
        errorMessage: action.payload
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
    case DirectoryActionsTypes.SET_CARD_DISPLAYED_STYLE: {
      return {
        ...state,
        displayedStyle: 'card'
      };
    }
    case DirectoryActionsTypes.SET_TABLE_DISPLAYED_STYLE: {
      return {
        ...state,
        displayedStyle: 'table'
      };
    }
    case DirectoryActionsTypes.APPLY_GROUP_FILTER: {
      return  {
        ...state,
        groupedBy: action.payload,
        displayedStyle: 'table'
      };
    }
    case DirectoryActionsTypes.SET_SELECTED_EXERCISES: {
      return {
        ...state,
        selected: action.payload
      };
    }
    case DirectoryActionsTypes.SET_IS_EDITABLE: {
      return {
        ...state,
        isEditable: action.payload
      };
    }
    case DirectoryActionsTypes.SET_IS_EMBEDDABLE: {
      return {
        ...state,
        isEmbeddable: action.payload
      };
    }
    default: {
      return state;
    }
  }
}
