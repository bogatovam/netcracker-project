import { CdkDragDrop, moveItemInArray } from "@angular/cdk/drag-drop";
import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { FormControl } from "@angular/forms";
import { MatPaginator, MatSort, MatTable, MatTableDataSource } from "@angular/material";
import { ActivatedRoute } from "@angular/router";
import { Store } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";
import * as fromDirectory from "src/app/store/actions/directory.actions";
import * as fromWorkout from "src/app/store/actions/workout.actions";
import { selectWorkoutComplexes } from "src/app/store/selectors/workout-complex.selector";
import {
  selectError,
  selectIsEditable,
  selectIsLoaded,
  selectSourceWorkoutComplex,
  selectWorkout
} from "src/app/store/selectors/workout.selector";
import { AppState } from "src/app/store/state/app.state";

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
  sourceWorkoutComplexIdControl: FormControl;

  dataSource = new MatTableDataSource<Exercise>();
  displayedColumns: string[] = ['name', 'complexity'];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<Exercise>;

  constructor(private activateRoute: ActivatedRoute, private store: Store<AppState>) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(params => {
      const workoutId = params['id'];
      const workoutComplexId = params['workoutComplexId'];
      if (workoutId === 'create') {
        this.store.dispatch(new fromWorkout.LoadSourceWorkoutComplex(workoutComplexId));
        this.openFormToCreatingWorkout();
      } else {
        this.store.dispatch(new fromWorkout.LoadWorkout(workoutId));
      }
    });

    this.store.select(selectWorkoutComplexes).subscribe(workoutComplexes => this.workoutComplexes = workoutComplexes);
    this.store.select(selectWorkout).subscribe(workout => {
      this.workout = workout;
      if (this.workout !== null) {
        this.dataSource = new MatTableDataSource<Exercise>(this.workout.exercises);
      }
    });
    this.store.select(selectSourceWorkoutComplex).subscribe(source => {
      this.sourceWorkoutComplex = source;
      if (this.sourceWorkoutComplex !== null) {
        this.sourceWorkoutComplexIdControl = new FormControl(this.sourceWorkoutComplex.id);
      }
    });
    this.store.select(selectIsEditable).subscribe(flag => this.isEditable = flag);
    this.store.select(selectIsLoaded).subscribe(flag => this.isLoaded = flag);
    this.store.select(selectError).subscribe(error => this.errorMessage = error);
  }

  openFormToCreatingWorkout(): void {
    this.nameWorkoutControl = new FormControl('');
    this.descriptionWorkoutControl = new FormControl('');
    this.store.dispatch(new fromDirectory.SetIsEditable(true));
    this.store.dispatch(new fromDirectory.SetIsEmbeddable(true));
    this.store.dispatch(new fromWorkout.SetEditable(true));
  }

  openFormToEditingWorkout(): void {
    this.nameWorkoutControl = new FormControl(this.workout.name);
    this.descriptionWorkoutControl = new FormControl(this.workout.description);
    this.sourceWorkoutComplexIdControl = new FormControl(this.sourceWorkoutComplex.id);
    this.store.dispatch(new fromDirectory.SetIsEditable(true));
    this.store.dispatch(new fromDirectory.SetIsEmbeddable(true));
    this.store.dispatch(new fromWorkout.SetEditable(true));
  }

  saveEditedOrCreatedWorkoutComplex(): void {
    if (this.workout === null) {
      const newWorkout = new Workout(null,
        this.nameWorkoutControl.value,
        this.descriptionWorkoutControl.value,
        this.dataSource.data);
      this.store.dispatch(new fromWorkout.CreateWorkout({workout: newWorkout, workoutComplex: this.sourceWorkoutComplexIdControl.value}));
    } else {
      const newWorkout = new Workout(this.workout.id,
        this.nameWorkoutControl.value,
        this.descriptionWorkoutControl.value,
        this.dataSource.data);

      if (this.sourceWorkoutComplexIdControl.value !== this.sourceWorkoutComplex.id) {
        this.store.dispatch(new fromWorkout.ChangeSourceWorkoutComplex({
          oldWorkoutComplexId: this.sourceWorkoutComplex.id,
          newWorkoutComplexId: this.sourceWorkoutComplexIdControl.value,
          workoutId: this.workout.id
        }));
      }
      this.store.dispatch(new fromWorkout.UpdateWorkout(newWorkout));
    }
  }

  cancel(): void {
    this.store.dispatch(new fromDirectory.SetIsEmbeddable(false));
  }

  deleteWorkout(): void {
    this.store.dispatch(new fromWorkout.DeleteWorkout(this.workout));
  }

  getMuscleLoad(w: Workout): string[] {
    return [];
  }

  getComplexity(w: Workout): string {
    return '';
  }

  drop(event: CdkDragDrop<MatTableDataSource<Exercise>, any>): void {
    console.log(event);
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
}
