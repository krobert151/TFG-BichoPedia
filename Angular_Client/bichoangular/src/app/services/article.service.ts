import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticleEspecieResponse } from '../models/article/articles-resoponses.module';
import { environment } from '../../environments/environment.development';
import { ArticleDetails } from '../models/article/article-details.module';
import { CreateArticle } from '../models/article/create-article.module';
import { EditArticle } from '../models/article/article-edit.module';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {


  constructor(private http: HttpClient) { }

  getArticleDetails(uuid:string):Observable<ArticleDetails>{
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<ArticleDetails>(`${environment.HeadUrl}/writer/articles/details/${uuid}`,{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

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

  editArticle(editArticle:EditArticle,id:string):Observable<ArticleEspecieResponse>{
    let token = localStorage.getItem(`TOKEN`);
    return this.http.put<ArticleEspecieResponse>(`${environment.HeadUrl}/writer/articles/edit/${id}`,
      editArticle,{
        headers:{
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      })
  }

}
