package entity;

import java.util.ArrayList;

public class DanhSachNhanVien {
	private ArrayList<NhanVien> danhSachNhanVien;
	
	public DanhSachNhanVien() {
		danhSachNhanVien = new ArrayList<>();
	}
	
	public boolean themNhanVien(NhanVien nv) {
		if(danhSachNhanVien.contains(nv)) {
			return false;
		}
		danhSachNhanVien.add(nv);
		return true;
	}
	
	public boolean xoaNhanVien(String maNhanVien) {
		NhanVien nv = new NhanVien(maNhanVien);
		if(danhSachNhanVien.contains(nv)) {
			danhSachNhanVien.remove(nv);
			return true;
		}
		return false;
	}
	
	public ArrayList<NhanVien> timNhanVienTheoMa(String maNVCanTim){
		ArrayList<NhanVien>  dsnvCanTim = new ArrayList<>();
		for(NhanVien nv : danhSachNhanVien) {
			if(nv.getMaNV().matches(".*" + maNVCanTim +"*.")){
				dsnvCanTim.add(nv);
			}
		}
		return dsnvCanTim;
	}
	
}
