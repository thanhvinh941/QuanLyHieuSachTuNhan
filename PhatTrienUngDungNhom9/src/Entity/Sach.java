package Entity;

public class Sach extends SanPham{
	private TacGia tacGia;
	private int soTrang;
	private NhaXuatBan nhaXuatBan;
	public Sach(String maSP, String tenSP, int donGia, int soLuong, LoaiSanPham danhMuc, TacGia tacGia, int soTrang, NhaXuatBan nhaXuatBan) {
		super(maSP, tenSP, donGia, soLuong, danhMuc);
		// TODO Auto-generated constructor stub
		this.tacGia = tacGia;
		this.soTrang = soTrang;
		this.nhaXuatBan = nhaXuatBan;
	}
	public TacGia getTacGia() {
		return tacGia;
	}
	public void setTacGia(TacGia tacGia) {
		this.tacGia = tacGia;
	}
	public int getSoTrang() {
		return soTrang;
	}
	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}
	public NhaXuatBan getNhaXuatBan() {
		return nhaXuatBan;
	}
	public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}
	@Override
	public String toString() {
		return super.toString()+"SanPhamSach [tacGia=" + tacGia + ", soTrang=" + soTrang + ", nhaXuatBan=" + nhaXuatBan + "]";
	}
	
}
