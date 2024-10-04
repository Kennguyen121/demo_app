package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.Ban;
import entity.DonHang;
import entity.KhachHang;
import entity.NhanVien;

public class DonHang_Dao {
	public ArrayList<DonHang> getDonHang() {
		ArrayList<DonHang> ds = new ArrayList<DonHang>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from donHang");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		Integer maDonHang = rs.getInt(1);
        		Integer maNhanVien = rs.getInt(2);
        		Integer maKhachHang = rs.getInt(3);
        		Integer maBan = rs.getInt(4);
        		
        		Date ngayTaoDon = rs.getDate(5);
        		Double tongTien= rs.getDouble(6);
        
        		NhanVien nv =  new NhanVien_Dao().findNhanVien(maNhanVien);
        		KhachHang kh =  new KhachHang_Dao().findKhachHang(maKhachHang);
        		Ban ban= new Ban_Dao().findBan(maBan);
        		
          		DonHang dh= new DonHang(maDonHang, nv, kh, ban, ngayTaoDon, tongTien);
        		ds.add(dh);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public Boolean updateDonHang(DonHang dh) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("update donHang set maNhanVien=?,maKhachHang=?, maBan=?,ngayTaoDon=?,tongTien=? where  maDonHang =?");
			stmt.setInt(1, dh.getNhanVien().getMaNhanVien());
			stmt.setInt(2, dh.getKhachHang().getMaKhachHang());
			stmt.setInt(3, dh.getBan().getMaBan());
			stmt.setDate(4, dh.getNgayTaoDon());
			stmt.setDouble(5, dh.getTongTien());
			stmt.setInt(6, dh.getMaDonHang());
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
	public Boolean addDonHang(DonHang dh) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("insert donHang values(?,?,?,?,?,?)");
			stmt.setInt(1,dh.getMaDonHang());
			stmt.setInt(2, dh.getNhanVien().getMaNhanVien());
			stmt.setInt(3, dh.getKhachHang().getMaKhachHang());
			stmt.setInt(4, dh.getBan().getMaBan());
			stmt.setDate(5, dh.getNgayTaoDon());
			stmt.setDouble(6, dh.getTongTien());
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
	public Boolean deleteDonHang(Integer maDonHang) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("delete donHang where  maDonHang =? ");
			stmt.setInt(1,maDonHang);
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
	public DonHang findDonHang(Integer maDonHang) {
		DonHang dh =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from donHang where maDonHang=?");
			stmt.setInt(1, maDonHang);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	if(rs.next()) {
        		Integer maNhanVien = rs.getInt(2);
        		Integer maKhachHang = rs.getInt(3);
        		Integer maBan = rs.getInt(4);
        		
        		Date ngayTaoDon = rs.getDate(5);
        		Double tongTien= rs.getDouble(6);
        
        		NhanVien nv =  new NhanVien_Dao().findNhanVien(maNhanVien);
        		KhachHang kh =  new KhachHang_Dao().findKhachHang(maKhachHang);
        		Ban ban= new Ban_Dao().findBan(maBan);
        		
          		dh= new DonHang(maDonHang, nv, kh, ban, ngayTaoDon, tongTien);
        	}
        	
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return dh;
	}
}
