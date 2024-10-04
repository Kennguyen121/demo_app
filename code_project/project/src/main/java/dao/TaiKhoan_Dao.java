package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_Dao {
	public ArrayList<TaiKhoan> getTaiKhoan() {
		ArrayList<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from taiKhoan");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		Integer maNhanVien = rs.getInt(1);
        		String tenDangNhap = rs.getString(2);
        		String matKhau = rs.getString(3);
        		NhanVien nv =  new NhanVien_Dao().findNhanVien(maNhanVien);
          		TaiKhoan tk= new TaiKhoan(nv, tenDangNhap, matKhau);
        		ds.add(tk);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public Boolean updateTaiKhoan(TaiKhoan tk) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("update taiKhoan set tenDangNhap=?,matKhau=? where  maNhanVien =?");
			stmt.setString(1, tk.getTenDangNhap());
			stmt.setString(2,tk.getMatKhau());
			stmt.setInt(3,tk.getNhanVien().getMaNhanVien());
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
	public Boolean addTaiKhoan(TaiKhoan tk) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("insert taiKhoan values(?,?,?)");
			stmt.setInt(1,tk.getNhanVien().getMaNhanVien());
			stmt.setString(2, tk.getTenDangNhap());
			stmt.setString(3,tk.getMatKhau());
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
	public Boolean deleteTaiKhoan(Integer maNhanVien) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("delete taiKhoan where  maNhanVien =? ");
			stmt.setInt(1,maNhanVien);
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
	public TaiKhoan findTaiKhoan(Integer maNhanVien) {
		TaiKhoan tk =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from taiKhoan where maNhanVien=?");
			stmt.setInt(1, maNhanVien);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	if(rs.next()) {
        		String tenDangNhap = rs.getString(2);
        		String matKhau = rs.getString(3);
        		NhanVien nv =  new NhanVien_Dao().findNhanVien(maNhanVien);
        		tk= new TaiKhoan(nv, tenDangNhap, matKhau);
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return tk;
	}
	public TaiKhoan findTaiKhoanTheoTenDN(String tenDangNhap) {
		TaiKhoan tk =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from taiKhoan where tenDangNhap=?");
			stmt.setString(1, tenDangNhap);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	if(rs.next()) {
        		Integer maNhanVien = rs.getInt(1);
        		String matKhau = rs.getString(3);
        		NhanVien nv =  new NhanVien_Dao().findNhanVien(maNhanVien);
        		tk= new TaiKhoan(nv, tenDangNhap, matKhau);
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return tk;
	}
	
}
