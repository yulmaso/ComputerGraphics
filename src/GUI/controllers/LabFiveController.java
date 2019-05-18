package GUI.controllers;

import ThreadHandler.ThreadHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

public class LabFiveController {
    public float zRotate = 10.0f;
    public float xRotate = 10.0f;
    public float yRotate = 10.0f;
    public float radius = 10.0f;
    public float xzRadius = 10.0f;

    public int angleX = 0;
    public int angleY = 0;

    public Slider xSlider;
    public Slider ySlider;
    public CheckBox checkBox;

    @FXML
    private void initialize(){
        ThreadHandler.startFifthScene(this);

        xSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xRotate = (float)(Math.cos(Math.toRadians(xSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(xSlider.getValue()))*xzRadius);
                angleX = Math.toIntExact(Math.round(xSlider.getValue()));
            }
        });

        ySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xzRadius = (float)(Math.cos(Math.toRadians(ySlider.getValue()))*radius);
                yRotate = (float)(Math.sin(Math.toRadians(ySlider.getValue()))*radius);
                xRotate = (float)(Math.cos(Math.toRadians(xSlider.getValue()))*xzRadius);
                zRotate = (float)(Math.sin(Math.toRadians(xSlider.getValue()))*xzRadius);
                angleY = Math.toIntExact(Math.round(ySlider.getValue()));
            }
        });
    }

    public boolean showLines(){
        return !checkBox.isSelected();
    }
}
