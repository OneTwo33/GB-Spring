package ru.onetwo33;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.onetwo33")
public class AppConfig {

    @Bean
    public Camera camera(CameraRoll cameraRoll) {
        return new Camera(cameraRoll);
    }
}
