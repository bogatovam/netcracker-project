import { Injectable } from '@angular/core';
import {HttpClient} from '../authorization/http-client.service';
import { Observable } from 'rxjs';


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
