import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { VacationService } from '../../services/vacation.service'; 
import { AuthService } from '../../services/auth.service';
import { Vacation } from '../../models/vacation.model';

@Component({
  selector: 'app-vacation-details',
  imports: [CommonModule,ReactiveFormsModule, RouterLink],
  templateUrl: './vacation-details.component.html',
  styleUrl: './vacation-details.component.css'
})
export class VacationDetailsComponent implements OnInit {

  vacationForm!: FormGroup;
  isEditMode = false;
  vacationId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private vacationService: VacationService,
    private authService: AuthService,
    private router: Router
  ) {  }

  ngOnInit(): void {
      this.vacationForm = this.fb.group({
      title: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.isEditMode = true;
      this.vacationId = +id;
      this.vacationService.getVacationById(this.vacationId).subscribe((vac) => {
        this.vacationForm.patchValue(vac);
      });
    }
  }

  onSubmit(): void {
  if (this.vacationForm.invalid) return;

  const user = this.authService.getUser();

  const vacationData: Vacation = {
    id: this.vacationId!,
    title: this.vacationForm.value.title!,
    startDate: new Date(this.vacationForm.value.startDate!),
    endDate: new Date(this.vacationForm.value.endDate!),
    userId: user.id,
    excursionCount: 0 // not necessary 
  };

  if (this.isEditMode && this.vacationId) {
    console.log(vacationData);
    this.vacationService.updateVacation(user.id, vacationData).subscribe(() => {
      this.router.navigate(['/vacations']);
    });
  } else {
    this.vacationService.createVacation(user.id, vacationData).subscribe(() => {
      this.router.navigate(['/vacations']);
    });
  }
}
}
