import { SelectionModel } from "@angular/cdk/collections";
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTable, MatTableDataSource } from "@angular/material";
import { Exercise } from "src/app/models/exercise";

@Component({
  selector: 'app-directory',
  templateUrl: './directory.component.html',
  styleUrls: ['./directory.component.css']
})
export class DirectoryComponent implements OnInit {
  @Input() exercises: Exercise[];
  @Input() isEditable: boolean;
  @Input() isEmbeddable: boolean;
  @Input() displayedStyle: string;

  @Input() initialSelection = [];

  @Output() unselectedExercise: EventEmitter<Exercise> = new EventEmitter<Exercise>();
  @Output() selectedExercise: EventEmitter<Exercise> = new EventEmitter<Exercise>();

  @ViewChild(MatTable) table: MatTable<Exercise>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  displayedColumns: string[] = ['name', 'complexity'];
  dataSource = new MatTableDataSource<Exercise>();

  allowMultiSelect = true;
  selection = new SelectionModel<Exercise>(this.allowMultiSelect, this.initialSelection);

  muscleLoad: string[] = null; // = ['hips', 'biceps', 'abs', 'chest', 'shoulders', 'back'];
  groupedBy: string = null;

  constructor() { }

  isExpansionDetailRow = (index, row) => row.hasOwnProperty('detailRow');

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.loadExercises();
    // console.log(this.initialSelection);

    if (this.isEditable) {
      this.displayedColumns.unshift('select');
    }
  }

  loadExercises(): void {
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
    this.groupedBy = filterValue;

    this.dataSource.filterPredicate =
      (data: Exercise, filter: string) => {
        return data.infForRecommendation.muscleLoad[filter] > 0.4;
      };

    this.dataSource.filter = filterValue;
    this.displayedStyle = 'table';
  }

  isAllSelected(): boolean {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  masterToggle(): void {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  switchToCard(): void {
    this.displayedStyle = 'card';
    this.groupedBy = null;
  }

  switchToTable(): void {
    this.displayedStyle = 'table';
    this.groupedBy = null;
    this.dataSource.filter = '';
  }

  selectExercise(e: Exercise): void {
    this.selection.toggle(e);
    if (this.selection.isSelected(e)) {
      this.selectedExercise.emit(e);
    } else {
      this.unselectedExercise.emit(e);
    }
  }

  selectALlExercise(): void {
    this.masterToggle();

    this.isAllSelected() ?
      this.dataSource.data.forEach(row => this.selectedExercise.emit(row)) :
      this.dataSource.data.forEach(row => this.unselectedExercise.emit(row));
  }

}
