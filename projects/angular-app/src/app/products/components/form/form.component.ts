import { Component, EventEmitter, Output } from '@angular/core';
import { Product } from '../../models/product';
import { FormsModule } from '@angular/forms';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'product-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
})
export class FormComponent {
  product: Product = {
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
}
