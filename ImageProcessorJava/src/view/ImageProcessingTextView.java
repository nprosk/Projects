package view;

import java.io.IOException;

/**
 * Represents a text view for an image processor that renders messages to a given appendable.
 */
public class ImageProcessingTextView implements ImageProcessingView {

  private final Appendable ap;

  /**
   * Initializes appendable.
   * @param ap where the view renders to
   * @throws IllegalArgumentException if the given appendable is null
   */
  public ImageProcessingTextView(Appendable ap) throws IllegalArgumentException {
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
