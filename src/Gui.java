import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI and Graphics
 *
 * @author Jose(Abe) Fernandez
 * @version 12/14/2021
 */
public class Gui extends JFrame {
    /**   Button Instance     */
    private JButton drawButton;
    /**   Main Panel Instance     */
    private JPanel mainPanel;
    /**   Drawing Area Instance     */
    private JPanel drawingArea;
    /**   Color Choice Instance     */
    private JComboBox<String> colorChoice;
    /**   Shape Choice Instance     */
    private JComboBox<String> shapeChoice;
    /**   X-Coordinate Instance     */
    private JTextField coordinateX;
    /**   Y-Coordinate Instance     */
    private JTextField coordinateY;
    /**   Size Range Value Instance     */
    private JSlider sizeRange;  //contain the size width and height
    /**   Shapes Value Instance     */
    private String shape = "Circle";    //shape combo box selection
    /**   Color Value Instance     */
    private String color = "Red";   //color combo box selection
    /**   X-Position Value Instance     */
    private int xPosition = 0;
    /**   Y-Position Value Instance     */
    private int yPosition = 0;
    /**   Size Value Instance     */
    private int size;
    /**   Slider Minimum Value     */
    static final int FPS_MIN = 100;
    /**   Slider Maximum Value     */
    static final int FPS_MAX = 400;
    /**   Slider Init Value     */
    static final int FPS_INIT = 250;

    /**
     * Constructor
     *
     */
    public Gui(){
        setTitle("Shape Drawing");
        setSize(750, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set up main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(null); // absolute positioning
        getContentPane().add(mainPanel);

        // set up ShapeComboBox
        String[] shapeList = {"Circle", "Square"};
        shapeChoice = new JComboBox<String>(shapeList);
        shapeChoice.setBounds(10, 10, 100, 30);
        mainPanel.add(shapeChoice);
        shapeChoice.setSelectedIndex(0);


        // set up ColorComboBox
        String[] colorList = {"Red","Green","Blue"};
        colorChoice = new JComboBox<String>(colorList);
        colorChoice.setBounds(120, 10, 100, 30);
        mainPanel.add(colorChoice);

        // set up x textField
        coordinateX = new JTextField("0");
        JLabel xCoordinateLabel = new JLabel("x: ");
        Font myFont = new Font("san-serif",Font.BOLD,14);
        xCoordinateLabel.setFont(myFont);
        xCoordinateLabel.setBounds(230,15,20,20);
        coordinateX.setBounds(250,10,50,30);
        mainPanel.add(xCoordinateLabel);
        mainPanel.add(coordinateX);

        // set up y textField
        coordinateY = new JTextField("0");
        JLabel yCoordinateLabel = new JLabel("y: ");
        yCoordinateLabel.setFont(myFont);
        yCoordinateLabel.setBounds(310,15,20,20);
        coordinateY.setBounds(330,10,50,30);
        mainPanel.add(yCoordinateLabel);
        mainPanel.add(coordinateY);

        // set up slider
        sizeRange = new JSlider(JSlider.HORIZONTAL,FPS_MIN,FPS_MAX,FPS_INIT);
        sizeRange.setMajorTickSpacing(100);
        sizeRange.setMinorTickSpacing(50);
        sizeRange.setPaintTicks(true);
        sizeRange.setPaintLabels(true);
        sizeRange.setBounds(390,10,200,45);
        mainPanel.add(sizeRange);

        // set up Button
        drawButton = new JButton("Draw");
        drawButton.setBounds(600,10,100,40);
        mainPanel.add(drawButton);
        drawButton.addActionListener(new buttonActionListener());

        //Drawing Area
        drawingArea = new DrawingArea();
        drawingArea.setBounds(0,60,750,640);
        mainPanel.add(drawingArea);

        setVisible(true);
   }

    /**
     * This class contain the action listener event
     *
     */
    private class buttonActionListener implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            shape = (String)shapeChoice.getSelectedItem();
            color = (String)colorChoice.getSelectedItem();
            xPosition = Integer.parseInt(coordinateX.getText());
            yPosition = Integer.parseInt(coordinateY.getText());
            size = sizeRange.getValue();
            drawingArea.repaint();
        }
    }

    /**
     * This class is the drawing panel
     *
     */
    private class DrawingArea extends JPanel {
       @Override
       public void paintComponent(Graphics g) {
           super.paintComponents(g);
           g.setColor(getBackground());
           g.fillRect(0,0,750,650);

           if (color.equals("Red")){
               g.setColor(Color.RED);
           }
           if (color.equals("Green")){
               g.setColor(Color.GREEN);
           }
           if (color.equals("Blue")){
               g.setColor(Color.BLUE);
           }

           if (shape.equals("Circle")){
               g.fillOval(xPosition, yPosition, size, size);
           }
           if (shape.equals("Square")){
               g.fillRect(xPosition, yPosition, size, size);
           }

       }
   }
}
