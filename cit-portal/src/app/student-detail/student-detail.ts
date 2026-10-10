import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-detail',
  standalone: true,
  imports: [RouterLink, AsyncPipe],
  templateUrl: './student-detail.html',
})
export class StudentDetailComponent {
  private route = inject(ActivatedRoute);
  private studentService = inject(StudentService);

  student$ = this.studentService.getStudent(this.route.snapshot.paramMap.get('id'));
}
