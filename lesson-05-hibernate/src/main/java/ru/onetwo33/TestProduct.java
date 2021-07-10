package ru.onetwo33;

import org.hibernate.cfg.Configuration;
import ru.onetwo33.entity.Product;
import ru.onetwo33.dao.ProductDao;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TestProduct {
    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

        ProductDao productDao = new ProductDao(emFactory);

        // INSERT

//        productDao.saveOrUpdate(new Product(null, "Product 1", 33.7F));
//        productDao.saveOrUpdate(new Product(null, "Product 2", 30.7F));
//        productDao.saveOrUpdate(new Product(null, "Product 3", 35.7F));

        // SELECT

        Optional<Product> product = productDao.findById(3L);
        System.out.println(product);

        // UPDATE

        if (product.isPresent()) {
            product.get().setCost(BigDecimal.valueOf(540.30));
            productDao.saveOrUpdate(product.get());
        }
        System.out.println(product);

        // FIND ALL

        List<Product> products = productDao.findAll();
        System.out.println(products);

    }
}
