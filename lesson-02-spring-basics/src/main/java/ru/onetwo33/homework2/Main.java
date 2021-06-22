package ru.onetwo33.homework2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.onetwo33.AppConfig;
import ru.onetwo33.homework2.persist.Cart;
import ru.onetwo33.homework2.persist.Product;
import ru.onetwo33.homework2.persist.ProductRepository;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository productRepository = context.getBean(ProductRepository.class);
        productRepository.save(new Product(1L, "Product 1", 12.44F));
        productRepository.save(new Product(2L, "Product 2", 13.45F));
        productRepository.save(new Product(3L, "Product 3", 5.67F));
        productRepository.save(new Product(4L, "Product 4", 18.00F));
        productRepository.save(new Product(5L, "Product 5", 11.05F));

        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        List<Cart> carts = new ArrayList<>();

        System.out.println("Example: \n new - create new cart \n add product - cart 1 add 3 \n remove product - cart 1 delete 3 \n \"exit\" for exit \n");

        while (scanner.hasNext()) {
            String s = scanner.nextLine();

            String[] cmds = s.split(" ");

            if ("exit".equals(cmds[0])) {
                scanner.close();
                return;
            }
            if ("new".equals(cmds[0])) {
                carts.add(context.getBean("cart", Cart.class));
            }
            if ("cart".equals(cmds[0])) {
                String cartNum = cmds[1];
                if ("add".equals(cmds[2])) {
                    Cart cart = carts.get(Integer.parseInt(cartNum) - 1);
                    cart.add(Integer.parseInt(cmds[3]));
                }
                if ("delete".equals(cmds[2])) {
                    Cart cart = carts.get(Integer.parseInt(cartNum) - 1);
                    cart.delete(Integer.parseInt(cmds[3]));
                }
            }

            for (Cart cart : carts) {
                System.out.println(cart);
            }
        }
    }
}
