import { Injectable } from '@angular/core';
import { CanActivate, Router } from "@angular/router";
import { TokenStorageService } from "src/app/authorization/services/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuardService implements CanActivate {
  constructor(public token: TokenStorageService,
              public router: Router) {
  }

  canActivate(): boolean {
    if (!this.token.getToken()) {
      this.router.navigateByUrl('/log-in');
      return false;
    }
    return true;
  }
}
