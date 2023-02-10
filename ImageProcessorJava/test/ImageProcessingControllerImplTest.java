import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods, and input, output of the image processing controller.
 */
public class ImageProcessingControllerImplTest {

  private StringBuilder log;
  private Readable rd;
  private Appendable ap;
  private ImageProcessingModel mockModel;
  private ImageProcessingView mockView;
  private ImageProcessingController controller;

  private String viewOutputTest =
          "Commands:\n" +
          "help - shows all commands\n" +
          "load [reference name] [file-to-load] - " +
          "loads the file which can be referred to as the reference name\n" +
          "save [reference name] [file-name] - " +
          "saves the file with the reference name to the destination\n" +
          "red-component [reference name] [original reference name] - " +
          "visualize the original as a greyscale with red values\n" +
          "green-component [reference name] [original reference name] - " +
          "visualize the original as a greyscale with green values\n" +
          "blue-component [reference name] [original reference name] - " +
          "visualize the original as a greyscale with blue values\n" +
          "value-component [reference name] [original reference name] -" +
          " visualize the original as a greyscale with max rgb value of each pixel\n" +
          "intensity-component [reference name] [original reference name] -" +
          " visualize the original as a greyscale with the average rgb value of each pixel\n" +
          "luma-component [reference name] [original reference name] - " +
          "visualize the original as a greyscale luma image\n" +
          "horizontal-flip [reference name] [original reference name] - " +
          "flips the image horizontally\n" +
          "vertical-flip [reference name] [original reference name] - " +
          "flips the image vertically\n" +
          "brighten [reference name] [increment value] [original reference name] - " +
          "brightens or darkens the image by the given value\n" +
          "blur [reference name] [original reference name] - blurs the image\n" +
          "sharpen [reference name] [original reference name] - sharpens the image\n" +
          "grayscale [reference name] [original reference name] - makes the image grayscale\n" +
          "sepia [reference name] [original reference name] -" +
          " applies the sepia filter on the image\n" +
          "image: Loaded threebythree\n" +
          "red: Color visualized image\n" +
          "green: Color visualized image\n" +
          "blue: Color visualized image\n" +
          "value: Visualized value of imageintensity-component\n" +
          "Invalid command. Type help to see valid commands\n" +
          "Invalid command. Type help to see valid commands\n" +
          "luma: Visualized luma of image\n" +
          "Invalid command. Type help to see valid commands\n" +
          "Invalid command. Type help to see valid commands\n" +
          "vertical: Vertically flipped image\n" +
          "bright: Brightened image by 100\n" +
          "Saved red to red.ppm\n" +
          "Commands:\n" +
          "help - shows all commands\n" +
          "load [reference name] [file-to-load] - " +
          "loads the file which can be referred to as the reference name\n" +
          "save [reference name] [file-name] - " +
          "saves the file with the reference name to the destination\n" +
          "red-component [reference name] [original reference name]" +
          " - visualize the original as a greyscale with red values\n" +
          "green-component [reference name] [original reference name] " +
          "- visualize the original as a greyscale with green values\n" +
          "blue-component [reference name] [original reference name] - " +
          "visualize the original as a greyscale with blue values\n" +
          "value-component [reference name] [original reference name] -" +
          " visualize the original as a greyscale with max rgb value of each pixel\n" +
          "intensity-component [reference name] [original reference name] - " +
          "visualize the original as a greyscale with the average rgb value of each pixel\n" +
          "luma-component [reference name] [original reference name] - " +
          "visualize the original as a greyscale luma image\n" +
          "horizontal-flip [reference name] [original reference name] - " +
          "flips the image horizontally\n" +
          "vertical-flip [reference name] [original reference name] -" +
          " flips the image vertically\n" +
          "brighten [reference name] [increment value] [original reference name] -" +
          " brightens or darkens the image by the given value\n" +
          "blur [reference name] [original reference name] - blurs the image\n" +
          "sharpen [reference name] [original reference name] - sharpens the image\n" +
          "grayscale [reference name] [original reference name] - makes the image grayscale\n" +
          "sepia [reference name] [original reference name] - " +
          "applies the sepia filter on the image\n" +
          "Quitting.\n";

  @Before
  public void init() {
    this.log = new StringBuilder();
    this.rd = new StringReader("");
    this.ap = new StringBuilder();
    this.mockModel = new MockImageProcessingModelMyImage(this.log);
    this.mockView = new MockImageProcessingTextView(this.ap);
    this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, this.rd);
  }

  @Test
  public void testConstructors() {
    try {
      this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, null);
    } catch (IllegalArgumentException e) {
      assertEquals("The model, view, and readable must not be null", e.getMessage());
    }

    try {
      this.controller = new ImageProcessingControllerImpl(this.mockModel, null, this.rd);
    } catch (IllegalArgumentException e) {
      assertEquals("The model, view, and readable must not be null", e.getMessage());
    }

    try {
      this.controller = new ImageProcessingControllerImpl(mockModel, this.mockView, this.rd);
    } catch (IllegalArgumentException e) {
      assertEquals("The model, view, and readable must not be null", e.getMessage());
    }

  }

  @Test
  public void testConfirmInputs() {
    this.rd = new StringReader("load image threebythree quit");
    this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, this.rd);
    controller.execute();
    assertEquals("image threebythree\n", this.log.toString());

    init();
    this.rd = new StringReader("load image threebythree red-component red image quit");
    this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, this.rd);
    controller.execute();
    assertEquals("image threebythree\n" +
            "red image\n", this.log.toString());

    init();
    this.rd = new StringReader("load image threebythree red-component red image " +
            "green-component green image blue-component blue image value-component value image" +
            "intensity-component intensity image luma-component luma image horizontal-flip" +
            "horizontal image vertical-flip vertical image brighten bright 100 image save red " +
            "blur blur image sharpen sharp image grayscale grayscale luma " +
            "sepia sepia image red.ppm quit");
    this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, this.rd);
    controller.execute();
    assertEquals("image threebythree\n" +
            "red image\n" +
            "green image\n" +
            "blue image\n" +
            "value imageintensity-component\n" +
            "luma image\n" +
            "vertical image\n" +
            "bright image 100\n" +
            "blur red\n" +
            "image sharpen\n" +
            "grayscale luma\n" +
            "sepia image\n", this.log.toString());

  }

  @Test
  public void testConfirmOutputs() {
    this.rd = new StringReader("load image threebythree red-component red image " +
            "green-component green image blue-component blue image value-component value image" +
            "intensity-component intensity image luma-component luma image horizontal-flip" +
            "horizontal image vertical-flip vertical image brighten bright 100 image save red " +
            "red.ppm help quit");
    this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, this.rd);
    controller.execute();
    assertEquals(this.viewOutputTest, this.ap.toString());

  }

  @Test
  public void testInvalidInputs() {
    this.rd = new StringReader("load image invalidImageThatDoesn'tExist quit");
    this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, this.rd);
    controller.execute();
    assertEquals("Commands:\n" +
            "help - shows all commands\n" +
            "load [reference name] [file-to-load] - " +
            "loads the file which can be referred to as the reference name\n" +
            "save [reference name] [file-name] - " +
            "saves the file with the reference name to the destination\n" +
            "red-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with red values\n" +
            "green-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with green values\n" +
            "blue-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with blue values\n" +
            "value-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with max rgb value of each pixel\n" +
            "intensity-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with the average rgb value of each pixel\n" +
            "luma-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale luma image\n" +
            "horizontal-flip [reference name] [original reference name] - " +
            "flips the image horizontally\n" +
            "vertical-flip [reference name] [original reference name] - " +
            "flips the image vertically\n" +
            "brighten [reference name] [increment value] [original reference name] - " +
            "brightens or darkens the image by the given value\n" +
            "blur [reference name] [original reference name] - blurs the image\n" +
            "sharpen [reference name] [original reference name] - sharpens the image\n" +
            "grayscale [reference name] [original reference name] - makes the image grayscale\n" +
            "sepia [reference name] [original reference name] - " +
            "applies the sepia filter on the image\n" +
            "image: Loaded invalidImageThatDoesn'tExist\n" +
            "Quitting.\n", this.ap.toString());

    this.rd = new StringReader("none image threebythree quit");
    this.controller = new ImageProcessingControllerImpl(this.mockModel, this.mockView, this.rd);
    controller.execute();
    assertEquals("Commands:\n" +
            "help - shows all commands\n" +
            "load [reference name] [file-to-load] - " +
            "loads the file which can be referred to as the reference name\n" +
            "save [reference name] [file-name] - " +
            "saves the file with the reference name to the destination\n" +
            "red-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with red values\n" +
            "green-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with green values\n" +
            "blue-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with blue values\n" +
            "value-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with max rgb value of each pixel\n" +
            "intensity-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with the average rgb value of each pixel\n" +
            "luma-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale luma image\n" +
            "horizontal-flip [reference name] [original reference name] - " +
            "flips the image horizontally\n" +
            "vertical-flip [reference name] [original reference name] - " +
            "flips the image vertically\n" +
            "brighten [reference name] [increment value] [original reference name] - " +
            "brightens or darkens the image by the given value\n" +
            "blur [reference name] [original reference name] - blurs the image\n" +
            "sharpen [reference name] [original reference name] - sharpens the image\n" +
            "grayscale [reference name] [original reference name] - makes the image grayscale\n" +
            "sepia [reference name] [original reference name] - " +
            "applies the sepia filter on the image\n" +
            "image: Loaded invalidImageThatDoesn'tExist\n" +
            "Quitting.\n" +
            "Commands:\n" +
            "help - shows all commands\n" +
            "load [reference name] [file-to-load] - " +
            "loads the file which can be referred to as the reference name\n" +
            "save [reference name] [file-name] - " +
            "saves the file with the reference name to the destination\n" +
            "red-component [reference name] [original reference name] -" +
            " visualize the original as a greyscale with red values\n" +
            "green-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with green values\n" +
            "blue-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with blue values\n" +
            "value-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with max rgb value of each pixel\n" +
            "intensity-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale with the average rgb value of each pixel\n" +
            "luma-component [reference name] [original reference name] - " +
            "visualize the original as a greyscale luma image\n" +
            "horizontal-flip [reference name] [original reference name] -" +
            " flips the image horizontally\n" +
            "vertical-flip [reference name] [original reference name] -" +
            " flips the image vertically\n" +
            "brighten [reference name] [increment value] [original reference name] - " +
            "brightens or darkens the image by the given value\n" +
            "blur [reference name] [original reference name] - blurs the image\n" +
            "sharpen [reference name] [original reference name] - sharpens the image\n" +
            "grayscale [reference name] [original reference name] - makes the image grayscale\n" +
            "sepia [reference name] [original reference name] - " +
            "applies the sepia filter on the image\n" +
            "Invalid command. Type help to see valid commands\n" +
            "Invalid command. Type help to see valid commands\n" +
            "Invalid command. Type help to see valid commands\n" +
            "Quitting.\n", this.ap.toString());
  }

}