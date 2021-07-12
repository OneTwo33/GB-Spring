package ru.onetwo33;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.onetwo33.util.EntityManagerFactorySingleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("ru.onetwo33")
public class AppConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();
    }
//    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ru.onetwo33.entity");
}
