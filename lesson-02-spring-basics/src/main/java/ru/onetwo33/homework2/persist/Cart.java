package ru.onetwo33.homework2.persist;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("cart")
@Scope("prototype")
public class Cart {

    private static long counter = 1;

    private final ProductRepository productRepository;

    private final List<Product> products = new ArrayList<>();

    private Long id;

    public Cart(ProductRepository productRepository) {
        setId(counter++);
        this.productRepository = productRepository;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void add(long id) {
        products.add(productRepository.findById(id));
    }

    public void delete(long id) {
        products.remove(productRepository.findById(id));
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
