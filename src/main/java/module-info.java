module ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires com.google.gson;
    requires org.json;

    opens ui to javafx.fxml;
    exports ui;
    exports ui.mainMenu;
    opens ui.mainMenu to javafx.fxml;
}