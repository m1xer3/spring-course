package ru.danilsibgatyllin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatyllin.exceptions.BadRequestException;
import ru.danilsibgatyllin.exceptions.NotFoundException;
import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductParams;
import ru.danilsibgatyllin.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService){
        this.productService=productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/filter", produces = "application/json")
    public Page<Product> findWithFilter(ProductParams productParams) {
        return productService.findWithFilter(productParams);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @PostMapping(produces = "application/json")
    public Product create(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new BadRequestException("User Id should be null");
        }
        productService.save(product);
        return product;
    }

    @PutMapping(produces = "application/json")
    public void update(@RequestBody Product user) {
        if (user.getId() == null) {
            throw new BadRequestException("User Id shouldn't be null");
        }
        productService.save(user);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

}
