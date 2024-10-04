package entity;

import java.util.Date;

public class HoaDonThanhToan {
	private String maHoaDon;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private DonGoiMon donGoiMon;
	private Date thoiGianTao;
	private Thue thue;
	
	public HoaDonThanhToan(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, DonGoiMon donGoiMon,
			Date thoiGianTao, Thue thue) {
		super();
		this.maHoaDon = maHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.donGoiMon = donGoiMon;
		this.thoiGianTao = thoiGianTao;
		this.thue = thue;
	}
	
	public HoaDonThanhToan() {
		super();
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public DonGoiMon getDonGoiMon() {
		return donGoiMon;
	}

	public void setDonGoiMon(DonGoiMon donGoiMon) {
		this.donGoiMon = donGoiMon;
	}

	public Date getThoiGianTao() {
		return thoiGianTao;
	}

	public void setThoiGianTao(Date thoiGianTao) {
		this.thoiGianTao = thoiGianTao;
	}

	public Thue getThue() {
		return thue;
	}

	public void setThue(Thue thue) {
		this.thue = thue;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	@Override
	public String toString() {
		return "HoaDonThanhToan [maHoaDon=" + maHoaDon + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", donGoiMon=" + donGoiMon + ", thoiGianTao=" + thoiGianTao + ", thue=" + thue + "]";
	}
	
	// Derived attributes
	public double getTongTien() {
		return 0;
	}
}
