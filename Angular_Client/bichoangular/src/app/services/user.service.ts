import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginResponse } from '../models/login-response.module';
import { UserItemResponse } from '../models/user/user-item-response.module';
import { UserDetailsResponse } from '../models/user/user-details-response.module';
import { UpdateUserPermisions } from '../models/user/update-permisions.module';
import { GetUserPermissionsDTO } from '../models/user/get-permisions.module';
import { UpdateUserInfo } from '../models/user/update-user-info.module';
import { CreateUser } from '../models/user/new-user.module';



@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient) { }

  LoginResponse(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.HeadUrl}/auth/login`,
      {
        "username": `${username}`,
        "password": `${password}`,
      }
    )
  }

  getAllUsers(seacrh: string): Observable<UserItemResponse[]> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<UserItemResponse[]>(`${environment.HeadUrl}/admin/user/allusers${seacrh}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  getUserDetails(id: string): Observable<UserDetailsResponse> {
    let token = localStorage.getItem(`TOKEN`);
    return this.http.get<UserDetailsResponse>(`${environment.HeadUrl}/admin/user/${id}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })

  }

  updateRoles(id: string, roles: string[]): Observable<UserItemResponse> {
    let token = localStorage.getItem('TOKEN');
    if (!token) {
      console.error('No token found in local storage');
    }

    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.put<UserItemResponse>(
      `${environment.HeadUrl}/admin/user/update/roles/${id}`,
      { roles },
      { headers }
    );
  }
  updateUserPermissions(id: string, permissions: UpdateUserPermisions): Observable<GetUserPermissionsDTO> {
    let token = localStorage.getItem('TOKEN');
    if (!token) {
      console.error('No token found in local storage');
    }

    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.put<GetUserPermissionsDTO>(
      `${environment.HeadUrl}/admin/user/update/permissions/${id}`,
      permissions,
      { headers }
    );
  }

  updateUserInfo(id: string, edit: UpdateUserInfo): Observable<UserItemResponse> {
    let token = localStorage.getItem('TOKEN');
    if (!token) {
      console.error('No token found in local storage');
    }

    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.put<UserItemResponse>(
      `${environment.HeadUrl}/admin/user/update/${id}`,
      edit,
      { headers }
    );
  }

  deleteUser(id: string) {
    let token = localStorage.getItem('TOKEN');
    if (!token) {
      console.error('No token found in local storage');
    }
    return this.http.delete<UserItemResponse[]>(`${environment.HeadUrl}/admin/user/delete/${id}`, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  createUser(user: CreateUser): Observable<CreateUser> {
    let token = localStorage.getItem('TOKEN');
    if (!token) {
      console.error('No token found in local storage');
    }
    return this.http.post<CreateUser>(`${environment.HeadUrl}/admin/user/`,
      user,
      {
        headers: {
          accept: 'application/json',
          'Authorization': `Bearer ${token}`
        }
      }

    )
  }

  logout(): Observable<String> {
    let token = localStorage.getItem('TOKEN');
    if (!token) {
      console.error('No token found in local storage');
    }
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<String>(`${environment.HeadUrl}/userLogout`,
      {
        headers
      }

    )
  }


}


