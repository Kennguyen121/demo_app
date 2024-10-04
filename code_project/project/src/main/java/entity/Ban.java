package entity;

import java.util.Objects;

public class Ban {
	private int maBan;
	private int soChoNgoi;
	private KhuVuc khuVuc;
	private int tinhTrang;
	
	// Constructor
	public Ban(int maBan, int soGhe, KhuVuc khuVuc, int tinhTrang) {
		super();
		this.maBan = maBan;
		this.soChoNgoi = soGhe;
		this.khuVuc = khuVuc;
		this.tinhTrang = tinhTrang;
	}
	
	public Ban() {
		super();
	}

	// Getters and Setters
	public int getSoGhe() {
		return soChoNgoi;
	}

	public void setSoGhe(int soGhe) {
		this.soChoNgoi = soGhe;
	}

	public KhuVuc getKhuVuc() {
		return khuVuc;
	}

	public void setKhuVuc(KhuVuc khuVuc) {
		this.khuVuc = khuVuc;
	}

	public int isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public int getMaBan() {
		return maBan;
	}

	// toString
	@Override
	public String toString() {
		return "Ban [maBan=" + maBan + ", soGhe=" + soChoNgoi + ", khuVuc=" + khuVuc + ", tinhTrang=" + tinhTrang + "]";
	}

	// HashCode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(maBan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Ban other = (Ban) obj;
		return maBan == other.maBan;
	}
	
	
}
