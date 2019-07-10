import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from "@angular/material";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  ngOnInit(): void { }

  constructor(private ref: MatDialogRef<LogoutComponent>) {}

  cancel(): void {
    this.ref.close(false);
  }

  confirm(): void {
    console.log('!!');
    this.ref.close(true);
  }
}
