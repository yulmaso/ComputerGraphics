package GUI.controllers;

import ThreadHandler.ThreadHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lab4.Segment;
import lab4.Settings;
import lab4.Scene4;

import javax.swing.*;
import java.util.List;

public class LabFourController {
    public TextField numberTextField;
    public Button startButton;
    public Slider widthSlider;
    public Slider heightSlider;
    public AnchorPane toolsPane;
    private Settings settings;

    public List<Segment> getSegments() {
        return settings.getSegments();
    }

    public Scene4 getWindow() {
        return settings.getWindow();
    }

    @FXML
    private void initialize(){
        toolsPane.setVisible(false);

        widthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double k = widthSlider.getValue();
                settings.getWindow().setWidth((double)widthSlider.getValue()/100);
            }
        });

        heightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double k = heightSlider.getValue();
                settings.getWindow().setHeight((double)heightSlider.getValue()/100);
            }
        });
    }

    @FXML
    private void startButtonClick(){
        if (!numberTextField.getText().equals("")) {
            if (!(Integer.parseInt(numberTextField.getText()) < 1 || Integer.parseInt(numberTextField.getText()) > 100)) {
                settings = new Settings(Integer.parseInt(numberTextField.getText()), (double) widthSlider.getValue() / 100, (double) heightSlider.getValue() / 100);
                ThreadHandler.startFourthScene(this);
                toolsPane.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Допустимое количество отрезков - [1 - 100]","Ошибка",2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Введите данные","Ошибка",2);
        }
    }

}
