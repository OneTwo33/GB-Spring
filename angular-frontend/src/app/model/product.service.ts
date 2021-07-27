import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Product} from "./product";
import {ProductHttpParams} from "./productHttpParams";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  // deploy only, for dev uses proxy.json.conf and start node server 'ng serve'
  private url: string = 'http://localhost:8080/spring-boot-app'

  constructor(public http: HttpClient) { }

  public findAll(httpParams: ProductHttpParams) {
    let params = new HttpParams()

    if (httpParams.titleFilter != null) {
      params = params.set('titleFilter', httpParams.titleFilter)
    }
    if (httpParams.minCostFilter != null) {
      params = params.set('minCostFilter', httpParams.minCostFilter)
    }
    if (httpParams.maxCostFilter != null) {
      params = params.set('maxCostFilter', httpParams.maxCostFilter)
    }
    if (httpParams.size != null) {
      params = params.set('size', httpParams.size)
    }
    if (httpParams.sort != null) {
      params = params.set('sort', httpParams.sort)
    }
    if (httpParams.direction != null) {
      params = params.set('direction', httpParams.direction)
    }
    if (httpParams.page != null) {
      params = params.set('page', httpParams.page)
    }

    return this.http.get<Product[]>(this.url + '/api/v1/product/all', {params}).toPromise()
  }

  public findById(id: number) {
    return this.http.get<Product>(this.url + `/api/v1/product/${id}`).toPromise()
  }

  public save(product: Product) {
    if (product.id == -1) {
      return this.http.post<Product>(this.url + '/api/v1/product', product).toPromise()
    }
    return this.http.put<Product>(this.url + '/api/v1/product', product).toPromise()
  }

  public delete(id: number) {
    return this.http.delete<void>(this.url + `/api/v1/product/${id}`).toPromise()
  }
}
