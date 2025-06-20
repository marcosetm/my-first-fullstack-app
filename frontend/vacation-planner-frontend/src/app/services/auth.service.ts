import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly tokenKey = 'vacation_planner_user';
  
  constructor() { }

  isAuthenticated(): boolean {
    return !!sessionStorage.getItem(this.tokenKey);
  }

  login(user: any): void {
    sessionStorage.setItem(this.tokenKey, JSON.stringify(user));
  }

  logout(): void {
    sessionStorage.removeItem(this.tokenKey);
  }

  getUser(): any {
    const storedUser = sessionStorage.getItem(this.tokenKey);
    return storedUser ? JSON.parse(storedUser) : null;
  }
}
