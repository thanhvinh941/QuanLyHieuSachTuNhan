package Entity;

public class DungCuHocTap extends SanPham{
	private NhaCungCap nhaCungCap;

	public DungCuHocTap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DungCuHocTap(String maSP, String tenSP, int donGia, int soLuong, LoaiSanPham danhMuc,NhaCungCap nhaCungCap) {
		super(maSP, tenSP, donGia, soLuong, danhMuc);
		// TODO Auto-generated constructor stub
		this.nhaCungCap = nhaCungCap;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	@Override
	public String toString() {
		return super.toString()+"SanPhamKhac [nhaCungCap=" + nhaCungCap + "]";
	}
	
}
