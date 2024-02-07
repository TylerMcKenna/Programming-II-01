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

    public static BufferedImage brightnessFilter(BufferedImage img) {

        // make a new BufferedImage to edit
        BufferedImage brightImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        // initialize variables for all the RGBA
        double r = 0;
        double g = 0;
        double b = 0;
        int a = 0;

        // loop through all the pixels
        for (int y = 0; y <img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                // get the RGBA of the current pixel in a color to use the get color functions
                int pixel = img.getRGB(x,y);
                Color oldColor = new Color(pixel);

                // multiply all the values to get them closer to white
                r = (oldColor.getRed() * 1.5);
                // limits the value to not go past the maximum 255
                if (r > 255) {
                    r = 255;
                }
                g = oldColor.getGreen() * 1.5;
                if (g > 255) {
                    g = 255;
                }
                b = oldColor.getBlue() * 1.5;
                if (b > 255) {
                    b = 255;
                }
                a = oldColor.getAlpha();

                // make a new color to put the new values into the pixel
                Color newColor = new Color((int) r, (int) g, (int) b, a);
                brightImage.setRGB(x, y, newColor.getRGB());
            }
        }
        // return the new image
        return brightImage;
    }

    public static BufferedImage flipHFilter(BufferedImage img) {
        // make a new BufferedImage to edit
        BufferedImage flippedHImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int rL = 0;
        int gL = 0;
        int bL = 0;
        int aL = 0;

        int rR = 0;
        int gR = 0;
        int bR = 0;
        int aR = 0;

        int oppositeSidex = 0;

        // loop through all the pixels, but only half the x pixels
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth() / 2; x++) {

                // get the RGBA of the current pixel
                // and the pixel on the opposite side
                // in a color to use the get color functions
                int pixelLeft = img.getRGB(x,y);
                Color oldColorLeft = new Color(pixelLeft);

                // make a value for the opposite side pixel
                oppositeSidex = img.getWidth() - 1 - x;

                int pixelRight = img.getRGB(oppositeSidex, y);
                Color oldColorRight = new Color(pixelRight);

                rL = oldColorLeft.getRed();
                bL = oldColorLeft.getBlue();
                gL = oldColorLeft.getGreen();
                aL = oldColorLeft.getAlpha();

                rR = oldColorRight.getRed();
                bR = oldColorRight.getBlue();
                gR = oldColorRight.getGreen();
                aR = oldColorRight.getAlpha();

                // make a new color to put the new values into the pixel
                Color newColorLeft = new Color(rR, gR, bR, aR);
                flippedHImage.setRGB(x, y, newColorLeft.getRGB());

                Color newColorRight = new Color(rL, gL, bL, aL);
                flippedHImage.setRGB(oppositeSidex, y, newColorRight.getRGB());
            }
        }
        // return the new image
        return flippedHImage;
    }

    public static BufferedImage flipVFilter(BufferedImage img) {
        // make a new BufferedImage to edit
        BufferedImage flippedVImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        int rT = 0;
        int gT = 0;
        int bT = 0;
        int aT = 0;

        int rB = 0;
        int gB = 0;
        int bB = 0;
        int aB = 0;

        int oppositeSidey = 0;

        // loop through all the pixels, but only half the y pixels
        for (int y = 0; y < img.getHeight() / 2; y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                // get the RGBA of the current pixel
                // and the pixel on the opposite side
                // in a color to use the get color functions
                int pixelTop = img.getRGB(x,y);
                Color oldColorLeft = new Color(pixelTop);

                // make a value for the opposite side pixel
                oppositeSidey = img.getHeight() - 1 - y;

                int pixelBottom = img.getRGB(x, oppositeSidey);
                Color oldColorRight = new Color(pixelBottom);

                rT = oldColorLeft.getRed();
                bT = oldColorLeft.getBlue();
                gT = oldColorLeft.getGreen();
                aT = oldColorLeft.getAlpha();

                rB = oldColorRight.getRed();
                bB = oldColorRight.getBlue();
                gB = oldColorRight.getGreen();
                aB = oldColorRight.getAlpha();

                // make a new color to put the new values into the pixel
                Color newColorTop = new Color(rB, gB, bB, aB);
                flippedVImage.setRGB(x, y, newColorTop.getRGB());

                Color newColorBottom = new Color(rT, gT, bT, aT);
                flippedVImage.setRGB(x, oppositeSidey, newColorBottom.getRGB());
            }
        }
        // return the new image
        return flippedVImage;
    }

}
