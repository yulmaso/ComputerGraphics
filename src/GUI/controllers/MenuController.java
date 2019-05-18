package GUI.controllers;

import GUI.MainWindow;
import ThreadHandler.ThreadHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {
    //private static ThreadHandler startRoot;
    private static MainWindow mainWindow;

    public MenuBar menuBar = new MenuBar();

    public Menu fileMenu = new Menu();
    public Menu openMenu = new Menu();
    public Menu helpMenu = new Menu();

    public MenuItem closeItem = new MenuItem();
    public MenuItem aboutItem = new MenuItem();

    public RadioMenuItem labOne = new RadioMenuItem();
    public RadioMenuItem labTwo = new RadioMenuItem();
    public RadioMenuItem labThree = new RadioMenuItem();
    public RadioMenuItem labFour = new RadioMenuItem();
    public RadioMenuItem labFive = new RadioMenuItem();
    public RadioMenuItem labSix = new RadioMenuItem();

    private ToggleGroup group = new ToggleGroup();

    @FXML
    public void initialize(){
        labOne.setToggleGroup(group);
        labTwo.setToggleGroup(group);
        labThree.setToggleGroup(group);
        labFour.setToggleGroup(group);
        labFive.setToggleGroup(group);
        labSix.setToggleGroup(group);
        labOne.setSelected(true);

        closeItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        labOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { mainWindow.showLabOneOverview(); }
        });

        labTwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { mainWindow.showLabTwoOverview(); }
        });

        labThree.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainWindow.showLabThreeOverview();
            }
        });

        labFour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainWindow.showLabFourOverview();
            }
        });

        labFive.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.showLabFiveOverview();
            }
        });

        labSix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainWindow.showLabSixOverview();
            }
        });
    }

    public void setStartRoot( MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
}
