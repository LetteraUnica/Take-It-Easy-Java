package ui.windows.gameOver;

import engine.controller.GameInterface;
import engine.model.board.BoardInterface;
import exceptions.ReassignedControllerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import ui.windows.UIControllerInterface;
import ui.navigator.NavigationConstants;
import ui.navigator.Navigator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static ui.utils.UIUtils.getStage;

public class GameOverController implements UIControllerInterface {
    @FXML
    private Text winnerText;
    private GameInterface gameController;

    @Override
    public void initController(GameInterface gameController) throws ReassignedControllerException {
        if (this.gameController != null) {
            throw new ReassignedControllerException();
        }
        this.gameController = gameController;

        List<String> winners = getWinners();
        String text;
        if (winners.size() == 1) {
            text = "The winner is " + winners.get(0) + " with " + getWinnerScore() + " points!";
        } else {
            text = "The winners are " + winners.stream().reduce((prev, s) -> prev + " " + s).orElse("No winner") + " with " + getWinnerScore() + " points!";
        }

        winnerText.setText(text);
    }

    private List<String> getWinners() {
        int winnerScore = getWinnerScore();

        return gameController
                .getPlayers()
                .stream()
                .filter(board -> board.getScore() == winnerScore)
                .map(BoardInterface::getNickname)
                .toList();
    }

    private int getWinnerScore() {
        return Collections.max(gameController.getScores());
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
