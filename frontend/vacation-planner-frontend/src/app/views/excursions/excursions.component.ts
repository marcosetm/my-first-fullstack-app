import { Component, Input, OnInit } from '@angular/core';
import { ExcursionService } from '../../services/excursion.service';
import { Excursion } from '../../models/excursion.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { excursionDateValidator } from '../../validators/excursion-date.validator';

@Component({
  selector: 'app-excursions',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './excursions.component.html',
})
export class ExcursionsComponent implements OnInit {
  @Input() vacationId!: number;
  @Input() vacationStartDate!: Date;
  @Input() vacationEndDate!: Date;
  excursions: Excursion[] = [];
  excursionForm!: FormGroup;
  selectedExcursion: Excursion | null = null;
  showForm = false;
  isEditMode = false;


  constructor(private excursionService: ExcursionService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.loadExcursions();
    this.excursionForm = this.fb.group({
      name: ['', Validators.required],
      date: ['', [Validators.required, excursionDateValidator(this.vacationStartDate, this.vacationEndDate)]],
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    this.isEditMode = false;
    this.selectedExcursion = null;
    this.excursionForm.reset();
  }


  loadExcursions(): void {
    this.excursionService.getExcursions(this.vacationId).subscribe({
      next: (data) => { //(this.excursions = data),
        this.excursions = data.sort((a, b) =>
          new Date(a.date).getTime() - new Date(b.date).getTime()
        );
      },
      error: (err) => console.error('Failed to load excursions:', err),
    });
  }

  onSubmit(): void {
    if (this.excursionForm.invalid) return;

    const excursionData: Excursion = {
      ...this.excursionForm.value,
      vacationId: this.vacationId,
      id: this.selectedExcursion?.id
    };

    if (this.isEditMode && this.selectedExcursion) {
      this.excursionService.updateExcursion(this.vacationId, excursionData).subscribe({
        next: () => {
          this.resetForm();
          this.loadExcursions();
        },
        error: (err) => console.error('Update failed:', err)
      });
    } else {
      this.excursionService.addExcursion(this.vacationId, excursionData).subscribe({
        next: () => {
          this.resetForm();
          this.loadExcursions();
        },
        error: (err) => console.error('Add failed:', err)
      });
    }
  }

  resetForm(): void {
    this.excursionForm.reset();
    this.showForm = false;
    this.isEditMode = false;
    this.selectedExcursion = null;
  }


  updateExcursion(excursion: Excursion): void {
    this.showForm = true;
    this.isEditMode = true;
    this.selectedExcursion = excursion;

    this.excursionForm.patchValue({
      name: excursion.name,
      date: excursion.date
    });
  }

  deleteExcursion(excursionId: number): void {
    if (confirm('Are you sure you want to delete this vacation?')) {
      this.excursionService.deleteExcursion(excursionId).subscribe({
        next: () => {
          this.loadExcursions();
        },
        error: (err) => {
          console.error('Failed to delete vacation:', err);
          alert('Failed to delete vacation.');
        },
      });
    }
  }
}

