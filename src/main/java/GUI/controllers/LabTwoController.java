package GUI.controllers;

import GUI.DualTableContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lab2.Scene2;

public class LabTwoController {
    public TableView<DualTableContainer> tableView;
    private ObservableList<DualTableContainer> tableViewData = FXCollections.observableArrayList();
    public TableColumn<DualTableContainer, String> firstColumn;
    public TableColumn<DualTableContainer, String> secondColumn;

    @FXML
    private void initialize(){
        Scene2.labTwoController = this;
        firstColumn.setCellValueFactory(cellData -> cellData.getValue().xProperty());
        secondColumn.setCellValueFactory(cellData -> cellData.getValue().yProperty());
    }

    public void refreshData(double X, double Y){
        DualTableContainer container = new DualTableContainer(String.valueOf(X), String.valueOf(Y));
        tableViewData.add(container);
        tableView.setItems(tableViewData);
    }
}
