import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Student {
  private students = [
    { name: 'Alice Nakato', regNumber: '2024001', gpa: 3.75 },
    { name: 'Bob Okello', regNumber: '2024002', gpa: 3.10 },
    { name: 'Grace Namono', regNumber: '2024003', gpa: 3.92 },
    { name: 'David Ssekandi', regNumber: '2024004', gpa: 2.85 },
  ];

  getStudents() {
    return this.students;
  }
}
