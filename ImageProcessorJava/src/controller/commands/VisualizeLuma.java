package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command to visualize the luma of an image in an ImageProcessingModel.
 */
public class VisualizeLuma implements ImageProcessingCommand {

  private final String name;
  private final String original;

  public VisualizeLuma(String name, String original) {
    this.name = name;
    this.original = original;
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView v) {
    try {
      m.visualizeLuma(this.name, this.original);
      v.renderMessage(name + ": Visualized luma of " + original + "\n");
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