import org.junit.Test;

import java.io.IOException;

import view.ImageProcessingTextView;
import view.ImageProcessingView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Tests the methods and constructors in the image processing text view class.
 */
public class ImageProcessingTextViewTest {

  @Test
  public void testConstructor() {
    try {
      new ImageProcessingTextView(null);
    }
    catch (IllegalArgumentException e) {
      assertEquals("The provided appendable cannot be null", e.getMessage());
    }
    try {
      new ImageProcessingTextView(new FaultyAppendable()).renderMessage("Hi");
    }
    catch (IOException e) {
      assertEquals("Cannot render", e.getMessage());
    }
    new ImageProcessingTextView(System.out);
  }

  @Test
  public void renderMessage() {
    Appendable ap = new StringBuilder();
    ImageProcessingView v = new ImageProcessingTextView(ap);
    try {
      v.renderMessage("Hi.");
      assertEquals("Hi.", ap.toString());
      v.renderMessage(" How are you?");
      assertEquals("Hi. How are you?", ap.toString());
    }
    catch (IOException e) {
      fail();
    }
  }
}