import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Workout} from "../shared/model/workout";
import {UserService} from "../shared/user.service";
import {Exercise} from "../shared/model/exercise";

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css']
})
export class WorkoutComponent implements OnInit {

  private exercises: Exercise[];
  selectedWorkout: Workout;

  @Input() workout: Workout;
  @Output() workoutUpdated: EventEmitter<Workout> = new EventEmitter<Workout>();
  @Output() workoutDeleted: EventEmitter<Workout> = new EventEmitter<Workout>();

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.getNamesOfExercises();
  }

  updateWorkout() {
    this.workoutUpdated.emit(this.workout);
  }

  deleteWorkout() {
    this.workoutDeleted.emit(this.workout);
  }

  getNamesOfExercises() {
    this.userService.getExercises(this.workout.id).subscribe(
      result => {
        console.log(result);
        this.exercises = result;
      },
      error1 => {
        console.log(error1);
      }
    );
  }
}
