package com.imagesaver.controller;

import com.imagesaver.model.ImageModel;
import com.imagesaver.util.PreviewManager;
import com.imagesaver.view.PreviewPanel;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PreviewHandler {
    private final PreviewPanel previewPanel;
    private final PreviewManager previewManager;
    private final ImageModel imageModel;
    private final String fontFamily;
    private final int fontSize;

    public PreviewHandler(ImageModel imageModel, String fontFamily, int fontSize) {
        this.imageModel = imageModel;
        this.previewPanel = new PreviewPanel();
        this.previewManager = new PreviewManager(imageModel);
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }

    public PreviewPanel getPreviewPanel() {
        return previewPanel;
    }

    public void setupPreviewListeners(ImageView mainImageView, TextArea notesArea) {
        // Update preview when image changes
        mainImageView.imageProperty().addListener((obs, oldImage, newImage) -> {
            updatePreview(newImage, notesArea.getText());
        });

        // Update preview when text changes
        notesArea.textProperty().addListener((obs, oldText, newText) -> {
            updatePreview(mainImageView.getImage(), newText);
        });
    }

    private void updatePreview(Image sourceImage, String notes) {
        if (sourceImage != null) {
            Image previewImage = previewManager.generatePreview(
                    sourceImage,
                    notes,
                    fontFamily,
                    fontSize);
            previewPanel.updatePreview(previewImage);
        } else {
            previewPanel.clear();
        }
    }
}