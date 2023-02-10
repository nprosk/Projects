package view;


import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Graphics;


/**
 * Represents a histogram that visualizes the red, green, blue and intensity values
 * of an image with a bar chart.
 */
public class Histogram extends JPanel {

  private int[] red;
  private int[] green;
  private int[] blue;
  private int[] intensity;
  private int height;

  /**
   * Creates a histogram given the data for frequencies of the parameters.
   * @param red the frequency of red value pixels from 0 to 255
   * @param green the frequency of green value pixels from 0 to 255
   * @param blue the frequency of blue value pixels from 0 to 255
   * @param intensity the frequency of intensity value pixels from 0 to 255
   */
  public Histogram(int[] red, int[] green, int[] blue, int[] intensity) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.intensity = intensity;
    this.height = 200;
    this.setPreferredSize(new Dimension(512, this.height));

  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    normalize();
    for (int i = 0; i < 256; i++) {
      g.setColor(new Color(255, 0, 0, 150));
      g.fillRect(2 * i, this.height - red[i], 2, red[i]);
      g.setColor(new Color(0, 255, 0, 150));
      g.fillRect(2 * i, this.height - green[i], 2, green[i]);
      g.setColor(new Color(0, 0, 255, 150));
      g.fillRect(2 * i, this.height - blue[i], 2, blue[i]);
      g.setColor(new Color(70, 70, 70, 150));
      g.fillRect(2 * i, this.height - intensity[i], 2, intensity[i]);

    }
  }

  private void normalize() {
    int min = getMinValue();
    int max = getMaxValue();

    for (int i = 0; i < 256; i++) {
      red[i] = (int)(((red[i] - min) / (double)(max - min) * this.height));
      green[i] = (int)(((green[i] - min) / (double)(max - min) * this.height));
      blue[i] = (int)(((blue[i] - min) / (double)(max - min) * this.height));
      intensity[i] = (int)(((intensity[i] - min) / (double)(max - min) * this.height));

    }
  }

  private int getMaxValue() {
    int max = 0;
    for (int i = 0; i < 256; i++) {
      int newMax = Math.max(Math.max(Math.max(this.red[i], this.green[i]), this.blue[i]),
          this.intensity[i]);
      if (max < newMax) {
        max = newMax;
      }
    }

    return max;
  }

  private int getMinValue() {
    int min = 255;
    for (int i = 0; i < 256; i++) {
      int newMin = Math.min(Math.min(Math.min(this.red[i], this.green[i]), this.blue[i]),
          this.intensity[i]);
      if (min > newMin) {
        min = newMin;
      }
    }

    return min;
  }
}
