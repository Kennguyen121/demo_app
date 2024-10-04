package entity;

import java.util.Date;
import java.util.Objects;

public class DonDatBan {
	private int maDon;
	private KhachHang khachHang;
	private Date thoiGianNhanBan;
	private Date thoiGianTraBan;
	private Date thoiGianDatBan;
	private double tienCoc;
	private String trangThai;
	private NhanVien nhanVien;
	private HoaDonThanhToan hoaDonThanhToan;
	
	// Constructor
	public DonDatBan(int maDon, KhachHang khachHang, Date thoiGianBatDau, Date thoiGianKetThuc,
			Date thoiGianDatBan, double tienCoc, String trangThai, NhanVien nhanVien,  HoaDonThanhToan hoaDonThanhToan) {
		super();
		this.maDon = maDon;
		this.khachHang = khachHang;
		this.thoiGianNhanBan = thoiGianBatDau;
		this.thoiGianTraBan = thoiGianKetThuc;
		this.thoiGianDatBan = thoiGianDatBan;
		this.tienCoc = tienCoc;
		this.trangThai = trangThai;
		this.nhanVien = nhanVien;
		this.hoaDonThanhToan = hoaDonThanhToan;
	}

	public DonDatBan() {
		super();
	}

	// Getters and Setters
	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Date getThoiGianBatDau() {
		return thoiGianNhanBan;
	}

	public void setThoiGianBatDau(Date thoiGianBatDau) {
		this.thoiGianNhanBan = thoiGianBatDau;
	}

	public Date getThoiGianKetThuc() {
		return thoiGianTraBan;
	}

	public void setThoiGianKetThuc(Date thoiGianKetThuc) {
		this.thoiGianTraBan = thoiGianKetThuc;
	}

	public Date getThoiGianDatBan() {
		return thoiGianDatBan;
	}

	public void setThoiGianDatBan(Date thoiGianDatBan) {
		this.thoiGianDatBan = thoiGianDatBan;
	}

	public double getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(double tienCoc) {
		this.tienCoc = tienCoc;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public int getMaDon() {
		return maDon;
	}
	
	public HoaDonThanhToan getHoaDonThanhToan() {
		return hoaDonThanhToan;
	}
	
	public void setHoaDonThanhToan(HoaDonThanhToan hoaDonThanhToan) {
        this.hoaDonThanhToan = hoaDonThanhToan;
    }

	// toString
	@Override
	public String toString() {
		return "DonDatBan [maDon=" + maDon + ", khachHang=" + khachHang
				+ ", thoiGianBatDau=" + thoiGianNhanBan + ", thoiGianKetThuc=" + thoiGianTraBan + ", thoiGianDatBan="
				+ thoiGianDatBan + ", tienCoc=" + tienCoc + ", trangThai=" + trangThai + ", nhanVien=" + nhanVien + "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		DonDatBan other = (DonDatBan) obj;
		return maDon == other.maDon;
	}
	
	
}
