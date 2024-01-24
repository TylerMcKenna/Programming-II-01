package com.example.imagetransform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class ImageController {
    @FXML
    private ImageView imageView;

    @FXML
    private Button fileButton;

    @FXML
    public void initialize() {

    }

    // Tyler McKenna's code, please do not touch!
    @FXML
    private void fileButtonPressed(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);

        //Image image = new Image(file.toURI().toString());
        BufferedImage img = ImageIO.read(file);
        // To test your own method edit this line
        BufferedImage newImage = changeAlpha(img, 0.5);
        Image image = convertToFxImage(newImage);
        imageView.setImage(image);
    }

    // Tyler McKenna's code, please do not touch!
    private BufferedImage changeAlpha(BufferedImage img, double transparency) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x,y);
                Color color = new Color(pixel);

                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                alpha = (int)(alpha * transparency);

                Color newPixel = new Color(alpha, red, green, blue);
                img.setRGB(x, y, newPixel.getRGB());
            }
        }
        return img;
    }

    // Tyler McKenna's code, please do not touch!
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
}