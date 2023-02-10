package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command to load an image in an ImageProcessingModel.
 */
public class Load implements ImageProcessingCommand {

  private final String name;
  private final String path;

  public Load(String name, String path) {
    this.name = name;
    this.path = path;
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView v) {
    try {
      m.loadImage(this.name, this.path);
      v.renderMessage(name + ": Loaded " + path + "\n");
    }
    catch (IllegalArgumentException e) {
      try {
        v.renderMessage("Invalid file name/path\n");
      }
      catch (IOException io) {
        throw new IllegalStateException();
      }

    } catch (IOException e) {
      throw new IllegalStateException();
    }

  }
}
