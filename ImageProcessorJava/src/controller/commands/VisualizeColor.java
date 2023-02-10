package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import model.RGB;
import view.ImageProcessingView;

/**
 * Command to visualize the color of an image in an ImageProcessingModel.
 */
public class VisualizeColor implements ImageProcessingCommand {

  private final String newName;
  private final String originalName;
  private final RGB.ColorType color;

  /**
   * Creates a visualize color command.
   *
   * @param newName      the new name
   * @param originalName the original name
   * @param color        the color
   */
  public VisualizeColor(String newName, String originalName, RGB.ColorType color) {
    this.newName = newName;
    this.originalName = originalName;
    this.color = color;
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView v) {
    try {
      m.visualizeColor(newName, originalName, color);
      v.renderMessage(newName + ": Color visualized " + originalName + "\n");
    } catch (IllegalArgumentException | IOException e) {
      try {
        v.renderMessage("Invalid reference name. Try again.\n");
      } catch (IOException io) {
        throw new IllegalStateException();
      }
    }
  }
}
