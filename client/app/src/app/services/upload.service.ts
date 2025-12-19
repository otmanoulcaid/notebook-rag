import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { finalize } from 'rxjs';
import { HttpResponse } from '../models/HttpResponse';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private http = inject(HttpClient);
  wait = signal(false);

  upload(files: File[]) {
    const formData = new FormData();

    files.forEach(file => {
      formData.append('files', file);
    });

    this.wait.set(true);

    return this.http
      .post<HttpResponse>('http://100.111.232.195:8080/api/v1/upload', formData)
      .pipe(
        finalize(() => this.wait.set(false))
      );
  }
}
