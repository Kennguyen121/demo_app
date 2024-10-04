package giaodien;

import java.awt.BorderLayout;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrangChu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrangChu() {
		 ImageIcon imageIcon = new ImageIcon("../project/anh/backGround.jpg");
	        JLabel labelWithImage = new JLabel(imageIcon);
	        setLayout(new BorderLayout());
	        add(labelWithImage,BorderLayout.CENTER);
	}
}	
