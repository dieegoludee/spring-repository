import { Injectable } from '@angular/core';
import { Product } from '../models/product';

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
      id: 1,
      name: 'Teclado mecánico',
      description: 'Excelente teclado para typing',
      price: 500,
    },
  ];

  constructor() {}
}
