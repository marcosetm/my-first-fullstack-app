import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Excursion } from '../models/excursion.model';

@Injectable({
  providedIn: 'root',
})
export class ExcursionService {
  private apiUrl = 'http://localhost:8080/api/excursions';

  constructor(private http: HttpClient) {}

  getExcursions(vacationId: number): Observable<Excursion[]> {
    return this.http.get<Excursion[]>(`${this.apiUrl}/vacation/${vacationId}`);
  }

  addExcursion(vacationId: number, excursion: Excursion): Observable<Excursion> {
    return this.http.post<Excursion>(`${this.apiUrl}/vacation/${vacationId}`, excursion);
  }

  // Optional: delete, update
}
