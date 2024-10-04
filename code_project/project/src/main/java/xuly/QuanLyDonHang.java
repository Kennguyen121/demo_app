package xuly;

import java.util.ArrayList;

import entity.ChiTietDonHang;
import entity.DonHang;

public class QuanLyDonHang {
	private ArrayList<XuLyDonHang> dsDonHang;

	public QuanLyDonHang() {
		super();
		this.dsDonHang = new ArrayList<XuLyDonHang>();
	}

	public ArrayList<XuLyDonHang> getDsDonHang() {
		return dsDonHang;
	}

	public void setDsDonHang(ArrayList<XuLyDonHang> dsDonHang) {
		this.dsDonHang = dsDonHang;
	}
	public Boolean addDonHang(XuLyDonHang dh) {
		for (XuLyDonHang xuLyDonHang : dsDonHang) {
			if(xuLyDonHang.getMaBan()==dh.getMaBan()) {
				return false;
			}
		}
		return dsDonHang.add(dh);
	}
	public Boolean xoaDonHang(Integer maBan) {
		for (XuLyDonHang xuLyDonHang : dsDonHang) {
			if(xuLyDonHang.getMaBan()==maBan) {
				System.out.println("Xóa xử lý đơn hàng thành công");
				dsDonHang.remove(xuLyDonHang);
				return true;
			}
		}
		return false;
	}
	public Boolean suaDonHang(XuLyDonHang dh) {
		for (int i=0;i<dsDonHang.size();i++) {
			if(dsDonHang.get(i).getMaBan()==dh.getMaBan()) {
				dsDonHang.set(i, dh);
				return true;
			}
		}
		return false;
	}
	public XuLyDonHang timDonHangTheoMaHoaDon(Integer maDH) {
		for (XuLyDonHang xuLyDonHang : dsDonHang) {
			if(xuLyDonHang.getMaDonHang()==maDH) {
				return xuLyDonHang;
			}
		}
		return null;
	}
	public XuLyDonHang timDonHangTheoBan(Integer maBan) {
		for (XuLyDonHang xuLyDonHang : dsDonHang) {
			if(xuLyDonHang.getMaBan()==maBan) {
				return xuLyDonHang;
			}
		}
		return null;
	}
	public Boolean updateTongTien(Integer maBan,Double khuyenMai) {
		for (XuLyDonHang xuLyDonHang : dsDonHang) {
			if(xuLyDonHang.getMaBan()==maBan) {
				for (ChiTietDonHang ctdh : xuLyDonHang.getDsChiTietDonHang()) {
					ctdh.setTongTien(ctdh.getTongTien()*(1-khuyenMai));
					ctdh.setKhuyenMai(khuyenMai);
					DonHang dh= ctdh.getDonHang();
					dh.setTongTien(dh.getTongTien()*(1-khuyenMai));
					ctdh.setDonHang(dh);
				}
				return true;
			}
			
		}
		return false;
	}
}
