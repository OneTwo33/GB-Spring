package ru.onetwo33.entity;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {

    EntityManagerFactory emFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    EntityManager em = null;

    public Product findById(Long id) {
        em = emFactory.createEntityManager();

        Product product = em.find(Product.class, id);

        em.close();

        return product;
    }

    public List<Product> findAll() {
        em = emFactory.createEntityManager();

        List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();

        em.close();
        return products;
    }

    public void deleteById(Long id) {
        em = emFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.getReference(Product.class, id);
        em.remove(product);

        em.getTransaction().commit();
        em.close();
    }

    public Product saveOrUpdate(Product product) {
        em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(product);

        em.getTransaction().commit();
        em.close();

        return product;
    }
}
