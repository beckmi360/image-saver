package com.imagesaver.view;

import com.imagesaver.util.StyleUtils;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;

public class NotesPanel extends VBox {
    private final TextArea notesArea;

    public NotesPanel(String fontFamily, int fontSize) {
        setSpacing(0);
        setPadding(new Insets(0));

        notesArea = new TextArea();
        notesArea.setPromptText("Enter notes about the image here");
        notesArea.setWrapText(true);
        notesArea.setPrefRowCount(10);

        notesArea.setStyle(
                "-fx-control-inner-background: #FFFFFF;" +
                        "-fx-text-fill: #000000;" +
                        "-fx-prompt-text-fill: #808080;" +
                        "-fx-font-family: '" + fontFamily + "';" +
                        "-fx-font-size: " + fontSize + "px;" +
                        "-fx-background-insets: 0;" +
                        "-fx-padding: 5;" +
                        "-fx-background-color: #FFFFFF;");

        // Make TextArea fill the width and use available height
        notesArea.prefWidthProperty().bind(widthProperty());
        VBox.setVgrow(notesArea, Priority.ALWAYS);

        getChildren().add(notesArea);
    }

    public TextArea getNotesArea() {
        return notesArea;
    }

    public void clear() {
        notesArea.clear();
    }
}