import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Config, CONFIG } from '../model';
import { NewsModel } from './model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(
    private httpClient: HttpClient,
    @Inject(CONFIG) private config: Config
  ) { }

  getNews(country: string, category: string) : Observable<NewsModel> {
    return this.httpClient.get<NewsModel>(`${this.config.domainUrl}/news/${country}/${category}/`);
  }
}
