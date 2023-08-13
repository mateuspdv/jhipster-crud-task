import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model';
import { Page } from 'app/shared/models/page.model';
import { ApplicationConfigService } from 'app/core/config/application-config.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/categories');

  constructor(private httpClient: HttpClient,
              private applicationConfigService: ApplicationConfigService) { }

  getCategoriesByLogin(login: string, page: number, size: number): Observable<Page<Category>> {
    const params = { page: page, size: size };
    return this.httpClient.get<Page<Category>>(`${this.resourceUrl}/user/${login}`, { params });
  }

}
