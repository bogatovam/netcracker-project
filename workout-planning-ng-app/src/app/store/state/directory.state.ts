import { Exercise } from "src/app/models/exercise";

export interface DirectoryState {
  exercises: Exercise[];
  isEditable: boolean;
  isEmbeddable: boolean;
  displayedStyle: string;
  selected: Exercise[];
  groupedBy: string;
  muscleLoad: string[];
  errorMessage: string;
}

export const initialDirectoryState = {
  exercises: [],
  isEditable: false,
  isEmbeddable: true,
  displayedStyle: 'table',
  selected: [],
  groupedBy: null,
  muscleLoad: [],
  errorMessage: null
};
