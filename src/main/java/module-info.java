module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires com.google.gson;
    requires org.json;
    requires java.desktop;
    requires org.jetbrains.annotations;

    exports ui;
    opens ui.windows.mainmenu to javafx.fxml;
    opens ui.windows.gamelobby to javafx.fxml;
    opens ui.windows.gameboard to javafx.fxml;
    opens ui.windows.gameover to javafx.fxml;
    opens ui.navigator to javafx.fxml;

    opens engine.model.tile to com.google.gson;
    opens engine.state to com.google.gson;
    opens utils.boardutils to com.google.gson;
    opens ui to javafx.fxml;
    opens utils.tile to com.google.gson;
    exports ui.windows;
    opens ui.windows to javafx.fxml;
    exports utils.ui;
    opens utils.ui to javafx.fxml;
}