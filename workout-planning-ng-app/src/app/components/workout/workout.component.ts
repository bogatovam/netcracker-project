import { CdkDragDrop, moveItemInArray } from "@angular/cdk/drag-drop";
import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { FormControl } from "@angular/forms";
import { MatPaginator, MatSort, MatTable, MatTableDataSource } from "@angular/material";
import { ActivatedRoute } from "@angular/router";
import { switchMap } from "rxjs/operators";
import { Exercise } from "src/app/models/exercise";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css']
})
export class WorkoutComponent implements OnInit {
  workout: Workout;
  sourceWorkoutComplex: WorkoutComplex;
  isEditable: boolean;
  isLoaded: boolean;
  errorMessage: string;

  workoutComplexes: WorkoutComplex[];
  nameWorkoutControl: FormControl;
  descriptionWorkoutControl: FormControl;
  sourceWorkoutIdControl: FormControl;

  dataSource = new MatTableDataSource<Exercise>();
  displayedColumns: string[] = ['name', 'complexity'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(params => {
      const workoutId = params['id'];
      if (workoutId === 'create') {

      } else {

      }
    });

    if (this.sourceWorkoutComplex === null) {
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
