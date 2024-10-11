package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import connect.Database;
import entity.NhanVien;

public class NhanVien_Dao {
	//lay danh sach nhan vien
	public ArrayList<NhanVien> getNhanVien() {
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("select * from nhanVien");
			while(rs.next()) {//Di chuyen con tro xuong ban ghi ke tiep
        		int maNhanVien = rs.getInt(1);
        		String tenNhanVien = rs.getString(2);
        		String sdt = rs.getString(3);
        		String email = rs.getString(4);
        		String chucVu = rs.getString(5);
        		boolean trangThai = rs.getBoolean(6);
        		//thêm nhân viên thì chắc là bắt đầu làm việc nên cứ để mặc định là đang làm việc
          		NhanVien nv= new NhanVien(maNhanVien, tenNhanVien, sdt, email, chucVu, trangThai);
        		ds.add(nv);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	// đã xong
	public boolean capNhatNV(NhanVien nv) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("UPDATE nhanVien "
										+ "    SET HoTen = ?, "
										+ "    SoDienThoai = ?, "
										+ "    Email = ?, "
										+ "    ChucVu = ?, "
										+ "    TrangThaiLamViec = ? "
										+ "    WHERE MaNV = ?");			
			stmt.setString(1, nv.getHoTen());
			stmt.setString(2,nv.getSoDienThoai());
			stmt.setString(3,nv.getEmail());
			stmt.setString(4,nv.getChucVu());
			stmt.setBoolean(5,nv.isTrangThaiLamViec());
			stmt.setInt(6,nv.getMaNV());
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
	// đã xong
	public boolean themNhanVien(NhanVien nv) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			// mặc định khi thêm nhân viên mới là cho đang làm việc 
			stmt = con.prepareStatement("INSERT INTO NhanVien (HoTen, SoDienThoai, Email, ChucVu, TrangThaiLamViec) VALUES(?,?,?,?, 1)");
			stmt.setString(1, nv.getHoTen());
			stmt.setString(2,nv.getSoDienThoai());
			stmt.setString(3,nv.getEmail());
			stmt.setString(4,nv.getChucVu());
			//java.sql.Date date=  new java.sql.Date( nv.getNgaySinh().getTime());
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
	
	public NhanVien timNhanVien(int maNhanVien) {
		NhanVien nv =null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("SELECT * FROM NhanVien WHERE MaNV=?");
			stmt.setInt(1, maNhanVien);
        	ResultSet rs = stmt.executeQuery();
        	//Duyet tren ket qua tra ve
         	if(rs.next()) {
         		String hoTen = rs.getString(2);
        		String sdt = rs.getString(3);
        		String email = rs.getString(4);
        		String chucVu = rs.getString(5);
        		boolean trangThaiLamViec = rs.getBoolean(6);
        		nv= new NhanVien(maNhanVien, hoTen, sdt, email, chucVu, trangThaiLamViec);
         	}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return nv;
	}
	public static void main(String[] args) {
        NhanVien_Dao nhanVienDao = new NhanVien_Dao();
        Scanner scanner = new Scanner(System.in);
//        NhanVien nv1 = new NhanVien("Nguyen Van A", "0123456789", "nguyenvana@gmail.com", "Quản lý");
//        NhanVien nv2 = new NhanVien("Tran Thi B", "0987654321", "tranthib@gmail.com", "Nhân viên");
//        
//        System.out.println("Thêm nhân viên:");
//        boolean isAdded1 = nhanVienDao.themNhanVien(nv1);
//        boolean isAdded2 = nhanVienDao.themNhanVien(nv2);
//        System.out.println("Thêm nv1: " + (isAdded1 ? "Thành công" : "Thất bại"));
//        System.out.println("Thêm nv2: " + (isAdded2 ? "Thành công" : "Thất bại"));
        
        // Lấy danh sách nhân viên
        
//        System.out.println("Cập nhật nhân viên:");
//        NhanVien nv1 = new NhanVien(1, "Nguyen Van A", "0123456789", "nguyenvana@gmail.com", "Quản lý", true);
//        boolean isUpdated = nhanVienDao.capNhatNV(nv1);
        
        System.out.println("\nDanh sách nhân viên:");
        ArrayList<NhanVien> danhSachNV = nhanVienDao.getNhanVien();
        for (NhanVien nv : danhSachNV) {
            System.out.println(nv.getMaNV() + " - " + nv.getHoTen() + " - " + nv.getSoDienThoai() + " - " + nv.getEmail() + " - " + nv.getChucVu());
        }
        
    }
}
