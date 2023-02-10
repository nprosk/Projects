package model;

/**
 * Represents a pixel in an image.
 */
public class MyPixel {
  private final int row;
  private final int col;
  private final RGB rgb;

  /**
   * Creates a pixel object.
   * @param col the x position of the pixel
   * @param row the y position of the pixel
   * @param rgb the rgb color value of the pixel
   */
  public MyPixel(int row, int col, RGB rgb) throws IllegalArgumentException {
    if (rgb == null) {
      throw new IllegalArgumentException("RGB cannot be null");
    }
    this.row = row;
    this.col = col;
    this.rgb = rgb;
  }

  /**
   * gets the column of this pixel.
   * @return column of pixel
   */
  public int getCol() {
    return this.col;
  }

  /**
   * gets the row of this pixel.
   * @return row of pixel
   */
  public int getRow() {
    return this.row;
  }

  /**
   * gets the RGB value of this pixel.
   * @return a new RGB with the values of the RGB of this pixel
   */
  public RGB getRGB() {
    return new RGB(this.rgb.getColorValue(RGB.ColorType.RED),
            this.rgb.getColorValue(RGB.ColorType.GREEN),
            this.rgb.getColorValue(RGB.ColorType.BLUE));
  }

  /**
   * visualizes the RGB of this pixel with the given ColorType.
   * @param c color to visualize
   * @return new MyPixel with visualized color
   */
  public MyPixel visualizeColor(RGB.ColorType c) {
    return new MyPixel(this.row, this.col, this.rgb.equalize(c));
  }

  /**
   * visualizes the RGB value of this pixel.
   * @return new MyPixel with visualized value
   */
  public MyPixel visualizeValue() {
    return new MyPixel(this.row, this.col, this.rgb.visualizeValue());
  }

  /**
   * visualizes the RGB intensity of this pixel.
   * @return new MyPixel with visualized intensity
   */
  public MyPixel visualizeIntensity() {
    return new MyPixel(this.row, this.col, this.rgb.visualizeIntensity());
  }

  /**
   * visualizes the RGB luma of this pixel.
   * @return new MyPixel with visualized luma
   */
  public MyPixel visualizeLuma() {
    return new MyPixel(this.row, this.col, this.rgb.visualizeLuma());
  }

  /**
   * brightens/darkens the pixel by a given value (changes all colors in the RGB by that value).
   * @param value value used to determine how much to change brightness
   * @return new MyPixel with RGB changed
   */
  public MyPixel brightenPixel(int value) {
    RGB currentRGB = this.getRGB();
    return new MyPixel(this.row, this.col,
            new RGB(clamp(currentRGB.getColorValue(RGB.ColorType.RED) + value, 0, 255),
                    clamp(currentRGB.getColorValue(RGB.ColorType.GREEN) + value, 0, 255),
                    clamp(currentRGB.getColorValue(RGB.ColorType.BLUE) + value, 0, 255)));
  }

  //makes sure the value doesn't go below min or above max
  private int clamp(int value, int min, int max) {
    return (Math.max(min, Math.min(value, max)));
  }

  /**
   * applies color transform filter (based on given array) to a pixel.
   * @param arr 3x3 array signifying the linear combinations to use.
   * @return MyPixel with converted RGB
   */
  public MyPixel colorTransform(double[][] arr) {
    RGB currRGB = getRGB();
    int currRed = currRGB.getColorValue(RGB.ColorType.RED);
    int currGreen = currRGB.getColorValue(RGB.ColorType.GREEN);
    int currBlue = currRGB.getColorValue(RGB.ColorType.BLUE);

    int[] newRGB = new int[3];

    for (int row = 0; row < arr.length; row++) {
      newRGB[row] = clamp((int)
              (currRed * arr[row][0] + currGreen * arr[row][1] + currBlue * arr[row][2]),
              0, 255);
    }

    return new MyPixel(getRow(), getCol(), new RGB(newRGB[0], newRGB[1], newRGB[2]));
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MyPixel)) {
      return false;
    }

    MyPixel other = (MyPixel) o;
    return other.col == this.col
            && other.row == this.row
            && other.rgb.equals(this.rgb);
  }

  @Override
  public int hashCode() {
    return this.col * 10 + this.row * 100 + this.rgb.hashCode() * 1000;
  }

}
