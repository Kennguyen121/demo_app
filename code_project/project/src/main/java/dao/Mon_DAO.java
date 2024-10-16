package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.LoaiMon;
import entity.Mon;

public class Mon_DAO {
	public ArrayList<Mon> getMon() {
		ArrayList<Mon> dsMon = new ArrayList<Mon>();
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from Mon");
			while(rs.next()) {
				Integer maMon = rs.getInt(1);
				String tenMon = rs.getString(2);
				Double donGia = rs.getDouble(3);
				Integer soLuong = rs.getInt(4);
				Integer maLoai = rs.getInt(5);
				LoaiMon lMon = new LoaiMon_DAO().findLoaiMonTheoMa(maLoai);
				Mon mon = new Mon(maMon, tenMon, donGia, soLuong, lMon);
				dsMon.add(mon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsMon;
	}

	public Boolean addMon(Mon mon) {
		Database.getInstance();
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert Mon values(?, ?, ?, ?, ?");
			stmt.setInt(1, mon.getMaMon());
			stmt.setString(2, mon.getTenMon());
			stmt.setDouble(3, mon.getDonGia());
			stmt.setInt(4, mon.getSoLuong());
			stmt.setInt(5, mon.getLoaiMon().getMaLoai());
			
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
	
	
}
