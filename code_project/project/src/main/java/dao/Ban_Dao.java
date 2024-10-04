package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.Ban;
import entity.KhuVuc;

public class Ban_Dao{
	public ArrayList<Ban> getBan() {
		ArrayList<Ban> dsBan = new ArrayList<Ban>();
        try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	String sql = "select * from Ban";
        	Statement statement = con.createStatement();
        	ResultSet rs = statement.executeQuery(sql);
        	//Duyet tren ket qua tra ve
        	while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		int maBan = rs.getInt(1);
        		int soChoNgoi = rs.getInt(2);
        		int maKhuVuc = rs.getInt(3);
        		KhuVuc khuVuc = new KhuVuc_Dao().findKhuVuc(maKhuVuc);
        		String tinhTrang = rs.getString(4);
        		Ban p = new Ban(maBan, soChoNgoi, maKhuVuc, tinhTrang);
        		dsBan.add(p);
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return dsBan;
	}
	public Boolean updateBan(Ban ban) {
		Database.getInstance();
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("update ban set trangThai =? where maBan =?");
			stmt.setBoolean(1, ban.getTrangThai());
			stmt.setInt(2, ban.getMaBan());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
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
	public Boolean addBan(Ban ban) {
		Database.getInstance();
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("insert ban values(?,?)");
			stmt.setInt(1, ban.getMaBan());
			stmt.setBoolean(2, ban.getTrangThai());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
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
	public Boolean deleteBan(Integer maBan) {
		Database.getInstance();
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n =0;
		try {
			stmt = con.prepareStatement("delete ban where maBan =? ");
			stmt.setInt(1, maBan);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
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
	public Ban	findBan(Integer maBan) {
		Ban ban =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from ban where maBan=?");
			stmt.setInt(1, maBan);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	if(rs.next()) {
        	   	Boolean trangThai = rs.getBoolean(2);
            	ban = new Ban(maBan, trangThai);	
        	}
     
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return ban;
	}

}
