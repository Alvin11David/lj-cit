import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of, timeout } from 'rxjs';

import { apiConfig } from './api.config';
import { Student } from './student';

const API_TIMEOUT_MS = 3000;

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private http = inject(HttpClient);

  private readonly fallbackStudents: Student[] = [
    { name: 'Alice Nakato', regNumber: '2024001', gpa: 3.75, track: 'Software Engineering', isActive: true },
    { name: 'Bob Okello', regNumber: '2024002', gpa: 3.1, track: 'Computer Science', isActive: true },
    { name: 'Grace Namono', regNumber: '2024003', gpa: 3.92, track: 'Information Systems', isActive: true },
    { name: 'David Ssekandi', regNumber: '2024004', gpa: 2.85, track: 'Networks', isActive: false },
  ];

  getStudents(): Observable<Student[]> {
    if (!apiConfig.useLiveApi) {
      return of([...this.fallbackStudents]);
    }

    return this.http.get<Student[]>(apiConfig.baseUrl).pipe(
      timeout(API_TIMEOUT_MS),
      catchError(() => {
        console.warn('Students API unavailable — showing local data.');
        return of([...this.fallbackStudents]);
      }),
    );
  }

  getTopStudents(minGpa = 3.5): Observable<Student[]> {
    return this.getStudents().pipe(map((students) => students.filter((s) => s.gpa >= minGpa)));
  }

  getStudent(regNumber: string | null): Observable<Student | undefined> {
    if (!apiConfig.useLiveApi) {
      return of(this.fallbackStudents.find((s) => s.regNumber === regNumber));
    }

    return this.http.get<Student>(`${apiConfig.baseUrl}/${regNumber}`).pipe(
      timeout(API_TIMEOUT_MS),
      catchError(() => of(this.fallbackStudents.find((s) => s.regNumber === regNumber))),
    );
  }

  addStudent(student: Student): Observable<Student> {
    if (!apiConfig.useLiveApi) {
      this.fallbackStudents.push(student);
      return of(student);
    }

    return this.http.post<Student>(apiConfig.baseUrl, student).pipe(
      catchError(() => {
        this.fallbackStudents.push(student);
        return of(student);
      }),
    );
  }

  updateStudent(regNumber: string, student: Student): Observable<Student> {
    if (!apiConfig.useLiveApi) {
      this.replaceFallback(regNumber, student);
      return of(student);
    }

    return this.http.put<Student>(`${apiConfig.baseUrl}/${regNumber}`, student).pipe(
      catchError(() => {
        this.replaceFallback(regNumber, student);
        return of(student);
      }),
    );
  }

  deleteStudent(regNumber: string): Observable<void> {
    if (!apiConfig.useLiveApi) {
      this.removeFallback(regNumber);
      return of(undefined);
    }

    return this.http.delete<void>(`${apiConfig.baseUrl}/${regNumber}`).pipe(
      catchError(() => {
        this.removeFallback(regNumber);
        return of(undefined);
      }),
    );
  }

  private replaceFallback(regNumber: string, student: Student): void {
    const index = this.fallbackStudents.findIndex((s) => s.regNumber === regNumber);
    if (index !== -1) {
      this.fallbackStudents[index] = student;
    }
  }

  private removeFallback(regNumber: string): void {
    const index = this.fallbackStudents.findIndex((s) => s.regNumber === regNumber);
    if (index !== -1) {
      this.fallbackStudents.splice(index, 1);
    }
  }
}
