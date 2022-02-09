package ru.danilsibgatyllin.interfaces;

import org.springframework.data.domain.Page;
import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductParams;

import java.util.List;
import java.util.Optional;

public interface ProductInterface {

    List<Product> findAll();

    Page<Product> findWithFilter(ProductParams productParams);

    Optional<Product> findById(Long id);

    void save(Product user);

    void deleteById(Long id);
}
