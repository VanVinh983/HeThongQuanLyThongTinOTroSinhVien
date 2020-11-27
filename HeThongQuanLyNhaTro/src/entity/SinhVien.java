package entity;

import java.time.LocalDate;

import javax.xml.crypto.Data;

public class SinhVien {
	private String maSV;
	private String tenSV;
	private LocalDate ngaySinh;
	private String queQuanSV;
	private String maLop;
	private NhanVien maNV;
	private String gioiTinh;
	private String chuyenNghanh;
	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SinhVien(String maSV) {
		super();
		this.maSV = maSV;
	}
	public SinhVien(String maSV, String tenSV, LocalDate ngaySinh, String queQuanSV, String maLop, NhanVien maNV,
			String gioiTinh, String chuyenNghanh) {
		super();
		this.maSV = maSV;
		this.tenSV = tenSV;
		this.ngaySinh = ngaySinh;
		this.queQuanSV = queQuanSV;
		this.maLop = maLop;
		this.maNV = maNV;
		this.gioiTinh = gioiTinh;
		this.chuyenNghanh = chuyenNghanh;
	}

	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getTenSV() {
		return tenSV;
	}
	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getQueQuanSV() {
		return queQuanSV;
	}
	public void setQueQuanSV(String queQuanSV) {
		this.queQuanSV = queQuanSV;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public NhanVien getMaNV() {
		return maNV;
	}
	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getChuyenNghanh() {
		return chuyenNghanh;
	}
	public void setChuyenNghanh(String chuyenNghanh) {
		this.chuyenNghanh = chuyenNghanh;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSV == null) ? 0 : maSV.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		if (maSV == null) {
			if (other.maSV != null)
				return false;
		} else if (!maSV.equals(other.maSV))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", tenSV=" + tenSV + ", ngaySinh=" + ngaySinh + ", queQuanSV=" + queQuanSV
				+ ", maLop=" + maLop + ", maNV=" + maNV + ", gioiTinh=" + gioiTinh + ", chuyenNghanh=" + chuyenNghanh
				+ "]";
	}
	
}
