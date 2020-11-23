package entity;

public class NhaTro {

	private String maTro;
	private String tenChutro;
	private String SDT;
	private DiaChi diaChiTro;
	public NhaTro() {
		
	}
	public NhaTro(String maTro, String tenChutro, String sDT, DiaChi diaChiTro) {
		super();
		this.maTro = maTro;
		this.tenChutro = tenChutro;
		SDT = sDT;
		this.diaChiTro = diaChiTro;
	}
	public NhaTro(String maTro) {
		super();
		this.maTro = maTro;
	}
	public String getMaTro() {
		return maTro;
	}
	public void setMaTro(String maTro) {
		this.maTro = maTro;
	}
	public String getTenChutro() {
		return tenChutro;
	}
	public void setTenChutro(String tenChutro) {
		this.tenChutro = tenChutro;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public DiaChi getDiaChiTro() {
		return diaChiTro;
	}
	public void setDiaChiTro(DiaChi diaChiTro) {
		this.diaChiTro = diaChiTro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maTro == null) ? 0 : maTro.hashCode());
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
		NhaTro other = (NhaTro) obj;
		if (maTro == null) {
			if (other.maTro != null)
				return false;
		} else if (!maTro.equals(other.maTro))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhaTro [maTro=" + maTro + ", tenChutro=" + tenChutro + ", SDT=" + SDT + ", diaChiTro=" + diaChiTro
				+ "]";
	}
	
	
}
