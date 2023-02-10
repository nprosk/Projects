import org.junit.Before;
import org.junit.Test;
import model.RGB;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


/**
 * Tests the methods in the ImageProcessingModel.
 */
public class ImageProcessingModelMyImageTest {

  private MockImageProcessingModelHashMap model;

  @Before
  public void init() {
    this.model = new MockImageProcessingModelHashMap();
    this.model.loadImage("image", "threebythree.ppm");
  }

  @Test
  public void loadPPMImage() {
    this.model = new MockImageProcessingModelHashMap();
    assertNull(this.model.getHashMap().getOrDefault("baseImage", null));
    this.model.loadImage("baseImage", "threebythree.ppm");
    assertTrue(this.model.getHashMap().containsKey("baseImage"));

    assertNull(this.model.getHashMap().getOrDefault("imageDoubled", null));
    this.model.loadImage("imageDoubled", "threebythree.ppm");
    assertTrue(this.model.getHashMap().containsKey("imageDoubled"));
  }

  @Test
  public void visualizeColor() {
    assertNull(this.model.getHashMap().getOrDefault("visualize", null));
    this.model.visualizeColor("visualize", "image", RGB.ColorType.BLUE);
    assertTrue(this.model.getHashMap().containsKey("visualize"));

    assertNull(this.model.getHashMap().getOrDefault("visualize2", null));
    this.model.visualizeColor("visualize2", "visualize", RGB.ColorType.BLUE);
    assertTrue(this.model.getHashMap().containsKey("visualize2"));
  }

  @Test
  public void visualizeValue() {
    assertNull(this.model.getHashMap().getOrDefault("visualize", null));
    this.model.visualizeValue("visualize", "image");
    assertTrue(this.model.getHashMap().containsKey("visualize"));

    assertNull(this.model.getHashMap().getOrDefault("visualize2", null));
    this.model.visualizeValue("visualize2", "visualize");
    assertTrue(this.model.getHashMap().containsKey("visualize2"));
  }

  @Test
  public void visualizeIntensity() {
    assertNull(this.model.getHashMap().getOrDefault("visualize", null));
    this.model.visualizeIntensity("visualize", "image");
    assertTrue(this.model.getHashMap().containsKey("visualize"));

    assertNull(this.model.getHashMap().getOrDefault("visualize2", null));
    this.model.visualizeIntensity("visualize2", "visualize");
    assertTrue(this.model.getHashMap().containsKey("visualize2"));
  }

  @Test
  public void visualizeLuma() {
    assertNull(this.model.getHashMap().getOrDefault("visualize", null));
    this.model.visualizeLuma("visualize", "image");
    assertTrue(this.model.getHashMap().containsKey("visualize"));

    assertNull(this.model.getHashMap().getOrDefault("visualize2", null));
    this.model.visualizeLuma("visualize2", "visualize");
    assertTrue(this.model.getHashMap().containsKey("visualize2"));
  }

  @Test
  public void flipHorizontal() {
    assertNull(this.model.getHashMap().getOrDefault("flip", null));
    this.model.flipHorizontal("flip", "image");
    assertTrue(this.model.getHashMap().containsKey("flip"));

    assertNull(this.model.getHashMap().getOrDefault("flip2", null));
    this.model.flipHorizontal("flip2", "flip");
    assertTrue(this.model.getHashMap().containsKey("flip2"));
  }

  @Test
  public void flipVertical() {
    assertNull(this.model.getHashMap().getOrDefault("flip", null));
    this.model.flipVertical("flip", "image");
    assertTrue(this.model.getHashMap().containsKey("flip"));

    assertNull(this.model.getHashMap().getOrDefault("flip2", null));
    this.model.flipVertical("flip2", "flip");
    assertTrue(this.model.getHashMap().containsKey("flip2"));
  }

  @Test
  public void modifyBrightness() {
    assertNull(this.model.getHashMap().getOrDefault("bright", null));
    this.model.modifyBrightness("bright", "image", 10);
    assertTrue(this.model.getHashMap().containsKey("bright"));

    assertNull(this.model.getHashMap().getOrDefault("bright2", null));
    this.model.modifyBrightness("bright2", "bright", -244);
    assertTrue(this.model.getHashMap().containsKey("bright2"));
  }

  @Test
  public void sepia() {
    assertNull(this.model.getHashMap().getOrDefault("sepia", null));
    this.model.sepia("sepia", "image");
    assertTrue(this.model.getHashMap().containsKey("sepia"));

    assertNull(this.model.getHashMap().getOrDefault("sepia2", null));
    this.model.sepia("sepia2", "sepia");
    assertTrue(this.model.getHashMap().containsKey("sepia2"));
  }

  @Test
  public void sharpen() {
    assertNull(this.model.getHashMap().getOrDefault("sharpen", null));
    this.model.sharpen("sharpen", "image");
    assertTrue(this.model.getHashMap().containsKey("sharpen"));

    assertNull(this.model.getHashMap().getOrDefault("sharpen2", null));
    this.model.sharpen("sharpen2", "sharpen");
    assertTrue(this.model.getHashMap().containsKey("sharpen2"));
  }

  @Test
  public void grayscale() {
    assertNull(this.model.getHashMap().getOrDefault("grayscale", null));
    this.model.grayscale("grayscale", "image");
    assertTrue(this.model.getHashMap().containsKey("grayscale"));

    assertNull(this.model.getHashMap().getOrDefault("grayscale2", null));
    this.model.grayscale("grayscale2", "grayscale");
    assertTrue(this.model.getHashMap().containsKey("grayscale2"));
  }

  @Test
  public void blur() {
    assertNull(this.model.getHashMap().getOrDefault("blur", null));
    this.model.blur("blur", "image");
    assertTrue(this.model.getHashMap().containsKey("blur"));

    assertNull(this.model.getHashMap().getOrDefault("blur2", null));
    this.model.blur("blur2", "blur");
    assertTrue(this.model.getHashMap().containsKey("blur2"));
  }

  // Tested file saving (saveToPPM) by running image processor to create files attached with project

}