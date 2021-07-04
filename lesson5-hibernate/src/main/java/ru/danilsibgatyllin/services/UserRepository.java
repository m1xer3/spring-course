package ru.danilsibgatyllin.services;

import lombok.Data;
import ru.danilsibgatyllin.models.User;

import javax.persistence.EntityManager;
import java.util.List;

@Data
public class UserRepository {

    private EntityManager em;

    public UserRepository (EntityManager em){
        this.em=em;
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u").getResultList();
    }

    public User findById(long id) {
        return em.find(User.class,id);
    }

    public void addOrUpdate(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void delete(long id) {
        User delUser = em.find(User.class,id);
        em.getTransaction().begin();
        em.remove(delUser);
        em.getTransaction().commit();
    }

}
