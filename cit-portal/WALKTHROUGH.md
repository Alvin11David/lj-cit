# CIT Portal — Weeks 13 & 14 Walkthrough

This document explains what was built in the `cit-portal` Angular app for **Week 13 (Routing)** and **Week 14 (HttpClient & RxJS)**, where the code lives, and how to explain it to a tutor.

---

## 1. How to run and demo

```bash
cd cit-portal
npm install
npm start          # serves on http://localhost:4200
npm run build      # production build
npx ng test --watch=false   # unit tests (Vitest)
```

Demo flow:
1. Open the app. It redirects `/` to `/students`.
2. Students list loads from local seed data (because `apiConfig.useLiveApi` is `false` by default). Set it to `true` in `api.config.ts` to hit the real backend at `localhost:8080`.
3. Add a student via the form → the list refreshes (`POST`).
4. Click **View →** (routerLink) or **Open** (programmatic `Router.navigate`) → `/students/:id` detail page.
5. Click **Delete** → `DELETE` + refresh.
6. Click **Set JWT** in the header; then every outgoing request carries an `Authorization: Bearer <token>` header added by the interceptor.
7. Click **About** — protected by `authGuard`. Use **Grant/Revoke About Access** to toggle it. Visit a bad URL to see the wildcard 404 page.

---

## 2. Project architecture

```
src/app/
  app.config.ts            # provides router + HttpClient + auth interceptor
  app.routes.ts            # Week 13 routes
  auth.guard.ts            # Week 13 canActivate guard
  auth.interceptor.ts      # Week 14 JWT interceptor
  student.ts               # Student model (interface)
  student.service.ts       # Week 14 API service (HttpClient + RxJS)
  student-list/            # Week 13/14 list + create form
  student-detail/          # Week 13/14 detail page (route param + async pipe)
  about/ not-found/        # Week 13 extra + wildcard routes
  app.ts / app.html        # shell: nav + JWT/guard demo toggles
```

---

## 3. Week 13 — Angular Routing

| Outcome | Where | Notes |
|---|---|---|
| Configure routes mapping paths → components | `app.routes.ts` | `''`, `students`, `students/:id`, `about`, `not-found`, `**` |
| Render active route | `app.html` | `<router-outlet>` in the shell |
| Navigate with `routerLink` (not `href`) | `app.html`, `student-list.html` | no full-page reload |
| Highlight active link | `app.html` | `routerLinkActive="active"` |
| Read route parameter | `student-detail.ts` | `this.route.snapshot.paramMap.get('id')` |
| Programmatic navigation | `student-list.ts` | `this.router.navigate(['/students', regNumber])` on the **Open** button |
| Wildcard 404 | `app.routes.ts` | `{ path: '**', redirectTo: 'not-found' }` — always last |
| Route guard | `auth.guard.ts` | `CanActivateFn` redirects to `/students` when not authorised |

Key talking points:
- An SPA loads one HTML file; the router **swaps components** and updates the URL so bookmarks/back button still work.
- Route order matters — `**` must be last because Angular uses the first match.
- A guard is **UX, not security** — the real gate is server-side (the backend's Spring Security). Anyone can edit client JS.

---

## 4. Week 14 — HttpClient & RxJS

### 4.1 Enabling HttpClient
`app.config.ts`
```ts
provideHttpClient(withInterceptors([authInterceptor]))
```
Registers `HttpClient` and wires the interceptor into every outgoing request.

### 4.2 Typed GET returning an Observable
`student.service.ts:22`
```ts
getStudents(): Observable<Student[]> {
  return this.http.get<Student[]>(this.apiUrl).pipe(
    catchError((error) => {
      console.warn('Students API unavailable, showing local data instead.', error);
      return of([...this.fallbackStudents]);
    }),
  );
}
```
- `get<Student[]>` tells TypeScript the response shape (typed against the API).
- The request is **lazy** — nothing is sent until a subscriber appears.
- `catchError` recovers from a failed request with a safe fallback list (Week 8 error handling as a stream).

### 4.3 Displaying with the async pipe
`student-list.ts` holds `students$ = this.studentService.getStudents();`
`student-list.html`
```html
@for (student of students$ | async; track student.regNumber) { ... }
@empty { <li class="empty-state">No students found.</li> }
```
The `async` pipe subscribes, unwraps, and **unsubscribes on destroy** — no memory leaks.

### 4.4 RxJS operators
`student.service.ts`
- `map` — `getTopStudents()` filters high achievers (`s.gpa >= minGpa`).
- `catchError` — used on every request as a graceful fallback.
- `of()` — creates a fallback Observable.

### 4.5 Writing data — POST / PUT / DELETE
`student.service.ts:41-72`
```ts
addStudent(student: Student): Observable<Student>     // POST
updateStudent(regNumber: string, student: Student)    // PUT
deleteStudent(regNumber: string): Observable<void>    // DELETE
```
`student-list.ts` subscribes to the POST and then refreshes the list:
```ts
this.studentService.addStudent({ ...this.newStudent }).subscribe({
  next: () => { this.students$ = this.studentService.getStudents(); },
  error: () => { this.saveError = 'Could not save the student.'; },
});
```
**Important concept:** Observables are lazy — a POST with no subscriber never fires. Write operations subscribe (there is nothing to display with the async pipe) and refresh on success.

### 4.6 JWT interceptor
`auth.interceptor.ts`
```ts
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');
  if (!token) return next(req);
  return next(req.clone({ setHeaders: { Authorization: `Bearer ${token}` } }));
};
```
- Requests are **immutable**, so we `clone()` to add the header, then continue with `next(req)`.
- One function attaches the token to **every** request — the frontend half of the backend's security.

---

## 5. API switch — live API vs local data

`api.config.ts` holds one flag:
```ts
export const apiConfig = {
  useLiveApi: false,   // flip to true when the backend runs on :8080
  baseUrl: 'http://localhost:8080/students/api/v1/students',
};
```

- `useLiveApi: false` (default) — the service returns local seed data directly. No network request is made, so the browser console stays clean and load is instant.
- `useLiveApi: true` — every method uses the real `HttpClient`, with `timeout(3000)` and `catchError` falling back to local data if the backend is down.

Using `catchError` fallbacks means the app still demonstrates HTTP/RxJS behaviour gracefully when the backend is unavailable:
- `GET` falls back to a local seed list.
- `POST` adds to the local list.
- `PUT` updates, `DELETE` removes from the local list.

---

## 6. Likely tutor questions

- **Observable vs Promise?** Observable can emit many values over time, is lazy until subscribed, can be cancelled, and is transformed with operators inside `pipe()`. A Promise delivers one value and starts immediately.
- **Why the async pipe over subscribe()?** It manages subscribe/update/unsubscribe automatically, preventing leaks.
- **Why didn't my POST do anything?** No subscriber — Observables are lazy.
- **Is the route guard real security?** No; it only improves UX. Security lives on the server.
- **Why clone the request in the interceptor?** Http requests are immutable, so the header must be added to a clone.
- **What do map/filter/catchError do?** Transform each value / pass only matching values / handle errors with a fallback.

---

## 7. Verification status

- `npm run build` — succeeds (one non-fatal CSS size warning on `app.css`).
- `npx ng test --watch=false` — **7 tests passing** across 6 spec files.
