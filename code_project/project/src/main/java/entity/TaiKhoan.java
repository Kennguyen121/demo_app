package entity;

public class TaiKhoan {
	private NhanVien nhanVien;
	private String tenDangNhap;
	private String matKhau;
	
	public TaiKhoan(NhanVien nhanVien, String tenDangNhap, String matKhau) {
		super();
		this.nhanVien = nhanVien;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
}
