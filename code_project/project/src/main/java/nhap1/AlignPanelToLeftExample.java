package nhap1;
import java.awt.*;
import javax.swing.*;

public class AlignPanelToLeftExample extends JFrame {
    public AlignPanelToLeftExample() {
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Left Aligned Panel"));
        panel1.setBackground(Color.RED);

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        
        // Create a horizontal Box
        Box box = Box.createHorizontalBox();
        
        // Add panel1 with glue to align it to the left
        box.add(panel1);
        box.add(Box.createHorizontalGlue());
        
        // Add other elements (buttons) to the right
        box.add(button1);
        box.add(Box.createHorizontalStrut(10)); // Add some space between elements
        box.add(button2);

        JPanel a= new JPanel(new FlowLayout(FlowLayout.LEFT));
        a.add(box);
        add(a,BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AlignPanelToLeftExample());
    }
}
