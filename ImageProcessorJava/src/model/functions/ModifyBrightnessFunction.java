package model.functions;

import java.util.function.Function;

import model.MyPixel;

/**
 * function that applies modifyBrightness to a MyPixel.
 */
public class ModifyBrightnessFunction implements Function<MyPixel, MyPixel> {

  private final int value;

  public ModifyBrightnessFunction(int value) {
    this.value = value;
  }

  @Override
  public MyPixel apply(MyPixel pixel) {
    return pixel.brightenPixel(this.value);
  }
}
