package ru.onetwo33;

import ru.onetwo33.entity.Product;
import ru.onetwo33.entity.ProductDao;

import java.util.List;

public class TestProduct {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();

        // INSERT

        productDao.saveOrUpdate(new Product(null, "Product 1", 33.7F));
        productDao.saveOrUpdate(new Product(null, "Product 2", 30.7F));
        productDao.saveOrUpdate(new Product(null, "Product 3", 35.7F));

        // SELECT

        Product product = productDao.findById(3L);
        System.out.println(product);

        // UPDATE

        product.setCost(540.30F);
        productDao.saveOrUpdate(product);
        System.out.println(product);

        // FIND ALL

        List<Product> products = productDao.findAll();
        System.out.println(products);

    }
}
