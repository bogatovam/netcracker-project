import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Workout} from '../../shared/model/workout';
import {ApiService} from '../../shared/api.service';
import {Exercise} from '../../shared/model/exercise';
import {WorkoutComplex} from '../../shared/model/workout-complex';
import {MatPaginator, MatSort, MatTable, MatTableDataSource} from '@angular/material';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css'],
  animations: [
    trigger('detailExpand', [
      state('void', style({height: '0px', minHeight: '0', visibility: 'hidden'})),
      state('*', style({height: '*', visibility: 'visible'})),
      transition('void <=> *', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class WorkoutComponent implements OnInit {
  displayedColumns: string[] = ['name', 'complexity'];
  selectedExercise: Exercise = null;
  @Input() workoutComplexes: WorkoutComplex[];
  @Input() sourceWorkoutComplex: WorkoutComplex;
  @Input() workout: Workout;

  @Output() workoutUpdated: EventEmitter<Workout> = new EventEmitter<Workout>();
  @Output() workoutDeleted: EventEmitter<Workout> = new EventEmitter<Workout>();
  @Output() unselectedWorkout: EventEmitter<WorkoutComplex> = new EventEmitter<WorkoutComplex>();

  @Input() isEditable: boolean = false;

  dataSource = new MatTableDataSource<Exercise>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<Exercise>;

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.dataSource = new MatTableDataSource<Exercise>(this.workout.exercises);
  }

  updateWorkout(): void {
    this.workoutUpdated.emit(this.workout);
  }

  deleteWorkout(): void {
    this.workoutDeleted.emit(this.workout);
  }

  selectExercise(exercise: Exercise): void {
    this.selectedExercise = exercise;
  }

  getMuscleLoad(w: any) {
    return [];
  }

  getComplexity(w: any) {
    return [];
  }

  setEditable(flag: boolean): void {
    this.isEditable = flag;
  }

  drop(event: CdkDragDrop<MatTableDataSource<Exercise>, any>) {
    const prevIndex = this.dataSource.data.findIndex((d) => d === event.item.data);
    moveItemInArray(this.dataSource.data, prevIndex, event.currentIndex);
    this.table.renderRows();
  }

  addExercise(e: Exercise): void {
    this.dataSource.data.unshift(e);
    this.table.renderRows();
  }

  deleteExercise(e: Exercise) {
    this.dataSource.data.splice(this.dataSource.data.findIndex((v, n, o) => {
      return v.id === e.id
    }), 1);
    this.table.renderRows();
  }
}
