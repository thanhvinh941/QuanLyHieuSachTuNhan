package Entity;

public class CTHoaDon {
	private SanPham sanPham;
	private HoaDon hoaDon;
	private int soLuong;
	private int thanhTien;
	public CTHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CTHoaDon(SanPham sanPham, int soLuong, int thanhTien) {
		super();
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
	}

	public CTHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong) {
		this.sanPham = sanPham;
		this.hoaDon = hoaDon;
		this.soLuong = soLuong;
		this.thanhTien = sanPham.getDonGia()*soLuong;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
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
		return "CTHoaDon [sanPham=" + sanPham + ", hoaDon=" + hoaDon + ", soLuong=" + soLuong +", thanhTien="+ thanhTien + "]";
	}
}
