package nhap1;

import javax.swing.*;
import java.awt.*;

public class ImageLabelExample {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null); // Use null layout

        // Create an ImageIcon
        ImageIcon imageIcon = new ImageIcon("../project/anh/backGround.jpg");
        Image img= imageIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imageIcon= new ImageIcon(img);
        // Create a JLabel with the image icon
        JLabel labelWithImage = new JLabel(imageIcon);
        labelWithImage.setBounds(0, 0, imageIcon.getIconWidth(),imageIcon.getIconHeight() ); // Set bounds to fill the frame

        // Create a JLabel for the text
        JLabel labelText = new JLabel("Your Text");
        labelText.setBounds(10, 10, 100, 20); // Set bounds to position at top left
        labelText.setForeground(Color.BLACK); // Set the font color

        // Add the labels to the frame
        frame.add(labelWithImage);
        frame.add(labelText);

        // Make the frame visible
        frame.setVisible(true);
    }
}