package giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import dao.Ban_Dao;
import dao.ChiTietDonHang_Dao;
import dao.DonHang_Dao;
import dao.KhachHang_Dao;
import dao.LoaiSanPham_Dao;
import dao.SanPham_Dao;
import entity.Ban;
import entity.ChiTietDonHang;
import entity.DonHang;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.SanPham;
import entity.TaiKhoan;
import xuly.FormatTienVaDate;
import xuly.QuanLyDonHang;
import xuly.XuLyDonHang;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class GiaoDienDatMon extends JPanel implements MouseListener, ActionListener,ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel paneLeft;
	private JPanel paneCen;
	private JPanel paneRight;
	private JLabel lblSoBan;
	private JLabel lblTrangThai;
	private JPanel pnTop;
	private JButton btnDatCho;
	private JButton btnDatMon;
	private Box menuBox;
	private JLabel lblNhomLoai;
	private JPanel arrayCard;
	private JPanel pnButtons;
	private JScrollPane scrollPane;
	private JPanel pnRight;
	private JLabel lblTrong;
	private JLabel lblDangPhucVu;
	private JPanel pnTopRight;
	private JPanel greenPanel;
	private JPanel redPanel;
	private JPanel pnContent;
	private JPanel arrayCardTable;
	private JPanel pnCenter;
	private Box infoBox;
	private static final String CARD = "card";
	private static final String TABLE = "table";
	private JScrollPane scrllPane;
	private JPanel cardSP;

	private static final String CARDSP = "cardSP";
//	private static final DecimalFormat priceFormatter = new DecimalFormat("#,###VND");
	private SanPham_Dao sanPham_Dao = new SanPham_Dao();
	private Ban_Dao ban_Dao = new Ban_Dao();
	private KhachHang_Dao khachHang_Dao = new KhachHang_Dao();
	private LoaiSanPham_Dao loaiSanPham_Dao = new LoaiSanPham_Dao();
	private DonHang_Dao donHang_Dao = new DonHang_Dao();
	private ChiTietDonHang_Dao chiTietDonHang_Dao = new ChiTietDonHang_Dao();

	private JPanel selectedTable;
	private boolean btnDatMonClicked = false;
	private JButton btnThanhToan;
	private JTextField inputMoney;
	private JLabel returnMoney;
	private JLabel moneyBack;
	private JComboBox<String> discountComboBox;
//	private int quantity;
//	private int discountPercentage;
	private ArrayList<XuLyDonHang> dsDonHang = new ArrayList<XuLyDonHang>();
//	private DonHang tmpDH;
//	private int tmpMDH;
//	private int tmpMCT;
	private Font smallFont =new Font("", Font.PLAIN, 20);
	private FormatTienVaDate fo = new FormatTienVaDate();
	private Box sanPhamTrongHoaDonBox;
	private JTextField tongTienTF;
	private JTextField thanhTienTF;
	private JButton confirmPayment;
	private JButton cancelPayment;
	private TaiKhoan taiKhoanDangNhap;
	private XuLyDonHang xuLyDonHang;
	private QuanLyDonHang quanLyDonHang= new QuanLyDonHang();
	private Integer maCTDH= chiTietDonHang_Dao.getChiTietDonHang().size();
	private JTextField inputNames;
	private JTextField inputPhones;
//	private JPanel thanhToanPane = initPaymentPanel();
	/**
	 * Create the frame.
	 */
	public GiaoDienDatMon(TaiKhoan tk) {
		taiKhoanDangNhap = tk;
		setLayout(new BorderLayout());
		initLeftPane();
		initCenterPane();
		initRightPane();
//		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.addMouseListener(this);
	}
//khởi tạo right
	private void initRightPane() {
		paneRight = new JPanel();
		paneRight.setLayout(new BorderLayout());
		// phần loại sản phẩm
		JButton btnAll = new JButton("Tất cả");
		menuBox = Box.createHorizontalBox();
		
		menuBox.add(btnAll);
		btnAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblNhomLoai.setText("Tất cả sản phẩm");
				// Remove all components from arrayCard
				arrayCard.removeAll();
				arrayCard.revalidate();
				arrayCard.repaint();
				// Get all products from the database
				ArrayList<SanPham> productList = sanPham_Dao.getSanPham();
				// Add cards for all products to arrayCard
				for (SanPham product : productList) {
					JPanel productCard = createCardSanPham(product.getAnh(), product.getTenSanPham(), product.getGia());
					arrayCard.add(productCard);
				}
				// Update the display
				arrayCard.revalidate();
				arrayCard.repaint();
				
			}
		});
		ArrayList<LoaiSanPham> loaiSanPham = loaiSanPham_Dao.getLoaiSanPham();
		for (LoaiSanPham loai : loaiSanPham) {
			JButton btn = new JButton(loai.getTenLoai());
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JButton b =(JButton) e.getSource();
					if (b.getText().compareToIgnoreCase("Cafe")==0) {
						lblNhomLoai.setText("Cafe");
						// Remove all components from arrayCard
						arrayCard.removeAll();
						arrayCard.revalidate();
						arrayCard.repaint();
						// Get all products from the database
						ArrayList<SanPham> productList = sanPham_Dao.getSanPhamByMaLoai(1);
						// Add cards for all products to arrayCard
						for (SanPham product : productList) {
							JPanel productCard = createCardSanPham(product.getAnh(),product.getTenSanPham(), product.getGia());
							arrayCard.add(productCard);
						}
						// Update the display
						arrayCard.revalidate();
						arrayCard.repaint();
					}
					else if (b.getText().compareToIgnoreCase("Trà")==0) {
						lblNhomLoai.setText("Trà");
						// Remove all components from arrayCard
						arrayCard.removeAll();
						// Get all products from the database
						ArrayList<SanPham> productList = sanPham_Dao.getSanPhamByMaLoai(2);
						// Add cards for all products to arrayCard
						for (SanPham product : productList) {
							JPanel productCard = createCardSanPham(product.getAnh(),product.getTenSanPham(), product.getGia());
							arrayCard.add(productCard);
						}
						// Update the display
						arrayCard.revalidate();
						arrayCard.repaint();
					}
					else if (b.getText().compareToIgnoreCase("Nước Đóng chai")==0) {
						lblNhomLoai.setText("Nước đóng chai");
						// Remove all components from arrayCard
						arrayCard.removeAll();
						// Get all products from the database
						ArrayList<SanPham> productList = sanPham_Dao.getSanPhamByMaLoai(3);
						// Add cards for all products to arrayCard
						for (SanPham product : productList) {
							JPanel productCard = createCardSanPham(product.getAnh(),product.getTenSanPham(), product.getGia());
							arrayCard.add(productCard);
						}
						// Update the display
						arrayCard.revalidate();
						arrayCard.repaint();
					}
					else if (b.getText().compareToIgnoreCase("Đồ ăn")==0) {
						lblNhomLoai.setText("Đồ ăn");
						// Remove all components from arrayCard
						arrayCard.removeAll();
						// Get all products from the database
						ArrayList<SanPham> productList = sanPham_Dao.getSanPhamByMaLoai(4);
						// Add cards for all products to arrayCard
						for (SanPham product : productList) {
							JPanel productCard = createCardSanPham(product.getAnh(),product.getTenSanPham(), product.getGia());
							arrayCard.add(productCard);
						}
						// Update the display
						arrayCard.revalidate();
						arrayCard.repaint();
					}
					else if (b.getText().compareToIgnoreCase("Khác")==0) {
						lblNhomLoai.setText("Khác");
						// Remove all components from arrayCard
						arrayCard.removeAll();
						// Get all products from the database
						ArrayList<SanPham> productList = sanPham_Dao.getSanPhamByMaLoai(5);
						// Add cards for all products to arrayCard
						for (SanPham product : productList) {
							JPanel productCard = createCardSanPham(product.getAnh(),product.getTenSanPham(), product.getGia());
							arrayCard.add(productCard);
						}
						// Update the display
						arrayCard.revalidate();
						arrayCard.repaint();
					}
				}
			});
			menuBox.add(Box.createHorizontalStrut(5));
			menuBox.add(btn);
		}
		
		
		JPanel buttonPane= new JPanel();
		buttonPane.add(menuBox);
		paneRight.add(buttonPane, BorderLayout.NORTH);
		//Title của loại sản phẩm
		lblNhomLoai = new JLabel("Tất cả sản phẩm");
		lblNhomLoai.setFont(new Font("",Font.PLAIN, 20));
		
		pnTopRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnTopRight.add(lblNhomLoai);

		// Add card san pham
		arrayCard = new JPanel(new GridLayout(0, 2, 10, 10));
		ArrayList<SanPham> productList = sanPham_Dao.getSanPham();
		
		for (SanPham product : productList) {
			JPanel productCard = createCardSanPham(product.getAnh(),product.getTenSanPham(), product.getGia());
			arrayCard.add(productCard);
		}

		scrollPane = new JScrollPane(arrayCard);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		pnRight = new JPanel(new BorderLayout());
		pnRight.add(pnTopRight, BorderLayout.NORTH);
		pnRight.add(scrollPane, BorderLayout.CENTER);

		paneRight.add(pnRight, BorderLayout.CENTER);
		paneRight.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// Add to main panel
		add(paneRight, BorderLayout.EAST);

	}
//khởi tạo center
	private void initCenterPane() {
		// Phan dau cua hoa don chua so ban va trang thai cua ban
		lblSoBan = new JLabel("Bàn: ...");
		lblSoBan.setFont(new Font("", Font.PLAIN, 30));
		
		lblTrangThai = new JLabel("Trạng thái: ...");
		lblTrangThai.setFont(smallFont);
		
		Box vBox= Box.createVerticalBox();
		vBox.add(lblSoBan);
		vBox.add(lblTrangThai);
		pnTop = new JPanel();
		pnTop.add(vBox);

		// Phan giua hoa don chua cac mon da dat
		JPanel arrayCardSP = new JPanel();
		sanPhamTrongHoaDonBox = Box.createVerticalBox();
		scrllPane = new JScrollPane(sanPhamTrongHoaDonBox);
		arrayCardSP.add(scrllPane);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setFont(smallFont);
		tongTienTF = new JTextField();
		tongTienTF.setEditable(false);
		JPanel tongTienPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tongTienPanel.add(lblTongTien);
		tongTienPanel.add(tongTienTF);
		
		JLabel lblGiamGia = new JLabel("Giảm giá");
		lblGiamGia.setFont(smallFont);
		String[] discountOptions = { "0%", "5%", "10%", "15%" };
		discountComboBox = new JComboBox<>(discountOptions);
		discountComboBox.addItemListener(this);
		discountComboBox.setEnabled(false);
		JPanel giamGPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		giamGPanel.add(lblGiamGia);
		giamGPanel.add(discountComboBox);
	
		JLabel lblThanhTien = new JLabel("Thành tiền  ");
		lblThanhTien.setFont(smallFont);
		thanhTienTF = new JTextField();
		thanhTienTF.setEditable(false);
		JPanel thanhTienPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
		thanhTienPanel.add(lblThanhTien);
		thanhTienPanel.add(thanhTienTF);
		
		lblTongTien.setPreferredSize(lblThanhTien.getPreferredSize());
		lblGiamGia.setPreferredSize(lblThanhTien.getPreferredSize());
		discountComboBox.setPreferredSize(new Dimension(400, 35));
		tongTienTF.setPreferredSize(new Dimension(400, 35));
		thanhTienTF.setPreferredSize(new Dimension(400, 35));
		
		infoBox = Box.createVerticalBox();
		infoBox.add(tongTienPanel);
		infoBox.add(giamGPanel);
		infoBox.add(thanhTienPanel);
		infoBox.setName("infoBox");
		infoBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		pnCenter.add(arrayCardSP, BorderLayout.CENTER);
		pnCenter.add(infoBox, BorderLayout.SOUTH);

		btnDatCho = new JButton("Đặt chỗ");
		btnDatCho.addActionListener(this);
		btnDatCho.setEnabled(false);
		btnDatCho.setFont(smallFont);
		
		btnDatMon = new JButton("Gọi món");
		btnDatMon.addActionListener(this);
		btnDatMon.setEnabled(false);
		btnDatMon.setFont(smallFont);
		
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(smallFont);
		btnThanhToan.setEnabled(false);
		btnThanhToan.setVisible(true);
		btnThanhToan.addActionListener(this);

		pnButtons = new JPanel();
		pnButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		pnButtons.add(btnDatCho);
		pnButtons.add(btnDatMon);
		pnButtons.add(btnThanhToan);

		paneCen = new JPanel();
		paneCen.setLayout(new BorderLayout());
		paneCen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		paneCen.add(pnTop, BorderLayout.NORTH);
		paneCen.add(pnCenter, BorderLayout.CENTER);
		
		paneCen.add(pnButtons, BorderLayout.SOUTH);

		add(paneCen, BorderLayout.CENTER);
	}
//khởi tạo left
	private void initLeftPane() {
//		JPanel pnLeft = new JPanel();
		// Tao panel voi gridlayout co 4 cot add card ban trong quan
		arrayCardTable = new JPanel(new GridLayout(0, 4, 10, 10));
		ArrayList<Ban> tableList = ban_Dao.getBan();
		for (Ban table : tableList) {
			JPanel tableCard = createCardTable(table.getMaBan(), table.getTrangThai());
			arrayCardTable.add(tableCard);
		}
		
		// Ghi chu: pnContent chua 2 panel greenPanel va redPanel
		lblTrong = new JLabel("Trống");
		greenPanel = new JPanel();
		greenPanel.setBackground(Color.GREEN);

		lblDangPhucVu = new JLabel("Còn Trống");
		redPanel = new JPanel();
		redPanel.setBackground(Color.RED);

		pnContent = new JPanel();
		pnContent.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		pnContent.add(greenPanel);
		pnContent.add(lblTrong);
		pnContent.add(redPanel);
		pnContent.add(lblDangPhucVu);

		paneLeft = new JPanel(new BorderLayout());
		paneLeft.add(arrayCardTable, BorderLayout.CENTER);
		paneLeft.add(pnContent, BorderLayout.SOUTH);
		add(paneLeft, BorderLayout.WEST);

	}
//tạo card cho phần tổng sản phẩm
	public JPanel createCardSanPham(String path, String name, Double price) {
		JLabel ten = new JLabel("<html><p width=\"150\">" + name + "</p></html>");
		ten.setFont(smallFont);
		JLabel gia = new JLabel(fo.taoTienFormat(price));
		gia.setFont(smallFont);
		ImageIcon icon =new ImageIcon(path);
		Image newImg= icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		icon= new ImageIcon(newImg);
		
		JLabel anh = new JLabel(icon);
		JPanel anhpn=  new JPanel();
		anhpn.add(anh);
		anhpn.setBackground(Color.WHITE);
		Box box = Box.createVerticalBox();
		box.add(ten);
		box.add(gia);
	

		Box hBox =Box.createHorizontalBox();
		hBox.add(anhpn);
		hBox.add(Box.createHorizontalStrut(5));
		hBox.add(box);

		JPanel card = new JPanel(new FlowLayout(FlowLayout.LEFT));
		card.setBorder(new LineBorder(Color.black, 2));
		card.setBackground(Color.WHITE);
		card.add(hBox);
		card.setName(CARD);
		card.addMouseListener(this);


		card.putClientProperty("price", price);
		card.putClientProperty("nameProduct", name);

		return card;
	}
//thêm sản phẩm váo hóa đơn
	private void addCardSPToCenterPanel(String name, Double price) {
		// Create CardSP with the given name and price
		cardSP = createCardSP(name, price);
		// Check if the product is already in the arrayCardSP panel
		for (Component component : sanPhamTrongHoaDonBox.getComponents()) {
			if (component instanceof JPanel) {
				JPanel card = (JPanel) component;
				//Neu ten san pham da co trong hoa don
				if (card.getName().equals(CARDSP)) {
					String productName = (String) card.getClientProperty("nameProduct");
					if (productName.equals(name)) {
						// If the product is already in the arrayCardSP panel, increase the quantity by 1
						Integer quantity = (int) card.getClientProperty("quantity") + 1;
						card.putClientProperty("quantity", quantity);
						// Update the quantity in the spinner
						
				        for (Component comp : card.getComponents()) {
				            if (comp instanceof Box) {
				                // Traverse components within the Box
				                for (Component boxComp : ((Box) comp).getComponents()) {
				                    if (boxComp instanceof JSpinner) {
				                        // Update the value of the JSpinner
				                        ((JSpinner) boxComp).setValue(quantity);
				                        break; // No need to continue searching
				                    }
				                }
				            }
				        }
						// Update the display
						sanPhamTrongHoaDonBox.revalidate();
						sanPhamTrongHoaDonBox.repaint();
						// Update the total amount
						updateTotalAmount();
						return;
					}
				}
			}
		}
		
		// If the product is not in the arrayCardSP panel, add it to the arrayCardSP panel
		// Add the CardSP to the arrayCardSP panel in the centerPanel
		
		sanPhamTrongHoaDonBox.add(cardSP);
		// Update the display
		paneCen.revalidate();
		paneCen.repaint();
		// Update the total amount
		updateTotalAmount();
	}
// tạo card cho hóa đơn
	public JPanel createCardSP(String name, Double price) {
		JLabel ten = new JLabel("<html><p width=\"270\">" + name + "</p></html>");
		ten.setFont(smallFont);
		JLabel gia = new JLabel(fo.taoTienFormat(price));
		gia.setFont(smallFont);
		
		// số lượng và nut xóa khỏi hóa đơn
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
		// Initial value, min value, max value, step
		// Create a JSpinner with the SpinnerModel,
		JSpinner soLuong = new JSpinner(spinnerModel);
		
		soLuong.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// When the spinner value changes, update the quantity in the client property 
				// cập nhật luôn trong xử lý dữ liệu
				Container parent = soLuong.getParent().getParent();
				JPanel card = (JPanel) parent;
				card.putClientProperty("quantity", soLuong.getValue());
				
				String nameProduct = (String) card.getClientProperty("nameProduct");
				Integer sl = (Integer) card.getClientProperty("quantity");
				XuLyDonHang dh= quanLyDonHang.timDonHangTheoBan((Integer) selectedTable.getClientProperty("tableNumber"));
				if(dh!=null) {
					dh.updateChiTietDonHang(nameProduct,sl);
				}
				// Recalculate and update the total amount
				updateTotalAmount();
				// làm gì khi thay đổi------------------------------------------------------------
			}
		});

		JButton btn = new JButton("X");
		btn.setForeground(Color.RED);
		btn.setBorder(null);
		
		cardSP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Box hBox= Box.createHorizontalBox();
		hBox.add(ten);
		hBox.add(Box.createHorizontalGlue());
		hBox.add(gia);
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(soLuong);
		hBox.add(Box.createHorizontalStrut(20));
		hBox.add(btn);
		cardSP.add(hBox);
		cardSP.setName(CARDSP);
		cardSP.setBorder( BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));		
		// thực hiện xóa sản phẩm
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Find the parent JPanel of the button
		        // Lấy và in ra ClientProperty("quantity") và "nameProduct" từ cardSP
				  Container parent = btn.getParent().getParent();
				  JPanel card = (JPanel) parent;
		        String nameProduct = (String) card.getClientProperty("nameProduct");
		        XuLyDonHang xuLyDonHang = quanLyDonHang.timDonHangTheoBan((Integer) selectedTable.getClientProperty("tableNumber"));
				
		        if(xuLyDonHang!=null)
					xuLyDonHang.xoaChiTietDonHang(nameProduct);
		        // Xóa cardSP từ container của nó
		      
		        if (parent instanceof JPanel) {
		            sanPhamTrongHoaDonBox.remove(parent);
		            // Repaint the parent container to reflect the changes
		            paneCen.revalidate();
		            paneCen.repaint();
		            updateTotalAmount();
		        }
			}
		});
		cardSP.addMouseListener(this);
		cardSP.putClientProperty("price", price);
		Integer soL = Integer.parseInt( soLuong.getValue().toString());
		cardSP.putClientProperty("quantity", soL);
		cardSP.putClientProperty("nameProduct", name);
		return cardSP;
	}
//tạo card cho table
	public JPanel createCardTable(int tableNumber, boolean trangThai) {
		// Add table icon
		Icon iconBan = new ImageIcon("../project/anh/coffee_table.png");
		iconBan = new ImageIcon(((ImageIcon) iconBan).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		JLabel iconLabel = new JLabel(iconBan);
		
		JLabel tableLabel = new JLabel("Bàn " + tableNumber);
		tableLabel.setFont(smallFont);

		Box box = Box.createVerticalBox();
		box.add(iconLabel);
		box.add(Box.createVerticalStrut(5));
		box.add(tableLabel);

		JPanel tableCard = new JPanel();
		tableCard.setName(TABLE);
		tableCard.addMouseListener(this);

		tableCard.putClientProperty("tableNumber", tableNumber);

		tableCard.add(box);
		if (!trangThai) {
			tableCard.setBackground(Color.GREEN);
		} else {
			tableCard.setBackground(Color.RED);
		}

		return tableCard;
	}
//khởi tạo phần thanh toán
	private JPanel initPaymentPanel() {
		// New panel for payment configuration
		JPanel paymentPanel = new JPanel(new BorderLayout());

		JLabel titlePayment = new JLabel("Thanh toán");
		titlePayment.setFont(new Font("", Font.BOLD, 20));
		paymentPanel.add(titlePayment, BorderLayout.NORTH);

		JLabel nameCustomer = new JLabel("Tên khách hàng: ");
		nameCustomer.setFont(smallFont);

		inputNames = new JTextField(20);
		inputNames.setFont(smallFont);	

		JLabel phone = new JLabel("Số điện thoại: ");
		phone.setFont(smallFont);

		inputPhones = new JTextField(20);
		inputPhones.setFont(smallFont);
		inputPhones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(!Pattern.matches("^0[0-9]{9}$",inputPhones.getText())) {
					moneyBack.setText("<html>Số điện thoại phải theo có 10 số<br>và theo định dạng 0xxxxxxxxx</html>");

				}
				else {
					moneyBack.setText("");
				}
				moneyBack.setForeground(Color.red);
			}
		});

		JLabel money = new JLabel("Nhập số tiền khách đưa: ");
		money.setFont(smallFont);

		inputMoney = new JTextField(20);
		inputMoney.setFont(smallFont);

		returnMoney = new JLabel("Số tiền thừa: ");
		returnMoney.setFont(smallFont);

		moneyBack = new JLabel();
		moneyBack.setFont(new Font("...", Font.PLAIN, 15));
		moneyBack.setForeground(Color.RED);

		JPanel pmCenter = new JPanel();
		pmCenter.setLayout(new GridLayout(5, 2));
		pmCenter.add(nameCustomer);
		pmCenter.add(inputNames);
		pmCenter.add(phone);
		pmCenter.add(inputPhones);
		pmCenter.add(money);
		pmCenter.add(inputMoney);
		pmCenter.add(returnMoney);
		pmCenter.add(moneyBack);
		//khi nhập sai thì không cho thanh toán
		inputMoney.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				 if(!calculateReturnMoney()) {
					 confirmPayment.setEnabled(false);
				 }
				 else 
					 confirmPayment.setEnabled(true);
			}
		});

		paymentPanel.add(pmCenter, BorderLayout.CENTER);

		// Buttons for confirmation and cancellation
		JPanel buttonPanel = new JPanel();
		confirmPayment = new JButton("Xác nhận");
		confirmPayment.setEnabled(false);
		confirmPayment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//thực hiện lưu dữ liệu ------------------------------
				luuQuanLyDonHangVaoDaTaBase();
				JOptionPane.showMessageDialog(null, "Thanh toán thành công");
				selectedTable.setBackground(Color.GREEN);
				lblTrangThai.setText("Trạng thái: Trống");
				sanPhamTrongHoaDonBox.removeAll();
				sanPhamTrongHoaDonBox.revalidate();
				sanPhamTrongHoaDonBox.repaint();
				updateTotalAmount();
				btnDatCho.setText("Đặt chỗ");
				btnDatMon.setEnabled(false);
				btnDatMonClicked = false;
				btnThanhToan.setEnabled(false);
				btnDatMonClicked = false;
				btnDatMon.setText("Gọi món");
				discountComboBox.setSelectedIndex(0);
				
				//thực hiện xóa đơn hàng
				quanLyDonHang.xoaDonHang((Integer) selectedTable.getClientProperty("tableNumber"));
				hidePaymentPanel();
			}
		});
		cancelPayment = new JButton("Huỷ");
		cancelPayment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				discountComboBox.setEnabled(false);
				hidePaymentPanel();
				
			}
		});
		buttonPanel.add(confirmPayment);
		buttonPanel.add(cancelPayment);
		paymentPanel.add(buttonPanel, BorderLayout.SOUTH);
		return paymentPanel;
	}
// cập nhật trạng thái của bàn
	private void updateTableInfo(String tableNumber, Boolean tableStatus) {
		lblSoBan.setText(tableNumber);
		if (tableStatus) {
			lblTrangThai.setText("Trạng thái: Trống");
		} else {
			lblTrangThai.setText("Trạng thái: Đang phục vụ");
		}
	}
//cập nhật tiền	
	private void updateTotalAmount() {
		Double tongTien = calculateTotalAmount();
		Integer discount = Integer.parseInt(discountComboBox.getSelectedItem().toString().replace("%", ""));
	  	Double thanhTien = tongTien-tongTien*(discount/100.0);
		tongTienTF.setText( fo.taoTienFormat((tongTien)));
		// Assuming no discount for now, set thanhTien to tongTien
	  	thanhTienTF.setText( fo.taoTienFormat((thanhTien)));
	}
//tính toán tiền
	private Double calculateTotalAmount() {
		Double tongTien = 0.0;
		for (Component component : sanPhamTrongHoaDonBox.getComponents()) {
			if (component instanceof JPanel) {
				JPanel cardSP = (JPanel) component;
				if (cardSP.getName().equals(CARDSP)) {
					Double price = (Double) cardSP.getClientProperty("price");
					Integer quantity = (Integer) cardSP.getClientProperty("quantity");
					tongTien += price * quantity;
				}
			}
		}
		return tongTien;
	}

//tính toán tiền thừa
	private Boolean calculateReturnMoney() {
		// TODO Auto-generated method stub
		try {
			Double money = Double.parseDouble(inputMoney.getText().trim()); // Thanh tien: 12,700VND
			Double total = fo.huyTienFormat(thanhTienTF.getText());
			moneyBack.setForeground(Color.RED);
			if (money < total) {
				moneyBack.setText("Không đủ tiền");
				return false;
			} else {
				moneyBack.setText(fo.taoTienFormat(money - total));
				return true;
			}
		} catch (NumberFormatException e) {
			moneyBack.setText("Không hợp lệ");
			return false;
		}
	}
//ẩn phần thanh toán
	protected void hidePaymentPanel() {
		// TODO Auto-generated method stub
		paneCen.remove(paneCen.getComponentCount() - 1); // Remove the last component (paymentPanel)
		paneCen.add(pnButtons, BorderLayout.SOUTH);
		paneCen.revalidate();
		paneCen.repaint();
	}
//xử lý sự kiện mouseClicked
	@Override
	public void mouseClicked(MouseEvent e) {

		Component clickedComponent = e.getComponent();
		if (clickedComponent instanceof JPanel) {//Chon panel
			JPanel clickedPanel = (JPanel) e.getSource();
			String panelName = clickedPanel.getName();
			if(btnDatMon.getText().equals("Hủy")&&TABLE.equals(panelName)) {
				JOptionPane.showMessageDialog(null, "vui lòng hủy đặt món để tiếp tục");
				return;
			}
			//Neu panel la ban
			else if (TABLE.equals(panelName)) {
				Integer tableNumber = (Integer) clickedPanel.getClientProperty("tableNumber");
				if (tableNumber != null) {
					selectedTable = clickedPanel;
					boolean trangThai = clickedPanel.getBackground().equals(Color.GREEN);
					// Cap nhat thong tin ban
					updateTableInfo("Bàn " + tableNumber.toString(), trangThai);
					if (!trangThai) {// Neu ban duoc chon da duoc dat cho
						//Set text btnDatCho = "Huy cho" va  enable btnDatMon 
						btnDatCho.setText("Hủy chỗ");
						btnDatMon.setEnabled(true);										
						btnDatMonClicked = false;
						
						//-------------------------------------------------------------------load
						System.out.println("Đang đọc dữ liệu");
						layDonHangChuaThanhToan();
						
					} else {// Ban duoc chon dang trong
						btnDatCho.setText("Đặt chỗ");
						btnDatCho.setEnabled(true);
						btnDatMon.setEnabled(false);
		
						// clear arrayCardSP
						sanPhamTrongHoaDonBox.removeAll();
						sanPhamTrongHoaDonBox.revalidate();
						sanPhamTrongHoaDonBox.repaint();
						updateTotalAmount();
						
						//Set text btnDatMon = "Goi mon"
						btnDatMon.setText("Gọi món");
						
						//Set lblGiamGia = "Giam gia: 0%"
						discountComboBox.setSelectedIndex(0);
						
						//Set btnDatMonClicked = false
						btnDatMonClicked = false;
						
						//Set btnThanhToan = false
						btnThanhToan.setEnabled(false);							
					}
					
				}
				//Neu panel la san pham
			} else if (CARD.equals(panelName)) {
				if (btnDatMonClicked) {
					String name = (String) clickedPanel.getClientProperty("nameProduct");
					Double price = (Double) clickedPanel.getClientProperty("price");
					// Add the product to the arrayCardSP panel in the centerPanel
					addCardSPToCenterPanel(name, price);
					
					//thêm chi tiết đơn hàng trong xử lý đơn hàng
					XuLyDonHang xuLy = quanLyDonHang.timDonHangTheoBan((Integer) (selectedTable.getClientProperty("tableNumber")));
					
					ChiTietDonHang chiTietDonH= newChiTietDonHang(selectedTable, clickedPanel);
					System.out.println("mã "+ chiTietDonH.getMaChiTietDonHang() +" Số lượng "+ chiTietDonH.getSoLuong()+ " tên sản phẩm" + chiTietDonH.getSanPham().getTenSanPham()+" tongTien "+ chiTietDonH.getTongTien());
					Integer maDH= chiTietDonH.getDonHang().getMaDonHang();
					xuLy.setMaDonHang(maDH);
					//thêm chi tiết đơn hàng nếu mà trong xử lý đã có đơn hàng thì sẽ ko tăng mã chi tiết đơn hàng
					if (xuLy.addChiTietDonHang(chiTietDonH)==0) {
						maCTDH--;
						System.out.println(maCTDH);
					}
				
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
// tạo mới 1 đơn hang khi nhấn gọi món
	private DonHang newDonHang(JPanel banDuocChon) {
		Integer tableNumber = (Integer) banDuocChon.getClientProperty("tableNumber");
		Integer maDonHang= donHang_Dao.getDonHang().size()+1;
		while(donHang_Dao.findDonHang(maDonHang)!=null) {
			maDonHang++;
		}
		System.out.println("mã đơn hàng là "+maDonHang);
		Ban ban= ban_Dao.findBan(tableNumber);
		java.util.Date cur = new java.util.Date();
		DonHang dh= new DonHang(maDonHang,taiKhoanDangNhap.getNhanVien(), null, ban,new Date(cur.getTime()),fo.huyTienFormat( thanhTienTF.getText()));
		return dh;
	}
//tạo mới 1 chi tiết đơn hàng khi khi phần hóa đơn được thêm sản phẩm
	private ChiTietDonHang newChiTietDonHang(JPanel banDuocChon,JPanel sanPham) {
		DonHang dh= newDonHang(banDuocChon);
		String productName=null;
		Integer quantity=1;
		Double tien= 0.0;
		if (sanPham.getName().equals(CARD)) {
			productName = (String) sanPham.getClientProperty("nameProduct");	
			tien = (Double) sanPham.getClientProperty("price");
		}
		maCTDH++;
		SanPham sp =sanPham_Dao.findSanPhamByTen(productName);
		Integer discount = Integer.parseInt(discountComboBox.getSelectedItem().toString().replace("%", ""));
		Double khuyenMai = discount/100.0;
		tien-= tien*khuyenMai;
		System.out.println("Tiền sau khi giảm giá của chi tiết hóa dơn "+tien);
		ChiTietDonHang cTDH= new ChiTietDonHang(maCTDH, dh, sp, quantity, khuyenMai,tien , null);

		return cTDH;
	}
// lưu đơn hàng và các chi tiết vào cơ sở dữ liệu khi xác nhận thanh toán
	private Boolean luuQuanLyDonHangVaoDaTaBase() {
		Integer soBanDuocChon= (Integer) selectedTable.getClientProperty("tableNumber");
		Integer inT = Integer.parseInt(discountComboBox.getSelectedItem().toString().replace("%", ""));
	  	Double discount= inT/100.0;
		quanLyDonHang.updateTongTien(soBanDuocChon, discount);
		dsDonHang= quanLyDonHang.getDsDonHang();
		for (XuLyDonHang xuLyDonHang : dsDonHang) {
			if(xuLyDonHang.getMaBan() != soBanDuocChon) {
				continue;
			}
			//tìm kiếm khách hàng theo số điện thoại nếu là khách hàng mới thì thêm khách hàng
			KhachHang khMoi=null;
			if(inputPhones!=null) {
				System.out.println("số điện thoại của khách hàng" +inputPhones.getText());
				System.out.println("tên khách hàng" +inputNames.getText());		
				khMoi = khachHang_Dao.findKhachHang(inputPhones.getText());
				if(khMoi==null) {		
					System.out.println("Tạo khách hàng");
					//tạo khách hàng mới
					Integer maKhachHang= khachHang_Dao.getKhachHang().size()+1;
					String tenKhachHang= inputNames.getText();
					String sdtKhachHang = inputPhones.getText();
					khMoi= new KhachHang(maKhachHang,tenKhachHang,sdtKhachHang);
					if(khachHang_Dao.addKhachHang(khMoi)) {
						System.out.println("thêm khách hàng mới thành công");
					}
				}
			}
			
			// tạo mới đơn hàng
			DonHang dh= xuLyDonHang.getDsChiTietDonHang().get(0).getDonHang();
			dh.setKhachHang(khMoi);
			Double tongTien=0.0;
			if(donHang_Dao.addDonHang(dh)) {
				System.out.println("đơn hàng mới được thêm thành công");
				ArrayList<ChiTietDonHang> dsChiTietDonHang=  xuLyDonHang.getDsChiTietDonHang();
				for (ChiTietDonHang chiTietDonHang : dsChiTietDonHang) {							
					chiTietDonHang_Dao.addChiTietDonHang(chiTietDonHang);	
					tongTien+=chiTietDonHang.getTongTien();
				}
				//khi thanh toán thì xóa đơn hàng trong quản lý
				quanLyDonHang.xoaDonHang(soBanDuocChon);
			}
			System.out.println("tổng tiền là "+tongTien);
			dh.setTongTien(tongTien);
			donHang_Dao.updateDonHang(dh);
			return true;
			
		}
		return false;
	}
// trong trường hợp các hóa đơn chưa thanh toán thì lấy hóa đơn chưa thanh toán từ quản lý hóa đơn
	private void layDonHangChuaThanhToan() {
		sanPhamTrongHoaDonBox.removeAll();
		sanPhamTrongHoaDonBox.revalidate();
		sanPhamTrongHoaDonBox.repaint();
		ArrayList<XuLyDonHang> donHangChuaThanhToan = quanLyDonHang.getDsDonHang();
		Integer soBanDuocChon= (Integer) selectedTable.getClientProperty("tableNumber");
		System.out.println("số bàn đọc dữ liệu "+soBanDuocChon);
		for (XuLyDonHang xuLyDonHang : donHangChuaThanhToan) {
			if(xuLyDonHang.getMaBan()==soBanDuocChon) {
				ArrayList<ChiTietDonHang> dsChiTietDonHangs= xuLyDonHang.getDsChiTietDonHang();
				for (ChiTietDonHang ctdh : dsChiTietDonHangs) {
					String tenSP = ctdh.getSanPham().getTenSanPham();
					Double gia= ctdh.getSanPham().getGia();
					System.out.println("đang thêm các card");
					addCardSPToCenterPanel(tenSP, gia);
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDatCho)) {
			//Neu btnDatCho = "Dat cho" va ban chon dang trong
			if (selectedTable.getBackground().equals(Color.green) && btnDatCho.getText().equals("Đặt chỗ")) {
				if (selectedTable != null) {
					//set trang thai cua ban = "Dang phuc vu" va set buttons
					selectedTable.setBackground(Color.RED);
					lblTrangThai.setText("Trạng thái: Đang phục vụ");
					btnDatCho.setText("Hủy chỗ");
					btnDatMon.setEnabled(true);
					btnThanhToan.setEnabled(false);
					//btnDatMon chua duoc click (click se cho phep add san pham vao hoa don)
					btnDatMonClicked = false;
					//thực hiện tạo mới đơn hàng và lưu vào quanLyDonHang
					Integer tableNumber = (Integer) selectedTable.getClientProperty("tableNumber");
					System.out.println("Đặt bàn ở bàn "+tableNumber);
					xuLyDonHang =new XuLyDonHang(tableNumber);
					quanLyDonHang.addDonHang(xuLyDonHang);
				}
			}
			else if (selectedTable.getBackground().equals(Color.RED) && btnDatCho.getText().equals("Hủy chỗ")) {
				if (selectedTable != null) {
					selectedTable.setBackground(Color.GREEN);
					lblTrangThai.setText("Trạng thái: Trống");
					sanPhamTrongHoaDonBox.removeAll();
					sanPhamTrongHoaDonBox.revalidate();
					sanPhamTrongHoaDonBox.repaint();
					updateTotalAmount();
					btnDatCho.setText("Đặt chỗ");
					btnDatMon.setEnabled(false);
					btnDatMonClicked = false;
					btnThanhToan.setEnabled(false);
					btnDatMonClicked = false;
					btnDatMon.setText("Gọi món");
					discountComboBox.setSelectedIndex(0);
					//thực hiện xóa đơn hàng
					quanLyDonHang.xoaDonHang((Integer) selectedTable.getClientProperty("tableNumber"));
					discountComboBox.setEnabled(false);
				}
			}
		} else if (e.getSource().equals(btnDatMon)) {//Neu click btnDatMon
			if (btnDatMon.getText().equals("Gọi món")) {//Neu btnDatMon = "Goi mon"
				btnDatMon.setText("Hủy");
				btnDatMonClicked = true;
				btnThanhToan.setEnabled(true);
//				discountComboBox.setEditable(false);

			} else if (btnDatMon.getText().equals("Hủy")) {
				btnDatMon.setText("Gọi món");
				btnDatMonClicked = false;
				btnThanhToan.setEnabled(false);
				discountComboBox.setEnabled(false);
			}
		} else if (e.getSource().equals(btnThanhToan)) {
			discountComboBox.setEnabled(true);
			paneCen.remove(pnButtons);
			paneCen.add(initPaymentPanel(), BorderLayout.SOUTH);
			paneCen.revalidate();
			paneCen.repaint();
		} 
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object sc= e.getSource();
		if(sc.equals(discountComboBox)) {
			updateTotalAmount();
		}
	}

}