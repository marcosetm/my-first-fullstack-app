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
    return this.http.get<Vacation[]>('${this.baseUrl/user/${userId}}')
  }
}
