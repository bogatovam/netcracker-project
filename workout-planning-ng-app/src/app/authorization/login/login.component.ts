import { Component, OnInit } from '@angular/core';

import { AuthorizationService } from '../authorization.service';
import { TokenStorageService } from '../token-storage.service';
import { AuthorizationLoginInfo } from '../../shared/model/login';
import {JwtResponse} from "../../shared/model/jwt-response";
import {Router, RouterModule, Routes} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: any = {};
  errorMessage = '';
  roles: string[] = [];

  private loginInfo: AuthorizationLoginInfo;

  constructor(private authService: AuthorizationService, private tokenStorage: TokenStorageService,
              private router : Router) { }

  ngOnInit() {

  }

  onSubmit() {
    console.log(this.form);
    this.loginInfo = new AuthorizationLoginInfo(
      this.form.username,
      this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.authService.isLoginFailed = false;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.authService.isLoginFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

  redirect() {
    this.router.navigateByUrl("auth/signup");
  }

}