package GUI.controllers;

import ThreadHandler.ThreadHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import lab3.Input;

public class LabThreeController {
    public Slider xSlider;
    public Slider ySlider;
    public Slider zSlider;
    public Slider scaleSlider;
    public Label zSliderLabel;

    public static float zRotate = 10.0f;
    public static float xRotate = 10.0f;
    public static float yRotate = 10.0f;
    public static float radius = 10.0f;
    public static float xzRadius = 10.0f;

    public Input input = new Input();

    @FXML
    public void initialize(){
        ThreadHandler.startThirdScene(this);
        zSlider.setVisible(false);
        zSliderLabel.setVisible(false);

        xSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xRotate = (float)(Math.cos(Math.toRadians(xSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(xSlider.getValue()))*xzRadius);
            }
        });

        ySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xzRadius = (float)(Math.cos(Math.toRadians(ySlider.getValue()))*radius);
                yRotate = (float)(Math.sin(Math.toRadians(ySlider.getValue()))*radius);
                xRotate = (float)(Math.cos(Math.toRadians(xSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(xSlider.getValue()))*xzRadius);
            }
        });

        zSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });

        scaleSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                radius = (float)scaleSlider.getValue();
                xzRadius = (float)(Math.cos(Math.toRadians(ySlider.getValue()))*radius);
                yRotate = (float)(Math.sin(Math.toRadians(ySlider.getValue()))*radius);
                xRotate = (float)(Math.cos(Math.toRadians(xSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(xSlider.getValue()))*xzRadius);
            }
        });
    }


}
