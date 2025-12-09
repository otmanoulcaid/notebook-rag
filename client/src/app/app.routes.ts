import { Routes } from '@angular/router';
import { HomeComponent } from './Home/home.component';
import { ChatComponent } from './chat/chat.component';
import { UploadComponent } from './upload/upload.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'upload',
        component: UploadComponent
    },
    {
        path: 'assistant',
        component: ChatComponent
    },
];
