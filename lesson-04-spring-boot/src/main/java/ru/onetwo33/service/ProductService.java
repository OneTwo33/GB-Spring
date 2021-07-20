package ru.onetwo33.service;

import org.springframework.data.domain.Page;
import ru.onetwo33.controller.ProductListParams;
import ru.onetwo33.controller.UserListParams;
import ru.onetwo33.persist.Product;
import ru.onetwo33.persist.User;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Page<Product> findWithFilter(ProductListParams productListParams);

    Optional<Product> findById(Long id);

    void save(Product product);

    void deleteById(Long id);
}
