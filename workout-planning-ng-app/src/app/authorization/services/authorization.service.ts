import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtResponse } from "src/app/authorization/models/jwt-response";
import { AuthorizationLoginInfo } from "src/app/authorization/models/login";
import { SignUpInfo } from "src/app/authorization/models/signup";
import { TokenStorageService } from 'src/app/authorization/services/token-storage.service';

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
