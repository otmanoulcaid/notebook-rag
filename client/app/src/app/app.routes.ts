import { Routes } from '@angular/router';
import { HomeComponent } from './features/Home/home.component';
import { ChatComponent } from './features/chat/chat.component';
import { UploadComponent } from './features/upload/upload.component';
import { LoginComponent } from './features/auth/login/login.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        title: 'Home'
    },
    {
        path: 'upload',
        component: UploadComponent,
        title: 'Upload your files'
    },
    {
        path: 'assistant',
        component: ChatComponent,
        title: 'Assistant'
    },
    {
        path: 'login',
        component: LoginComponent,
        title: 'login'
    },
];
