package ru.danilsibgatyllin.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class ShoppingBasket {

    private List<Product> shoppingBasket =new ArrayList<>();

    @Getter
    private Integer totalPrice =0;

    public void putProductIntoBasket(Product product){
        this.shoppingBasket.add(product);
        this.totalPrice+=product.getCost();
    }

    public List<Product> getProductsInBasket(){
        return this.shoppingBasket;
    }


}
