import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './authorization/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void { }
}
