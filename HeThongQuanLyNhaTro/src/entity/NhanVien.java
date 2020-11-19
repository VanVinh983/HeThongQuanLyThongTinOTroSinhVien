package entity;

import java.sql.Date;
public class NhanVien {
	private String maNV;
	private String loaiNV;
	private String tenNV;
	private String matKhau;
	private Date ngaySinh;
	private String tenKhoa;
	
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getLoaiNV() {
		return loaiNV;
	}
	public void setLoaiNV(String loaiNV) {
		this.loaiNV = loaiNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	public NhanVien(String maNV, String loaiNV, String tenNV, String matKhau, Date ngaySinh, String tenKhoa) {
		super();
		this.maNV = maNV;
		this.loaiNV = loaiNV;
		this.tenNV = tenNV;
		this.matKhau = matKhau;
		this.ngaySinh = ngaySinh;
		this.tenKhoa = tenKhoa;
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", loaiNV=" + loaiNV + ", tenNV=" + tenNV + ", matKhau=" + matKhau
				+ ", ngaySinh=" + ngaySinh + ", tenKhoa=" + tenKhoa + "]";
	}
	
	
	
}
