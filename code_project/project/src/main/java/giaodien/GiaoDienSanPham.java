package giaodien;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import dao.LoaiSanPham_Dao;
import dao.SanPham_Dao;
import entity.LoaiSanPham;
import entity.SanPham;
import xuly.FormatTienVaDate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class GiaoDienSanPham extends JPanel implements ActionListener,ItemListener,MouseListener{
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel anhSPF;
	private JTextField maSPF;
	private JTextField tenSPF;
	private JComboBox<String> trangThaiF;
	private JTextField giaF;
	private JTextField donVTF;
	private LoaiSanPham_Dao loaiSanPham_Dao= new LoaiSanPham_Dao();
	private SanPham_Dao sanPham_Dao =new SanPham_Dao();
	private JComboBox<String> loaiSPF;
	private JButton themAnhButton;
	private JButton them;
	private JButton sua;
	private JButton xoa;
	private JButton lammoi;
	private JButton timkiemBT;
	private  Font font = new Font("", Font.BOLD, 25);
	private Font smallFont= new Font("", Font.BOLD, 20);
	private JTable table;
	private JComboBox<String> loaiSPTK;
	private String pathAnh=null;
	private JTextField timkiemF;
	private FormatTienVaDate fo =new FormatTienVaDate();
	private JButton refresh;
	public GiaoDienSanPham() {
        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(SwingConstants.VERTICAL, initLeft(),initRight());
        add(splitPane,BorderLayout.CENTER);
        readData();
        themAnhButton.addActionListener(this);
        them.addActionListener(this);
        sua.addActionListener(this);
        xoa.addActionListener(this);
        lammoi.addActionListener(this);
        timkiemBT.addActionListener(this);
        refresh.addActionListener(this);
        loaiSPTK.addItemListener(this);
  
        table.addMouseListener(this);
    }
    public JPanel initLeft() {
        JLabel thongTin = new JLabel("Thông tin sản phẩm");
        JLabel anhSP = new JLabel("Ảnh sản phẩm                       ");
        JLabel maSP = new JLabel("Mã sản phẩm");
        JLabel tenSP = new JLabel("Tên sản phẩm   ");
        JLabel loaiSp = new JLabel("Loại sản phẩm");
        JLabel trangThai = new JLabel("Trạng thái");
        JLabel gia = new JLabel("Giá");
        JLabel donVT = new JLabel("Đơn vị tính");
     
        maSP.setPreferredSize(tenSP.getPreferredSize());
        loaiSp.setPreferredSize(tenSP.getPreferredSize());
        gia.setPreferredSize(tenSP.getPreferredSize());     
        trangThai.setPreferredSize(tenSP.getPreferredSize());
        donVT.setPreferredSize(tenSP.getPreferredSize());
        
        thongTin.setFont(font);

        anhSPF = new JLabel();
        anhSPF.setBorder(new LineBorder(Color.gray));
        maSPF = new JTextField(30);
        tenSPF = new JTextField(30);
        loaiSPF = new JComboBox<String>();
		ArrayList<LoaiSanPham> arrayLSP=  loaiSanPham_Dao.getLoaiSanPham();
		for (LoaiSanPham loaiSanPham : arrayLSP) {
			loaiSPF.addItem(loaiSanPham.getTenLoai());
		}
		loaiSPF.setPreferredSize(new Dimension(320, 25));
        trangThaiF = new JComboBox<String>(new String[]{"Còn hàng", "Hết hàng"});
        trangThaiF.setPreferredSize(new Dimension(320, 25));
        giaF = new JTextField(30);
        donVTF = new JTextField(30);
                
        JPanel line1 = new JPanel();
        line1.add(thongTin);
     
        JPanel line2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        line2.add(anhSP);
        
        JPanel line9 = new JPanel();
        line9.add(anhSPF);
        
        JPanel line3 = new JPanel();
        line3.add(maSP);
        line3.add(maSPF);
        
        JPanel line4 = new JPanel();
        line4.add(tenSP);
        line4.add(tenSPF);
        
        JPanel line5 = new JPanel();
        line5.add(loaiSp);
        line5.add(loaiSPF);
        
        JPanel line6 = new JPanel();
        line6.add(trangThai);
        line6.add(trangThaiF);
        
        JPanel line7 = new JPanel();
        line7.add(gia);
        line7.add(giaF);
      
        JPanel line8 = new JPanel();
        line8.add(donVT);
        line8.add(donVTF);
        Box vbox = Box.createVerticalBox();
        vbox.add(line1);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line2);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line9);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line3);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line4);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line5);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line6);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line7);
        vbox.add(Box.createVerticalStrut(5));
        vbox.add(line8);

        themAnhButton = new JButton("Thêm ảnh");
        // Xử lý sự kiện khi nhấn vào nút "Thêm ảnh"

        line2.add(themAnhButton);
      
        them = new JButton("Thêm");     
        sua = new JButton("Sửa");  
        xoa = new JButton("Xóa");
        lammoi = new JButton("Xóa rỗng");
        
        them.setFont(smallFont);
        sua.setFont(smallFont);
        xoa.setFont(smallFont);
        lammoi.setFont(smallFont);
 

        // Tạo Box vertical và thêm các nút vào đó
        JPanel boxButton = new JPanel(new GridLayout(2, 2, 40, 30));
        boxButton.add(them);
        boxButton.add(xoa);
        boxButton.add(sua);
        boxButton.add(lammoi);
        boxButton.setBorder(new EmptyBorder(0, 0, 30, 0));
        JPanel left= new JPanel(new BorderLayout());
        JPanel box= new JPanel();
        box.add(vbox);
        left.add(box,BorderLayout.CENTER);
        left.add(boxButton,BorderLayout.SOUTH);
        return left;
        
	}
    public JPanel initRight() {
    	JLabel loaiSP= new JLabel("Loại sản phẩm   ");
    	loaiSP.setFont(smallFont);
    	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
    	model.addElement("Tất cả");
    	for (int i = 0; i < loaiSPF.getItemCount(); i++) {
    		model.addElement(loaiSPF.getItemAt(i));
    	}
    	loaiSPTK= new JComboBox<String>(model);
    	refresh = new JButton("     Refresh bảng  ");
    	refresh.setFont(smallFont);
    	JLabel tenSP = new JLabel("Tên sản phẩm   ");
    	tenSP.setFont(smallFont);
        timkiemF = new JTextField("sreach",8);
        timkiemBT = new JButton("Tìm kiếm");

        Box timKiemBox = Box.createHorizontalBox();
        timKiemBox.add(refresh);
        timKiemBox.add(Box.createHorizontalStrut(20));
        timKiemBox.add(loaiSP);
        timKiemBox.add(Box.createHorizontalStrut(20));
        timKiemBox.add(loaiSPTK);
        timKiemBox.add(Box.createHorizontalStrut(20));
        timKiemBox.add(tenSP);
        timKiemBox.add(Box.createHorizontalStrut(20));
        timKiemBox.add(timkiemF);
        timKiemBox.add(Box.createHorizontalStrut(20));
        timKiemBox.add(timkiemBT);
        timKiemBox.setBorder(new TitledBorder("Tìm kiếm"));
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Trạng thái", "Loại sản phẩm", "Đơn vị tính", "Giá", "Ảnh"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        // Get the InputMap for the WHEN_IN_FOCUSED_WINDOW condition
        InputMap inputMap = timkiemBT.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Map the ENTER key to the "click" action
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "click");

        // Now add an ActionMap entry that performs the click action
        timkiemBT.getActionMap().put("click", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
				timkiemBT.doClick();
            }
        });
     // Get the InputMap for the WHEN_IN_FOCUSED_WINDOW condition
        InputMap inputMapRefresh = refresh.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Map the F5 key to the "click" action
        inputMapRefresh.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "click");

        // Now add an ActionMap entry that performs the click action
        refresh.getActionMap().put("click", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                refresh.doClick();
            }
        });

    
       
        JScrollPane pane = new JScrollPane(table);
        JPanel right = new JPanel(new BorderLayout());
        right.add(timKiemBox,BorderLayout.NORTH);
        right.add(pane,BorderLayout.CENTER);
        return right;
	}
    public void removeDate() {
		DefaultTableModel model =(DefaultTableModel) table.getModel();
		model.getDataVector().removeAllElements();
	}
	public void readData() {
		ArrayList<SanPham> arr= sanPham_Dao.getSanPham();
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		for (SanPham sanPham : arr) {
			String trangThai= sanPham.getTrangThai()?"Còn hàng":"Hết hàng";
	
			String gia = fo.taoTienFormat( sanPham.getGia());
			String anhString = sanPham.getAnh();
			model.addRow(new Object[] {sanPham.getMaSanPham().toString(),
										sanPham.getTenSanPham(),
										trangThai,
										sanPham.getLoaiSanPham().getTenLoai(),
										sanPham.getDonViTinh(),
										gia,
										anhString});
		}
	}
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sc=  e.getSource();
		if(sc.equals(themAnhButton)) {
		     JFileChooser fileChooser = new JFileChooser();
             FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "gif");
             fileChooser.setFileFilter(filter);
             int returnValue = fileChooser.showOpenDialog(null);
             if (returnValue == JFileChooser.APPROVE_OPTION) {
                 File selectedFile = fileChooser.getSelectedFile();                  
                 ImageIcon img = new ImageIcon(selectedFile.getPath());  
                  pathAnh= selectedFile.getPath();
                 Image newimg = img.getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
                 img = new ImageIcon(newimg);                
                 anhSPF.setIcon(img);
             }
		}
		else if(sc.equals(lammoi)) {
			anhSPF.setIcon(null);
			maSPF.setText("");
	        tenSPF.setText("");
	        loaiSPF.setSelectedIndex(0);
	        trangThaiF.setSelectedIndex(0);
	        giaF.setText("");
	        donVTF.setText("");
	    	maSPF.requestFocus();
		}
		else if(sc.equals(xoa)) {
			int row =table.getSelectedRow();
			if(row!=-1) {
				Integer ma= Integer.parseInt( table.getValueAt(row, 0).toString());
				if(!sanPham_Dao.deleteSanPham(ma)) {
					JOptionPane.showMessageDialog(null, "Xóa thất bại");				
					return;
				}
				JOptionPane.showMessageDialog(null, "Xóa thành công");
				
			}
		}
		else if(sc.equals(them)) {
			if(isValidValue()) {
				Integer maSp = Integer.parseInt(maSPF.getText().toString());
				Boolean trangThai = (trangThaiF.getSelectedIndex()==0)?true:false;
				Double gia =fo.huyTienFormat(giaF.getText());
				LoaiSanPham lsp = loaiSanPham_Dao.findLoaiSanPhamTheoTen(loaiSPF.getSelectedItem().toString());

				SanPham sp =new SanPham(maSp, tenSPF.getText(), pathAnh, trangThai, gia, donVTF.getText(), lsp);
				if (sanPham_Dao.addSanPham(sp)) {
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					return;
				}
				JOptionPane.showMessageDialog(null, "Thêm thất bại");
			}
		}
		else if(sc.equals(sua)) {
			if(isValidValue()) {
				Integer maSp = Integer.parseInt(maSPF.getText().toString());
				Boolean trangThai = (trangThaiF.getSelectedIndex()==0)?true:false;
				Double gia =fo.huyTienFormat(giaF.getText());
				LoaiSanPham lsp = loaiSanPham_Dao.findLoaiSanPhamTheoTen(loaiSPF.getSelectedItem().toString());
				SanPham sp =new SanPham(maSp, tenSPF.getText(), pathAnh, trangThai, gia, donVTF.getText(), lsp);
				if (sanPham_Dao.updateSanPham(sp)) {
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					return;
				}
				JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
			}
		}
		else if(sc.equals(timkiemBT)) {
			if(timkiemF!=null && timkiemF.getText().compareTo("sreach")!=0) {
				String tenSP= timkiemF.getText();
				for(int i=0;i<table.getRowCount();i++) {
					if(table.getValueAt(i,1).toString().compareToIgnoreCase(tenSP)==0){
						table.setRowSelectionInterval(i,i);
						Rectangle cellRectangle= table.getCellRect(i, 0, true);
						table.scrollRectToVisible(cellRectangle);
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm có tên là "+ timkiemF.getText());
			
		}
		else if(sc.equals(refresh)) {
			removeDate();
			table.revalidate();
			readData();
		}
	}
	public Boolean isValidValue() {
		if(maSPF==null) {
			JOptionPane.showMessageDialog(null,"Mã sản phẩm không rỗng");
			return false;	
		}
		Integer maSPInt;
		try {
			maSPInt =Integer.parseInt(maSPF.getText());
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Mã sản phẩm phải là 1 số");
			return false;	
		}
		if(maSPInt<=0) {
			JOptionPane.showMessageDialog(null, "Mã sản phẩm phải là 1 số dương");
			return false;	
		}
		
		if(tenSPF==null) {	
			JOptionPane.showMessageDialog(null,"Tên sản phẩm không rỗng");
			return false;	
		}
		if(Pattern.matches("^[.]+$", tenSPF.getText())) {	
			JOptionPane.showMessageDialog(null,"Tên sản phẩm không rỗng");
			return false;	
		}
		
		if(donVTF==null) {
			JOptionPane.showMessageDialog(null,"Đơn vị tính không rỗng");
			return false;	
		}
		if(Pattern.matches("^[.]+$", donVTF.getText())) {
			JOptionPane.showMessageDialog(null,"Đơn vị tính không rỗng");
			return false;	
		}
		Double gia=fo.huyTienFormat(giaF.getText());
		if(gia <=0.0) {
			JOptionPane.showMessageDialog(null, "Giá phải là số nguyên dương");
			return false;
		}
		
		if(anhSPF.getIcon()==null) {
			JOptionPane.showMessageDialog(null, "Ảnh sản phẩm không rỗng");
			return false;
		}
		return true;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object sc= e.getSource();
		if(sc.equals(loaiSPTK)) {
			if( loaiSPTK.getSelectedIndex()!=0) {
				String tenLoai= loaiSPTK.getSelectedItem().toString();
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				for(int i = table.getRowCount() - 1; i >= 0; i--) {
		            if(!table.getValueAt(i,3).toString().equalsIgnoreCase(tenLoai)){
		                
		                model.removeRow(i);
		            }
		        }
			}
			else {
				removeDate();
				readData();
			}

		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row!=-1) {
			String maSp= table.getValueAt(row, 0).toString();  
			String tenSp= table.getValueAt(row, 1).toString();  
			String trangThai= table.getValueAt(row, 2).toString(); 
			String loaiSP= table.getValueAt(row, 3).toString(); 
			String donVT= table.getValueAt(row, 4).toString(); 
			String gia= table.getValueAt(row, 5).toString();  
			String path = table.getValueAt(row, 6).toString();  
			
			ImageIcon icon = new ImageIcon(path);
			pathAnh= path;
			 Image newimg = icon.getImage().getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			 icon = new ImageIcon(newimg); 
			anhSPF.setIcon(icon);
			
		      	maSPF.setText(maSp);
		        tenSPF.setText(tenSp);
		        for(int i=0;i<loaiSPF.getItemCount();i++) {
		        	if(loaiSPF.getItemAt(i).compareToIgnoreCase(loaiSP)==0) {
		        		loaiSPF.setSelectedIndex(i);
		        		break;
		        	}
		        }				 
		        for(int i=0;i<trangThaiF.getItemCount();i++) {
		        	if(trangThaiF.getItemAt(i).compareToIgnoreCase(trangThai)==0) {
		        		trangThaiF.setSelectedIndex(i);
		        		break;
		        	}
		        }
		        giaF.setText(gia);
		        donVTF.setText(donVT);
		        
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
