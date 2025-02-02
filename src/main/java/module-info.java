module com.imagesaver {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.graphics;
    requires java.desktop;

    opens com.imagesaver.controller to javafx.fxml;
    opens com.imagesaver.view to javafx.fxml;
    opens com.imagesaver.model to javafx.fxml;
    opens com.imagesaver to javafx.fxml;

    exports com.imagesaver;
    exports com.imagesaver.controller;
    exports com.imagesaver.view;
    exports com.imagesaver.model;
    exports com.imagesaver.util;
}