import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WeightCalculator extends JFrame {
    private final double OUNCES_IN_GRAMS = 0.035;

    public WeightCalculator() {
        super("Weight Conversion");
        setSize(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add (new GramstoOuncesPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        WeightCalculator calculator = new WeightCalculator();
    }

    class GramstoOuncesPanel extends JPanel implements ActionListener {
        private JLabel gramsLabel = new JLabel("Grams", JLabel.CENTER);
        private JLabel ouncesLabel = new JLabel("Ounces", JLabel.CENTER);
        private JTextField gramsInputField = new JTextField("");
        private JTextField ouncesResultField = new JTextField("");
        private JButton convertButton = new JButton("Convert");
        private double gramsInput;
        private double ouncesResult;

        public GramstoOuncesPanel() {
            setLayout(new BorderLayout());
            JPanel gTOPanel = new JPanel();
            gTOPanel.setLayout(new GridLayout(2, 3));
            gTOPanel.add(gramsLabel, 0,0);
            gTOPanel.add(ouncesLabel, 0, 1);
            gTOPanel.add(gramsInputField, 1,0);
            gTOPanel.add(ouncesResultField, 1,1);
            gTOPanel.add(convertButton,1,2);
            convertButton.addActionListener(this);
            ouncesResultField.setBackground(Color.lightGray);
            ouncesResultField.setEditable(false);
            add(gTOPanel, BorderLayout.CENTER);
        }

        public void actionPerformed(ActionEvent e) {
            String gramString = gramsInputField.getText();
            gramsInput = Double.parseDouble(gramString);
            ouncesResult = gramsInput*OUNCES_IN_GRAMS;
            String ouncesString = String.format("%.3f", ouncesResult);
            ouncesResultField.setText(ouncesString);
        }
    }
}

