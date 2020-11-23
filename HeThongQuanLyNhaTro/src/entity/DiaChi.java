package entity;

public class DiaChi {

	private String tenQuan;
	private String tenPhuong;
	private String soNha;
	private String tenDuong;
	public DiaChi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiaChi(String tenQuan, String tenPhuong, String soNha, String tenDuong) {
		super();
		this.tenQuan = tenQuan;
		this.tenPhuong = tenPhuong;
		this.soNha = soNha;
		this.tenDuong = tenDuong;
	}
	public String getTenQuan() {
		return tenQuan;
	}
	public void setTenQuan(String tenQuan) {
		this.tenQuan = tenQuan;
	}
	public String getTenPhuong() {
		return tenPhuong;
	}
	public void setTenPhuong(String tenPhuong) {
		this.tenPhuong = tenPhuong;
	}
	public String getSoNha() {
		return soNha;
	}
	public void setSoNha(String soNha) {
		this.soNha = soNha;
	}
	public String getTenDuong() {
		return tenDuong;
	}
	public void setTenDuong(String tenDuong) {
		this.tenDuong = tenDuong;
	}
	@Override
	public String toString() {
		return "DiaChi [tenQuan=" + tenQuan + ", tenPhuong=" + tenPhuong + ", soNha=" + soNha + ", tenDuong=" + tenDuong
				+ "]";
	}
	
	
}
