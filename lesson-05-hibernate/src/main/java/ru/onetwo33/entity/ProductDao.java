package ru.onetwo33.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductDao {

    private final EntityManagerFactory emFactory;

    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Optional<Product> findById(Long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(Product.class, id))
        );
    }

    public List<Product> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("select p from Product p", Product.class).getResultList()
        );
    }

    public void deleteById(Long id) {
        executeInTransaction(
                em -> em.createQuery("delete from Product where id = :id")
                .setParameter("id", id)
                .executeUpdate()
        );
    }

    public void insert(Product product) {
        executeInTransaction(
                em -> em.persist(product)
        );
    }

    public void update(Product product) {
        executeInTransaction(
                em -> em.merge(product)
        );
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            insert(product);
        } else {
            update(product);
        }
    }

    private <T> T executeForEntityManager(Function<EntityManager, T> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
