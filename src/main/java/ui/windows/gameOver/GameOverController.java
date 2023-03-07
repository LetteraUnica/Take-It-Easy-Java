package ui.windows.gameOver;

import engine.controller.GameControllerInterface;
import exceptions.ReassignedControllerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;
import ui.windows.UIControllerInterface;

import java.io.IOException;
import java.util.List;

import static ui.utils.UIUtils.getStage;

public class GameOverController implements UIControllerInterface {
    @FXML
    private Text winnerText;
    private GameControllerInterface gameController;

    @Override
    public void initController(GameControllerInterface gameController) throws ReassignedControllerException {
        if (this.gameController != null) {
            throw new ReassignedControllerException();
        }
        this.gameController = gameController;

        initWinnerText();
    }

    private void initWinnerText() {
        winnerText.setText(getWinText());
    }

    @NotNull
    private String getWinText() {
        List<String> winners = gameController.getGameWinners();
        int winnerScore = gameController.getWinnersScore();
        String text;
        if (winners.size() == 1) {
            text = "The winner is " + winners.get(0) + " with " + winnerScore + " points!";
        } else {
            text = "The winners are " + winners.stream().reduce((prev, s) -> prev + " " + s) + " with " + winnerScore + " points!";
        }
        return text;
    }

    @FXML
    void returnToMainMenu(ActionEvent e) throws IOException {
        Navigator navigator = new Navigator();
        navigator.navigateTo(getStage(e), NavigationConstants.MAIN_MENU_FXML);
    }

    @FXML
    public void returnToLobby(ActionEvent e) throws IOException {
        Navigator navigator = new Navigator();
        navigator.navigateTo(getStage(e), NavigationConstants.LOBBY_FXML);
    }
}
