import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-student-card',
  imports: [],
  templateUrl: './student-card.html',
  styleUrl: './student-card.css',
})
export class StudentCard {
  @Input() student!: { name: string; regNumber: string; gpa: number };
  @Output() select = new EventEmitter<{ name: string; regNumber: string; gpa: number }>();

  onSelect() {
    this.select.emit(this.student);
  }
}
