package GUI.controllers;

import ThreadHandler.ThreadHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import lab3.Input;

public class LabSixController {

    public Slider verticalSceneSlider;
    public Slider horizontalSceneSlider;
    public Slider verticalLightSlider;
    public Slider horizontalLightSlider;
    public Slider scaleSlider;

    public float zRotate = 10.0f;
    public float xRotate = 10.0f;
    public float yRotate = 10.0f;
    public float radius = 10.0f;
    public float xzRadius = 10.0f;

    public float xLight = 1.0f;
    public float yLight = 1.0f;
    public float zLight = 1.0f;
    public float lightRadius = 1.0f;
    public float lightXZ = 1.0f;

    //public Input input = new Input();

    @FXML
    public void initialize(){
        ThreadHandler.startSixthScene(this);
        horizontalSceneSlider.setValue(xRotate/xzRadius);
        verticalSceneSlider.setValue(yRotate/radius);
        scaleSlider.setValue(radius);

        horizontalSceneSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xRotate = (float)(Math.cos(Math.toRadians(horizontalSceneSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(horizontalSceneSlider.getValue()))*xzRadius);
            }
        });

        verticalSceneSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xzRadius = (float)(Math.cos(Math.toRadians(verticalSceneSlider.getValue()))*radius);
                yRotate = (float)(Math.sin(Math.toRadians(verticalSceneSlider.getValue()))*radius);
                xRotate = (float)(Math.cos(Math.toRadians(horizontalSceneSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(horizontalSceneSlider.getValue()))*xzRadius);
            }
        });

        verticalLightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                lightXZ = (float)(Math.cos(Math.toRadians(verticalLightSlider.getValue()))*lightRadius);
                yLight = (float)(Math.sin(Math.toRadians(verticalLightSlider.getValue()))*lightRadius);
                xLight = (float)(Math.cos(Math.toRadians(horizontalLightSlider.getValue()))*lightXZ);
                zLight = (float)(Math.sin(Math.toRadians(horizontalLightSlider.getValue()))*lightXZ);
            }
        });

        horizontalLightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xLight = (float)(Math.cos(Math.toRadians(horizontalLightSlider.getValue()))*lightXZ);
                zLight = (float)(Math.sin(Math.toRadians(horizontalLightSlider.getValue()))*lightXZ);
            }
        });

        scaleSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                radius = (float) scaleSlider.getValue();
                xzRadius = (float)(Math.cos(Math.toRadians(verticalSceneSlider.getValue()))*radius);
                yRotate = (float)(Math.sin(Math.toRadians(verticalSceneSlider.getValue()))*radius);
                xRotate = (float)(Math.cos(Math.toRadians(horizontalSceneSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(horizontalSceneSlider.getValue()))*xzRadius);
            }
        });
    }
}
