package ui.windows.mainMenu;

import engine.controller.GameControllerInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;
import ui.windows.UIControllerInterface;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static ui.utils.UIUtils.getStage;

public class MainMenuController implements UIControllerInterface {

    public static final String HOW_TO_PLAY_URL = "https://www.ultraboardgames.com/take-it-easy/game-rules.php";

    @Override
    public void initController(GameControllerInterface gameController) {
    }

    @FXML
    public void exitGame() {
        Platform.exit();
    }

    @FXML
    public void howToPlay() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(HOW_TO_PLAY_URL));
    }

    @FXML
    public void startGame(ActionEvent e) throws IOException {
        Navigator navigator = new Navigator();
        navigator.navigateTo(getStage(e), NavigationConstants.LOBBY_FXML);
    }


}
