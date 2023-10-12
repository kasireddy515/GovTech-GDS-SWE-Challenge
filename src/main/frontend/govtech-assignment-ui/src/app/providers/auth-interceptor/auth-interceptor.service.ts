import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HTTP_INTERCEPTORS, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

const TOKEN_HEADER_KEY = 'Authorization';   
@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    let loggedInUserAccountData:any=localStorage.getItem("loggedInUserAccountData");
    loggedInUserAccountData=loggedInUserAccountData?JSON.parse(loggedInUserAccountData):null;
    let token=loggedInUserAccountData?loggedInUserAccountData["accessToken"]:"";
    authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    return next.handle(authReq).pipe(
      catchError((error) => {
        if (
          error instanceof HttpErrorResponse &&
          !req.url.includes('login') &&
          error.status === 401
        ) {
          return this.handle401Error(req, next);
        }
        return throwError(() => error);
      })
    )
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    let loggedInUserAccountData:any=localStorage.getItem("loggedInUserAccountData");
    if (loggedInUserAccountData) {
      
    }
    return next.handle(request);
  }
}

export const AUTH_INTERCEPTOR_PROVIDERS = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }
];
