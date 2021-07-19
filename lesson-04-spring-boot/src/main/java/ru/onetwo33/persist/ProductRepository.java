package ru.onetwo33.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p where " +
            "(p.title like CONCAT(:prefix, '%') or :prefix is null ) and " +
            "(p.cost >= :minAge or :minAge is null ) and " +
            "(p.cost <= :maxAge or :maxAge is null)")
    List<Product> filterProducts(@Param("prefix") String prefix,
                                 @Param("minAge") BigDecimal minAge,
                                 @Param("maxAge") BigDecimal maxAge);
}
