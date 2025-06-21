import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const dateRangeValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const start = control.get('startDate')?.value;
  const end = control.get('endDate')?.value;

  if (!start || !end) return null;

  const today = new Date();
  today.setHours(0, 0, 0, 0);
  
  const startDate = new Date(start);
  const endDate = new Date(end);

  const isEndBeforeStart = endDate < startDate;
  const isStartBeforeToday = startDate < today;

  const errors: ValidationErrors = {};

  if (isEndBeforeStart) {
    errors['dateRange'] = true;
  }

  if (isStartBeforeToday) {
    errors['startDatePast'] = true;
  }

  return Object.keys(errors).length > 0 ? errors : null;
};
