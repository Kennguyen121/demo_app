package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.LoaiSanPham;
import entity.SanPham;

public class SanPham_Dao {
	public ArrayList<SanPham> getSanPham() {
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from sanPham");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		Integer maSanPham = rs.getInt(1);
        		String tenSanPham = rs.getString(2);
        		String anh = rs.getString(3);
        		Boolean trangThai = rs.getBoolean(4);
        		Double gia= rs.getDouble(5);
        		String donViTinh= rs.getString(6);
        		Integer maLoai= rs.getInt(7);
        		LoaiSanPham lSP =  new LoaiSanPham_Dao().findLoaiSanPham(maLoai);
          		SanPham sp = new SanPham(maSanPham, tenSanPham, anh, trangThai, gia, donViTinh, lSP);
        		ds.add(sp);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public Boolean updateSanPham(SanPham sp) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("update sanPham set tenSanPham=?,anh=?, trangThai=?,gia= ?,donViTinh=?,maLoai=? where  maSanPham =?");
			stmt.setString(1, sp.getTenSanPham());
			stmt.setString(2,sp.getAnh());
			stmt.setBoolean(3, sp.getTrangThai());
			stmt.setDouble(4, sp.getGia());
			stmt.setString(5,sp.getDonViTinh());
			stmt.setInt(6,sp.getLoaiSanPham().getMaLoai());
			stmt.setInt(7,sp.getMaSanPham());
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
	public Boolean addSanPham(SanPham sp) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("insert sanPham values (?,?,?,?,?,?,?)");
			stmt.setInt(1,sp.getMaSanPham());
			stmt.setString(2, sp.getTenSanPham());
			stmt.setString(3,sp.getAnh());
			stmt.setBoolean(4, sp.getTrangThai());
			stmt.setDouble(5,sp.getGia());
			stmt.setString(6,sp.getDonViTinh());
			stmt.setInt(7,sp.getLoaiSanPham().getMaLoai());
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
	public Boolean deleteSanPham(Integer maSanPham) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("delete sanPham where  maSanPham =? ");
			stmt.setInt(1,maSanPham);
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
	public SanPham findSanPham(Integer maSanPham) {
		SanPham sp =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from sanPham where maSanPham=?");
			stmt.setInt(1, maSanPham);
        	ResultSet rs = stmt.executeQuery();
        	if(rs.next()) {
        		//Duyet tren ket qua tra ve
        		String tenSanPham = rs.getString(2);
        		String anh = rs.getString(3);
        		Boolean trangThai = rs.getBoolean(4);
        		Double gia = rs.getDouble(5);
        		String donViTinh= rs.getString(6);
        		Integer maLoai= rs.getInt(7);
        		LoaiSanPham lSP =  new LoaiSanPham_Dao().findLoaiSanPham(maLoai);
          		 sp = new SanPham(maSanPham, tenSanPham, anh, trangThai, gia, donViTinh, lSP);
        	}
    
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return sp;
	}
	public SanPham findSanPhamByTen(String tenSanPham) {
		SanPham sp = null;
		PreparedStatement stmt = null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("select * from sanPham where tenSanPham=?");
			stmt.setString(1, tenSanPham);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				// Duyet tren ket qua tra ve
				Integer maSanPham = rs.getInt(1);
				String anh = rs.getString(3);
				Boolean trangThai = rs.getBoolean(4);
				Double gia = rs.getDouble(5);
				String donViTinh = rs.getString(6);
				Integer maLoai = rs.getInt(7);
				LoaiSanPham lSP = new LoaiSanPham_Dao().findLoaiSanPham(maLoai);
				sp = new SanPham(maSanPham, tenSanPham, anh, trangThai, gia, donViTinh, lSP);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}

	public ArrayList<SanPham> getSanPhamByMaLoai(Integer maLoai) {
		ArrayList<SanPham> ds = new ArrayList<SanPham>();
		PreparedStatement stmt = null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("select * from sanPham where maLoai=?");
			stmt.setInt(1, maLoai);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Integer maSanPham = rs.getInt(1);
				String tenSanPham = rs.getString(2);
				String anh = rs.getString(3);
				Boolean trangThai = rs.getBoolean(4);
				Double gia = rs.getDouble(5);
				String donViTinh = rs.getString(6);
				LoaiSanPham lSP = new LoaiSanPham_Dao().findLoaiSanPham(maLoai);
				SanPham sp = new SanPham(maSanPham, tenSanPham, 
						anh, trangThai, gia, donViTinh, lSP);
				ds.add(sp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
