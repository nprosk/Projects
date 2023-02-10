package view;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Image;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

import controller.Features;

/**
 * A GUI view for image processing models that allows the user to perform operations on images,
 * and load and save a variety of images at the same time through the interface.
 */
public class ImageProcessingGUIViewImpl extends JFrame implements ImageProcessingGUIView {

  private JButton operationGoButton;
  private JTextField referenceNameInput;
  private JButton loadButton;
  private JButton saveButton;
  private JComboBox<String> imageReferences;
  private ButtonGroup operationButtonGroup;
  private JLabel displayedImage;
  private JPanel histogramPanel;
  private JSpinner scaleSpinner;

  /**
   * Creates the GUI view by defining the necessary panels, buttons, labels, etc necessary
   * for supporting the functions of the model.
   */
  public ImageProcessingGUIViewImpl() {
    super();
    setTitle("Image Processor");
    setSize(500, 500);
    setLocation(250, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Main Scrollable Panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // Section with Display Image and Operations
    JPanel imageAndOps = new JPanel();
    imageAndOps.setLayout(new FlowLayout());
    mainPanel.add(imageAndOps);


    // Box Layout for operations and input buttons
    JPanel operationsPanel = new JPanel();
    operationsPanel.setLayout(new BoxLayout(operationsPanel, BoxLayout.PAGE_AXIS));

    // Radio Menu with operations in imageAndOps JPanel
    JPanel operationsMenu = new JPanel();
    operationsMenu.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    operationsMenu.setLayout(new BoxLayout(operationsMenu, BoxLayout.PAGE_AXIS));

    operationButtonGroup = new ButtonGroup();

    JRadioButton[] operationButtons = new JRadioButton[15];
    operationButtons[0] = new JRadioButton("Blur");
    operationButtons[1] = new JRadioButton("Flip Horizontal");
    operationButtons[2] = new JRadioButton("Flip Vertical");
    operationButtons[3] = new JRadioButton("Grayscale");
    operationButtons[4] = new JRadioButton("Sepia");
    operationButtons[5] = new JRadioButton("Sharpen");
    operationButtons[6] = new JRadioButton("Visualize Red");
    operationButtons[7] = new JRadioButton("Visualize Green");
    operationButtons[8] = new JRadioButton("Visualize Blue");
    operationButtons[9] = new JRadioButton("Visualize Intensity");
    operationButtons[10] = new JRadioButton("Visualize Luma");
    operationButtons[11] = new JRadioButton("Visualize Value");
    operationButtons[12] = new JRadioButton("Brighten");
    operationButtons[13] = new JRadioButton("Darken");

    // add scale panel
    JRadioButton scalePanel = new JRadioButton("Scale");
    scalePanel.setLayout(new FlowLayout());
    scaleSpinner = new JSpinner();
    SpinnerNumberModel spinModel = new SpinnerNumberModel(1.0, 0.3, 1.0, 0.1);
    scaleSpinner.setModel(spinModel);
    scalePanel.add(scaleSpinner);
    operationButtons[14] = scalePanel;

    for (int i = 0; i < operationButtons.length; i++) {
      operationButtons[i].setActionCommand("Operation " + (i));

      operationButtonGroup.add(operationButtons[i]);
      operationsMenu.add(operationButtons[i]);
    }

    operationsPanel.add(operationsMenu);

    JPanel referenceNamePanel = new JPanel();
    referenceNamePanel.setLayout(new FlowLayout());

    // Operations go button
    operationGoButton = new JButton("Apply Operation");
    operationGoButton.setActionCommand("ApplyOperation");
    referenceNameInput = new JTextField(13);
    referenceNameInput.setBorder(BorderFactory.createTitledBorder("Image Reference Name"));

    // Load and Save Buttons
    JPanel loadSavePanel = new JPanel();
    loadSavePanel.setLayout(new FlowLayout());

    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load");

    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save");
    loadSavePanel.add(loadButton);
    loadSavePanel.add(saveButton);

    referenceNamePanel.add(referenceNameInput);
    referenceNamePanel.add(operationGoButton);
    operationsPanel.add(referenceNamePanel);
    operationsPanel.add(loadSavePanel);

    // Image Panel
    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));

    // Reference Images Dropdown

    imageReferences = new JComboBox<String>();
    imageReferences.setActionCommand("Image Options");
    imageReferences.addItem("cow.jpg");


    // Image Creator
    String filepath = "res/cow.jpg";
    displayedImage = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(displayedImage);
    displayedImage.setIcon(new ImageIcon(filepath));
    imageScrollPane.setPreferredSize(new Dimension(500, 500));

    // Histogram JPanel
    histogramPanel = new JPanel();
    histogramPanel.setPreferredSize(new Dimension(512, 200));
    histogramPanel.add(new JPanel());

    imagePanel.add(imageReferences);
    imagePanel.add(imageScrollPane);
    imagePanel.add(histogramPanel);

    imageAndOps.add(imagePanel);
    imageAndOps.add(operationsPanel);

    mainPanel.add(imageAndOps);

    pack();

    setVisible(true);

  }

  @Override
  public void setHistogram(JPanel histogram) {
    this.histogramPanel.removeAll();
    this.histogramPanel.add(histogram);
    pack();
  }



  @Override
  public void addFeatures(Features features) {

    loadButton.addActionListener(evt -> features.load());
    saveButton.addActionListener(evt -> features.save(getSelectedImage()));
    operationGoButton.addActionListener(evt -> features.applyOperation(referenceNameInput.getText(),
        this.operationButtonGroup.getSelection().getActionCommand()));
    imageReferences.addActionListener(evt ->
        features.setDisplayedImage((String)imageReferences.getSelectedItem()));

  }

  @Override
  public void setDisplayedImage(Image image) {
    displayedImage.setIcon(new ImageIcon(image));

  }

  @Override
  public String getSelectedImage() {
    return (String)imageReferences.getSelectedItem();
  }

  @Override
  public String getSelectedOperation() {
    return operationButtonGroup.getSelection().getActionCommand();
  }

  @Override
  public String getInputName() {
    return referenceNameInput.getText();
  }

  @Override
  public void renderMessage(String msg) throws IOException {
    System.out.println(msg);
  }

  /**
   * Adds a new item to the dropdown.
   * @param name the name of the reference to be added
   */
  public void addToDropDown(String name) {

    this.imageReferences.addItem(name);
    int idx = this.imageReferences.getItemCount() - 1;
    this.imageReferences.setSelectedIndex(idx);

  }

  /**
   * Gets the value of the scale spinner.
   * @return a double with the value of the scale spinner
   */
  public double getScaleFactor() {
    return (double)scaleSpinner.getValue();
  }
}
