package entity;

import java.util.Objects;

public class Mon {
	private int maMon;
	private String tenMon;
	private double donGia;
	private int soLuong;
	private LoaiMon loaiMon;
	
	// Constructor
	public Mon(int maMon, String tenMon, double donGia, int soLuong, LoaiMon loaiMon) {
		super();
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.loaiMon = loaiMon;
	}
	
	public Mon() {
		super();
	}

	// Getters and Setters
	public String getTenMon() {
		return tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	
	public int getSoLuong() {
		return soLuong;
	}
	
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public LoaiMon getLoaiMon() {
		return loaiMon;
	}

	public void setLoaiMon(LoaiMon loaiMon) {
		this.loaiMon = loaiMon;
	}

	public int getMaMon() {
		return maMon;
	}

	// toString
	@Override
	public String toString() {
		return "Mon [maMon=" + maMon + ", tenMon=" + tenMon + ", donGia=" + donGia + ", loaiMon=" + loaiMon + "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maMon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Mon other = (Mon) obj;
		return maMon == other.maMon;
	}
	
	
}
