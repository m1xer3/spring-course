package ru.danilsibgatyllin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.danilsibgatyllin.models.ProductRepository;
import ru.danilsibgatyllin.models.ShoppingBasket;

@Configuration
public class ProductWebConfig {

    @Bean(name = "productRepo")
        public ProductRepository productRepo(){
        return new ProductRepository();
    }

    @Bean(name = "shoppingBasket")
    @Scope("prototype")
        public ShoppingBasket shoppingBasket(){
        return new ShoppingBasket();
    }
}
