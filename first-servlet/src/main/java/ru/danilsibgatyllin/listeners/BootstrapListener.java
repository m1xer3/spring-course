package ru.danilsibgatyllin.listeners;

import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ProductRepository repo =new ProductRepository();
        repo.addProduct(new Product("Banana",150));
        repo.addProduct(new Product("Mango",350));
        repo.addProduct(new Product("Onion",50));
        repo.addProduct(new Product("Apple",100));
        repo.addProduct(new Product("Orange",220));
        repo.addProduct(new Product("Kiwi",310));
        repo.addProduct(new Product("Cucumber",170));
        repo.addProduct(new Product("Tangerine",260));
        repo.addProduct(new Product("Plum",140));
        repo.addProduct(new Product("Pumpkin",200));
        servletContext.setAttribute("productRepository",repo);
    }
}
