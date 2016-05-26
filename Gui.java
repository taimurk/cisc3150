package core;

/**
 * Import Java Libraries for GUI
 */
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * GUI Class
 */
public class Gui{

  /**
   * Private objects for GUI
   */
  private JFrame      frame;
  private JSlider     sliderB;
  private JSlider     sliderO;
  private JSlider     sliderSpeed;
  private JLabel      labelTitle;
  private JLabel      labelSpeed;
  private JLabel      labelB;
  private JLabel      labelO;
  private JLabel      labelCase;
  private JTextArea   caseResult;
  private JScrollPane caseResultScroll;

  /**
   * GUI construct
   */
  public Gui(){
    this.labelTitle       = this.setupTitle();
    this.labelB           = this.setupLabel("Blue Bot");
    this.labelO           = this.setupLabel("Orange Bot");
    this.labelCase        = this.setupLabel("Output Cases");
    this.sliderB          = this.setupSlider(labelB, "Blue Bot");
    this.sliderO          = this.setupSlider(labelO, "Orange Bot");
    this.sliderSpeed      = this.setupSliderSpeed();
    this.labelSpeed       = this.setupLabel("Speed: "+this.getSpeed()+"x");
    this.caseResultScroll = this.setupTextAreaWithScrollPane();
    this.setupFrame();
  }

  /**
   * Setup Title of the Project inside GUI
   * @return JLabel
   */
  private JLabel setupTitle(){
    JLabel label = new JLabel();
    label.setText(Config.FRAME_NAME);
    label.setFont(new Font("Arial", Font.BOLD, 30));
    label.setOpaque(true);
    label.setMinimumSize(new Dimension(Config.FRAME_WIDTH, 200));
    return label;
  }

  /**
   * Setup basic label
   * @param  String text Text that will get display inside GUI
   * @return JLabel
   */
  private JLabel setupLabel(String text){
    JLabel label = new JLabel();
    label.setText(text);
    label.setFont(new Font("Arial", Font.BOLD, 22));
    label.setOpaque(true);
    label.setMinimumSize(new Dimension(Config.FRAME_WIDTH, 100));
    return label;
  }

  /**
   * Setup basic slider
   * @param  JLabel label JLabel that will be linked to this slider
   * @param  String text  Defult text of the JLabel that will be linked to this slider
   * @return JSlider
   */
  private JSlider setupSlider(JLabel label, String text){
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
    slider.setMaximum(Config.MAX_LOCATION);
    slider.setMinimum(0);
    slider.setMajorTickSpacing(5);
    slider.setPaintLabels(true);
    slider.setPaintTicks(true);
    slider.setLabelTable(slider.createStandardLabels(5));
    slider.setValue(1);
    slider.disable();
    slider.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSlider) {
          JSlider theJSlider = (JSlider) source;
          if (!theJSlider.getValueIsAdjusting()) {
            int value = theJSlider.getValue();
            value = (value<1) ? 1 : value;
            label.setText(text+": "+value);
          }
        }
      }
    });
    return slider;
  }

  /**
   * Setup TextArea with ScrollPane so we don't have to resize the window to see case result.
   * @return JScrollPane
   */
  private JScrollPane setupTextAreaWithScrollPane(){
    this.caseResult = new JTextArea(10, 10);
    this.caseResult.disable();
    JScrollPane sp = new JScrollPane(this.caseResult, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    return sp;
  }

  /**
   * Setup Speed Slider which user will control.
   * @return JSlider
   */
  private JSlider setupSliderSpeed(){
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
    slider.setMaximum(Config.MAX_SPEED);
    slider.setMinimum(0);
    slider.setMajorTickSpacing(Config.MAX_SPEED / 20);
    slider.setPaintLabels(true);
    slider.setPaintTicks(true);
    slider.setLabelTable(slider.createStandardLabels(Config.MAX_SPEED / 20));
    slider.setValue(Config.DEFAULT_SPEED);
    slider.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSlider) {
          JSlider theJSlider = (JSlider) source;
          if (!theJSlider.getValueIsAdjusting()) {
            int speed = theJSlider.getValue();
            speed = (speed<1) ? 1 : speed;
            labelSpeed.setText("Speed: "+speed+"x");
          }
        }
      }
    });
    return slider;
  }

  /**
   * Setup the Frame that user will interact
   * @return void
   */
  private void setupFrame(){
    this.frame = new JFrame(Config.FRAME_NAME);
    this.frame.setMinimumSize(new Dimension(Config.FRAME_WIDTH, Config.FRAME_HEIGHT));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    this.frame.add(this.labelTitle);
    this.frame.add(this.labelB);
    this.frame.add(this.sliderB);
    this.frame.add(this.labelO);
    this.frame.add(this.sliderO);
  }

  /**
   * Change the slider value base on the Bot
   * @param  String bot   name of the Bot
   * @param  int    value value for the slider
   * @return void
   */
  public void changeValue(String bot, int value){
    JSlider slider = (bot.equals("O")) ? this.sliderO : this.sliderB;
    slider.setValue(value);
  }

  /**
   * Get speed number from the speed slider
   * @return int
   */
  public int getSpeed(){
    int speed = this.sliderSpeed.getValue();
    return (speed<1) ? 1 : speed;
  }

  /**
   * Display case in TextArea
   * @param  output string that will get append in TextArea
   * @return void
   */
  public void displayCase(String output){
    this.caseResult.append(output);
  }

  /**
   * Display the frame
   * @return void
   */
  public void display(){
    this.frame.setVisible(true);
  }
}
