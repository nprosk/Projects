import org.junit.Before;
import org.junit.Test;

import model.RGB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tests RGB class.
 */
public class RGBTest {
  RGB rgb1;
  RGB rgb2;
  RGB rgb3;

  @Before
  public void init() {
    rgb1 = new RGB(100, 200, 0);
    rgb2 = new RGB(255, 100, 255);
    rgb3 = new RGB(100, 100, 100);
  }

  @Test
  public void testConstructor() {
    testConstructorInvalid(400, 200, 200, "Invalid red value");
    testConstructorInvalid(-10, 200, 200, "Invalid red value");
    testConstructorInvalid(200, 400, 200, "Invalid green value");
    testConstructorInvalid(200, 200, -25, "Invalid blue value");
    new RGB(0, 255, 200);
    try {
      new RGB(400, -300, -20);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid red value", e.getMessage());
    }
  }

  private void testConstructorInvalid(int red, int green, int blue, String message) {
    try {
      new RGB(red, green, blue);
    }
    catch (IllegalArgumentException e) {
      assertEquals(message, e.getMessage());
    }
  }

  @Test
  public void testEqualize() {
    assertEquals(new RGB(100, 100, 100), rgb1.equalize(RGB.ColorType.RED));
    assertEquals(new RGB(200, 200, 200), rgb1.equalize(RGB.ColorType.GREEN));
    assertEquals(new RGB(0, 0, 0), rgb1.equalize(RGB.ColorType.BLUE));
    assertEquals(new RGB(255, 255, 255), rgb2.equalize(RGB.ColorType.RED));
  }

  @Test
  public void testGetColorValue() {
    assertEquals(100, rgb1.getColorValue(RGB.ColorType.RED));
    assertEquals(200, rgb1.getColorValue(RGB.ColorType.GREEN));
    assertEquals(0, rgb1.getColorValue(RGB.ColorType.BLUE));
    assertEquals(255, rgb2.getColorValue(RGB.ColorType.RED));
  }

  @Test
  public void testValue() {
    assertEquals(200, rgb1.value());
    assertEquals(255, rgb2.value());
    assertEquals(100, rgb3.value());
  }

  @Test
  public void testVisualizeValue() {
    assertEquals(new RGB(200, 200, 200), rgb1.visualizeValue());
    assertEquals(new RGB(255, 255, 255), rgb2.visualizeValue());
    assertEquals(new RGB(100, 100, 100), rgb3.visualizeValue());
  }

  @Test
  public void testIntensity() {
    assertEquals(100, rgb1.intensity());
    assertEquals(203, rgb2.intensity());
    assertEquals(100, rgb3.intensity());
  }

  @Test
  public void testVisualizeIntensity() {
    assertEquals(new RGB(100, 100, 100), rgb1.visualizeIntensity());
    assertEquals(new RGB(203, 203, 203), rgb2.visualizeIntensity());
    assertEquals(new RGB(100, 100, 100), rgb3.visualizeIntensity());
  }

  @Test
  public void testLuma() {
    assertEquals(164, rgb1.luma());
    assertEquals(144, rgb2.luma());
    assertEquals(100, rgb3.luma());
  }

  @Test
  public void testVisualizeLuma() {
    assertEquals(new RGB(164, 164, 164), rgb1.visualizeLuma());
    assertEquals(new RGB(144, 144, 144), rgb2.visualizeLuma());
    assertEquals(new RGB(100, 100, 100), rgb3.visualizeLuma());
  }

  @Test
  public void testEquals() {
    assertTrue(rgb1.equals(new RGB(100, 200, 0)));
    assertFalse(rgb1.equals(rgb2));
    assertFalse(rgb1.equals(rgb3));
    assertFalse(rgb1.equals(new RGB(200, 100, 0)));
    assertTrue(rgb2.equals(new RGB(255, 100, 255)));
    assertFalse(rgb2.equals(new RGB(255, 101, 255)));
    assertTrue(rgb3.equals(new RGB(100, 100, 100)));
    assertFalse(rgb3.equals(new RGB(100, 105, 95)));
  }

  @Test
  public void testHashCode() {
    assertEquals(new RGB(100, 200, 0).hashCode(), rgb1.hashCode());
    assertEquals(new RGB(255, 100, 255).hashCode(), rgb2.hashCode());
    assertEquals(new RGB(100, 100, 100).hashCode(), rgb3.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("100 200 0", rgb1.toString());
    assertEquals("255 100 255", rgb2.toString());
    assertEquals("100 100 100", rgb3.toString());
  }
}