package ru.danilsibgatyllin;

import org.hibernate.cfg.Configuration;
import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.services.ProductRepository;
import ru.danilsibgatyllin.services.UserRepository;

import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        UserRepository ur = new UserRepository(emFactory.createEntityManager());
        System.out.println(ur.findAll());
//        ur.delete(2L);

        ProductRepository rp = new ProductRepository(emFactory.createEntityManager());
        rp.saveOrUpdateProduct(new Product(null,"mandarin",250));
        rp.saveOrUpdateProduct(new Product(3L,"kiwis",999));

        System.out.println(rp.getAllProduct());

    }
}
