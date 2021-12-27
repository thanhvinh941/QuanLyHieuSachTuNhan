package Entity;

public class Ca {
	private String maCa;
	private String caLam;
	public Ca(String maCa, String caLam) {
		super();
		this.maCa = maCa;
		this.caLam = caLam;
	}
	public Ca() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaCa() {
		return maCa;
	}
	public void setMaCa(String maCa) {
		this.maCa = maCa;
	}
	public String getCaLam() {
		return caLam;
	}
	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}
	@Override
	public String toString() {
		return "Ca [maCa=" + maCa + ", caLam=" + caLam + "]";
	}
	
}
