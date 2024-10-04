package giaodien;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GiaoDientaiKhoan extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GiaoDientaiKhoan() {
		 setLayout(new BorderLayout());
	     
	        // Create a label
	        JLabel titleLabel = new JLabel("Trang tài khoản");
	        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font
	        
	        // Create a button
	        JButton button = new JButton("Click Me!");
	        
	        // Create a text area
	        JTextArea textArea = new JTextArea();
	        textArea.setRows(5); // Set number of rows
	        textArea.setColumns(20); // Set number of columns
	        JScrollPane scrollPane = new JScrollPane(textArea); // Add a scroll pane
	        
	        // Add components to the panel
	        add(titleLabel, BorderLayout.SOUTH);
	        add(scrollPane, BorderLayout.CENTER);
//	        add(button, BorderLayout.SOUTH);
	}
}
