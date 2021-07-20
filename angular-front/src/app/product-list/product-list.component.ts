import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {ProductService} from "../model/product.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  public products: Product[]=[];
  public isError: boolean = false;

  constructor(public productService: ProductService) { }

  ngOnInit(): void {
    this.retrieveProduct();
  }

  private retrieveProduct(){
    this.productService.findAll()
      .then(res => {
          this.isError = false;
          this.products = res;
      })
      .catch(err => {
        this.isError = true;
        console.log(err);
      })
  }

  delete(id: number) {
    this.productService.delete(id)
      .then(() => {
        this.retrieveProduct();
      })
  }


}
