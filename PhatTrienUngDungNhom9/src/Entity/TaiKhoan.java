package Entity;

public class TaiKhoan {
	private String tenDN;
	private String matKhau;
	private NhanVien nhanVien;
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String tenDN, String matKhau, NhanVien nhanVien) {
		super();
		this.tenDN = tenDN;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}
	public String getTenDN() {
		return tenDN;
	}
	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public String toString() {
		return "TaiKhoan [tenDN=" + tenDN + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + "]";
	}
}
