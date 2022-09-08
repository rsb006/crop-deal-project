import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CropsComponent } from './crops/crops.component';
import { CropsRoutingModule } from './crops-routing.module';



@NgModule({
  declarations: [
    CropsComponent
  ],
  imports: [
    CommonModule,
    CropsRoutingModule
  ]
})
export class CropsModule { }
