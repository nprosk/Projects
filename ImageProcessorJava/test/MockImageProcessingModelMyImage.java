import model.ImageProcessingModel;
import model.RGB;

/**
 * mock model that adds to a log every time one of its methods is called.
 */
public class MockImageProcessingModelMyImage implements ImageProcessingModel {
  StringBuilder log;

  public MockImageProcessingModelMyImage(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void loadImage(String name, String filename) {
    this.log.append(name + " " + filename + "\n");
  }

  @Override
  public void visualizeColor(String name, String original, RGB.ColorType c) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void visualizeValue(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void visualizeIntensity(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void visualizeLuma(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void flipHorizontal(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void flipVertical(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void modifyBrightness(String name, String original, int value) {
    this.log.append(name + " " + original + " " + value + "\n");
  }

  @Override
  public void saveFile(String name, String filename) {
    this.log.append(filename + " " + name + "\n");
  }

  @Override
  public void blur(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void sharpen(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void grayscale(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }

  @Override
  public void sepia(String name, String original) {
    this.log.append(name + " " + original + "\n");
  }
}
