import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function excursionDateValidator(vacationStart: Date, vacationEnd: Date): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const excursionDate = new Date(control.value);

    if (!vacationStart || !vacationEnd || !excursionDate) return null;

    const start = new Date(vacationStart);
    const end = new Date(vacationEnd);

    if (excursionDate < start || excursionDate > end) {
      return { excursionDateOutOfRange: true };
    }

    return null;
  };
}
