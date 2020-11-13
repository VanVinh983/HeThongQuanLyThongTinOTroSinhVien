package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connectDB.ConnectDB;

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
	
}
