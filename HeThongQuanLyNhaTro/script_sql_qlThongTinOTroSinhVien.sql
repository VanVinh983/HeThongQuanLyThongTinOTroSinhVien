
USE Master 
GO
CREATE DATABASE QLThongTinOTroSinhVien
ON
( NAME = QLThongTinOTroSinhVien_dat,
FILENAME = 'D:\Learn\Phat Trien Ung Dung\qlthongtinotrosinhvien_dat.mdf',
SIZE = 25MB,
MAXSIZE = 50MB,
FILEGROWTH = 10MB)
LOG ON
( NAME = QLThongTinOTroSinhVien_log,
FILENAME = 'D:\Learn\Phat Trien Ung Dung\qlthongtinotrosinhvien_log.ldf',
SIZE = 5MB,
MAXSIZE = 25MB,
FILEGROWTH = 5)
GO

USE QLThongTinOTroSinhVien

create table NhaTro
(
	maNhaTro varchar(8) not null,
	constraint pk_maNhaTro primary key (maNhaTro),
	tenChuNha nvarchar(20), 
	soDienThoai varchar(10),
	trangThaiDangThue bit
)
create table DiaChi
(
	maDiaChi varchar(8) unique not null,
	soNha nvarchar(50) not null,
	tenDuong nvarchar(20) not null,
	constraint pk_fk_maDiaChi foreign key (maDiaChi) references NhaTro(maNhaTro),
	phuong nvarchar(20),
	quan nvarchar(20)
)

create table NhanVien
(
	maNhanVien varchar(8) not null,
	constraint pk_maNhanVien primary key(maNhanVien),
	loaiNhanVien varchar(3),
	matKhau varchar(8),
	tenNhanVien nvarchar(20),
	ngaySinh date,
	tenKhoa nvarchar(50)
	
)

create table SinhVien
(
	maSinhVien varchar(8) not null,
	tenSinhVien nvarchar(50) not null, 
	ngaySinh date, 
	queQuanSinhVien nvarchar(50),
	constraint pk_maSinhVien primary key (maSinhVien),
	maLop varchar(8),
	maNhanVien varchar(8),
	constraint fk_maNhanVien_SinhVien foreign key (maNhanVien) references NhanVien(maNhanVien),
	gioiTinh bit,
	chuyenNganh nvarchar(20)
)
create table ThongTinThueTro
(
	giaTien money, 
	ngayBatDau date, 
	ngayKetThuc date, 
	ngayCapNhat date,
	trangthai bit, 
	maNhaTro varchar(8), 
	maSinhVien varchar(8),
	constraint fk_maNhaTro foreign key (maNhaTro) references NhaTro(maNhaTro),
	constraint fk_maSinhVien foreign key (maSinhVien) references SinhVien(maSinhVien),
	constraint pk_maNhaTro_maSinhVien primary key (maNhaTro,maSinhVien)
)

create table TamLuuMaNhanVien
(
	maNhanVien varchar(8),
)

go

create trigger suaTrangThaiThueTrolucThem on dbo.ThongTinThueTro
after insert
as
begin
	begin Transaction
	if exists (select * from ThongTinThueTro where maNhaTro = (select i.maNhaTro from inserted i))
	begin
		update NhaTro set trangThaiDangThue = 1 where maNhaTro = (select i.maNhaTro from inserted i)
		commit
	end
end
go
create trigger suaTrangThaiThueTrolucXoa on dbo.ThongTinThueTro
after delete
as
begin
	begin Transaction
		if not exists(select * from ThongTinThueTro where maNhaTro = (select d.maNhaTro from deleted d))
		begin
			update NhaTro set trangThaiDangThue = 0 where maNhaTro = (select d.maNhaTro from deleted d)
			commit
		end
end

