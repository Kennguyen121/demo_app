package nhap1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiPageApp extends JFrame {
    private JPanel page1;
    private JPanel page2;
    private JButton btnPage1;
    private JButton btnPage2;

    public MultiPageApp() {
        setTitle("Multi-Page App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        page1 = new page1();
        page2 = new page2();

//        // Create buttons
//        btnPage1 = ;
//        btnPage2 = 
        
        page1.add(createPanel(), BorderLayout.CENTER);
        page2.add(createPanel(), BorderLayout.CENTER);

        // Add action listeners to buttons


        setContentPane(page1);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultiPageApp());
    }
    public JPanel createPanel() {
        btnPage2 = new JButton("Go to Page 2");
        btnPage1 = new JButton("Go to Page 1");
        btnPage1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(page1);
                revalidate(); // Refresh the frame
            }
        });

        btnPage2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(page2);
                revalidate(); // Refresh the frame
            }
        });
        JPanel a= new JPanel();
        a.add(btnPage1);
        a.add(btnPage2);
		return  a;
	}
}
class page1 extends JPanel{
	public page1() {
	       setLayout(new BorderLayout());
	        
	        // Create a label
	        JLabel titleLabel = new JLabel("Welcome to Page 1");
	        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font

	        
	        // Add components to the panel
	        add(titleLabel, BorderLayout.NORTH);
	}
}
class page2 extends JPanel{
	public page2() {
	       setLayout(new BorderLayout());

	        JLabel titleLabel = new JLabel("Welcome to Page 2");
	        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font

	        add(titleLabel, BorderLayout.NORTH);
	}
}