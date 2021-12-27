package Entity;

public class CTDonHang {
	private SanPham sanPham;
	private DonHang donHang;
	private int soLuong;
	private int thanhTien;
	public CTDonHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CTDonHang(SanPham sanPham, DonHang donHang, int soLuong) {
		super();
		this.sanPham = sanPham;
		this.donHang = donHang;
		this.soLuong = soLuong;
		this.thanhTien = soLuong*sanPham.getDonGia();
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public DonHang getDonHang() {
		return donHang;
	}
	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public int getThanhTien() {
		return thanhTien;
	}
	@Override
	public String toString() {
		return "CTDonHang [sanPham=" + sanPham + ", donHang=" + donHang + ", soLuong=" + soLuong + ", thanhTien="
				+ thanhTien + "]";
	}
}
