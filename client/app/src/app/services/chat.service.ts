import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { finalize } from 'rxjs';
import { HttpResponse } from '../models/HttpResponse';
import { Message } from '../models/Message';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  httpClient = inject(HttpClient);
  wait = signal(false);

  send(prompt: string) {
    this.wait.set(true)
    return this.httpClient.post<HttpResponse>('http://localhost:8080/api/v1/chat', {
      prompt
    }).pipe(finalize(() => {
      this.wait.set(false);
    }));
  }

  getMessages() {
    return this.httpClient.get<Message[]>('http://localhost:8080/api/v1/messages')
  }
}
