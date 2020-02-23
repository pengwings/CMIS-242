import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Project3 {

    public static void main(String[] args) {
        JFrame project3 = new JFrame("Geometric Drawing");
        project3.setSize(450,300);
        project3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project3.add(new GeometricDrawPanel());
        project3.setVisible(true);
    }

    private static class GeometricDrawPanel extends JPanel {
        private JLabel shapeTypeLabel = new JLabel("Shape Type");
        private JLabel fillTypeLabel = new JLabel("Fill Type");
        private JLabel colorLabel = new JLabel("Color");
        private JLabel widthLabel = new JLabel("Width");
        private JLabel heightLabel = new JLabel("Height");
        private JLabel xcoordLabel = new JLabel("x coordinate");
        private JLabel ycoordLabel = new JLabel("y coordinate");

        private String[] shapeTypes = {"Oval", "Rectangular"};
        private JComboBox shapeList = new JComboBox(shapeTypes);

        private String[] fillTypes = {"Solid", "Hollow"};
        private JComboBox fillList = new JComboBox(fillTypes);

        private String[] colorTypes = {"Black", "Red", "Orange", "Yellow", "Green", "Blue", "Magenta"};
        private Color[] colorTypeValues = {Color.black, Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
        private JComboBox colorList = new JComboBox(colorTypes);

        private JTextField widthField = new JTextField("");
        private JTextField heightField = new JTextField("");
        private JTextField xcoordField = new JTextField("");
        private JTextField ycoordField = new JTextField("");

        public GeometricDrawPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JPanel shapeConfigPanel = new JPanel();
            shapeConfigPanel.setMaximumSize(new Dimension(200,200));
            shapeConfigPanel.setLayout(new GridLayout(7,2));
            shapeConfigPanel.add(shapeTypeLabel); shapeConfigPanel.add(shapeList);
            shapeConfigPanel.add(fillTypeLabel); shapeConfigPanel.add(fillList);
            shapeConfigPanel.add(colorLabel); shapeConfigPanel.add(colorList);
            shapeConfigPanel.add(widthLabel); shapeConfigPanel.add(widthField);
            shapeConfigPanel.add(heightLabel); shapeConfigPanel.add(heightField);
            shapeConfigPanel.add(xcoordLabel); shapeConfigPanel.add(xcoordField);
            shapeConfigPanel.add(ycoordLabel); shapeConfigPanel.add(ycoordField);

            Drawing drawingPanel = new Drawing();
            drawingPanel.setMaximumSize(drawingPanel.getPreferredSize());
            drawingPanel.setBorder(new TitledBorder("Shape Drawing"));

            JButton drawButton = new JButton(new AbstractAction("Draw") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Shape selectedShape = getShapeInfo();
                    try {
                        drawingPanel.drawShape(selectedShape);
                    } catch (OutsideBounds outsideBounds) {
                        outsideBounds.printStackTrace();
                    }
                }
            });

            JPanel mainPanel = new JPanel();
            mainPanel.setMaximumSize(new Dimension(400,400));
            mainPanel.setLayout(new GridLayout(1,2));
            mainPanel.add(shapeConfigPanel);
            mainPanel.add(drawingPanel);

            add(mainPanel);
            add(drawButton);
        }

        private Shape getShapeInfo() {
            Shape input = null;
            try {
                String shapeType = String.valueOf(shapeList.getSelectedItem());

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

                if(shapeType == "Oval") {
                    input = new Oval(new Rectangle(width, height, xcoord, ycoord), colorTypeValues[colorIndex], fillTypeValue);
                } else {
                    input = new Rectangular(new Rectangle(width, height, xcoord, ycoord), colorTypeValues[colorIndex], fillTypeValue);
                }

            } catch(NumberFormatException nex) {
                JOptionPane.showMessageDialog(null, "Incorrect values entered.");
            }
            return input;
        }

        private int getInput(JTextField inputTextField) throws NumberFormatException {
            String inputString = inputTextField.getText();
            return Integer.parseInt(inputString);
        }
    }
}
