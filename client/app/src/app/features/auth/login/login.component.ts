import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  router = inject(Router)
  authService = inject(AuthService)

  email = '';
  password = '';
  loading = false;

  login() {
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
      console.log('Login:', this.email, this.password);
      this.router.navigate(['/'])
      this.authService.authenticated.update(loged => !loged)
    }, 1500);
  }

  loginWithGoogle() {
    window.location.href = 'http://100.111.232.195:8080/oauth2/authorize/google';
  }

  loginWithGithub() {
    window.location.href = 'http://100.111.232.195:8080/oauth2/authorize/github';
  }
}
