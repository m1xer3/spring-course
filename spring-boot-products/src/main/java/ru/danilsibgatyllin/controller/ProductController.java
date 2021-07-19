package ru.danilsibgatyllin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductParams;
import ru.danilsibgatyllin.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model,
                           ProductParams productParams) {
        logger.info("Product list page requested");

        model.addAttribute("products", productService.findWithFilter(productParams));

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
        model.addAttribute("product",productService.findById(id));
        return "product_form";
    }

    @PostMapping("/add")
    public String update(Product product) {
        if(product.getId()==null){
            logger.info("Add product"+product);
            productService.save(product);
        } else {
            logger.info("Update product"+product);
            productService.save(product);
        }
        return "redirect:/product";
    }


    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Delete product id "+id);
        productService.deleteById(id);
        return "redirect:/product";
    }




}
