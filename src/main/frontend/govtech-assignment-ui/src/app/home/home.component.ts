import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../providers/user/user.service';
import { SessionService } from '../providers/session/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.less']
})
export class HomeComponent implements OnInit {

  dataSource=new MatTableDataSource<any>();
  displayedColumns: string[] = ['sNo', 'title','noOfUsersInvited','noOfSubmittedRestaurants','selectedRestaurantName','active','createdByUserName','createdOn','action'];
  pageLimit:number=8;
  pageOffset:number=0;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  totalRows = 0;
  pageSizeOptions: number[] = [8, 20, 50,100];
  searchForm!: FormGroup;
  sortBy:string='createdOn';
  sortOrder:string='desc';
  userDetails:any;
  
  constructor(private fb: FormBuilder,private sessionService:SessionService,private router: Router) { 
    this.getSessions();
  }

  ngOnInit(): void {
    this.userDetails = localStorage.getItem('loggedInUserData');
    if (this.userDetails) {
      this.userDetails = JSON.parse(this.userDetails);
    }
    this.initializeSearchForm();
  }

  initializeSearchForm(){
    this.searchForm = this.fb.group({
      searchText: ['', [Validators.minLength(3)]]
    });
  }

  public searchFormError = (
    controlName: string,
    errorName: string
  ) => {
    return this.searchForm.controls[controlName].hasError(
      errorName
    );
  };

  pageChanged(event: PageEvent) {
    this.pageLimit = event.pageSize;
    this.pageOffset = event.pageIndex;
    this.getSessions();
  }

  getSessions(){
    this.sessionService.find(this.pageOffset,this.pageLimit,this.searchForm?this.searchForm.value.searchText:null,this.sortBy,this.sortOrder).subscribe(data => {
      if(data.sessions && data.sessions.length>0){
        data.sessions.forEach((eachSession: any) => {
          eachSession.createdByUserName=eachSession.createdByUser.firstName+" "+eachSession.createdByUser.lastName;
        });
      }
      this.dataSource.data=data.sessions;
      this.totalRows=data.total;
      setTimeout(() => {
        this.paginator.pageIndex = this.pageOffset;
        this.paginator.length = this.totalRows;
      });
    },error => {
      this.dataSource.data=[];
      setTimeout(() => {
        this.paginator.pageIndex = this.pageOffset;
        this.paginator.length = 0;
      });
    },
    () => {
    });
  }

  joinSession(selectedSession:any){
    this.router.navigate([
      'view-session',
      selectedSession.id
    ]);
  }

  viewSession(selectedSession:any){
    this.router.navigate([
      'view-session',
      selectedSession.id
    ]);
  }

  search(){
    let searchText=this.searchForm.value.searchText;
    if((searchText && searchText.length>=3) || (!searchText || searchText=='')){
      this.paginator.pageIndex = 0;
      this.pageOffset=0;
      this.getSessions();
    }
  }

  createSession(){
    this.router.navigate(['create-session']);
  }

  editSession(selectedSession:any){
    this.router.navigate([
      'edit-session',
      selectedSession.id
    ]);
  }

  deleteSessionConfirm(selectedSession:any){
    if(confirm("Are you sure to delete the session : "+selectedSession.title)) {
      this.deleteSession(selectedSession);
    }
  }

  deleteSession(selectedSession:any){
    this.sessionService.delete(selectedSession.id).subscribe(data => {
      alert("Session has been deleted successfully : "+selectedSession.title);
      this.getSessions();
    },error => {
    },
    () => {
    });
  }

  endSessionConfirm(selectedSession:any){
    if(confirm("Are you sure to end the session : "+selectedSession.title)) {
      this.endSession(selectedSession);
    }
  }

  
  endSession(selectedSession:any){
    let request = {
      active:false
    };
    this.sessionService.update(selectedSession.id,request).subscribe(data => {
      alert("Session has been closed successfully : "+selectedSession.title);
      this.getSessions();
    },error => {
    },
    () => {
    });
  }

}
