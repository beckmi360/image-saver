package com.imagesaver;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.imagesaver.view.ImageSaverUI;

public class ImageSaverApp extends Application {
    private static final double WINDOW_WIDTH = 1200;
    private static final double WINDOW_HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) {
        ImageSaverUI ui = new ImageSaverUI();

        Scene scene = new Scene(ui.createContent(), WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getRoot().setStyle(
                "-fx-base: #C0C0C0;" +
                        "-fx-background: #C0C0C0;" +
                        "-fx-control-inner-background: #FFFFFF;" +
                        "-fx-accent: #000080;" +
                        "-fx-focus-color: #000080;");

        primaryStage.setTitle("Image Save Application");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}