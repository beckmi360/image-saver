package com.imagesaver.util;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class StyleUtils {
    public static void applyTextFieldStyle(TextField field) {
        field.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-text-fill: #000000;" +
                "-fx-prompt-text-fill: #808080;" +
                "-fx-border-color: #808080 #FFFFFF #808080 #808080;" +
                "-fx-border-width: 2;" +
                "-fx-font-size: 16px;" +
                "-fx-padding: 0 10 0 10;");
    }

    public static void applyPasteAreaStyle(StackPane area) {
        area.setStyle(
                "-fx-border-color: #808080 #FFFFFF #808080 #808080;" +
                "-fx-border-width: 2;" +
                "-fx-background-color: #FFFFFF;");
    }

    public static void applyInstructionLabelStyle(Label label) {
        label.setStyle("-fx-font-size: 16; -fx-text-fill: #000000; -fx-text-alignment: center;");
    }

    public static void applyNotesAreaStyle(TextArea area, String fontFamily, int fontSize) {
        // Set the style for the TextArea itself
        area.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: '" + fontFamily + "';" +
                "-fx-font-size: " + fontSize + "px;" +
                // Remove internal padding
                "-fx-background-insets: 0;" +
                "-fx-padding: 5;" +
                // Set explicit border
                "-fx-border-color: #808080 transparent #808080 #808080;" +
                "-fx-border-width: 2;");

        // Remove default focus highlighting
        area.setFocusTraversable(false);
        
        // Style the content area
        area.lookup(".content").setStyle(
                "-fx-background-color: white;" +
                "-fx-padding: 0;");
        
        // Ensure viewport doesn't add padding
        area.lookup(".viewport").setStyle(
                "-fx-background-color: white;" +
                "-fx-padding: 0;");
    }

    public static void applySaveButtonStyle(Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(40);
        button.setStyle(
                "-fx-font-size: 14;" +
                "-fx-background-color: #C0C0C0;" +
                "-fx-text-fill: #000000;" +
                "-fx-border-color: #FFFFFF #808080 #808080 #FFFFFF;" +
                "-fx-border-width: 2;" +
                "-fx-cursor: hand;" +
                "-fx-background-radius: 0;" +
                "-fx-border-radius: 0;");

        // Add hover effect
        button.setOnMouseEntered(e -> button.setStyle(button.getStyle() + "-fx-background-color: #D3D3D3;"));
        button.setOnMouseExited(e -> button.setStyle(button.getStyle().replace("-fx-background-color: #D3D3D3;",
                "-fx-background-color: #C0C0C0;")));
    }
}