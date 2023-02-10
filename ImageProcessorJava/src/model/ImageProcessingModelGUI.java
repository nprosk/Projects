package model;

import java.awt.Image;

/**
 * An ImageProcessingModel designed to work with a GUI that displays/transmits information
 * from this model. Allows for the modification of images in addition to saving, and loading them.
 */
public class ImageProcessingModelGUI extends ImageProcessingModelMyImage
    implements GUIImageProcessingModel {

  /**
   * Creates the model by calling the super constructor.
   */
  public ImageProcessingModelGUI() {
    super();
  }

  @Override
  public Image getBufferedImage(String name) {
    Image img = super.toBufferedImage(name);
    return img;
  }

  @Override
  public int[] getHistogramColor(String name, RGB.ColorType color) {
    MyImage image = super.images.get(name);
    int[] frequencies = new int[256];
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0;  c < image.getWidth(); c++) {
        frequencies[image.getPixelAt(r, c).getRGB().getColorValue(color)] += 1;
      }
    }
    return frequencies;
  }

  @Override
  public int[] getHistogramIntensity(String name) {
    MyImage image = super.images.get(name);
    int[] frequencies = new int[256];
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0;  c < image.getWidth(); c++) {
        frequencies[image.getPixelAt(r, c).getRGB().intensity()] += 1;
      }
    }
    return frequencies;
  }

  @Override
  public void scale(String name, String original, double scaleFactor) {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.scale(scaleFactor));
  }

}
