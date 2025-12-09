import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';

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

  newMessage: string = '';

  sendMessage() {
    if (!this.newMessage.trim()) return;

    this.messages.push({ sender: 'user', text: this.newMessage });

    setTimeout(() => {
      this.messages.push({ sender: 'bot', text: 'This is a mock reply for your question: "' + this.newMessage + '"' });
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
