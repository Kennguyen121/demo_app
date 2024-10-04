package giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietDonHang_Dao;
import dao.DonHang_Dao;
import dao.KhachHang_Dao;
import dao.LoaiSanPham_Dao;
import dao.NhanVien_Dao;
import dao.SanPham_Dao;
import xuly.FormatTienVaDate;
import xuly.ThongKe;
import entity.ChiTietDonHang;
import entity.DonHang;
import entity.LoaiSanPham;


public class GiaoDienThongKe extends JPanel implements ActionListener,MouseListener,ItemListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> boLocThoiGian;
	private JDateChooser chiTietBoLocThoiGianStart;
	private JDateChooser chiTietBoLocThoiGianEnd;
	private JComboBox<String> loaiSanPhamComboBox;

	private JTable table;
	private JButton thongKeTheoHoaDon;
	private JButton thongKeTheoSanPham;
	private JPanel left;
	private JPanel right;
	private Integer flag=0;
	private ThongKe thongKe_Dao=new ThongKe();
	private NhanVien_Dao nhanVien_Dao = new NhanVien_Dao();
	private KhachHang_Dao khachHang_Dao= new KhachHang_Dao();
	private SanPham_Dao sanPham_Dao= new SanPham_Dao();
	private LoaiSanPham_Dao loaiSanPham_Dao= new LoaiSanPham_Dao();
	private DonHang_Dao donHang_Dao= new DonHang_Dao();
	private ChiTietDonHang_Dao chiTietDonHang_Dao =new ChiTietDonHang_Dao();
	private JLabel anhIcon;
	
	private JTextField tongDoanhThuTF;
	private JTextField tsoDonHangTF;
	private JTextField tsoSanPhamTF;
	private JTextField tsanPhamBanChayNhatTF;
	private JTextField tloaiSanPhamBanChayNhatTF;
	private JTextField tnhanVienBanNhieuNhatTF;
	
	private JTextField ctmaDonHangTF;
	private JTextField ctmaChiTietDonHangTF;
	private JTextField cttenNhanVienTF;
	private JDateChooser ctngayTaoDonJD;
	private JTextField cttenkhachHangTF;
	private JTextField cttenSanPhamTF;
	private JTextField ctsoLuongTF;
	private JTextField ctkhuyenMaiTF;
	private JTextField cttongTienTF;
	private JTextArea ctmoTaTA;
	private JTextField spmaSanPhamTF;
	private JTextField sptenSanPhamTF;
	private JTextField spmaLoaiTF;
	private JTextField sptenLoaiTF;
	private JTextField spsoLuongDaBanTF;
	private JTextField spsoDonHangTF;
	private JTextField sptongTienTF;
	private FormatTienVaDate fo= new FormatTienVaDate();
	private JButton thongKeTheoBangButton;
	private JTextField tongTienTHDF;
	private JTextField nhanVienTHDF;
	private JTextField soLuongChiTietHoaDonTHDF;

	private JTextField soLuongSanPhamTHDF;
	private JDateChooser ngayTaoDonTHDF;
	private JTextField khuyenMaiF;
	private JTextField timkiemHDTK;
	private JComboBox<String> sapXepHDComboBox;
	private JButton timkiemBHDTKButton;
	private java.sql.Date  thoiGianStart;
	private java.sql.Date  thoiGianEnd;
	private Integer loaiSPTrongBoLoc;

	//phần giao diện
	public GiaoDienThongKe() {
		 setLayout(new BorderLayout());
		 //phần thống kê 
		left= initLeft();
		right= initRight();
		JSplitPane thongKeSp= new JSplitPane(SwingConstants.VERTICAL, left,right );
		thongKeSp.setDividerLocation(600);
		add(thongKeSp,BorderLayout.CENTER);
		
		thongKeTheoHoaDon.addActionListener(this);
		thongKeTheoSanPham.addActionListener(this);
		thongKeTheoBangButton.addActionListener(this);
		boLocThoiGian.addItemListener(this);
		table.addMouseListener(this);
	}
	private JPanel initLeft() {
		 thongKeTheoHoaDon = new JButton("Thống kê theo hóa đơn", FontIcon.of(FontAwesomeSolid.FILE_INVOICE_DOLLAR,15));
		 thongKeTheoSanPham = new JButton("Thống kê theo sản phẩm", FontIcon.of(FontAwesomeSolid.STORE_ALT,15)); 
		 Box boxTheLoai= Box.createVerticalBox();
		 boxTheLoai.add(Box.createVerticalStrut(10));
		 boxTheLoai.add(thongKeTheoHoaDon);
		 boxTheLoai.add(Box.createVerticalStrut(10));
		 boxTheLoai.add(thongKeTheoSanPham);
		 // phần bộ lọc theo từng thống kê
		 
		JSplitPane boLocSplitPane = new JSplitPane(SwingConstants.VERTICAL, boxTheLoai, boLocDoanhThu());
		JPanel left = new JPanel(new BorderLayout());
		boLocSplitPane.setBorder(new TitledBorder("Tìm kiếm") );
		 left.add(boLocSplitPane,BorderLayout.NORTH);
		 left.add(new JPanel(),BorderLayout.CENTER);
		return left;
	}
 	private JPanel boLocDoanhThu() {
		//phần thời gian
		boLocThoiGian = new JComboBox<String>();
		boLocThoiGian.addItem("Chi Tiết");
		boLocThoiGian.addItem("Hôm nay");
		boLocThoiGian.addItem("Hôm qua");
		boLocThoiGian.addItem("Tuần này");
		boLocThoiGian.addItem("Tuần trước");
		
		chiTietBoLocThoiGianStart= new JDateChooser();
		chiTietBoLocThoiGianEnd= new JDateChooser();
		chiTietBoLocThoiGianStart.setDateFormatString("dd/MM/yyyy");
		chiTietBoLocThoiGianEnd.setDateFormatString("dd/MM/yyyy");
		JLabel start =new JLabel("Từ  ");
		JLabel end =new JLabel("Đến  ");

	
		//loại sản phẩm
		loaiSanPhamComboBox =new JComboBox<String>();
		loaiSanPhamComboBox.addItem("Tất cả");
		ArrayList<LoaiSanPham> arrayLSP=  loaiSanPham_Dao.getLoaiSanPham();
		for (LoaiSanPham loaiSanPham : arrayLSP) {
			loaiSanPhamComboBox.addItem(loaiSanPham.getTenLoai());
		}
		//sau này sẽ sửa
		thongKeTheoBangButton = new JButton("Thống kê theo bảng");
		
		//phần 
		Box hBox = Box.createHorizontalBox();
		hBox.add(boLocThoiGian);
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(loaiSanPhamComboBox);

		Box hBox2= Box.createHorizontalBox();
		hBox2.add(start);
		hBox2.add(chiTietBoLocThoiGianStart);
		hBox2.add(Box.createHorizontalStrut(10));
		hBox2.add(end);
		hBox2.add(chiTietBoLocThoiGianEnd);
		
		Box hBox3= Box.createHorizontalBox();
		hBox3.add(Box.createHorizontalStrut(20));
		hBox3.add(thongKeTheoBangButton);
		
		Box boLocBox= Box.createVerticalBox();
		boLocBox.add(Box.createVerticalStrut(10));
		boLocBox.add(hBox);
		boLocBox.add(Box.createVerticalStrut(10));
		boLocBox.add(hBox2);
		boLocBox.add(Box.createVerticalStrut(10));
		boLocBox.add(hBox3);

		JPanel boLocDT= new JPanel(new BorderLayout());
		boLocDT.add(boLocBox,BorderLayout.CENTER);
		return boLocDT;
	}
 	private JPanel chiTietTheoHoaDon() {
 		JLabel hoaDon= new JLabel("Hóa Đơn");
 		hoaDon.setFont(new Font("", Font.BOLD, 30));
 		JLabel maDonHang= new JLabel("Mã đơn hàng");
 		JLabel maChiTietDonHang= new JLabel("Mã chi tiết đơn hàng     ");
 		JLabel tenNhanVien= new JLabel("Tên nhân viên");
 		JLabel tenkhachHang= new JLabel("Tên khách hàng");
 		JLabel ngayTaoDon= new JLabel("Ngày tạo đơn");
 		JLabel tenSanPham = new JLabel("Tên sản phẩm");
 		JLabel soLuong = new JLabel("Số lượng");
 		JLabel khuyenMai = new JLabel("Khuyến mãi");
 		JLabel tongTien = new JLabel("Tổng tiền");
 		JLabel moTa = new JLabel("Mô tả   ");
 		
 		maDonHang.setPreferredSize(maChiTietDonHang.getPreferredSize());
 		tenNhanVien.setPreferredSize(maChiTietDonHang.getPreferredSize());
 		ngayTaoDon.setPreferredSize(maChiTietDonHang.getPreferredSize());
 		tenkhachHang.setPreferredSize(maChiTietDonHang.getPreferredSize());
 		tenSanPham.setPreferredSize(maChiTietDonHang.getPreferredSize());
 		tongTien.setPreferredSize(maChiTietDonHang.getPreferredSize());
 		moTa.setPreferredSize(maChiTietDonHang.getPreferredSize());
 		
 		ctmaDonHangTF= new JTextField();
 		ctmaChiTietDonHangTF= new JTextField();
 		cttenNhanVienTF= new JTextField();
 		ctngayTaoDonJD= new JDateChooser();
 		cttenkhachHangTF= new JTextField();
 		cttenSanPhamTF= new JTextField(12);
 		ctsoLuongTF= new JTextField(7);
 		ctkhuyenMaiTF= new JTextField(7);
 		cttongTienTF= new JTextField();
 		ctmoTaTA= new JTextArea(10,10);
 		ctmoTaTA.setBorder(BorderFactory.createLineBorder(Color.gray));
 		
 		JPanel line1 =  new JPanel(new BorderLayout());
 		line1.add(maDonHang,BorderLayout.WEST);
 		line1.add(ctmaDonHangTF,BorderLayout.CENTER);
 		
 		JPanel line2 =  new JPanel(new BorderLayout());
 		line2.add(maChiTietDonHang,BorderLayout.WEST);
 		line2.add(ctmaChiTietDonHangTF,BorderLayout.CENTER);
 		
 		JPanel line3 =  new JPanel(new BorderLayout());
 		line3.add(tenNhanVien,BorderLayout.WEST);
 		line3.add(cttenNhanVienTF,BorderLayout.CENTER);
 		
 		JPanel line4 =  new JPanel(new BorderLayout());
 		line4.add(ngayTaoDon,BorderLayout.WEST);
 		line4.add(ctngayTaoDonJD,BorderLayout.CENTER);
 		
 		JPanel line5 =  new JPanel(new BorderLayout());
 		line5.add(tenkhachHang,BorderLayout.WEST);
 		line5.add(cttenkhachHangTF,BorderLayout.CENTER);

 		JPanel line6 =  new JPanel(new BorderLayout());
 		Box hBox = Box.createHorizontalBox();
 		hBox.add(cttenSanPhamTF);
 		hBox.add(Box.createHorizontalStrut(20));
 		hBox.add(soLuong);
 		hBox.add(ctsoLuongTF);
 		hBox.add(Box.createHorizontalStrut(20));
 		hBox.add(khuyenMai);
 		hBox.add(ctkhuyenMaiTF);
 		line6.add(tenSanPham,BorderLayout.WEST);
 		line6.add(hBox,BorderLayout.CENTER);
 	
		JPanel line7 =  new JPanel(new BorderLayout());
 		line7.add(tongTien,BorderLayout.WEST);
 		line7.add(cttongTienTF,BorderLayout.CENTER);
 		
		JPanel line8 =  new JPanel(new BorderLayout());
		line8.add(moTa,BorderLayout.WEST);
		line8.add(ctmoTaTA,BorderLayout.CENTER);
		
		Box box= Box.createVerticalBox();
		box.add(line1);
		box.add(Box.createVerticalStrut(10));
		box.add(line2);
		box.add(Box.createVerticalStrut(10));
		box.add(line3);
		box.add(Box.createVerticalStrut(10));
		box.add(line4);
		box.add(Box.createVerticalStrut(10));
		box.add(line5);
		box.add(Box.createVerticalStrut(10));
		box.add(line6);
		box.add(Box.createVerticalStrut(10));
		box.add(line7);
		box.add(Box.createVerticalStrut(10));
		box.add(line8);	
		
		JPanel boxPanel = new JPanel();
		boxPanel.add(box);
		JPanel hoaDonPanel= new JPanel();
		hoaDonPanel.add(hoaDon);
		
		JPanel chiTietTheoHoaDon = new JPanel(new BorderLayout());
		chiTietTheoHoaDon.add(hoaDonPanel,BorderLayout.NORTH);
		chiTietTheoHoaDon.add(boxPanel,BorderLayout.CENTER);
		
		return chiTietTheoHoaDon;
 	}
 	private JPanel chiTietTheoSanPham() {
 		JLabel sanPham= new JLabel("Sản Phẩm");
 		sanPham.setFont(new Font("", Font.BOLD, 30));
 		JLabel maSanPham= new JLabel("Mã sản phẩm");
 		JLabel tenSanPham= new JLabel("Tên sản phẩm");
 		JLabel anh= new JLabel("Ảnh");
 		JLabel maLoai= new JLabel("Mã loại");
 		JLabel tenLoai= new JLabel("Tên loại");
 		JLabel soLuongDaBan= new JLabel("Số lượng đã bán");
 		JLabel soDonHang = new JLabel("Số đơn hàng");
 		JLabel tongTien = new JLabel("Tổng tiền");

 		maSanPham.setPreferredSize(soLuongDaBan.getPreferredSize());
 		tenSanPham.setPreferredSize(soLuongDaBan.getPreferredSize());
 		maLoai.setPreferredSize(soLuongDaBan.getPreferredSize());
 		tenLoai.setPreferredSize(soLuongDaBan.getPreferredSize());
 		soDonHang.setPreferredSize(soLuongDaBan.getPreferredSize());
 		tongTien.setPreferredSize(soLuongDaBan.getPreferredSize());
 		anh.setPreferredSize(soLuongDaBan.getPreferredSize());
 		
		ImageIcon icon = new ImageIcon("");
		anhIcon= new JLabel(icon); 

		 
 		spmaSanPhamTF= new JTextField();
 		sptenSanPhamTF= new JTextField();	
 		spmaLoaiTF= new JTextField(10);
 		sptenLoaiTF= new JTextField(20);
 		spsoLuongDaBanTF= new JTextField();
 		spsoDonHangTF= new JTextField();
 		sptongTienTF= new JTextField();
 	
 		
 		JPanel line1 =  new JPanel(new BorderLayout());
 		line1.add(anh,BorderLayout.WEST);
 		line1.add(anhIcon,BorderLayout.CENTER);
 		
 		JPanel line2 =  new JPanel(new BorderLayout());
 		line2.add(maSanPham,BorderLayout.WEST);
 		line2.add(spmaSanPhamTF,BorderLayout.CENTER);
 		
 		JPanel line3 =  new JPanel(new BorderLayout());
 		line3.add(tenSanPham,BorderLayout.WEST);
 		line3.add(sptenSanPhamTF,BorderLayout.CENTER);
 		
 		JPanel line4 =  new JPanel(new BorderLayout());
 		Box hBox = Box.createHorizontalBox();
 		hBox.add(spmaLoaiTF);
 		hBox.add(Box.createHorizontalStrut(20));
 		hBox.add(tenLoai);
 		hBox.add(sptenLoaiTF);
 		line4.add(maLoai,BorderLayout.WEST);
 		line4.add(hBox,BorderLayout.CENTER);
 		
		JPanel line5 =  new JPanel(new BorderLayout());
 		line5.add(soLuongDaBan,BorderLayout.WEST);
 		line5.add(spsoLuongDaBanTF,BorderLayout.CENTER);
 		
		JPanel line6 =  new JPanel(new BorderLayout());
		line6.add(soDonHang,BorderLayout.WEST);
		line6.add(spsoDonHangTF,BorderLayout.CENTER);
		
		JPanel line7 =  new JPanel(new BorderLayout());
		line7.add(tongTien,BorderLayout.WEST);
		line7.add(sptongTienTF,BorderLayout.CENTER);
		
		Box box= Box.createVerticalBox();
		box.add(line1);
		box.add(Box.createVerticalStrut(10));
		box.add(line2);
		box.add(Box.createVerticalStrut(10));
		box.add(line3);
		box.add(Box.createVerticalStrut(10));
		box.add(line4);
		box.add(Box.createVerticalStrut(10));
		box.add(line5);
		box.add(Box.createVerticalStrut(10));
		box.add(line6);
		box.add(Box.createVerticalStrut(10));
		box.add(line7);
		
		JPanel boxPanel = new JPanel();
		boxPanel.add(box);
		
		JPanel sanPhamPanel= new JPanel();
		sanPhamPanel.add(sanPham);
		
		JPanel chiTietTheoHoaDon = new JPanel(new BorderLayout());
		chiTietTheoHoaDon.add(sanPhamPanel,BorderLayout.NORTH);
		chiTietTheoHoaDon.add(boxPanel,BorderLayout.CENTER);
		
		return chiTietTheoHoaDon;
 	}
 	private JPanel initRight(){

 		right = new JPanel(new BorderLayout());
 		Object[] cot = {"cot1","cot2","cot3","cot4","cot5"};
 	
		right.add(phanThongKe(cot),BorderLayout.CENTER);
	 	JPanel phanTongCong= phanTongCong();
	 	
	 	right.add(phanTongCong,BorderLayout.SOUTH);
	 	right.add(phanTimKiemVaSapXepTrenBang(),BorderLayout.NORTH);
 		return right;
 	}
 	private JPanel phanTimKiemVaSapXepTrenBang() {
    	JLabel maHDTK = new JLabel("Mã hóa đơn");
    	maHDTK.setFont(new Font("",Font.PLAIN, 15));
    	
        timkiemHDTK = new JTextField("sreach",15);
        timkiemBHDTKButton = new JButton("Tìm kiếm ");
        timkiemBHDTKButton.addActionListener(this);
        // Get the InputMap for the WHEN_IN_FOCUSED_WINDOW condition
        InputMap inputMap = timkiemBHDTKButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Map the ENTER key to the "click" action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "click");

        // Now add an ActionMap entry that performs the click action
        timkiemBHDTKButton.getActionMap().put("click", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
				timkiemBHDTKButton.doClick();
            }
        });
    	JLabel sapXepHDTK = new JLabel("Sắp xếp hóa đơn");
    	sapXepHDTK.setFont(new Font("",Font.PLAIN, 15));
        String[] loaiSapXepHD= {"Không","Sắp xếp theo tổng tiền của hóa đơn tăng dần","Sắp xếp theo tổng tiền của hóa đơn giảm dần"};
        sapXepHDComboBox = new JComboBox<String>(loaiSapXepHD);
        sapXepHDComboBox.addItemListener(this);
        Box box = Box.createHorizontalBox();
        box.add(maHDTK);
        box.add(Box.createHorizontalStrut(10));
        box.add(timkiemHDTK);
        box.add(Box.createHorizontalStrut(10));
        box.add(timkiemBHDTKButton);
        box.add(Box.createHorizontalStrut(100));
        box.add(sapXepHDTK);
        box.add(Box.createHorizontalStrut(10));
        box.add(sapXepHDComboBox);
        JPanel sapXepPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sapXepPanel.add(box);
        sapXepPanel.setBorder(new TitledBorder("Tìm kiếm và sắp xếp"));
        return sapXepPanel;
	}
 	private JPanel phanThongKe(Object[] cot) {
		DefaultTableModel model= new DefaultTableModel(null, cot);
		table = new JTable(model);
		table.setBorder(new LineBorder(Color.BLACK,3));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		JPanel phanThongKe= new JPanel(new BorderLayout());
		phanThongKe.add(scrollPane);
		phanThongKe.setBorder(new TitledBorder("Bảng"));
		return phanThongKe;
	}
 	private JPanel phanTongCong() {
 		JLabel tongDoanhThu= new JLabel("  Tổng doanh thu");
 		tongDoanhThu.setFont(new Font("", Font.BOLD, 20));
 		JLabel soDonHang= new JLabel("   Tổng số đơn hàng");
 		JLabel soSanPham= new JLabel("Tổng Số sản phẩm   ");
 		JLabel sanPhamBanChayNhat= new JLabel("Sản phẩm bán chạy nhất   ");
 		JLabel loaiSanPhamBanChayNhat= new JLabel("   Loại sản phẩm bán chạy nhất   ");
 		JLabel nhanVienBanNhieuNhat= new JLabel("   Nhân viên bán nhiều nhất");
 		
 		tongDoanhThu.setPreferredSize(loaiSanPhamBanChayNhat.getPreferredSize());
 		soDonHang.setPreferredSize(loaiSanPhamBanChayNhat.getPreferredSize());
 		nhanVienBanNhieuNhat.setPreferredSize(loaiSanPhamBanChayNhat.getPreferredSize());

		tongDoanhThuTF= new JTextField();
		tsoDonHangTF= new JTextField(10);
		tsoSanPhamTF= new JTextField(10);
		tsanPhamBanChayNhatTF= new JTextField(10);
		tloaiSanPhamBanChayNhatTF= new JTextField(10);
		tnhanVienBanNhieuNhatTF = new JTextField();
 		
		tongDoanhThuTF.setEditable(false);
		tsoDonHangTF.setEditable(false);
		tsoSanPhamTF.setEditable(false);
		tsanPhamBanChayNhatTF.setEditable(false);
		tloaiSanPhamBanChayNhatTF.setEditable(false);
		tnhanVienBanNhieuNhatTF.setEditable(false);
		
 		JPanel line1 = new JPanel(new BorderLayout());
 		line1.add(tongDoanhThu, BorderLayout.WEST);
 		line1.add(tongDoanhThuTF, BorderLayout.CENTER);
 		
 		JPanel line2 = new JPanel(new BorderLayout());
 		Box hbox = Box.createHorizontalBox();
 		hbox.add(tsoDonHangTF);
 		hbox.add(Box.createHorizontalStrut(20));
 		hbox.add(soSanPham);
 		hbox.add(tsoSanPhamTF);
 		line2.add(soDonHang, BorderLayout.WEST);
 		line2.add(hbox, BorderLayout.CENTER);
 		
 		JPanel line3 = new JPanel(new BorderLayout());
 		Box hbox1 = Box.createHorizontalBox();
 		hbox1.add(tloaiSanPhamBanChayNhatTF);
 		hbox1.add(Box.createHorizontalStrut(20));
 		hbox1.add(sanPhamBanChayNhat);
 		hbox1.add(tsanPhamBanChayNhatTF);
 		line3.add(loaiSanPhamBanChayNhat, BorderLayout.WEST);
 		line3.add(hbox1, BorderLayout.CENTER);
 		
		JPanel line4 = new JPanel(new BorderLayout());
 		line4.add(nhanVienBanNhieuNhat, BorderLayout.WEST);
 		line4.add(tnhanVienBanNhieuNhatTF, BorderLayout.CENTER);
 		
 		Box vbox= Box.createVerticalBox();
 		vbox.add(Box.createVerticalStrut(20));
 		vbox.add(line1);
 		vbox.add(Box.createVerticalStrut(10));
 		vbox.add(line2);
		vbox.add(Box.createVerticalStrut(10));
 		vbox.add(line3);
		vbox.add(Box.createVerticalStrut(10));
 		vbox.add(line4);
 		vbox.add(Box.createVerticalStrut(20));
 	
 		JPanel phanTongCong = new JPanel(new BorderLayout());
 		phanTongCong.add(vbox,BorderLayout.CENTER);
		phanTongCong.setBorder(new MatteBorder(5, 5, 5, 5, Color.red));
 		return phanTongCong;
 	}
 	private JPanel phanTongHoaDon() {
 		JLabel tongTienTHD = new JLabel("Tổng tiền          ");
 		tongTienTHD.setFont(new Font("", Font.BOLD, 20));
 		tongTienTHDF =new JTextField();
 		JLabel nhanVienTHD = new JLabel("Nhân viên xử lý hóa đơn     ");
 		nhanVienTHDF =new JTextField();
 		JLabel soLuongChiTietHoaDonTHD = new JLabel("Số lượng chi tiết hóa đơn");
 		soLuongChiTietHoaDonTHDF =new JTextField();
 	
 		JLabel soLuongSanPhamTHD=new JLabel("Số lượng sản phẩm");
 		soLuongSanPhamTHDF = new JTextField();
 		JLabel khuyenMai=new JLabel("Khuyến mãi");
 		khuyenMaiF = new JTextField();
 		JLabel ngayTaoDonTHD=new JLabel("Ngày tạo đơn");
 		ngayTaoDonTHDF = new JDateChooser();
 		
 		khuyenMai.setPreferredSize(soLuongChiTietHoaDonTHD.getPreferredSize());
 		ngayTaoDonTHD.setPreferredSize(soLuongSanPhamTHD.getPreferredSize());
 		

 		soLuongChiTietHoaDonTHDF.setPreferredSize(new Dimension(250,20));
 		soLuongSanPhamTHDF.setPreferredSize(new Dimension(250,20));
 		khuyenMaiF.setPreferredSize(new Dimension(250,20));
 		ngayTaoDonTHDF.setPreferredSize(new Dimension(250,20));
 		
 		JPanel line1 =new JPanel(new BorderLayout());
 		line1.add(tongTienTHD,BorderLayout.WEST);
 		line1.add(tongTienTHDF,BorderLayout.CENTER);
 		
 		JPanel line2 =new JPanel(new BorderLayout());
 		line2.add(nhanVienTHD,BorderLayout.WEST);
 		line2.add(nhanVienTHDF,BorderLayout.CENTER);
 		
 		JPanel line3 =new JPanel(new BorderLayout());
 		Box box = Box.createHorizontalBox();

 		box.add(Box.createHorizontalStrut(10));
 		box.add(soLuongChiTietHoaDonTHDF);
 		box.add(Box.createHorizontalStrut(20));
 		box.add(soLuongSanPhamTHD);
 		box.add(Box.createHorizontalStrut(10));
 		box.add(soLuongSanPhamTHDF);
 		line3.add(soLuongChiTietHoaDonTHD,BorderLayout.WEST);
 		line3.add(box,BorderLayout.CENTER);
 		
 		JPanel line4 =new JPanel(new BorderLayout());
 		Box box2 = Box.createHorizontalBox();

 		box2.add(Box.createHorizontalStrut(10));
 		box2.add(khuyenMaiF);
 		box2.add(Box.createHorizontalStrut(20));
 		box2.add(ngayTaoDonTHD);
 		box2.add(Box.createHorizontalStrut(10));
 		box2.add(ngayTaoDonTHDF);
 		line4.add( khuyenMai,BorderLayout.WEST);
 		line4.add(box2,BorderLayout.CENTER);
 		
 		Box vBox= Box.createVerticalBox();
 		vBox.add(line1);
 		vBox.add(Box.createVerticalStrut(2));
 		vBox.add(line2);
 		vBox.add(Box.createVerticalStrut(2));
 		vBox.add(line3);
 		vBox.add(Box.createVerticalStrut(2));
 		vBox.add(line4);
 		
		JPanel trave = new JPanel();
		trave.add(vBox);
		trave.setBorder(new MatteBorder(5, 5, 5, 5, Color.red));
		return trave;
	}
 	//phần xử lí
 	//phần xử lý
 	private void readDataForPhanTongHoaDon() {
		// TODO Auto-generated method stub
 		int row= table.getSelectedRow();
 		if(row!=-1) {
 			Integer maDonHang= Integer.parseInt( (String) table.getValueAt(row, 0));
 			DonHang donHang= donHang_Dao.findDonHang(maDonHang);
 			if(donHang!=null) {
 				tongTienTHDF.setText(fo.taoTienFormat(donHang.getTongTien()));
 				nhanVienTHDF.setText(donHang.getNhanVien().getTenNhanVien());
 				ArrayList<ChiTietDonHang> dsChiTietDonHang = chiTietDonHang_Dao.findDonHangTheoMaDonHang(maDonHang);
 				Integer soLuongChiTiet =dsChiTietDonHang.size();
 				soLuongChiTietHoaDonTHDF.setText(soLuongChiTiet.toString());
 				Integer soLuongSanPham=0;
 				for (ChiTietDonHang chiTietDonHang : dsChiTietDonHang) {
 					soLuongSanPham+= chiTietDonHang.getSoLuong();
				}
 				soLuongSanPhamTHDF.setText(soLuongSanPham.toString());
 				khuyenMaiF.setText(fo.taoKhuyenMaiFormat( dsChiTietDonHang.get(0).getKhuyenMai()));
 				ngayTaoDonTHDF.setDate(donHang.getNgayTaoDon());
 				ngayTaoDonTHDF.setDateFormatString("dd/MM/yyyy");
 			}
 		}
	}
 	//loại format 1 là Date 2 là tiền  3 là discount
 	private void formatDateMoneyDiscount(int row, int col,int loaiFormat) {
 	
 		DefaultTableModel tableModel=  (DefaultTableModel) table.getModel();
 		if(loaiFormat==1) {
			LocalDate ngayTD= LocalDate.parse((CharSequence) tableModel.getValueAt(row, col));
			Date newDate= Date.valueOf(ngayTD);
			String dateFormat = fo.taoDateFormat(newDate);
			tableModel.setValueAt(dateFormat, row, col);
 		}
 		else if(loaiFormat ==2) {
 			Double tien = Double.parseDouble( tableModel.getValueAt(row, col).toString());
 			String tienS= fo.taoTienFormat(tien);
 			tableModel.setValueAt(tienS, row, col);
 		}
 		else if(loaiFormat == 3) {
 			Double khuyenMai = Double.parseDouble( tableModel.getValueAt(row, col).toString());
 			String khuyenMaiS= fo.taoKhuyenMaiFormat(khuyenMai);
 			
 			tableModel.setValueAt(khuyenMaiS, row, col);
 		}
 	}
 	private void getBoLoc() {
		java.util.Date utilDate = chiTietBoLocThoiGianStart.getDate();
		java.util.Date utilDate2 = chiTietBoLocThoiGianEnd.getDate();
 	// Convert java.util.Date to java.sql.Date
 	 thoiGianStart = null;
 	 thoiGianEnd =null;
 				//hôm nay
 				Calendar calendar = Calendar.getInstance();
 				if(boLocThoiGian.getSelectedIndex()==1) {
 					thoiGianStart= new java.sql.Date(new java.util.Date().getTime());
 					thoiGianEnd =  new java.sql.Date(new java.util.Date().getTime());
 				}
 				//hôm qua
 				else if(boLocThoiGian.getSelectedIndex()==2) {
 					// Get the current date as a java.util.Date
 					java.util.Date cur = new java.util.Date();

 					// Create a Calendar instance and set it to the current date
 				
 					calendar.setTime(cur);

 					// Subtract one day
 					calendar.add(Calendar.DAY_OF_MONTH, -1);

 					// Get the date representing yesterday
 					java.util.Date yesterday = calendar.getTime();

 					// Convert java.util.Date to java.sql.Date
 					thoiGianStart= new java.sql.Date(yesterday.getTime());
 					thoiGianEnd =  new java.sql.Date(cur.getTime());
 				}
 				//tuần này 
 				else if(boLocThoiGian.getSelectedIndex()==3) {
 					java.util.Date cur = new java.util.Date();

 					// Create a Calendar instance and set it to the current date
 					calendar.setTime(cur);

 					// Calculate the number of days to subtract to get to Monday (assuming Sunday is the first day of the week)
 					int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
 					int daysToSubtract = (dayOfWeek == Calendar.SUNDAY) ? 6 : (dayOfWeek - Calendar.MONDAY);

 					// Subtract the appropriate number of days
 					calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract);

 					// Get the date representing Monday of this week
 					java.util.Date mondayOfThisWeek = calendar.getTime();

 					// Convert java.util.Date to java.sql.Date
 					thoiGianStart = new java.sql.Date(mondayOfThisWeek.getTime());
 					thoiGianEnd =  new java.sql.Date(cur.getTime());
 				}
 				//tuần trước
 				else if(boLocThoiGian.getSelectedIndex()==4) {
 					java.util.Date cur = new java.util.Date();

 					// Create a Calendar instance and set it to the current date
 					calendar.setTime(cur);
 					calendar.add(java.util.Calendar.WEEK_OF_YEAR, -1); // Go to last week
 					int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
 					
 					int daysToSubtract = (dayOfWeek == Calendar.SUNDAY) ? 6 : (dayOfWeek - Calendar.MONDAY);
 					calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
 					java.util.Date monday = calendar.getTime();
 					thoiGianStart = new java.sql.Date(monday.getTime());
 					calendar.add(Calendar.DAY_OF_MONTH, 6);
 					java.util.Date sunday = calendar.getTime();
 					thoiGianEnd =  new java.sql.Date(sunday.getTime());
 				}
 				if(utilDate!=null) {
 					thoiGianStart= new java.sql.Date(utilDate.getTime());
 				}
 				if(utilDate2!=null) {
 					thoiGianEnd= new java.sql.Date(utilDate2.getTime());
 				}
 				loaiSPTrongBoLoc= null;
 				if(loaiSanPhamComboBox.getSelectedIndex()!=0) {
 					LoaiSanPham lsp = loaiSanPham_Dao.findLoaiSanPhamTheoTen(loaiSanPhamComboBox.getSelectedItem().toString());
 					loaiSPTrongBoLoc = lsp.getMaLoai();
 				}
 	}
 	private void removeData() {
 		DefaultTableModel tableModel=  (DefaultTableModel) table.getModel();
 		tableModel.getDataVector().removeAllElements();
 		table.revalidate();
 		table.repaint();
 	}
 	private void readData(Date start, Date end, Integer maLoai) {
		if(flag==1) {
			try {
				ArrayList<ArrayList<String>> data = thongKe_Dao.getDataTheoDonHang(start,end,maLoai);
				int row=0;

				for (ArrayList<String> arrayList : data) {
					DefaultTableModel tableModel=  (DefaultTableModel) table.getModel();
					tableModel.addRow(arrayList.toArray());
					formatDateMoneyDiscount(row,3,1);
					formatDateMoneyDiscount(row,8,2);
					formatDateMoneyDiscount(row,7,3);
					row++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else if(flag==2) {
			try {
				
				ArrayList<ArrayList<String>> data = thongKe_Dao.getDataTheoSanPham(start,end,maLoai);
				int row=0;

				for (ArrayList<String> arrayList : data) {
					DefaultTableModel tableModel=  (DefaultTableModel) table.getModel();
					tableModel.addRow(arrayList.toArray());
					formatDateMoneyDiscount(row,6,2);
					row++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
 	private void tongDoanhThu() {
 	 	ArrayList<String> tongDoanhThu;
		try {
			tongDoanhThu = thongKe_Dao.getDataTongDoanhThu();
			Double tong= Double.parseDouble(tongDoanhThu.get(0));
			String tongFormat= fo.taoTienFormat(tong);
			tongDoanhThuTF.setText(tongFormat);
			tsoDonHangTF.setText(tongDoanhThu.get(1));
			tsoSanPhamTF.setText(tongDoanhThu.get(2));
			tloaiSanPhamBanChayNhatTF.setText(tongDoanhThu.get(3));
			tsanPhamBanChayNhatTF.setText(tongDoanhThu.get(4));
			tnhanVienBanNhieuNhatTF.setText(tongDoanhThu.get(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sc= e.getSource();
		if(sc.equals(thongKeTheoHoaDon)) {
			if(flag!=1) {
				flag=1;
		 		Object[] cot= {"Mã đơn hàng","Mã chi tiết đơn hàng","Mã nhân viên","Ngày tạo đơn","Mã khách hàng","Mã Sản Phẩm","Số Lượng","Khuyến mãi","Tổng tiền","Mô tả"};
		 		DefaultTableModel model= new DefaultTableModel(null,cot);
		 		table.setModel(model);
				Component centerComponent = left.getComponent(1);
				left.remove(centerComponent);
				left.add(chiTietTheoHoaDon(),BorderLayout.CENTER);
				left.revalidate();
				left.repaint();
				readData(null,null,null);
				tongDoanhThu();
				
				//làm mới bộ lọc 
				boLocThoiGian.setSelectedIndex(0);
				loaiSanPhamComboBox.setSelectedIndex(0);
				chiTietBoLocThoiGianEnd.setDate(null);
				chiTietBoLocThoiGianStart.setDate(null);
			}
		}
		else if(sc.equals(thongKeTheoSanPham)) {
			if(flag!=2) {
				flag=2;
				Object[] cot= {"Mã sản phẩm","Tên sản phẩm","Ảnh","Mã loại","Số lượng đã bán" ,"Số đơn hàng","Tổng tiền"};
		 		DefaultTableModel model= new DefaultTableModel(null, cot);
		 		table.setModel(model);
				Component centerComponent = left.getComponent(1);
				left.remove(centerComponent);
				left.add(chiTietTheoSanPham(),BorderLayout.CENTER);
				left.revalidate();
				left.repaint();
				readData(null,null,null);
				//load lại right south
				Component southComponent = right.getComponent(1);
				right.remove(southComponent);
				right.add(phanTongCong(),BorderLayout.SOUTH);
				right.revalidate();
				right.repaint();
				tongDoanhThu();
		
			}

		}
		else if(sc.equals(thongKeTheoBangButton)) {
			getBoLoc();
			removeData();
			readData(thoiGianStart, thoiGianEnd, loaiSPTrongBoLoc);
			
		}
		else if (sc.equals(timkiemBHDTKButton)) {
			if(timkiemHDTK!=null && timkiemHDTK.getText().compareTo("sreach")!=0) {
				String maDH= timkiemHDTK.getText();
				for(int i=0;i<table.getRowCount();i++) {
					if(table.getValueAt(i,1).toString().compareToIgnoreCase(maDH)==0){
						table.setRowSelectionInterval(i,i);
						Rectangle cellRectangle= table.getCellRect(i, 0, true);
						table.scrollRectToVisible(cellRectangle);
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm có tên là "+ timkiemHDTK.getText());		
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row ==-1) {
			return;
		}
		if(flag==1) {
			//lấy dữ liệu từ table sang bên left 
			 Object maDonHang = table.getValueAt(row, 0);  
			 Object maChiTietDonHang = table.getValueAt(row, 1);
			 Integer maNhanVien = Integer.parseInt(table.getValueAt(row, 2).toString());
			 Object ngayTaoDon = table.getValueAt(row, 3);	 
			 Integer maKhachHang = Integer.parseInt(table.getValueAt(row, 4).toString());
			 Integer maSanPham = Integer.parseInt(table.getValueAt(row, 5).toString());
			 Object soLuong = table.getValueAt(row, 6);
			 Object khuyenMai = table.getValueAt(row, 7);
			 Object tongTien = table.getValueAt(row, 8);
			 Object  moTa = table.getValueAt(row, 9);
			 
	
			 ctmaDonHangTF.setText(maDonHang.toString());
			 ctmaChiTietDonHangTF.setText(maChiTietDonHang.toString());
			 cttenNhanVienTF.setText(nhanVien_Dao.findNhanVien(maNhanVien).getTenNhanVien());
	 
			 String tenKH= khachHang_Dao.findKhachHang(maKhachHang).getTenKhachHang();
			 ctngayTaoDonJD.setDate(fo.huyDateFormat(ngayTaoDon.toString()));
			 ctngayTaoDonJD.setDateFormatString("dd/MM/yyyy");
			 cttenkhachHangTF.setText(tenKH!=null?tenKH:"");
			 cttenSanPhamTF.setText(sanPham_Dao.findSanPham(maSanPham).getTenSanPham());
			 ctsoLuongTF.setText(soLuong.toString());
			 ctkhuyenMaiTF.setText(khuyenMai.toString());
			 cttongTienTF.setText(tongTien.toString());
			 ctmoTaTA.setText(moTa!=null? moTa.toString():"");
			 
			 
			 // thực hiện đổi south của right 
			Component southComponent = right.getComponent(1);
			right.remove(southComponent);
			right.add(phanTongHoaDon(),BorderLayout.SOUTH);
			right.revalidate();
			right.repaint();
			
			// read data
			readDataForPhanTongHoaDon();
		}
		else if(flag==2) {
			String maSp= table.getValueAt(row, 0).toString();  
			String tenSp= table.getValueAt(row, 1).toString();  
			String path = table.getValueAt(row, 2).toString(); 
			String maLoai= table.getValueAt(row, 3).toString();  
			String soLuongDaB= table.getValueAt(row, 4).toString(); 
			String soDonH= table.getValueAt(row, 5).toString();  
			String tongTien= table.getValueAt(row, 6).toString();  
			
			ImageIcon icon = new ImageIcon(path);
			 Image newimg = icon.getImage().getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			 icon = new ImageIcon(newimg); 
			anhIcon.setIcon(icon);
			spmaSanPhamTF.setText(maSp);
			sptenSanPhamTF.setText(tenSp);
			spmaLoaiTF.setText(maLoai);
			sptenLoaiTF.setText(loaiSanPham_Dao.findLoaiSanPham(Integer.parseInt(maLoai)).getTenLoai());
			spsoLuongDaBanTF.setText(soLuongDaB);
			
			spsoDonHangTF.setText(soDonH);
			sptongTienTF.setText(tongTien);
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
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(boLocThoiGian)) {
			if(boLocThoiGian.getSelectedIndex()!=0) {
				chiTietBoLocThoiGianStart.setEnabled(false);
				chiTietBoLocThoiGianEnd.setEnabled(false);
			}
			else {
				chiTietBoLocThoiGianStart.setEnabled(true);
				chiTietBoLocThoiGianEnd.setEnabled(true);
			}
		
		}
		else if(e.getSource().equals(sapXepHDComboBox)) {
			if(sapXepHDComboBox.getSelectedIndex()==0) {
				getBoLoc();
				removeData();
				readData(thoiGianStart, thoiGianEnd, loaiSPTrongBoLoc);
			}
			else if(sapXepHDComboBox.getSelectedIndex()==1) {
				getBoLoc();
				removeData();
				int row=0;
				ArrayList<ArrayList<String>> data;
				try {
					data = thongKe_Dao.getDataTheoDonHangDuocSapXep(thoiGianStart,thoiGianEnd,loaiSPTrongBoLoc,1);
					for (ArrayList<String> arrayList : data) {
						DefaultTableModel tableModel=  (DefaultTableModel) table.getModel();
						tableModel.addRow(arrayList.toArray());
						formatDateMoneyDiscount(row,3,1);
						formatDateMoneyDiscount(row,8,2);
						formatDateMoneyDiscount(row,7,3);
						row++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
			else if(sapXepHDComboBox.getSelectedIndex()==2) {
				getBoLoc();
				removeData();
				int row=0;
				ArrayList<ArrayList<String>> data;
				try {
					data = thongKe_Dao.getDataTheoDonHangDuocSapXep(thoiGianStart,thoiGianEnd,loaiSPTrongBoLoc,0);
					for (ArrayList<String> arrayList : data) {
						DefaultTableModel tableModel=  (DefaultTableModel) table.getModel();
						tableModel.addRow(arrayList.toArray());
						formatDateMoneyDiscount(row,3,1);
						formatDateMoneyDiscount(row,8,2);
						formatDateMoneyDiscount(row,7,3);
						row++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}

		}
	}
}
