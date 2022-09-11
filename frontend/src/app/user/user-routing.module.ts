import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';

import { UserComponent } from './user/user.component';


const routes: Routes = [
  
 { path:"" ,component:NavbarComponent },
  {path:"profile/:username" ,component:UserComponent }

  
  
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }