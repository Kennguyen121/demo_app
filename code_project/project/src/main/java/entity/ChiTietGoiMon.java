package entity;

public class ChiTietGoiMon {
	private Mon mon;
	private int soLuong;
	private DonGoiMon donGoiMon;
	private String ghiChu;
	
	// Constructor
	public ChiTietGoiMon(Mon mon, int soLuong, DonGoiMon donGoiMon, String ghiChu) {
		super();
		this.mon = mon;
		this.soLuong = soLuong;
		this.donGoiMon = donGoiMon;
		this.ghiChu = ghiChu;
	}
	
	public ChiTietGoiMon() {
		super();
	}

	// Getters and Setters
	public Mon getMon() {
		return mon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public DonGoiMon getDonGoiMon() {
		return donGoiMon;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	// Derived Attributes
	public double thanhTien() {
		return mon.getDonGia() * soLuong;
	}

	//To String
	@Override
	public String toString() {
		return "ChiTietGoiMon [mon=" + mon + ", soLuong=" + soLuong + ", donGoiMon=" + donGoiMon + ", ghiChu=" + ghiChu
				+ ", thanhTien()=" + thanhTien() + "]";
	}
}
