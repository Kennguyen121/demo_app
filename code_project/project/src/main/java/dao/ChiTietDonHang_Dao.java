package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.Database;
import entity.ChiTietDonHang;
import entity.DonHang;
import entity.SanPham;

public class ChiTietDonHang_Dao {
	public ArrayList<ChiTietDonHang> getChiTietDonHang() {
		ArrayList<ChiTietDonHang> ds = new ArrayList<ChiTietDonHang>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from chiTietDonHang");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
	    		Integer maChiTietDonHang = rs.getInt(1);
        		Integer maDonHang = rs.getInt(2);
        		Integer maSanPham = rs.getInt(3);
        		Integer soLuong = rs.getInt(4);
        		Double khuyenMai = rs.getDouble(5);
        		Double tongTien= rs.getDouble(6);
        		String moTa= rs.getString(7);
        		DonHang dh =  new  DonHang_Dao().findDonHang(maDonHang);
        		SanPham sp =  new SanPham_Dao().findSanPham(maSanPham);
 
        		ChiTietDonHang ctdh= new ChiTietDonHang(maChiTietDonHang, dh, sp, soLuong, khuyenMai, tongTien, moTa);
        		ds.add(ctdh);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public Boolean updateChiTietDonHang(ChiTietDonHang ctdh) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("update chiTietDonHang set maDonHang=?,maSanPham=?, soLuong=?,khuyenMai=?,tongTien=?,moTa=? where  maChiTietDonHang =?");
			stmt.setInt(1, ctdh.getDonHang().getMaDonHang());
			stmt.setInt(2, ctdh.getSanPham().getMaSanPham());
			stmt.setInt(3, ctdh.getSoLuong());
			stmt.setDouble(4, ctdh.getKhuyenMai());
			stmt.setDouble(5, ctdh.getTongTien());
			stmt.setString(6, ctdh.getMoTa());
			stmt.setInt(7, ctdh.getMaChiTietDonHang());
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
	public Boolean addChiTietDonHang(ChiTietDonHang ctdh) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("insert chiTietDonHang values(?,?,?,?,?,?,?)");
			stmt.setInt(1, ctdh.getMaChiTietDonHang());
			stmt.setInt(2, ctdh.getDonHang().getMaDonHang());
			stmt.setInt(3, ctdh.getSanPham().getMaSanPham());
			stmt.setInt(4, ctdh.getSoLuong());
			stmt.setDouble(5, ctdh.getKhuyenMai());
			stmt.setDouble(6, ctdh.getTongTien());
			stmt.setString(7, ctdh.getMoTa());
			
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
	public Boolean deleteDonHang(Integer maChiTietDonHang) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("delete chiTietDonHang where  maChiTietDonHang =? ");
			stmt.setInt(1,maChiTietDonHang);
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
	public ChiTietDonHang findDonHang(Integer maChiTietDonHang) {
		ChiTietDonHang ctdh =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from chiTietDonHang where maChiTietDonHang=?");
			stmt.setInt(1, maChiTietDonHang);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	if(rs.next()) {
        		Integer maDonHang = rs.getInt(2);
        		Integer maSanPham = rs.getInt(3);
        		Integer soLuong = rs.getInt(4);
        		Double khuyenMai = rs.getDouble(5);
        		Double tongTien= rs.getDouble(6);
        		String moTa= rs.getString(7);
        		
        		DonHang dh =  new  DonHang_Dao().findDonHang(maDonHang);
        		SanPham sp =  new SanPham_Dao().findSanPham(maSanPham);

        		ctdh= new ChiTietDonHang(maChiTietDonHang, dh, sp, soLuong, khuyenMai, tongTien, moTa);
        	}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return ctdh;
	}
	public ArrayList<ChiTietDonHang> findDonHangTheoMaDonHang(Integer maDonHang){
		ArrayList< ChiTietDonHang> dsChiTietDonHang =new ArrayList<ChiTietDonHang>();
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("select * from chiTietDonHang where maDonHang=?");
			stmt.setInt(1, maDonHang);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
        	while(rs.next()) {
        		Integer maChiTietDonHang = rs.getInt(1);
        		Integer maSanPham = rs.getInt(3);
        		Integer soLuong = rs.getInt(4);
        		Double khuyenMai = rs.getDouble(5);
        		Double tongTien= rs.getDouble(6);
        		String moTa= rs.getString(7);
        		
        		DonHang dh =  new  DonHang_Dao().findDonHang(maDonHang);
        		SanPham sp =  new SanPham_Dao().findSanPham(maSanPham);

        		ChiTietDonHang ctdh= new ChiTietDonHang(maChiTietDonHang, dh, sp, soLuong, khuyenMai, tongTien, moTa);
        		dsChiTietDonHang.add(ctdh);
        	}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return dsChiTietDonHang;
	}
}
