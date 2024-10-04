package entity;

import java.util.Objects;

public class NhanVien {
	private int maNV;
	private String hoTen;
	private String soDienThoai;
	private String email;
	private String chucVu;
	
	// Constructor
	public NhanVien(int maNV, String hoTen, String soDienThoai, String email, String chucVu) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.chucVu = chucVu;
	}
	
	public NhanVien() {
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

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public int getMaNV() {
		return maNV;
	}

	// toString
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", email=" + email
				+ ", chucVu=" + chucVu + "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		NhanVien other = (NhanVien) obj;
		return maNV == other.maNV;
	}
	
	
}
