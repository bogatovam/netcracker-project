import { SelectionModel } from "@angular/cdk/collections";
import { MatTableDataSource } from "@angular/material";
import { Exercise } from "src/app/models/exercise";

export interface DirectoryState {
  exercises: MatTableDataSource<Exercise>;
  isEditable: boolean;
  isEmbeddable: boolean;
  displayedStyle: string;
  selected: SelectionModel<Exercise>;
  groupedBy: string;
  muscleLoad: string[];
}

export const initialDirectoryState = {
  exercises: new MatTableDataSource<Exercise>(),
  isEditable: false,
  isEmbeddable: false,
  displayedStyle: 'table',
  selected: new SelectionModel<Exercise>(true, []),
  groupedBy: null,
  muscleLoad: []
};
