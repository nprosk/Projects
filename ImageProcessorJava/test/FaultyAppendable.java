import java.io.IOException;

/**
 * faulty appendable for testing.
 */
public class FaultyAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Cannot render");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Cannot render");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Cannot render");
  }
}
