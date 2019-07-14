import {animate, state, style, transition, trigger} from "@angular/animations";
import { CdkDragDrop, moveItemInArray } from "@angular/cdk/drag-drop";
import { Component, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { FormControl } from "@angular/forms";
import { MatPaginator, MatSort, MatTable, MatTableDataSource } from "@angular/material";
import { ActivatedRoute, NavigationEnd, Router, RouterEvent } from "@angular/router";
import { Store } from "@ngrx/store";
import { Exercise } from "src/app/models/exercise";
import { Workout } from "src/app/models/workout";
import { WorkoutComplex } from "src/app/models/workout-complex";
import * as fromDirectory from "src/app/store/actions/directory.actions";
import * as fromWorkout from "src/app/store/actions/workout.actions";
import { selectWorkoutComplexes } from "src/app/store/selectors/workout-complex.selector";
import {
  selectError, selectExercises,
  selectIsEditable,
  selectIsLoaded,
  selectSourceWorkoutComplex,
  selectWorkout
} from "src/app/store/selectors/workout.selector";
import { AppState } from "src/app/store/state/app.state";

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
  ],
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

  constructor(private activateRoute: ActivatedRoute, private store: Store<AppState>, private router: Router) { }

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
    });
    this.store.select(selectExercises).subscribe(exercises => {
      console.log(exercises);
        this.dataSource = new MatTableDataSource<Exercise>(exercises);
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
    this.store.dispatch(new fromWorkout.AddTemplateToCreatingWorkout());
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
      const workoutComplex = this.sourceWorkoutComplex.id === this.sourceWorkoutComplexIdControl.value ?
         this.sourceWorkoutComplex
        : this.workoutComplexes.find((wc) => wc.id === this.sourceWorkoutComplexIdControl.value);
      if (workoutComplex !== null) {
        this.store.dispatch(new fromWorkout.CreateWorkout({workout: newWorkout, workoutComplex: workoutComplex}));
      }
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
    if (this.workout === null) {
      this.router.navigateByUrl('workout-complex');
    }
    this.store.dispatch(new fromWorkout.SetEditable(false));
  }

  back(): void {
    this.router.navigateByUrl('workout-complex');
  }

  setEditable(flag: boolean): void {
    this.openFormToEditingWorkout();
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
}
