package ru.onetwo33.homework2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.onetwo33.homework2.persist.Cart;
import ru.onetwo33.homework2.persist.ProductRepository;

@Configuration
@ComponentScan("ru.onetwo33.homework2")
public class AppConfig {

    @Bean
    public Cart cart(ProductRepository productRepository) {
        return new Cart(productRepository);
    }
}
