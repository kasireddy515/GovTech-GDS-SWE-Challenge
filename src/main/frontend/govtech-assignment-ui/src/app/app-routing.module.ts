import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { CreateSessionComponent } from './create-session/create-session.component';
import { ViewSessionComponent } from './view-session/view-session.component';
import { EditSessionComponent } from './edit-session/edit-session.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'create-session', component: CreateSessionComponent },
  { path: 'view-session/:id', component: ViewSessionComponent },
  { path: 'edit-session/:id', component: EditSessionComponent },
  { path: 'sign-in', component: SignInComponent },
  { path: 'sign-up', component: SignUpComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
