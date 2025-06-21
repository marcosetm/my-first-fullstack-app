import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';
// views
import { LoginRegisterComponent } from './views/login-register/login-register.component';
import { VacationListComponent } from './views/vacation-list/vacation-list.component';
import { VacationDetailsComponent } from './views/vacation-details/vacation-details.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginRegisterComponent },
    { path: 'vacations', component: VacationListComponent, canActivate: [authGuard] },
    { path: 'vacations/new', component: VacationDetailsComponent, canActivate: [authGuard] },
    { path: 'vacations/:id', component: VacationDetailsComponent, canActivate: [authGuard] }
];
