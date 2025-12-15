import { Component, ElementRef, inject, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatService } from '../../services/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  standalone: true,
  imports: [FormsModule]
})
export class ChatComponent {
  messages = [
    { sender: 'bot', text: 'Hello! I am your Note Assistant. How can I help you today?' },
    { sender: 'user', text: 'Hi! Can I ask questions about my PDFs?' },
    { sender: 'bot', text: 'Absolutely! Upload a PDF and then ask me anything about it.' }
  ];
  @ViewChild('chatWindow') private chatWindow!: ElementRef;
  chatService = inject(ChatService);
  newMessage: string = '';

  sendMessage() {
    if (!this.newMessage.trim()) return;

    this.messages.push({ sender: 'user', text: this.newMessage });
    this.chatService.send(this.newMessage).subscribe({
      error: err=> console.log(err),
      next: (response) => this.messages.push({ sender: 'bot', text: response })
    })
    setTimeout(() => {
      this.messages.push({ sender: 'bot', text: 'This response is comming from the AI, just wait...' });
    }, 800);
    this.newMessage = '';
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  private scrollToBottom(): void {
    if (this.chatWindow) {
      const el = this.chatWindow.nativeElement;
      el.scrollTop = el.scrollHeight;
    }
  }
}
