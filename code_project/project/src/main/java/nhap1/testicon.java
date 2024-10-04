package nhap1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.kordamp.ikonli.fontawesome5.FontAwesomeBrands;
import org.kordamp.ikonli.fontawesome5.FontAwesomeBrandsIkonHandler;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.swing.FontIcon;

public class testicon extends JFrame{
	public testicon() {
		// khai báo icon
		FontIcon a= FontIcon.of(FontAwesomeBrands.ACCESSIBLE_ICON);
		a.setIconSize(40);
		JLabel label = new JLabel(a);
		add(label,BorderLayout.CENTER);
		setVisible(true);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new testicon();
//		FontAwesomeBrandsIkonHandler  a= new FontAwesomeBrandsIkonHandler();
	}
}
