package model;

/**
 * Represents a model to load, save, and perform various operations on images.
 */
public interface ImageProcessingModel {

  /**
   * loads an image from a PPM file.
   * @param name name for this image to be referred by
   * @param filename name of the PPM file
   */
  void loadImage(String name, String filename);

  /**
   * visualizes the color of the image with the given name.
   * @param name name for this new image to be referred by
   * @param original name of image to be visualized
   * @param c color to visualize
   */
  void visualizeColor(String name, String original, RGB.ColorType c);

  /**
   * visualizes the value of the image with the given name.
   * @param name name for this new image to be referred by
   * @param original name of image to be visualized
   */
  void visualizeValue(String name, String original);

  /**
   * visualizes the intensity of the image with the given name.
   * @param name name for this new image to be referred by
   * @param original name of image to be visualized
   */
  void visualizeIntensity(String name, String original);

  /**
   * visualizes the luma of the image with the given name.
   * @param name name for this new image to be referred by
   * @param original name of image to be visualized
   */
  void visualizeLuma(String name, String original);

  /**
   * flips the image with the given name horizontally.
   * @param name name for this new image to be referred by
   * @param original name of image to be flipped
   */
  void flipHorizontal(String name, String original);

  /**
   * flips the image with the given name horizontally.
   * @param name name for this new image to be referred by
   * @param original name of image to be flipped
   */
  void flipVertical(String name, String original);

  /**
   * modifies the brightness of the image with the given name.
   * @param name name for this new image to be referred by
   * @param original name of image to be brightened/darkened
   */
  void modifyBrightness(String name, String original, int value);

  /**
   * Saves the image to a file.
   * @param name reference name of the image
   * @param filename name for this new image to be saved as
   */
  void saveFile(String name, String filename);

  /**
   * blurs the image.
   * @param name new reference name for the image
   * @param original reference name for the image to be blurred
   */
  void blur(String name, String original);

  /**
   * sharpens the image.
   * @param name new reference name for the image
   * @param original reference name for the image to be sharpened
   */
  void sharpen(String name, String original);

  /**
   * grayscale version of the image.
   * @param name new reference name for the image
   * @param original reference name for the image to be grayscaled
   */
  void grayscale(String name, String original);

  /**
   * applies sepia filter to the image.
   * @param name new reference name for the image
   * @param original reference name for the image that sepia is applied to
   */
  void sepia(String name, String original);
}
