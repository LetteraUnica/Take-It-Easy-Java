package ui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws IOException {
        primaryStage.setResizable(false);
        setLogo(primaryStage);
        Navigator navigator = new Navigator();
        navigator.navigateTo(primaryStage, NavigationConstants.MAIN_MENU_FXML);
    }

    private void setLogo(@NotNull Stage primaryStage) {
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("icon.png")).toString());
        primaryStage.getIcons().add(icon);
    }
}
