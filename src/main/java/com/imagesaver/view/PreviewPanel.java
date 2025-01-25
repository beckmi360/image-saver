package com.imagesaver.view;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class PreviewPanel extends StackPane {
    private final ImageView previewImageView;
    private final ScrollPane scrollPane;
    private final VBox contentBox;

    public PreviewPanel() {
        setStyle("-fx-background-color: #303030;");
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Create and configure the ImageView
        previewImageView = new ImageView();
        previewImageView.setPreserveRatio(true);
        previewImageView.setSmooth(true);

        Label previewLabel = new Label("Live Preview");
        previewLabel.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 5;" +
                        "-fx-background-color: #202020;" +
                        "-fx-alignment: center;" +
                        "-fx-max-width: infinity;");

        contentBox = new VBox(0);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.getChildren().add(previewImageView);
        contentBox.setPadding(new Insets(0, 0, 10, 0));

        scrollPane = new ScrollPane(contentBox);
        scrollPane.setStyle(
                "-fx-background: #303030;" +
                        "-fx-border-color: transparent;" +
                        "-fx-background-color: transparent;" +
                        "-fx-padding: 0;");
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Make sure scrollPane uses available space
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.prefWidthProperty().bind(widthProperty());
        scrollPane.prefHeightProperty().bind(heightProperty());

        // Add style class for custom scrollbar styling
        scrollPane.getStyleClass().add("preview-scroll-pane");
        getStylesheets().add(getClass().getResource("/styles/preview.css").toExternalForm());

        VBox layout = new VBox(0);
        layout.getChildren().addAll(previewLabel, scrollPane);

        // Ensure proper expansion
        VBox.setVgrow(layout, Priority.ALWAYS);
        layout.prefWidthProperty().bind(widthProperty());
        layout.prefHeightProperty().bind(heightProperty());

        getChildren().add(layout);
    }

    public void updatePreview(Image previewImage) {
        if (previewImage != null) {
            previewImageView.setImage(previewImage);

            // Calculate the available width accounting for scrollbar and padding
            double availableWidth = getWidth() - 25; // Account for scrollbar width and some padding

            // Set the image width to fit the available space
            previewImageView.setFitWidth(availableWidth);

            // The height will adjust automatically due to preserveRatio being true

            // Ensure content box width matches parent
            contentBox.setPrefWidth(availableWidth);

            // Important: Request layout to ensure proper sizing
            requestLayout();
        } else {
            previewImageView.setImage(null);
        }
    }

    public void clear() {
        previewImageView.setImage(null);
    }
}