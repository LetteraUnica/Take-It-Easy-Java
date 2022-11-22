package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.navigator.Navigator;
import utils.NavigationConstants;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Navigator navigator = new Navigator();
        navigator.navigateTo(primaryStage, NavigationConstants.STARTING_MENU_FXML);
    }
}
