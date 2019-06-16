import {Component, OnInit} from '@angular/core';

import {AuthorizationService} from '../authorization.service';
import {SignUpInfo} from '../../shared/model/signup';
import {Router} from '@angular/router';
import {TokenStorageService} from '../token-storage.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  errorMessage = '';

  constructor(private authService: AuthorizationService, private tokenStorage: TokenStorageService,
              private router: Router) {}

  ngOnInit() {}

  onSubmit(): void {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      this.form.name,
      this.form.username,
      this.form.email,
      this.form.password,
      this.form.gender,
      this.form.age,
      this.form.weight,
      this.form.growth,
      this.form.goal
    );

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.authService.isSignedUp = true;
        this.authService.isSignUpFailed = false;
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.authService.isSignUpFailed = true;
      }
    );
  }
}
