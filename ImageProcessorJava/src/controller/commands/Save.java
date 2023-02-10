package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command to save an image in an ImageProcessingModel.
 */
public class Save implements ImageProcessingCommand {
  private final String fileName;
  private final String originalName;

  public Save(String originalName, String fileName) {
    this.originalName = originalName;
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView v) {
    try {
      m.saveFile(originalName, fileName);
      v.renderMessage("Saved " + originalName + " to " + fileName + "\n");
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
