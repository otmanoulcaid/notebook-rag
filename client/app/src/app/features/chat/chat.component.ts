import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatService } from '../../services/chat.service';
import { Message } from '../../models/Message';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule]
})
export class ChatComponent {

  messages: Message[] = [
    { sender: 'bot', text: 'Hello! I am your Note Assistant. How can I help you today?' }
  ];

  newMessage = '';
  chatService = inject(ChatService);

  @ViewChild('chatWindow') private chatWindow!: ElementRef;

  sendMessage() {
    if (!this.newMessage.trim()) return;

    this.messages.push({
      sender: 'user',
      text: this.newMessage
    });

    const thinkingMessage: Message = {
      sender: 'bot',
      text: '',
      loading: true
    };

    this.messages.push(thinkingMessage);

    const question = this.newMessage;
    this.newMessage = '';

    this.chatService.send(question).subscribe({
      next: (response) => {
        thinkingMessage.loading = false;
        thinkingMessage.text = response.response;
      },
      error: () => {
        thinkingMessage.loading = false;
        thinkingMessage.text = '⚠️ Sorry, something went wrong.';
      }
    });
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  private scrollToBottom() {
    if (this.chatWindow) {
      const el = this.chatWindow.nativeElement;
      el.scrollTop = el.scrollHeight;
    }
  }
}
