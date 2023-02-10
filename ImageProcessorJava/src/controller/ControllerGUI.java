package controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GUIImageProcessingModel;
import model.RGB;
import view.Histogram;
import view.ImageProcessingGUIView;
import view.ImageProcessingGUIViewImpl;

/**
 * Represents a controller for handling interactions between a gui model and a gui view.
 */
public class ControllerGUI implements Features {

  private final GUIImageProcessingModel model;
  private ImageProcessingGUIView view;

  /**
   * Creates a ControllerGUI with the given GUI model.
   * @param model a gui model that performs image processing operations
   */
  public ControllerGUI(GUIImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    this.model = model;

  }

  /**
   * Sets the view that this controller will use to display information from the model.
   * @param view an ImageProcessingGUIView
   */
  public void setView(ImageProcessingGUIView view) {
    this.view = view;
    this.model.loadImage("cow.jpg", "res/cow.jpg");
    this.view.addFeatures(this);
    this.setDisplayedImage("cow.jpg");
  }

  @Override
  public void applyOperation(String referenceName, String commandName) {
    switch (commandName) {
      case "Operation 0":
        this.model.blur(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 1":
        this.model.flipHorizontal(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 2":
        this.model.flipVertical(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 3":
        this.model.grayscale(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 4":
        this.model.sepia(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 5":
        this.model.sharpen(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 6":
        this.model.visualizeColor(referenceName, this.view.getSelectedImage(), RGB.ColorType.RED);
        break;
      case "Operation 7":
        this.model.visualizeColor(referenceName, this.view.getSelectedImage(), RGB.ColorType.GREEN);
        break;
      case "Operation 8":
        this.model.visualizeColor(referenceName, this.view.getSelectedImage(), RGB.ColorType.BLUE);
        break;
      case "Operation 9":
        this.model.visualizeIntensity(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 10":
        this.model.visualizeLuma(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 11":
        this.model.visualizeValue(referenceName, this.view.getSelectedImage());
        break;
      case "Operation 12":
        this.model.modifyBrightness(referenceName, this.view.getSelectedImage(), 10);
        break;
      case "Operation 13":
        this.model.modifyBrightness(referenceName, this.view.getSelectedImage(), -10);
        break;
      case "Operation 14":
        this.model.scale(referenceName, this.view.getSelectedImage(),
                ((ImageProcessingGUIViewImpl)this.view).getScaleFactor());
        break;
      default:
        // do noting
        break;
    }
    this.view.setDisplayedImage(model.getBufferedImage(referenceName));
    this.view.addToDropDown(referenceName);

  }

  @Override
  public void setDisplayedImage(String name) {
    this.view.setDisplayedImage(this.model.getBufferedImage(name));
    int[] redData = this.model.getHistogramColor(name, RGB.ColorType.RED);
    int[] greenData = this.model.getHistogramColor(name, RGB.ColorType.GREEN);
    int[] blueData = this.model.getHistogramColor(name, RGB.ColorType.BLUE);
    int[] intensityData = this.model.getHistogramIntensity(name);
    this.view.setHistogram(new Histogram(redData, greenData, blueData, intensityData));
  }

  @Override
  public void load() {
    JFileChooser fileChooser = new JFileChooser(".");

    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, PPM, " +
            "GIF, BMP files are supported", "jpg", "gif", "png", "ppm", "bmp");
    fileChooser.setFileFilter(filter);
    int retValue = fileChooser.showOpenDialog((ImageProcessingGUIViewImpl) this.view);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      this.model.loadImage(f.getName(), f.getPath());
      this.view.setDisplayedImage(model.getBufferedImage(f.getName()));
      this.view.addToDropDown(f.getName());
    }

  }

  @Override
  public void save(String name) {

    JFileChooser fileChooser = new JFileChooser(".");
    int retValue = fileChooser.showSaveDialog((ImageProcessingGUIViewImpl) this.view);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      this.model.saveFile(name, f.getPath());
    }
  }
}
