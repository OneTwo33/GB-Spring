package ru.onetwo33;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Camera {

    private final CameraRoll cameraRoll;

    @Autowired
    public Camera(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    void doPhotography() {
        cameraRoll.processing();
    }
}
