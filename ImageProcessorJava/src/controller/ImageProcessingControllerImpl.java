package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.Grayscale;
import controller.commands.Help;
import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VisualizeColor;
import controller.commands.VisualizeIntensity;
import controller.commands.VisualizeLuma;
import controller.commands.VisualizeValue;
import model.ImageProcessingModel;
import model.RGB;
import view.ImageProcessingView;

/**
 * Represents a controller that communicates with a model and view to execute commands.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {

  private final ImageProcessingModel model;
  private final ImageProcessingView view;
  private final Readable rd;

  private final Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;


  /**
   * Creates a controller with the given model.
   * @param model an ImageProcessingModel that modifies images
   */
  public ImageProcessingControllerImpl(ImageProcessingModel model, ImageProcessingView view,
      Readable rd) throws IllegalArgumentException {
    if (model == null || view == null || rd == null) {
      throw new IllegalArgumentException("The model, view, and readable must not be null");
    }

    this.model = model;
    this.view = view;
    this.rd = rd;



    knownCommands = new HashMap<>();
    knownCommands.put("help", s -> new Help());
    knownCommands.put("load", s -> new Load(s.next(), s.next()));
    knownCommands.put("save", s -> new Save(s.next(), s.next()));
    knownCommands.put("red-component", s -> new VisualizeColor(s.next(),
        s.next(), RGB.ColorType.RED));
    knownCommands.put("green-component", s -> new VisualizeColor(s.next(), s.next(),
        RGB.ColorType.BLUE));
    knownCommands.put("blue-component", s -> new VisualizeColor(s.next(), s.next(),
        RGB.ColorType.GREEN));
    knownCommands.put("value-component", s -> new VisualizeValue(s.next(), s.next()));
    knownCommands.put("intensity-component", s -> new VisualizeIntensity(s.next(), s.next()));
    knownCommands.put("luma-component", s -> new VisualizeLuma(s.next(), s.next()));
    knownCommands.put("horizontal-flip", s -> new FlipHorizontal(s.next(), s.next()));
    knownCommands.put("vertical-flip", s -> new FlipVertical(s.next(), s.next()));
    knownCommands.put("brighten", s -> new Brighten(s.next(), s.nextInt(), s.next()));
    knownCommands.put("blur", s -> new Blur(s.next(), s.next()));
    knownCommands.put("sharpen", s -> new Sharpen(s.next(), s.next()));
    knownCommands.put("grayscale", s -> new Grayscale(s.next(), s.next()));
    knownCommands.put("sepia", s -> new Sepia(s.next(), s.next()));
  }

  @Override
  public void execute() throws IllegalStateException {
    Scanner scan = new Scanner(this.rd);
    boolean running = true;

    knownCommands.get("help").apply(scan).execute(this.model, this.view);

    while (running) {
      ImageProcessingCommand c;
      String in = scan.next();

      if (in.equals("quit")) {
        try {
          this.view.renderMessage("Quitting.\n");
        } catch (IOException e) {
          throw new IllegalStateException("Could not render message.");
        }
        return;
      }
      Function<Scanner, ImageProcessingCommand> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        try {
          this.view.renderMessage("Invalid command. Type help to see valid commands\n");
        } catch (IOException e) {
          throw new IllegalStateException("Could not render message.");
        }
      } else {
        c = cmd.apply(scan);
        c.execute(this.model, this.view);
      }

    }

  }
}
