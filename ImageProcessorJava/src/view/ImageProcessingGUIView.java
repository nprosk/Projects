package view;

import java.awt.Image;

import javax.swing.JPanel;


import controller.Features;

/**
 * Represents the operations for a GUI view necessary to make the gui operate.
 */
public interface ImageProcessingGUIView extends ImageProcessingView {

  /**
   * Sets the view's histogram to the given histogram object.
   * @param histogram a histogram
   */
  void setHistogram(JPanel histogram);

  /**
   * Adds necessary action listeners to GUI components that communicate with the controller.
   * @param features a ControllerGUI object
   */
  void addFeatures(Features features);

  /**
   * Sets the image currently being worked on to the displayed image in the GUI.
   * @param image the image to be displayed
   */
  void setDisplayedImage(Image image);

  /**
   * Gets the current image that is selected in the view and being worked on.
   * @return the reference name of the image to be returned
   */
  String getSelectedImage();

  /**
   * Gets the name of the selected operation to be applied to the image.
   * @return the name of the operation
   */
  String getSelectedOperation();

  /**
   * Gets the inputted reference name of the new image to be created/modified.
   * @return a string of the reference name
   */
  String getInputName();

  /**
   * Adds a new reference name/image to the dropdown of images the user can select to work on.
   * @param name the name of the reference to be added
   */
  public void addToDropDown(String name);

}
