package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.DiaChi;
import entity.NhaTro;


public class NhaTro_Dao {
	private ArrayList<NhaTro> listNhaTro;

	public NhaTro_Dao() {
		listNhaTro = new ArrayList<NhaTro>();
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<NhaTro> layTatCaBang()
	{
		
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String maTro = rs.getString(1);
				String tenChutro = rs.getString(2);
				String SDT = rs.getString(3);
				String soNha= rs.getString(6);
				String tenPhuong = rs.getString(7);
				String tenQuan = rs.getString(8);
				String tenDuong = rs.getString(9);
				NhaTro nhaTro = new NhaTro(maTro, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
				listNhaTro.add(nhaTro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return listNhaTro;
	}
	
	public boolean them(NhaTro nhaTro)
	{
		if(listNhaTro.contains(nhaTro))
			return false;
		else
		{
			themNhaTro(nhaTro);
			themDiaChi(nhaTro);
		}	
		
		return true;
	}
	
	
	private boolean themNhaTro(NhaTro nhaTro)
	{
			Connection con = ConnectDB.getInstance().getConnecction();
			PreparedStatement stmt = null;
			int n =0;
			 
			try {
				stmt = con.prepareStatement("insert NhaTro values(?,?,?,?)");
				stmt.setString(1, nhaTro.getMaTro());
				stmt.setString(2, nhaTro.getTenChutro());
				stmt.setString(3, nhaTro.getSDT());
				try {
					stmt.setByte(4, (byte) 0);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Thêm lỗi");
				}
				
				n=stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return n>0;
	}
	
	private boolean themDiaChi(NhaTro nhaTro)
	{
			Connection con = ConnectDB.getInstance().getConnecction();
			PreparedStatement stmt = null;
			int n =0;
			 
			try {
				stmt = con.prepareStatement("insert DiaChi values(?,?,?,?,?)");
				stmt.setString(1, nhaTro.getMaTro());
				stmt.setString(2, nhaTro.getDiaChiTro().getSoNha());
				stmt.setString(3, nhaTro.getDiaChiTro().getTenPhuong());
				stmt.setString(4,nhaTro.getDiaChiTro().getTenQuan());
				stmt.setString(5,nhaTro.getDiaChiTro().getTenDuong());
				n = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		return n>0;
	}

	public boolean DeleteNhaTroSinhVien(String maNhaTro)
	{
		boolean flag1, flag2;
		flag2 = DeleteDiaChi(maNhaTro);
		flag1 = DeleteNhaTro(maNhaTro);
		;
		if(flag1==true && flag2==true)
		{
			return true;
		}
		else
			return false;
	}
	
	private boolean DeleteNhaTro(String maNhaTro)
	{
		Connection con = ConnectDB.getInstance().getConnecction();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement(" Delete from [QLThongTinOTroSinhVien].[dbo].[NhaTro] where [maNhaTro] =?");
			stmt.setString(1, maNhaTro);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	private boolean DeleteDiaChi(String maDiaChi)
	{
		Connection con = ConnectDB.getInstance().getConnecction();
		PreparedStatement stmt = null;
		int n =0;
		
		try {
			stmt = con.prepareStatement("Delete from [QLThongTinOTroSinhVien].[dbo].[DiaChi] where [maDiaChi] =?");
			stmt.setString(1, maDiaChi);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return n>0;
	}
	
	
	
	public boolean UpdateNhaTroSinhVien(NhaTro nhaTro)
	{
		UpdateNhaTro(nhaTro);
		UpdateDiaChi(nhaTro);
		return true;
	}
	
	private boolean UpdateNhaTro(NhaTro nhaTro)
	{
		Connection con = ConnectDB.getInstance().getConnecction();
		PreparedStatement stmt  = null;
		int n=0;
		
		try {
			stmt = con.prepareStatement("Update NhaTro set tenChuNha = ?, soDienThoai= ? where maNhaTro = ?");
			stmt.setString(1, nhaTro.getTenChutro());
			stmt.setString(2, nhaTro.getSDT());
			stmt.setString(3, nhaTro.getMaTro());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean UpdateDiaChi(NhaTro nhaTro)
	{
		Connection con = ConnectDB.getInstance().getConnecction();
		PreparedStatement stmt = null;
		int n =0;
		
		try {
			stmt = con.prepareStatement("Update DiaChi set soNha=?, quan=?, phuong =?, tenDuong = ? where maDiaChi=?");
			stmt.setString(1, nhaTro.getDiaChiTro().getSoNha());
			stmt.setString(2, nhaTro.getDiaChiTro().getTenQuan());
			stmt.setString(3, nhaTro.getDiaChiTro().getTenPhuong());
			stmt.setString(4, nhaTro.getDiaChiTro().getTenDuong());
			stmt.setString(5, nhaTro.getMaTro());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
//	public NhaTro timNhaTro(String maTro) {
//		
//			ConnectDB.getInstance();
//			Connection con = ConnectDB.getConnecction();
//			PreparedStatement stmt = null;
//			try {
//				System.out.println("\n++++++++++++++++++++++++++ ");
//				stmt = con.prepareStatement("select * from [QLThongTinOTroSinhVien].[dbo].[NhaTro] as nt  join [QLThongTinOTroSinhVien].[dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where nt.maNhaTro =  'NT_0005'");
//				//stmt.setString(1, maTro);
//				ResultSet rs = stmt.executeQuery();
//				System.out.println("\n++++++++++++++++++++++++++ " + rs.getString(1));
//				String maTroSV = rs.getString(1);
//				String tenChutro = rs.getString(2);
//				String SDT = rs.getString(3);
//				String soNha= rs.getString(6);
//				String tenPhuong = rs.getString(7);
//				String tenQuan = rs.getString(8);
//				String tenDuong = rs.getString(9);
//				NhaTro nhaTro = new NhaTro(maTroSV, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
//				System.out.println("\n++++++++++++++++++++++++++ " + nhaTro.toString());
//				return nhaTro;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			return null;
//		
//	}
	public NhaTro layTroTheoMa(String ma)
	{
		
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where maNhaTro = "+"'"+ma+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String maTro = rs.getString(1);
				String tenChutro = rs.getString(2);
				String SDT = rs.getString(3);
				String soNha= rs.getString(6);
				String tenPhuong = rs.getString(7);
				String tenQuan = rs.getString(8);
				String tenDuong = rs.getString(9);
				NhaTro nhaTro = new NhaTro(maTro, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
				System.out.println(nhaTro.toString());
				return nhaTro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
}

