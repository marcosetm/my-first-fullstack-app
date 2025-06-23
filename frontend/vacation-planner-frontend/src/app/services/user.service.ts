import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../models/loginRequest.model';
import { UserResponse } from '../models/userResponse.model';
import { RegisterRequest } from '../models/registerRequest.model';
import { environment } from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loginUrl = `${environment.apiUrl}/users/login`;
  private registerUrl = `${environment.apiUrl}/users`;

  constructor(private http: HttpClient) { }

  loginUser(loginData: LoginRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(this.loginUrl, loginData);
  }

  registerUser(registerRequest: RegisterRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(this.registerUrl, registerRequest)
  }
}
