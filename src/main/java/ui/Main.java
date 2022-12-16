package ui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setLogo(primaryStage);
        Navigator navigator = new Navigator();
        navigator.navigateTo(primaryStage, NavigationConstants.STARTING_MENU_FXML);
    }

    private void setLogo(Stage primaryStage) {
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("icon.png")).toString());
        primaryStage.getIcons().add(icon);
    }
}
