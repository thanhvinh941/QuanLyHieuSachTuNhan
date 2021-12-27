package Entity;

public class TacGia {
	private String maTG;
	private String tenTG;
	private String quocTich;
	public TacGia(String maTG, String tenTG, String quocTich) {
		super();
		this.maTG = maTG;
		this.tenTG = tenTG;
		this.quocTich = quocTich;
	}
	public TacGia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaTG() {
		return maTG;
	}
	public void setMaTG(String maTG) {
		this.maTG = maTG;
	}
	public String getTenTG() {
		return tenTG;
	}
	public void setTenTG(String tenTG) {
		this.tenTG = tenTG;
	}
	public String getQuocTich() {
		return quocTich;
	}
	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}
	@Override
	public String toString() {
		return "TacGia [maTG=" + maTG + ", tenTG=" + tenTG + ", quocTich=" + quocTich + "]";
	}
}
