package view;

import java.io.IOException;

/**
 * Represents a view for an ImageProcessor.
 */
public interface ImageProcessingView {

  /**
   * renders a message to the user.
   * @param msg message to render
   * @throws IOException if the message cannot be rendered
   */
  void renderMessage(String msg) throws IOException;

}
