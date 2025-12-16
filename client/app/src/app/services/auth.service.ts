import { Injectable, signal } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    authenticated = signal(false)

    logout() {
        this.authenticated.set(false)
    }
}