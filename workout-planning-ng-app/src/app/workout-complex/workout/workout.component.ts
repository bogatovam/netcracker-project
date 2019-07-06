import { animate, state, style, transition, trigger } from '@angular/animations';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTable, MatTableDataSource } from '@angular/material';
import { ApiService } from '../../shared/api.service';
import { Exercise } from '../../shared/model/exercise';
import { Workout } from '../../shared/model/workout';
import { WorkoutComplex } from '../../shared/model/workout-complex';

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

  @Output() sourceWorkoutComplexChange: EventEmitter<WorkoutComplex> = new EventEmitter<WorkoutComplex>();
  @Input() isEditable: boolean = false;
  isLoaded: boolean = false;
  selectedSourceWorkoutId: string;

  dataSource = new MatTableDataSource<Exercise>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<Exercise>;

  constructor(private apiService: ApiService) {
  }
  ngOnInit(): void {
    if(this.sourceWorkoutComplex === null) {
      this.apiService.getSourceWorkoutComplex(this.workout).subscribe(
        result => {
          console.log(result);
          this.sourceWorkoutComplex = result;
          this.selectedSourceWorkoutId = result.id;
          this.sourceWorkoutComplexChange.emit(this.sourceWorkoutComplex);
          this.isLoaded = true;
        }
      );
    } else {
      this.selectedSourceWorkoutId = this.sourceWorkoutComplex.id;
      this.isLoaded = true;
    }
    this.dataSource = new MatTableDataSource<Exercise>(this.workout.exercises);
      }

  selectExercise(exercise: Exercise): void {
    this.selectedExercise = exercise;
  }

  getMuscleLoad(w: Workout): string[] {
    return [];
  }

  getComplexity(w: Workout): string {
    return '';
  }

  setEditable(flag: boolean): void {
    this.isEditable = flag;
  }

  drop(event: CdkDragDrop<MatTableDataSource<Exercise>, any>): void {
    const prevIndex = this.dataSource.data.findIndex((d) => d === event.item.data);
    moveItemInArray(this.dataSource.data, prevIndex, event.currentIndex);
    this.table.renderRows();
  }

  addExercise(e: Exercise): void {
    this.dataSource.data.unshift(e);
    this.table.renderRows();
  }

  deleteExercise(e: Exercise): void {
    this.dataSource.data.splice(this.dataSource.data.findIndex((v, n, o) => {
      return v.id === e.id;
    }), 1);
    this.table.renderRows();
  }

  changeSourceWorkoutComplex(): void {
    console.log(this.selectedSourceWorkoutId);
    this.workoutComplexes.forEach((workoutComplex) => {
      if (this.selectedSourceWorkoutId === workoutComplex.id) {
        this.sourceWorkoutComplex = workoutComplex;
      }
    });
    this.sourceWorkoutComplexChange.emit(this.sourceWorkoutComplex);
    console.log(this.sourceWorkoutComplex);
  }
}
