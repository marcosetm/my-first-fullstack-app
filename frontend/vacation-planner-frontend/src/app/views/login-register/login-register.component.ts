import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { LoginRequest } from '../../models/loginRequest.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './login-register.component.html',
  styleUrl: './login-register.component.css'
})
export class LoginRegisterComponent {
  email = '';
  password = '';
  loginError = '';

  constructor(private userService: UserService, private router: Router) {}

  onLogin(): void {
    const loginData: LoginRequest = {
      email: this.email,
      password: this.password
    };

    // debug
    console.log(loginData);

    this.userService.loginUser(loginData).subscribe({
      next: (user) => {
        console.log('Logged in: ', user);
        this.router.navigate(['/vacations']);
      },
      error: (err) => {
        this.loginError = 'Login failed. Please check your credentials.';
        console.error(err);
      }
    });

  }
}
