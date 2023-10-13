import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../providers/authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  loggedInUserData: any;

  constructor(private router: Router,private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.initializeLoggedInUserData();
  }

  initializeLoggedInUserData() {
    this.loggedInUserData = localStorage.getItem(
      'loggedInUserData'
    );
    if (this.loggedInUserData) {
      this.loggedInUserData = JSON.parse(this.loggedInUserData);
    }else{
      this.router.navigate(['/']);
    }
  }

  gotoLogOut() {
    localStorage.removeItem('loggedInUserAccountData');
    localStorage.removeItem('loggedInUserData');
    this.authenticationService.storeLoggedInUserData(null);
    this.authenticationService.storeLoggedInUserAccountData(null);
    this.router.navigate(['/']);
  }
}
