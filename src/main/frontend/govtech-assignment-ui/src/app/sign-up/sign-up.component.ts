import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../providers/authentication/authentication.service';
import { UserService } from '../providers/user/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.less']
})
export class SignUpComponent implements OnInit {

  signUpForm!: FormGroup;
  signUpResponse: any;
  signUpErrors: any[] = [];
  isDataLoading: boolean = false;
  errors=[];

  constructor(private fb: FormBuilder,private router: Router,private authenticationService: AuthenticationService,private userService: UserService) {
    this.checkExistingLoggedInUserData();
  }

  ngOnInit(): void {
    this.initializeSignUpForm();
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

  initializeSignUpForm() {
    this.signUpForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      userName: ['', [Validators.required]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]]
    });
  }
  
  public signUpFormError = (controlName: string, errorName: string) => {
    return this.signUpForm.controls[controlName].hasError(errorName);
  };

  signUp() {
    this.signUpErrors =[];
    this.isDataLoading = true;
    let request = this.signUpForm.value;
    this.userService.create(request).subscribe(
      (data) => {
        this.signUpResponse = data;
        this.authenticationService.storeLoggedInUserAccountData(data);
        this.getUserDetailsByAccountId(data.id);
        this.isDataLoading = false;
      },
      (error) => {
        this.isDataLoading = false;
        this.signUpErrors = error.error;
      },
      () => { }
    );
  }

  getUserDetailsByAccountId(id: string) {
    this.isDataLoading = true;
    this.userService.getById(id).subscribe(
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
