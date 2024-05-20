import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SpecieItemResponse } from '../models/specie/specie.module';
import { environment } from '../../environments/environment';
import { SpecieDetailsResponse } from '../models/specie-details/specie-details.module';
import { SpecieUpdate } from '../models/update-specie/update-specie.module';
import { CreateSpecie } from '../models/species/create-specie/create-specie.module';

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







}
