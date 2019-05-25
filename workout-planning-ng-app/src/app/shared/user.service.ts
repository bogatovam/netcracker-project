import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'https://localhost:8443/test';

  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<any> {
    return this.http.get(this.userUrl);
  }

}
