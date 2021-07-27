import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {ProductService} from "../model/product.service";
import {ProductHttpParams} from "../model/productHttpParams";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  public products: Product[] = []
  public isError: boolean = false
  public httpParams: ProductHttpParams = new ProductHttpParams()

  constructor(public productService: ProductService) { }

  ngOnInit(): void {
    this.retrieveProducts()
  }

  public retrieveProducts() {
    this.productService.findAll(this.httpParams)
      .then(res => {
        this.isError = false
        this.products = res
      })
      .catch(err => {
        this.isError = true
        console.log(err)
      })
  }

  delete(id: number) {
    this.productService.delete(id)
      .then(() => {
        this.retrieveProducts()
      })
  }

  update() {
    this.retrieveProducts()
  }
}
