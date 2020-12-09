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
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [trangThaiDangThue]=0";
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
		List<NhaTro> listNT = layTatCaBang();
		if(listNT.contains(nhaTro))
		{
			return false;
		}
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

	public boolean XoaNhaTroSinhVien(String maNhaTro)
	{
		boolean flag1, flag2;
		flag2 = XoaDiaChi(maNhaTro);
		flag1 = XoaNhaTro(maNhaTro);
		;
		if(flag1==true && flag2==true)
		{
			return true;
		}
		else
			return false;
	}
	
	private boolean XoaNhaTro(String maNhaTro)
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
	
	private boolean XoaDiaChi(String maDiaChi)
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
	
	
	
	public boolean CapNhatNhaTroSinhVien(NhaTro nhaTro)
	{
		CapNhatNhaTro(nhaTro);
		CapNhatDiaChi(nhaTro);
		return true;
	}
	
	private boolean CapNhatNhaTro(NhaTro nhaTro)
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
	
	public boolean CapNhatDiaChi(NhaTro nhaTro)
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
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where maNhaTro = "+"'"+ma+"'and [trangThaiDangThue]=0";
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
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where tenChuNha = "+"N'"+ten+"' and [trangThaiDangThue]=0";
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
			String sql = "    select * from [QLThongTinOTroSinhVien].[dbo].[NhaTro] as nt  join [QLThongTinOTroSinhVien].[dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [soNha] = "+"'"+soN+"'"+" and [tenDuong] = "+ "N'"+tenD+"'"+" and [phuong] = N'"+tenP+"' and [quan] = N'"+tenQ+"' and [trangThaiDangThue]=0";
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
				String tenNV = rs.getString(2);
				return tenNV;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}
	
	public ArrayList<NhaTro> layNhaTroTheoTenDuong(String tDuong) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [tenDuong] ="+"N'"+tDuong+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	
	public ArrayList<NhaTro> layNhaTroTheoSoNha(String sn) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [soNha] ="+"N'"+sn+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	
	public ArrayList<NhaTro> layNhaTroTheoPhuong(String phuong) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where phuong ="+"N'"+phuong+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	
	public ArrayList<NhaTro> layNhaTroTheoQuan(String quan) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where quan ="+"N'"+quan+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	
	//2  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<NhaTro> layNhaTroTheoPhuongQuan(String quan, String phuong) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where phuong = N'"+phuong+"' and  quan ="+"N'"+quan+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	
	public ArrayList<NhaTro> layNhaTroTheoQuanTenDuong(String tQuan, String tDuong) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [tenDuong] = N'"+tDuong+"' and  quan ="+"N'"+tQuan+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	
	public ArrayList<NhaTro> layNhaTroTheoQuanSoNha(String tQuan, String sn) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [soNha] = '"+sn+"' and  quan ="+"N'"+tQuan+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	//Tim theo phuong va ten duong
	public ArrayList<NhaTro> layNhaTroTheoPhuongTenDuong(String Phuong, String td) {
		ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
		try {
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [phuong] = N'"+Phuong+"' and  [tenDuong] ="+"N'"+td+"' and  [trangThaiDangThue]=0";
			Statement statement = con.createStatement();
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	return dsNhaTro;
	}
	
	//Tim theo phuong va so nha
		public ArrayList<NhaTro> layNhaTroTheoPhuongSoNha(String Phuong, String sn) {
			ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
			try {
				Connection con = ConnectDB.getInstance().getConnecction();
				String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [phuong] = N'"+Phuong+"' and  [soNha] = '"+sn+"' and  [trangThaiDangThue]=0";
				Statement statement = con.createStatement();
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return dsNhaTro;
		}
		
		//Tim theo ten duong va so nha
				public ArrayList<NhaTro> layNhaTroTheoTenDuongSoNha(String td, String sn) {
					ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
					try {
						Connection con = ConnectDB.getInstance().getConnecction();
						String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [tenDuong] = N'"+td+"' and  [soNha] = '"+sn+"' and  [trangThaiDangThue]=0";
						Statement statement = con.createStatement();
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
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				return dsNhaTro;
				}
				
				//Tim theo phuong, quan , so nha
				public ArrayList<NhaTro> layNhaTroTheoPhuongQuanSoNha(String Phuong,String Quan, String sn) {
					ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
					try {
						Connection con = ConnectDB.getInstance().getConnecction();
						String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [phuong] = N'"+Phuong+"' and  [quan] = N'"+Quan+"' and  [soNha] = '"+sn+"' and  [trangThaiDangThue]=0";
						Statement statement = con.createStatement();
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
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				return dsNhaTro;
				}
				
				//Tim theo phuong, quan , ten duong
				public ArrayList<NhaTro> layNhaTroTheoQuanPhuongTenDuong(String Quan,String Phuong,String td) {
					ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
					try {
						Connection con = ConnectDB.getInstance().getConnecction();
						String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [phuong] = N'"+Phuong+"' and  [quan] = N'"+Quan+"' and  [tenDuong] = N'"+td+"' and  [trangThaiDangThue]=0";
						Statement statement = con.createStatement();
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
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				return dsNhaTro;
				}
				
				//Tim theo quan , ten duong, so nha
				public ArrayList<NhaTro> layNhaTroTheoQuanTenDuongSoNha(String Quan,String td,String sn) {
					ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
					try {
						Connection con = ConnectDB.getInstance().getConnecction();
						String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [soNha] = N'"+sn+"' and  [quan] = N'"+Quan+"' and  [tenDuong] = N'"+td+"' and  [trangThaiDangThue]=0";
						Statement statement = con.createStatement();
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
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				return dsNhaTro;
				}
				
				//Tim theo Phuong , ten duong, so nha
				public ArrayList<NhaTro> layNhaTroTheoPhuongTenDuongSoNha(String Phuong,String td,String sn) {
					ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
					try {
						Connection con = ConnectDB.getInstance().getConnecction();
						String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [soNha] = N'"+sn+"' and  [phuong] = N'"+Phuong+"' and  [tenDuong] = N'"+td+"' and  [trangThaiDangThue]=0";
						Statement statement = con.createStatement();
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
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				return dsNhaTro;
				}
	
				//Tim theo quan, Phuong , ten duong, so nha
				public ArrayList<NhaTro> layNhaTroTheoQuanPhuongTenDuongSoNha(String Quan,String Phuong,String td,String sn) {
					ArrayList<NhaTro> dsNhaTro = new ArrayList<NhaTro>();
					try {
						Connection con = ConnectDB.getInstance().getConnecction();
						String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [quan] = N'"+Quan+"' and [soNha] = N'"+sn+"' and  [phuong] = N'"+Phuong+"' and  [tenDuong] = N'"+td+"' and  [trangThaiDangThue]=0";
						Statement statement = con.createStatement();
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
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				return dsNhaTro;
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

