import org.junit.Before;
import org.junit.Test;

import model.MyImage;
import model.MyPixel;
import model.RGB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests MyImage class.
 */
public class MyImageTest {
  RGB[][] picArr;
  MyImage pic;

  RGB.ColorType red = RGB.ColorType.RED;
  RGB.ColorType green = RGB.ColorType.GREEN;
  RGB.ColorType blue = RGB.ColorType.BLUE;

  @Before
  public void init() {
    picArr = new RGB[][]{{new RGB(100, 100, 200), new RGB(255, 150, 20)}
            , {new RGB(15, 200, 0), new RGB(0, 0, 0)}};
    pic = new MyImage(picArr);
  }

  @Test
  public void testConstructor() {
    try {
      new MyImage(-1, 40);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Height and width must be > 0", e.getMessage());
    }
    try {
      new MyImage(40, 0);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Height and width must be > 0", e.getMessage());
    }
    try {
      new MyImage(null);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Array cannot be null", e.getMessage());
    }
    new MyImage(4, 4);
  }

  @Test
  public void testInsertPixel() {
    pic.insertPixel(new MyPixel(0, 0, new RGB(50, 50, 50)));
    assertEquals(new MyPixel(0, 0, new RGB(50, 50, 50)),
            pic.getPixelAt(0, 0));
    pic.insertPixel(new MyPixel(1, 1, new RGB(50, 100, 50)));
    assertEquals(new MyPixel(1, 1, new RGB(50, 100, 50)),
            pic.getPixelAt(1, 1));
    pic.insertPixel(new MyPixel(0, 1, new RGB(50, 100, 100)));
    assertEquals(new MyPixel(0, 1, new RGB(50, 100, 100)),
            pic.getPixelAt(0, 1));
    try {
      pic.insertPixel(new MyPixel(-1,1, new RGB(0,0,0)));
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column for given pixel", e.getMessage());
    }
    try {
      pic.insertPixel(new MyPixel(0,2, new RGB(0,0,0)));
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column for given pixel", e.getMessage());
    }
  }

  @Test
  public void testGetHeight() {
    assertEquals(2, pic.getHeight());
    assertEquals(4, new MyImage(4, 3).getHeight());
  }

  @Test
  public void testGetWidth() {
    assertEquals(2, pic.getWidth());
    assertEquals(3, new MyImage(4, 3).getWidth());
  }

  @Test
  public void testGetPixelAt() {
    assertEquals(new MyPixel(0, 0, new RGB(100, 100, 200)),
            pic.getPixelAt(0, 0));
    assertEquals(new MyPixel(0, 1, new RGB(255, 150, 20)),
            pic.getPixelAt(0, 1));
    assertEquals(new MyPixel(1, 0, new RGB(15, 200, 0)),
            pic.getPixelAt(1, 0));
    assertEquals(new MyPixel(1, 1, new RGB(0, 0, 0)),
            pic.getPixelAt(1, 1));
    try {
      pic.getPixelAt(-1, 1);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column given", e.getMessage());
    }
    try {
      pic.getPixelAt(0, 2);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column given", e.getMessage());
    }
  }

  @Test
  public void testMaxValue() {
    assertEquals(255, pic.maxValue());
    MyImage pic2 = new MyImage(new RGB[][]{{new RGB(100, 100, 100),
            new RGB(150, 150, 150)}
            , {new RGB(200, 200, 200), new RGB(0, 0, 0)}});
    assertEquals(200, pic2.maxValue());
  }

  @Test
  public void testVisualizeColor() {
    MyImage visRed = pic.visualizeColor(red);
    MyImage visGreen = pic.visualizeColor(green);
    MyImage visBlue = pic.visualizeColor(blue);
    MyImage picRed = new MyImage(new RGB[][]{{new RGB(100, 100, 100),
            new RGB(255, 255, 255)}
            ,{new RGB(15, 15, 15), new RGB(0, 0, 0)}});
    MyImage picGreen = new MyImage(new RGB[][]{{new RGB(100, 100, 100),
            new RGB(150, 150, 150)}
            ,{new RGB(200, 200, 200), new RGB(0, 0, 0)}});
    MyImage picBlue = new MyImage(new RGB[][]{{new RGB(200, 200, 200),
            new RGB(20, 20, 20)}
            ,{new RGB(0, 0, 0), new RGB(0, 0, 0)}});
    assertEquals(picRed, visRed);
    assertEquals(picGreen, visGreen);
    assertEquals(picBlue, visBlue);
  }

  @Test
  public void testVisualizeValue() {
    MyImage visVal = pic.visualizeValue();
    MyImage picVal = new MyImage(new RGB[][]{{new RGB(200, 200, 200),
            new RGB(255, 255, 255)}
            ,{new RGB(200, 200, 200), new RGB(0, 0, 0)}});
    assertEquals(picVal, visVal);
  }

  @Test
  public void testVisualizeIntensity() {
    MyImage visInt = pic.visualizeIntensity();
    MyImage picInt = new MyImage(new RGB[][]{{new RGB(133, 133, 133),
            new RGB(142, 142, 142)}
            ,{new RGB(72, 72, 72), new RGB(0, 0, 0)}});
    assertEquals(visInt, picInt);
  }

  @Test
  public void testVisualizeLuma() {
    MyImage visLuma = pic.visualizeLuma();
    MyImage picLuma = new MyImage(new RGB[][]{{new RGB(107, 107, 107),
            new RGB(163, 163, 163)}
            ,{new RGB(146, 146, 146), new RGB(0, 0, 0)}});
    assertEquals(visLuma, picLuma);
  }

  @Test
  public void testFlipHorizontal() {
    MyImage visHoriz = pic.flipHorizontal();
    MyImage picHoriz = new MyImage(new RGB[][]{{new RGB(255, 150, 20),
            new RGB(100, 100, 200)}
            ,{new RGB(0, 0, 0), new RGB(15, 200, 0)}});
    assertEquals(visHoriz, picHoriz);
  }

  @Test
  public void testFlipVertical() {
    MyImage visVert = pic.flipVertical();
    MyImage picVert = new MyImage(new RGB[][]{{new RGB(15, 200, 0),
            new RGB(0, 0, 0)}
            ,{new RGB(100, 100, 200), new RGB(255, 150, 20)}});
    assertEquals(visVert, picVert);
  }

  @Test
  public void testModifyBrightness() {
    MyImage visBri50 = pic.modifyBrightness(50);
    MyImage picBri50 = new MyImage(new RGB[][]{{new RGB(150, 150, 250),
            new RGB(255, 200, 70)}
            ,{new RGB(65, 250, 50), new RGB(50, 50, 50)}});
    assertEquals(visBri50, picBri50);
    MyImage visDar100 = pic.modifyBrightness(-100);
    MyImage picDar100 = new MyImage(new RGB[][]{{new RGB(0, 0, 100),
            new RGB(155, 50, 0)}
            ,{new RGB(0, 100, 0), new RGB(0, 0, 0)}});
    assertEquals(visDar100, picDar100);
  }

  @Test
  public void testApplyMatrix() {
    MyImage visApp = pic.applyMatrix(new double[][] {{.0625, .125, .0625}
            ,{.125, .25, .125},{.0625, .125, .0625}});
    MyImage picApp = new MyImage(new RGB[][]{{new RGB(57, 68, 52),
            new RGB(75, 61, 30)}
            ,{new RGB(30, 71, 26), new RGB(38, 49, 14)}});
    assertEquals(visApp, picApp);

    MyImage visShar = pic.applyMatrix(new double[][]{{-.125, -.125, -.125, -.125, -.125}
            , {-.125, .25, .25, .25, -.125}
            , {-.125, .25, 1, .25, -.125}
            , {-.125, .25, .25, .25, -.125}
            , {-.125, -.125, -.125, -.125, -.125}});

    MyImage picShar = new MyImage(new RGB[][]{{new RGB(166, 187, 205),
            new RGB(255, 225, 70)}
            ,{new RGB(103, 255, 55), new RGB(91, 112, 55)}});
    assertEquals(visShar, picShar);
  }

  @Test
  public void testColorTransform() {
    MyImage visGS = pic.colorTransform(new double[][] {{.2126, .7152, .0722}
            ,{.2126, .7152, .0722},{.2126, .7152, .0722}});
    MyImage picGS = new MyImage(new RGB[][]{{new RGB(107, 107, 107),
            new RGB(162, 162, 162)}
            ,{new RGB(146, 146, 146), new RGB(0, 0, 0)}});

    assertEquals(visGS, picGS);

    MyImage visSep = pic.colorTransform(new double[][] {{.393, .769, .189}
            ,{.349, .686, .168},{.272, .534, .131}});
    MyImage picSep = new MyImage(new RGB[][]{{new RGB(154, 137, 106),
            new RGB(219, 195, 152)}
            ,{new RGB(159, 142, 110), new RGB(0, 0, 0)}});

    assertEquals(visSep, picSep);

  }

  @Test
  public void testEquals() {
    MyImage eqImg = new MyImage(new RGB[][]{{new RGB(100, 100, 200),
            new RGB(255, 150, 20)}
            ,{new RGB(15, 200, 0), new RGB(0, 0, 0)}});
    assertEquals(eqImg, pic);
    MyImage unEqImg = new MyImage(new RGB[][]{{new RGB(100, 100, 200),
            new RGB(255, 150, 20)}
            ,{new RGB(15, 200, 0), new RGB(1, 0, 0)}});
    assertNotEquals(pic, unEqImg);
    MyImage unEqImg2 = new MyImage(new RGB[][]{{new RGB(100, 100, 200),
            new RGB(255, 150, 20)}
            ,{new RGB(15, 200, 0), new RGB(0, 0, 0)}
            ,{new RGB(15, 200, 0), new RGB(0, 0, 0)}});
    assertNotEquals(pic, unEqImg2);
  }

  @Test
  public void testHashCode() {
    MyImage eqImg = new MyImage(new RGB[][]{{new RGB(100, 100, 200),
            new RGB(255, 150, 20)}
            ,{new RGB(15, 200, 0), new RGB(0, 0, 0)}});
    assertEquals(pic.hashCode(), eqImg.hashCode());
    MyImage unEqImg = new MyImage(new RGB[][]{{new RGB(100, 100, 200),
            new RGB(255, 150, 20)}
            ,{new RGB(15, 200, 0), new RGB(1, 0, 0)}});
    assertNotEquals(pic.hashCode(), unEqImg.hashCode());
    MyImage unEqImg2 = new MyImage(new RGB[][]{{new RGB(100, 100, 200),
            new RGB(255, 150, 20)}
            ,{new RGB(15, 200, 0), new RGB(0, 0, 0)}
            ,{new RGB(15, 200, 0), new RGB(0, 0, 0)}});
    assertNotEquals(pic.hashCode(), unEqImg2.hashCode());
  }
}