package ru.danilsibgatyllin.services;

import ru.danilsibgatyllin.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class ProductRepository {

    private EntityManagerFactory emFactory;

    public ProductRepository (EntityManagerFactory emFactory){
        this.emFactory=emFactory;
    }

    public List<Product> getAllProduct() {
        return executeForEntityManger(em ->em.createQuery("select p from Product p").getResultList());
    }

    public Product getOneProduct (Long id){
        return executeForEntityManger(em-> em.find(Product.class,id));
    }

    public void saveOrUpdateProduct(Product product) {
        executeInTransaction(em->em.merge(product));
    }

    public void deleteProduct(long id) {
        Product delProd= executeForEntityManger(em -> em.getReference(Product.class, id));
        executeInTransaction(em-> em.remove(delProd));
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
