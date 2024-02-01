package com.example.imagetransform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class ImageController {
    @FXML
    private ImageView imageViewNew;

    @FXML
    private ImageView imageViewOriginal;

    @FXML
    private Button fileButton;

    @FXML
    public void initialize() {

    }

    // Tyler McKenna's code, please do not touch! (excluding the line I specified)
    @FXML
    private void fileButtonPressed(ActionEvent event) throws IOException {
        // Reads in file
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);

        // Gets image from file, displays it on the original view
        Image image = new Image(file.toURI().toString());
        imageViewOriginal.setImage(image);

        BufferedImage img = ImageIO.read(file);
        // To test your own method edit this line
        BufferedImage newImage = greyscaleFilter(img);

        // Converts BufferedImage "newImage" back to image and displays the changes
        imageViewNew.setImage(convertToFxImage(newImage));
    }
    // Tyler McKenna's code, please do not touch!
    // Math found here https://stackoverflow.com/questions/1061093/how-is-a-sepia-tone-created
    private BufferedImage sepiaFilter(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x,y);
                Color color = new Color(pixel);

                int alpha = color.getAlpha();
                int red = (int)((color.getRed() * 0.393) + (color.getGreen() * 0.769) + (color.getBlue() * 0.189));
                int green = (int)((color.getRed() * 0.349) + (color.getGreen() * 0.686) + (color.getBlue() * 0.168));
                int blue = (int)((color.getRed() * 0.272) + (color.getGreen() * 0.534) + (color.getBlue() * 0.131));

                if(red > 255) red = 255;
                if(green > 255) green = 255;
                if(blue > 255) blue = 255;

                Color newPixel = new Color(red, green, blue, alpha);
                img.setRGB(x, y, newPixel.getRGB());
            }
        }
        return img;
    }
    // Tyler McKenna's code, please do not touch!
    // Math found here https://support.ptc.com/help/mathcad/r9.0/en/index.html#page/PTC_Mathcad_Help/example_grayscale_and_color_in_images.html
    private BufferedImage greyscaleFilter(BufferedImage img) {
        // Loops through all the pixels
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                // gets the rgba of pixel at x,y and puts it into a color
                int pixel = img.getRGB(x,y);
                Color color = new Color(pixel);

                // edits the pixel
                int alpha = color.getAlpha();
                int red = (int)(color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
                int green = (int)(color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);
                int blue = (int)(color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);

                // changes the pixel in the image
                Color newPixel = new Color(red, green, blue, alpha);
                img.setRGB(x, y, newPixel.getRGB());
            }
        }
        // return the edited images
        return img;
    }

    // Copied method from stackoverflow
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