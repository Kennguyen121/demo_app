package entity;

import java.util.Objects;

public class KhachHang {
	private int maKH;
	private String hoTen;
	private String soDienThoai;
	private String email;
	
	// Constructor
	public KhachHang(int maKH, String hoTen, String soDienThoai, String email) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.email = email;
	}

	public KhachHang() {
		super();
	}

	// Getters and Setters
	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMaKH() {
		return maKH;
	}

	// toString
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", email=" + email
				+ "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		KhachHang other = (KhachHang) obj;
		return maKH == other.maKH;
	}
	
	
}
