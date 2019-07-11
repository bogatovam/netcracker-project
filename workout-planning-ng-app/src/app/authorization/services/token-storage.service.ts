import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthorizationToken';
const USERNAME_KEY = 'AuthorizationUsername';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() {}

  public static logOut(): void {
    window.localStorage.clear();
  }

  public static saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public static getToken(): string {
    return localStorage.getItem(TOKEN_KEY);
  }

  public static saveUserId(userId: string): void {
    window.localStorage.removeItem(USERNAME_KEY);
    window.localStorage.setItem(USERNAME_KEY, userId);
  }

  public static getUserId(): string {
    return localStorage.getItem(USERNAME_KEY);
  }
}
