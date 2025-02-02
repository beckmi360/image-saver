package com.imagesaver.util;

import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;

public class ImageUtils {

    public static Image getClipboardImage() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (clipboard.hasImage()) {
            return clipboard.getImage();
        }
        return null;
    }

    public static boolean saveImage(Image image, String filename) {
        try {
            // Get Documents folder path and create ImageSaver directory
            String documentsPath = System.getProperty("user.home") + File.separator + "Documents" + File.separator
                    + "ImageSaver";
            File directory = new File(documentsPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create file in the ImageSaver directory
            File output = new File(directory, filename + ".png");

            return ImageIO.write(
                    SwingFXUtils.fromFXImage(image, null),
                    "png",
                    output);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}