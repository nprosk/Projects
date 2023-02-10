package model.functions;

import java.util.function.Function;

import model.MyPixel;

/**
 * function that applies visualizeLuma to a MyPixel.
 */
public class VisualizeLumaFunction implements Function<MyPixel, MyPixel> {
  @Override
  public MyPixel apply(MyPixel pixel) {
    return pixel.visualizeLuma();
  }
}
