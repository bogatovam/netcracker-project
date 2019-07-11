import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material";
import { Exercise } from "src/app/models/exercise";

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent implements OnInit {
  @Input() exercise: Exercise;
  muscleLoad: string[] = ['hips', 'biceps', 'abs', 'chest', 'shoulders', 'back'];

  myLoad = new  MatTableDataSource<string>(this.muscleLoad);
  displayedColumns: string[] = ['name', 'load'];

  constructor() { }
  ngOnInit(): void { }
}
