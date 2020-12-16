package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectDB.ConnectDB;
import entity.NhanVien;

public class TamLuuMaNhanVien_Dao {
	public boolean themMaNhanVienVaoVungNhoTam(String maMaNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into [dbo].[TamLuuMaNhanVien] values(?)");
			statement.setString(1, maMaNV);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	
	public boolean xoaMaNhanVienVaoVungNhoTam() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("delete from  [dbo].[TamLuuMaNhanVien]");
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	
	public NhanVien layNhanVienTrongBangTamLuu() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnecction();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement("select n.maNhanVien, loaiNhanVien,matKhau,tenNhanVien,ngaySinh,tenKhoa from TamLuuMaNhanVien t join NhanVien n on t.maNhanVien = n.maNhanVien ");
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
