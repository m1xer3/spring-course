package ru.danilsibgatyllin;

import org.hibernate.cfg.Configuration;
import ru.danilsibgatyllin.models.Client;
import ru.danilsibgatyllin.services.ClientService;
import ru.danilsibgatyllin.services.ProductRepository;
import ru.danilsibgatyllin.services.UserRepository;

import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        UserRepository ur = new UserRepository(emFactory);
        ProductRepository rp = new ProductRepository(emFactory);
        ClientService clientService =new ClientService(emFactory);

        clientService.saveOrUpdateClient(new Client(null,"Petr"));
        clientService.saveOrUpdateClient(new Client(null,"Jony"));

        clientService.addPoductInOrder(2L,3L);
        clientService.addPoductInOrder(2L,1L);
        clientService.addPoductInOrder(2L,3L);

        System.out.println(clientService.clientProducts(2L));
        System.out.println(clientService.whoByThisProduct(3L));

    }
}
