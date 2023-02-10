import org.junit.Before;
import org.junit.Test;

import model.MyPixel;
import model.RGB;

import static org.junit.Assert.assertEquals;

/**
 * Test the methods in the MyPixel class.
 */
public class MyPixelTest {

  RGB red;
  RGB green;
  RGB blue;
  MyPixel pixel1;
  MyPixel pixel2;
  MyPixel pixel3;
  MyPixel pixel4;

  @Before
  public void init() {
    this.red = new RGB(255, 0, 0);
    this.green = new RGB(1, 230, 2);
    this.blue = new RGB(6, 4, 220);
    this.pixel1 = new MyPixel(0, 0, this.red);
    this.pixel2 = new MyPixel(5, 10, this.green);
    this.pixel3 = new MyPixel(3, 29, this.blue);
    this.pixel4 = new MyPixel(450, 5457, this.red);
  }

  @Test
  public void testConstructors() {
    this.pixel1 = new MyPixel(1, 2, new RGB(1, 230, 2));
    assertEquals(1, this.pixel1.getRow());
    assertEquals(2, this.pixel1.getCol());
    assertEquals(this.green, this.pixel1.getRGB());
    try {
      new MyPixel(1, 2, null);
    }
    catch (IllegalArgumentException e) {
      assertEquals("RGB cannot be null", e.getMessage());
    }


  }

  @Test
  public void getCol() {
    assertEquals(0, this.pixel1.getCol());
    assertEquals(10, this.pixel2.getCol());
    assertEquals(29, this.pixel3.getCol());
    assertEquals(5457, this.pixel4.getCol());
  }

  @org.junit.Test
  public void getRow() {
    assertEquals(0, this.pixel1.getRow());
    assertEquals(5, this.pixel2.getRow());
    assertEquals(3, this.pixel3.getRow());
    assertEquals(450, this.pixel4.getRow());
  }

  @org.junit.Test
  public void getRGB() {
    assertEquals(this.red, this.pixel1.getRGB());
    assertEquals(this.green, this.pixel2.getRGB());
    assertEquals(this.blue, this.pixel3.getRGB());
    assertEquals(this.red, this.pixel4.getRGB());
  }

  @org.junit.Test
  public void visualizeColor() {
    assertEquals(new MyPixel(0, 0, new RGB(255, 255 ,255)),
        this.pixel1.visualizeColor(RGB.ColorType.RED));
    assertEquals(new MyPixel(0, 0, new RGB(0, 0 ,0)),
        this.pixel1.visualizeColor(RGB.ColorType.BLUE));
    assertEquals(new MyPixel(0, 0, new RGB(0, 0 ,0)),
        this.pixel1.visualizeColor(RGB.ColorType.GREEN));
    assertEquals(new MyPixel(5, 10, new RGB(1, 1 ,1)),
        this.pixel2.visualizeColor(RGB.ColorType.RED));
    assertEquals(new MyPixel(5, 10, new RGB(230, 230 ,230)),
        this.pixel2.visualizeColor(RGB.ColorType.GREEN));
    assertEquals(new MyPixel(5, 10, new RGB(2, 2 ,2)),
        this.pixel2.visualizeColor(RGB.ColorType.BLUE));
  }

  @org.junit.Test
  public void visualizeValue() {
    assertEquals(new MyPixel(0, 0, new RGB(255, 255 ,255)),
        this.pixel1.visualizeValue());
    assertEquals(new MyPixel(5, 10, new RGB(230, 230 ,230)),
        this.pixel2.visualizeValue());
    assertEquals(new MyPixel(3, 29, new RGB(220, 220 ,220)),
        this.pixel3.visualizeValue());
    assertEquals(new MyPixel(450, 5457, new RGB(255, 255 ,255)),
        this.pixel4.visualizeValue());
  }

  @org.junit.Test
  public void visualizeIntensity() {
    assertEquals(new MyPixel(0, 0, new RGB(85, 85 ,85)),
        this.pixel1.visualizeIntensity());
    assertEquals(new MyPixel(5, 10, new RGB(78, 78 ,78)),
        this.pixel2.visualizeIntensity());
    assertEquals(new MyPixel(3, 29, new RGB(77, 77 ,77)),
        this.pixel3.visualizeIntensity());
    assertEquals(new MyPixel(450, 5457, new RGB(85, 85 ,85)),
        this.pixel4.visualizeIntensity());
  }

  @org.junit.Test
  public void visualizeLuma() {
    assertEquals(new MyPixel(0, 0, new RGB(54, 54 ,54)),
        this.pixel1.visualizeLuma());
    assertEquals(new MyPixel(5, 10, new RGB(165, 165 ,165)),
        this.pixel2.visualizeLuma());
    assertEquals(new MyPixel(3, 29, new RGB(20, 20 ,20)),
        this.pixel3.visualizeLuma());
    assertEquals(new MyPixel(450, 5457, new RGB(54, 54 ,54)),
        this.pixel4.visualizeLuma());
  }

  @org.junit.Test
  public void brightenPixel() {
    assertEquals(new MyPixel(0, 0, new RGB(255, 10, 10)),
            this.pixel1.brightenPixel(10));
    assertEquals(new MyPixel(5, 10, new RGB(0, 220, 0)),
            this.pixel2.brightenPixel(-10));
    assertEquals(new MyPixel(3, 29, new RGB(255, 255, 255)),
            this.pixel3.brightenPixel(270));
    assertEquals(new MyPixel(450, 5457, new RGB(255, 0, 0)),
            this.pixel4.brightenPixel(0));
  }

  @org.junit.Test
  public void testEquals() {
    assertEquals(new MyPixel(0, 0, new RGB(255, 0, 0)),
            this.pixel1);
    assertEquals(new MyPixel(5, 10, new RGB(1, 230, 2)),
            this.pixel2);
    assertEquals(new MyPixel(3, 29, new RGB(6, 4, 220)),
            this.pixel3);
    assertEquals(new MyPixel(450, 5457, new RGB(255, 0, 0)),
            this.pixel4);
  }

  @org.junit.Test
  public void testHashCode() {
    assertEquals(255000000, this.pixel1.hashCode());
    assertEquals(233000600, this.pixel2.hashCode());
    assertEquals(230000590, this.pixel3.hashCode());
    assertEquals(255099570, this.pixel4.hashCode());
  }

  @Test
  public void testColorTransform() {
    assertEquals(new MyPixel(0, 0, new RGB(54, 54, 54)),
            this.pixel1.colorTransform(new double[][]{{.2126, .7152, .0722}
                    , {.2126, .7152, .0722}, {.2126, .7152, .0722}}));
    assertEquals(new MyPixel(0, 0, new RGB(100, 88, 69)),
            this.pixel1.colorTransform(new double[][]{{.393, .769, .189}
                    , {.349, .686, .168}, {.272, .534, .131}}));
    assertEquals(new MyPixel(5, 10, new RGB(164, 164, 164)),
            this.pixel2.colorTransform(new double[][]{{.2126, .7152, .0722}
                    , {.2126, .7152, .0722}, {.2126, .7152, .0722}}));
    assertEquals(new MyPixel(5, 10, new RGB(177, 158, 123)),
            this.pixel2.colorTransform(new double[][]{{.393, .769, .189}
                    , {.349, .686, .168}, {.272, .534, .131}}));
  }
}