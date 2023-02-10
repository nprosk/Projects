package controller;

import view.ImageProcessingGUIView;

/**
 * Represents the features of a GUI controller which are called by the view, get information from
 * the model, and return the appropriate response to the view.
 */
public interface Features {

  /**
   * Applies the given command/operation to the given image.
   * @param referenceName the name of the image to apply the operation to.
   * @param commandName the name of the operation to be applied.
   */
  void applyOperation(String referenceName, String commandName);

  /**
   * Sets the displayed image of the GUI to the image of the given reference name.
   * @param name the reference name of the image to be displayed
   */
  void setDisplayedImage(String name);

  /**
   * Loads an image.
   */
  void load();

  /**
   * Saves an image with the given name.
   * @param name the name of the image to be saved
   */
  void save(String name);

  /**
   * Sets the GUI view to be used by the controller.
   * @param view the GUI view to be used
   */
  public void setView(ImageProcessingGUIView view);

}
