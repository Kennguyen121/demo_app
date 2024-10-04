package xuly;

import java.util.ArrayList;

import entity.ChiTietDonHang;
public class XuLyDonHang {
	private Integer maDonHang;
	private Integer maBan;
	private ArrayList<ChiTietDonHang> dsChiTietDonHang;
	
	public XuLyDonHang(Integer maBan) {
		super();
		this.maBan= maBan;
		this.maDonHang= null;
		this.dsChiTietDonHang = new ArrayList<ChiTietDonHang>();
	}
	
	public Integer getMaBan() {
		return maBan;
	}

	public void setMaBan(Integer maBan) {
		this.maBan = maBan;
	}

	public Integer getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(Integer maDonHang) {
		this.maDonHang = maDonHang;
	}

	public ArrayList<ChiTietDonHang> getDsChiTietDonHang() {
		return dsChiTietDonHang;
	}

	public void setDsChiTietDonHang(ArrayList<ChiTietDonHang> dsChiTietDonHang) {
		this.dsChiTietDonHang = dsChiTietDonHang;
	}
	public ChiTietDonHang findChiTietDonHang(Integer maCTDH) {
		for (ChiTietDonHang chiTietDonHang : dsChiTietDonHang) {
			if(chiTietDonHang.getMaChiTietDonHang()==maCTDH)
				return chiTietDonHang;
		}
		return null;
	}
	public ChiTietDonHang findChiTietDonHangTheoTenSanPham(String tenSP) {
		System.out.println("tên sản phẩm cần tìm "+ tenSP);
		for (ChiTietDonHang chiTietDonHang : dsChiTietDonHang) {
			if(chiTietDonHang!=null) {
				if(chiTietDonHang.getSanPham().getTenSanPham().compareToIgnoreCase(tenSP)==0)
					return chiTietDonHang;
			}
		
	
		}
		return null;
	}
	public Integer addChiTietDonHang(ChiTietDonHang ctdh) {
		ChiTietDonHang chiTietDonHang = findChiTietDonHangTheoTenSanPham(ctdh.getSanPham().getTenSanPham());
		//nếu mà đã tồn tại sản phẩm thì tăng số lượng
		if(chiTietDonHang!=null) {
		  	Integer sl= chiTietDonHang.getSoLuong();
		  	System.out.println("	số lượng trong đơn hàng là " +sl);
		  	Double gia=chiTietDonHang.getSanPham().getGia();
		  	Double tongTien =  gia* sl -(gia*sl*chiTietDonHang.getKhuyenMai()/100.0);
		  	chiTietDonHang.setTongTien(tongTien);

		  	return 0;
		}
		System.out.println("Thêm thành công");
		dsChiTietDonHang.add(ctdh);
		return 1;
	}
	public Boolean xoaChiTietDonHang(String tenSp) {
		ChiTietDonHang chiTietDonHang= findChiTietDonHangTheoTenSanPham(tenSp);
		if(chiTietDonHang!=null) {
			System.out.println("xóa thành công");
			return dsChiTietDonHang.remove(chiTietDonHang);
		}
		return false;
	}
	public Boolean updateChiTietDonHang(String tenSP,Integer soLuong) {
		ChiTietDonHang chiTietDonHang= findChiTietDonHangTheoTenSanPham(tenSP);
		if(chiTietDonHang!=null) {
			System.out.println("cập nhật thành công");
			chiTietDonHang.setSoLuong(soLuong);
			return true;
		}
		return false;
	}
	public Boolean updateTongTienCTDH(String tenSP,Double khuyenMai) {
		ChiTietDonHang chiTietDonHang= findChiTietDonHangTheoTenSanPham(tenSP);
		if(chiTietDonHang!=null) {
			System.out.println("cập nhật tiền thành công");
			Double tongT= chiTietDonHang.getTongTien();
			tongT -= tongT*khuyenMai;
			chiTietDonHang.setTongTien(tongT);
			return true;
		}
		return false;
	}
	public void xemDanhSachChiTietHoaDon() {
		for (ChiTietDonHang chiTietDonHang : dsChiTietDonHang) {

			xemChiTietDonHang(chiTietDonHang);
		}
	}
	public void xemChiTietDonHang(ChiTietDonHang ct) {
		System.out.println("chi tiết đơn hàng "+ct.getMaChiTietDonHang() +" "+ ct.getSanPham().getTenSanPham() +" "+ct.getSoLuong());
	}
	
}
