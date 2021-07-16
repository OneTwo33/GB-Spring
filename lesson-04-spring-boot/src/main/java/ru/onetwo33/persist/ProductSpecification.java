package ru.onetwo33.persist;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class ProductSpecification {

    public static Specification<Product> titlePrefix(String prefix) {
        return (root, query, cb) -> cb.like(root.get("title"), prefix + "%");
    }

    public static Specification<Product> minCost(BigDecimal minCost) {
        return (root, query, cb) -> cb.ge(root.get("cost"), minCost);
    }

    public static Specification<Product> maxCost(BigDecimal maxCost) {
        return (root, query, cb) -> cb.le(root.get("cost"), maxCost);
    }
}
