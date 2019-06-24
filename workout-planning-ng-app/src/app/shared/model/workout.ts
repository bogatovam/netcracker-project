import {Exercise} from './exercise';

export class Workout {
  id: string;
  name: string;
  description: string;
  exercises: Exercise[];

  constructor(id: string, name: string,
              description: string, exercises: Exercise[]) {
  }
}
