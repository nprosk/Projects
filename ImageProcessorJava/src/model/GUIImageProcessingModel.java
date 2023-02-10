package model;

import java.awt.Image;

/**
 * Represents operations that an image processing model designed for GUI's can perform.
 */
public interface GUIImageProcessingModel extends ImageProcessingModel {

  /**
   * Returns the image format of the reference name given.
   * @param name the reference name for the image to grab
   * @return an image that contains the data of the given reference name image
   */
  Image getBufferedImage(String name);

  /**
   * Gets the frequencies of each pixel value (0,255) of the specified color in the specified image
   * by reference name. The frequency of each pixel is placed in its respective value index in
   * the return array.
   * @param name the reference name of the image to get the histogram data for.
   * @param color the color to get the data from
   * @return an integer array where each index is the frequency of pixels that share their RGB value
   */
  int[] getHistogramColor(String name, RGB.ColorType color);

  /**
   * Gets the frequencies of each pixel value (0,255) of the intensity in the specified image
   * by reference name.
   * @param name the reference name of the image to get the data from
   * @return an integer array where each index is the frequency of pixels that share their intensity
   */
  int[] getHistogramIntensity(String name);


  /**
   * Scales an image down by the given scale factor.
   * @param name the reference name of the resulting image
   * @param original the reference name of the image to modify
   * @param scaleFactor the factor by which to scale the original image
   */
  void scale(String name, String original, double scaleFactor);
}
