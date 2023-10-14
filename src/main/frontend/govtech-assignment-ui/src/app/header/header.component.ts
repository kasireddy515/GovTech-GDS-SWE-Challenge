import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../providers/authentication/authentication.service';
import { UsernotificationService } from '../providers/usernotification/usernotification.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  loggedInUserData: any;
  userNotifications:any[]=[];

  constructor(private router: Router,private authenticationService: AuthenticationService,private usernotificationService: UsernotificationService,private userNotificationService:UsernotificationService) { }

  ngOnInit(): void {
    this.initializeLoggedInUserData();
    this.subscribeToNotifications();
    this.getUserNotifications();
  }

  subscribeToNotifications(){
    this.usernotificationService.subscribe()
            .subscribe({
                next: (event) => {
                  let allUserNotifications=JSON.parse(event.data);
                  this.filterUserNotifications(allUserNotifications);
                }
    });
  }

  filterUserNotifications(allUserNotifications: any) {
    if(allUserNotifications!=null && allUserNotifications.length>0 && this.loggedInUserData!=null){
      this.userNotifications= allUserNotifications.filter((n: any) => n.receiver.id==this.loggedInUserData.id);
    }
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

  getUserNotifications() {
    this.userNotificationService.get().subscribe(
      (data) => {
        this.userNotifications=data;
      },
      (error) => {
      },
      () => { }
    );
  }

  viewSession(selectedUserNotification:any){
    this.router.navigate([
      'view-session',
      selectedUserNotification.session.id
    ]);
  }


  readUserNotification(selectedUserNotification:any) {
    let request={
      read:true
    };
    this.userNotificationService.update(selectedUserNotification.id,request).subscribe(
      (data) => {
        this.viewSession(selectedUserNotification);
      },
      (error) => {
      },
      () => { }
    );
  }

}
