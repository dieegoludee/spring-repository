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

  productSelected: Product = new Product();

  constructor(private service: ProductService) {}

  ngOnInit(): void {
    // los products que llegan del backend se guardan en la variable this.products
    this.service.findAll().subscribe((products) => (this.products = products));
  }

  addProduct(product: Product): void {
    if (product.id > 0) {
      this.products = this.products.map((prod) => {
        if (prod.id == product.id) {
          return { ...product };
        }
        return prod;
      });
    } else {
      // product.id = new Date().getTime();
      // this.products.push(product);
      this.products = [
        ...this.products,
        { ...product, id: this.products.length + 1 }, // new Date().getTime(); para id
      ]; // esta sería la forma inmutable, en angular se pueden hacer de ambas formas
    }
    this.productSelected = new Product(); // para limpiar el form al crear o actualizar
  }

  onUpdateProduct(productRow: Product): void {
    this.productSelected = productRow;
  }

  onRemoveProduct(id: number): void {
    this.products = this.products.filter((product) => product.id != id);
  }
}
