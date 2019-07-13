import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from "@angular/router";
import { Store } from "@ngrx/store";
import { selectIsLoggedIn } from "src/app/authorization/store";
import { LogOut } from "src/app/authorization/store/actions/authorization.actions";
import * as fromWorkoutComplex from "src/app/store/actions/workout-complex.actions";
import * as fromWorkout from "src/app/store/actions/workout.actions";
import { AppState } from "src/app/store/state/app.state";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  isLogin: boolean = false ;

  loginUrl: string = 'authentication/login';
  signupUrl: string = 'authentication/signup';
  homeUrl: string = 'home';
  directoryUrl: string = 'directory';
  workoutComplexUrl: string = 'workout-complex';

  constructor(private store: Store<AppState>, private router: Router) {
  }

  ngOnInit(): void {
    this.store.select(selectIsLoggedIn).subscribe(flag => this.isLogin = flag);
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd ) {
        if (event.url.match('workout-complex/[0-9a-z]+/workout/[0-9a-z]+') === null) {
          console.log("w editable");
          this.store.dispatch(new fromWorkout.SetEditable(false));
        }
        if (event.url.indexOf('workout-complex') === -1) {
          console.log("wc editable");
          this.store.dispatch(new fromWorkoutComplex.SetIsWorkoutComplexEditable(false));
        }
      }
    });
  }

  logOut(): void {
    this.store.dispatch(new LogOut());
  }
}
