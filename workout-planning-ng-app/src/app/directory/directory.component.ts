import {animate, state, style, transition, trigger} from '@angular/animations';
import {SelectionModel} from '@angular/cdk/collections';
import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTable, MatTableDataSource} from '@angular/material';
import {Router} from '@angular/router';
import {AuthorizationService} from '../authorization/authorization.service';
import {ApiService} from '../shared/api.service';
import {Exercise} from '../shared/model/exercise';

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
  @Input() editable = false;
  @Input() fullVersion = true;
  @Input() displayedStyle = 'card';
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

  constructor(private router: Router, private authService: AuthorizationService,
              private apiService: ApiService) {
  }

  isExpansionDetailRow = (index, row) => row.hasOwnProperty('detailRow');

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.loadExercises();
    // console.log(this.initialSelection);

    if (this.editable) {
      this.displayedColumns.unshift('select');
    }
  }

  loadExercises(): void {
    this.apiService.getMuscleLoad().subscribe(
      result => {
        this.muscleLoad = result;
        },
      error1 => {
        console.log('ERROR: get exercises load: ' + error1.toString());
      }
    );
    this.apiService.getAllExercises().subscribe(
      result => {
//        console.log(result);
        this.dataSource = new MatTableDataSource<Exercise>(result);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        this.initialSelection.forEach(s => {
          this.selection.select(this.dataSource.data.find((v, i, n) => {
            return v.id === s.id;
          }));
        });

        this.table.renderRows();
      },
      error1 => {
        console.log('ERROR: get all exercise: ' + error1.toString());
      }
    );
  }

  applyFilter(filterValue: string): void {
    this.dataSource.filterPredicate =
      (data: Exercise, filter: string) => {
        return (this.groupedBy === null && data.name.indexOf(filter) != -1) ||
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
