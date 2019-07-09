import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthorizationService } from "src/app/authorization/services/authorization.service";
import { TokenStorageService } from "src/app/authorization/services/token-storage.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  form: any = {};
  errorMessage = '';

  constructor(private authService: AuthorizationService, private tokenStorage: TokenStorageService,
              private router: Router) {}

  ngOnInit(): void {}

  onSubmit(): void {
    console.log(this.form);

  }
}
