import {Workout} from './workout';

export interface WorkoutComplex {
  id: string;
  name: string;
  description: string;
  workouts: Workout[];
}
