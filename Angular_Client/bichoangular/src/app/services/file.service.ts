import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  uploadImage(image: any) {
    let token = localStorage.getItem(`TOKEN`);
    const formData = new FormData();
    formData.append("file", image);

    return this.http.post(`${environment.HeadUrl}/upload`, formData, {
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    });

  }



}
