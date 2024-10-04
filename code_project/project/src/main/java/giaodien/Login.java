package giaodien;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import dao.TaiKhoan_Dao;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import java.util.regex.Pattern;


public class Login extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tenDangNhapF;
    private JPasswordField passwordField;
	private JButton quenMK;
	private TaiKhoan_Dao taiKhoan_Dao = new TaiKhoan_Dao();
	private static final Color BG_COLOR = new Color(251, 227, 248);
    public Login() {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        initialize();
    }

    private void initialize() {
    	ImageIcon logo = new ImageIcon("../project/anh/Free-Logos-PNG-Clipart.png");
    	Image newImg= logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    	logo = new ImageIcon(newImg);
    	JLabel logoLabel = new JLabel(logo);
    	
        JLabel dangNhap = new JLabel("Đăng Nhập");
        dangNhap.setFont(new Font("",Font.BOLD,30));
        JLabel lblUsername = new JLabel("  Tên đăng nhập  ");
        JLabel lblPassword = new JLabel("  Mật khẩu   ");
        lblPassword.setPreferredSize(lblUsername.getPreferredSize());
        tenDangNhapF = new JTextField();
        passwordField = new JPasswordField();
        JPanel line1= new JPanel();
        line1.add(dangNhap);
        line1.setBackground(BG_COLOR);
        JPanel line2= new JPanel(new BorderLayout());
        line2.add(lblUsername,BorderLayout.WEST);
        line2.add(tenDangNhapF,BorderLayout.CENTER);
        line2.add(new JLabel("    "),BorderLayout.EAST);
        line2.setBackground(BG_COLOR);
        JPanel line3= new JPanel(new BorderLayout());
        line3.add(lblPassword,BorderLayout.WEST);
        line3.add(passwordField,BorderLayout.CENTER);
        line3.add(new JLabel("    "),BorderLayout.EAST);
        line3.setBackground(BG_COLOR);
        JPanel line4= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        line4.add(quenMK= new JButton("<html><u>Quên mật khẩu?</u></html>"));
        quenMK.setBackground(BG_COLOR);
        line4.setBackground(BG_COLOR);
        quenMK.setBorder(null);
        JButton btnLogin = new JButton("Đăng nhập");
        JButton btnThoat = new JButton("Thoát");
      
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the login page
            }
        });
        quenMK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the login page
                QuenMK a=  new QuenMK();
                a.setVisible(true);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	TaiKhoan tk =taiKhoan_Dao.findTaiKhoanTheoTenDN(tenDangNhapF.getText());
            	if(tk ==null) {
            		JOptionPane.showMessageDialog(null,"Tên đăng nhập hoặc mật khẩu không đúng");
            		return;
            	}
            	else if(tk.getMatKhau().compareTo(new String(passwordField.getPassword()))!=0) {
            		JOptionPane.showMessageDialog(null,"Tên đăng nhập hoặc mật khẩu không đúng");
            		return;
            	}
            	dispose();
            	revalidate();
            	Main m= new Main(tenDangNhapF.getText());
            	m.setVisible(true);      	
            }
        });    
     // Get the InputMap for the WHEN_IN_FOCUSED_WINDOW condition
        InputMap inputMap = btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Map the ENTER key to the "click" action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "click");

        // Now add an ActionMap entry that performs the click action
        btnLogin.getActionMap().put("click", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick();
            }
        });

      
        JPanel line5= new JPanel();
        line5.add(btnLogin);
        line5.add(Box.createHorizontalStrut(20));
        line5.add( btnThoat);
        line5.setBackground(BG_COLOR);
        Box box= Box.createVerticalBox();
        box.add(line1);
        box.add(Box.createVerticalStrut(10));
        box.add(line2);
        box.add(Box.createVerticalStrut(10));
        box.add(line3);
        box.add(line4);
        box.add(Box.createVerticalStrut(10));
        box.add(line5);

        JPanel frame = new JPanel(new BorderLayout());
        frame.add(logoLabel, BorderLayout.WEST);
        frame.add(box , BorderLayout.CENTER);
        frame.setBackground(BG_COLOR);
        add(frame);
        setTitle("Đăng nhập");
    	setSize(500,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    
}

class QuenMK extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tenDangNhapTF;
	private JTextField maNhanVienTF;
	private JTextField tenNhanVienTF;
	private JComboBox<String> loaiNhanVienCB;
	private JDateChooser ngaySinhDC;
	private JRadioButton nam;
	private JRadioButton nu;
	private TaiKhoan_Dao taiKhoan_Dao = new TaiKhoan_Dao();
	private TaiKhoan tk;
	public QuenMK() {
		init();
	}
	public void init() {
		JLabel xacThucLB = new JLabel("  Xác thực thông tin");
		xacThucLB.setFont(new Font("",Font.BOLD,30));
		JLabel tenDangNhap = new JLabel("  Tên đăng nhập   ");
		JLabel maNhanVien = new JLabel("  Mã nhân viên");
		JLabel tenNhanVien = new JLabel("  Tên nhân viên");
		JLabel loaiNhanVien = new JLabel("  Loại nhân viên");
		JLabel gioiTinh = new JLabel("  Giới tính  ");
		JLabel ngaySinh = new JLabel("  Ngày sinh");
		
		maNhanVien.setPreferredSize(tenDangNhap.getPreferredSize());
		tenNhanVien.setPreferredSize(tenDangNhap.getPreferredSize());
		loaiNhanVien.setPreferredSize(tenDangNhap.getPreferredSize());
		ngaySinh.setPreferredSize(tenDangNhap.getPreferredSize());
		
		tenDangNhapTF= new JTextField();
		maNhanVienTF= new JTextField();
		tenNhanVienTF= new JTextField();
		loaiNhanVienCB= new JComboBox<String>();
		loaiNhanVienCB.addItem("Quản lý");
		loaiNhanVienCB.addItem("Nhân viên");
		ButtonGroup gioiTinhG = new ButtonGroup();
		 nam = new JRadioButton("Nam");
		 nu = new JRadioButton("Nu");
		gioiTinhG.add(nam);
		gioiTinhG.add(nu);
		ngaySinhDC= new JDateChooser();
		ngaySinhDC.setDateFormatString("dd/MM/yyyy");
	
		JPanel line1= new JPanel();
		line1.add(xacThucLB);
		JPanel line2= new JPanel(new BorderLayout());
		line2.add(tenDangNhap,BorderLayout.WEST);
		line2.add(tenDangNhapTF,BorderLayout.CENTER);
		line2.add(new JLabel("   "),BorderLayout.EAST);
		
		
		JPanel line3= new JPanel(new BorderLayout());
		line3.add(maNhanVien,BorderLayout.WEST);
		line3.add(maNhanVienTF,BorderLayout.CENTER);
		line3.add(new JLabel("   "),BorderLayout.EAST);

		JPanel line4= new JPanel(new BorderLayout());
		line4.add(tenNhanVien,BorderLayout.WEST);
		line4.add(tenNhanVienTF,BorderLayout.CENTER);
		line4.add(new JLabel("   "),BorderLayout.EAST);
		Box hbox=Box.createHorizontalBox();
		hbox.add(loaiNhanVien);
		hbox.add(loaiNhanVienCB);
		hbox.add(Box.createHorizontalStrut(10));
		hbox.add(gioiTinh);
		hbox.add(nam);
		hbox.add(nu);
		JPanel line5= new JPanel(new BorderLayout());
		line5.add(hbox,BorderLayout.CENTER);
		line5.add(new JLabel("   "),BorderLayout.EAST);
		JPanel line6= new JPanel(new BorderLayout());
		line6.add(ngaySinh,BorderLayout.WEST);
		line6.add(ngaySinhDC,BorderLayout.CENTER);
		line6.add(new JLabel("   "),BorderLayout.EAST);
		JButton xacThucBT= new  JButton("Xác nhận thông tin");
		xacThucBT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// điều kiện check
				tk = taiKhoan_Dao.findTaiKhoanTheoTenDN(tenDangNhapTF.getText());
				if(tk==null) {
					JOptionPane.showMessageDialog(null,"Thông tin không chính xác");
					return;
				}
				Boolean check= checkThongTin(tk);
				if(check) {
					setContentPane(nhapLaiMatKhau());
					revalidate(); 
				}
				else {
					JOptionPane.showMessageDialog(null,"Thông tin không chính xác");
				}
			}
		});
		
		JButton troVe= new JButton("Trở về đăng nhập");
		troVe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Login lg= new Login();
				lg.setVisible(true);
			}
		});
		JPanel line7 = new JPanel();
		line7.add(xacThucBT);
		line7.add(Box.createHorizontalStrut(20));
		line7.add(troVe);
		
		Box vBox= Box.createVerticalBox();
		vBox.add(line1);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(line2);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(line3);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(line4);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(line5);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(line6);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(line7);
		
		add(vBox);
		setSize(550,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public boolean checkThongTin(TaiKhoan tk) {
		if(tk==null)
			return false;
		NhanVien nv= tk.getNhanVien();
		Boolean check= true;
		check &= nv.getMaNhanVien().toString().compareTo(maNhanVienTF.getText())==0;
		check &= nv.getTenNhanVien().compareTo(tenNhanVienTF.getText())==0;
		check &=nv.getLoaiNhanVien().compareTo(loaiNhanVienCB.getSelectedItem().toString())==0;
		Boolean gioiTinh= nam.isSelected();
		check &=nv.getGioiTinh()== gioiTinh;
		check &=nv.getNgaySinh().compareTo(ngaySinhDC.getDate())==0;
		return check;
	}
	public JPanel nhapLaiMatKhau() {
		JPanel nhapLai = new JPanel();
		JLabel mkMoi= new JLabel("Nhập mật khẩu mới");
		JLabel mkMoi2= new JLabel("Nhập lại mật khẩu   ");
		JLabel thongBao= new JLabel("");
		
		JTextField mkMoiF = new JTextField();
		mkMoiF.setPreferredSize(new Dimension(200, 25));
		JPasswordField mkMoi2F = new JPasswordField();
		mkMoi2F.setPreferredSize(new Dimension(200, 25));
		JButton xacNhan = new JButton("Xác nhận");
		xacNhan.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!Pattern.matches(".{5,}",mkMoiF.getText().trim())) {
					thongBao.setText("Mật khẩu phải có ít nhất 5 ký tự");	
				}
				else if(mkMoiF.getText().compareTo(new String(mkMoi2F.getPassword()))!=0) {
					thongBao.setText("Mật khẩu không khớp");	
				}
				else {
					thongBao.setText("");
					//xử lí lưu
					tk.setMatKhau(mkMoiF.getText());
					taiKhoan_Dao.updateTaiKhoan(tk);
					JOptionPane.showMessageDialog(null,"Đổi mật khẩu thành công");
					dispose();
					Login lg= new Login();
					lg.setVisible(true);
				
				}	
			}
		});
		JButton huyBo = new JButton("Hủy bỏ");
		huyBo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		JPanel line1 = new JPanel();
		line1.add(mkMoi);
		line1.add(mkMoiF);
		JPanel line2 = new JPanel();
		line2.add(mkMoi2);
		line2.add(mkMoi2F);
		JPanel line3 =new JPanel();
		line3.add(thongBao);
		JPanel line4 = new JPanel();
		line4.add(xacNhan);
		line4.add(huyBo);
		Box box = Box.createVerticalBox();
		box.add(line1);
		box.add(Box.createVerticalStrut(15));
		box.add(line2);
		box.add(Box.createVerticalStrut(15));
		box.add(line3);
		box.add(Box.createVerticalStrut(15));
		box.add(line4);
		nhapLai.add(box);
		return nhapLai;
	}
}