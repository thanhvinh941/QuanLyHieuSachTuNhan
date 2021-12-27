package Entity;

import java.sql.Date;

public class HoaDon {
	private String maHD;
	private Date ngayTao;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private int tongThanhTien;
	private int tienMat;
	private int tienDu;
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
	public HoaDon(String maHD, Date ngayTao, KhachHang khachHang, NhanVien nhanVien, int tongThanhTien, int tienMat) {
		super();
		this.maHD = maHD;
		this.ngayTao = ngayTao;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.tongThanhTien = tongThanhTien;
		this.tienMat = tienMat;
		this.tienDu = tienMat - tongThanhTien;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public int getTongThanhTien() {
		return tongThanhTien;
	}
	public void setTongThanhTien(int tongThanhTien) {
		this.tongThanhTien = tongThanhTien;
	}
	public int getTienMat() {
		return tienMat;
	}
	public void setTienMat(int tienMat) {
		this.tienMat = tienMat;
	}
	public int getTienDu() {
		return tienDu;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayTao=" + ngayTao + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", tongThanhTien=" + tongThanhTien + ", tienMat=" + tienMat + ", tienDu=" + tienDu + "]";
	}
}
