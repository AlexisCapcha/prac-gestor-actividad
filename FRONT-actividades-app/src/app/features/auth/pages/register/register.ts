import { Component } from '@angular/core';
import { EmailValidator, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Auth } from '../../../../core/services/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  registerForm! : FormGroup;
  loading = false;
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: Auth
  ){
    this.createForm();
  }

  private createForm(): void{
    this.registerForm = this.fb.group({
      email: ['',[Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  register(): void{
    if(this.registerForm.invalid){
      this.registerForm.markAllAsTouched();
      return;
    }

    const request = {
      ...this.registerForm.value,
      roles: ['USER']
    }

    this.loading = true;

    this.authService.register(request).subscribe({
      next: () =>{
        this.successMessage = 'Usuario registrado correctamente';
        this.errorMessage = '';
        this.registerForm.reset();
        this.loading = false;
      },
      error: err =>{
        this.errorMessage = 'Error al registrar usuario';
        this.loading = false;
      }
    });

  }
}
