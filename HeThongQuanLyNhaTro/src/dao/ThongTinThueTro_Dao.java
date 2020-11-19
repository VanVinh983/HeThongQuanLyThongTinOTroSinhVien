package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaTro;
import entity.SinhVien;
import entity.ThongTinThueTro;

public class ThongTinThueTro_Dao {
	public ArrayList<ThongTinThueTro>  layTatCaDsThongTinThueTro(){
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
				LocalDate ngaybatdau = LocalDate.of(rs.getDate(2).toLocalDate().getYear(), rs.getDate(2).toLocalDate().getMonthValue(), rs.getDate(2).toLocalDate().getDayOfMonth());
				LocalDate ngayketthuc = LocalDate.of(rs.getDate(3).toLocalDate().getYear(), rs.getDate(3).toLocalDate().getMonthValue(), rs.getDate(3).toLocalDate().getDayOfMonth());
				LocalDate ngaycapnhat = LocalDate.of(rs.getDate(4).toLocalDate().getYear(), rs.getDate(4).toLocalDate().getMonthValue(), rs.getDate(4).toLocalDate().getDayOfMonth());
				String trangThai ="";
				if(rs.getByte(5) == 1) {
					trangThai = "Đang Thuê";
				}
				else {
					trangThai = "Không Còn Thuê";
				}
				NhaTro nhatro = new NhaTro(rs.getString(6));
				SinhVien sinhvien = new SinhVien(rs.getString(7));
				
				ThongTinThueTro t = new ThongTinThueTro(gia, ngaybatdau, ngayketthuc, ngaycapnhat, trangThai, nhatro, sinhvien);
				dsThongTinThueTro.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dsThongTinThueTro;
	}
	
	public ArrayList<ThongTinThueTro>  layTatCaDsThongTinThueTroTheoMaNhanVien(String manv){
		ArrayList<ThongTinThueTro> dsThongTinThueTro = new ArrayList<ThongTinThueTro>();
		Statement statement = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnecction();
			
			String sql = "select giaTien, ngayBatDau, ngayKetThuc, ngayCapNhat, trangthai, maNhaTro, s.maSinhVien from ThongTinThueTro t join SinhVien s on t.maSinhVien = s.maSinhVien join NhanVien n on n.maNhanVien = s.maNhanVien where n.maNhanVien = "+"'"+manv+"'";
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				double gia = rs.getDouble(1);
				LocalDate ngaybatdau = LocalDate.of(rs.getDate(2).toLocalDate().getYear(), rs.getDate(2).toLocalDate().getMonthValue(), rs.getDate(2).toLocalDate().getDayOfMonth());
				LocalDate ngayketthuc = LocalDate.of(rs.getDate(3).toLocalDate().getYear(), rs.getDate(3).toLocalDate().getMonthValue(), rs.getDate(3).toLocalDate().getDayOfMonth());
				LocalDate ngaycapnhat = LocalDate.of(rs.getDate(4).toLocalDate().getYear(), rs.getDate(4).toLocalDate().getMonthValue(), rs.getDate(4).toLocalDate().getDayOfMonth());
				String trangThai ="";
				if(rs.getByte(5) == 1) {
					trangThai = "Đang Thuê";
				}
				else {
					trangThai = "Không Còn Thuê";
				}
				NhaTro nhatro = new NhaTro(rs.getString(6));
				SinhVien sinhvien = new SinhVien(rs.getString(7));
				ThongTinThueTro t = new ThongTinThueTro(gia, ngaybatdau, ngayketthuc, ngaycapnhat, trangThai, nhatro, sinhvien);
				dsThongTinThueTro.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dsThongTinThueTro;
	}
}
