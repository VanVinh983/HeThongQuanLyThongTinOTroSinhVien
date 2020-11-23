package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

}
