package com.imagesaver.controller;

import com.imagesaver.model.ImageModel;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class ImageController {
    private ImageView imagePreview;
    private final ImageModel model = new ImageModel();

    public void setImageView(ImageView imagePreview) {
        this.imagePreview = imagePreview;
    }

    public ImageModel getImageModel() {
        return model;
    }

    public void handlePaste() {
        if (imagePreview != null) {
            model.pasteFromClipboard().ifPresent(image -> {
                imagePreview.setImage(image);
            });
        }
    }

    public boolean handleSave(String filename, String notes, String fontFamily, int fontSize) {
        if (filename == null || filename.trim().isEmpty()) {
            showAlert("Error", "Please enter a filename!");
            return false;
        }

        if (!model.hasImage()) {
            showAlert("Error", "No image to save!");
            return false;
        }

        boolean success = model.saveImageWithNotes(filename, notes, fontFamily, fontSize);
        if (success) {
            showAlert("Success", "Image saved successfully as: " + filename + ".png");
            model.clearCurrentImage(); // Clear the model's image reference
        } else {
            showAlert("Error", "Failed to save image!");
        }
        return success;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}