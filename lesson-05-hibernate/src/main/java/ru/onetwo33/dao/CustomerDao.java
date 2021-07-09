package ru.onetwo33.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.onetwo33.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class CustomerDao {

    private final EntityManagerFactory emFactory;

    @Autowired
    public CustomerDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Optional<Customer> findById(Long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(Customer.class, id))
        );
    }

    public List<Customer> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("select c from Customer c", Customer.class).getResultList()
        );
    }

    public void deleteById(Long id) {
        executeInTransaction(
                em -> em.createQuery("delete from Customer where id = :id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }

    public void insert(Customer customer) {
        executeInTransaction(
                em -> em.persist(customer)
        );
    }

    public void update(Customer customer) {
        executeInTransaction(
                em -> em.merge(customer)
        );
    }

    public void saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            insert(customer);
        } else {
            update(customer);
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
