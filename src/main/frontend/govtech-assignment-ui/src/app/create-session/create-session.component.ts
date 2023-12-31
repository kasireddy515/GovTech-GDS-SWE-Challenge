import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../providers/user/user.service';
import { Router } from '@angular/router';
import { SessionService } from '../providers/session/session.service';
import { UsernotificationService } from '../providers/usernotification/usernotification.service';

@Component({
  selector: 'app-create-session',
  templateUrl: './create-session.component.html',
  styleUrls: ['./create-session.component.less']
})
export class CreateSessionComponent implements OnInit {

  createSessionForm!: FormGroup;
  createSessionErrors: any[] = [];
  dataSource=new MatTableDataSource<any>();
  displayedColumns: string[] = ['sNo', 'firstName','lastName','action'];
  pageLimit:number=6;
  pageOffset:number=0;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  totalRows = 0;
  pageSizeOptions: number[] = [5, 20, 50,100];
  searchForm!: FormGroup;
  sortBy:string='createdOn';
  sortOrder:string='desc';
  isDataLoading:boolean=false;
  selectedUsers:any[]=[];
  createdSessionId:any;

  constructor(private userNotificationService:UsernotificationService,private sessionService:SessionService,private router: Router,private fb: FormBuilder,private userService:UserService) {
    this.getUsers();
   }

  ngOnInit(): void {
    this.initializeSearchForm();
    this.initializeCreateSessionForm();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
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

  getUsers(){
    this.isDataLoading=true;
    this.userService.find(this.pageOffset,this.pageLimit,this.searchForm?this.searchForm.value.searchText:null,this.sortBy,this.sortOrder).subscribe(data => {
      this.isDataLoading=false;
      this.dataSource.data=data.users;
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
      this.isDataLoading=false;
    },
    () => {
    });
  }

  initializeCreateSessionForm() {
    this.createSessionForm = this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]]
    });
  }

  public createSessionFormError = (controlName: string, errorName: string) => {
    return this.createSessionForm.controls[controlName].hasError(errorName);
  };

  pageChanged(event: PageEvent) {
    this.pageLimit = event.pageSize;
    this.pageOffset = event.pageIndex;
    this.getUsers();
  }

  selectUser(selectedUser:any){
    if(!selectedUser.selected){
      this.selectedUsers.push(selectedUser.id);
    }else{
      this.selectedUsers = this.selectedUsers.filter(u => u!= selectedUser.id);
    }
    selectedUser.selected=!selectedUser.selected;
  }

  goToSessions(){
    this.router.navigate(['home']);
  }

  createSession(){
    this.createdSessionId=null;
    this.createSessionErrors =[];
    this.isDataLoading = true;
    let request = this.createSessionForm.value;
    request.userIds=this.selectedUsers;
    this.sessionService.create(request).subscribe(
      (data) => {
        this.isDataLoading = false;
        this.createdSessionId=data.id;
        this.selectedUsers=[];
        this.createSessionForm.reset();
        if(this.dataSource.data && this.dataSource.data.length>0){
          this.dataSource.data.forEach(e => {
            e.selected=false;
          });
        }
        if(request.userIds && request.userIds.length>0){
          this.sendNotificationToSessionInvitees(request);
        }
      },
      (error) => {
        this.isDataLoading = false;
        this.createSessionErrors = error.error;
      },
      () => { }
    );
  }
  sendNotificationToSessionInvitees(request: any) {
    let userNotificationRequest = {
      userIds:request.userIds,
      message:"Session Invite"
    };
    this.userNotificationService.create(this.createdSessionId,userNotificationRequest).subscribe(
      (data) => {
        this.isDataLoading = false;
      },
      (error) => {
        this.isDataLoading = false;
        this.createSessionErrors = error.error;
      },
      () => { }
    );
  }

  search(){
    let searchText=this.searchForm.value.searchText;
    if((searchText && searchText.length>=3) || (!searchText || searchText=='')){
      this.paginator.pageIndex = 0;
      this.pageOffset=0;
      this.getUsers();
    }
  }

  viewSession(){
    this.router.navigate([
      'view-session',
      this.createdSessionId
    ]);
  }
}
