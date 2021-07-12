package ru.onetwo33.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onetwo33.dao.CustomerDao;
import ru.onetwo33.dao.ProductDao;
import ru.onetwo33.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private EntityManagerFactory emf;
    private CustomerDao customerDao;
    private ProductDao productDao;

    @Autowired
    public ShopService(EntityManagerFactory emf, CustomerDao customerDao, ProductDao productDao) {
        this.emf = emf;
        this.customerDao = customerDao;
        this.productDao = productDao;
    }

    public ShopService() {
    }

    public void findProductsByCustomerId(Long id) {
        Optional<Customer> customer = customerDao.findById(id);
        if (customer.isPresent()) {
            EntityManager em = emf.createEntityManager();
            try {
                List<JoinedCustomerProduct> customerProducts = em.createQuery("select jcp from JoinedCustomerProduct jcp join fetch jcp.customer join fetch jcp.product where jcp.customer.id = :customer_id", JoinedCustomerProduct.class)
                        .setParameter("customer_id", id)
                        .getResultList();
                for (JoinedCustomerProduct product : customerProducts) {
                    System.out.println("Товар: " + product.getProduct().getTitle() + ". Цена: " + product.getCost());
                }
            } finally {
                em.close();
            }
        }
    }

    public void findCustomersByProductId(Long id) {
        Optional<Product> product = productDao.findById(id);
        if (product.isPresent()) {
            EntityManager em = emf.createEntityManager();
            try {
                List<JoinedCustomerProduct> productCustomers = em.createQuery("select jcp from JoinedCustomerProduct jcp join fetch jcp.customer join fetch jcp.product where jcp.product.id = :product_id", JoinedCustomerProduct.class)
                        .setParameter("product_id", id)
                        .getResultList();
                for (JoinedCustomerProduct customer : productCustomers) {
                    System.out.println("Покупатель: " + customer.getCustomer().getName() + ". Цена: " + customer.getCost());
                }
            } finally {
                em.close();
            }
        }
    }
}
