package model.functions;

import java.util.function.Function;

import model.MyPixel;
import model.RGB;

/**
 * function that applies visualizeColor to a MyPixel.
 */
public class VisualizeColorFunction implements Function<MyPixel, MyPixel> {
  private final RGB.ColorType color;

  public VisualizeColorFunction(RGB.ColorType color) {
    this.color = color;
  }

  @Override
  public MyPixel apply(MyPixel pixel) {
    return pixel.visualizeColor(this.color);
  }
}
