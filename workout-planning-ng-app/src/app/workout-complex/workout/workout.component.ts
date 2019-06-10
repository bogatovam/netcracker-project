import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Workout} from "../../shared/model/workout";
import {ApiService} from "../../shared/api.service";
import {Exercise} from "../../shared/model/exercise";

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css']
})
export class WorkoutComponent implements OnInit {

  private exercises: Exercise[];

  @Input() workout: Workout;
  @Output() workoutUpdated: EventEmitter<Workout> = new EventEmitter<Workout>();
  @Output() workoutDeleted: EventEmitter<Workout> = new EventEmitter<Workout>();

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.exercises = this.workout.exercises;
  }

  updateWorkout() {
    this.workoutUpdated.emit(this.workout);
  }

  deleteWorkout() {
    this.workoutDeleted.emit(this.workout);
  }
}
