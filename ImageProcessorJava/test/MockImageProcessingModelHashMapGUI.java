import java.awt.Image;

import model.GUIImageProcessingModel;
import model.MyImage;
import model.RGB;

/**
 * A mock test class for GUI models that allows for the hashmap to be checked.
 */
public class MockImageProcessingModelHashMapGUI extends MockImageProcessingModelHashMap
    implements GUIImageProcessingModel {


  /**
   * Creates the model by calling the super constructor.
   */
  public MockImageProcessingModelHashMapGUI() {
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
    return;
  }
}
