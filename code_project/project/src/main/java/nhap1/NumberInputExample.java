package nhap1;
import javax.swing.*;
import java.awt.*;

public class NumberInputExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Input Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a SpinnerModel to define the range and step size of the spinner
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1); // Initial value, min value, max value, step

        // Create a JSpinner with the SpinnerModel
        JSpinner spinner = new JSpinner(spinnerModel);

        // Customize the spinner's appearance
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        }

        // Add the spinner to the frame
        frame.add(spinner, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
