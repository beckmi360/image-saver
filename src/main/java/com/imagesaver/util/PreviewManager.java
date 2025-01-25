package com.imagesaver.util;

import javafx.scene.image.Image;
import com.imagesaver.model.ImageModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PreviewManager {
        private static final double PREVIEW_SCALE = 0.4;
        private final ImageModel imageModel;
        private final TextRenderer textRenderer;
        private final BorderRenderer borderRenderer;

        public PreviewManager(ImageModel imageModel) {
                this.imageModel = imageModel;
                this.textRenderer = new TextRenderer();
                this.borderRenderer = new BorderRenderer();
        }

        public Image generatePreview(Image sourceImage, String notes, String fontFamily, int fontSize) {
                if (sourceImage == null) {
                        return null;
                }

                // Calculate scaled dimensions
                double scaledWidth = sourceImage.getWidth() * PREVIEW_SCALE;
                double scaledHeight = sourceImage.getHeight() * PREVIEW_SCALE;
                int scaledFontSize = (int) (fontSize * PREVIEW_SCALE);
                int borderThickness = borderRenderer.getTotalBorderThickness();

                // Calculate notes height (don't scale it again, as we're using scaled font
                // size)
                double notesHeight = 0;
                if (notes != null && !notes.trim().isEmpty()) {
                        notesHeight = textRenderer.calculateNotesHeight(
                                        notes,
                                        fontFamily,
                                        scaledFontSize,
                                        scaledWidth);
                }

                // Calculate total canvas height including all components
                double totalHeight = scaledHeight + (borderThickness * 2) + // Image height + borders
                                (notesHeight > 0 ? notesHeight + borderThickness : 0); // Notes height + divider if
                                                                                       // present

                // Create canvas with proper dimensions
                Canvas previewCanvas = new Canvas(
                                scaledWidth + (borderThickness * 2),
                                totalHeight);

                GraphicsContext gc = previewCanvas.getGraphicsContext2D();

                // Draw borders and background
                borderRenderer.drawBorderedBackground(gc, previewCanvas.getWidth(), previewCanvas.getHeight());

                // Draw white background for image area
                gc.setFill(Color.WHITE);
                gc.fillRect(
                                borderThickness,
                                borderThickness,
                                scaledWidth,
                                scaledHeight);

                // Draw scaled image
                gc.drawImage(
                                sourceImage,
                                borderThickness,
                                borderThickness,
                                scaledWidth,
                                scaledHeight);

                // Draw divider and notes if present
                if (notesHeight > 0) {
                        double dividerY = borderThickness + scaledHeight;
                        borderRenderer.drawDivider(
                                        gc,
                                        borderThickness,
                                        dividerY,
                                        scaledWidth);

                        // Draw notes below divider
                        textRenderer.drawNotes(
                                        gc,
                                        notes,
                                        scaledHeight + borderThickness,
                                        scaledWidth,
                                        fontFamily,
                                        scaledFontSize,
                                        borderThickness);
                }

                return previewCanvas.snapshot(null, null);
        }
}