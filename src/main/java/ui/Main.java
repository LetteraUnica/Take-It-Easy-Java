package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.mainMenu.MainMenu;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.showStage(primaryStage);
    }
}
