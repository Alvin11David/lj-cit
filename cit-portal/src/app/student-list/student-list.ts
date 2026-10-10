import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [RouterLink, AsyncPipe, FormsModule],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css',
})
export class StudentListComponent {
  private studentService = inject(StudentService);
  private router = inject(Router);

  students$ = this.studentService.getStudents();

  saving = false;
  saveError = '';

  newStudent: Student = this.emptyStudent();

  addStudent() {
    this.saving = true;
    this.saveError = '';

    this.studentService.addStudent({ ...this.newStudent }).subscribe({
      next: () => {
        this.saving = false;
        this.newStudent = this.emptyStudent();
        this.students$ = this.studentService.getStudents();
      },
      error: () => {
        this.saving = false;
        this.saveError = 'Could not save the student. Please try again.';
      },
    });
  }

  deleteStudent(regNumber: string) {
    this.studentService.deleteStudent(regNumber).subscribe({
      next: () => (this.students$ = this.studentService.getStudents()),
    });
  }

  openStudent(regNumber: string) {
    this.router.navigate(['/students', regNumber]);
  }

  private emptyStudent(): Student {
    return { name: '', regNumber: '', gpa: 0, track: '', isActive: true };
  }
}
