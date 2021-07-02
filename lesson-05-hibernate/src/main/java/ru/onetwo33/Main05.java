package ru.onetwo33;

import org.hibernate.cfg.Configuration;
import ru.onetwo33.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main05 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        // INSERT

//        em.getTransaction().begin();
//
//        User user = new User(null, "user1", 25);
//        em.persist(user);
//
//        em.getTransaction().commit();

        // SELECT

//        User user = em.find(User.class, 1L);
//        System.out.println(user);

        // HQL, JPQL
//        List<User> users = em.createQuery("select * from User u where u.age > 25", User.class)
//                .getResultList();
//        System.out.println(users);
//
//        Long countUsers = em.createNamedQuery("countUsers", Long.class).getSingleResult();
//        System.out.println(countUsers);
//
//        users = em.createNativeQuery("select * from users", User.class)
//                .getResultList();
//        System.out.println(users);

        // UPDATE

//        em.getTransaction().begin();

//        em.createQuery("update User set age = 22 where id = 1")
//                .executeUpdate();

//        User user = em.find(User.class, 2L);
//        user.setAge(27);

//        User user = new User(1L, "user1", 64);
//        em.merge(user);
//
//        em.getTransaction().commit();

        // DELETE

        em.getTransaction().begin();

        User user = em.getReference(User.class, 6L);
        em.remove(user);

        em.getTransaction().commit();

        em.close();
    }
}
