package com.imagesaver.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;

public class TextRenderer {
    private static final int PADDING = 40;
    private static final Color TEXT_BG_COLOR = Color.rgb(32, 32, 32); // Dark background
    private static final Color TEXT_COLOR = Color.rgb(220, 220, 220); // Light grey text

    public double calculateNotesHeight(String notes, String fontFamily, int fontSize, double availableWidth) {
        if (notes == null || notes.trim().isEmpty()) {
            return 0;
        }

        double lineHeight = fontSize * 1.5;
        List<String> wrappedLines = wrapText(notes, availableWidth - (2 * PADDING), fontFamily, fontSize);
        return (wrappedLines.size() * lineHeight) + (3 * PADDING);
    }

    public void drawNotes(GraphicsContext gc, String notes, double yOffset,
            double width, String fontFamily, int fontSize, int borderThickness) {
        if (notes == null || notes.trim().isEmpty()) {
            return;
        }

        // Draw dark background for text area
        gc.setFill(TEXT_BG_COLOR);
        double notesHeight = calculateNotesHeight(notes, fontFamily, fontSize, width);
        gc.fillRect(borderThickness, yOffset, width, notesHeight);

        // Set up text properties with light color
        gc.setFill(TEXT_COLOR);
        gc.setFont(Font.font(fontFamily, FontWeight.NORMAL, fontSize));

        double lineHeight = fontSize * 1.5;
        double y = yOffset + (1.5 * PADDING);

        List<String> wrappedLines = wrapText(notes, width - (2 * PADDING), fontFamily, fontSize);
        for (String line : wrappedLines) {
            gc.fillText(line, borderThickness + PADDING, y);
            y += lineHeight;
        }
    }

    private List<String> wrapText(String text, double maxWidth, String fontFamily, int fontSize) {
        List<String> lines = new ArrayList<>();
        Text textNode = new Text();
        textNode.setFont(Font.font(fontFamily, FontWeight.NORMAL, fontSize));

        String[] paragraphs = text.split("\n");

        for (String paragraph : paragraphs) {
            if (paragraph.trim().isEmpty()) {
                lines.add("");
                continue;
            }

            StringBuilder currentLine = new StringBuilder();
            String[] words = paragraph.split(" ");

            for (String word : words) {
                String potentialLine = currentLine.length() > 0
                        ? currentLine + " " + word
                        : word;

                textNode.setText(potentialLine);

                if (textNode.getLayoutBounds().getWidth() <= maxWidth) {
                    currentLine = new StringBuilder(potentialLine);
                } else {
                    if (currentLine.length() > 0) {
                        lines.add(currentLine.toString());
                        currentLine = new StringBuilder(word);
                    } else {
                        lines.add(word);
                    }
                }
            }

            if (currentLine.length() > 0) {
                lines.add(currentLine.toString());
            }
        }

        return lines;
    }
}