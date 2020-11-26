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
				String  tenDuong= rs.getString(7);
				String tenPhuong= rs.getString(8);
				String  tenQuan = rs.getString(9);
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
				stmt.setString(3, nhaTro.getDiaChiTro().getTenDuong());
				stmt.setString(4, nhaTro.getDiaChiTro().getTenPhuong());
				stmt.setString(5, nhaTro.getDiaChiTro().getTenQuan());
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
				String  tenDuong= rs.getString(7);
				String tenPhuong= rs.getString(8);
				String  tenQuan = rs.getString(9);
				NhaTro nhaTro = new NhaTro(maTro, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
				return nhaTro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	
	public NhaTro layTroTheoTen(String ten)
	{
		
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where tenChuNha = "+"N'"+ten+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String maTro = rs.getString(1);
				String tenChutro = rs.getString(2);
				String SDT = rs.getString(3);
				String soNha= rs.getString(6);
				String  tenDuong= rs.getString(7);
				String tenPhuong= rs.getString(8);
				String  tenQuan = rs.getString(9);
				NhaTro nhaTro = new NhaTro(maTro, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
				return nhaTro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	
	public NhaTro layTroTheoDiaChi(String tenD, String soN, String tenP, String tenQ)
	{
		
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "    select * from [QLThongTinOTroSinhVien].[dbo].[NhaTro] as nt  join [QLThongTinOTroSinhVien].[dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [soNha] = "+"'"+soN+"'"+" and [tenDuong] = "+ "N'"+tenD+"'"+" and [phuong] = N'"+tenP+"' and [quan] = N'"+tenQ+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String maTro = rs.getString(1);
				String tenChutro = rs.getString(2);
				String SDT = rs.getString(3);
				String soNha= rs.getString(6);
				String  tenDuong= rs.getString(7);
				String tenPhuong= rs.getString(8);
				String  tenQuan = rs.getString(9);
				NhaTro nhaTro = new NhaTro(maTro, tenChutro, SDT, new DiaChi(tenQuan, tenPhuong, soNha, tenDuong));
				
				return nhaTro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	
	private String layMaNVTamLuu()
	{
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "SELECT [maNhanVien] FROM [QLThongTinOTroSinhVien].[dbo].[TamLuuMaNhanVien]";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String maNV = rs.getString(1);
				return maNV;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	
	public String layTenNhanVien()
	{
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = " SELECT [tenKhoa],[loaiNhanVien] FROM [QLThongTinOTroSinhVien].[dbo].[NhanVien] where [maNhanVien] = " + "'" + layMaNVTamLuu() + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String tenNV = rs.getString(1);
				if(rs.getString(2).equals("NV"))
					return "Giáo vụ khoa: " + tenNV;
				else if(rs.getString(2).equals("QL")){
					return "Người quản lý: " + tenNV;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	public ArrayList<DiaChi> layDiaChiTheoQuan(String quan) {
		ArrayList<DiaChi> dsDiaChi = new ArrayList<DiaChi>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from DiaChi where quan ="+"N'"+quan+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String phuong = rs.getString(4);
				String duong = rs.getString(3);
				String sonha = rs.getString(2);
				
				DiaChi diachi = new DiaChi(quan, phuong, sonha, duong);
				dsDiaChi.add(diachi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsDiaChi;
	}
	
	public ArrayList<DiaChi> layDiaChiTheoPhuongVaQuan(String phuong, String quan) {
		ArrayList<DiaChi> dsDiaChi = new ArrayList<DiaChi>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from DiaChi where quan =" + "N'" +quan+ "'" +"and"+ " phuong ="+ "N'" + phuong+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				String duong = rs.getString(3);
				String sonha = rs.getString(2);
				
				DiaChi diachi = new DiaChi(quan, phuong, sonha, duong);
				dsDiaChi.add(diachi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsDiaChi;
	}
	
	public ArrayList<DiaChi> layDiaChiTheoQuanPhuongDuong(String phuong, String quan, String duong) {
		ArrayList<DiaChi> dsDiaChi = new ArrayList<DiaChi>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from DiaChi where quan =N'"+ quan +"' and phuong = N'"+phuong+"' and tenDuong = N'"+duong+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				String sonha = rs.getString(2);
				DiaChi diachi = new DiaChi(quan, phuong, sonha, duong);
				dsDiaChi.add(diachi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsDiaChi;
	}
	
	public NhaTro layNhaTroTheoDia(String phuong, String quan, String duong,String sonha) {
		Statement statement=null;
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from NhaTro	n join DiaChi d on n.maNhaTro = d.maDiaChi where soNha =N'"+ sonha +"' and tenDuong = N'"+duong+"' and phuong = N'"+phuong+"' and quan = N'"+quan+"'";
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
				return nhaTro;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	return null;
	}
	public ArrayList<NhaTro> layNhaTroTheoSDT(String soDienThoai) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		Statement statement = null;
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from NhaTro n join DiaChi d on n.maNhaTro = d.maDiaChi where soDienThoai like ('"+soDienThoai+"%')";
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
	public ArrayList<NhaTro> layNhaTroTheoTenChuTro(String tenChuTro) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		Statement statement = null;
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from NhaTro n join DiaChi d on n.maNhaTro = d.maDiaChi where tenChuNha like (N'%"+tenChuTro+"%')";
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

