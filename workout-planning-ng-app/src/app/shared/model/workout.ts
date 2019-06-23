import {Exercise} from './exercise';

export interface Workout {
  id: string;
  name: string;
  description: string;
  exercises: Exercise[];
}
