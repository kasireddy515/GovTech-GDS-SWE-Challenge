import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RestClientService {
  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = window.location.origin + '/';
    this.baseUrl = 'http://localhost:8080/';
  }

  public get(serviceURL: any) {
    return this.http.get<any>(this.baseUrl + serviceURL);
  }

  public post(request: any, serviceURL: any) {
    return this.http.post<any>(this.baseUrl + serviceURL, request);
  }

  public put(request: any, serviceURL: any) {
    return this.http.put<any>(this.baseUrl + serviceURL, request);
  }
  
  public delete(serviceURL: any) {
    return this.http.delete<any>(this.baseUrl + serviceURL);
  }
}
