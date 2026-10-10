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

  formStudent: Student = this.emptyStudent();
  editingRegNumber: string | null = null;

  get isEditing(): boolean {
    return this.editingRegNumber !== null;
  }

  submit() {
    this.saving = true;
    this.saveError = '';

    const request$ = this.editingRegNumber
      ? this.studentService.updateStudent(this.editingRegNumber, { ...this.formStudent })
      : this.studentService.addStudent({ ...this.formStudent });

    request$.subscribe({
      next: () => {
        this.saving = false;
        this.resetForm();
        this.students$ = this.studentService.getStudents();
      },
      error: () => {
        this.saving = false;
        this.saveError = 'Could not save the student. Please try again.';
      },
    });
  }

  startEdit(student: Student) {
    this.editingRegNumber = student.regNumber;
    this.formStudent = { ...student };
    this.saveError = '';
  }

  cancelEdit() {
    this.resetForm();
  }

  deleteStudent(regNumber: string) {
    this.studentService.deleteStudent(regNumber).subscribe({
      next: () => {
        if (this.editingRegNumber === regNumber) {
          this.resetForm();
        }
        this.students$ = this.studentService.getStudents();
      },
    });
  }

  openStudent(regNumber: string) {
    this.router.navigate(['/students', regNumber]);
  }

  private resetForm() {
    this.editingRegNumber = null;
    this.formStudent = this.emptyStudent();
    this.saveError = '';
  }

  private emptyStudent(): Student {
    return { name: '', regNumber: '', gpa: 0, track: '', isActive: true };
  }
}
