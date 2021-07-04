package ru.danilsibgatyllin.services;

import lombok.Data;
import ru.danilsibgatyllin.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Data
public class UserRepository {

    private EntityManagerFactory emFactory;

    public UserRepository (EntityManagerFactory emFactory){
        this.emFactory=emFactory;
    }

    public List<User> findAll() {
        return executeForEntityManger(em->em.createQuery("select u from User u").getResultList());
    }

    public User findById(long id) {
        return executeForEntityManger(em-> em.find(User.class,id));
    }

    public void addOrUpdate(User user) {
        executeInTransaction(em -> em.merge(user));
    }

    public void delete(long id) {
            User delUser = executeForEntityManger(em -> em.getReference(User.class, id));
            executeInTransaction(em -> em.remove(delUser));
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
