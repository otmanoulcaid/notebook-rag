import { Component, inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  private authService = inject(AuthService)

  get authenticated() {
    return this.authService.authenticated()
  }

  logout() {
    this.authService.logout();
  }
}
