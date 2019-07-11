import { Exercise } from "src/app/models/exercise";

export interface DirectoryState {
  exercises: Exercise[];
  isEditable: boolean;
  isEmbeddable: boolean;
  displayedStyle: string;
 }

export const initialDirectoryState = {
  exercises: [],
  isEditable: false,
  isEmbeddable: false,
  displayedStyle: 'table'
};
