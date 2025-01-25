package com.imagesaver.view;

import com.imagesaver.controller.ImageController;
import com.imagesaver.util.StyleUtils;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

public class PastePanel extends StackPane {
    private final ImageView imagePreview;
    private final Label instructionLabel;
    private final ImageController controller;
    private static final double MAX_IMAGE_SIZE_PERCENTAGE = 0.9;

    public PastePanel(ImageController controller) {
        this.controller = controller;

        StyleUtils.applyPasteAreaStyle(this);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        imagePreview = new ImageView();
        imagePreview.setPreserveRatio(true);
        imagePreview.setSmooth(true);

        instructionLabel = new Label("Click here and press Ctrl+V to paste image");
        StyleUtils.applyInstructionLabelStyle(instructionLabel);

        setAlignment(Pos.CENTER);

        getChildren().addAll(instructionLabel, imagePreview);

        setupPasteHandlers();
        setupImageListener();

        controller.setImageView(imagePreview);

        widthProperty().addListener((obs, oldWidth, newWidth) -> updateImageSize());
        heightProperty().addListener((obs, oldHeight, newHeight) -> updateImageSize());
    }

    private void updateImageSize() {
        if (imagePreview.getImage() != null) {
            double maxWidth = getWidth() * MAX_IMAGE_SIZE_PERCENTAGE;
            double maxHeight = getHeight() * MAX_IMAGE_SIZE_PERCENTAGE;

            double imageWidth = imagePreview.getImage().getWidth();
            double imageHeight = imagePreview.getImage().getHeight();

            double scale = Math.min(
                    maxWidth / imageWidth,
                    maxHeight / imageHeight);

            scale = Math.min(scale, 1.0);

            imagePreview.setFitWidth(imageWidth * scale);
            imagePreview.setFitHeight(imageHeight * scale);
        }
    }

    private void setupPasteHandlers() {
        setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.V) {
                controller.handlePaste();
            }
        });

        setFocusTraversable(true);
        setOnMouseClicked(event -> requestFocus());
    }

    private void setupImageListener() {
        imagePreview.imageProperty().addListener((obs, oldImage, newImage) -> {
            instructionLabel.setVisible(newImage == null);
            if (newImage != null) {
                updateImageSize();
            }
        });
    }

    public ImageView getImagePreview() {
        return imagePreview;
    }

    public void clear() {
        imagePreview.setImage(null);
    }
}