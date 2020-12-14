package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DiaChi;
import entity.NhaTro;

public class ThongKe_Dao {
	public ArrayList<NhaTro> thongKeTroDangThue_data(){
		ArrayList<NhaTro> dsnt = new ArrayList<>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getInstance().getConnecction();
			String sql = "select * from [dbo].[NhaTro] as nt  join [dbo].[DiaChi] as dc on nt.maNhaTro = dc.maDiaChi where [trangThaiDangThue]=1";
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
				dsnt.add(nhaTro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsnt;
	}
	
	public ArrayList<NhaTro> thongKeTroChuaThueTro_data(){
		ArrayList<NhaTro> dsnt = new ArrayList<>();
		try {
			ConnectDB.getInstance().connect();
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
				dsnt.add(nhaTro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsnt;
	}
	
	
}
