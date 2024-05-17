import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  uploadImage(image: File) {
    this.http.post<File>(`${environment.HeadUrl}/upload`,
      image,
      {
        headers: {
          accept: '*/*'
        }
      });
  }



}
