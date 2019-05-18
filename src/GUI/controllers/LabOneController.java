package GUI.controllers;

import GUI.DualTableContainer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lab1.Scene1;

import javax.swing.*;

public class LabOneController {
    public Button startButton;
    public TextField angleField;
    public ComboBox axisChooser;
    public Slider xSlider;
    public Slider ySlider;
    public Slider zSlider;
    public TableView<DualTableContainer> tableView;
    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();
    private ObservableList<DualTableContainer> tableViewData = FXCollections.observableArrayList();
    public TableColumn<DualTableContainer, String> firstColumn;
    public TableColumn<DualTableContainer, String> secondColumn;
    private double x = 0, y = 0, z = 0;

    @FXML
    private void initialize(){
        comboBoxData.add("X");
        comboBoxData.add("Y");
        comboBoxData.add("Z");

        axisChooser.setItems(comboBoxData);
        axisChooser.setPromptText("Выберите ось");

        firstColumn.setCellValueFactory(cellData -> cellData.getValue().xProperty());
        secondColumn.setCellValueFactory(cellData -> cellData.getValue().yProperty());

        xSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double k = xSlider.getValue();
                if (k > x) {
                    Scene1.commitRotation(5, "X");
                } else {
                    Scene1.commitRotation(-5, "X"); }
                x = k;
            }
        });

        ySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double k = ySlider.getValue();
                if (k > y) {
                    Scene1.commitRotation(5, "Y");
                } else {
                    Scene1.commitRotation(-5, "Y"); }
                y = k;
            }
        });

        zSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double k = zSlider.getValue();
                if (k > z) {
                    Scene1.commitRotation(5, "Z");
                } else {
                    Scene1.commitRotation(-5, "Z"); }
                z = k;
            }
        });
    }

    @FXML
    private void startButtonClick(){
        if (!angleField.getText().equals("") || !axisChooser.getSelectionModel().getSelectedItem().toString().equals("")){
            Scene1.commitRotation(Integer.parseInt(angleField.getText()), axisChooser.getSelectionModel().getSelectedItem().toString());
            DualTableContainer container = new DualTableContainer(axisChooser.getSelectionModel().getSelectedItem().toString(), angleField.getText());
            tableViewData.add(container);
            tableView.setItems(tableViewData);
        } else {
            JOptionPane.showMessageDialog(null, "Введите данные","Ошибка",2);
        }
    }
}
