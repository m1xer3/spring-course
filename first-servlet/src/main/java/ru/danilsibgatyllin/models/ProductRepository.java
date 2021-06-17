package ru.danilsibgatyllin.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class ProductRepository {

    private final Map<Long,Product> productRepository = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public void addProduct(Product product){
        long id = identity.incrementAndGet();
        product.setId(id);
        productRepository.put(id,product);
    }

    public List<Product> getAllProduct() {
        return new ArrayList<>(productRepository.values());
    }

}
