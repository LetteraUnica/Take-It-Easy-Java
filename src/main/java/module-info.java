module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires com.google.gson;
    requires org.json;
    requires java.desktop;

    exports ui;
    opens ui.mainMenu to javafx.fxml;
    opens ui.lobby to javafx.fxml;
    opens ui.mainGame to javafx.fxml;
    opens ui.navigator to javafx.fxml;

    opens engine.model.tile to com.google.gson;
    opens engine.state to com.google.gson;
    opens utils to com.google.gson;
    opens utils.boardutils to com.google.gson;
    opens ui to javafx.fxml;
}