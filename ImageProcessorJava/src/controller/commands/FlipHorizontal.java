package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command to flip an image horizontally in an ImageProcessingModel.
 */
public class FlipHorizontal implements ImageProcessingCommand {

  private final String name;
  private final String original;

  /**
   * Creates a horizontal flip command.
   * @param name the name to be set
   * @param original the original name
   */
  public FlipHorizontal(String name, String original) {
    this.name = name;
    this.original = original;
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView v) {
    try {
      m.flipHorizontal(this.name, this.original);
      v.renderMessage(name + ": Horizontally flipped " + original + "\n");
    }
    catch (IllegalArgumentException e) {
      try {
        v.renderMessage("Invalid reference name. Try again.\n");
      }
      catch (IOException io) {
        throw new IllegalStateException();
      }
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}