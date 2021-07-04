package ru.danilsibgatyllin.services;

import ru.danilsibgatyllin.models.Product;
import ru.danilsibgatyllin.models.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class ProductRepository {

    private EntityManager em;

    public ProductRepository (EntityManager em){
        this.em=em;
    }

    public List<Product> getAllProduct() {
        return em.createQuery("select p from Product p").getResultList();
    }

    public Product getOneProduct (Long id){
        return em.find(Product.class,id);
    }

    public void saveOrUpdateProduct(Product product) {
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public void deleteProduct(long id) {
        User delUser = em.find(User.class,id);
        em.getTransaction().begin();
        em.remove(delUser);
        em.getTransaction().commit();
    }


}
