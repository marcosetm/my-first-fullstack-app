import { Routes } from '@angular/router';
// views
import { VacationListComponent } from './views/vacation-list/vacation-list.component';

export const routes: Routes = [
    { path: '/vacations', component: VacationListComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' }
];
