package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.LoaiMon;

public class LoaiMon_DAO {
	//Get danh sach loai mon
	public ArrayList<LoaiMon> getLoaiMon() {
		ArrayList<LoaiMon> dsLoaiMon = new ArrayList<LoaiMon>();
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from LoaiMon");
			while(rs.next()) {
				Integer maLoai = rs.getInt(1);
				String tenLoai = rs.getString(2);
				LoaiMon loaiMon = new LoaiMon(maLoai, tenLoai);
				dsLoaiMon.add(loaiMon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiMon;
	}
	//Cap nhat ten cua loai mon
	public Boolean updateTenLoaiMon(int MaLoai) {
		Database.getInstance();
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LoaiMon set tenLoai = ? where MaLoai = ?");
			stmt.setString(1, "Ten Loai");
			stmt.setInt(2, MaLoai);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	//Them loai mon moi
	public Boolean addLoaiMon(LoaiMon loaiMon) {
		Database.getInstance();
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert LoaiMon values(?, ?)");
			stmt.setInt(1, loaiMon.getMaLoai());
			stmt.setString(2, loaiMon.getTenLoai());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	//Xoa loai mon
	public Boolean deleteLoaiMon(int MaLoai) {
		Database.getInstance();
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete LoaiMon where MaLoai = ?");
			stmt.setInt(1, MaLoai);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	//Tim loai mon theo ma
	public LoaiMon findLoaiMonTheoMa(int MaLoai) {
		LoaiMon loaiMon = null;
		PreparedStatement stmt = null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("select * from LoaiMon where LoaiMon = ?");
			stmt.setInt(1, MaLoai);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				String tenLoai = rs.getString(2);
                loaiMon = new LoaiMon(MaLoai, tenLoai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loaiMon;
	}
	//Tim loai mon theo ten
	public LoaiMon findLoaiMonTheoTen(String tenLoai) {
		LoaiMon loaiMon = null;
		PreparedStatement stmt = null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("select * from LoaiMon where tenLoai = ?");
			stmt.setString(1, tenLoai);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Integer maLoai = rs.getInt(1);
                loaiMon = new LoaiMon(maLoai, tenLoai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loaiMon;
	}
}
