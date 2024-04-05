import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private products: Product[] = [
    {
      id: 1,
      name: 'Mesa comedor',
      description: 'Excelente mesa para el comedor',
      price: 700,
    },
    {
      id: 2,
      name: 'Teclado mecánico',
      description: 'Excelente teclado para typing',
      price: 500,
    },
  ];

  constructor() {}

  findAll(): Observable<Product[]> {
    return of(this.products); // la función of() convierte un objeto o array en un Observable.
  }
}
