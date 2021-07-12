package ru.onetwo33;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.onetwo33.service.ShopService;

public class TestService {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ShopService shopService = context.getBean("shopService", ShopService.class);

        shopService.findProductsByCustomerId(1L);
        shopService.findCustomersByProductId(3L);
    }
}
