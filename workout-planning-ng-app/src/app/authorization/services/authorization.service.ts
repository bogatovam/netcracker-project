import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from "src/app/authorization/models/user";
import { TokenStorageService } from 'src/app/authorization/services/token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {
  loginUrl: string = 'http://localhost:8080/authentication/signin';
  signupUrl: string = 'http://localhost:8080/authentication/signup';

  logOutUrl: string = 'http://localhost:4200';
  authorizationSuccessUrl: string = '/home';
  authorizationFailureUrl: string = 'authentication/login';
  signUpSuccessUrl: string = 'authentication/login';
  signUpFailureUrl: string = 'authentication/signup';

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  logIn(user: User): Observable<User> {
    return this.http.post<User>(this.loginUrl, user, httpOptions);
  }

  signUp(user: User): Observable<string> {
    return this.http.post<string>(this.signupUrl, user, httpOptions);
  }
}
