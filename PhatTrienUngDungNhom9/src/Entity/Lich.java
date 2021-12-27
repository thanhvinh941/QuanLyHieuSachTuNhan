package Entity;

import java.sql.Date;

public class Lich {
	private NhanVien nv;
	private Ca ca;
	private Date ngayLam;
	public Lich(NhanVien nv, Ca ca, Date ngayLam) {
		super();
		this.nv = nv;
		this.ca = ca;
		this.ngayLam = ngayLam;
	}
	public Lich() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	public Ca getCa() {
		return ca;
	}
	public void setCa(Ca ca) {
		this.ca = ca;
	}
	public Date getNgayLam() {
		return ngayLam;
	}
	public void setNgayLam(Date ngayLam) {
		this.ngayLam = ngayLam;
	}
	@Override
	public String toString() {
		return "Lich [nv=" + nv + ", ca=" + ca + ", ngayLam=" + ngayLam + "]";
	}
	
}
