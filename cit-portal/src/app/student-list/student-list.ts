import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-student-list',
  imports: [],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css',
})
export class StudentList {
  isLoading = signal(true);

  students = [
    { name: 'Alice Nakato', regNumber: '2024001', gpa: 3.75 },
    { name: 'Bob Okello', regNumber: '2024002', gpa: 3.10 },
    { name: 'Grace Namono', regNumber: '2024003', gpa: 3.92 },
    { name: 'David Ssekandi', regNumber: '2024004', gpa: 2.85 },
  ];

  constructor() {
    setTimeout(() => {
      this.isLoading.set(false);
    }, 1500);
  }

  select(student: { name: string }) {
    console.log('you clicked on', student.name);
  }
}
