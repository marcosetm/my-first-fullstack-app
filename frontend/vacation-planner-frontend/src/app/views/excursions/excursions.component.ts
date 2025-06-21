import { Component, Input, OnInit } from '@angular/core';
import { ExcursionService } from '../../services/excursion.service';
import { Excursion } from '../../models/excursion.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-excursions',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './excursions.component.html',
})
export class ExcursionsComponent implements OnInit {
  @Input() vacationId!: number;
  excursions: Excursion[] = [];
  excursionForm!: FormGroup;
  showForm = false;

  constructor(private excursionService: ExcursionService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadExcursions();
    this.excursionForm = this.fb.group({
      name: ['', Validators.required],
      date: ['', Validators.required],
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    if (!this.showForm) this.excursionForm.reset();
  }

  loadExcursions(): void {
    this.excursionService.getExcursions(this.vacationId).subscribe({
      next: (data) => (this.excursions = data),
      error: (err) => console.error('Failed to load excursions:', err),
    });
  }

  onSubmit(): void {
    if (this.excursionForm.invalid) return;

    const excursion: Excursion = {
      ...this.excursionForm.value,
      vacationId: this.vacationId,
    };

    this.excursionService.addExcursion(this.vacationId, excursion).subscribe({
      next: () => {
        this.excursionForm.reset();
        this.showForm = false;
        this.loadExcursions();
      },
      error: (err) => console.error('Failed to add excursion:', err),
    });
  }
}

