import { Injectable } from '@angular/core';
import { RestClientService } from '../rest-client/rest-client.service';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private baseUrl: string;

  constructor(private restClientService: RestClientService) { 
    this.baseUrl = 'api/v1/session';
  }

  create(createSessionRequest:any){
    return this.restClientService.post(createSessionRequest,this.baseUrl);
  }

  find(pageOffset:number,pageLimit:number,searchText:string,sortBy:string,sortOrder:string) {
    searchText=searchText=="" || !searchText?"ALL":searchText;
    return this.restClientService.get(this.baseUrl+"/"+pageOffset+"/"+pageLimit+"/"+searchText+"/"+sortBy+"/"+sortOrder);
  }
  
}
