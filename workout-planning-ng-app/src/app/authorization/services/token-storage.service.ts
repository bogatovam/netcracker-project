import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthorizationToken';
const USERNAME_KEY = 'AuthorizationUsername';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() {}

  public static logOut(): void {
    window.sessionStorage.clear();
  }

  public static saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public static getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public static saveLogin(login: string): void {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, login);
  }

  public static getLogin(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }
}
