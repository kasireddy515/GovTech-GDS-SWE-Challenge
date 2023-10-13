import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RestaurantService } from '../providers/restaurant/restaurant.service';

@Component({
  selector: 'app-suggest-session-restaurant',
  templateUrl: './suggest-session-restaurant.component.html',
  styleUrls: ['./suggest-session-restaurant.component.less']
})
export class SuggestSessionRestaurantComponent implements OnInit {

  selectedSessionData: any;
  submitRestaurantSessionForm!: FormGroup;
  submitRestaurantErrors: any[] = [];
  errors: any[] = [];

  constructor(private dialogRef: MatDialogRef<SuggestSessionRestaurantComponent>,
    @Inject(MAT_DIALOG_DATA) data: any,
    private fb: FormBuilder,private restaurantService:RestaurantService) { 
      this.selectedSessionData = data.selectedSessionData;
    }

  ngOnInit(): void {
    this.initializeSubmitRestaurantSessionForm();
  }

  initializeSubmitRestaurantSessionForm() {
    this.submitRestaurantSessionForm = this.fb.group({
      title: ['', [Validators.required]],
      location: ['', [Validators.required]]
    });
  }

  public submitRestaurantSessionFormError = (controlName: string, errorName: string) => {
    return this.submitRestaurantSessionForm.controls[controlName].hasError(errorName);
  };

  close() {
    this.dialogRef.close(0);
  }

  submitSessionRestaurant() {
    let request = this.submitRestaurantSessionForm.value;
    this.restaurantService
      .submitSessionRestaurant(this.selectedSessionData.id,request)
      .subscribe(
        (data) => {
          this.dialogRef.close(data);
        },
        (error) => {
          this.errors = error.error;
        },
        () => {}
      );
  }
}
