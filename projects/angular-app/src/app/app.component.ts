import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ProductComponent } from './products/components/product.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [CommonModule, RouterOutlet, ProductComponent],
})
export class AppComponent {
  title: string = 'Hola mundo Angular 17';
  enabled: boolean = false;
  courses: string[] = ['Angular', 'React', 'Spring Boot'];

  setEnabled(): void {
    this.enabled = this.enabled ? false : true;
    console.log('Hemos hecho click en setEnabled');
  }
}
