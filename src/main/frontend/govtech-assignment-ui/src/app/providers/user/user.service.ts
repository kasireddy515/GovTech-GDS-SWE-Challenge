import { Injectable } from '@angular/core';
import { RestClientService } from '../rest-client/rest-client.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string;

  constructor(private restClientService: RestClientService) { 
    this.baseUrl = 'api/v1/user';
  }

  getByAccountId(accountId:string) {
    return this.restClientService.get(this.baseUrl+"/account/"+accountId);
  }

  getById(id:string) {
    return this.restClientService.get(this.baseUrl+"/"+id);
  }
  
  create(createUserRequest:any){
    return this.restClientService.post(createUserRequest,this.baseUrl);
  }

  find(pageOffset:number,pageLimit:number,searchText:string,sortBy:string,sortOrder:string) {
    searchText=searchText=="" || !searchText?"ALL":searchText;
    return this.restClientService.get(this.baseUrl+"/"+pageOffset+"/"+pageLimit+"/"+searchText+"/"+sortBy+"/"+sortOrder);
  }

}
