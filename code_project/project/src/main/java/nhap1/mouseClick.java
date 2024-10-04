package nhap1;

import javax.swing.*;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mouseClick extends JFrame {
    public mouseClick() {
        JPanel a = new JPanel();
        JLabel label1 = createJlabel("dong 1");
        JLabel label2 = createJlabelDontHaveMouseClick("dong 2");

        // Add custom mouse listener to label1
        label1.setName("label1");
        label1.addMouseListener(new MyMouseListener());

        // Add custom mouse listener to label2
        label2.setName("label2");
        label2.addMouseListener(new MyMouseListener());

        a.add(label1);
        a.add(label2);
        JButton button= new JButton("button 1");
        button.setName("button");
        button.addMouseListener(new MyMouseListener());
        a.add(button);

        add(a);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JLabel createJlabel(String text) {
        return new JLabel(text);
    }

    public JLabel createJlabelDontHaveMouseClick(String text) {
        return new JLabel(text);
    }

    public static void main(String[] args) {
        new mouseClick();
    }

    // Custom MouseListener class
    class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
        	 Component clickedComponent = e.getComponent();

            if(clickedComponent instanceof JLabel) {
            	JLabel clickedLabel = (JLabel) e.getSource();
                String labelName = clickedLabel.getName();
                if ("label1".equals(labelName)) {
                    // Handle click for label1
                    System.out.println("Label 1 clicked!");
                    // Add your custom logic here
                } else if ("label2".equals(labelName)) {
                    // Handle click for label2
                    System.out.println("Label 2 clicked!");
                    // Add your custom logic here
                }
            }
            else if(clickedComponent instanceof JButton) {
            	JButton clickedLabel = (JButton) e.getSource();
                String labelName = clickedLabel.getName();
            	if("button1".equals(labelName))
            		System.out.println("button 1 clicked!");
            	else 
            		System.out.println("sai ten clicked!");
            }

        }
    }
}
