
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.spi.CollatorProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_Dao {
	
private ArrayList<NhanVien> danhSachNhanVien;
	
	public NhanVien_Dao() {
		
		danhSachNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public void loadNhanVienTuDatabase() {
		//mở kết nối sql
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnecction();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM NhanVien");
			while (rs.next()) {
				String maNV = rs.getString(1);
				String loaiNV = rs.getString(2);
				String matkhau = rs.getString(3);
				String tenNV  =rs.getString(4);
				Date ngaysinh = rs.getDate(5);
				String tenkhoa = rs.getString(6);
				NhanVien nv = new NhanVien(maNV, loaiNV, tenNV, matkhau, ngaysinh, tenkhoa);
				danhSachNhanVien.add(nv);
			} 
			con.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

	
	public boolean themNV(NhanVien nv) {
		if(danhSachNhanVien.contains(nv)) {
			return false;
		}
		danhSachNhanVien.add(nv);
		themNhanVienVaoDatabase(nv);
		return true;
	}
	
	
	public boolean xoaNhanVien(String maNhanVien) {
		NhanVien nv = new NhanVien(maNhanVien);
		if(danhSachNhanVien.contains(nv)) {
			danhSachNhanVien.remove(nv);
			xoaNhanVienKhoiDatabase(nv.getMaNV());
			return true;
		}
		return false;
	}
	
	public void themNhanVienVaoDatabase(NhanVien nv) {
		//sql connecting
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnecction();
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO NhanVien(maNhanVien, loaiNhanVien, matKhau, tenNhanVien, ngaySinh, tenKhoa) "
					+ "VALUES ('"+nv.getMaNV()+"', '"+nv.getLoaiNV()+"', '"+nv.getMatKhau()+"', N'"+nv.getTenNV()+"', '"+nv.getNgaySinh()+"', N'"+nv.getTenKhoa()+"')"); 
			con.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();

		}
	}
	
	public void xoaNhanVienKhoiDatabase(String maNV) {
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnecction();
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM NhanVien WHERE maNhanVien='"+maNV+"'"); 
			con.close();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();

		}
	}
	
	public void capNhatNhanVienTrongDatabase(NhanVien nv) {
		//sql connecting
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnecction();
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE NhanVien SET tenNhanVien = N'"+nv.getTenNV()+"', loaiNhanVien = '"+nv.getLoaiNV()+"', matKhau = '"+nv.getMatKhau()+"', ngaySinh = '"+nv.getNgaySinh()+"', tenKhoa = N'"+nv.getTenKhoa()+"' WHERE maNhanVien='"+nv.getMaNV()+"'");
			this.xoaNhanVien(nv.getMaNV());
			this.themNV(nv);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public ArrayList<NhanVien> timNhanVienTheoMa(String maNVCanTim){
		ArrayList<NhanVien>  dsnvCanTim = new ArrayList<>();
		for(NhanVien nv : danhSachNhanVien) {
			//if(nv.getMaNV().matches(".*" + maNVCanTim +"*.")){
			if(nv.getMaNV().contains(maNVCanTim)) {
				dsnvCanTim.add(nv);
			}
		}
		return dsnvCanTim;
	}
	
	public ArrayList<NhanVien> timNhanVienTheoTen(String tenNVCanTim){
		ArrayList<NhanVien>  dsnvCanTim = new ArrayList<>();
		for(NhanVien nv : danhSachNhanVien) {
			if(nv.getTenNV().contains(tenNVCanTim)) {
				dsnvCanTim.add(nv);
			}
		}
		return dsnvCanTim;
	}
	
	public ArrayList<NhanVien> timNhanVienTheoKhoa(String khoaCanTim){
		ArrayList<NhanVien>  dsnvCanTim = new ArrayList<>();
		for(NhanVien nv : danhSachNhanVien) {
			if(nv.getTenKhoa().contains(khoaCanTim)) {
				dsnvCanTim.add(nv);
			}
		}
		return dsnvCanTim;
	}
	
	
	public ArrayList<NhanVien> layDanhSach(){
		return danhSachNhanVien;
	}
	
	public String phatSinhMaNV() {
		String maNV = "NV_00001";
		int number = Integer.parseInt(maNV.split("_")[1]);
		for(NhanVien nv : danhSachNhanVien) {
			
			int number_x = Integer.parseInt(nv.getMaNV().split("_")[1]);
			if(number < number_x) {
				number = number_x;
			}	
		}
			number++;
			if(number < 100000) {
				maNV = "NV_" + String.valueOf(number/10000) + String.valueOf(number/1000) + String.valueOf(number/100) + String.valueOf(number/10) + String.valueOf(number/1);
			}
			else {
				maNV = "NV_" + number;
			}
		return maNV;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
