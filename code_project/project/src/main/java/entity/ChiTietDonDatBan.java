package entity;

public class ChiTietDonDatBan {
	private DonDatBan donDatBan;
	private Ban ban;
	
	// Constructor
	public ChiTietDonDatBan(DonDatBan donDatBan, Ban ban) {
		super();
		this.donDatBan = donDatBan;
		this.ban = ban;
	}
	
	public ChiTietDonDatBan() {
		super();
	}
	
	// Getters and Setters
	public DonDatBan getDonDatBan() {
		return donDatBan;
	}
	
	public void setDonDatBan(DonDatBan donDatBan) {
		this.donDatBan = donDatBan;
	}
	
	public Ban getBan() {
		return ban;
	}
	
	public void setBan(Ban ban) {
		this.ban = ban;
	}
	
	// toString
	@Override
	public String toString() {
		return "ChiTietDonDatBan [donDatBan=" + donDatBan + ", ban=" + ban + "]";
	}
}
