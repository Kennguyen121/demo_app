package nhap1;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import dao.SanPham_Dao;
import entity.LoaiSanPham;
import entity.SanPham;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

public class ImageImportButton {
    private JFrame frame;
    private JLabel imageLabel;

    public static void main(String[] args) {
//    	Date a= 
    	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           
           // Use the format() method to format the date
    	   LocalDate localDate = LocalDate.parse("2000-02-02");

    	// Convert LocalDate to java.sql.Date
//    	   java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

    	// Convert java.sql.Date to java.util.Date
    		Date utilDate = Date.valueOf(localDate);
           String formattedDate = sdf.format(utilDate);
           
           // Print the formatted date
           System.out.println("Formatted Date: " + formattedDate);
   
    	try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ImageImportButton window = new ImageImportButton();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    	
    
    	
    }

    public ImageImportButton() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JButton btnNewButton = new JButton("Import Image");
//        btnNewButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent arg0) {
//                JFileChooser fileChooser = new JFileChooser();
//                int returnValue = fileChooser.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = fileChooser.getSelectedFile();
//                    System.out.println(selectedFile.getPath());
//                	String a = selectedFile.getPath();
////                	String desiredResult = ".." + a.substring(a.indexOf("\\project"));
//                	String desiredResult = "..\\project\\anh\\1bacXiuDa.jpg";
//                	SanPham_Dao spDao= new SanPham_Dao();
//                	SanPham sp =new SanPham(2, "trà Đào",desiredResult, true,(double) 10000f, "ly", new LoaiSanPham(1, "aa"));
//                	spDao.addSanPham(sp);
//                    System.out.println(desiredResult);
//                    ImageIcon img= new ImageIcon(desiredResult);
//                   
//                    Image newimg = img.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
//                    img = new ImageIcon(newimg);  // transform it back to ImageIcon
//                    imageLabel.setIcon(img);
//                }
//            }
//        });
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SanPham_Dao spDao= new SanPham_Dao();
				SanPham sp = spDao.findSanPham(2);
				String desiredResult = sp.getAnh();
				System.out.println(desiredResult);
              ImageIcon img= new ImageIcon("..\\project\\anh\\4mangomisu.png");
             
              Image newimg = img.getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
              img = new ImageIcon(newimg);  // transform it back to ImageIcon
              imageLabel.setIcon(img);
			}
		});
        frame.getContentPane().add(btnNewButton);
        
        imageLabel = new JLabel("");
        frame.getContentPane().add(imageLabel);
    }
}
