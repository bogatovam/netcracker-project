import { Component, OnInit } from '@angular/core';
import { Store } from "@ngrx/store";
import { selectIsLoggedIn } from "src/app/authorization/store";
import { LogOut } from "src/app/authorization/store/actions/authorization.actions";
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

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.store.select(selectIsLoggedIn).subscribe(flag => this.isLogin = flag);
  }

  logOut(): void {
    this.store.dispatch(new LogOut());
  }
}
