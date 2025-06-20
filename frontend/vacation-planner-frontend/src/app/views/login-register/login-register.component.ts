import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { LoginRequest } from '../../models/loginRequest.model';
import { CommonModule } from '@angular/common';
import { RegisterRequest } from '../../models/registerRequest.model';

@Component({
  selector: 'app-login-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './login-register.component.html',
  styleUrl: './login-register.component.css'
})
export class LoginRegisterComponent {
  // login
  email = '';
  password = '';
  errorMessage = '';

  // register
  firstName = '';
  lastName = '';
  regEmail = '';
  regPassword = '';
  dateOfBirth = '';

  constructor(private userService: UserService, private router: Router) {}

  onLogin(): void {
    const loginData: LoginRequest = {
      email: this.email,
      password: this.password
    };

    this.userService.loginUser(loginData).subscribe({
      next: (user) => {
        console.log('Logged in: ', user);
        this.router.navigate(['/vacations']);
      },
      error: (err) => {
        this.errorMessage = 'Login failed. Please check your credentials.';
        console.error(err);
      }
    });
  }

  onRegister(): void {
    const registerData: RegisterRequest = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.regEmail,
      password: this.regPassword,
      dateOfBirth: this.dateOfBirth
    }

    this.userService.registerUser(registerData).subscribe({
      next: (newUser) => {
        console.log('Registerd user:', newUser);
        this.router.navigate(['/vacations']);
      },
      error: (err) => {
        this.errorMessage = 'Registration failed. Please check your fields.';
        console.error(err);
      }
    });
  }

}
