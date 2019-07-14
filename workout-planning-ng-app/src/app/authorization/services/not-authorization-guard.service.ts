import { Injectable } from '@angular/core';
import { CanActivate, Router } from "@angular/router";
import { TokenStorageService } from "src/app/authorization/services/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class NotAuthorizationGuardService implements CanActivate {
  constructor(public token: TokenStorageService,
              public router: Router) {
  }

  canActivate(): boolean {
    if (!TokenStorageService.getToken()) {
      this.router.navigateByUrl('home');
      return false;
    }
    return true;
  }
}
