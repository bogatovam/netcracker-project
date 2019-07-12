import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { switchMap } from "rxjs/operators";

@Component({
  selector: 'app-workout',
  templateUrl: './workout.component.html',
  styleUrls: ['./workout.component.css']
})
export class WorkoutComponent implements OnInit {

  constructor(private activateRoute: ActivatedRoute) {
    this.activateRoute.paramMap.pipe(
      switchMap(params => params.get('id')))
      .subscribe(data => console.log(data));
  }

  ngOnInit(): void { }

}
