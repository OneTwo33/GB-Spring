package ru.onetwo33;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("ru.onetwo33")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Camera camera(CameraRoll cameraRoll) {
        return new Camera(cameraRoll);
    }
}
