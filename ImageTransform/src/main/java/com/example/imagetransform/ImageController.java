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
import java.awt.image.BufferedImage;

import java.io.Console;
import java.io.File;
import java.io.IOException;

public class ImageController {
    @FXML
    private Button btnFile, btnGreyscale, btnInvert, btnMirror, btnSepia, btnBrighten, btnFlipH, btnFlipV;

    @FXML
    private ImageView imageViewOriginal, imageViewNew;

    private BufferedImage img;
    private File file;

    // All of these filterPressed functions just disable themselves and enable other buttons
    @FXML
    void greyscalePressed(ActionEvent event) throws IOException {
        setImage(TransformImage.greyscaleFilter(ImageIO.read(file)));
        // this is meh
        btnGreyscale.setDisable(true);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnBrighten.setDisable(false);
        btnFlipH.setDisable(false);
        btnFlipV.setDisable(false);
    }

    @FXML
    void flipHPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.flipHFilter(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnBrighten.setDisable(false);
        btnFlipH.setDisable(true);
        btnFlipV.setDisable(false);
    }

    @FXML
    void flipVPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.flipVFilter(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnBrighten.setDisable(false);
        btnFlipH.setDisable(false);
        btnFlipV.setDisable(true);
    }

    @FXML
    void brightenPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.brightnessFilter(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnBrighten.setDisable(true);
        btnFlipH.setDisable(false);
        btnFlipV.setDisable(false);
    }

    @FXML
    void invertPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.invert(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(true);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnBrighten.setDisable(false);
        btnFlipH.setDisable(false);
        btnFlipV.setDisable(false);
    }

    @FXML
    void mirrorPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.mirrorImageTu(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(true);
        btnSepia.setDisable(false);
        btnBrighten.setDisable(false);
        btnFlipH.setDisable(false);
        btnFlipV.setDisable(false);
    }

    @FXML
    void sepiaPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.sepiaFilter(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(true);
        btnBrighten.setDisable(false);
        btnFlipH.setDisable(false);
        btnFlipV.setDisable(false);
    }

    // This takes in a file and sets the image, then disables the file button and enables the filter buttons
    @FXML
    private void fileButtonPressed(ActionEvent event){
        // Reads in file
        try {
            FileChooser chooser = new FileChooser();
            file = chooser.showOpenDialog(null);

            // Gets image from file, displays it on the original view
            Image image = new Image(file.toURI().toString());
            imageViewOriginal.setImage(image);

            // Sets img for image filters to reference
            img = ImageIO.read(file);

            // Enables the filters and stops user from changing image
            btnGreyscale.setDisable(false);
            btnInvert.setDisable(false);
            btnMirror.setDisable(false);
            btnSepia.setDisable(false);
            btnBrighten.setDisable(false);
            btnFlipH.setDisable(false);
            btnFlipV.setDisable(false);
            btnFile.setDisable(true);
        } catch (Exception exception) {
            System.out.println("There was an issue selecting the image.");
        }
    }

    // Just for readability
    private void setImage(BufferedImage img) {
        imageViewNew.setImage(convertToFxImage(img));
    }

    // Copied method from stackoverflow, converts BufferedImage to Image
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