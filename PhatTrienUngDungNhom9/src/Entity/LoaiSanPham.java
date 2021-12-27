package Entity;

public class LoaiSanPham {
	private String maDM;
	private String tenDM;
	private int loaiDM;
	public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiSanPham(String maDM) {
		super();
		this.maDM = maDM;
	}
	public LoaiSanPham(String maDM, String tenDM, int loaiDM) {
		super();
		this.maDM = maDM;
		this.tenDM = tenDM;
		this.loaiDM = loaiDM;
	}
	public String getMaDM() {
		return maDM;
	}
	public void setMaDM(String maDM) {
		this.maDM = maDM;
	}
	public String getTenDM() {
		return tenDM;
	}
	public void setTenDM(String tenDM) {
		this.tenDM = tenDM;
	}
	public int isLoaiDM() {
		return loaiDM;
	}
	public void setLoaiDM(int loaiDM) {
		this.loaiDM = loaiDM;
	}
	@Override
	public String toString() {
		return "DanhMuc [maDM=" + maDM + ", tenDM=" + tenDM + ", loaiDM=" + loaiDM + "]";
	}
}
