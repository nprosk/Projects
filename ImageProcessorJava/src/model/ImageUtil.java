package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format to a MyImage.
   * @param filename the path of the file.
   */
  public static MyImage readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalArgumentException("Bad file name.");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    MyImage img = new MyImage(width, height);
    int maxValue = sc.nextInt();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        RGB rgb = new RGB(r, g, b);
        img.insertPixel(new MyPixel(i, j, rgb));
      }
    }

    return img;
  }

  /**
   * reads an image in a standard format to a MyImage.
   * @param filename name of the file
   * @return MyImage representing the image
   * @throws IllegalArgumentException if the image cannot be read
   */
  public static MyImage readFile(String filename) throws IllegalArgumentException {

    File imageFile = new File(filename);

    try {
      ImageInputStream input = ImageIO.createImageInputStream(imageFile);
      BufferedImage bufferedImage = ImageIO.read(input);
      return bufferedImageToMyImage(bufferedImage);


    }
    catch (IOException e) {
      throw new IllegalArgumentException("Bad filename");
    }
  }

  //turns a BufferedImage to a MyImage
  private static MyImage bufferedImageToMyImage(BufferedImage bufferedImage) {
    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();

    MyImage image = new MyImage(height, width);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        int rgb = bufferedImage.getRGB(col, row);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        image.insertPixel(new MyPixel(row, col, new RGB(red, green, blue)));
      }
    }
    return image;
  }
}

