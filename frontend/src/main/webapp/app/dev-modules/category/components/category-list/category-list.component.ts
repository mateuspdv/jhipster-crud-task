import { Component, OnInit } from '@angular/core';
import { Category } from '../../models/category.model';
import { CategoryService } from '../../services/category.service';
import { Page } from 'app/shared/models/page.model';

@Component({
  selector: 'jhi-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit{

  categories: Category[] = [];

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.getCategoriesByLogin();
  }

  getCategoriesByLogin(): void {
    this.categoryService.getCategoriesByLogin('admin', 0, 5)
      .subscribe({
        next: (page: Page<Category>) => {
          this.categories = page.content;
        },
        error: (error) => {
          console.log(error);
        }
    });
  }

}
