import {Component, Input, OnInit, Output} from '@angular/core';
import {Exercise} from '../../shared/model/exercise';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent implements OnInit {
  @Input() exercise: Exercise;
  constructor() {}

  ngOnInit() {}

}
