import { Exercise } from './exercise';

export class Workout {
  constructor(public id: string, public name: string,
              public description: string, public exercises: Exercise[]) {
  }
}
