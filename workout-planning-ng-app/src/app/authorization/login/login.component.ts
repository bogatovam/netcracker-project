import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthorizationLoginInfo } from '../../shared/model/login';
import { AuthorizationService } from '../authorization.service';
import { TokenStorageService } from '../token-storage.service';

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
              private router: Router) {}

  ngOnInit(): void {}

  onSubmit(): void {
    this.loginInfo = new AuthorizationLoginInfo(
      this.form.username,
      this.form.password);
    console.log(this.loginInfo);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);

        this.authService.isLoginFailed = false;
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.authService.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  redirect(): void {
    this.router.navigateByUrl('authentication/signup');
  }
}
