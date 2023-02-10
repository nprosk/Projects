

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import model.ImageProcessingModel;
import model.ImageUtil;
import model.MyImage;
import model.RGB;

/**
 * model that can edit MyImages and store them to be used again or saved.
 */
public class MockImageProcessingModelHashMap implements ImageProcessingModel {
  protected final Map<String, MyImage> images;

  public MockImageProcessingModelHashMap() {
    this.images = new HashMap<>();
  }

  @Override
  public void loadImage(String name, String filename) throws IllegalArgumentException {
    String fileType = getImageType(filename);
    if (fileType.equals("ppm")) {
      this.images.put(name, ImageUtil.readPPM(filename));
    } else {
      this.images.put(name, ImageUtil.readFile(filename));
    }
  }

  private String getImageType(String filename) {
    String[] split = filename.split("\\.");
    return split[split.length - 1];
  }

  @Override
  public void visualizeColor(String name, String original, RGB.ColorType c)
          throws IllegalArgumentException {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.visualizeColor(c));
  }

  @Override
  public void visualizeValue(String name, String original) throws IllegalArgumentException {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.visualizeValue());
  }

  @Override
  public void visualizeIntensity(String name, String original) throws IllegalArgumentException {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.visualizeIntensity());
  }

  @Override
  public void visualizeLuma(String name, String original) throws IllegalArgumentException {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.visualizeLuma());
  }

  @Override
  public void flipHorizontal(String name, String original) throws IllegalArgumentException {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.flipHorizontal());
  }

  @Override
  public void flipVertical(String name, String original) throws IllegalArgumentException {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.flipVertical());
  }

  @Override
  public void modifyBrightness(String name, String original, int value)
          throws IllegalArgumentException {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.modifyBrightness(value));
  }

  @Override
  public void blur(String name, String original) {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.applyMatrix(new double[][] {{.0625, .125, .0625}
            ,{.125, .25, .125},{.0625, .125, .0625}}));
  }

  @Override
  public void sharpen(String name, String original) {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.applyMatrix(new double[][]{{-.125, -.125, -.125, -.125, -.125}
            , {-.125, .25, .25, .25, -.125}
            , {-.125, .25, 1, .25, -.125}
            , {-.125, .25, .25, .25, -.125}
            , {-.125, -.125, -.125, -.125, -.125}}));
  }

  @Override
  public void grayscale(String name, String original) {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.colorTransform(new double[][]{{.2126, .7152, .0722}
            , {.2126, .7152, .0722}, {.2126, .7152, .0722}}));
  }

  @Override
  public void sepia(String name, String original) {
    MyImage image = images.getOrDefault(original, null);
    checkImage(image);
    this.images.put(name, image.colorTransform(new double[][]{{.393, .769, .189}
            , {.349, .686, .168}, {.272, .534, .131}}));
  }

  @Override
  public void saveFile(String name, String filename) {
    String fileType = getImageType(filename);
    if (fileType.equals("ppm")) {
      this.saveToPPM(name, filename);
    } else {
      saveToFile(name, filename);
    }
  }


  private void saveToPPM(String name, String filename) {

    File saveFile = new File(filename);

    MyImage image = images.get(name);
    try {
      OutputStreamWriter writer = new FileWriter(saveFile);
      writer.write("P3" + System.lineSeparator() + image.getWidth()
              + " " + image.getHeight() + System.lineSeparator() + image.maxValue() +
              System.lineSeparator());
      for (int row = 0; row < image.getHeight(); row++) {
        for (int col = 0; col < image.getWidth(); col++) {
          if (row <= image.getHeight() - 1 || col <= image.getWidth() - 1) {
            writer.write(image.getPixelAt(row, col).getRGB().toString()
                    + System.lineSeparator());
          } else {
            writer.write(image.getPixelAt(row, col).getRGB().toString());
          }
        }
      }
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid path name");
    }
  }

  protected BufferedImage toBufferedImage(String name) {
    MyImage image = this.images.get(name);

    BufferedImage buffIm = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        buffIm.setRGB(c, r, image.getPixelAt(r, c).getRGB().toIntRGB());
      }
    }

    return buffIm;
  }

  private void saveToFile(String name, String filename) {
    String imageType = getImageType(filename);

    File saveFile = new File(filename);
    BufferedImage buffIm = toBufferedImage(name);

    try {
      ImageIO.write(buffIm, imageType, saveFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid path name");
    }

    this.images.put("The file has been saved", new MyImage(10, 10));

  }

  private void checkImage(MyImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Image does not exist in hashmap");
    }
  }

  public Map<String, MyImage> getHashMap() {
    return this.images;
  }

}
