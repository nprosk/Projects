import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.ControllerGUI;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelGUI;
import model.GUIImageProcessingModel;
import model.ImageProcessingModelMyImage;
import view.ImageProcessingGUIView;
import view.ImageProcessingGUIViewImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingView;

/**
 * Runs the image processor program.
 */
public class ImageProcessor {

  /**
   * Main method that runs an instance of the image processor program.
   * @param args command line args
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    if (args.length > 0) {
      if (args[0].equals("-file") || args[0].equals("-text")) {

        ImageProcessingModel model = new ImageProcessingModelMyImage();
        ImageProcessingView view = new ImageProcessingTextView(System.out);

        Path path;
        String fileType;
        if (args.length > 1) {
          System.out.println("3");
          String[] split = args[1].split("\\.");
          fileType = split[split.length - 1];
          if (fileType.equals("txt")) {
            path = Path.of(args[1]);
            try {
              rd = new StringReader(Files.readString(path) + "\rquit");
            }
            catch (IOException e) {
              rd = new InputStreamReader(System.in);
            }
          }
        }
        ImageProcessingController controller = new ImageProcessingControllerImpl(model, view, rd);
        controller.execute();
      }
    }
    else if (args.length == 0) {
      GUIImageProcessingModel model = new ImageProcessingModelGUI();
      ControllerGUI controller = new ControllerGUI(model);
      ImageProcessingGUIView view = new ImageProcessingGUIViewImpl();
      controller.setView(view);
    }
    else {
      System.out.println("Command Line Arguments Invalid");
    }

  }
}
