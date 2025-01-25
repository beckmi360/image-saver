package com.imagesaver.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BorderRenderer {
    private static final int BORDER_SIZE = 8;
    private static final Color BORDER_BASE = Color.rgb(160, 160, 160);
    private static final Color BORDER_HIGHLIGHT = Color.rgb(200, 200, 200);
    private static final Color BORDER_SHADOW = Color.rgb(100, 100, 100);

    public int getTotalBorderThickness() {
        return BORDER_SIZE;
    }

    public void drawBorderedBackground(GraphicsContext gc, double width, double height) {
        // Draw main border area
        gc.setFill(BORDER_BASE);
        gc.fillRect(0, 0, width, height);

        double lineWidth = 2.0;
        gc.setLineWidth(lineWidth);

        // Light edges (top and left)
        gc.setStroke(BORDER_HIGHLIGHT);
        gc.strokeLine(0, height - lineWidth / 2, 0, lineWidth / 2); // Left edge
        gc.strokeLine(lineWidth / 2, 0, width - lineWidth / 2, 0); // Top edge

        // Shadow edges (bottom and right)
        gc.setStroke(BORDER_SHADOW);
        gc.strokeLine(width - lineWidth / 2, 0, width - lineWidth / 2, height - lineWidth / 2); // Right edge
        gc.strokeLine(0, height - lineWidth / 2, width - lineWidth / 2, height - lineWidth / 2); // Bottom edge
    }

    public void drawDivider(GraphicsContext gc, double x, double y, double width) {
        drawDivider(gc, x, y, width, BORDER_SIZE);
    }

    public void drawDivider(GraphicsContext gc, double x, double y, double width, double dividerHeight) {
        // Center the divider vertically on y position
        double yStart = y - (dividerHeight / 2.0);

        // Draw base color
        gc.setFill(BORDER_BASE);
        gc.fillRect(x, yStart, width, dividerHeight);

        double lineWidth = 2.0;
        gc.setLineWidth(lineWidth);

        // Draw highlight at top
        gc.setStroke(BORDER_HIGHLIGHT);
        gc.strokeLine(x, yStart + lineWidth / 2, x + width - lineWidth, yStart + lineWidth / 2);

        // Draw shadow at bottom
        gc.setStroke(BORDER_SHADOW);
        gc.strokeLine(x, yStart + dividerHeight - lineWidth / 2, x + width - lineWidth,
                yStart + dividerHeight - lineWidth / 2);
    }
}