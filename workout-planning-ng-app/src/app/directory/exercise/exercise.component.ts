import {Component, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {Exercise} from '../../shared/model/exercise';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css'],
})
export class ExerciseComponent implements OnInit {
  @Input() exercise: Exercise;
  muscleLoad: string[] = ['hips', 'biceps', 'abs', 'chest', 'shoulders', 'back'];

  myLoad = new  MatTableDataSource<string>(this.muscleLoad);
  displayedColumns: string[] = ['name', 'load'];

  constructor() {}

  ngOnInit() {
  }
}
