package ui.navigator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigator {
    public Scene initializeScene(String to) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Navigator.class.getResource(to)));
        return new Scene(root);
    }

    public void navigateTo(Stage stage, String to) throws Exception {
        Scene scene = initializeScene(to);
        stage.setScene(scene);
        stage.show();
    }
}
