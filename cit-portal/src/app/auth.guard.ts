import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const hasStorage = typeof localStorage !== 'undefined';
  const isAuthorized = hasStorage && localStorage.getItem('citPortalAuth') === 'true';

  if (isAuthorized) {
    return true;
  }

  return router.createUrlTree(['/students'], {
    queryParams: { denied: 'about' },
  });
};