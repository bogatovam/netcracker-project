import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'workout-planning-ng-app';
  greeting = {};
  constructor(private http: HttpClient) {
    //http.get('https://localhost:8443/test').subscribe(data => this.greeting = data);
  }
}
