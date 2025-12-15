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
  @ViewChild('chatWindow') private chatWindow!: ElementRef;
  chatService !:ChatService;
  messages!: Message[];
  newMessage = '';

  constructor(chatService: ChatService) {
    this.chatService = chatService;
    this.messages = [
      { sender: 'BOT', text: 'Hello! I am your Note Assistant. How can I help you today?' }
    ];
    this.chatService.getMessages().subscribe({
      next: messages => this.messages.push(...messages),
      error: err => {
        console.log(err);
        alert('something goes wrrong');
      }
    });
  }

  sendMessage() {
    if (!this.newMessage.trim()) return;

    this.messages.push({
      sender: 'USER',
      text: this.newMessage
    });

    const thinkingMessage: Message = {
      sender: 'BOT',
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
