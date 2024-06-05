import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticleEspecieResponse } from '../models/articles-resoponses/articles-resoponses.module';
import { environment } from '../../environments/environment.development';
import { ArticleDetails } from '../models/article-details/article-details.module';
import { CreateArticle } from '../models/create-article/create-article.module';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {


  constructor(private http: HttpClient) { }


  allArticles(): Observable<ArticleEspecieResponse[]> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<ArticleEspecieResponse[]>(`${environment.HeadUrl}/writer/articles/allArticles`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }


  createArticle(newArticle: CreateArticle):Observable<ArticleEspecieResponse> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.post<ArticleEspecieResponse>(`${environment.HeadUrl}/writer/articles/create`,
    newArticle,
    {
      headers:{
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }
}
