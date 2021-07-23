import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "./product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<Product[]>('http://194.67.111.234:8080/spring-boot-app/api/v1/product/all').toPromise()
  }

  public findById(id: number) {
    return this.http.get<Product>(`http://194.67.111.234:8080/spring-boot-app/api/v1/product/${id}`).toPromise()
  }

  public save(product: Product) {
    if (product.id == -1) {
      return this.http.post<Product>('http://194.67.111.234:8080/spring-boot-app/api/v1/product', product).toPromise()
    }
    return this.http.put<Product>('http://194.67.111.234:8080/spring-boot-app/api/v1/product', product).toPromise()
  }

  public delete(id: number) {
    return this.http.delete<void>(`http://194.67.111.234:8080/spring-boot-app/api/v1/product/${id}`).toPromise()
  }
}
