create database QLCoffeShop
go
use QLCoffeShop
go
--phần sản phẩm
CREATE table loaiSanPham(
	maLoai int Primary key not null,
	tenLoai Nvarchar(20)
)
go
CREATE table sanPham(
	maSanPham int Primary key not null,
	tenSanPham Nvarchar(50),
	anh Nvarchar(100),
	trangThai TINYINT not null,--0 là còn hết hàng, 1 là còn hàng
	gia Money Constraint soDuongGia Check (gia >0),
	donViTinh nvarchar(20),
	maLoai int FOREIGN KEY REFERENCES loaiSanPham(maLoai),
)
go
--phần nhân viên
CREATE table nhanVien(
	maNhanVien int Primary key not null,
	tenNhanVien Nvarchar(50),
	loaiNhanVien Nvarchar(20) not null,--nhân viên thường hay quản lý
	gioiTinh TINYINT not null,-- 0 là nữ, 1 là nam
	ngaySinh Date CONSTRAINT duTuoi CHECK ( Year(getDate())-Year(ngaySinh) >=18),
	diachi  Nvarchar(50),
)

CREATE table taiKhoan(
	maNhanVien int primary key FOREIGN KEY REFERENCES nhanVien(maNhanVien),
	tenDangNhap Nvarchar(50) not null,
	matKhau Nvarchar(50) not null,
)

--phần khách hàng
CREATE table khachHang(
	maKhachHang int Primary key not null,
	tenKhachHang Nvarchar(50),
	soDienThoai Nvarchar(20)

)
--phần bàn
CREATE table ban(
	maBan int Primary key not null,
	trangThai TINYINT not null,-- 0 là còn trống, 1 là đã đặt
)
--phần hóa đơn
CREATE table donHang(
	maDonHang int Primary key not null,
	maNhanVien int FOREIGN KEY REFERENCES nhanVien(maNhanVien),
	maKhachHang int FOREIGN KEY REFERENCES khachHang(maKhachHang),
	maBan int FOREIGN KEY REFERENCES ban(maBan),
	ngayTaoDon Date,
	tongTien money,--tính bằng tổng tiền của chi tiết đơn hàng
)
CREATE table chiTietDonHang(
	maChiTietDonHang int Primary key not null,
	maDonHang int FOREIGN KEY REFERENCES donHang(maDonHang),
	maSanPham int FOREIGN KEY REFERENCES sanPham(maSanPham),
	soLuong int,
	khuyenMai FLOAT Constraint check_KM CHECK (khuyenMai >=0 AND  khuyenMai <=10),-- tính theo %, thang đo từ 0-10 tức là 10= 100%
	tongTien money,
	moTa NvarChar(200)--mô tả về đơn hàng
)

/*

 -1 nhân viên sẽ có  1 tài khoản. 

  -1 sản phẩm có nhiều đơn nhập hàng, 
 -1 đơn hàng có nhiều chi tiết đơn hàng
  -1 sản phẩm có nhiều chi tiết đơn hàng
 -1 loại sản phẩm có nhiều sản phẩm
 1 đơn hàng có 1 bàn
 1 đơn hàng có 1 khách hàng tại 1 thời điểm
*/
-- dữ liệu của bàn---------------
insert [dbo].[ban] values (1,0)
insert [dbo].[ban] values (2,0)
insert [dbo].[ban] values (3,0)
insert [dbo].[ban] values (4,0)
insert [dbo].[ban] values (5,0)
insert [dbo].[ban] values (6,0)
insert [dbo].[ban] values (7,0)
insert [dbo].[ban] values (8,0)
insert [dbo].[ban] values (9,0)
insert [dbo].[ban] values (10,0)
insert [dbo].[ban] values (11,0)
insert [dbo].[ban] values (12,0)
insert [dbo].[ban] values (13,0)
insert [dbo].[ban] values (14,0)
insert [dbo].[ban] values (15,0)
insert [dbo].[ban] values (16,0)
insert [dbo].[ban] values (17,0)
insert [dbo].[ban] values (18,0)
insert [dbo].[ban] values (19,0)
insert [dbo].[ban] values (20,0)
--dữ liệu khách hàng-----------------------
insert [dbo].[khachHang] values(1,N'Mai Đức Trường','0123456789')
insert [dbo].[khachHang] values(2,N'Nguyễn Đỗ Xuân Trường','0987654321')
insert [dbo].[khachHang] values(3,N'Tô Hoàng Thành','0234567891')
insert [dbo].[khachHang] values(4,N'Trương Mỹ Lan','0678912345')
insert [dbo].[khachHang] values(5,N'Duy Thẩm','0125456789')
insert [dbo].[khachHang] values(6,N'Độ mixi','0986432689')
insert [dbo].[khachHang] values(7,'','');
--dữ liệu của loại sản phẩm-----------------------------
insert [dbo].[loaiSanPham] values(1,'Cafe')
insert [dbo].[loaiSanPham] values(2,N'Trà')
insert [dbo].[loaiSanPham] values(3,N'Nước đóng chai')
insert [dbo].[loaiSanPham] values(4,N'Đồ ăn')
insert [dbo].[loaiSanPham] values(5,N'Khác')
--dữ liệu của sản phẩm----------------------------------------
--loại cafe
insert [dbo].[sanPham] values(1,N'Bạc xỉu đá','..\project\anh\1bacXiuDa.jpg',1,20000,'ly',1)
insert [dbo].[sanPham] values(2,N'Cappucino','..\project\anh\1cappucino.jpg',1,21000,'ly',1)
insert [dbo].[sanPham] values(3,N'Caramel Macchiatto','..\project\anh\1CARAMEL_MACCHIATTO.jpg',1,30000,'ly',1)
insert [dbo].[sanPham] values(4,N'Đen Đá','..\project\anh\1denDa.jpg',1,15000,'ly',1)
insert [dbo].[sanPham] values(5,N'Đen Nóng','..\project\anh\1denNong.jpg',1,19000,'ly',1)
insert [dbo].[sanPham] values(6,N'Expresso','..\project\anh\1EXPRESSO.jpg',1,18000,'ly',1)
insert [dbo].[sanPham] values(7,N'Latte','..\project\anh\1latte.jpg',1,25000,'ly',1)
insert [dbo].[sanPham] values(8,N'Phindi kem sữa','..\project\anh\1PHINDI_KEM_SUA.jpg',1,29000,'ly',1)
insert [dbo].[sanPham] values(9,N'Phin sữa đá','..\project\anh\1phinSuaDa.jpg',1,30000,'ly',1)
insert [dbo].[sanPham] values(10,N'Sữa nóng','..\project\anh\1suaNong.jpg',1,19000,'ly',1)
--loại trà
insert [dbo].[sanPham] values(11,N'Trà dác thơm','..\project\anh\2dacthom.png',1,30000,'ly',2)
insert [dbo].[sanPham] values(12,N'Hồng trà chanh','..\project\anh\2hong_tra_chanh.png',1,30000,'ly',2)
insert [dbo].[sanPham] values(13,N'Trà Lucky','..\project\anh\2luckytea.png',1,30000,'ly',2)
insert [dbo].[sanPham] values(14,N'Trà nhãn sen','..\project\anh\2tra_nhan_sen.png',1,30000,'ly',2)
insert [dbo].[sanPham] values(15,N'Trà olong dâu','..\project\anh\2tra_olong_dau.png',1,40000,'ly',2)
insert [dbo].[sanPham] values(16,N'Trà  quả mọng anh đào','..\project\anh\2TRA_QUA_MONG ANH_DAO.jpg',1,40000,'ly',2)
insert [dbo].[sanPham] values(17,N'Trà sen vàng','..\project\anh\2TRA_SEN_VANG.jpg',1,40000,'ly',2)
insert [dbo].[sanPham] values(18,N'Trà sữa mẵng cầu sợi dừa','..\project\anh\2Tra_Sua_Mang_Cau_Soi_Dua.png',1,40000,'ly',2)
insert [dbo].[sanPham] values(19,N'Trà sữa matcha','..\project\anh\2Tra_Sua_matcha.png',0,25000,'ly',2)
insert [dbo].[sanPham] values(20,N'Trà sữa nhãn sen','..\project\anh\2tra_sua_nhan_sen.png',0,25000,'ly',2)
insert [dbo].[sanPham] values(21,N'Trà sữa olong','..\project\anh\2Tra_sua_olong.png',0,25000,'ly',2)
insert [dbo].[sanPham] values(22,N'Trà sữa phúc long','..\project\anh\2Tra_sua_Phuc_long.png',0,25000,'ly',2)
--loại nước đóng chai
insert [dbo].[sanPham] values(23,N'Coca','..\project\anh\3coca.jpg',1,10000,'chai',3)
insert [dbo].[sanPham] values(24,N'Gold Strike','..\project\anh\3gold_strike.png',1,10000,'chai',3)
insert [dbo].[sanPham] values(25,N'Monster','..\project\anh\3Monster.png',1,10000,'chai',3)
insert [dbo].[sanPham] values(26,N'Pesi','..\project\anh\3pesi.jpg',1,10000,'chai',3)
insert [dbo].[sanPham] values(27,N'Sữa tươi','..\project\anh\3Sua_tuoi.png',1,10000,'ly',3)
--loại đồ ăn
insert [dbo].[sanPham] values(28,N'Bánh croissant','..\project\anh\4Banh_croissant.png',1,15000,N'cái',4)
insert [dbo].[sanPham] values(29,N'Bánh cua phô mai','..\project\anh\4Banh_cua_pho_mai.png',1,15000,N'cái',4)
insert [dbo].[sanPham] values(30,N'Bánh mì bơ tỏi','..\project\anh\4Banh_mi_bo_toi.png',1,25000,N'cái',4)
insert [dbo].[sanPham] values(31,N'Bánh su bơ sữa','..\project\anh\4Banh_su_bo_sua.png',1,6000,N'cái',4)
insert [dbo].[sanPham] values(32,N'Bánh mì','..\project\anh\4banhMi.png',1,15000,N'cái',4)
insert [dbo].[sanPham] values(33,N'Green tea chocolate cake','..\project\anh\4greenteachocolatecake.png',1,25000,N'Đĩa',4)
insert [dbo].[sanPham] values(34,N'Mangomisu','..\project\anh\4Mangomisua.png',1,25000,N'Đĩa',4)
--loại khác
insert [dbo].[sanPham] values(35,N'Đậu đỏ','..\project\anh\5DauDo.png',1,5000,N'100g',5)
insert [dbo].[sanPham] values(36,N'Sương sáo','..\project\anh\5Suongsao.png',1,5000,N'100g',5)
insert [dbo].[sanPham] values(37,N'Trân châu đen','..\project\anh\5TranChauDen.png',1,5000,N'100g',5)
insert [dbo].[sanPham] values(38,N'Trân châu trắng','..\project\anh\5TranChauTrang.png',1,5000,N'100g',5)
--dữ liệu Nhân viên
insert [dbo].[nhanVien] values (1,N'Mai Đức Trường',N'Quản lý',1,'1/1/2000',N'Nguyễn văn bảo, P4, Gò vấp');
insert [dbo].[nhanVien] values (2,N'Nguyễn Đỗ Xuân Trường',N'Quản lý',1,'2/1/2000',N'Nguyễn văn bảo, P4, Gò vấp');
insert [dbo].[nhanVien] values (3,N'Tô Hoàng Thành',N'Quản lý',1,'3/1/2000',N'Nguyễn văn bảo, P4, Gò vấp');
insert [dbo].[nhanVien] values (4,N'Nguyễn Văn A',N'Nhân viên',1,'3/1/2000',N'Nguyễn văn bảo, P4, Gò vấp');
--dữ liệu tài khoản
insert [dbo].[taiKhoan] values (1,N'admin1','admin1');
insert [dbo].[taiKhoan] values (2,N'admin2','admin2');
insert [dbo].[taiKhoan] values (3,N'admin3','admin3');
insert [dbo].[taiKhoan] values (4,N'nhanvien1','nhanvien1');
--dữ liệu Đơn hàng
insert [dbo].[donHang] values (1,1,1,1,CONVERT(DATE, '18/04/2024', 103),124000)
insert [dbo].[donHang] values (2,2,2,2,CONVERT(DATE, '17/04/2024', 103),200000)
insert [dbo].[donHang] values (3,3,3,3,CONVERT(DATE, '16/04/2024', 103),20000)
insert [dbo].[donHang] values (4,2,4,4,CONVERT(DATE, '15/04/2024', 103),200000)
--dữ liệu chi tiết đơn hàng
insert [dbo].[chiTietDonHang] values (1,1,1,1,0,20000,null)
insert [dbo].[chiTietDonHang] values (2,1,2,2,0,42000,null)
insert [dbo].[chiTietDonHang] values (3,1,3,2,0,60000,null)

insert [dbo].[chiTietDonHang] values (4,2,1,5,0,100000,null)
insert [dbo].[chiTietDonHang] values (5,2,14,1,0,30000,null)
insert [dbo].[chiTietDonHang] values (6,2,15,1,0,40000,null)
insert [dbo].[chiTietDonHang] values (7,2,16,1,0,40000,null)

insert [dbo].[chiTietDonHang] values (8,3,34,1,0.2,20000,null)
insert [dbo].[chiTietDonHang] values (9,4,35,20,0,100000,null)
insert [dbo].[chiTietDonHang] values (10,4,36,20,0,100000,null)

go
--drop procedure [dbo].[getThongKeTheoSanPham]
create PROC getThongKeTheoDonHang @start Date, @end Date, @maLoai int
AS
	if(@start is null and @end is null and @maLoai is null)
		begin 
				SELECT DH.maDonHang,maChiTietDonHang, maNhanVien,ngayTaoDon
				,maKhachHang,CTDH.maSanPham,soLuong,khuyenMai,CTDH.tongTien,moTa FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH
				ON DH.maDonHang = CTDH.maDonHang
				join [dbo].[sanPham] SP 
				ON SP.maSanPham = CTDH.maSanPham

		end
	else 
		begin
				SELECT DH.maDonHang,maChiTietDonHang, maNhanVien,ngayTaoDon
				,maKhachHang,CTDH.maSanPham,soLuong,khuyenMai,CTDH.tongTien,moTa FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH
				ON DH.maDonHang = CTDH.maDonHang
				join [dbo].[sanPham] SP 
				ON SP.maSanPham = CTDH.maSanPham
				where (@maLoai is null or @maLoai = SP.maLoai)
					AND (@start is null or DH.ngayTaoDon >=@start)
					and (@end is null or DH.ngayTaoDon <=@end)
		end
go
create PROC getThongKeTheoSanPham  @start Date, @end Date, @maLoai int
AS
	if(@start is null and @end is null and @maLoai is null)
		begin
			SELECT CTDH.maSanPham,tenSanPham,anh, LSP.maLoai,SoLuongDaBan = SUM(CTDH.soLuong),SoDonHang =  Count(DH.maDonHang),tongTien = Sum(CTDH.tongTien)  FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH 
			ON DH.maDonHang = CTDH.maDonHang
			join [dbo].[sanPham] SP
			ON CTDH.maSanPham = SP.maSanPham
			join [dbo].[loaiSanPham] LSP
			ON LSP.maLoai = SP.maLoai
			group by CTDH.maSanPham,tenSanPham,anh,LSP.maLoai
		end
	else 
		begin
			SELECT CTDH.maSanPham,tenSanPham,anh, LSP.maLoai,SoLuongDaBan = SUM(CTDH.soLuong),SoDonHang =  Count(DH.maDonHang),tongTien = Sum(CTDH.tongTien)  FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH 
			ON DH.maDonHang = CTDH.maDonHang
			join [dbo].[sanPham] SP
			ON CTDH.maSanPham = SP.maSanPham
			join [dbo].[loaiSanPham] LSP
			ON LSP.maLoai = SP.maLoai
			where (@maLoai is null or @maLoai = SP.maLoai)
					AND (@start is null or DH.ngayTaoDon >=@start)
					and (@end is null or DH.ngayTaoDon <=@end)
			group by CTDH.maSanPham,tenSanPham,anh,LSP.maLoai
		end
GO	
create PROC getTongDoanhThu 
AS
	declare @tongDoanhThu money, @soDonHang int, @soSanPham int,@loaiSanPhamBanChayNhat  Nvarchar(20), @sanPhamBanChayNhat  Nvarchar(50),@nhanVienBanNhieuNhat  Nvarchar(50)
	select @tongDoanhThu = Sum(DH.tongTien), @soDonHang= Count(DH.maDonHang), @soSanPham =Sum(CTDH.soLuong)
	FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH 
	ON DH.maDonHang = CTDH.maDonHang
	join [dbo].[sanPham] SP
	ON CTDH.maSanPham = SP.maSanPham
	join [dbo].[loaiSanPham] LSP
	ON LSP.maLoai = SP.maLoai

	set @loaiSanPhamBanChayNhat = (Select top 1 LSP.tenLoai FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH 
	ON DH.maDonHang = CTDH.maDonHang
	join [dbo].[sanPham] SP
	ON CTDH.maSanPham = SP.maSanPham
	join [dbo].[loaiSanPham] LSP
	ON LSP.maLoai = SP.maLoai
	Group by LSP.maLoai,LSP.tenLoai
	Having Sum(CTDH.soLuong) >= ALL(Select Sum(CTDH.soLuong) FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH 
									ON DH.maDonHang = CTDH.maDonHang
									join [dbo].[sanPham] SP
									ON CTDH.maSanPham = SP.maSanPham
									join [dbo].[loaiSanPham] LSP
									ON LSP.maLoai = SP.maLoai
									Group by  LSP.maLoai,LSP.tenLoai)
	)

	set @sanPhamBanChayNhat = (Select top 1 SP.tenSanPham FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH 
	ON DH.maDonHang = CTDH.maDonHang
	join [dbo].[sanPham] SP
	ON CTDH.maSanPham = SP.maSanPham
	join [dbo].[loaiSanPham] LSP
	ON LSP.maLoai = SP.maLoai
	Group by Sp.maSanPham,SP.tenSanPham
	Having Sum(CTDH.soLuong) >= ALL (Select Sum(CTDH.soLuong) FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH 
									ON DH.maDonHang = CTDH.maDonHang
									join [dbo].[sanPham] SP
									ON CTDH.maSanPham = SP.maSanPham
									join [dbo].[loaiSanPham] LSP
									ON LSP.maLoai = SP.maLoai
									Group by Sp.maSanPham,SP.tenSanPham)
	)
	set @nhanVienBanNhieuNhat= (Select top 1 NV.tenNhanVien FROM [dbo].[donHang] DH join [dbo].[nhanVien] NV
	ON DH.maNhanVien = NV.maNhanVien
	Group by NV.tenNhanVien,NV.maNhanVien
	Having Sum(Dh.tongTien) >= ALL(Select Sum(Dh.tongTien) FROM [dbo].[donHang] DH join [dbo].[nhanVien] NV
								ON DH.maNhanVien = NV.maNhanVien
								Group by NV.tenNhanVien,NV.maNhanVien)
	)

	Select tongDoanhThu = @tongDoanhThu,
	soDonHang= @soDonHang,
	soSanPham= @soSanPham,
	loaiSanPhamBanChayNhat = @loaiSanPhamBanChayNhat,
	sanPhamBanChayNhat = @sanPhamBanChayNhat,
	nhanVienBanNhieuNhat=@nhanVienBanNhieuNhat

go
create PROC getThongKeTheoDonHangDuocSapXep @start Date, @end Date, @maLoai int,@isTangDan int
AS
	if(@start is null and @end is null and @maLoai is null)
		begin 
			SELECT DH.maDonHang,maChiTietDonHang, maNhanVien,ngayTaoDon
			,maKhachHang,CTDH.maSanPham,soLuong,khuyenMai,CTDH.tongTien,moTa FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH
			ON DH.maDonHang = CTDH.maDonHang
			join [dbo].[sanPham] SP 
			ON SP.maSanPham = CTDH.maSanPham
			ORDER BY    
				CASE WHEN @isTangDan = 1 THEN DH.tongTien END ASC,
				CASE WHEN @isTangDan = 0 THEN DH.tongTien END DESC;		
		end
	else 
		begin
				SELECT DH.maDonHang,maChiTietDonHang, maNhanVien,ngayTaoDon
				,maKhachHang,CTDH.maSanPham,soLuong,khuyenMai,CTDH.tongTien,moTa FROM [dbo].[donHang] DH join [dbo].[chiTietDonHang] CTDH
				ON DH.maDonHang = CTDH.maDonHang
				join [dbo].[sanPham] SP 
				ON SP.maSanPham = CTDH.maSanPham
				where (@maLoai is null or @maLoai = SP.maLoai)
					AND (@start is null or DH.ngayTaoDon >=@start)
					and (@end is null or DH.ngayTaoDon <=@end)
				ORDER BY    
					CASE WHEN @isTangDan = 1 THEN DH.tongTien END ASC,
					CASE WHEN @isTangDan = 0 THEN DH.tongTien END DESC;	
		end
go