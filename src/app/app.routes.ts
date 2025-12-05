import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CareersComponent } from './pages/careers/careers.component';
import { ContactComponent } from './pages/contact/contact.component';

export const routes: Routes = [
    {path:'', component:HomeComponent},
    {path:'careers', component: CareersComponent},
    {path:'contact', component: ContactComponent}
];
