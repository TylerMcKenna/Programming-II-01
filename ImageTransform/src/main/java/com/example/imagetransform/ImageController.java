package com.example.imagetransform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import java.nio.Buffer;

public class ImageController {
    @FXML
    private Button btnFile, btnGreyscale, btnInvert, btnMirror, btnSepia, btnButton5;

    @FXML
    private ImageView imageViewOriginal, imageViewNew;

    private BufferedImage img;
    private File file;

    @FXML
    void greyscalePressed(ActionEvent event) throws IOException {
        setImage(TransformImage.greyscaleFilter(ImageIO.read(file)));
        // change this
        btnGreyscale.setDisable(true);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnButton5.setDisable(false);
    }


    @FXML
    void button5Pressed(ActionEvent event) {
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnButton5.setDisable(true);    }

    @FXML
    void invertPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.invert(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(true);
        btnMirror.setDisable(false);
        btnSepia.setDisable(false);
        btnButton5.setDisable(false);
    }

    @FXML
    void mirrorPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.mirrorImageTu(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(true);
        btnSepia.setDisable(false);
        btnButton5.setDisable(false);
    }

    @FXML
    void sepiaPressed(ActionEvent event) throws IOException {
        setImage(TransformImage.sepiaFilter(ImageIO.read(file)));
        btnGreyscale.setDisable(false);
        btnInvert.setDisable(false);
        btnMirror.setDisable(false);
        btnSepia.setDisable(true);
        btnButton5.setDisable(false);
    }

    // Tyler McKenna's code, please do not touch! (excluding the line I specified)
    @FXML
    private void fileButtonPressed(ActionEvent event) throws IOException {
        // Reads in file
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
        btnButton5.setDisable(false);
        btnFile.setDisable(true);
    }

    private void setImage(BufferedImage img) {
        imageViewNew.setImage(convertToFxImage(img));
    }

    @FXML
    public void initialize() {

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