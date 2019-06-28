import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from '../authorization/authorization.service';
import { TokenStorageService } from '../authorization/token-storage.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private token: TokenStorageService, private authService: AuthorizationService) { }

  ngOnInit(): void {}

  logout(): void {
    this.token.signOut();
    window.location.reload();
  }
}
