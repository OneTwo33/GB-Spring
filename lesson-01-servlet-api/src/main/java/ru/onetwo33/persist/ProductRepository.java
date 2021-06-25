package ru.onetwo33.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(long id);

    void save(Product product);

    void delete(long id);
}
