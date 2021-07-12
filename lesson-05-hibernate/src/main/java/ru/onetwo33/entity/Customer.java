package ru.onetwo33.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "customer")
    private Set<JoinedCustomerProduct> customerProducts = new HashSet<>();

    public Customer() {
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<JoinedCustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    public void setCustomerProducts(Set<JoinedCustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }

    public void addCustomerProduct(JoinedCustomerProduct customerProduct) {
        this.customerProducts.add(customerProduct);
    }
}
