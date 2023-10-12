import { Injectable } from '@angular/core';
import { RestClientService } from '../rest-client/rest-client.service';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl: string;

  private currentUserAccountDataSubject:BehaviorSubject<any>;
  public currentUserAccountData:Observable<any>;

  private currentUserDataSubject:BehaviorSubject<any>;
  public currentUserData:Observable<any>;

  constructor(private restClientService: RestClientService) { 

    this.baseUrl = 'api/v1/account';

    let loggedInUserAccountData=localStorage.getItem("loggedInUserAccountData");
    this.currentUserAccountDataSubject=new BehaviorSubject<any>(loggedInUserAccountData);
    this.currentUserAccountData=this.currentUserAccountDataSubject.asObservable();

    let loggedInUserData=localStorage.getItem("loggedInUserData");
    this.currentUserDataSubject=new BehaviorSubject<any>(loggedInUserData);
    this.currentUserData=this.currentUserDataSubject.asObservable();

  }


  signInToAccount(createAccountRequest:any){
    return this.restClientService.post(createAccountRequest,this.baseUrl+'/sign-in');
  }

  public get currentLoggedUserAccountData():any {
    return this.currentUserAccountDataSubject.value;
  }

  storeLoggedInUserAccountData(loggedInUserAccountData:any) {
    localStorage.setItem("loggedInUserAccountData", JSON.stringify(loggedInUserAccountData));
    this.currentUserAccountDataSubject.next(loggedInUserAccountData);
  }

  public get currentLoggedUserData():any {
    return this.currentUserDataSubject.value;
  }

  storeLoggedInUserData(loggedInUserData:any) {
    localStorage.setItem("loggedInUserData", JSON.stringify(loggedInUserData));
    this.currentUserDataSubject.next(loggedInUserData);
  }

}
