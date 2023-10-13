import { Injectable } from '@angular/core';
import { RestClientService } from '../rest-client/rest-client.service';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private baseUrl: string;

  constructor(private restClientService: RestClientService) { 
    this.baseUrl = 'api/v1/restaurant';
  }

  submitSessionRestaurant(sessionId:string,submitSessionRestaurantRequest:any){
    return this.restClientService.post(submitSessionRestaurantRequest,this.baseUrl+"/"+sessionId);
  }

  selectSuggestedSessionRestaurant(sessionId:string){
    return this.restClientService.get(this.baseUrl+"/select/"+sessionId);
  }
  
}
