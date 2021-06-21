package ru.onetwo33;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
//        CameraRoll roll1 = new ColorCameraRoll();
//        CameraRoll roll2 = new BlackAndWhiteCameraRoll();
//        Camera camera = new Camera(roll2);
//        camera.doPhotography();

        // ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Camera camera = context.getBean("camera", Camera.class);
        camera.doPhotography();
    }
}
