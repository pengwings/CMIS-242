/**
 * Project2.java
 * Brian Yu
 * 2/9/2020
 * This class constructs an automobile sales tax calculator with GUI.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Project2 {
    //main method to construct calculator, placing calculator jpanel on jframe
    public static void main(String[] args) {
        JFrame project2 = new JFrame("Automobile Sales Tax Calculator");
        project2.setSize(450, 300);
        project2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project2.add(new SalesCalculatorPanel());
        project2.setVisible(true);
    }
    //private inner class for calculator jpanel
    private static class SalesCalculatorPanel extends JPanel {
        //creates array of 5 Automobile objects for report
        private Automobile[] carHistory = new Automobile[5];
        private int carCount=0;
        //labels and fields for make/model/price section of panel
        private JLabel makeModelLabel = new JLabel("Make and Model");
        private JLabel priceLabel = new JLabel("Sales Price");
        private JTextField makeModelInputField = new JTextField("");
        private JTextField priceInputField = new JTextField("");

        //labels and fields for automobile type section of panel
        private JLabel mpgLabel = new JLabel("Miles per Gallon", JLabel.LEFT);
        private JLabel weightLabel = new JLabel("Weight in Pounds", JLabel.LEFT);
        ButtonGroup automobileTypeButtons = new ButtonGroup();
        private JRadioButton hybridButton = new JRadioButton("Hybrid");
        private JRadioButton electricButton = new JRadioButton("Electric");
        private JRadioButton otherButton = new JRadioButton("Other");
        private JTextField mpgInputField = new JTextField("");
        private JTextField weightInputField = new JTextField("");

        //buttons and fields for button section of panel
        //anonymous inner class for sales tax button
        private JButton taxButton = new JButton(new AbstractAction("Compute Sales Tax") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Automobile taxAuto = getCarData();
                if(taxAuto == null) {
                    taxField.setText("");
                } else {
                    taxField.setText("$" + String.format("%.2f", taxAuto.salesTax()));
                }
                //utilizes a First In First Out for storing the last 5 Automobile objects
                if(carCount<5) {
                    carHistory[carCount] = taxAuto;
                    carCount++;
                } else {
                    for(int i=0; i<4; i++){
                        carHistory[i]=carHistory[i+1];
                    }
                    carHistory[4]=taxAuto;
                }
            }
        });
        private JTextField taxField = new JTextField("");
        //anonymous inner class for clear fields button
        private JButton clearButton = new JButton(new AbstractAction("Clear Fields") {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeModelInputField.setText("");
                priceInputField.setText("");
                mpgInputField.setText("");
                weightInputField.setText("");
                taxField.setText("");
            }
        });
        //anonymous inner class for display report button
        private JButton displayButton = new JButton(new AbstractAction("Display Report") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Automobile Sales Tax History");
                //handles the case when invalidly entered Automobile objects are stored as null
                for(int i=0; i<carHistory.length; i++) {
                    if(carHistory[i]==null){
                        System.out.println("Invalid or no entry.");
                    } else {
                        System.out.println(carHistory[i].toString());
                    }
                }
            }
        });

        //panel constructor
        public SalesCalculatorPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            //configuring layout and appearance of make/model/price section
            JPanel makeModelPanel = new JPanel();
            makeModelPanel.setMaximumSize(new Dimension(250,75));
            makeModelPanel.setLayout(new GridLayout(2, 2, 5, 0));
            makeModelPanel.add(makeModelLabel);
            makeModelPanel.add(makeModelInputField);
            makeModelPanel.add(priceLabel);
            makeModelPanel.add(priceInputField);

            //configuring layout and appearance of automobile type section
            JPanel automobileTypePanel = new JPanel();
            automobileTypePanel.setMaximumSize(new Dimension(400,125));
            automobileTypePanel.setLayout(new GridLayout(1, 3));
            automobileTypePanel.setBorder(new TitledBorder("Automobile Type"));
            JPanel typePanel = new JPanel();
            typePanel.setLayout(new GridLayout(3, 1));
            automobileTypeButtons.add(hybridButton);
            automobileTypeButtons.add(electricButton);
            automobileTypeButtons.add(otherButton);
            typePanel.add(hybridButton, 0);
            typePanel.add(electricButton, 1);
            typePanel.add(otherButton, 2);
            JPanel mwLabelsPanel = new JPanel();
            mwLabelsPanel.setLayout(new GridLayout(3, 1));
            mwLabelsPanel.add(mpgLabel);
            mwLabelsPanel.add(weightLabel);
            JPanel mwInputPanel = new JPanel();
            mwInputPanel.setLayout(new GridLayout(3, 1));
            mwInputPanel.add(mpgInputField);
            mwInputPanel.add(weightInputField);
            automobileTypePanel.add(typePanel);
            automobileTypePanel.add(mwLabelsPanel);
            automobileTypePanel.add(mwInputPanel);

            //configuring layout and appearance of button section
            JPanel buttonPanel = new JPanel();
            buttonPanel.setMaximumSize(new Dimension(300,75));
            buttonPanel.setLayout(new GridLayout(2, 2, 5, 5));
            buttonPanel.add(taxButton);
            buttonPanel.add(taxField);
            taxField.setOpaque(false);
            taxField.setEditable(false);
            buttonPanel.add(clearButton);
            buttonPanel.add(displayButton);

            //laying out all sections with spacers
            add(Box.createRigidArea(new Dimension(450,5)));
            add(makeModelPanel);
            add(Box.createRigidArea(new Dimension(450,5)));
            add(automobileTypePanel);
            add(Box.createRigidArea(new Dimension(450,5)));
            add(buttonPanel);
            add(Box.createRigidArea(new Dimension(450,5)));
        }

        //method for creating appropriate object based on automobile type, handles non-int inputs and empty fields
        private Automobile getCarData() {
            Automobile input = null;
            try {
                String makeModel = makeModelInputField.getText();
                int priceInput = getInput(priceInputField);
                if (hybridButton.isSelected()) {
                    int mpgInput = getInput(mpgInputField);
                    input = new Hybrid(makeModel, priceInput, mpgInput);
                } else if (electricButton.isSelected()) {
                    int weightInput = getInput(weightInputField);
                    input = new Electric(makeModel, priceInput, weightInput);
                } else if (otherButton.isSelected()) {
                    input = new Automobile(makeModel, priceInput);
                }
            } catch(NumberFormatException nex) {
                JOptionPane.showMessageDialog(null, "Invalid input type or not all fields completed.", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            return input;
        }

        //method that that accepts string from price/mpg/weight fields and returns int
        private int getInput(JTextField inputTextField) throws NumberFormatException {
            String inputString = inputTextField.getText();
            return Integer.parseInt(inputString);
        }
    }
}

