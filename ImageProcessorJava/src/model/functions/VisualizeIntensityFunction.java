package model.functions;

import java.util.function.Function;

import model.MyPixel;

/**
 * function that applies visualizeIntensity to a MyPixel.
 */
public class VisualizeIntensityFunction implements Function<MyPixel, MyPixel> {
  @Override
  public MyPixel apply(MyPixel pixel) {
    return pixel.visualizeIntensity();
  }
}
