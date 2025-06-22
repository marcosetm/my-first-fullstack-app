import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Excursion } from '../models/excursion.model';
import { environment } from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root',
})
export class ExcursionService {
  private baseUrl = `${environment.apiUrl}/excursions`;

  constructor(private http: HttpClient) {}

  getExcursions(vacationId: number): Observable<Excursion[]> {
    return this.http.get<Excursion[]>(`${this.baseUrl}/vacation/${vacationId}`);
  }

  addExcursion(vacationId: number, excursion: Excursion): Observable<Excursion> {
    return this.http.post<Excursion>(`${this.baseUrl}/vacation/${vacationId}`, excursion);
  }
  updateExcursion(vacationId: number, excursion: Excursion): Observable<Excursion> {
    return this.http.put<Excursion>(`${this.baseUrl}/vacation/${vacationId}`, excursion);
  }

  deleteExcursion(excursionId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${excursionId}`)
  }
}
