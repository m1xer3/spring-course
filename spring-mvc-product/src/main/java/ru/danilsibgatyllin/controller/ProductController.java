package ru.danilsibgatyllin.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String listPage(Model model) {
        logger.info("Product list page requested");
        model.addAttribute("product", productRepository.getAllProduct());
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
        model.addAttribute("product",productRepository.getOneProduct(id));
        return "product_form";
    }

    @PostMapping("/add")
    public String update(Product product) {
        if(product.getId()==null){
            logger.info("Add product"+product);
            productRepository.addProduct(product);
        } else {
            logger.info("Update product"+product);
            productRepository.updateProduct(product);
        }
        return "redirect:/product";
    }


    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Delete product id "+id);
        productRepository.deleteProduct(id);
        return "redirect:/product";
    }




}
