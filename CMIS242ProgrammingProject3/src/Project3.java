/**
 * Project3.java
 * Brian Yu
 * 2/23/2020
 * This class constructs a tool to draw rectangles and ovals with customizable size, position, color, and fill.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Project3 {
    //Main method to put together the Geometric Drawing window
    public static void main(String[] args) {
        JFrame project3 = new JFrame("Geometric Drawing");
        project3.setSize(475,300);
        project3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project3.add(new GeometricDrawPanel());
        project3.setVisible(true);
    }
    //Inner class that defines the panels to be put in the Geometric Drawing window
    private static class GeometricDrawPanel extends JPanel {
        //JLabels for all the fields
        private JLabel shapeTypeLabel = new JLabel("Shape Type");
        private JLabel fillTypeLabel = new JLabel("Fill Type");
        private JLabel colorLabel = new JLabel("Color");
        private JLabel widthLabel = new JLabel("Width");
        private JLabel heightLabel = new JLabel("Height");
        private JLabel xcoordLabel = new JLabel("x coordinate");
        private JLabel ycoordLabel = new JLabel("y coordinate");
        //JComboBox and entries for shape types
        private String[] shapeTypes = {"Oval", "Rectangular"};
        private JComboBox shapeList = new JComboBox(shapeTypes);
        //JComboBox and entries for fill types
        private String[] fillTypes = {"Solid", "Hollow"};
        private JComboBox fillList = new JComboBox(fillTypes);
        //JComboBox and entries for color types along with Color array with corresponding colors
        private String[] colorTypes = {"Black", "Red", "Orange", "Yellow", "Green", "Blue", "Magenta"};
        private Color[] colorTypeValues = {Color.black, Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
        private JComboBox colorList = new JComboBox(colorTypes);
        //Text fields for dimensions and coordinates of Shape
        private JTextField widthField = new JTextField("");
        private JTextField heightField = new JTextField("");
        private JTextField xcoordField = new JTextField("");
        private JTextField ycoordField = new JTextField("");
        //Constructor for panel object
        public GeometricDrawPanel() {
            //Sets layout for entire panel
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            //Creates and populates panel for shape configuration labels and fields
            JPanel shapeConfigPanel = new JPanel();
            shapeConfigPanel.setMaximumSize(new Dimension(200,200));
            shapeConfigPanel.setLayout(new GridLayout(7,2,0,2));
            shapeConfigPanel.add(shapeTypeLabel); shapeConfigPanel.add(shapeList);
            shapeConfigPanel.add(fillTypeLabel); shapeConfigPanel.add(fillList);
            shapeConfigPanel.add(colorLabel); shapeConfigPanel.add(colorList);
            shapeConfigPanel.add(widthLabel); shapeConfigPanel.add(widthField);
            shapeConfigPanel.add(heightLabel); shapeConfigPanel.add(heightField);
            shapeConfigPanel.add(xcoordLabel); shapeConfigPanel.add(xcoordField);
            shapeConfigPanel.add(ycoordLabel); shapeConfigPanel.add(ycoordField);
            //Creates and configures Drawing object
            Drawing drawingPanel = new Drawing();
            drawingPanel.setMaximumSize(drawingPanel.getPreferredSize());
            drawingPanel.setBorder(new TitledBorder("Shape Drawing"));
            //Action listener added to button as anonymous class to draw shape on drawing panel with configured values
            //Button also catches exceptions thrown by incorrect values entered (non-integers, empty values, and shapes that won't fit in the drawing panel)
            JButton drawButton = new JButton(new AbstractAction("Draw") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Shape selectedShape = getShapeInfo(drawingPanel);
                        drawingPanel.drawShape(selectedShape);
                    } catch (OutsideBounds obex) {
                        JOptionPane.showMessageDialog(null, "Desired shape is bigger than drawing panel.");
                    } catch (NumberFormatException nfex) {
                        JOptionPane.showMessageDialog(null, "Incorrect values entered.");
                    }
                }
            });
            drawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            //Formatting panel that includes both shape configuration panel and drawing panel
            JPanel mainPanel = new JPanel();
            mainPanel.setMaximumSize(new Dimension(400,400));
            mainPanel.setLayout(new GridLayout(1,2,10,10));
            mainPanel.add(shapeConfigPanel);
            mainPanel.add(drawingPanel);
            //Populates parent panel with padding
            add(Box.createRigidArea(new Dimension(450, 10)));
            add(mainPanel);
            add(Box.createRigidArea(new Dimension(450,5)));
            add(drawButton);
            add(Box.createRigidArea(new Dimension(450,10)));
        }
        //Local method to read entered shape configuration values
        private Shape getShapeInfo(Drawing panel) throws NumberFormatException, OutsideBounds {
            Shape input = null;
                String shapeType = String.valueOf(shapeList.getSelectedItem());
                //Converts selected String to corresponding boolean value for shape fill
                String fillType = String.valueOf(fillList.getSelectedItem());
                boolean fillTypeValue;
                if(fillType == "Solid") {
                    fillTypeValue = true;
                } else {
                    fillTypeValue = false;
                }

                int colorIndex = colorList.getSelectedIndex();
                int width = getInput(widthField);
                int height = getInput(heightField);
                int xcoord = getInput(xcoordField);
                int ycoord = getInput(ycoordField);
                //Catches shape configuration values that would result in shape that doesn't fit in drawing panel early in order not to add to the number of shapes counter with invalid Shape objects
                if((width+xcoord) > panel.getWidth() || (height+ycoord) > panel.getHeight()) {
                    throw new OutsideBounds();
                } else {
                    if (shapeType == "Oval") {
                        input = new Oval(new Rectangle(xcoord, ycoord, width, height), colorTypeValues[colorIndex], fillTypeValue);
                    } else {
                        input = new Rectangular(new Rectangle(xcoord, ycoord, width, height), colorTypeValues[colorIndex], fillTypeValue);
                    }
                }

            return input;
        }
        //Local method to convert entered Strings to integers
        private int getInput(JTextField inputTextField) throws NumberFormatException {
            String inputString = inputTextField.getText();
            return Integer.parseInt(inputString);
        }
    }
}
