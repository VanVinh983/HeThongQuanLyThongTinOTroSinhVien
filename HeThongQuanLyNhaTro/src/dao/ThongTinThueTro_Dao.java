package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.DiaChi;
import entity.NhaTro;
import entity.NhanVien;
import entity.SinhVien;
import entity.ThongTinThueTro;

public class ThongTinThueTro_Dao {
	public ArrayList<ThongTinThueTro> layTatCaDsThongTinThueTro() {
		ArrayList<ThongTinThueTro> dsThongTinThueTro = new ArrayList<ThongTinThueTro>();
		Statement statement = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnecction();

			String sql = "select * from ThongTinThueTro";
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				double gia = rs.getDouble(1);
				LocalDate ngaybatdau = LocalDate.of(rs.getDate(2).toLocalDate().getYear(),
						rs.getDate(2).toLocalDate().getMonthValue(), rs.getDate(2).toLocalDate().getDayOfMonth());
				LocalDate ngayketthuc = LocalDate.of(rs.getDate(3).toLocalDate().getYear(),
						rs.getDate(3).toLocalDate().getMonthValue(), rs.getDate(3).toLocalDate().getDayOfMonth());
				LocalDate ngaycapnhat = LocalDate.of(rs.getDate(4).toLocalDate().getYear(),
						rs.getDate(4).toLocalDate().getMonthValue(), rs.getDate(4).toLocalDate().getDayOfMonth());
				String trangThai = "";
				if (rs.getByte(5) == 1) {
					trangThai = "Đang Thuê";
				} else {
					trangThai = "Không Còn Thuê";
				}
				NhaTro nhatro = new NhaTro(rs.getString(6));
				SinhVien sinhvien = new SinhVien(rs.getString(7));

				ThongTinThueTro t = new ThongTinThueTro(gia, ngaybatdau, ngayketthuc, ngaycapnhat, trangThai, nhatro,
						sinhvien);
				dsThongTinThueTro.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dsThongTinThueTro;
	}

	public ArrayList<ThongTinThueTro> layTatCaDsThongTinThueTroTheoMaNhanVien(String manv) {
		ArrayList<ThongTinThueTro> dsThongTinThueTro = new ArrayList<ThongTinThueTro>();
		Statement statement = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnecction();

			String sql = "select giaTien, ngayBatDau, ngayKetThuc, ngayCapNhat, trangthai, maNhaTro, s.maSinhVien from ThongTinThueTro t join SinhVien s on t.maSinhVien = s.maSinhVien join NhanVien n on n.maNhanVien = s.maNhanVien where n.maNhanVien = "
					+ "'" + manv + "'";
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				double gia = rs.getDouble(1);
				LocalDate ngaybatdau = LocalDate.of(rs.getDate(2).toLocalDate().getYear(),
						rs.getDate(2).toLocalDate().getMonthValue(), rs.getDate(2).toLocalDate().getDayOfMonth());
				LocalDate ngayketthuc = LocalDate.of(rs.getDate(3).toLocalDate().getYear(),
						rs.getDate(3).toLocalDate().getMonthValue(), rs.getDate(3).toLocalDate().getDayOfMonth());
				LocalDate ngaycapnhat = LocalDate.of(rs.getDate(4).toLocalDate().getYear(),
						rs.getDate(4).toLocalDate().getMonthValue(), rs.getDate(4).toLocalDate().getDayOfMonth());
				String trangThai = "";
				if (rs.getByte(5) == 1) {
					trangThai = "Đang Thuê";
				} else {
					trangThai = "Không Còn Thuê";
				}
				NhaTro nhatro = new NhaTro(rs.getString(6));
				SinhVien sinhvien = new SinhVien(rs.getString(7));
				ThongTinThueTro t = new ThongTinThueTro(gia, ngaybatdau, ngayketthuc, ngaycapnhat, trangThai, nhatro,
						sinhvien);
				dsThongTinThueTro.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dsThongTinThueTro;
	}

	public boolean themThongTinThueTro(ThongTinThueTro t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into ThongTinThueTro values(?,?,?,?,?,?,?)");
			stmt.setDouble(1, t.getGiaThue());
			stmt.setDate(2, Date.valueOf(t.getNgayBatDau()));
			stmt.setDate(3, Date.valueOf(t.getNgayKetThuc()));
			stmt.setDate(4, Date.valueOf(t.getNgayCapNhat()));
			byte trangthai = 0;
			if (t.getTrangThai().equals("Đang Thuê")) {
				trangthai = 1;
			}
			stmt.setByte(5, trangthai);
			stmt.setString(6, t.getNhaTro().getMaTro());
			stmt.setString(7, t.getSinhVien().getMaSV());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean xoaThong(String maSinhVien, String maNhaTro) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("delete from ThongTinThueTro where maNhaTro = ? and maSinhVien = ?");
			statement.setString(1, maNhaTro);
			statement.setString(2, maSinhVien);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public boolean suaThongTinThueTro(String maNhaTro, String maSV, ThongTinThueTro t) {
		if (!maNhaTro.equals(t.getNhaTro().getMaTro())||!maSV.equals(t.getSinhVien().getMaSV()))
			return false;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement(
					"update ThongTinThueTro set ngayBatDau= ?,ngayKetThuc=?,ngayCapNhat=?,giaTien=?,trangthai=? where maNhaTro = ? and maSinhVien = ?");
			statement.setDouble(4, t.getGiaThue());
			statement.setDate(1, Date.valueOf(t.getNgayBatDau()));
			statement.setDate(2, Date.valueOf(t.getNgayKetThuc()));
			statement.setDate(3, Date.valueOf(t.getNgayCapNhat()));
			byte trangthai = 0;
			if (t.getTrangThai().equals("Đang Thuê")) {
				trangthai = 1;
			}
			statement.setByte(5, trangthai);
			statement.setString(6, maNhaTro);
			statement.setString(7, maSV);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public List<ThongTinThueTro> xemLichSuTheoMaSinhVien(String masv, String loaiNV, String maNV){
		List<ThongTinThueTro> listTT= new ArrayList<ThongTinThueTro>();
		Statement statement = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnecction();
			String sql =null;
			
			if (loaiNV.equals("QL")) {
				sql = "select t.giaTien, t.ngayBatDau,t.ngayKetThuc,t.ngayCapNhat,t.trangthai, t.maNhaTro, t.maSinhVien from ThongTinThueTro t where maSinhVien = '"+masv+"'";
			}
			else {
				sql = "select t.giaTien, t.ngayBatDau,t.ngayKetThuc,t.ngayCapNhat,t.trangthai, t.maNhaTro, t.maSinhVien from ThongTinThueTro t join SinhVien sv on t.maSinhVien = sv.maSinhVien where maNhanVien = '"+maNV+"' and t.maSinhVien = '"+masv+"'";
			}
			
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				double gia = rs.getDouble(1);
				LocalDate ngaybatdau = LocalDate.of(rs.getDate(2).toLocalDate().getYear(),
						rs.getDate(2).toLocalDate().getMonthValue(), rs.getDate(2).toLocalDate().getDayOfMonth());
				LocalDate ngayketthuc = LocalDate.of(rs.getDate(3).toLocalDate().getYear(),
						rs.getDate(3).toLocalDate().getMonthValue(), rs.getDate(3).toLocalDate().getDayOfMonth());
				LocalDate ngaycapnhat = LocalDate.of(rs.getDate(4).toLocalDate().getYear(),
						rs.getDate(4).toLocalDate().getMonthValue(), rs.getDate(4).toLocalDate().getDayOfMonth());
				String trangThai = "";
				if (rs.getByte(5) == 1) {
					trangThai = "Đang Thuê";
				} else {
					trangThai = "Không Còn Thuê";
				}
				NhaTro nhatro = new NhaTro(rs.getString(6));
				SinhVien sinhvien = new SinhVien(rs.getString(7));
				ThongTinThueTro t = new ThongTinThueTro(gia, ngaybatdau, ngayketthuc, ngaycapnhat, trangThai, nhatro,
						sinhvien);
				listTT.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return listTT;
	}
	public void suaTrangThaiThueTro(String maSV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("update ThongTinThueTro set trangthai = 0 where maSinhVien='"+maSV+"'");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public ArrayList<SinhVien> timKiemSinhVienTrongBangThongTin(String tuKhoa, String maNV, String loaiNV)
	{
		ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
		Statement statement = null;
		try {
			if (tuKhoa.contains("/")) {
				String[] s = tuKhoa.split("/");
				String d = s[0];
				String m = s[1];
				String y = s[2];
				tuKhoa = y+"-"+m+"-"+d;
 			}
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = null;
			if(loaiNV.equals("QL")) {
				sql = "select * from SinhVien sv where sv.maLop='"+tuKhoa+"' or sv.chuyenNganh like (N'%"+tuKhoa+"%') or sv.gioiTinh like (N'%"+tuKhoa+"%') or sv.maSinhVien like (N'%"+tuKhoa+"%') or sv.ngaySinh like (N'%"+tuKhoa+"%') or sv.queQuanSinhVien like (N'%"+tuKhoa+"%') or sv.tenSinhVien like (N'%"+tuKhoa+"%')\r\n"
						+ "";
			}
			else {
				sql = "select * from SinhVien where maSinhVien in (select maSinhVien from SinhVien sv where sv.maLop='"+tuKhoa+"' or sv.chuyenNganh like (N'%"+tuKhoa+"%') or sv.gioiTinh like (N'%"+tuKhoa+"%') or sv.maSinhVien like (N'%"+tuKhoa+"%') or sv.ngaySinh like (N'%"+tuKhoa+"%') or sv.queQuanSinhVien like (N'%"+tuKhoa+"%') or sv.tenSinhVien like (N'%"+tuKhoa+"%')) and maNhanVien ='"+maNV+"'\r\n"
						+ "";	
			}
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);	
			
			while(rs.next())
			{
				
				String maSV= rs.getString(1);
				String ten = rs.getString(2);
				DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
				
				LocalDate ngaySinh = LocalDate.of(rs.getDate(3).toLocalDate().getYear(), rs.getDate(3).toLocalDate().getMonthValue(), rs.getDate(3).toLocalDate().getDayOfMonth());
				String queQuanSV= rs.getString(4);
				String maLop = rs.getString(5);
				String maNhanVien = rs.getString(6);
				String gioiTinh = "";
				if(rs.getByte(7)==1)
				{
					gioiTinh = "Nữ";
				}
				else {
					gioiTinh = "Nam";
				}
				String chuyenNghanh = rs.getString(8);
				SinhVien sv = new SinhVien(maSV, ten, ngaySinh, queQuanSV, maLop, new NhanVien(maNhanVien), gioiTinh, chuyenNghanh);
				dssv.add(sv);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	return dssv;
	}
	
	
	public ArrayList<NhaTro> TimKiemTroTrenBangThongTin(String tuKhoa) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		Statement statement = null;
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql=null;
			if (tuKhoa.contains(", ")) {
				String[] s = tuKhoa.split(", ");
				String quan,phuong,duong,sonha;
				if(s.length == 4) {
					if(s[0].contains("Quận")||s[0].contains("quận")) {
						quan = s[0];
						phuong = s[1];
						duong = s[2];
						sonha = s[3];
					}
					else {
						quan = s[3];
						phuong = s[2];
						duong = s[1];
						sonha = s[0];
					}
					sql = "select * from NhaTro nt join DiaChi dc on nt.maNhaTro = dc.maDiaChi where soNha like(N'%"+sonha+"%') and tenDuong like(N'%"+duong+"%') and phuong like(N'%"+phuong+"%') and quan like(N'%"+quan+"%')";
				}else if (s.length==3) {
					if(s[0].contains("Quận")||s[0].contains("quận")) {
						quan = s[0];
						phuong = s[1];
						duong = s[2];
					}
					else {
						quan = s[2];
						phuong = s[1];
						duong = s[0];
					}
					sql = "select * from NhaTro nt join DiaChi dc on nt.maNhaTro = dc.maDiaChi where tenDuong like(N'%"+duong+"%') and phuong like(N'%"+phuong+"%') and quan like(N'%"+quan+"%')";

				}else if (s.length==2) {
					if(s[0].contains("Quận")||s[0].contains("quận")) {
						quan = s[0];
						phuong = s[1];
					}
					else {
						quan = s[1];
						phuong = s[0];
					}
					sql = "select * from NhaTro nt join DiaChi dc on nt.maNhaTro = dc.maDiaChi where  phuong like(N'%"+phuong+"%') and quan like(N'%"+quan+"%')";

				}
			}else {
				sql = "select * from NhaTro nt join DiaChi d on nt.maNhaTro = d.maDiaChi where nt.maNhaTro ='"+tuKhoa+"' or tenChuNha like(N'%"+tuKhoa+"%') or soDienThoai like(N'%"+tuKhoa+"%') or tenDuong like(N'%"+tuKhoa+"%') or phuong like(N'%"+tuKhoa+"%') or quan like(N'%"+tuKhoa+"%')";
			}
			
			
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				String maTro = rs.getString(1);
				String tenChutro = rs.getString(2);
				String SDT = rs.getString(3);
				String soNha= rs.getString(6);
				String tenDuong = rs.getString(7);
				String tenPhuong = rs.getString(8);
				String tenQuan = rs.getString(9);
				NhaTro nhaTro = new NhaTro(maTro, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
				dsNhaTro.add(nhaTro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	return dsNhaTro;
	}
	
}