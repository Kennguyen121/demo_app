package entity;

import java.util.Objects;

public class LoaiMon {
	private int maLoai;
	private String tenLoai;
	
	// Constructor
	public LoaiMon(int maLoaiMon, String tenLoaiMon) {
		super();
		this.maLoai = maLoaiMon;
		this.tenLoai = tenLoaiMon;
	}

	public LoaiMon() {
		super();
	}
	
	// Getters and Setters
	public int getMaLoai() {
		return maLoai;
	}
	
	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	// toString
	@Override
	public String toString() {
		return "LoaiMon [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		LoaiMon other = (LoaiMon) obj;
		return maLoai == other.maLoai;
	}
}
