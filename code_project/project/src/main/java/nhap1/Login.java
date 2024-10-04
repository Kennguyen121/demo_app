package nhap1;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Login() {
        initialize();
    }

    private void initialize() {
        JLabel dangNhap = new JLabel("Đăng Nhập");
        dangNhap.setFont(new Font("",Font.BOLD,30));
        JLabel lblUsername = new JLabel("  Tên đăng nhập  ");
        JLabel lblPassword = new JLabel("  Mật khẩu   ");
        lblPassword.setPreferredSize(lblUsername.getPreferredSize());
        tenDangNhapF = new JTextField();
        passwordField = new JPasswordField();
        JPanel line1= new JPanel();
        line1.add(dangNhap);
        
        JPanel line2= new JPanel(new BorderLayout());
        line2.add(lblUsername,BorderLayout.WEST);
        line2.add(tenDangNhapF,BorderLayout.CENTER);
        line2.add(new JLabel("    "),BorderLayout.EAST);
        JPanel line3= new JPanel(new BorderLayout());
        line3.add(lblPassword,BorderLayout.WEST);
        line3.add(passwordField,BorderLayout.CENTER);
        line3.add(new JLabel("    "),BorderLayout.EAST);
        JPanel line4= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        line4.add(quenMK= new JButton("<html><u>Quên mật khẩu?</u></html>"));
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
//            	check
            	
            }
        });
        JPanel line5= new JPanel();
        line5.add(btnLogin);
        line5.add(Box.createHorizontalStrut(20));
        line5.add( btnThoat);
        Box box= Box.createVerticalBox();
        box.add(line1);
        box.add(Box.createVerticalStrut(10));
        box.add(line2);
        box.add(Box.createVerticalStrut(10));
        box.add(line3);
        box.add(line4);
        box.add(Box.createVerticalStrut(10));
        box.add(line5);
        
        add(box);
        setTitle("Đăng nhập");
    	setSize(400,240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public Boolean checkAccount() {
    	Boolean check = true;
    	
    	if(check==false) {
    		JOptionPane.showMessageDialog(null,"Thông tin đăng nhập không chính xác");
    	}
		return check;
	}
}

class QuenMK extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		
		JTextField tenDangNhapTF= new JTextField();
		JTextField maNhanVienTF= new JTextField();
		JTextField tenNhanVienTF= new JTextField();
		JComboBox<String> loaiNhanVienCB= new JComboBox<String>();
		loaiNhanVienCB.addItem("Quản lý");
		loaiNhanVienCB.addItem("Nhân viên");
		ButtonGroup gioiTinhG = new ButtonGroup();
		JRadioButton nam = new JRadioButton("Nam");
		JRadioButton nu = new JRadioButton("Nu");
		gioiTinhG.add(nam);
		gioiTinhG.add(nu);
		JDateChooser ngaySinhDC= new JDateChooser();
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
				if(true) {
					setContentPane(nhapLaiMatKhau());
					revalidate(); 
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
		setSize(450,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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