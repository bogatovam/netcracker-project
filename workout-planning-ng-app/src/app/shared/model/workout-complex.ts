import { Workout } from './workout';

export class WorkoutComplex {
  constructor(public id: string, public name: string,
              public description: string, public workouts: Workout[]) {
  }
}
