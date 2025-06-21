import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vacation } from '../models/vacation.model';

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  // extract into a config file for production
  private baseUrl = 'http://localhost:8080/api/vacations';

  constructor(private http: HttpClient) { }

  getVacationbyUserId(userId: number): Observable<Vacation[]> {
    return this.http.get<Vacation[]>(`${this.baseUrl}/user/${userId}`)
  }

  getVacationById(vacationId: number): Observable<Vacation> {
    return this.http.get<Vacation>(`${this.baseUrl}/${vacationId}`)
  }

  createVacation(userId: number, vacationData: Vacation): Observable<Vacation[]> {
    const createUrl = `${this.baseUrl}/user/${userId}`;
    return this.http.post<Vacation[]>(createUrl, vacationData);
  }

  updateVacation(userId: number, vacationData: Vacation): Observable<Vacation[]> {
    const vacationId = vacationData.id;
    const updateUrl = `${this.baseUrl}/user/${userId}`;
    return this.http.put<Vacation[]>(updateUrl, vacationData);
  }

}
