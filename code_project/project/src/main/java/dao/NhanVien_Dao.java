package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connect.Database;
import entity.NhanVien;

public class NhanVien_Dao {
	public ArrayList<NhanVien> getNhanVien() {
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from nhanVien");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		Integer maNhanVien = rs.getInt(1);
        		String tenNhanVien = rs.getString(2);
        		String loaiNhanVien = rs.getString(3);
        		Boolean gioiTinh= rs.getBoolean(4);
        		Date ngaySinh = rs.getDate(5);
        		String diaChi = rs.getString(6);
          		NhanVien nv= new NhanVien(maNhanVien, tenNhanVien, loaiNhanVien, gioiTinh,ngaySinh , diaChi);
        		ds.add(nv);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public Boolean updateNhanVien(NhanVien nv) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("update nhanVien set tenNhanVien=?,loaiNhanVien=?,gioiTinh=?,ngaySinh=?,diachi=? where  maNhanVien =?");
			stmt.setString(1, nv.getTenNhanVien());
			stmt.setString(2,nv.getLoaiNhanVien());
			stmt.setBoolean(3, nv.getGioiTinh());
			stmt.setDate(4, (java.sql.Date) nv.getNgaySinh());
			stmt.setString(5,nv.getDiaChi());
			stmt.setInt(6,nv.getMaNhanVien());
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
	public Boolean addNhanVien(NhanVien nv) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("insert nhanVien values(?,?,?,?,?,?)");
			stmt.setInt(1,nv.getMaNhanVien());
			stmt.setString(2, nv.getTenNhanVien());
			stmt.setString(3,nv.getLoaiNhanVien());
			stmt.setBoolean(4, nv.getGioiTinh());
			java.sql.Date date=  new java.sql.Date( nv.getNgaySinh().getTime());
			stmt.setDate(5, date);
			stmt.setString(6,nv.getDiaChi());
	
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
	public Boolean deleteNhanVien(Integer maNhanVien) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("delete nhanVien where  maNhanVien =? ");
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
	public NhanVien findNhanVien(Integer maNhanVien) {
		NhanVien nv =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from nhanVien where maNhanVien=?");
			stmt.setInt(1, maNhanVien);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
         	if(rs.next()) {
         		String tenNhanVien = rs.getString(2);
        		String loaiNhanVien = rs.getString(3);
        		Boolean gioiTinh= rs.getBoolean(4);
        		Date ngaySinh = rs.getDate(5);
        		String diaChi = rs.getString(6);
        		nv= new NhanVien(maNhanVien, tenNhanVien, loaiNhanVien, gioiTinh,ngaySinh , diaChi);
         	}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return nv;
	}
}
