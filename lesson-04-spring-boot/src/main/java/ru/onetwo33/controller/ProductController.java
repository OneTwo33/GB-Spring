package ru.onetwo33.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.onetwo33.persist.Product;
import ru.onetwo33.persist.ProductRepository;
import ru.onetwo33.persist.ProductSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("titleFilter") Optional<String> titleFilter,
                           @RequestParam("minCostFilter") Optional<BigDecimal> minCostFilter,
                           @RequestParam("maxCostFilter") Optional<BigDecimal> maxCostFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sort") Optional<String> sort) {
        logger.info("Product list page requested");

        Specification<Product> spec = Specification.where(null);
        if (titleFilter.isPresent() && !titleFilter.get().isEmpty()) {
            spec = spec.and(ProductSpecification.titlePrefix(titleFilter.get()));
        }
        if (minCostFilter.isPresent()) {
            spec = spec.and(ProductSpecification.minCost(minCostFilter.get()));
        }
        if (maxCostFilter.isPresent()) {
            spec = spec.and(ProductSpecification.maxCost(maxCostFilter.get()));
        }

        model.addAttribute("products", productRepository.findAll(spec,
                PageRequest.of(page.orElse(1) - 1, size.orElse(10), Sort.by(sort.orElse("id")))));
        model.addAttribute("page", page.orElse(1));
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        logger.info("New product page requested");

        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Edit product page requested");
        model.addAttribute("product", productRepository.findById(id));

        return "product_form";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Delete product page requested");
        model.addAttribute("product", productRepository.findById(id));

        return "product_delete";
    }

    @PostMapping
    public String update(Product product) {
        logger.info("Saving product");

        productRepository.save(product);
        return "redirect:/product";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Delete product");

        productRepository.deleteById(id);
        return "redirect:/product";
    }
}
