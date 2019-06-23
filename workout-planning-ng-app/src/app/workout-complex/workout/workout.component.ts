import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Workout} from '../../shared/model/workout';
import {ApiService} from '../../shared/api.service';
import {Exercise} from '../../shared/model/exercise';
import {WorkoutComplex} from "../../shared/model/workout-complex";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css']
})
export class WorkoutComponent implements OnInit {
  displayedColumns: string[] = ['name', 'complexity'];
  selectedExercise: Exercise = null;
  @Input() workoutComplexes: WorkoutComplex[];
  @Input() workout: Workout;

  @Output() workoutUpdated: EventEmitter<Workout> = new EventEmitter<Workout>();
  @Output() workoutDeleted: EventEmitter<Workout> = new EventEmitter<Workout>();
  @Output() unselectedWorkout: EventEmitter<WorkoutComplex> = new EventEmitter<WorkoutComplex>();


  dataSource = new MatTableDataSource<Exercise>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

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
    return[];
  }
}
