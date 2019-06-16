import {Exercise} from './exercise';

export interface Workout {
  id: string;
  name: string;
  exercises: Exercise[];
}
