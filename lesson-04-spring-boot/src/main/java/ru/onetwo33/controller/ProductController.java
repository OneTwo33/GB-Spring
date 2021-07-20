package ru.onetwo33.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.onetwo33.persist.Product;
import ru.onetwo33.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model, ProductListParams productListParams) {
        logger.info("Product list page requested");

        model.addAttribute("page", productListParams.getPage());
        model.addAttribute("direction", productListParams.getDirection());
        model.addAttribute("products", productService.findWithFilter(productListParams));
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
        model.addAttribute("product", productService.findById(id));

        return "product_form";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Delete product page requested");
        model.addAttribute("product", productService.findById(id));

        return "product_delete";
    }

    @PostMapping
    public String update(Product product) {
        logger.info("Saving product");

        productService.save(product);
        return "redirect:/product";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Delete product");

        productService.deleteById(id);
        return "redirect:/product";
    }
}
