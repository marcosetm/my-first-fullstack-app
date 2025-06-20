import { 
  CanActivateFn ,
  Router
} from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
/*
ActivatedRouteSnapshot,
  RouterStateSnapshot
route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot

*/

export const authGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isAuthenticated()) {
    console.log("heelo from auth.guard")
    return true;
  } else {
    router.navigate(['/login']);
      return false;
  }
};
