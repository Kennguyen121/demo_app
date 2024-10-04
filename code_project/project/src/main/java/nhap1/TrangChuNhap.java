package nhap1;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class TrangChuNhap extends JPanel implements MouseListener,ActionListener{

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */

	private JTextField tongTienTexField;
	private JButton thanhToanButton;
	private JButton huyDonHangButton;
	private JButton themDonHang;

	private JButton xoaSanPham;
	private JButton tatCaTheLoaiButton;
	private JButton cafeTheLoaiButton;
	private JButton traTheLoaiButton;
	private JButton doAnTheLoaiButton;
	private JButton nuocDCTheLoaiButton;
	private JButton khacTheLoaiButton;
	private JTextField tienKhachHangDuaTexField;
	private JTextField tienTraLaiTexField;
	private JPanel east;
	private JTextField timKiemInput;
	private JButton timKiemButton;

	private JButton anDonHang;
	private static final String CARD ="card";
	private static final String ANH ="anh";

	/**
	 * Create the frame.
	 */
	public TrangChuNhap() {	
		 setLayout(new BorderLayout());
		initEast();
		initcenter();
	}	
	public void initEast() {
		
		JPanel donHang1 = new JPanel(new BorderLayout());
		//cái donHang2 chỉ thêm vào để có cái nhìn đầu tiên về giao diện sau này sẽ xóa
	      JPanel donHang2 = new JPanel();

	      JTabbedPane tabs = new JTabbedPane();
	      tabs.add("Đơn Hàng 1", donHang1);
	      
	      Box p1Box= Box.createVerticalBox();
	      Double price= (double) 100000;
	      p1Box.add(createDonHang("path","tên sản phẩm hơi dài aaaaaaaaaaaaaaaaaaaaaaaaaaaa",price));
	      p1Box.add(createDonHang("path","tên sp",price));
	      
	      // dùng để chiếm chỗ khoảng trắng đằng sau và ngăn các element bị kéo dãn
	      p1Box.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE)));
	      JScrollPane p1ScrollPane = new JScrollPane(p1Box);
	  
	      donHang1.add(p1ScrollPane,BorderLayout.CENTER);
	      

	     tongTienTexField= new JTextField("0đ",19);
	      JPanel tongTien= new JPanel(new FlowLayout(FlowLayout.LEFT));
	      tongTien.add(new JLabel("Tổng tiền:    "));
	      tongTien.add(tongTienTexField);
	      
	      tienKhachHangDuaTexField= new JTextField("0đ",13);
	      JPanel tienKhachHangDua= new JPanel(new FlowLayout(FlowLayout.LEFT));
	      tienKhachHangDua.add(new JLabel("Tiền khách hàng đưa:  "));
	      tienKhachHangDua.add(tienKhachHangDuaTexField);
	      
	      tienTraLaiTexField= new JTextField("0đ",18);
	      JPanel tienTraLai= new JPanel(new FlowLayout(FlowLayout.LEFT));
	      tienTraLai.add(new JLabel("Tiền trả lại:    "));
	      tienTraLai.add(tienTraLaiTexField);
	      
	      Box thanhToanVaHuyDonHangBox=Box.createHorizontalBox();
	      thanhToanButton = new JButton("Thanh Toán");
	      huyDonHangButton = new JButton("Hủy Đơn Hàng", FontIcon.of(FontAwesomeSolid.ERASER,15));
	      thanhToanVaHuyDonHangBox.add(thanhToanButton);
	      thanhToanVaHuyDonHangBox.add(Box.createHorizontalStrut(10));
	      thanhToanVaHuyDonHangBox.add(huyDonHangButton);
	       
	      Box thanhToanBox= Box.createVerticalBox();
	      thanhToanBox.add(tongTien);
	      thanhToanBox.add(Box.createVerticalStrut(10));
	      thanhToanBox.add(tienKhachHangDua);
	      thanhToanBox.add(Box.createVerticalStrut(10));
	      thanhToanBox.add(tienTraLai);
	      thanhToanBox.add(Box.createVerticalStrut(10));
	      thanhToanBox.add(thanhToanVaHuyDonHangBox);
	      
		   JPanel thanhToanPanel= new JPanel();
		   thanhToanPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.black));
		    thanhToanPanel.add(thanhToanBox);
		    
		    donHang1.add(thanhToanPanel,BorderLayout.SOUTH);
		   
		   //sau này sẽ xóa dòng này
	      tabs.add("Đơn Hàng 2", donHang2); 
	      
	       themDonHang= new JButton("Thêm đơn hàng", FontIcon.of(FontAwesomeRegular.PLUS_SQUARE,15));
	       anDonHang= new JButton("Ẩn đơn hàng", FontIcon.of(FontAwesomeRegular.MINUS_SQUARE,15));
	       anDonHang.addActionListener(this);
	      JPanel themDonHangPanel= new JPanel();
	      themDonHangPanel.add(themDonHang);
	      themDonHangPanel.add(anDonHang);
	      themDonHangPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.gray));
	      
	      east = new JPanel(new BorderLayout());
	      
	      east.add(themDonHangPanel,BorderLayout.SOUTH);
	      east.add(tabs,BorderLayout.CENTER);
	      east.setVisible(false);
	 	 add(east,BorderLayout.EAST);
	 	 
	}
	public void initcenter() {
 
		tatCaTheLoaiButton = new JButton("Tất cả", FontIcon.of(FontAwesomeSolid.ALIGN_JUSTIFY, 10));
		cafeTheLoaiButton = new JButton("Cafe");
		traTheLoaiButton = new JButton("Trà");
		doAnTheLoaiButton = new JButton("Đồ ăn");
		nuocDCTheLoaiButton = new JButton("Nước đóng chai");
		khacTheLoaiButton = new JButton("Khác");
//		
		Box  theLoaiBox= Box.createHorizontalBox();
		theLoaiBox.add(Box.createHorizontalStrut(50));
		theLoaiBox.add(new JLabel("Thể loại"));
		theLoaiBox.add(Box.createHorizontalStrut(20));
		theLoaiBox.add(tatCaTheLoaiButton);
		theLoaiBox.add(Box.createHorizontalStrut(20));
		theLoaiBox.add(cafeTheLoaiButton);
		theLoaiBox.add(Box.createHorizontalStrut(20));
		theLoaiBox.add(traTheLoaiButton);
		theLoaiBox.add(Box.createHorizontalStrut(20));
		theLoaiBox.add(doAnTheLoaiButton);
		theLoaiBox.add(Box.createHorizontalStrut(20));
		theLoaiBox.add(nuocDCTheLoaiButton);
		theLoaiBox.add(Box.createHorizontalStrut(20));
		theLoaiBox.add(khacTheLoaiButton);
		
		//tìm kiếm 
		 timKiemInput= new JTextField("search");
		 timKiemInput.setPreferredSize(new Dimension(300, 25));
		 timKiemButton= new JButton();
		 FontIcon searchIcon=FontIcon.of(FontAwesomeSolid.SEARCH, 15);
		 timKiemButton.setIcon(searchIcon);
		 JPanel timKiemPanel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 timKiemPanel.add(timKiemInput);
		 timKiemPanel.add(timKiemButton);
		 
		 theLoaiBox.add(Box.createHorizontalGlue());
		 theLoaiBox.add(timKiemPanel);
		theLoaiBox.add(Box.createHorizontalStrut(20));
		 
//		JPanel thanhTheLoai = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		thanhTheLoai.add(theLoaiBox);

		

		JPanel arrayCard= new JPanel(new GridLayout(0, 4, 20, 20));
		
		//sau này sẽ thay thế
		Double price=(double) 100000;
		arrayCard.add(createCard("../project/anh/denNong.jpg", "Ten san pham", price));
		arrayCard.add(createCard("../project/anh/PHINDI_KEM_SUA.jpg", "Ten san pham", price));
		arrayCard.add(createCard("..\\project\\anh\\TRA_THANH_DAO-08.jpg", "Ten san pham", price));
		arrayCard.add(createCard("..\\project\\anh\\bacXiuDa.jpg", "Ten san pham", price));

		
		JScrollPane scrollPane = new JScrollPane(arrayCard);
		//làm thanh scroll mượt hơn
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		JPanel center = new JPanel(new BorderLayout());
		center.add(theLoaiBox,BorderLayout.NORTH);
		center.add(scrollPane,BorderLayout.CENTER);
	
		add(center,BorderLayout.CENTER);
		
	}
    private static void showEast(JPanel donHang) {
    	donHang.setVisible(true);
        int targetWidth = 300;
        final int[] currentWidth = {donHang.getWidth()};

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWidth[0] < targetWidth) {
                    currentWidth[0] += 100;
                    donHang.setPreferredSize(new Dimension(currentWidth[0], donHang.getHeight()));
                    donHang.revalidate();
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    private static void hideEast(JPanel donHang) {
    	 int targetWidth = 0;
         final int[] currentWidth = {donHang.getWidth()};
         Timer timer = new Timer(10, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (currentWidth[0] > targetWidth) {
                     currentWidth[0] -=100;
                     donHang.setPreferredSize(new Dimension(currentWidth[0], donHang.getHeight()));
                     donHang.revalidate();
                 } else {
                	 donHang.setVisible(false);
                     ((Timer) e.getSource()).stop();
                 }
             }
         });
         timer.start();
    }
	public JPanel createCard(String path, String name, Double price) {
		//sau này thay bằng path
		 ImageIcon icon = new ImageIcon(path);
		 Image newimg = icon.getImage().getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		 icon = new ImageIcon(newimg); 
		 JLabel anh= new JLabel(icon); 	
		 JLabel ten = new JLabel(name);
		 ten.setFont(new Font("", Font.PLAIN,20));
		 String p = price.toString() +"đ";
		 JLabel gia = new JLabel(p);
		 gia.setFont(new Font("", Font.PLAIN,20));
		 Box box = Box.createVerticalBox();
		 box.add(anh);
		 box.add(Box.createVerticalStrut(15));
		 box.add(ten);
		 box.add(gia);
		JPanel card = new JPanel();
		card.setName(CARD);
		card.addMouseListener(this);
		card.add(box);
		card.setBackground(Color.cyan);
		return card;
	}
	public JPanel createDonHang(String path, String name, Double price) {
		//ảnh sản phẩm
			//sau này thay ảnh bằng path
		 ImageIcon icon = new ImageIcon("../project/anh/Free-Logos-PNG-Clipart.png");
		 Image newimg = icon.getImage().getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		 icon = new ImageIcon(newimg); 
		 JLabel anh= new JLabel(icon); 
		 //tên và giá sản phẩm
		 JPanel ten = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 ten.add(new JLabel("<html><p width=\"200\">" + name + "</p></html>"));
		 ten.setBackground(Color.blue);
		 
		 JPanel gia = new JPanel(new FlowLayout(FlowLayout.LEFT));
		 String p = price.toString() +"đ";
		 gia.add( new JLabel(p));
		 gia.setBackground(Color.CYAN);
		
		 
		 // số lượng và nut xóa khỏi hóa đơn
	     SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1); // Initial value, min value, max value, step
	     // Create a JSpinner with the SpinnerModel,
	     JSpinner soLuong = new JSpinner(spinnerModel);
	     soLuong.setPreferredSize(new Dimension(130, 20));
	     xoaSanPham = new JButton(FontIcon.of(FontAwesomeRegular.TRASH_ALT,10));
	     
	     JPanel soLuongVaXoaB= new JPanel();
	     // Add the components to the panel
	     soLuongVaXoaB.add(soLuong);
	     soLuongVaXoaB.add(Box.createHorizontalStrut(15));
	     soLuongVaXoaB.add(xoaSanPham);
	     soLuongVaXoaB.setBackground(Color.red);
	     
		 Box thongTin= Box.createVerticalBox();
		 thongTin.add(ten);
		 thongTin.add(Box.createVerticalStrut(5));
		 thongTin.add(gia);
	     thongTin.add(soLuongVaXoaB);

	     JPanel sanPham= new JPanel(new FlowLayout(FlowLayout.LEFT));
	     sanPham.add(anh);
	     sanPham.add(thongTin);
	     
	     sanPham.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,Color.gray));
	     return sanPham;
	     
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Component clickedComponent= e.getComponent();
		if(clickedComponent instanceof JPanel) {
        	JPanel clickedLabel = (JPanel) e.getSource();
            String panelName = clickedLabel.getName();
			if(CARD.equals(panelName)) {
                if (!east.isVisible()) {              
                	showEast(east);
       
                }
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sc= e.getSource();
		if(sc.equals(anDonHang)) {
			hideEast(east);
		}
		
	}
}