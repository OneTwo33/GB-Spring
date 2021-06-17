package ru.onetwo33;

import ru.onetwo33.persist.Product;
import ru.onetwo33.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        ProductRepository productRepository = new ProductRepository();
        productRepository.save(new Product(1L, "Product 1", 12.44F));
        productRepository.save(new Product(2L, "Product 2", 13.45F));
        productRepository.save(new Product(3L, "Product 3", 5.67F));
        productRepository.save(new Product(4L, "Product 4", 18.00F));
        productRepository.save(new Product(5L, "Product 5", 11.05F));
        productRepository.save(new Product(6L, "Product 6", 12F));
        productRepository.save(new Product(7L, "Product 7", 14.38F));
        productRepository.save(new Product(8L, "Product 8", 15.99F));
        productRepository.save(new Product(9L, "Product 9", 3.84F));
        productRepository.save(new Product(10L, "Product 10", 88.6F));

        sc.setAttribute("productRepository", productRepository);
    }
}
