package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command to brighten/darken an image in an ImageProcessingModel.
 */
public class Brighten implements ImageProcessingCommand {

  private final String name;
  private final int increment;
  private final String original;

  /**
   * Brightens the image.
   * @param name the name
   * @param increment the increment
   * @param original the original name
   */
  public Brighten(String name, int increment, String original) {
    this.name = name;
    this.increment = increment;
    this.original = original;
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView v) {
    try {
      m.modifyBrightness(this.name, this.original, this.increment);
      v.renderMessage(name + ": Brightened " + original + " by " + increment + "\n");
    }
    catch (IllegalArgumentException e) {
      try {
        v.renderMessage("Invalid reference name. Try again.\n");
      }
      catch (IOException io) {
        throw new IllegalStateException();
      }
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }


  }
}
