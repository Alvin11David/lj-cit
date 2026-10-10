import { Routes } from '@angular/router';

import { AboutComponent } from './about/about';
import { authGuard } from './auth.guard';
import { NotFoundComponent } from './not-found/not-found';
import { StudentDetailComponent } from './student-detail/student-detail';
import { StudentListComponent } from './student-list/student-list';


export const routes: Routes = [
  { path: '', redirectTo: 'students', pathMatch: 'full' },

  { path: 'students', component: StudentListComponent },

  { path: 'students/:id', component: StudentDetailComponent },

  { path: 'about', component: AboutComponent, canActivate: [authGuard] },

  { path: 'not-found', component: NotFoundComponent },

  { path: '**', redirectTo: 'not-found' },
];
