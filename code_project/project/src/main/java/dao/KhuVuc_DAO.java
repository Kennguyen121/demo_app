package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.KhuVuc;

public class KhuVuc_DAO {
	public ArrayList<KhuVuc> getKhuVuc() {
		ArrayList<KhuVuc> dsKhuVuc = new ArrayList<KhuVuc>();
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			String sql = "select * from KhuVuc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maKhuVuc = rs.getInt(1);
				String tenKhuVuc = rs.getString(2);
				KhuVuc kv = new KhuVuc(maKhuVuc, tenKhuVuc);
				dsKhuVuc.add(kv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhuVuc;
	}
	
	
}
