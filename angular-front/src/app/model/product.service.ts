import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "./product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(public http: HttpClient) { }

  public findAll(){
    return this.http.get<Product[]>('api/v1/product/all').toPromise();
  }

  public findById(id: number) {
    return this.http.get<Product>(`/api/v1/product/${id}`).toPromise();
  }

  public delete(id: number) {
    return this.http.delete(`/api/v1/product/${id}`).toPromise();
  }


  public save(product: Product) {
    return this.http.put("/api/v1/product/",product).toPromise();
  }
}
