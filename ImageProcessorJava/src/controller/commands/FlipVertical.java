package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command to flip an image vertically in an ImageProcessingModel.
 */
public class FlipVertical implements ImageProcessingCommand {

  private final String name;
  private final String original;

  public FlipVertical(String name, String original) {
    this.name = name;
    this.original = original;
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView v) {
    try {
      m.flipVertical(this.name, this.original);
      v.renderMessage(name + ": Vertically flipped " + original + "\n");
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