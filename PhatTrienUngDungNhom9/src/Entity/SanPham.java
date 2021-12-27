package Entity;

public class SanPham {
	private String maSP;
	private String tenSP;
	private int donGia;
	private int soLuong;
	private LoaiSanPham danhMuc;
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
	public SanPham(String maSP, String tenSP, int donGia, int soLuong, LoaiSanPham danhMuc) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.danhMuc = danhMuc;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public LoaiSanPham getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(LoaiSanPham danhMuc) {
		this.danhMuc = danhMuc;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", donGia=" + donGia + ", soLuong=" + soLuong
				+ ", danhMuc=" + danhMuc + "]";
	}
}
