package model.functions;

import java.util.function.Function;

import model.MyPixel;

/**
 * function to apply color transform filter (based on given array) to a pixel.
 */
public class ColorTransform implements Function<MyPixel, MyPixel> {

  private final double[][] arr;

  public ColorTransform(double[][] arr) {
    this.arr = arr;
  }

  @Override
  public MyPixel apply(MyPixel pixel) {
    return pixel.colorTransform(this.arr);
  }
}
