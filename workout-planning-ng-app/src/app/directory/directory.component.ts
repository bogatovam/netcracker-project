import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "../shared/api.service";
import {AuthorizationService} from "../authorization/authorization.service";

@Component({
  selector: 'app-directory',
  templateUrl: './directory.component.html',
  styleUrls: ['./directory.component.css']
})
export class DirectoryComponent implements OnInit {


  constructor(private apiService: ApiService, private authService: AuthorizationService,
              private router: Router) {
  }

  ngOnInit() {
  }

}
