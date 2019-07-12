import { animate, state, style, transition, trigger } from '@angular/animations';
import { SelectionModel } from "@angular/cdk/collections";
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTable, MatTableDataSource } from "@angular/material";
import { Store } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";
import * as fromDirectory from "src/app/store/actions/directory.actions";
import {
  selectDisplayedStyle,
  selectExercises, selectGroupedBy,
  selectIsEditable,
  selectIsEmbeddable, selectMuscleLoad, selectSelectedExercises
} from "src/app/store/selectors/directory.selector";
import { AppState } from "src/app/store/state/app.state";

@Component({
  selector: 'app-directory',
  templateUrl: './directory.component.html',
  styleUrls: ['./directory.component.css'],
  animations: [
    trigger('detailExpand', [
      state('void', style({height: '0px', minHeight: '0', visibility: 'hidden'})),
      state('*', style({height: '*', visibility: 'visible'})),
      transition('void <=> *', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class DirectoryComponent implements OnInit {
  isEditable: boolean;
  isEmbeddable: boolean;
  displayedStyle: string;
  muscleLoad: string[];
  groupedBy: string;
  selected: Exercise[] = [];

  @ViewChild(MatTable) table: MatTable<Exercise>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns: string[] = ['name', 'complexity'];

  dataSource: MatTableDataSource<Exercise> = new MatTableDataSource<Exercise>([]);
  selection: SelectionModel<Exercise> = new SelectionModel(true, []);

  constructor(private store: Store<AppState>) {
  }

  isExpansionDetailRow = (index, row) => row.hasOwnProperty('detailRow');

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.store.dispatch<fromDirectory.GetAllExercises>(new fromDirectory.GetAllExercises());
    this.store.dispatch<fromDirectory.GetMuscleLoad>(new fromDirectory.GetMuscleLoad());

    this.store.select(selectMuscleLoad).subscribe(load => this.muscleLoad = load);

    this.store.select(selectExercises).subscribe(exercises => {
      this.dataSource = new MatTableDataSource<Exercise>(exercises);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;

      this.selected.forEach(s => {
        this.selection.select(this.dataSource.data.find((v, i, n) => {
          return v.id === s.id;
        }));
      });
    });

    this.store.select(selectIsEditable).subscribe(flag => {
      if (flag && !this.isEditable) {
        this.displayedColumns.unshift('select');
      } else if (!flag && this.isEditable) {
        this.displayedColumns.splice(0, 1);
      }
      this.isEditable = flag;
    });

    this.store.select(selectIsEmbeddable).subscribe(flag => this.isEmbeddable = flag);

    this.store.select(selectDisplayedStyle).subscribe(_style => this.displayedStyle = _style);

    this.store.select(selectGroupedBy).subscribe(_style => this.groupedBy = _style);

    this.store.select(selectSelectedExercises).subscribe(selectedExercises => {
      this.selected = selectedExercises;
      selectedExercises.forEach(s => {
        this.selection.select(this.dataSource.data.find((v, i, n) => {
          return v.id === s.id;
        }));
      });
    });
  }

  applyFilter(filterValue: string): void {
    this.dataSource.filterPredicate =
      (data: Exercise, filter: string) => {
        return (this.groupedBy === null && data.name.indexOf(filter) !== -1) ||
          (this.groupedBy !== null &&
            data.infForRecommendation.muscleLoad.get(this.groupedBy) > 0.4 &&
            data.name.indexOf(filter) !== -1);
      };

    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    if (filterValue === '') {
      this.applyGroupFilter(this.groupedBy);
    } else {
      this.dataSource.filter = filterValue;
    }
  }

  applyGroupFilter(filterValue: string): void {
    this.store.dispatch<fromDirectory.SetGroupedBy>(new fromDirectory.SetGroupedBy(filterValue));

    this.dataSource.filterPredicate =
      (data: Exercise, filter: string) => {
        return data.infForRecommendation.muscleLoad[filter] > 0.4;
      };

    this.dataSource.filter = filterValue;
    this.store.dispatch<fromDirectory.SetDisplayedStyle>(new fromDirectory.SetDisplayedStyle('table'));
  }

  isAllSelected(): boolean {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle(): void {
    if (this.isAllSelected()) {
      this.selection.clear();
      this.store.dispatch<fromDirectory.SetSelectedExercises>(new fromDirectory.SetSelectedExercises([]));
    } else {
      this.dataSource.data.forEach(row => this.selection.select(row));
      this.store.dispatch<fromDirectory.SetSelectedExercises>(new fromDirectory.SetSelectedExercises(this.dataSource.data));
    }
  }

  switchToCard(): void {
    this.store.dispatch<fromDirectory.SwitchToCard>(new fromDirectory.SwitchToCard());
  }

  switchToTable(): void {
    this.store.dispatch<fromDirectory.SwitchToCard>(new fromDirectory.SwitchToCard());
    this.dataSource.filter = '';
  }

  selectExercise(e: Exercise): void {
    this.selection.toggle(e);
    if (this.selection.isSelected(e)) {
      // this.selectedExercise.emit(e);
    } else {
      // this.unselectedExercise.emit(e);
    }
  }

  selectALlExercise(): void {
    this.masterToggle();

    // this.isAllSelected() ?
    //  this.dataSource.data.forEach(row => this.selectedExercise.emit(row)) :
    //  this.dataSource.data.forEach(row => this.unselectedExercise.emit(row));
  }

}
