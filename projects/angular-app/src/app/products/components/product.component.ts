import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../models/product';
import { FormComponent } from './form/form.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [FormComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css',
})
export class ProductComponent implements OnInit {
  products: Product[] = [];

  constructor(private service: ProductService) {}

  ngOnInit(): void {
    // los products que llegan del backend se guardan en la variable this.products
    this.service.findAll().subscribe((products) => (this.products = products));
  }

  addProduct(product: Product) {
    // product.id = new Date().getTime();
    // this.products.push(product);
    this.products = [
      ...this.products,
      { ...product, id: new Date().getTime() },
    ]; // esta sería la forma inmutable, en angular se pueden hacer de ambas formas
  }
}
