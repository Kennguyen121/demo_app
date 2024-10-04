package xuly;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import connect.Database;

public class ThongKe {
	public ArrayList<String> getNameOfColumn(Date start, Date end, Integer maLoai) throws SQLException {
		Database.getInstance();
		Connection con= Database.getConnection();
		 CallableStatement statement = con.prepareCall("{call getThongKeTheoDonHang(?,?,?)}");
		 statement.setDate(1, start);
		 statement.setDate(2, end);
		 if (maLoai != null) {
			    statement.setInt(3, maLoai);
			} else {
			    statement.setNull(3, Types.INTEGER);
			}
		 ResultSet rs = statement.executeQuery();
		 ResultSetMetaData rsmd = rs.getMetaData();
		 ArrayList<String> tenCot= new ArrayList<String>();
		 for(int i=1;i<=rsmd.getColumnCount();i++) {
			 tenCot.add(rsmd.getColumnName(i));
		 }
		 return tenCot;
	}
	public ArrayList<ArrayList<String>> getDataTheoDonHang(Date start, Date end, Integer maLoai) throws SQLException {
		Database.getInstance();
		Connection con= Database.getConnection();
		 CallableStatement statement = con.prepareCall("{call getThongKeTheoDonHang(?,?,?)}");
		 statement.setDate(1, start);
		 statement.setDate(2, end);
		 if (maLoai != null) {
			    statement.setInt(3, maLoai);
			} else {
			    statement.setNull(3, Types.INTEGER);
			}
		 ResultSet rs = statement.executeQuery();
		 ArrayList<ArrayList<String>> parent =  new ArrayList<ArrayList<String>>();
		 while(rs.next()) {
			 ArrayList<String> child =  new ArrayList<String>();
			 Integer maDonHang = rs.getInt(1);  
			 Integer maChiTietDonHang = rs.getInt(2);
			 Integer maNhanVien = rs.getInt(3);
			 Date ngayTaoDon = rs.getDate(4);	 
			 Integer maKhachHang = rs.getInt(5);
			 Integer maSanPham = rs.getInt(6);
			 Integer soLuong = rs.getInt(7);
			 Double khuyenMai = rs.getDouble(8);
			 Double tongTien = rs.getDouble(9);
			 String  moTa = rs.getString(10);
			 
			 child.add(maDonHang.toString());
			 child.add(maChiTietDonHang.toString());
			 child.add(maNhanVien.toString());
			 child.add(ngayTaoDon.toString());
			 child.add(maKhachHang.toString());
			 child.add(maSanPham.toString());
			 child.add(soLuong.toString());
			 child.add(khuyenMai.toString());
			 child.add(tongTien.toString());
			 
			 child.add(moTa);
			 parent.add(child);
		 }
		 return parent;
	}
	public ArrayList<String> getNameOfColumnFollowByProduct(Date start, Date end, Integer maLoai) throws SQLException {
		Database.getInstance();
		Connection con= Database.getConnection();
		 CallableStatement statement = con.prepareCall("{call getThongKeTheoSanPham(?,?,?)}");
		 statement.setDate(1, start);
		 statement.setDate(2, end);
		 if (maLoai != null) {
			    statement.setInt(3, maLoai);
			} else {
			    statement.setNull(3, Types.INTEGER);
			}
		 ResultSet rs = statement.executeQuery();
		 ResultSetMetaData rsmd = rs.getMetaData();
		 ArrayList<String> tenCot= new ArrayList<String>();
		 for(int i=1;i<=rsmd.getColumnCount();i++) {
			 tenCot.add(rsmd.getColumnName(i));
		 }
		 return tenCot;
	}
	public ArrayList<ArrayList<String>> getDataTheoSanPham(Date start, Date end, Integer maLoai) throws SQLException {
		Database.getInstance();
		Connection con= Database.getConnection();
		 CallableStatement statement = con.prepareCall("{call getThongKeTheoSanPham(?,?,?)}");
		 statement.setDate(1, start);
		 statement.setDate(2, end);
		 if (maLoai != null) {
			    statement.setInt(3, maLoai);
			} else {
			    statement.setNull(3, Types.INTEGER);
			}
		 ResultSet rs = statement.executeQuery();
		 ArrayList<ArrayList<String>> parent =  new ArrayList<ArrayList<String>>();
		 while(rs.next()) {
			 ArrayList<String> child =  new ArrayList<String>();
			 Integer maSanPham = rs.getInt(1);  
			 String tenSanPham = rs.getString(2);
			 String anh = rs.getString(3);
			 Integer maLoaiC = rs.getInt(4);
			 Integer soLuongDaBan = rs.getInt(5);
			 Integer soDonHang = rs.getInt(6);
			 Double tongTien = rs.getDouble(7);

			 child.add(maSanPham.toString());
			 child.add(tenSanPham);
			 child.add(anh);
			 child.add(maLoaiC.toString());
			 child.add(soLuongDaBan.toString());
			 child.add(soDonHang.toString());
			 child.add(tongTien.toString());
			 parent.add(child);
		 }
		 return parent;
	}
	public ArrayList<String> getDataTongDoanhThu() throws SQLException {
		Database.getInstance();
		Connection con= Database.getConnection();
		 CallableStatement statement = con.prepareCall("{call getTongDoanhThu()}");
		 
		 ResultSet rs = statement.executeQuery();
		 ArrayList<String> array =  new ArrayList<String>();
		 if(rs.next()) {
			 Double tongDoanhThu = rs.getDouble(1);  
			 Integer soDonHang = rs.getInt(2);
			 Integer soSanPham= rs.getInt(3);
			 String loaiSanPhamBanChayNhat = rs.getString(4);
			 String sanPhamBanChayNhat = rs.getString(5);
			 String nhanVienBanNhieuNhat = rs.getString(6);
			 
			 array.add(tongDoanhThu.toString());
			 array.add(soDonHang.toString());
			 array.add(soSanPham.toString());
			 array.add(loaiSanPhamBanChayNhat);
			 array.add(sanPhamBanChayNhat);
			 array.add(nhanVienBanNhieuNhat);
		 }
		 return array;
	}
	public ArrayList<ArrayList<String>> getDataTheoDonHangDuocSapXep(Date start, Date end, Integer maLoai,Integer isTangDan) throws SQLException {
		Database.getInstance();
		Connection con= Database.getConnection();
		 CallableStatement statement = con.prepareCall("{call getThongKeTheoDonHangDuocSapXep(?,?,?,?)}");
		 statement.setDate(1, start);
		 statement.setDate(2, end);
		 if (maLoai != null) {
			    statement.setInt(3, maLoai);
		} else {
			    statement.setNull(3, Types.INTEGER);
		}
		 statement.setInt(4, isTangDan);
		 ResultSet rs = statement.executeQuery();
		 ArrayList<ArrayList<String>> parent =  new ArrayList<ArrayList<String>>();
		 while(rs.next()) {
			 ArrayList<String> child =  new ArrayList<String>();
			 Integer maDonHang = rs.getInt(1);  
			 Integer maChiTietDonHang = rs.getInt(2);
			 Integer maNhanVien = rs.getInt(3);
			 Date ngayTaoDon = rs.getDate(4);	 
			 Integer maKhachHang = rs.getInt(5);
			 Integer maSanPham = rs.getInt(6);
			 Integer soLuong = rs.getInt(7);
			 Double khuyenMai = rs.getDouble(8);
			 Double tongTien = rs.getDouble(9);
			 String  moTa = rs.getString(10);
			 
			 child.add(maDonHang.toString());
			 child.add(maChiTietDonHang.toString());
			 child.add(maNhanVien.toString());
			 child.add(ngayTaoDon.toString());
			 child.add(maKhachHang.toString());
			 child.add(maSanPham.toString());
			 child.add(soLuong.toString());
			 child.add(khuyenMai.toString());
			 child.add(tongTien.toString());
			 
			 child.add(moTa);
			 parent.add(child);
		 }
		 return parent;
	}
	
}
