import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EncountersItemResponse } from '../models/encounter/encounter.module';
import { environment } from '../../environments/environment.development';
import { Observable } from 'rxjs';
import { EncounterEdit } from '../models/encounter/encounter-edit.module';
import { EncounterEditResponse } from '../models/encounter/encounter-edit-response.module';
import { EncounterCreate } from '../models/encounter/encounter-create.module';

@Injectable({
  providedIn: 'root'
})
export class EncounterService {

  constructor(private http: HttpClient) { }

  allEncounters(search: string): Observable<EncountersItemResponse[]> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<EncountersItemResponse[]>(`${environment.HeadUrl}/writer/encounters/allencounters${search}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  editSpecie(specie: EncounterEdit): Observable<EncounterEditResponse> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.put<EncounterEditResponse>(`${environment.HeadUrl}/writer/encounters/`,
      specie,
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      })
  }
  createSpecie(createSpecie: EncounterCreate): Observable<EncounterCreate> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.post<EncounterCreate>(`${environment.HeadUrl}/user/encounters/find/`,
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
    return this.http.delete<any>(`${environment.HeadUrl}/writer/encounters/${uuid}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }
}
