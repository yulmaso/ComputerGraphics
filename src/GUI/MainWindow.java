package GUI;

import GUI.controllers.MenuController;
import ThreadHandler.ThreadHandler;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainWindow extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Computer Graphics");

        initRootLayout();
        showLabSixOverview();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("layouts/rootLayout.fxml"));
            rootLayout = loader.load();

            MenuController controller = loader.<MenuController>getController();
            controller.setStartRoot(/*startRoot,*/ this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLabOneOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("layouts/labOneLayout.fxml"));
            AnchorPane mainWindow = loader.load();

            rootLayout.setCenter(mainWindow);

            //if (startRoot!=null) startRoot.startFirstScene();
            ThreadHandler.startFirstScene();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLabTwoOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("layouts/labTwoLayout.fxml"));
            AnchorPane mainWindow = loader.load();

            rootLayout.setCenter(mainWindow);

            ThreadHandler.startSecondScene();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLabThreeOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("layouts/labThreeLayout.fxml"));
            AnchorPane mainWindow = loader.load();

            rootLayout.setCenter(mainWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLabFourOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("layouts/labFourLayout.fxml"));
            AnchorPane mainWindow = loader.load();

            rootLayout.setCenter(mainWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLabFiveOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("layouts/labFiveLayout.fxml"));
            AnchorPane mainWindow = loader.load();

            rootLayout.setCenter(mainWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLabSixOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindow.class.getResource("layouts/labSixLayout.fxml"));
            AnchorPane mainWindow = loader.load();

            rootLayout.setCenter(mainWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /*
    @Override
    public void run() {
        thread.start();
        launch();
    }*/
}
