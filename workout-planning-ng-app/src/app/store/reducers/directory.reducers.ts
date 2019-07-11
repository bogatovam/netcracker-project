import { DirectoryActions, DirectoryActionsTypes, GetAllExercisesSuccess } from "src/app/store/actions/directory.actions";
import { DirectoryState, initialDirectoryState } from "src/app/store/state/directory.state";

export function reducer(state: DirectoryState = initialDirectoryState, action: DirectoryActions): DirectoryState {
  switch (action.type) {
    case DirectoryActionsTypes.GET_ALL_EXERCISES: {
      return state;
    }
    case DirectoryActionsTypes.GET_ALL_EXERCISES_FAILURE: {
      return state;
    }
    case DirectoryActionsTypes.GET_ALL_EXERCISES_SUCCESS: {
      return {
        ...state,
        exercises: action.payload
      };
    }
    default: {
      return state;
    }
  }
}
