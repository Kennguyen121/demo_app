package giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import com.formdev.flatlaf.FlatLightLaf;

import dao.TaiKhoan_Dao;
import entity.TaiKhoan;
import xuly.NameOfLogo;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton trangChuButton;
	private JButton thongKeButton;
	private JButton dangxuatButton;
	private ImageIcon logo;
	private TrangChu trangChu;
	private GiaoDienQuanLy quanLy;
	private GiaoDienThongKe thongKe;
	private GiaoDienDatMon datmon;
	private JButton datbanButton;
	private GiaoDienSanPham sanPham;
	private JButton sanPhamButton;
	private JButton quanLyButton;
	private TaiKhoan taiKhoanDangNhap;
	private static final Color BG_COLOR = new Color(251, 227, 248);

		public Main(String tenDN) {
			try {
				UIManager.setLookAndFeel(new FlatLightLaf());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TaiKhoan_Dao tk =  new TaiKhoan_Dao();
			 taiKhoanDangNhap = tk.findTaiKhoanTheoTenDN(tenDN);
			if(taiKhoanDangNhap!=null) {
				String mess= "Xin chào "+ taiKhoanDangNhap.getNhanVien().getTenNhanVien()+" ( " +taiKhoanDangNhap.getNhanVien().getLoaiNhanVien()+" )";
				JOptionPane.showMessageDialog(null, mess);
			}
			trangChu = new TrangChu();
			datmon = new GiaoDienDatMon(taiKhoanDangNhap);
			quanLy = new GiaoDienQuanLy();
			sanPham= new GiaoDienSanPham();
			thongKe = new GiaoDienThongKe();
			
			
			trangChu.add(header(), BorderLayout.NORTH);
			datmon.add(header(), BorderLayout.NORTH);
			quanLy.add(header(), BorderLayout.NORTH);
			sanPham.add(header(), BorderLayout.NORTH);
			thongKe.add(header(), BorderLayout.NORTH);
	
			setContentPane(trangChu);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);
	
		}

	public JPanel header() {

		// logo
		logo = new ImageIcon("../project/anh/Free-Logos-PNG-Clipart.png");
		Image newimg = logo.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// scale it the smooth way
		logo = new ImageIcon(newimg); // transform it back to ImageIcon
		// Add the resized logo to the JLabel
		JLabel logoLabel = new JLabel(logo);

		// tên của logo
		NameOfLogo nameOfLogo = new NameOfLogo("Hippotamus", new Color(178, 50, 253), Color.GRAY, 40);

		// các button chuyển trang
		trangChuButton = new JButton("Trang Chủ", FontIcon.of(FontAwesomeSolid.HOME, 20));
		datbanButton = new JButton("Đặt Món", FontIcon.of(FontAwesomeSolid.SHOPPING_BASKET, 20));
		sanPhamButton =  new JButton("Sản phẩm", FontIcon.of(FontAwesomeSolid.PLUS_CIRCLE, 20));
		quanLyButton = new JButton("Quản Lý", FontIcon.of(FontAwesomeSolid.PLUS_CIRCLE, 20));
		thongKeButton = new JButton("Thống Kê", FontIcon.of(FontAwesomeSolid.CHART_BAR, 20));
		dangxuatButton = new JButton("Đăng xuất", FontIcon.of(FontAwesomeRegular.USER_CIRCLE, 20));

		// chỉnh appearance của các button
		trangChuButton.setFont(new Font("", Font.BOLD, 20));
		datbanButton.setFont(new Font("", Font.BOLD, 20));
		sanPhamButton.setFont(new Font("", Font.BOLD, 20));
		quanLyButton.setFont(new Font("", Font.BOLD, 20));
		thongKeButton.setFont(new Font("", Font.BOLD, 20));
		dangxuatButton.setFont(new Font("", Font.BOLD, 20));

		// set background
		trangChuButton.setBackground(BG_COLOR);
		datbanButton.setBackground(BG_COLOR);
		quanLyButton.setBackground(BG_COLOR);
		thongKeButton.setBackground(BG_COLOR);
		dangxuatButton.setBackground(BG_COLOR);
		sanPhamButton.setBackground(BG_COLOR);
		// thêm actionPerformed cho các button
		trangChuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(trangChu);
				revalidate(); // Refresh the frame
			}
		});
		datbanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(datmon);
				revalidate(); // Refresh the frame
			}
		});
		sanPhamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(sanPham);
				revalidate(); // Refresh the frame
			}
		});
		quanLyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(quanLy);
				revalidate(); // Refresh the frame
			}
		});
		thongKeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(thongKe);
				revalidate(); // Refresh the frame
			}
		});
		dangxuatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice= JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất chứ?", "Message", JOptionPane.YES_NO_OPTION);
				if(choice == JOptionPane.YES_OPTION) {
					dispose();
					Login login =  new Login();
					login.setVisible(true);
					revalidate(); // Refresh the frame
				}
			}
		});

		// thêm các element vào box
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(20));
		box.add(logoLabel);
		box.add(Box.createHorizontalStrut(30));
		box.add(nameOfLogo);
		box.add(Box.createHorizontalGlue());
		box.add(trangChuButton);
		box.add(Box.createHorizontalStrut(20));
		box.add(datbanButton);
		box.add(Box.createHorizontalStrut(20));
		box.add(sanPhamButton);
		box.add(Box.createHorizontalStrut(20));
		box.add(thongKeButton);
		if(taiKhoanDangNhap.getNhanVien().getLoaiNhanVien().compareToIgnoreCase("Quản lý")==0) {
			box.add(Box.createHorizontalStrut(20));
			box.add(quanLyButton);
		}
		box.add(Box.createHorizontalStrut(20));
		box.add(dangxuatButton);
		box.add(Box.createHorizontalStrut(50));

		// chèn vào panel và return
		JPanel headerPane = new JPanel(new BorderLayout());
		headerPane.add(box, BorderLayout.CENTER);
		headerPane.setBackground(BG_COLOR);
		return headerPane;
	}
}
