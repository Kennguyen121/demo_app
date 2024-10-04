package entity;

import java.util.Date;
import java.util.Objects;

public class DonGoiMon {
	private int maDon;
	private Date thoiGian;
	private Ban ban;
	private HoaDonThanhToan hoaDonThanhToan;
	
	// Constructor
	public DonGoiMon(int maDon, Date thoiGian, Ban ban, HoaDonThanhToan hoaDonThanhToan) {
		super();
		this.maDon = maDon;
		this.thoiGian = thoiGian;
		this.ban = ban;
		this.hoaDonThanhToan = hoaDonThanhToan;
	}
	
	public DonGoiMon() {
		super();
	}

	// Getters and Setters
	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	public int getMaDon() {
		return maDon;
	}

	public Ban getBan() {
		return ban;
	}

	public HoaDonThanhToan getHoaDonThanhToan() {
		return hoaDonThanhToan;
	}

	// toString
	@Override
	public String toString() {
		return "DonGoiMon [maDon=" + maDon + ", thoiGian=" + thoiGian + "]";
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
		DonGoiMon other = (DonGoiMon) obj;
		return maDon == other.maDon;
	}
	
	
}
