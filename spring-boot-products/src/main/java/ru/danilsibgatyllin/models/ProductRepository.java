package ru.danilsibgatyllin.models;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long,Product> productRepository = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public void addProduct(Product product){
        long id = identity.incrementAndGet();
        product.setId(id);
        productRepository.put(id,product);
    }

    @PostConstruct
    public void init() {
        this.addProduct(new Product("Apple",150));
        this.addProduct(new Product("Orange",300));
        this.addProduct(new Product("Kiwi",220));
    }

    public List<Product> getAllProduct() {
        return new ArrayList<>(productRepository.values());
    }

    public Product getOneProduct (Long id){
       return productRepository.get(id);
    }

    public void updateProduct(Product product) {
        productRepository.put(product.getId(), product);
    }

    public void deleteProduct(long id) {productRepository.remove(id);}


}
