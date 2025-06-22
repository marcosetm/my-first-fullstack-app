import { Component, OnInit } from '@angular/core';
import { VacationService } from '../../services/vacation.service';
import { AuthService } from '../../services/auth.service';
import { Vacation } from '../../models/vacation.model';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-reports',
  imports: [CommonModule, RouterLink],
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  objectKeys = Object.keys;

  groupedVacations: { [key: string]: Vacation[] } = {};

  constructor(
    private vacationService: VacationService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const user = this.authService.getUser();
    this.vacationService.getVacationbyUserId(user.id).subscribe({
      next: vacations => {
        this.groupedVacations = this.groupByDate(vacations);
      },
      error: err => console.error('Failed to load vacations:', err)
    });
  }

  groupByDate(vacations: Vacation[]): { [key: string]: Vacation[] } {
    return vacations.reduce((acc, vacation) => {
      const dateKey = new Date(vacation.startDate).toDateString();
      if (!acc[dateKey]) {
        acc[dateKey] = [];
      }
      acc[dateKey].push(vacation);
      return acc;
    }, {} as { [key: string]: Vacation[] });
  }
}
