import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of } from 'rxjs';

import { Student } from './student';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private http = inject(HttpClient);

  private readonly apiUrl = 'http://localhost:8080/students/api/v1/students';

  private readonly fallbackStudents: Student[] = [
    { name: 'Alice Nakato', regNumber: '2024001', gpa: 3.75, track: 'Software Engineering', isActive: true },
    { name: 'Bob Okello', regNumber: '2024002', gpa: 3.1, track: 'Computer Science', isActive: true },
    { name: 'Grace Namono', regNumber: '2024003', gpa: 3.92, track: 'Information Systems', isActive: true },
    { name: 'David Ssekandi', regNumber: '2024004', gpa: 2.85, track: 'Networks', isActive: false },
  ];

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl).pipe(
      catchError((error) => {
        console.warn('Students API unavailable, showing local data instead.', error);
        return of([...this.fallbackStudents]);
      }),
    );
  }

  getTopStudents(minGpa = 3.5): Observable<Student[]> {
    return this.getStudents().pipe(map((students) => students.filter((s) => s.gpa >= minGpa)));
  }

  getStudent(regNumber: string | null): Observable<Student | undefined> {
    return this.http.get<Student>(`${this.apiUrl}/${regNumber}`).pipe(
      catchError(() => of(this.fallbackStudents.find((s) => s.regNumber === regNumber))),
    );
  }

  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student).pipe(
      catchError(() => {
        this.fallbackStudents.push(student);
        return of(student);
      }),
    );
  }

  updateStudent(regNumber: string, student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/${regNumber}`, student).pipe(
      catchError(() => {
        const index = this.fallbackStudents.findIndex((s) => s.regNumber === regNumber);
        if (index !== -1) {
          this.fallbackStudents[index] = student;
        }
        return of(student);
      }),
    );
  }

  deleteStudent(regNumber: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${regNumber}`).pipe(
      catchError(() => {
        const index = this.fallbackStudents.findIndex((s) => s.regNumber === regNumber);
        if (index !== -1) {
          this.fallbackStudents.splice(index, 1);
        }
        return of(undefined);
      }),
    );
  }
}
