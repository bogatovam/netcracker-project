import { Component, OnInit } from '@angular/core';
import { Store } from "@ngrx/store";
import { tap } from "rxjs/operators";
import { AppState, selectIsLoggedIn } from "src/app/authorization/store";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  isLogin: boolean = false ;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
    this.store.select(selectIsLoggedIn).subscribe(flag => this.isLogin = flag);
  }

}
