package ru.onetwo33.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.onetwo33.persist.Product;
import ru.onetwo33.persist.ProductRepository;

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
                           @RequestParam("betweenMinCostFilter") Optional<BigDecimal> betweenMinCostFilter,
                           @RequestParam("betweenMaxCostFilter") Optional<BigDecimal> betweenMaxCostFilter) {
        logger.info("Product list page requested");

        List<Product> products;

        if (titleFilter.isPresent()) {
            products = productRepository.findByTitleStartsWith(titleFilter.get());
        } else if (minCostFilter.isPresent()) {
            products = productRepository.findByCostGreaterThanEqual(minCostFilter.get());
        } else if (maxCostFilter.isPresent()) {
            products = productRepository.findByCostLessThanEqual(maxCostFilter.get());
        } else if (betweenMaxCostFilter.isPresent() && betweenMinCostFilter.isPresent()) {
            products = productRepository.findByCostBetween(betweenMinCostFilter.get(), betweenMaxCostFilter.get());
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
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
