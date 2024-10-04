package entity;

import java.util.Objects;

public class KhuVuc {
	private int maKhuVuc;
	private String tenKhuVuc;
	
	// Constructor
	public KhuVuc(int maKhuVuc, String tenKhuVuc) {
		super();
		this.maKhuVuc = maKhuVuc;
		this.tenKhuVuc = tenKhuVuc;
	}

	public KhuVuc() {
		super();
	}
	
	// Getters and Setters
	public String getTenKhuVuc() {
		return tenKhuVuc;
	}

	public void setTenKhuVuc(String tenKhuVuc) {
		this.tenKhuVuc = tenKhuVuc;
	}

	public int getMaKhuVuc() {
		return maKhuVuc;
	}

	// toString
	@Override
	public String toString() {
		return "KhuVuc [maKhuVuc=" + maKhuVuc + ", tenKhuVuc=" + tenKhuVuc + "]";
	}
	
	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maKhuVuc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		KhuVuc other = (KhuVuc) obj;
		return maKhuVuc == other.maKhuVuc;
	}
	
	
}
