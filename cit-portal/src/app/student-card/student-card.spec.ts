import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentCard } from './student-card';

describe('StudentCard', () => {
  let component: StudentCard;
  let fixture: ComponentFixture<StudentCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentCard],
    }).compileComponents();

    fixture = TestBed.createComponent(StudentCard);
    component = fixture.componentInstance;
    fixture.componentRef.setInput('student', { name: 'Alice Nakato', regNumber: '2024001', gpa: 3.75 });
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
