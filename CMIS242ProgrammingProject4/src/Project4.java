/**
 * Project4.java
 * Brian Yu
 * 3/8/2020
 * This class constructs a GUI that allows users to add, remove, and display entries of properties
 * in a real estate database.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.TreeMap;

public class Project4 {
    //Main method to put together the GUI
    public static void main(String[] args) {
        JFrame project4 = new JFrame("Real Estate Database");
        project4.setSize(325,250);
        project4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        project4.add(new realEstateDbPanel());
        project4.setVisible(true);
    }
    //Inner class that puts together all the elements of the GUI on the panel
    private static class realEstateDbPanel extends JPanel {
        //JLabels for all the fields
        private JLabel transactionLabel = new JLabel("Transaction No:");
        private JLabel addressLabel = new JLabel("Address:");
        private JLabel bedroomsLabel = new JLabel("Bedrooms:");
        private JLabel squareLabel = new JLabel("Square Footage:");
        private JLabel priceLabel = new JLabel("Price:");
        //JComboBox and entries for database operations
        private String[] dbOperations = {"Insert", "Delete", "Find"};
        private JComboBox dbList = new JComboBox(dbOperations);
        //JComboBox and entries for status
        private Status[] statuses = {Status.FOR_SALE, Status.UNDER_CONTRACT, Status.SOLD};
        private JComboBox statusList = new JComboBox(statuses);
        //Text fields for property information and transaction id
        private JTextField transactionField = new JTextField("");
        private JTextField addressField = new JTextField("");
        private JTextField bedroomsField = new JTextField("");
        private JTextField squareField = new JTextField("");
        private JTextField priceField = new JTextField("");
        //Tree map database to store properties and transaction ids as key/value pairs
        TreeMap<Integer, Property> propertyDb = new TreeMap<>();
        //Constructor for panel object
        public realEstateDbPanel() {
            //Sets layout for entire panel
            setLayout(new GridLayout(7,2, 7,10));
            //Populates entire panel
            this.add(transactionLabel); this.add(transactionField);
            this.add(addressLabel); this.add(addressField);
            this.add(bedroomsLabel); this.add(bedroomsField);
            this.add(squareLabel); this.add(squareField);
            this.add(priceLabel); this.add(priceField);
            /**Action listener added to button to perform appropriate option based on selection
             * Insert: validates entered values for appropriate format, checks for duplicate transaction id
             * and then stores entered transaction id and property information as key value pair in database
             * Delete: validates entered values for appropriate format, checks if transaction id exists
             * and then deletes appropriate key value pair from database
             * Find: validates entered values for appropriate format, checks if transaction id exists
             * and then displays appropriate information in pop-up window
            **/
            JButton processButton = new JButton(new AbstractAction("Process") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String processOption = String.valueOf(dbList.getSelectedItem());
                    try {
                        switch (processOption) {
                            case "Insert":
                                checkForDuplicates(getTransactionId());
                                propertyDb.put(getTransactionId(), getPropertyInfo());
                                JOptionPane.showMessageDialog(null, "Property successfully stored in database.");
                                break;
                            case "Delete":
                                checkforExisting(getTransactionId());
                                propertyDb.remove(getTransactionId());
                                JOptionPane.showMessageDialog(null, "Property successfully removed from database.");
                                break;
                            case "Find":
                                checkforExisting(getTransactionId());
                                Property getProperty = propertyDb.get(getTransactionId());
                                JOptionPane.showMessageDialog(null, getProperty.toString());
                                break;
                        }
                    } catch(NumberFormatException nex) {
                        JOptionPane.showMessageDialog(null, "Incorrect format for values entered.");
                    } catch(DuplicateProperty dex) {
                        JOptionPane.showMessageDialog(null, "Transaction id already exists in database.");
                    } catch(PropertyNotFound pex) {
                        JOptionPane.showMessageDialog(null, "Transaction id not found in database.");
                    }
                }
            });
            /**Action listener added to button to change status of property tied to transaction id after checking validating
            values for appropriate format and checking to see if transaction id exists
             **/
            JButton changeButton = new JButton(new AbstractAction("Change Status") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Status statusOption = (Status) statusList.getSelectedItem();
                        checkforExisting(getTransactionId());
                        Property changeProperty = propertyDb.get(getTransactionId());
                        changeProperty.changeState(statusOption);
                        propertyDb.put(getTransactionId(), changeProperty);
                        JOptionPane.showMessageDialog(null, "Property status successfully changed in database");
                    } catch(PropertyNotFound pex) {
                        JOptionPane.showMessageDialog(null, "Transaction id not found in database.");
                    } catch(NumberFormatException nex) {
                        JOptionPane.showMessageDialog(null, "Incorrect format for values entered.");
                    }
                }
            });

            this.add(processButton); this.add(dbList);
            this.add(changeButton); this.add(statusList);
        }
        //Local method to read entered property information and throws exception if wrong format entered
        private Property getPropertyInfo() throws NumberFormatException {
            String address = addressField.getText();
            int bedrooms = getInput(bedroomsField);
            int squareFt = getInput(squareField);
            int price = getInput(priceField);
            return new Property(address, bedrooms, squareFt, price);
        }
        //Local method read entered transaction id and throws exception if wrong format entered
        private int getTransactionId() throws NumberFormatException {
            return getInput(transactionField);
        }
        //Local method to verify property doesn't exist in db before insertion
        private void checkForDuplicates(int transactionId) throws DuplicateProperty{
            if(propertyDb.containsKey(transactionId)) {
                throw new DuplicateProperty();
            }
        }
        //Local method to verify property exists in db before deletion/find
        private void checkforExisting(int transactionId) throws PropertyNotFound {
            if(!propertyDb.containsKey(transactionId)) {
                throw new PropertyNotFound();
            }
        }
        //Local method to convert entered Strings to integers
        private int getInput(JTextField inputTextField) throws NumberFormatException {
            String inputString = inputTextField.getText();
            return Integer.parseInt(inputString);
        }
        //Defines custom exception if property already exists
        private class DuplicateProperty extends Exception {
            public DuplicateProperty() {
                super();
            }
        }
        //Defines custom exception if property does not exist
        private class PropertyNotFound extends Exception {
            public PropertyNotFound() {
                super();
            }
        }
    }
}
