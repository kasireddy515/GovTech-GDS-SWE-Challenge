import { NgModule } from '@angular/core';
import { BrowserModule, BrowserTransferStateModule, Meta } from '@angular/platform-browser';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MatBadgeModule } from '@angular/material/badge';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { CommonModule } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';
import { HeaderComponent } from './header/header.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import {MatButtonModule} from '@angular/material/button';
import { HomeComponent } from './home/home.component';
import { CreateSessionComponent } from './create-session/create-session.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AUTH_INTERCEPTOR_PROVIDERS } from './providers/auth-interceptor/auth-interceptor.service';
import { ViewSessionComponent } from './view-session/view-session.component';
import { EditSessionComponent } from './edit-session/edit-session.component';
import { SuggestSessionRestaurantComponent } from './suggest-session-restaurant/suggest-session-restaurant.component';
import { WelcomeComponent } from './welcome/welcome.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignInComponent,
    SignUpComponent,
    HomeComponent,
    CreateSessionComponent,
    ViewSessionComponent,
    EditSessionComponent,
    SuggestSessionRestaurantComponent,
    WelcomeComponent
  ],
  entryComponents:[
    SuggestSessionRestaurantComponent
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatCardModule,
    HttpClientModule,
    MatMenuModule,
    MatTableModule,
    MatPaginatorModule,
    MatStepperModule,
    MatBadgeModule,
    MatSnackBarModule,
    MatTooltipModule,
    CommonModule,
    MatDialogModule
  ],
  providers: [Meta, BrowserTransferStateModule, HttpClient,AUTH_INTERCEPTOR_PROVIDERS],
  bootstrap: [AppComponent]
})
export class AppModule { }
