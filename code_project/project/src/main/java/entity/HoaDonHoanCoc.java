package entity;

import java.util.Date;
import java.util.Objects;

public class HoaDonHoanCoc {
	private String maHoaDon;
	private DonDatBan donDatBan;
	private NhanVien nhanVien;
	private double soTienHoanTra;
	private String ghiChu;
	private Date thoiGianHoanTra;
	
	// Constructor
	public HoaDonHoanCoc(String maHoaDon, DonDatBan donDatBan, NhanVien nhanVien, double soTienHoanTra, String ghiChu,
			Date thoiGianHoanTra) {
		super();
		this.maHoaDon = maHoaDon;
		this.donDatBan = donDatBan;
		this.nhanVien = nhanVien;
		this.soTienHoanTra = soTienHoanTra;
		this.ghiChu = ghiChu;
		this.thoiGianHoanTra = thoiGianHoanTra;
	}
	
	public HoaDonHoanCoc() {
		super();
	}

	// Getters and Setters
	public DonDatBan getDonDatBan() {
		return donDatBan;
	}

	public void setDonDatBan(DonDatBan donDatBan) {
		this.donDatBan = donDatBan;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public double getSoTienHoanTra() {
		return soTienHoanTra;
	}

	public void setSoTienHoanTra(double soTienHoanTra) {
		this.soTienHoanTra = soTienHoanTra;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Date getThoiGianHoanTra() {
		return thoiGianHoanTra;
	}

	public void setThoiGianHoanTra(Date thoiGianHoanTra) {
		this.thoiGianHoanTra = thoiGianHoanTra;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	// toString
	@Override
	public String toString() {
		return "HoaDonHoanCoc [maHoaDon=" + maHoaDon + ", donDatBan=" + donDatBan + ", nhanVien=" + nhanVien
				+ ", soTienHoanTra=" + soTienHoanTra + ", ghiChu=" + ghiChu + ", thoiGianHoanTra=" + thoiGianHoanTra
				+ "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		HoaDonHoanCoc other = (HoaDonHoanCoc) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}
	
	
}
