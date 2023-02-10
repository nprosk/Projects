package model.functions;

import java.util.function.Function;

import model.MyPixel;

/**
 * function that applies visualizeValue to a MyPixel.
 */
public class VisualizeValueFunction implements Function<MyPixel, MyPixel> {
  @Override
  public MyPixel apply(MyPixel pixel) {
    return pixel.visualizeValue();
  }
}
