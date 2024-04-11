import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../models/product';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'product-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {
  @Input() product: Product = {
    // con Input pasamos info del padre al hijo
    id: 0,
    name: '',
    description: '',
    price: 0,
  };

  @Output() newProductEvent = new EventEmitter(); // con esto trasmitimos información al padre

  onSubmit(): void {
    this.newProductEvent.emit(this.product); // emitimos el producto del form
    console.log(this.product);
  }

  clean(): void {
    this.product = new Product();
  }
}
