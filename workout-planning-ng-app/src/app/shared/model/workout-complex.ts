import {Workout} from './workout';

export class WorkoutComplex {
  id: string;
  name: string;
  description: string;
  workouts: Workout[];

  constructor(id: string, name: string,
              description: string, workouts: Workout[]) {
  }
}
