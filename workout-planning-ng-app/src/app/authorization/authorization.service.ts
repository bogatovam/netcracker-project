import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JwtResponse } from '../model/JwtResponse';
import { AuthorizationLoginInfo } from '../model/login';
import { SignUpInfo } from '../model/signup';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private loginUrl = 'https://localhost:8443/users/signin';
  private signupUrl = 'https://localhost:8443/users/signup';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthorizationLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
