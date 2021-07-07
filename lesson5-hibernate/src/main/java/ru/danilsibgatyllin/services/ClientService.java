package ru.danilsibgatyllin.services;

import ru.danilsibgatyllin.models.Client;
import ru.danilsibgatyllin.models.Order;
import ru.danilsibgatyllin.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ClientService {

    private EntityManagerFactory emFactory;

    public ClientService(EntityManagerFactory emFactory){
        this.emFactory=emFactory;
    }

    public void saveOrUpdateClient(Client client){
        executeInTransaction(em->em.merge(client));
    }

    public void addPoductInOrder(Long clientId ,Long productId){
        Client client = executeForEntityManger(em -> em.getReference(Client.class, clientId));
        Product product = executeForEntityManger(em -> em.getReference(Product.class, productId));
        Order order = new Order(null,client,product);
        executeInTransaction(em-> em.persist(order));
    }

    public List<Product> clientProducts(Long clientId){
        return executeForEntityManger(em-> em
                .createQuery("select p from Order o join fetch  Product p on p=o.product where  o.client.id=:id")
                .setParameter("id",clientId)
                .getResultList());
    }

    public List<Client> whoByThisProduct(Long productId){
        return executeForEntityManger(em-> em
                .createQuery("select c from Order o join fetch  Client c on c=o.client where  o.product.id=:id")
                .setParameter("id",productId)
                .getResultList());
    }



    private <R> R executeForEntityManger(Function<EntityManager, R> function){
        EntityManager em = emFactory.createEntityManager();
        try{
            return function.apply(em);
        }finally {
            em.close();
        }
    }

    private void executeInTransaction (Consumer<EntityManager> consumer){
        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
        finally {
            em.close();
        }
    }

}
