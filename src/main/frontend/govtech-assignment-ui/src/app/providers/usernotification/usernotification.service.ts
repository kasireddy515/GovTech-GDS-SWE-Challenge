import { Injectable } from '@angular/core';
import { RestClientService } from '../rest-client/rest-client.service';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsernotificationService {

  private baseUrl: string;
  private serverURL: string;
  

  constructor(private restClientService: RestClientService) { 
    this.baseUrl = 'api/v1/user-notification';
    this.serverURL = 'http://localhost:8080/';
  }
  
  create(sessionId:string,createUserNotificationRequest:any){
    return this.restClientService.post(createUserNotificationRequest,this.baseUrl+"/"+sessionId);
  }

  update(userNotificationId:string,updateUserNotificationRequest:any){
    return this.restClientService.put(updateUserNotificationRequest,this.baseUrl+"/"+userNotificationId);
  }

  get(){
    return this.restClientService.get(this.baseUrl);
  }

  subscribe(): Subject<any> {
    let eventSource = new EventSource(this.serverURL+this.baseUrl+"/subscribe");
    let subscription = new Subject();
    eventSource.addEventListener("message", event=> {
        subscription.next(event);
    });
    return subscription;
 }

}
