package model;


/**
 * Represents an RGB color with red, green, and blue values.
 */
public class RGB {
  private int red;
  private int green;
  private int blue;

  /**
   * Creates an RGB color with the given red, green, and blue values.
   * @param red the value for red
   * @param green the value for green
   * @param blue the value for blue
   * @throws IllegalArgumentException if any color is below 0 or above 255
   */
  public RGB(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255) {
      throw new IllegalArgumentException("Invalid red value");
    }
    if (green < 0 || green > 255) {
      throw new IllegalArgumentException("Invalid green value");
    }
    if (blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid blue value");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * An enumeration of all the three colors that make up an RGB color.
   */
  public enum ColorType {
    RED,
    GREEN,
    BLUE
  }

  /**
   * Sets all the colors to the value of the given {@code ColorType}.
   * @param ct the ColorType to be equalized for
   */
  public RGB equalize(ColorType ct) {
    int value = this.getColorValue(ct);
    return new RGB(value, value, value);
  }

  /**
   * Gets the value of the given {@code ColorType}.
   * @param ct the ColorType to get the value for
   * @return the integer value of the color type
   */
  public int getColorValue(ColorType ct) {
    int value;
    switch (ct) {
      case RED:
        value = this.red;
        break;
      case GREEN:
        value = this.green;
        break;
      case BLUE:
        value = this.blue;
        break;
      default:
        throw new IllegalArgumentException(
                "Must get the color value with color type red, green, or blue");
    }
    return value;
  }

  /**
   * finds the maximum value of any color.
   * @return the value of this RGB
   */
  public int value() {
    return Math.max(this.red, Math.max(this.green, this.blue));
  }

  /**
   * makes an RGB with the 3 values being the value of this RGB.
   * @return a new grayscaled RGB
   */
  public RGB visualizeValue() {
    int val = this.value();
    return new RGB(val, val, val);
  }

  /**
   * averages the 3 values of this RGB.
   * @return the average
   */
  public int intensity() {
    return (int) Math.round((this.red + this.green + this.blue) / 3.0);
  }

  /**
   * makes an RGB with the 3 values being the intensity of this RGB.
   * @return a new grayscaled RGB
   */
  public RGB visualizeIntensity() {
    int intensity = this.intensity();
    return new RGB(intensity, intensity, intensity);
  }

  /**
   * finds the luma value of this RGB (.2126 * r + .7152 * g + .0722 * b)
   * @return the luma value
   */
  public int luma() {
    return (int) Math.round(.2126 * this.red + .7152 * this.green + .0722 * this.blue);
  }

  /**
   * makes an RGB with the 3 values being the luma value of this RGB.
   * @return a new grayscaled rgb
   */
  public RGB visualizeLuma() {
    int luma = this.luma();
    return new RGB(luma, luma, luma);
  }

  /**
   * Sets the rgb color value specified to the specified value.
   * @param color the color type to change
   * @param value the value to change it to
   */
  public void setValue(ColorType color, int value) {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("Invalid RGB Value");
    }
    switch (color) {
      case RED:
        this.red = value;
        break;
      case GREEN:
        this.green = value;
        break;
      case BLUE:
        this.blue = value;
        break;
      default:
        throw new IllegalArgumentException(
                "Must get the color value with color type red, green, or blue");
    }
  }

  public int toIntRGB() {
    return ((this.red & 0x0ff) << 16) | ((this.green & 0x0ff) << 8) | (this.blue & 0x0ff);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof RGB)) {
      return false;
    }

    RGB other = (RGB) o;
    return other.red == this.red
            && other.green == this.green
            && other.blue == this.blue;
  }

  @Override
  public int hashCode() {
    return this.red * 1000 + this.green * 1000 + this.blue * 1000;
  }

  @Override
  public String toString() {
    return this.red + " " + this.green + " " + this.blue;
  }
}
