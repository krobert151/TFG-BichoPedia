import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SpecieItemResponse } from '../models/specie/specie.module';
import { environment } from '../../environments/environment';
import { SpecieDetailsResponse } from '../models/specie/specie-details.module';
import { SpecieUpdate } from '../models/specie/update-specie.module';
import { CreateSpecie } from '../models/specie/create-specie.module';
import { SpecieNameResponse } from '../models/specie/species-names.module';
import { ArticleEspecieResponse } from '../models/article/articles-resoponses.module';

@Injectable({
  providedIn: 'root'
})
export class SpecieService {

  constructor(private http: HttpClient) { }

  allSpecies(search: string): Observable<SpecieItemResponse[]> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<SpecieItemResponse[]>(`${environment.HeadUrl}/user/species/allspecies${search}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }
  allSpeciesNames(): Observable<SpecieNameResponse[]> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<SpecieNameResponse[]>(`${environment.HeadUrl}/user/species/names`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }
  findById(uuid: string): Observable<SpecieDetailsResponse> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<SpecieDetailsResponse>(`${environment.HeadUrl}/user/species/speciebyid/${uuid}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }
  editSpecie(specie: SpecieUpdate): Observable<SpecieItemResponse> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.put<SpecieItemResponse>(`${environment.HeadUrl}/writer/species/`,
      specie,
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      })
  }
  createSpecie(createSpecie: CreateSpecie): Observable<SpecieItemResponse> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.post<SpecieItemResponse>(`${environment.HeadUrl}/writer/species/`,
      createSpecie,
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      })
  }
  deleteSpecie(uuid: String): Observable<any> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.delete<any>(`${environment.HeadUrl}/writer/species/${uuid}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  changeArticleVisibility(uuid:String):Observable<ArticleEspecieResponse>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<ArticleEspecieResponse>(`${environment.HeadUrl}/writer/articles/changeApprovedArticle/${uuid}`,{
      headers:{
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }

    });
  }







}
