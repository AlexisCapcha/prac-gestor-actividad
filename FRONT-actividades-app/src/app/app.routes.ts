import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth-guard';
import { RoleGuard } from './core/guards/role-guard';

export const routes: Routes = [
    {
        path: 'login',
        loadComponent: () => import('./features/auth/pages/login/login').then(m => m.Login)
    },
    {
        path: 'register',
        loadComponent: () => import('./features/auth/pages/register/register').then(m => m.Register)
    },
    {
        path: 'admin',
        canActivate: [authGuard, RoleGuard],
        data: { roles: ['ADMIN']},
        loadComponent: () => import('./features/admin/pages/index-admin/index-admin').then(m => m.IndexAdmin)
    },
    {
        path: 'user',
        canActivate: [authGuard, RoleGuard],
        data: { roles: ['USER']},
        loadComponent: () => import('./features/user/pages/index-user/index-user').then(m => m.IndexUser)
    },
    {
        path: '**',
        redirectTo: 'login'
    }
];
