import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SessionService } from '../providers/session/session.service';

@Component({
  selector: 'app-view-session',
  templateUrl: './view-session.component.html',
  styleUrls: ['./view-session.component.less']
})
export class ViewSessionComponent implements OnInit {

  sessionId: any;
  sessionData:any;
  userDetails:any;

  constructor(private route: ActivatedRoute,
    private router: Router,private sessionService:SessionService) {
      this.sessionId = this.route.snapshot.paramMap.get('id');
      this.getSessionData();
  }

  getSessionData() {
    this.sessionService.getById(this.sessionId).subscribe(
      (data) => {
        this.sessionData=data;
      },
      (error) => {;
      },
      () => { }
    );
  }

  ngOnInit(): void {
    this.userDetails = localStorage.getItem('loggedInUserData');
    if (this.userDetails) {
      this.userDetails = JSON.parse(this.userDetails);
    }
  }

  goToSessions(){
    this.router.navigate(['home']);
  }

  editSession(){
    this.router.navigate([
      'edit-session',
      this.sessionId
    ]);
  }

  deleteSessionConfirm(){
    if(confirm("Are you sure to delete the session : "+this.sessionData.title)) {
      this.deleteSession();
    }
  }

  deleteSession(){
    this.sessionService.delete(this.sessionId).subscribe(data => {
      alert("Session has been deleted successfully : "+this.sessionData.title);
      this.goToSessions();
    },error => {
    },
    () => {
    });
  }

  endSessionConfirm(){
    if(confirm("Are you sure to end the session : "+this.sessionData.title)) {
      this.endSession();
    }
  }

  
  endSession(){
    let request = {
      active:false
    };
    this.sessionService.update(this.sessionId,request).subscribe(data => {
      alert("Session has been closed successfully : "+this.sessionData.title);
      this.getSessionData();
    },error => {
    },
    () => {
    });
  }

}
