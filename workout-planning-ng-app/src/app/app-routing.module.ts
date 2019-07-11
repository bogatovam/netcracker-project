import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DirectoryComponent } from "src/app/components/directory/directory.component";

const APP_ROUTES = [
  {path: '', pathMatch: 'full', redirectTo: '/home'},
  {path: 'directory', component: DirectoryComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(APP_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
