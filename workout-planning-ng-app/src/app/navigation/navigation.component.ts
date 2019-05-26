import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../authorization/token-storage.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }
}
