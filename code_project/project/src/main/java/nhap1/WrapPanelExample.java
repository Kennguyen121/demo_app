package nhap1;

import javax.swing.*;
import java.awt.*;

public class WrapPanelExample extends JFrame {

    public WrapPanelExample() {
        JPanel contentPanel = new JPanel(new GridLayout(0,3, 20, 20));

        // Add some components to the panel
        for (int i = 0; i < 99; i++) {
            JLabel label = new JLabel("Label " + i);
            contentPanel.add(label);
        }

        // Create a scroll pane with vertical scrollbar policy
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setViewportView(contentPanel); 
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Set preferred size for content panel to ensure wrapping works
//        contentPanel.setPreferredSize(new Dimension(280, contentPanel.getPreferredSize().height));
        
        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WrapPanelExample::new);
    }
}
