import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacationService } from '../../services/vacation.service';
import { Vacation } from '../../models/vacation.model';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vacation-list',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './vacation-list.component.html',
  styleUrl: './vacation-list.component.css'
})
export class VacationListComponent implements OnInit {

  vacations: Vacation[] = [];
  user: any = {};
  searchTerm: string = '';

  constructor(
    private vacationService: VacationService, 
    private authService: AuthService,
    private router: Router
  ) {}
  // https://www.geeksforgeeks.org/how-to-use-getters-setters-in-typescript/
  get filteredVacations(): Vacation[] {
    if (!this.searchTerm.trim()) {
      return this.vacations;
    }

    const term = this.searchTerm.toLowerCase();

    return this.vacations.filter(vacation =>
      vacation.title.toLowerCase().includes(term)
    );
  }


  ngOnInit(): void {
    this.loadVacations();
  }

  loadVacations(): void {
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

  deleteVacation(vacationId: number): void {
      if (confirm('Are you sure you want to delete this vacation?')) {
      this.vacationService.deleteVacation(vacationId).subscribe({
        next: () => {
          this.loadVacations();
        },
        error: (err) => {
          console.error('Failed to delete vacation:', err);
          alert('Failed to delete vacation.');
        },
      });
    }
  }
}
