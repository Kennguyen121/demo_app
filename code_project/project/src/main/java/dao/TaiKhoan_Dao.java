package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import connect.Database;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoan_Dao {
	public ArrayList<TaiKhoan> getDSTaiKhoan() {
		ArrayList<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		try {
			Database.getInstance();
			Connection con= Database.getConnection();
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery("SELECT * FROM TaiKhoan");
			while(rs.next()) {
        		Integer maNhanVien = rs.getInt(1);
        		String tenDangNhap = rs.getString(2);
        		String matKhau = rs.getString(3);
        		NhanVien nv =  new NhanVien_Dao().timNhanVien(maNhanVien);
          		TaiKhoan tk= new TaiKhoan(nv, tenDangNhap, matKhau);
        		ds.add(tk);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;
	}
	public boolean capNhatTK(TaiKhoan tk) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("UPDATE TaiKhoan "
										+ "SET tenDangNhap = ?, "
										+ "	MatKhau = ? "
										+ "WHERE MaNV = ?");
			stmt.setString(1, tk.getTenDangNhap());
			stmt.setString(2,tk.getMatKhau());
			stmt.setInt(3,tk.getNhanVien().getMaNV());
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
	public boolean themTK(TaiKhoan tk) {
		int n =0;
		PreparedStatement stmt=null;
		try {
			Database.getInstance();
			Connection con = Database.getConnection();
			stmt = con.prepareStatement("INSERT INTO TaiKhoan (MaNV, TenDangNhap, MatKhau) VALUES (?, ?, ?)");
			stmt.setInt(1,tk.getNhanVien().getMaNV());
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
	
	public TaiKhoan timTK(int maNhanVien) {
		TaiKhoan tk = null;
		PreparedStatement stmt = null;
		try {
        	Database.getInstance();
        	Connection con= Database.getConnection(); 
        	stmt = con.prepareStatement("SELECT * FROM TaiKhoan WHERE MaNV=?");
			stmt.setInt(1, maNhanVien);
        	ResultSet rs = stmt.executeQuery();
        	if(rs.next()) {
        		String tenDangNhap = rs.getString(2);
        		String matKhau = rs.getString(3);
        		NhanVien nv =  new NhanVien_Dao().timNhanVien(maNhanVien);
        		tk= new TaiKhoan(nv, tenDangNhap, matKhau);
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return tk;
	}

	public TaiKhoan dangNhap(String tenDangNhap, String matKhau) {
		NhanVien_Dao nvDao = new NhanVien_Dao();
		TaiKhoan tk = null;
        PreparedStatement stmt = null;
        try {
            Database.getInstance();
            Connection con = Database.getConnection();
            stmt = con.prepareStatement("SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?");
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int maNhanVien = rs.getInt(1);;
                String tenDN = rs.getString(2);
                String mk = rs.getString(3);
                NhanVien nv = nvDao.timNhanVien(maNhanVien);
                tk = new TaiKhoan(nv, tenDN, mk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tk;
    }
    public static void main(String[] args) {
        TaiKhoan_Dao tkDao = new TaiKhoan_Dao();
        NhanVien_Dao nvDao = new NhanVien_Dao();
        Scanner scanner = new Scanner(System.in);

//        // Thêm tài khoản mới
//        System.out.println("==== Thêm Tài Khoản ====");
//        System.out.print("Nhập mã nhân viên: ");
//        int maNV = scanner.nextInt();
//        scanner.nextLine(); 
//        NhanVien nv = nvDao.timNhanVien(maNV);
//        if (nv != null) {
//            System.out.print("Nhập tên đăng nhập: ");
//            String tenDangNhap = scanner.nextLine();
//            System.out.print("Nhập mật khẩu: ");
//            String matKhau = scanner.nextLine();
//
//            TaiKhoan tk = new TaiKhoan(nv, tenDangNhap, matKhau);
//            boolean themThanhCong = tkDao.themTK(tk);
//            if (themThanhCong) {
//                System.out.println("Thêm tài khoản thành công!");
//            } else {
//                System.out.println("Thêm tài khoản thất bại!");
//            }
//        } else {
//            System.out.println("Không tìm thấy nhân viên!");
//        }

        // Lấy danh sách tài khoản
        System.out.println("\n==== Danh Sách Tài Khoản ====");
        ArrayList<TaiKhoan> dsTaiKhoan = tkDao.getDSTaiKhoan();
        for (TaiKhoan tk : dsTaiKhoan) {
            System.out.println("Nhân viên: " + tk.getNhanVien().getHoTen() + 
                               ", Tên đăng nhập: " + tk.getTenDangNhap());
        }

        // Kiểm tra đăng nhập
        System.out.println("\n==== Đăng Nhập ====");
        System.out.print("Nhập tên đăng nhập: ");
        String tenDN = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String mk = scanner.nextLine();
        TaiKhoan tk = tkDao.dangNhap(tenDN, mk);
        if (tk != null) {
            System.out.println("Đăng nhập thành công! Xin chào, " + tk.getNhanVien().getHoTen());
        } else {
            System.out.println("Đăng nhập thất bại! Vui lòng kiểm tra lại.");
        }

        scanner.close();
    }
	
}
