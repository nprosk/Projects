import org.junit.Before;
import org.junit.Test;

import controller.ControllerGUI;
import controller.Features;
import model.GUIImageProcessingModel;
import view.ImageProcessingGUIView;
import view.ImageProcessingGUIViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Tests the methods and input output of the GUI controller.
 */
public class ControllerGUITest {

  GUIImageProcessingModel model;
  Features controller;
  ImageProcessingGUIView view;

  @Before
  public void init() {
    this.model = new MockImageProcessingModelHashMapGUI();
    this.controller = new ControllerGUI(model);
    this.view = new ImageProcessingGUIViewImpl();
    this.model.loadImage("image", "test/testthreebythree.jpg");
  }

  @Test
  public void testConstructor() {
    this.model = null;
    try {
      this.controller = new ControllerGUI(this.model);
      fail();
    }
    catch (IllegalArgumentException e) {
      assertEquals("The model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testSetView() {
    assertFalse(((MockImageProcessingModelHashMapGUI)this.model)
        .getHashMap().containsKey("cow.jpg"));
    this.controller.setView(this.view);
    assertTrue(((MockImageProcessingModelHashMapGUI)this.model)
        .getHashMap().containsKey("cow.jpg"));
  }

  @Test
  public void testApplyOperation() {
    this.controller.setView(this.view);
    assertEquals("cow.jpg", this.view.getSelectedImage());
    assertFalse(((MockImageProcessingModelHashMapGUI)this.model)
            .getHashMap().containsKey("hello"));
    this.controller.applyOperation("hello", "Operation 1");
    assertTrue(((MockImageProcessingModelHashMapGUI)this.model)
            .getHashMap().containsKey("hello"));

    assertEquals("hello", this.view.getSelectedImage());
    assertFalse(((MockImageProcessingModelHashMapGUI)this.model)
            .getHashMap().containsKey("newImage"));
    this.controller.applyOperation("newImage", "Operation 0");
    assertTrue(((MockImageProcessingModelHashMapGUI)this.model)
            .getHashMap().containsKey("newImage"));

  }

  @Test
  public void testSetDisplayedImage() {
    this.controller.setView(this.view);
    assertEquals("cow.jpg", this.view.getSelectedImage());
    this.controller.applyOperation("hello", "Operation 1");
    assertEquals("hello", this.view.getSelectedImage());
    this.controller.applyOperation("image2", "Operation 1");
    assertEquals("image2", this.view.getSelectedImage());
  }

  @Test
  public void testLoad() {
    this.controller.setView(this.view);
    this.controller.load(); // load the testthreebythree.jpg image in the test folder
    assertTrue(((MockImageProcessingModelHashMapGUI)this.model)
            .getHashMap().containsKey("testthreebythree.jpg"));
    assertEquals("testthreebythree.jpg", this.view.getSelectedImage());

  }

  @Test
  public void testSave() {
    this.controller.setView(this.view);
    this.controller.save("cow.jpg");
    assertTrue(((MockImageProcessingModelHashMapGUI)this.model)
            .getHashMap().containsKey("The file has been saved"));
  }

}