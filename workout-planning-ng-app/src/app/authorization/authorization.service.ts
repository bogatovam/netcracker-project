import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtResponse } from '../shared/model/jwt-response';
import { AuthorizationLoginInfo } from '../shared/model/login';
import { SignUpInfo } from '../shared/model/signup';
import {TokenStorageService} from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  private loginUrl = 'http://localhost:8080/authentication/signin';
  private signupUrl = 'http://localhost:8080/authentication/signup';

  private _isSignedUp = false;
  private _isSignUpFailed = false;

  private _isLoggedIn = this.tokenStorage.getToken() !== null;
  private _isLoginFailed = false;

  get isSignedUp(): boolean {
    return this._isSignedUp;
  }

  set isSignedUp(value: boolean) {
    this._isSignedUp = value;
  }

  get isSignUpFailed(): boolean {
    return this._isSignUpFailed;
  }

  set isSignUpFailed(value: boolean) {
    this._isSignUpFailed = value;
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }

  set isLoggedIn(value: boolean) {
    this._isLoggedIn = value;
  }

  get isLoginFailed(): boolean {
    return this._isLoginFailed;
  }

  set isLoginFailed(value: boolean) {
    this._isLoginFailed = value;
  }

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  attemptAuth(credentials: AuthorizationLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
