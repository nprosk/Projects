import java.io.IOException;

import view.ImageProcessingView;

/**
 * Mocks the ImageProcessingTextView.
 */
public class MockImageProcessingTextView implements ImageProcessingView {

  private final Appendable ap;

  /**
   * initalizes given appendable.
   * @param ap where the view renders to
   * @throws IllegalArgumentException if ap is null
   */
  public MockImageProcessingTextView(Appendable ap) throws IllegalArgumentException {
    if (ap == null) {
      throw new IllegalArgumentException("The provided appendable cannot be null");
    }
    this.ap = ap;
  }

  @Override
  public void renderMessage(String msg) throws IOException {
    this.ap.append(msg);
  }
}
