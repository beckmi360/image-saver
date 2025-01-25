package com.imagesaver.view;

import com.imagesaver.controller.ImageController;
import com.imagesaver.controller.PreviewHandler;
import com.imagesaver.util.StyleUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ImageSaverUI {
    private final ImageController controller;
    private PreviewHandler previewHandler;
    private PastePanel pastePanel;
    private NotesPanel notesPanel;
    private TextField filenameField;

    public static final String FONT_FAMILY = "Consolas";
    public static final int FONT_SIZE = 22;

    public ImageSaverUI() {
        this.controller = new ImageController();
    }

    public VBox createContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #C0C0C0;");

        // Initialize preview handler and panels
        previewHandler = new PreviewHandler(controller.getImageModel(), FONT_FAMILY, FONT_SIZE);
        pastePanel = new PastePanel(controller);
        notesPanel = new NotesPanel(FONT_FAMILY, FONT_SIZE);
        setupFilenameField();

        // Create main layout
        VBox mainContent = new VBox(10);
        mainContent.setFillWidth(true);
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        // Create top section with paste area and preview
        HBox topSection = createTopSection();
        VBox.setVgrow(topSection, Priority.ALWAYS);

        // Add all components
        mainContent.getChildren().addAll(
                filenameField,
                topSection,
                notesPanel,
                createSaveButton());

        root.getChildren().add(mainContent);

        // Setup preview listeners
        previewHandler.setupPreviewListeners(pastePanel.getImagePreview(), notesPanel.getNotesArea());

        return root;
    }

    private HBox createTopSection() {
        HBox topSection = new HBox(0);
        topSection.setAlignment(Pos.CENTER);
        topSection.setFillHeight(true);

        // Create containers for paste area and preview
        StackPane leftSection = new StackPane(pastePanel);

        // Create separator
        Separator separator = new Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setStyle("-fx-background-color: transparent;" +
                "-fx-border-color: #808080;" +
                "-fx-border-width: 0 1 0 0;" +
                "-fx-border-style: solid;" +
                "-fx-padding: 0 1 0 0;");

        StackPane rightSection = previewHandler.getPreviewPanel();

        // Set up HBox constraints for 70/30 split
        HBox.setHgrow(leftSection, Priority.ALWAYS);
        HBox.setHgrow(rightSection, Priority.ALWAYS);

        // Set width constraints using percentages
        leftSection.prefWidthProperty().bind(topSection.widthProperty().multiply(0.7));
        rightSection.prefWidthProperty().bind(topSection.widthProperty().multiply(0.3));

        // Set height constraints
        leftSection.prefHeightProperty().bind(topSection.heightProperty());
        rightSection.prefHeightProperty().bind(topSection.heightProperty());

        topSection.getChildren().addAll(leftSection, separator, rightSection);
        return topSection;
    }

    private void setupFilenameField() {
        filenameField = new TextField();
        filenameField.setPromptText("Enter filename (without extension)");
        filenameField.setMaxWidth(Double.MAX_VALUE);
        filenameField.setPrefHeight(40);
        StyleUtils.applyTextFieldStyle(filenameField);
    }

    private Button createSaveButton() {
        Button saveButton = new Button("Save Image");
        StyleUtils.applySaveButtonStyle(saveButton);
        saveButton.setOnAction(e -> handleSave());
        return saveButton;
    }

    private void handleSave() {
        String filename = filenameField.getText();
        String notes = notesPanel.getNotesArea().getText();
        boolean success = controller.handleSave(filename, notes, FONT_FAMILY, FONT_SIZE);

        if (success) {
            filenameField.clear();
            notesPanel.clear();
            pastePanel.clear();
        }
    }
}