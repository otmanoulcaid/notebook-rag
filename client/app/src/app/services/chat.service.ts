import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { finalize } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  httpClient = inject(HttpClient);
  wait = signal(false);

  send(prompt: string) {
    this.wait.set(true)
    return this.httpClient.post<string>('http://localhost:8080/api/v1/chat', {
      prompt
    }).pipe(finalize(() => {
      this.wait.set(false);
    }));
  }
}
