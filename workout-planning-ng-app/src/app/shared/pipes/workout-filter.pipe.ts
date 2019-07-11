import { Pipe, PipeTransform } from '@angular/core';
import { Workout } from "src/app/models/workout";

@Pipe({
  name: 'workoutFilter'
})
export class WorkoutFilterPipe implements PipeTransform {

  transform(workouts: Workout[], text: string): Workout[] {
    if (text === null || text === '') {
      return workouts;
    }
    return workouts.filter(w => w.name.includes(text));
  }
}
