import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from "@angular/forms";
import { ErrorStateMatcher } from "@angular/material";
import { Router } from '@angular/router';
import { Store } from "@ngrx/store";
import { User } from "src/app/authorization/models/user";
import { AppState } from "src/app/authorization/store";
import { LogIn } from "src/app/authorization/store/actions/authorization.actions";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: User = new User('', '', '');
  loginFormControl = new FormControl(this.user.login, [Validators.required]);
  passwordFormControl = new FormControl(this.user.password, [Validators.required]);

  loginFormModel = new FormGroup({
    login: this.loginFormControl,
    password: this.passwordFormControl
  });

  matcher = new MyErrorStateMatcher();

  constructor(private router: Router, private store: Store<AppState>) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.user.login = this.loginFormModel.controls['login'].value;
    this.user.password = this.loginFormModel.controls['password'].value;
    this.store.dispatch<LogIn>(new LogIn(this.user));
  }

  redirect(): void {
    this.router.navigateByUrl('authentication/signup');
  }
}
