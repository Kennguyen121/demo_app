package giaodien;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.Ban_Dao;
import dao.LoaiSanPham_Dao;
import dao.NhanVien_Dao;
import dao.TaiKhoan_Dao;
import entity.Ban;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.TaiKhoan;
import xuly.FormatTienVaDate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class GiaoDienQuanLy extends JPanel implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton quanLyLoaiSP;
	private JButton quanLyNhanVien;
	private JButton quanLyTaiKhoan;
	private JButton quanLyBan;
	private JTable table;
	private JTextField LSPMaLoaiF;
	private JTextField NVmaNV;
	private JComboBox<String> NVloaiNV;
	private JTextField NVtenNV;
	private JDateChooser NVngaySinh;
	private JTextField NVdiaChi;
	private JComboBox<String> NVgioiTinh;
	private JTextField TKmaNV;
	private JTextField TKtenDN;
	private JTextField TKmatKhau;
	private JTextField BmaBan;
	private JComboBox<String> BtrangThai;
	private JButton themBtn;
	private JButton xoaBtn;
	private JButton suaBtn;
	private JButton clearBtn;
	private JPanel listButton;
	private JPanel giaoDien;
	private Integer loai=1;
	private LoaiSanPham_Dao loaiSanPham_Dao =new LoaiSanPham_Dao();
	private NhanVien_Dao nhanVien_Dao =new NhanVien_Dao();
	private TaiKhoan_Dao taiKhoan_Dao =new TaiKhoan_Dao();
	private Ban_Dao ban_Dao =new Ban_Dao();
	private FormatTienVaDate fo = new FormatTienVaDate();
	private JTextField LSPTenLoaiF;
	public GiaoDienQuanLy() {
		giaoDien= new JPanel(new BorderLayout());
		giaoDien.add(initCenter(),BorderLayout.CENTER);
		readData();
		setLayout(new BorderLayout());
		add(giaoDien);
	}
	public JPanel initCenter() {
		JPanel center= new JPanel(new BorderLayout());
		JScrollPane left = new JScrollPane();
		JPanel right = new JPanel(new BorderLayout());
		listButton =initButton();
		if(loai==1) {
	
			left=initLeft(tenCotQuanLyLoaiSp());
			right.add(initChiTietLoaiSanPham(),BorderLayout.CENTER);
		}
		else if(loai==2) {
			left=initLeft(tenCotquanLyNhanVien());
			right.add(initChiTietNhanVien(),BorderLayout.CENTER);
		}
		else if(loai==3) {
			left=initLeft(tenCotquanLyTaiKhoan());
			right.add(initChiTietTaiKhoan(),BorderLayout.CENTER);
		}
		else if(loai==4) {
			left=initLeft(tenCotquanLyBan());
			right.add(initChiTietBan(),BorderLayout.CENTER);
		}
		right.add(initNorth(),BorderLayout.NORTH);
		right.add(listButton,BorderLayout.SOUTH);
		JSplitPane splitPane= new JSplitPane(SwingConstants.VERTICAL, left, right);
		splitPane.setDividerLocation(900);
		center.add(splitPane);

		return center;
	}
	public JScrollPane initLeft(Object[] cot ) {
		DefaultTableModel model= new DefaultTableModel(null, cot);
		table= new JTable(model);
		table.setBorder(new LineBorder(Color.BLACK,3));
		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(this);
		return scrollPane;
	}
	public JPanel initNorth() {
		quanLyLoaiSP= new JButton("Quản lý loại sản phẩm");
		quanLyNhanVien= new JButton("Quản lý nhân viên");
		quanLyTaiKhoan = new JButton("Quản lý tài khoản");
		quanLyBan = new JButton("Quản lý bàn");
		
		quanLyLoaiSP.setFont(new Font("", Font.BOLD, 18));
		quanLyNhanVien.setFont(new Font("", Font.BOLD, 18));
		quanLyTaiKhoan.setFont(new Font("", Font.BOLD, 18));
		quanLyBan.setFont(new Font("", Font.BOLD, 18));
		
		quanLyLoaiSP.addActionListener(this);
		quanLyNhanVien.addActionListener(this);
		quanLyTaiKhoan.addActionListener(this);
		quanLyBan.addActionListener(this);
		
		JPanel line1 = new JPanel(new GridLayout(2, 2, 10, 10));
		line1.add(quanLyLoaiSP);
		line1.add(quanLyNhanVien);
		line1.add(quanLyTaiKhoan);
		line1.add(quanLyBan);
		 line1.setBorder(new EmptyBorder(30, 10, 10, 10));
		return line1;
	}
	public Object[] tenCotQuanLyLoaiSp() {
		Object[] cot= {"Mã loại sản phẩm","Tên loại sản phẩm"};
		return cot;
	}
	public Object[] tenCotquanLyNhanVien() {
		Object[] cot= {"Mã nhân viên","Tên nhân viên","Loại nhân viên","Giới tính","Ngày sinh","Địa chỉ"};
		return cot;
	}
	public Object[] tenCotquanLyTaiKhoan() {
		Object[] cot= {"Mã nhân viên","Tên đăng nhập","Mật khẩu"};
		return cot;
	}
	public Object[] tenCotquanLyBan() {
		Object[] cot= {"Mã bàn","Trạng thái"};
		return cot;
	}
	public JPanel initChiTietLoaiSanPham() {
		JLabel thongTin = new JLabel("Thông tin loại sản phẩm");
		thongTin.setFont(new Font("", Font.BOLD, 30));
		JLabel maLoai= new JLabel("Mã loại sản phẩm");
		JLabel tenLoai= new JLabel("Tên loại sản phẩm");
		maLoai.setPreferredSize(tenLoai.getPreferredSize());
		LSPMaLoaiF= new JTextField(30);
		
		LSPTenLoaiF= new JTextField();
		LSPMaLoaiF.setPreferredSize(new Dimension(200,40));
		LSPTenLoaiF.setPreferredSize(new Dimension(320,40));
		JPanel line1 = new JPanel();
		line1.add(maLoai);
		line1.add(LSPMaLoaiF);
		JPanel line2 = new JPanel();
		line2.add(tenLoai);
		line2.add(LSPTenLoaiF);
		Box box =Box.createVerticalBox();
		box.add(thongTin);
		box.add(Box.createVerticalStrut(20));
		box.add(line1);
		box.add(Box.createVerticalStrut(20));
		box.add(line2);
	    JPanel traVe = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weighty = 1; // Vertical weight to expand vertically
	    traVe.add(box, gbc);

		return traVe;
	}
	public JPanel initChiTietNhanVien() {
		JLabel thongTin = new JLabel("Thông tin nhân viên");
		thongTin.setFont(new Font("", Font.BOLD, 30));
		JLabel maNv= new JLabel("Mã nhân viên");
		JLabel tenNV= new JLabel("Tên nhân viên");
		JLabel loaiNV= new JLabel("Loại nhân viên");
		JLabel gioiTinh= new JLabel("Giới tính");
		JLabel ngaySinh= new JLabel("Ngày sinh");
		JLabel diaChi= new JLabel("Địa chỉ");
		
		maNv.setPreferredSize(loaiNV.getPreferredSize());
		tenNV.setPreferredSize(loaiNV.getPreferredSize());
		gioiTinh.setPreferredSize(loaiNV.getPreferredSize());
		ngaySinh.setPreferredSize(loaiNV.getPreferredSize());
		diaChi.setPreferredSize(loaiNV.getPreferredSize());
		
		NVmaNV= new JTextField(30);
		NVtenNV= new JTextField(30);
		NVloaiNV= new JComboBox<String>();
		ArrayList<NhanVien> arrayNhanViens = nhanVien_Dao.getNhanVien();
		for (NhanVien nhanVien : arrayNhanViens) {
			NVloaiNV.addItem(nhanVien.getLoaiNhanVien());
		}
		NVgioiTinh= new JComboBox<String>();
		NVgioiTinh.addItem("Nữ");
		NVgioiTinh.addItem("Nam");
		NVngaySinh= new JDateChooser();
		NVngaySinh.setDateFormatString("dd/MM/yyyy");
		NVdiaChi= new JTextField(30);
		
		NVmaNV.setPreferredSize(new Dimension(200,40));
		NVtenNV.setPreferredSize(new Dimension(200,40));
		NVloaiNV.setPreferredSize(new Dimension(320,40));
		NVgioiTinh.setPreferredSize(new Dimension(320,40));
		NVngaySinh.setPreferredSize(new Dimension(320,40));
		NVdiaChi.setPreferredSize(new Dimension(200,40));
		JPanel line1 = new JPanel();
		line1.add(maNv);
		line1.add(NVmaNV);
		JPanel line2 = new JPanel();
		line2.add(tenNV);
		line2.add(NVtenNV);
		JPanel line3 = new JPanel();
		line3.add(loaiNV);
		line3.add(NVloaiNV);
		JPanel line4 = new JPanel();
		line4.add(gioiTinh);
		line4.add(NVgioiTinh);
		JPanel line5 = new JPanel();
		line5.add(ngaySinh);
		line5.add(NVngaySinh);
		JPanel line6 = new JPanel();
		line6.add(diaChi);
		line6.add(NVdiaChi);
		Box box =Box.createVerticalBox();
		box.add(thongTin);
		box.add(Box.createVerticalStrut(20));
		box.add(line1);
		box.add(Box.createVerticalStrut(20));
		box.add(line2);
		box.add(Box.createVerticalStrut(20));
		box.add(line3);
		box.add(Box.createVerticalStrut(20));
		box.add(line4);
		box.add(Box.createVerticalStrut(20));
		box.add(line5);
		box.add(Box.createVerticalStrut(20));
		box.add(line6);
	    JPanel traVe = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weighty = 1; // Vertical weight to expand vertically
	    traVe.add(box, gbc);

		return traVe;
	}
	public JPanel initChiTietTaiKhoan() {
		JLabel thongTin = new JLabel("Thông tin tài khoản");
		thongTin.setFont(new Font("", Font.BOLD, 30));
		JLabel maNV= new JLabel("Mã nhân viên");
		JLabel tenDN= new JLabel("Tên đăng nhập   ");
		JLabel matKhau= new JLabel("Mật khẩu");
		maNV.setPreferredSize(tenDN.getPreferredSize());
		matKhau.setPreferredSize(tenDN.getPreferredSize());
		TKmaNV= new JTextField(30);
		TKtenDN= new JTextField(30);
		TKmatKhau= new JTextField(30);
		
		TKmaNV.setPreferredSize(new Dimension(200,40));
		TKtenDN.setPreferredSize(new Dimension(200,40));
		TKmatKhau.setPreferredSize(new Dimension(200,40));
		JPanel line1 = new JPanel();
		line1.add(maNV);
		line1.add(TKmaNV);
		JPanel line2 = new JPanel();
		line2.add(tenDN);
		line2.add(TKtenDN);
		JPanel line3 = new JPanel();
		line3.add(matKhau);
		line3.add(TKmatKhau);
		Box box =Box.createVerticalBox();
		box.add(thongTin);
		box.add(Box.createVerticalStrut(20));
		box.add(line1);
		box.add(Box.createVerticalStrut(20));
		box.add(line2);
		box.add(Box.createVerticalStrut(20));
		box.add(line3);
	    JPanel traVe = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weighty = 1; // Vertical weight to expand vertically
	    traVe.add(box, gbc);

		return traVe;
	}
	public JPanel initChiTietBan() {
		JLabel thongTin = new JLabel("Thông tin bàn");
		thongTin.setFont(new Font("", Font.BOLD, 30));
		JLabel maBan= new JLabel("Mã bàn");
		JLabel trangThai= new JLabel("Trạng Thái   ");

		maBan.setPreferredSize(trangThai.getPreferredSize());
		BmaBan= new JTextField(30);
		BtrangThai= new JComboBox<String>();
		BtrangThai.addItem("Đang sử dụng");
		BtrangThai.addItem("Còn trống");
		BmaBan.setPreferredSize(new Dimension(200,40));
		BtrangThai.setPreferredSize(new Dimension(320,40));
	
		JPanel line1 = new JPanel();
		line1.add(maBan);
		line1.add(BmaBan);
		JPanel line2 = new JPanel();
		line2.add(trangThai);
		line2.add(BtrangThai);

		Box box =Box.createVerticalBox();
		box.add(thongTin);
		box.add(Box.createVerticalStrut(20));
		box.add(line1);
		box.add(Box.createVerticalStrut(20));
		box.add(line2);
		
	    JPanel traVe = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weighty = 1; // Vertical weight to expand vertically
	    traVe.add(box, gbc);

		return traVe;
	}
	public JPanel initButton() {
	    Box box=Box.createHorizontalBox();
	    box.add(themBtn =new JButton("Thêm"));
	    box.add(Box.createHorizontalStrut(20));
	    box.add(xoaBtn =new JButton("Xóa"));
	    box.add(Box.createHorizontalStrut(20));
	    box.add(suaBtn =new JButton("Sửa"));
	    box.add(Box.createHorizontalStrut(20));
	    box.add(clearBtn =new JButton("Clear"));
	    themBtn.setFont(new Font("", Font.BOLD, 25));
	    xoaBtn.setFont(new Font("", Font.BOLD, 25));
	    suaBtn.setFont(new Font("", Font.BOLD, 25));
	    clearBtn.setFont(new Font("", Font.BOLD, 25));
	    JPanel listtButton= new JPanel();
	    listtButton.setBorder(new EmptyBorder(0, 0, 50, 0));
	    listtButton.add(box);
		themBtn.addActionListener(this);
		xoaBtn.addActionListener(this);
		suaBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		
		return listtButton;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sc= e.getSource();
		
			if(sc.equals(quanLyLoaiSP)) {
				loai=1;
				 giaoDien.remove(((BorderLayout)giaoDien.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				giaoDien.add(initCenter(),BorderLayout.CENTER);
		        giaoDien.revalidate();
		        giaoDien.repaint();
		        readData();
			}
			else if(sc.equals(quanLyNhanVien)) {
				loai=2;
				giaoDien.remove(((BorderLayout)giaoDien.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				giaoDien.add(initCenter(),BorderLayout.CENTER);
			    giaoDien.revalidate();
		        giaoDien.repaint();
		        readData();
			}
			else if(sc.equals(quanLyTaiKhoan)) {
				loai=3;
				giaoDien.remove(((BorderLayout)giaoDien.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				giaoDien.add(initCenter(),BorderLayout.CENTER);
		        giaoDien.revalidate();
		        giaoDien.repaint();
		        readData();
			}
			else if(sc.equals(quanLyBan)) {
				loai=4;
				giaoDien.remove(((BorderLayout)giaoDien.getLayout()).getLayoutComponent(BorderLayout.CENTER));
				giaoDien.add(initCenter(),BorderLayout.CENTER);
		        giaoDien.revalidate();
		        giaoDien.repaint();
		        readData();
			}
			else if(sc.equals(clearBtn)) {
				clearPerf();
			}
			else if(sc.equals(themBtn)) {
				themPerf();
			}
			else if(sc.equals(xoaBtn)) {
				int choice=  JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn xóa không?", "warning", JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION) {
					xoaPerf();
				}
			}
			else if(sc.equals(suaBtn)) {
				int choice=  JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn cập nhật không?", "warning", JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION) {
					 suaPerf();
				}	
			}
			
	}
	public void clearPerf() {
		if(loai==1) {
			LSPMaLoaiF.setText("");
			LSPTenLoaiF.setText("");;
		}
		else if (loai==2) {
			NVmaNV.setText("");
			NVtenNV.setText("");
			NVloaiNV.setSelectedIndex(0);
			NVgioiTinh.setSelectedIndex(0);;
			NVngaySinh.setDate(null);
			NVdiaChi.setText("");
		}
		else if (loai==3) {
			TKmaNV.setText("");
			TKtenDN.setText("");
			TKmatKhau.setText("");
		}
		else if(loai==4) {
			BmaBan.setText("");
			BtrangThai.setSelectedIndex(1);
		}
	}
	public void themPerf() {
		if(loai==1) {
			String maLoai=  LSPMaLoaiF.getText();
			String tenLoai= LSPTenLoaiF.getText();
			if(maLoai==null) {
				JOptionPane.showMessageDialog(null, "Mã loại không rỗng");
				return;
			}
			if(maLoai!= null && maLoai.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã loại không rỗng");
			}
			Integer maLoaiInt=null;
			try {
				maLoaiInt= Integer.parseInt(maLoai);
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã loại phải là số");
				return;
			}
			
			LoaiSanPham lsp= new LoaiSanPham(maLoaiInt, tenLoai);
			if(!loaiSanPham_Dao.addLoaiSanPham(lsp)) {
				JOptionPane.showMessageDialog(null, "Loại sản phẩm đã tồn tại");
				return;
			}
			JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thành công");
			
		}
		else if (loai==2) {
			String maNV= NVmaNV.getText();
			String tenNV= NVtenNV.getText();
			String loaiNV= NVloaiNV.getSelectedItem().toString();
			Boolean gioiTinh = NVgioiTinh.getSelectedIndex()==0?false:true;
			Date ngaySinh= NVngaySinh.getDate();
			String diaChi = NVdiaChi.getText();
			if(maNV==null) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			if(maNV!= null && maNV.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			Integer maNVInt;
			try {
				maNVInt=Integer.parseInt(maNV);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số");
				return;
			}
			if(tenNV==null) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không rỗng");
				return;
			}
			if(!Pattern.matches("(.+\\s*)+", tenNV)) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không đúng định dạng");
				return;
			}
			if(ngaySinh==null) {
				JOptionPane.showMessageDialog(null, "Ngày sinh không rỗng");
			}
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime(ngaySinh);
			
			Calendar minAgeDate = Calendar.getInstance();
			minAgeDate.add(Calendar.YEAR, -18);
			if (birthDate.after(minAgeDate)) {
				JOptionPane.showMessageDialog(null, "Tuổi lớn hơn hoặc bằng 18");
				return;
			} 
			NhanVien nv= new NhanVien(maNVInt, tenNV, loaiNV, gioiTinh, ngaySinh, diaChi);
			if(!nhanVien_Dao.addNhanVien(nv)) {
				JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại");
				return;
			}
			JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
		}
		else if (loai==3) {
			String maNV =TKmaNV.getText();
			String tenDN = TKtenDN.getText();
			String pass= TKmatKhau.getText();
			if(maNV==null) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			if(maNV!= null && maNV.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			Integer maNVInt;
			try {
				maNVInt=Integer.parseInt(maNV);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số");
				return;
			}
			if(tenDN==null) {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập không rỗng");
				return;
			}
			if(!Pattern.matches(".{6,}", tenDN)) {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập có ít nhất 6 ký tự");
				return;
			}
			if(pass==null) {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập không rỗng");
				return;
			}
			if(!Pattern.matches(".{6,}", pass)) {
				JOptionPane.showMessageDialog(null, "Mật khẩu có ít nhất 6 ký tự");
				return;
			}
			NhanVien nv= nhanVien_Dao.findNhanVien(maNVInt);
			if(nv==null) {
				JOptionPane.showMessageDialog(null, "Nhân viên không tồn tại");
				return;
			}
			TaiKhoan tk= new TaiKhoan(nv, tenDN, pass);
			if(!taiKhoan_Dao.addTaiKhoan(tk)) {
				JOptionPane.showMessageDialog(null, "1 Nhân viên chỉ có 1 tài khoản");
				return;
			}
			JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công");
		}
		else if(loai==4) {
			Boolean trangThai= BtrangThai.getSelectedIndex()==0?true:false;
			String maB= BmaBan.getText();
			if(maB==null) {
				JOptionPane.showMessageDialog(null, "Mã bàn không rỗng");
				return;
			}
			if(maB!= null && maB.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã bàn không rỗng");
				return;
			}
			Integer maBan;
			try {
				maBan= Integer.parseInt(maB);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã bàn phải là số");
				return;
			}
			Ban b= new Ban(maBan, trangThai);
			if(!ban_Dao.addBan(b)) {
				JOptionPane.showMessageDialog(null, "Bàn đã tồn tại");
				return;
			}
			JOptionPane.showMessageDialog(null, "Thêm bàn thành công");
		}
	}
	public void xoaPerf() {
		int row =table.getSelectedRow();
		if(row==-1) {
			JOptionPane.showMessageDialog(null, "vui lòng chọn 1 dòng trên bảng để xóa");
			return;
		}
		if(loai==1) {
			String maL= table.getValueAt(row, 0).toString();
			Integer maLInt= Integer.parseInt(maL);
			try {
				loaiSanPham_Dao.deleteLoaiSanPham(maLInt);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			} catch (Exception e) {
				// TODO: handle exception
				
				JOptionPane.showMessageDialog(null, "Không thể xóa loại sản phẩm do ràng buộc giữa sản phẩm và mã sản phẩm");
			}
			
		
		}
		else if(loai==2) {
			String maNV= table.getValueAt(row, 0).toString();
			Integer maNVInt= Integer.parseInt(maNV);
			try {
				nhanVien_Dao.deleteNhanVien(maNVInt);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Không thể xóa nhân viên do ràng buộc giữa cơ sở dữ liệu");
			}	
		}
		else if(loai==3) {
			String maNV= table.getValueAt(row, 0).toString();
			Integer maNVInt= Integer.parseInt(maNV);
		
			if(taiKhoan_Dao.deleteTaiKhoan(maNVInt)) {
				JOptionPane.showMessageDialog(null, "Xóa thành công");
				return;
			}
			JOptionPane.showMessageDialog(null, "Xóa thất bại");
		}
		else if(loai==4) {
			String maB= table.getValueAt(row, 0).toString();
			Integer maBInt= Integer.parseInt(maB);
			try {
				ban_Dao.deleteBan(maBInt);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Không thể xóa nhân viên do ràng buộc giữa cơ sở dữ liệu");
			}

		}
		
	}
	public void suaPerf() {
		if(loai==1) {
			String maLoai=  LSPMaLoaiF.getText();
			String tenLoai= LSPTenLoaiF.getText();
			if(maLoai==null) {
				JOptionPane.showMessageDialog(null, "Mã loại không rỗng");
				return;
			}
			if(maLoai!= null && maLoai.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã loại không rỗng");
			}
			Integer maLoaiInt=null;
			try {
				maLoaiInt= Integer.parseInt(maLoai);
				
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã loại phải là số");
				return;
			}
			
			LoaiSanPham lsp= new LoaiSanPham(maLoaiInt, tenLoai);
			if(!loaiSanPham_Dao.updateLoaiSanPham(lsp)) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy loại sản phẩm có mã là "+maLoaiInt);
				return;
			}
			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
		}
		else if (loai==2) {
			String maNV= NVmaNV.getText();
			String tenNV= NVtenNV.getText();
			String loaiNV= NVloaiNV.getSelectedItem().toString();
			Boolean gioiTinh = NVgioiTinh.getSelectedIndex()==0?false:true;
			Date ngaySinh= NVngaySinh.getDate();
			String diaChi = NVdiaChi.getText();
			if(maNV==null) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			if(maNV!= null && maNV.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			Integer maNVInt;
			try {
				maNVInt=Integer.parseInt(maNV);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số");
				return;
			}
			if(tenNV==null) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không rỗng");
				return;
			}
			if(!Pattern.matches("(.+\\s*)+", tenNV)) {
				JOptionPane.showMessageDialog(null, "Tên nhân viên không đúng định dạng");
				return;
			}
			if(ngaySinh==null) {
				JOptionPane.showMessageDialog(null, "Ngày sinh không rỗng");
			}
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime(ngaySinh);
			
			Calendar minAgeDate = Calendar.getInstance();
			minAgeDate.add(Calendar.YEAR, -18);
			if (birthDate.after(minAgeDate)) {
				JOptionPane.showMessageDialog(null, "Tuổi lớn hơn hoặc bằng 18");
				return;
			} 
			NhanVien nv= new NhanVien(maNVInt, tenNV, loaiNV, gioiTinh, ngaySinh, diaChi);
			if(!nhanVien_Dao.updateNhanVien(nv)) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã là " +maNVInt);
				return;
			}
			JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công");
		}
		else if (loai==3) {
			String maNV =TKmaNV.getText();
			String tenDN = TKtenDN.getText();
			String pass= TKmatKhau.getText();
			if(maNV==null) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			if(maNV!= null && maNV.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã nhân viên không rỗng");
				return;
			}
			Integer maNVInt;
			try {
				maNVInt=Integer.parseInt(maNV);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số");
				return;
			}
			if(tenDN==null) {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập không rỗng");
				return;
			}
			if(!Pattern.matches(".{6,}", tenDN)) {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập có ít nhất 6 ký tự");
				return;
			}
			if(pass==null) {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập không rỗng");
				return;
			}
			if(!Pattern.matches(".{6,}", pass)) {
				JOptionPane.showMessageDialog(null, "Mật khẩu có ít nhất 6 ký tự");
				return;
			}
			NhanVien nv= nhanVien_Dao.findNhanVien(maNVInt);
			if(nv==null) {
				JOptionPane.showMessageDialog(null, "Nhân viên không tồn tại");
				return;
			}
			TaiKhoan tk= new TaiKhoan(nv, tenDN, pass);
			if(!taiKhoan_Dao.updateTaiKhoan(tk)) {
				JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
				return;
			}
			JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công");
		}
		else if(loai==4) {
			Boolean trangThai= BtrangThai.getSelectedIndex()==0?true:false;
			String maB= BmaBan.getText();
			if(maB==null) {
				JOptionPane.showMessageDialog(null, "Mã bàn không rỗng");
				return;
			}
			if(maB!= null && maB.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Mã bàn không rỗng");
				return;
			}
			Integer maBan;
			try {
				maBan= Integer.parseInt(maB);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Mã bàn phải là số");
				return;
			}
			Ban b= new Ban(maBan, trangThai);
			if(!ban_Dao.updateBan(b)) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy bàn có mã là "+maBan);
				return;
			}
			JOptionPane.showMessageDialog(null, "Cập nhật bàn thành công");
		}
	}
	public void readData() {
		if(loai ==1) {
			ArrayList<LoaiSanPham> array =loaiSanPham_Dao.getLoaiSanPham();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (LoaiSanPham loaiSanPham : array) {
				model.addRow(new Object[] {loaiSanPham.getMaLoai().toString().toString(),loaiSanPham.getTenLoai()} );
			}
		}
		else if(loai ==2) {
			ArrayList<NhanVien> array =nhanVien_Dao.getNhanVien();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (NhanVien nv : array) {
				model.addRow(new Object[] {nv.getMaNhanVien().toString(),nv.getTenNhanVien(),nv.getLoaiNhanVien(),nv.getGioiTinh()?"Nam":"Nữ",fo.taoDateFormat(nv.getNgaySinh()),nv.getDiaChi()} );
			}
		}
		else if(loai ==3) {
			ArrayList<TaiKhoan> array =taiKhoan_Dao.getTaiKhoan();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (TaiKhoan tk : array) {
				model.addRow(new Object[]{tk.getNhanVien().getMaNhanVien().toString(),tk.getTenDangNhap(), tk.getMatKhau()});
			}
		}
		else if(loai ==4) {
			ArrayList<Ban> array =ban_Dao.getBan();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for (Ban ban : array) {
				model.addRow(new Object[] {ban.getMaBan().toString(),ban.getTrangThai()?"Đang sử dụng":"Còn trống"});
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row= table.getSelectedRow();
		if(row !=-1) {
			if(loai==1) {
				LSPMaLoaiF.setText((String) table.getValueAt(row, 0));
				String tenL=table.getValueAt(row, 1).toString();
				LSPTenLoaiF.setText(tenL);
			}
			else if (loai==2) {
				NVmaNV.setText((String) table.getValueAt(row, 0));
				NVtenNV.setText((String) table.getValueAt(row, 1));
				String loaiNV= table.getValueAt(row, 2).toString();
				for (int i = 0; i < NVloaiNV.getItemCount(); i++) {
					if(NVloaiNV.getItemAt(i).compareToIgnoreCase(loaiNV)==0) {
						NVloaiNV.setSelectedIndex(i);
						break;
					}
				}			
				String gioiTinh =(String) table.getValueAt(row, 3);
				NVgioiTinh.setSelectedIndex(0);
				if(gioiTinh.compareTo("Nam")==0) {
					NVgioiTinh.setSelectedIndex(1);
				}
				NVngaySinh.setDate(fo.huyDateFormat((String) table.getValueAt(row, 4)));
				NVdiaChi.setText((String) table.getValueAt(row, 5));
			}
			else if (loai==3) {
				TKmaNV.setText((String) table.getValueAt(row, 0));
				TKtenDN.setText((String) table.getValueAt(row, 1));
				TKmatKhau.setText((String) table.getValueAt(row, 2));
			}
			else if(loai==4) {
				BmaBan.setText((String) table.getValueAt(row, 0));
				Boolean trangThai = table.getValueAt(row, 1).toString().compareToIgnoreCase("Còn trống")==0?false:true;
				BtrangThai.setSelectedIndex(0);
				if(!trangThai)
					BtrangThai.setSelectedIndex(1);
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
	
}

