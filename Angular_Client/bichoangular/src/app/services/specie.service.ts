import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SpecieItemResponse } from '../models/specie/specie.module';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SpecieService {

  constructor(private http:HttpClient) { }

  allSpecies(search:string):Observable<SpecieItemResponse[]>{
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<SpecieItemResponse[]>(`${environment.HeadUrl}/species/allspecies`,{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

}
