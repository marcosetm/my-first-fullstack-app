import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacationService } from '../../services/vacation.service';
import { Vacation } from '../../models/vacation.model';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-vacation-list',
  imports: [CommonModule, RouterLink],
  templateUrl: './vacation-list.component.html',
  styleUrl: './vacation-list.component.css'
})
export class VacationListComponent implements OnInit {

  vacations: Vacation[] = [];
  user: any = {};

  constructor(
    private vacationService: VacationService, 
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = this.authService.getUser();      
  
    this.vacationService.getVacationbyUserId(this.user.id).subscribe({
      next: (data) => (this.vacations = data),
      error: (err) => console.error('Error loading vacations:', err)
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
