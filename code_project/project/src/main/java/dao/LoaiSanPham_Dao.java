package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.LoaiSanPham;

public class LoaiSanPham_Dao {
	public ArrayList<LoaiSanPham> getLoaiSanPham() {
		ArrayList<LoaiSanPham> ds = new ArrayList<LoaiSanPham>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from loaiSanPham");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		Integer maLoai = rs.getInt(1);
        		String tenLoai = rs.getString(2);
        		LoaiSanPham lSP= new LoaiSanPham(maLoai, tenLoai);
        		ds.add(lSP);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public Boolean updateLoaiSanPham(LoaiSanPham lSP) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("update loaiSanPham set tenLoai=? where  maLoai =?");
			stmt.setString(1, lSP.getTenLoai());
			stmt.setInt(2,lSP.getMaLoai());
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
	public Boolean addLoaiSanPham(LoaiSanPham lSP) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("insert loaiSanPham values(?,?)");
			stmt.setInt(1,lSP.getMaLoai());
			stmt.setString(2, lSP.getTenLoai());
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
	public Boolean deleteLoaiSanPham(Integer maLoai) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("delete loaiSanPham where  maLoai =? ");
			stmt.setInt(1,maLoai);
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
	public LoaiSanPham findLoaiSanPham(Integer maLoai) {
		LoaiSanPham lSP =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from loaiSanPham where maLoai= ?");
			stmt.setInt(1, maLoai);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
            if (rs.next()) {
                // Retrieve data only if there is a current row
                String tenLoai = rs.getString("tenLoai");
                lSP = new LoaiSanPham(maLoai, tenLoai);
            }
    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lSP;
	}
	public LoaiSanPham findLoaiSanPhamTheoTen(String tenLoai) {
		LoaiSanPham lSP =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from loaiSanPham where tenLoai= ?");
			stmt.setString(1, tenLoai);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
            if (rs.next()) {
                // Retrieve data only if there is a current row
                Integer maLoai = rs.getInt(1);
                lSP = new LoaiSanPham(maLoai, tenLoai);
            }
    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lSP;
	}

}
