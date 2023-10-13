import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SessionService } from '../providers/session/session.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { SuggestSessionRestaurantComponent } from '../suggest-session-restaurant/suggest-session-restaurant.component';
import { RestaurantService } from '../providers/restaurant/restaurant.service';

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
    private router: Router,private sessionService:SessionService,
    public dialog: MatDialog,private restaurantService:RestaurantService) {
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
    if(confirm("Are you sure to end the session and select a random restaurant from the submitted suggestions for the session : <b>"+this.sessionData.title+"</b>")) {
      this.endSession();
    }
  }

  
  endSession(){
    let request = {
      active:false
    };
    this.sessionService.update(this.sessionId,request).subscribe(data => {
      alert("Session has been closed successfully : "+this.sessionData.title);
      if(this.sessionData.submittedRestaurants && this.sessionData.submittedRestaurants.length>0){
        this.selectSuggestedRestaurant();
      }else{
        this.getSessionData();
      }
    },error => {
    },
    () => {
    });
  }

  suggestRestaurant(){

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.minWidth = 400;
    dialogConfig.maxWidth = 400;

    dialogConfig.data = {
      selectedSessionData: this.sessionData,
    };

    const dialogRef = this.dialog.open(SuggestSessionRestaurantComponent, dialogConfig);

    dialogRef
      .afterClosed()
      .subscribe((data) => this.readSuggestRestaurantDialogResponse(data));
  }

  readSuggestRestaurantDialogResponse(data: any) {
    if (data != 0) {
      alert("Restaurant suggested successfully to this session");
      this.getSessionData();
    }
  }

  selectSuggestedRestaurant(){
    this.restaurantService.selectSuggestedSessionRestaurant(this.sessionId).subscribe(data => {
      alert("A random restaurant has been selected successfully : "+data.title);
      this.getSessionData();
    },error => {
    },
    () => {
    });
  }
  
}
