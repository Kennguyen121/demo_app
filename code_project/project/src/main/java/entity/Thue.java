package entity;

import java.util.Objects;

public class Thue {
	private String tenThue;
	private double giaTriThue;
	
	// Constructor
	public Thue(String tenThue, double giaTriThue) {
		super();
		this.tenThue = tenThue;
		this.giaTriThue = giaTriThue;
	}

	public Thue() {
		super();
	}

	// Getters and Setters
	public String getTenThue() {
		return tenThue;
	}

	public double getGiaTriThue() {
		return giaTriThue;
	}

	public void setGiaTriThue(double giaTriThue) {
		this.giaTriThue = giaTriThue;
	}
	
	// toString
	@Override
	public String toString() {
		return "Thue [tenThue=" + tenThue + ", giaTriThue=" + giaTriThue + "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(tenThue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Thue other = (Thue) obj;
		return Objects.equals(tenThue, other.tenThue);
	}
	
	
}
