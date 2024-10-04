package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;

import entity.KhachHang;

public class KhachHang_Dao {
	public ArrayList<KhachHang> getKhachHang() {
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from khachHang");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		Integer maKhachHang = rs.getInt(1);
        		String tenKhachHang = rs.getString(2);
        		String soDienThoai = rs.getString(3);
        		KhachHang kh= new KhachHang(maKhachHang, tenKhachHang, soDienThoai);
        		ds.add(kh);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public Boolean updateKhachHang(KhachHang kh) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("update khachHang set tenKhachHang =?,soDienThoai=? where maKhachHang =?");
			stmt.setString(1, kh.getTenKhachHang());
			stmt.setString(2, kh.getSoDienThoai());
			stmt.setInt(3, kh.getMaKhachHang());
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
	public Boolean addKhachHang(KhachHang kh) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("insert khachHang values(?,?,?)");
			stmt.setInt(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getTenKhachHang());
			stmt.setString(3, kh.getSoDienThoai());

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
	public Boolean deleteKhachHang(Integer maKhachHang) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("delete khachHang where maKhachHang= ? ");
			stmt.setInt(1,maKhachHang);
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
	public KhachHang findKhachHang(Integer maKhachHang) {
		KhachHang kh =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from khachHang where maKhachHang=?");
			stmt.setInt(1, maKhachHang);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	if(rs.next()) {
        		String tenKhachHang = rs.getString(2);
        		String soDienThoai = rs.getString(3);
        		kh= new KhachHang(maKhachHang, tenKhachHang, soDienThoai);
        	}
        	
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return kh;
	}
	public KhachHang findKhachHang(String sdt) {
		KhachHang kh =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from khachHang where soDienThoai=?");
			stmt.setString(1, sdt);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	if(rs.next()) {
        		Integer maKhachHang = rs.getInt(1);
        		String tenKhachHang = rs.getString(2);
        		kh= new KhachHang(maKhachHang, tenKhachHang, sdt);
        	}
        	
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return kh;
	}
}
