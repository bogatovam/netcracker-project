import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { Store } from "@ngrx/store";
import { Observable } from "rxjs";
import { User } from "src/app/authorization/models/user";
import { AppState, selectIsLoggedIn, selectUser } from "src/app/authorization/store";
import { DeleteUser, GetUser } from "src/app/authorization/store/actions/authorization.actions";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: User;
  isLogin: boolean = false;

  getUser: Observable<User>;
  getIsLogin: Observable<boolean>;

  constructor(private router: Router, private store: Store<AppState>) {
    this.getUser = this.store.select(selectUser);
    this.getIsLogin = this.store.select(selectIsLoggedIn);
  }

  ngOnInit(): void {
    this.getIsLogin.subscribe(flag => {
      this.getUser.subscribe(user => this.user = user);
      this.isLogin = flag;
      if (this.isLogin && this.user === null) {
        this.store.dispatch<GetUser>(new GetUser());
      }
    });

  }

  redirect(): void {
    this.router.navigateByUrl('workout-complex');
  }

  deleteUser(): void {
    this.store.dispatch<DeleteUser>(new DeleteUser());
  }
}
