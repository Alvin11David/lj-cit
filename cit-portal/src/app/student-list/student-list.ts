import { Component, OnInit, signal } from '@angular/core';
import { Student } from '../student';
import { StudentCard } from '../student-card/student-card';

@Component({
  selector: 'app-student-list',
  imports: [StudentCard],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css',
})
export class StudentList implements OnInit {
  isLoading = signal(true);
  students: any[] = [];

  constructor(private studentService: Student) {}

  ngOnInit() {
    setTimeout(() => {
      this.students = this.studentService.getStudents();
      this.isLoading.set(false);
    }, 1500);
  }

  onSelect(student: { name: string }) {
    console.log('you clicked on', student.name);
  }
}
