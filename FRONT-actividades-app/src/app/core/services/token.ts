import { Injectable } from '@angular/core';

const TOKEN_KEY = 'access_token';
const ROLES_KEY = 'roles';

@Injectable({
  providedIn: 'root',
})
export class Token {

  saveToken(token: string): void {
    localStorage.setItem(TOKEN_KEY, token);
  }

  saveRoles(roles: string[]){
    localStorage.setItem(ROLES_KEY, JSON.stringify(roles));
  }
  
  getToken(): string | null{
    return localStorage.getItem(TOKEN_KEY);
  }

  getRoles(): string[]{
    const roles = localStorage.getItem(ROLES_KEY);
    return roles ? JSON.parse(roles) : [];
  }

  removeToken(): void{
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(ROLES_KEY);
  }

  isLogged(): boolean{
    return !! this.getToken();
  }
}
