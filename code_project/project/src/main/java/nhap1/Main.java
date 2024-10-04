package nhap1;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JSplitPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel label1 = new JLabel("Component 1");
        JLabel label2 = new JLabel("Component 2");

        // Create JSplitPane with horizontal orientation
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, label1, label2);

        // Set initial divider location (adjust width of first component)
        splitPane.setDividerLocation(200); // Adjust the width as needed

        // Add JSplitPane to the frame
        frame.add(splitPane);

        // Set frame size and make it visible
        frame.setSize(400, 300); // Adjust the size as needed
        frame.setVisible(true);
    }
}
