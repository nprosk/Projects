package controller.commands;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Represents a command to be applied to an ImageProcessingModel and ImageProcessingView.
 */
public interface ImageProcessingCommand {
  /**
   * applies the command.
   * @param m model to edit images
   * @param view to show user
   */
  void execute(ImageProcessingModel m, ImageProcessingView view);
}
