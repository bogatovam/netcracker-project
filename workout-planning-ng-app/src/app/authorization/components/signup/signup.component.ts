import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from "@angular/forms";
import { ErrorStateMatcher } from "@angular/material";
import { Router } from '@angular/router';
import { Store } from "@ngrx/store";
import { Gender, Goal, Goals, State, User } from "src/app/authorization/models/user";
import { AppState } from "src/app/authorization/store";
import { SignUp } from "src/app/authorization/store/actions/authorization.actions";

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return (control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user: User = new User();

  matcher = new MyErrorStateMatcher();
  genders: Gender[] = [{char: 'F', name: 'Женский'}, {char: 'M', name: 'Мужской'}];
  goals: Goal[] = [{name: 'Рельеф', goal: Goals.MUSCLE_RELIEF},
    {name: 'Набор массы', goal: Goals.SET_MASS},
    {name: 'Потеря веса', goal: Goals.WEIGHT_LOSS}];

  fullNameFormControl = new FormControl('', [Validators.required]);
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  loginFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  passwordFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  genderFormControl = new FormControl(this.genders[0], [Validators.required]);
  dobFormControl = new FormControl('', [Validators.required]);
  weightFormControl = new FormControl(60, [Validators.required]);
  growthFormControl = new FormControl(170, [Validators.required]);
  workoutsGoalFormControl = new FormControl('', [Validators.required]);

  signUpFormModel = new FormGroup({
    fullName: this.fullNameFormControl,
    email: this.emailFormControl,
    login: this.loginFormControl,
    password: this.passwordFormControl,
    gender: this.genderFormControl,
    dob: this.dobFormControl,
    weight: this.weightFormControl,
    growth: this.growthFormControl,
    workoutsGoal: this.workoutsGoalFormControl
  });

  constructor(private store: Store<AppState>) {
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (!this.signUpFormModel.invalid) {
      this.user.fullName = this.signUpFormModel.controls['fullName'].value;
      this.user.email = this.signUpFormModel.controls['email'].value;
      this.user.login = this.signUpFormModel.controls['login'].value;
      this.user.password = this.signUpFormModel.controls['password'].value;
      this.user.roles = 'ADMIN';
      this.user.state = State.ACTIVE;
      this.user.gender = this.signUpFormModel.controls['gender'].value;
      this.user.dateOfBirth = this.signUpFormModel.controls['dob'].value;
      this.user.weight = this.signUpFormModel.controls['weight'].value;
      this.user.growth = this.signUpFormModel.controls['growth'].value;
      this.user.workoutsGoal = this.signUpFormModel.controls['workoutsGoal'].value;
      console.log(this.user);
      this.store.dispatch<SignUp>(new SignUp(this.user));
    }
  }
}
