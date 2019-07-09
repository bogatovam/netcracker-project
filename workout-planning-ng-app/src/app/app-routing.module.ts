import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

const APP_ROUTES = [
  {path: '', pathMatch: 'full', redirectTo: '/home'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(APP_ROUTES, { enableTracing: true })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
