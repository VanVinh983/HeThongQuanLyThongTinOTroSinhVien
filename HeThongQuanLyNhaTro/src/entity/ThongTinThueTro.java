package entity;

import java.sql.Date;
import java.time.LocalDate;

public class ThongTinThueTro {
	private double giaThue;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private LocalDate ngayCapNhat;
	private String trangThai;
	private NhaTro nhaTro;
	private SinhVien sinhVien;
	public ThongTinThueTro() {
		super();
	}
	public ThongTinThueTro(double giaThue, LocalDate ngayBatDau, LocalDate ngayKetThuc, LocalDate ngayCapNhat,
			String trangThai, NhaTro nhaTro, SinhVien sinhVien) {
		super();
		this.giaThue = giaThue;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.ngayCapNhat = ngayCapNhat;
		this.trangThai = trangThai;
		this.nhaTro = nhaTro;
		this.sinhVien = sinhVien;
	}
	public double getGiaThue() {
		return giaThue;
	}
	public void setGiaThue(double giaThue) {
		this.giaThue = giaThue;
	}
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public LocalDate getNgayCapNhat() {
		return ngayCapNhat;
	}
	public void setNgayCapNhat(LocalDate ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public NhaTro getNhaTro() {
		return nhaTro;
	}
	public void setNhaTro(NhaTro nhaTro) {
		this.nhaTro = nhaTro;
	}
	public SinhVien getSinhVien() {
		return sinhVien;
	}
	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}
	@Override
	public String toString() {
		return "ThongTinThueTro [giaThue=" + giaThue + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc
				+ ", ngayCapNhat=" + ngayCapNhat + ", trangThai=" + trangThai + ", nhaTro=" + nhaTro + ", sinhVien="
				+ sinhVien + "]";
	}
	
	
}
