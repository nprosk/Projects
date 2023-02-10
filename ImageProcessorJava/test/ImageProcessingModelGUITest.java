import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

import model.GUIImageProcessingModel;
import model.ImageProcessingModelGUI;
import model.RGB;

import static org.junit.Assert.assertEquals;


/**
 * Tests methods of the ImageProcessingModelGUI class.
 */
public class ImageProcessingModelGUITest {

  GUIImageProcessingModel model;
  BufferedImage threeByThreeBuff;
  int[] reds;
  int[] greens;
  int[] blues;
  int[] intensity;

  @Before
  public void init() {
    this.model = new ImageProcessingModelGUI();
    this.model.loadImage("image", "test/testthreebythree.jpg");
    this.threeByThreeBuff = new BufferedImage(3, 3,
            BufferedImage.TYPE_INT_RGB);

    this.threeByThreeBuff.setRGB(0, 0, new RGB(131, 171, 179).toIntRGB());
    this.threeByThreeBuff.setRGB(1, 0, new RGB(121, 161, 169).toIntRGB());
    this.threeByThreeBuff.setRGB(2, 0, new RGB(139, 14, 82).toIntRGB());
    this.threeByThreeBuff.setRGB(0, 1, new RGB(126, 166, 174).toIntRGB());
    this.threeByThreeBuff.setRGB(1, 1, new RGB(18, 58, 66).toIntRGB());
    this.threeByThreeBuff.setRGB(2, 1, new RGB(171, 46, 114).toIntRGB());
    this.threeByThreeBuff.setRGB(0, 2, new RGB(48, 90, 102).toIntRGB());
    this.threeByThreeBuff.setRGB(1, 2, new RGB(221, 255, 255).toIntRGB());
    this.threeByThreeBuff.setRGB(2, 2, new RGB(167, 43, 115).toIntRGB());

    this.reds = new int[256];
    this.reds[131] = 1;
    this.reds[121] = 1;
    this.reds[139] = 1;
    this.reds[126] = 1;
    this.reds[18] = 1;
    this.reds[171] = 1;
    this.reds[48] = 1;
    this.reds[221] = 1;
    this.reds[167] = 1;

    this.greens = new int[256];
    this.greens[171] = 1;
    this.greens[161] = 1;
    this.greens[14] = 1;
    this.greens[166] = 1;
    this.greens[58] = 1;
    this.greens[46] = 1;
    this.greens[90] = 1;
    this.greens[255] = 1;
    this.greens[43] = 1;

    this.blues = new int[256];
    this.blues[179] = 1;
    this.blues[169] = 1;
    this.blues[82] = 1;
    this.blues[174] = 1;
    this.blues[66] = 1;
    this.blues[114] = 1;
    this.blues[102] = 1;
    this.blues[255] = 1;
    this.blues[115] = 1;

    this.intensity = new int[256];
    this.intensity[47] = 1;
    this.intensity[78] = 1;
    this.intensity[80] = 1;
    this.intensity[108] = 1;
    this.intensity[110] = 1;
    this.intensity[150] = 1;
    this.intensity[155] = 1;
    this.intensity[160] = 1;
    this.intensity[244] = 1;
  }

  @Test
  public void testGetBufferedImage() {
    BufferedImage buffIm = (BufferedImage) this.model.getBufferedImage("image");

    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        assertEquals(this.threeByThreeBuff.getRGB(x, y), buffIm.getRGB(x, y));
      }
    }

  }

  @Test
  public void testGetHistogramColor() {
    for (int i = 0; i < 256; i++) {
      assertEquals(this.reds[i], this.model.getHistogramColor("image", RGB.ColorType.RED)[i]);
      assertEquals(this.greens[i], this.model.getHistogramColor("image",
          RGB.ColorType.GREEN)[i]);
      assertEquals(this.blues[i],
          this.model.getHistogramColor("image", RGB.ColorType.BLUE)[i]);
    }
  }

  @Test
  public void testGetHistogramIntensity() {
    for (int i = 0; i < 256; i++) {
      System.out.println(i);
      assertEquals(this.intensity[i], this.model.getHistogramIntensity("image")[i]);
    }
  }

}