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

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.SinhVien;


public class SinhVien_Dao {
	private ArrayList<SinhVien> listSV;

	public SinhVien_Dao() {
		listSV = new ArrayList<SinhVien>();
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<SinhVien> layTatCaBang()
	{
		
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[SinhVien]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				
				//System.out.println("Ngay sinh : " + rs.getDate(3).toLocalDate());
				String maSV= rs.getString(1);
				String tenSV = rs.getString(2);
				
				DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/mm/yyyy");
				
				LocalDate ngaySinh = LocalDate.of(rs.getDate(3).toLocalDate().getYear(), rs.getDate(3).toLocalDate().getMonthValue(), rs.getDate(3).toLocalDate().getDayOfMonth());
				String queQuanSV= rs.getString(4);
				String maLop = rs.getString(5);
				String maNV = rs.getString(6);
				String gioiTinh = "";
				if(rs.getByte(7)==1)
				{
					gioiTinh = "Nữ";
				}
				else {
					gioiTinh = "Nam";
				}
				String chuyenNghanh = rs.getString(8);
				SinhVien sv = new SinhVien(maSV, tenSV, ngaySinh, queQuanSV, maLop, maNV, gioiTinh, chuyenNghanh);
				listSV.add(sv);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return listSV;
	}
	
	public boolean themSV(SinhVien sv)
	{
			Connection con = ConnectDB.getInstance().getConnecction();
			PreparedStatement stmt = null;
			int n =0;
			 System.out.println("Que____ " + sv.getQueQuanSV());
			try {
				stmt = con.prepareStatement("insert SinhVien values(?,?,?,?,?,?,?,?)");
				stmt.setString(1, sv.getMaSV());
				stmt.setString(2, sv.getTenSV());
				Date ns = Date.valueOf(sv.getNgaySinh());
				stmt.setDate(3, ns);
				stmt.setString(4, sv.getQueQuanSV());
				stmt.setString(5, sv.getMaLop());
				stmt.setString(6, sv.getMaNV());
				if(sv.getGioiTinh()=="Nam")
				{
					try {
						stmt.setByte(7, (byte) 0);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				else
				{
					try {
						stmt.setByte(7, (byte) 1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				stmt.setString(8, sv.getChuyenNghanh());
				
				n=stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return n>0;
	}
	
	
	
	public boolean DeleteSinhVien(String id)
	{
		Connection con = ConnectDB.getInstance().getConnecction();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement(" Delete from SinhVien where maSinhVien =?");
			stmt.setString(1, id);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	


	public boolean UpdateSinhVien(SinhVien sv)
	{
		Connection con = ConnectDB.getInstance().getConnecction();
		PreparedStatement stmt  = null;
		int n=0;
		
		
		try {
			stmt = con.prepareStatement("Update SinhVien set tenSinhVien = ?, ngaySinh= ?,queQuanSinhVien= ?,maLop= ?,maNhanVien= ?,gioiTinh= ?,chuyenNganh= ? where maSinhVien = ?");	
			stmt.setString(1, sv.getTenSV());
			Date ns = Date.valueOf(sv.getNgaySinh());
			stmt.setDate(2, ns);
			stmt.setString(3, sv.getQueQuanSV());
			stmt.setString(4, sv.getMaLop());
			stmt.setString(5, sv.getMaNV());
			if(sv.getGioiTinh()=="Nam")
			{
				try {
					stmt.setByte(6, (byte) 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			else
			{
				try {
					stmt.setByte(6, (byte) 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			stmt.setString(7, sv.getChuyenNghanh());
			stmt.setString(8, sv.getMaSV());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
}
