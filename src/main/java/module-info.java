module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires com.google.gson;
    requires org.json;
    requires java.desktop;

    opens ui to javafx.fxml;
    exports ui;
    exports ui.mainMenu;
    opens ui.mainMenu to javafx.fxml;
    exports ui.lobby;
    opens ui.lobby to javafx.fxml;
    exports ui.navigator;
    opens ui.navigator to javafx.fxml;

    opens engine.model.tile to com.google.gson;
    opens engine.state to com.google.gson;
    opens utils to com.google.gson;
    opens utils.boardUtils to com.google.gson;
}