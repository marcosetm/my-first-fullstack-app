import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VacationService } from '../../services/vacation.service';
import { Vacation } from '../../models/vacation.model';

@Component({
  selector: 'app-vacation-list',
  imports: [CommonModule],
  templateUrl: './vacation-list.component.html',
  styleUrl: './vacation-list.component.css'
})
export class VacationListComponent implements OnInit {

  vacations: Vacation[] = [];
  userId = 1; // to be replaced with logged-in user

  constructor(private vacationService: VacationService) {}

  ngOnInit(): void {
      console.log("hello from vacation-lists")
      this.vacationService.getVacationbyUserId(this.userId).subscribe({
        next: (data) => (this.vacations = data),
        error: (err) => console.error('Error loading vacations:', err)
      });
  }
}
