import { Injectable } from '@angular/core';
import {HttpClient} from '../authorization/http-client.service';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Response} from "@angular/http";
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

  private loginUrl = 'https://localhost:8443/authentication/signin';
  private signupUrl = 'https://localhost:8443/authentication/signup';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthorizationLoginInfo): Observable<any> {
    return this.http.post(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    return this.http.post(this.signupUrl, info, httpOptions);
  }
}
