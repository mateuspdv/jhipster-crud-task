import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryFormComponent } from './components/category-form/category-form.component';

import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [
    CategoryListComponent,
    CategoryFormComponent,
  ],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    ButtonModule
  ]
})
export class CategoryModule { }
