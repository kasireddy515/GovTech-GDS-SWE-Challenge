import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SessionService } from '../providers/session/session.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../providers/user/user.service';

@Component({
  selector: 'app-edit-session',
  templateUrl: './edit-session.component.html',
  styleUrls: ['./edit-session.component.less']
})
export class EditSessionComponent implements OnInit {

  sessionId: any;
  sessionData:any;
  userDetails:any;

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

  updateSessionForm!: FormGroup;
  updateSessionErrors: any[] = [];
  updatedSessionId:any;

  constructor(private route: ActivatedRoute,
    private router: Router,private sessionService:SessionService,private fb: FormBuilder,private userService:UserService) {
      this.sessionId = this.route.snapshot.paramMap.get('id');
      this.getSessionData();
  }

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
      this.selectInvitedUsers();
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

  getSessionData() {
    this.sessionService.getById(this.sessionId).subscribe(
      (data) => {
        this.sessionData=data;
        this.getUsers();
        this.initializeUpdateSessionForm();
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
    this.initializeSearchForm();
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
      this.sessionId
    ]);
  }

  goToSessions(){
    this.router.navigate(['home']);
  }

  initializeUpdateSessionForm() {
    this.updateSessionForm = this.fb.group({
      title: [this.sessionData.title, [Validators.required]],
      description: [this.sessionData.description, [Validators.required]]
    });
  }

  public updateSessionFormError = (controlName: string, errorName: string) => {
    return this.updateSessionForm.controls[controlName].hasError(errorName);
  };

  updateSession(){
    this.updatedSessionId=null;
    this.updateSessionErrors =[];
    this.isDataLoading = true;
    let request = this.updateSessionForm.value;
    this.sessionService.update(this.sessionId,request).subscribe(
      (data) => {
        this.isDataLoading = false;
        this.updatedSessionId=data.id;
        if(this.selectedUsers.length>0){
          this.updateSessionInvitees();
        }
      },
      (error) => {
        this.isDataLoading = false;
        this.updateSessionErrors = error.error;
      },
      () => { }
    );
  }

  updateSessionInvitees(){
    let request = {
      userIds:this.selectedUsers
    };
    this.sessionService.inviteUsers(this.sessionId,request).subscribe(
      (data) => {
        this.isDataLoading = false;
      },
      (error) => {
        this.isDataLoading = false;
      },
      () => { }
    );
  }

  selectInvitedUsers(){
    if(this.dataSource.data && this.dataSource.data.length>0){
      this.dataSource.data.forEach(e => {
        if(this.sessionData.usersInvited && this.sessionData.usersInvited.length>0){
          this.sessionData.usersInvited.forEach((u: any) => {
            if(e.id==u.id){
              e.selected=true;
              this.selectedUsers.push(e.id);
            }
          });
        }
      });
    }
  }
}
