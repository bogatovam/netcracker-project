import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";

@Injectable()
export class HttpClient {
  constructor(private http: Http, private token: TokenStorageService) {
  }

  createAuthorizationHeader(headers: Headers) {
    console.log("I get token");

    const token = this.token.getToken();
    console.log(token);
    if (token != null) {
      headers.append('Authorization', 'Bearer ' + token);
    }
  }

  get(url, customHeaders = null) {

    let headers = new Headers();
    if (customHeaders != null) {
      customHeaders.headers.forEach((value, header) => {
        headers.append(value.toString(), header.toString());
      });
    }
    console.log("I do get!");

    this.createAuthorizationHeader(headers);
    headers.forEach((value, header) => {
      console.log(header + " " + value);
    });
    return this.http.get(url, {
      headers: headers
    });
  }

  post(url, data, customHeaders = null) {
    let headers = new Headers();
    if (customHeaders != null)
      customHeaders.headers.forEach((value, header) => {
        headers.append(value.toString(), header.toString());
      });

    this.createAuthorizationHeader(headers);
    headers.forEach((value, header) => {
      console.log(header + " " + value);
    });
    return this.http.post(url, data, {
      headers: headers
    });
  }

}
