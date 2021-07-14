package ru.danilsibgatyllin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductRepository;
import ru.danilsibgatyllin.models.ProductSpecifications;

import java.util.Optional;


@Controller
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("productNameFilter")Optional<String> productNameFilter,
                           @RequestParam("minCost")Optional<Integer> minCost,
                           @RequestParam("maxCost")Optional<Integer> maxCost,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField) {
        logger.info("Product list page requested");

        Specification<Product> spec =Specification.where(null);

        if(productNameFilter.isPresent()&&!productNameFilter.get().isBlank()){
            spec=spec.and(ProductSpecifications.productPrefix(productNameFilter.get()));
        }
        if (minCost.isPresent()){
            spec=spec.and(ProductSpecifications.minCost(minCost.get()));
        }
        if (maxCost.isPresent()){
            spec=spec.and(ProductSpecifications.maxCost(maxCost.get()));
        }

        model.addAttribute("product", productRepository.findAll(spec, PageRequest.of(
                page.orElse(1)-1,
                size.orElse(3),
                Sort.by(sortField.orElse("id"))
        )));

        return "product";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        logger.info("New product page requested");

        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product",productRepository.findById(id));
        return "product_form";
    }

    @PostMapping("/add")
    public String update(Product product) {
        if(product.getId()==null){
            logger.info("Add product"+product);
            productRepository.save(product);
        } else {
            logger.info("Update product"+product);
            productRepository.save(product);
        }
        return "redirect:/product";
    }


    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Delete product id "+id);
        productRepository.deleteById(id);
        return "redirect:/product";
    }




}
