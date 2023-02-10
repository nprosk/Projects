package model;

import java.util.function.Function;

import model.functions.ColorTransform;
import model.functions.ModifyBrightnessFunction;
import model.functions.VisualizeColorFunction;
import model.functions.VisualizeIntensityFunction;
import model.functions.VisualizeLumaFunction;
import model.functions.VisualizeValueFunction;

/**
 * Represents an image with an array of MyPixels that have a position and RGB value.
 */
public class MyImage {
  private final MyPixel[][] pixels;

  /**
   * Creates an empty image of given size.
   * @param width width of image in pixels
   * @param height height of image in pixels
   * @throws IllegalArgumentException when height or width are less than 1
   */
  public MyImage(int height, int width) throws IllegalArgumentException {
    if (height < 1 || width < 1) {
      throw new IllegalArgumentException("Height and width must be > 0");
    }
    this.pixels = new MyPixel[height][width];
  }

  /**
   * converts a 2d array of RGBs to an image for testing purposes.
   * @param arr 2d array of RGBs
   * @throws IllegalArgumentException when the given array is null
   */
  public MyImage(RGB[][] arr) throws IllegalArgumentException {
    if (arr == null) {
      throw new IllegalArgumentException("Array cannot be null");
    }
    this.pixels = new MyPixel[arr.length][arr[0].length];
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        this.pixels[row][col] = new MyPixel(row, col, arr[row][col]);
      }
    }
  }

  /**
   * Inserts the given pixel into this image.
   * @param pixel the pixel to insert
   * @throws IllegalArgumentException if pixel's row or column are not valid
   */
  public void insertPixel(MyPixel pixel) {
    int row = pixel.getRow();
    int col = pixel.getCol();
    if (row < 0 || row >= this.getHeight() || col < 0 || col >= this.getWidth()) {
      throw new IllegalArgumentException("Invalid row or column for given pixel");
    }
    this.pixels[pixel.getRow()][pixel.getCol()] = pixel;
  }

  /**
   * gets height of image.
   * @return height of image
   */
  public int getHeight() {
    return this.pixels.length;
  }

  /**
   * gets width of image.
   * @return width of image
   */
  public int getWidth() {
    return this.pixels[0].length;
  }

  /**
   * gets a representation of the pixel at the specified location.
   * @param row row of the pixel
   * @param col col of the pixel
   * @return new MyPixel with information of the MyPixel at (row, col)
   */
  public MyPixel getPixelAt(int row, int col) {
    if (row < 0 || row >= this.getHeight() || col < 0 || col >= this.getWidth()) {
      throw new IllegalArgumentException("Invalid row or column given");
    }
    return new MyPixel(row, col, this.pixels[row][col].getRGB());
  }

  /**
   * finds the max RGB value in the whole image.
   * @return max RGB value
   */
  public int maxValue() {
    int max = 0;
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        max = Math.max(max, this.pixels[row][col].getRGB().value());
      }
    }
    return max;
  }

  // maps a function to all of the pixels returning a new MyImage
  private MyImage map(Function<MyPixel, MyPixel> function) {
    MyImage newImg = new MyImage(this.getHeight(), this.getWidth());
    for (int row = 0; row < newImg.getHeight(); row++) {
      for (int col = 0; col < newImg.getWidth(); col++) {
        newImg.insertPixel(function.apply(new MyPixel(row, col, this.pixels[row][col].getRGB())));
      }
    }
    return newImg;
  }

  /**
   * visualizes the RGB of this image with the given ColorType.
   * @param c color to visualize
   * @return new MyImage with visualized color
   */
  public MyImage visualizeColor(RGB.ColorType c) {
    return this.map(new VisualizeColorFunction(c));
  }

  /**
   * visualizes the RGB value of this image.
   * @return new MyImage with visualized value
   */
  public MyImage visualizeValue() {
    return this.map(new VisualizeValueFunction());
  }

  /**
   * visualizes the RGB intensity of this image.
   * @return new MyImage with visualized intensity
   */
  public MyImage visualizeIntensity() {
    return this.map(new VisualizeIntensityFunction());
  }

  /**
   * visualizes the RGB luma of this image.
   * @return new MyImage with visualized luma
   */
  public MyImage visualizeLuma() {
    return this.map(new VisualizeLumaFunction());
  }

  /**
   * Returns a new image of this image flipped horizontally.
   * @return a MyImage
   */
  public MyImage flipHorizontal() {
    MyImage flipped = new MyImage(this.getHeight(), this.getWidth());
    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        flipped.insertPixel(new MyPixel(r , this.getWidth() - 1 - c, this.pixels[r][c].getRGB()));
      }
    }
    return flipped;
  }

  /**
   * Returns a new image of this image flipped vertically.
   * @return a MyImage
   */
  public MyImage flipVertical() {
    MyImage flipped = new MyImage(this.getHeight(), this.getWidth());
    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        flipped.insertPixel(new MyPixel(this.getHeight() - 1 - r, c, this.pixels[r][c].getRGB()));
      }
    }
    return flipped;
  }

  /**
   * Returns a new image of this image with the brightness changed by the specified value.
   * @return a MyImage
   */
  public MyImage modifyBrightness(int value) {
    return map(new ModifyBrightnessFunction(value));
  }

  /**
   * Returns a new image with given array used to filter pixels of the current image.
   * @param arr an odd by odd array used for filtering.
   * @return a MyImage
   */
  public MyImage applyMatrix(double[][] arr) {
    MyImage newImg = new MyImage(this.getHeight(), this.getWidth());
    int[] rgb = new int[3];
    RGB.ColorType[] enums = new RGB.ColorType[] {RGB.ColorType.RED
            , RGB.ColorType.GREEN, RGB.ColorType.BLUE};

    for (int row = 0; row < newImg.getHeight(); row++) {
      for (int col = 0; col < newImg.getWidth(); col++) {
        rgb[0] = 0;
        rgb[1] = 0;
        rgb[2] = 0;
        for (int filterRow = 0; filterRow < arr.length; filterRow++) {
          for (int filterCol = 0; filterCol < arr[filterRow].length; filterCol++) {
            for (int color = 0; color < 3; color++) {
              try {
                rgb[color] += this.pixels[row - (arr.length / 2) + filterRow]
                        [col - (arr[filterRow].length / 2) + filterCol]
                        .getRGB().getColorValue(enums[color]) * arr[filterRow][filterCol];
              }
              catch (IndexOutOfBoundsException e) {
                rgb[color] += 0;
              }
            }
          }
        }
        rgb[0] = Math.max(0, Math.min(255, rgb[0]));
        rgb[1] = Math.max(0, Math.min(255, rgb[1]));
        rgb[2] = Math.max(0, Math.min(255, rgb[2]));
        newImg.insertPixel(new MyPixel(row, col, new RGB(rgb[0], rgb[1], rgb[2])));
      }
    }
    return newImg;
  }

  /**
   * Returns a new image with given array used to make linear combinations of the current image.
   * @param arr 3x3 array used for color transformation
   * @return a MyImage
   */
  public MyImage colorTransform(double[][] arr) {
    return this.map(new ColorTransform(arr));
  }

  /**
   * Scales an image by the given scale factor.
   * @param scaleFactor the factor scale the image by
   * @return a new my image that is caled
   */
  public MyImage scale(double scaleFactor) {
    MyImage newImg = new MyImage((int)(this.getHeight() * scaleFactor),
        (int)(this.getWidth() * scaleFactor));

    for (int r = 0; r < newImg.getHeight(); r++) {
      for (int c = 0; c < newImg.getWidth(); c++) {
        newImg.insertPixel(new MyPixel(r, c, this.getScaledColor(r, c, scaleFactor)));
      }
    }
    return newImg;
  }

  private RGB getScaledColor(int row, int col, double scaleFactor) {

    RGB scaledColor = new RGB(0, 0, 0);

    double pY = row * (1 / scaleFactor);
    double pX = col * (1 / scaleFactor);

    if (pY >= this.getHeight() - 1) {
      pY = this.getHeight() - 2;
    }
    if (pX >= this.getWidth() - 1) {
      pX = this.getHeight() - 2;
    }

    double xFloor = Math.floor(pX);
    double xCeil = Math.ceil(pX);
    double yFloor = Math.floor(pY);
    double yCeil = Math.ceil(pY);

    int aY = (int) yFloor;
    int aX = (int) xFloor;
    RGB a = this.getPixelAt(aY, aX).getRGB();

    int bY = (int) yFloor;
    int bX = (int) xCeil;
    RGB b = this.getPixelAt(bY, bX).getRGB();

    int cY = (int) yCeil;
    int cX = (int) xFloor;
    RGB c = this.getPixelAt(cY, cX).getRGB();

    int dY = (int) yCeil;
    int dX = (int) xCeil;
    RGB d = this.getPixelAt(dY, dX).getRGB();

    RGB.ColorType[] colors = {
      RGB.ColorType.RED,
      RGB.ColorType.GREEN,
      RGB.ColorType.BLUE};

    for (int i = 0; i < 3; i++) {
      double m = (b.getColorValue(colors[i]) * (pX - xFloor))
          + (a.getColorValue(colors[i]) * (xCeil - pX));

      double n = (d.getColorValue(colors[i]) * (pX - xFloor))
          + (c.getColorValue(colors[i]) * (xCeil - pX));

      int colorVal = (int) Math.round((n * (pY - yFloor))
          + (m * (yCeil - pY)));

      //System.out.println("(" + row + "," + col + "):" + pY + " " + pX + ": " + colorVal);
      scaledColor.setValue(colors[i], colorVal);
    }

    // GET THE SCALED COLOR FOR THIS R,C value
    return scaledColor;
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MyImage)) {
      return false;
    }

    MyImage other = (MyImage) o;

    if (other.getWidth() != this.getWidth() || other.getHeight() != this.getHeight()) {
      return false;
    }

    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        if (!this.pixels[r][c].equals(other.pixels[r][c])) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int code = 0;
    int counter = 10;
    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        counter++;
        code += this.pixels[r][c].hashCode() * counter;
      }
    }
    return code;
  }
}
