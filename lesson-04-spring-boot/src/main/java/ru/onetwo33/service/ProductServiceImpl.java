package ru.onetwo33.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.onetwo33.controller.ProductListParams;
import ru.onetwo33.persist.Product;
import ru.onetwo33.persist.ProductRepository;
import ru.onetwo33.persist.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findWithFilter(ProductListParams productListParams) {
        Specification<Product> spec = Specification.where(null);
        if (productListParams.getTitleFilter() != null && !productListParams.getTitleFilter().isEmpty()) {
            spec = spec.and(ProductSpecification.titlePrefix(productListParams.getTitleFilter()));
        }
        if (productListParams.getMinCostFilter() != null) {
            spec = spec.and(ProductSpecification.minCost(productListParams.getMinCostFilter()));
        }
        if (productListParams.getMaxCostFilter() != null) {
            spec = spec.and(ProductSpecification.maxCost(productListParams.getMaxCostFilter()));
        }

        if ("desc".equals(productListParams.getDirection())) {
            return productRepository.findAll(spec,
                    PageRequest.of(Optional.ofNullable(productListParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(productListParams.getSize()).orElse(10),
                            Sort.by(Sort.Direction.DESC, Optional.ofNullable(productListParams.getSort()).orElse("id"))));
        } else {
            return productRepository.findAll(spec,
                    PageRequest.of(Optional.ofNullable(productListParams.getPage()).orElse(1) - 1,
                            Optional.ofNullable(productListParams.getSize()).orElse(10),
                            Sort.by(Sort.Direction.ASC, Optional.ofNullable(productListParams.getSort()).orElse("id"))));
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
