import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginResponse } from '../models/login-response.module';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  LoginResponse(username: string, password:string):Observable<LoginResponse>{
    return this.http.post<LoginResponse>(`${environment.HeadUrl}/auth/login`,
    {
    "username":`${username}`,
    "password":`${password}`,
    }
    )
  }

}
