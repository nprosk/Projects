package controller.commands;

import java.io.IOException;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Command to give helpMessage to ImageProcessingView.
 */
public class Help implements ImageProcessingCommand {
  private final String helpMsg;

  /**
   * Initializes the message with all the commands.
   */
  public Help() {
    this.helpMsg = "Commands:\n" +
            "help - shows all commands\n" +
            "load [reference name] [file-to-load]" +
            " - loads the file which can be referred to as the reference name\n" +
            "save [reference name] [file-name]" +
            " - saves the file with the reference name to the destination\n" +
            "red-component [reference name] [original reference name]" +
            " - visualize the original as a greyscale with red values\n" +
            "green-component [reference name] [original reference name]" +
            " - visualize the original as a greyscale with green values\n" +
            "blue-component [reference name] [original reference name]" +
            " - visualize the original as a greyscale with blue values\n" +
            "value-component [reference name] [original reference name]" +
            " - visualize the original as a greyscale with max rgb value of each pixel\n" +
            "intensity-component [reference name] [original reference name]" +
            " - visualize the original as a greyscale with the average rgb value of each pixel\n" +
            "luma-component [reference name] [original reference name]" +
            " - visualize the original as a greyscale luma image\n" +
            "horizontal-flip [reference name] [original reference name]" +
            " - flips the image horizontally\n" +
            "vertical-flip [reference name] [original reference name]" +
            " - flips the image vertically\n" +
            "brighten [reference name] [increment value] [original reference name]" +
            " - brightens or darkens the image by the given value\n" +
            "blur [reference name] [original reference name] - blurs the image\n" +
            "sharpen [reference name] [original reference name] - sharpens the image\n" +
            "grayscale [reference name] [original reference name] - makes the image grayscale\n" +
            "sepia [reference name] [original reference name]" +
            " - applies the sepia filter on the image\n";
  }

  @Override
  public void execute(ImageProcessingModel m, ImageProcessingView view) {
    try {
      view.renderMessage(this.helpMsg);
    }
    catch (IOException e) {
      throw new IllegalStateException("Message was not able to be rendered");
    }

  }
}
