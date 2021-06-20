package ru.danilsibgatyllin.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.danilsibgatyllin.configs.ProductWebConfig;
import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductRepository;
import ru.danilsibgatyllin.models.ShoppingBasket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ApplicationContext context = new AnnotationConfigApplicationContext(ProductWebConfig.class);
        ProductRepository repo = context.getBean("productRepo",ProductRepository.class);
        ShoppingBasket shoppingBasket = context.getBean("shoppingBasket", ShoppingBasket.class);
        repo.addProduct(new Product("Banana",150));
        repo.addProduct(new Product("Mango",350));
        repo.addProduct(new Product("Onion",50));
        repo.addProduct(new Product("Apple",100));
        repo.addProduct(new Product("Orange",220));
        servletContext.setAttribute("productRepository",repo);
        servletContext.setAttribute("shoppingBasket", shoppingBasket);

    }
}
