import { Routes } from '@angular/router';
// views
import { LoginRegisterComponent } from './views/login-register/login-register.component';
import { VacationListComponent } from './views/vacation-list/vacation-list.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginRegisterComponent },
    { path: 'vacations', component: VacationListComponent, canActivate: [authGuard] }
];
