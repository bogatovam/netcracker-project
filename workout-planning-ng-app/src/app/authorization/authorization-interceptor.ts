// import { Injectable } from '@angular/core';
// import {HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders} from '@angular/common/http';
// import {HttpClient} from "@angular/common/http";
// import { TokenStorageService } from './token-storage.service';
//
// const TOKEN_HEADER_KEY = 'Authorization';
//
// @Injectable()
// export class AuthInterceptor implements HttpInterceptor {
//
//   constructor(private token: TokenStorageService) { }
//
//   intercept(req: HttpRequest<any>, next: HttpHandler) {
//     console.log("I intercept")
//     let  authReq = req;
//     // const token = this.token.getToken();
//     // if (token != null) {
//     //   console.log("I found token!");
//     //   console.log(req.headers);
//     //
//     //   authReq = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token) });
//     //   authReq.headers.set('Authorization', 'Bearer ' + token);
//     //   authReq.headers.append('Authorization', 'Bearer ' + token);
//     //   console.log(authReq.headers);
//     //   console.log(authReq.headers.has('Authorization'));
//     //   console.log(req.headers.has('Authorization'));
//     // }
//     // console.log(authReq.headers);
//     // console.log(authReq.headers.has('authorization'));
//     return next.handle(authReq);
//   }
// }
