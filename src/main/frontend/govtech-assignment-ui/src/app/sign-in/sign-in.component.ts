import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../providers/authentication/authentication.service';
import { UserService } from '../providers/user/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.less']
})
export class SignInComponent implements OnInit {

  signInForm!: FormGroup;
  signInResponse: any;
  signInErrors: any[] = [];
  isDataLoading: boolean = false;
  errors:any[]=[];

  constructor(private fb: FormBuilder,private router: Router,private authenticationService: AuthenticationService,private userService: UserService) {
    this.checkExistingLoggedInUserData();
  }

  ngOnInit(): void {
    this.initializeSignInForm();
  }

  checkExistingLoggedInUserData() {
    let userDetails = localStorage.getItem('loggedInUserData');
    if (userDetails) {
      userDetails = JSON.parse(userDetails);
    }
    if (userDetails) {
      this.router.navigate(['home']);
    } else {
      localStorage.removeItem('loggedInUserAccountData');
      localStorage.removeItem('loggedInUserData');
    }
  }

  initializeSignInForm() {
    this.signInForm = this.fb.group({
      userName: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }
  
  public signInFormError = (controlName: string, errorName: string) => {
    return this.signInForm.controls[controlName].hasError(errorName);
  };
  
  signIn() {
    this.signInErrors =[];
    this.isDataLoading = true;
    let request = this.signInForm.value;
    this.authenticationService.signInToAccount(request).subscribe(
      (data) => {
        this.signInResponse = data;
        this.authenticationService.storeLoggedInUserAccountData(data);
        this.getUserDetailsByAccountId(data.id);
        this.isDataLoading = false;
      },
      (error) => {
        this.isDataLoading = false;
        this.signInErrors = error.error;
      },
      () => { }
    );
  }

  getUserDetailsByAccountId(accountId: string) {
    this.isDataLoading = true;
    this.userService.getByAccountId(accountId).subscribe(
      (data) => {
        this.isDataLoading = false;
        this.navigateToUserHomePage(data);
      },
      (error) => {
        this.isDataLoading = false;
        this.errors = error.error;
      },
      () => { }
    );
  }

  navigateToUserHomePage(userData: any) {
    this.authenticationService.storeLoggedInUserData(userData);
    this.router.navigate(['home']);
  }

}
