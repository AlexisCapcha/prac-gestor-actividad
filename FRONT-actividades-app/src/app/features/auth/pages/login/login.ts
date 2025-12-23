import { Component } from '@angular/core';
import { Auth } from '../../../../core/services/auth';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Token } from '../../../../core/services/token';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  loginForm! : FormGroup;
  loading = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: Auth,
    private TokenService: Token,
    private router: Router
  ){
    this.createForm();
  }

  private createForm(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login(){

    if (this.loginForm.invalid){
      this.loginForm.markAllAsTouched();
      return;
    }

    this.loading = true;

    this.authService.login(this.loginForm.value).subscribe({
      next: res =>{
        this.TokenService.saveToken(res.token);
        this.TokenService.saveRoles(res.Roles);
        this.loading = false;

        if (res.Roles.includes('ADMIN')) {
          this.router.navigate(['/admin']);
          console.log('Login exitoso');
        }else if (res.Roles.includes('USER')) {
          this.router.navigate(['/user']);
          console.log('Login exitoso');
        }else {
          this.router.navigate(['/login']);
        }
      },
      error: err => {
        this.errorMessage = 'Usuario o contraseña incorrectos';
        this.loading = false;
      }
    });
  }

}
