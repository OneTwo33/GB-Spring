package ru.onetwo33.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleStartsWith(String prefix);
    List<Product> findByCostGreaterThanEqual(BigDecimal cost);
    List<Product> findByCostLessThanEqual(BigDecimal cost);
    List<Product> findByCostBetween(BigDecimal minCost, BigDecimal maxCost);
}
