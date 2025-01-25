package com.imagesaver.model;

import com.imagesaver.util.ImageUtils;
import com.imagesaver.util.TextRenderer;
import com.imagesaver.util.BorderRenderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.Optional;

public class ImageModel {
    private Image currentImage;
    private final TextRenderer textRenderer;
    private final BorderRenderer borderRenderer;
    private static final int PADDING = 40;

    public ImageModel() {
        this.textRenderer = new TextRenderer();
        this.borderRenderer = new BorderRenderer();
    }

    public Optional<Image> pasteFromClipboard() {
        currentImage = ImageUtils.getClipboardImage();
        return Optional.ofNullable(currentImage);
    }

    public boolean hasImage() {
        return currentImage != null;
    }

    public void clearCurrentImage() {
        currentImage = null;
    }

    public boolean saveImageWithNotes(String filename, String notes, String fontFamily, int fontSize) {
        if (!hasImage() || filename == null || filename.trim().isEmpty()) {
            return false;
        }

        try {
            Image combinedImage = createCombinedImage(notes, fontFamily, fontSize);
            return ImageUtils.saveImage(combinedImage, filename);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Image createCombinedImage(String notes, String fontFamily, int fontSize) {
        double imageWidth = currentImage.getWidth();
        double imageHeight = currentImage.getHeight();

        // Calculate notes height
        double notesHeight = 0;
        if (notes != null && !notes.trim().isEmpty()) {
            notesHeight = textRenderer.calculateNotesHeight(
                    notes,
                    fontFamily,
                    fontSize,
                    imageWidth);
        }

        int borderThickness = borderRenderer.getTotalBorderThickness();

        // Create canvas with space for divider
        Canvas canvas = new Canvas(
                imageWidth + (borderThickness * 2),
                imageHeight + notesHeight + (borderThickness * 2) + (notesHeight > 0 ? borderThickness : 0));
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw outer border and background
        borderRenderer.drawBorderedBackground(gc, canvas.getWidth(), canvas.getHeight());

        // Draw white background for image area
        gc.setFill(Color.WHITE);
        gc.fillRect(
                borderThickness,
                borderThickness,
                imageWidth,
                imageHeight);

        // Draw the image
        gc.drawImage(
                currentImage,
                borderThickness,
                borderThickness,
                imageWidth,
                imageHeight);

        // Draw divider and notes if present
        if (notesHeight > 0) {
            // Position divider
            double dividerY = borderThickness + imageHeight + (borderThickness / 2);
            borderRenderer.drawDivider(
                    gc,
                    borderThickness,
                    dividerY,
                    imageWidth);

            // Draw notes below divider
            textRenderer.drawNotes(
                    gc,
                    notes,
                    imageHeight + borderThickness * 2,
                    imageWidth,
                    fontFamily,
                    fontSize,
                    borderThickness);
        }

        return canvas.snapshot(null, null);
    }
}