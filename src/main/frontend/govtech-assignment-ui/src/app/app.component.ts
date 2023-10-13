import { Component } from '@angular/core';
import { AuthenticationService } from './providers/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent {
  title = 'govtech-assignment-ui';
  loggedInUserAccountData: any;

  constructor(private router: Router,private authenticationService: AuthenticationService){
      
  }

  public ngOnInit(): void {
    this.loggedInUserAccountData = null;
    this.initializeLoggedInUserData();
    this.subscribeToCurrentUserAccountData();
  }

  initializeLoggedInUserData() {
    this.loggedInUserAccountData = localStorage.getItem(
      'loggedInUserAccountData'
    );
    if (this.loggedInUserAccountData) {
      this.loggedInUserAccountData = JSON.parse(this.loggedInUserAccountData);
    }else{
      this.router.navigate(['/']);
    }
  }

  subscribeToCurrentUserAccountData() {
    this.authenticationService.currentUserAccountData.subscribe(
      (result: any) => {
        if (result != null) {
          if (typeof result === 'string') {
            this.loggedInUserAccountData = JSON.parse(result);
          } else {
            this.loggedInUserAccountData = result;
          }
        } else {
          this.loggedInUserAccountData = null;
        }
      }
    );
  }
}
