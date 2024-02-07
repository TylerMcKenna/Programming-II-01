package com.example.imagetransform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TransformImage {
    // Tyler McKenna
    // Math found here https://stackoverflow.com/questions/1061093/how-is-a-sepia-tone-created
    public static BufferedImage sepiaFilter(BufferedImage img) {
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

    // Tyler McKenna
    // https://support.ptc.com/help/mathcad/r9.0/en/index.html#page/PTC_Mathcad_Help/example_grayscale_and_color_in_images.html
    public static BufferedImage greyscaleFilter(BufferedImage img) {
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

    // Henri Junior
    // https://www.geeksforgeeks.org/negative-transformation-of-an-image-using-python-and-opencv/
    public static BufferedImage invert(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x,y);
                Color color = new Color(pixel);

                int alpha = color.getAlpha();
                int red = 255 - color.getRed();
                int green = 255 - color.getGreen();
                int blue = 255 - color.getBlue();

                Color newPixel = new Color(red, green, blue, alpha);
                img.setRGB(x, y, newPixel.getRGB());
            }
        }
        return img;
    }

    // Vojin
    // https://www.tutorialspoint.com/how-to-create-a-mirror-image-using-java-opencv-library
    public static BufferedImage mirrorImageTu(BufferedImage img) {

        // Getting the height and width of the read image.
        int height = img.getHeight();
        int width = img.getWidth();

        // Creating a BufferedImage to store the output
        BufferedImage mirroredImage = new BufferedImage(width, height, img.getType());

        // Creating mirrored image
        for (int y = 0; y < height; y++) {
            for (int x = 0, mirroredX = width - 1; x < width; x++, mirroredX--) {
                int pixel = img.getRGB(x, y);
                mirroredImage.setRGB(mirroredX, y, pixel);
            }
        }
        return mirroredImage;
    }
}
