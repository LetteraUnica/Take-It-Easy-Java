package utils.ui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class UIUtils {
    public static Stage getStage(ActionEvent e) {
        return (Stage) ((Node) e.getSource()).getScene().getWindow();
    }
}
