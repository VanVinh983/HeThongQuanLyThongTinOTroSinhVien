package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_Dao {
	public NhanVien dangNhap(String maMaNV, String matKhau) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select * from NhanVien where maNhanVien = ? and matKhau = ? ");
			statement.setString(1, maMaNV);
			statement.setString(2, matKhau);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String maNV = rs.getString(1);
				String loaiNV = rs.getString(2);
				String matkhau = rs.getString(3);
				String tenNV  =rs.getString(4);
				Date ngaysinh = rs.getDate(5);
				String tenkhoa = rs.getString(6);
				
				NhanVien nv = new NhanVien(maNV, loaiNV, tenNV, matkhau, ngaysinh, tenkhoa);
				return nv;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
