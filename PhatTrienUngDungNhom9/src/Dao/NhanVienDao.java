package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import Connect.ConnectDB;
import Entity.Ca;
import Entity.KhachHang;
import Entity.Lich;
import Entity.NhanVien;

public class NhanVienDao {	Connection conn;
PreparedStatement preStm;
ResultSet rs;
public NhanVienDao() {
	super();
	// TODO Auto-generated constructor stub
}
private void closeConnection() throws SQLException {
	if (rs != null) {
		rs.close();
	}
	if (preStm != null) {
		preStm.close();
	}
	if (conn != null) {
		conn.close();
	}
};
public NhanVien getKHTheoMa(String maNVCanTim) throws SQLException, ClassNotFoundException{
	NhanVien ds = null;
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NHANVIEN where MANV = '"+maNVCanTim+"'";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String maNV = rs.getString(1);
			String tenNV = rs.getString(2);
			String cMND = rs.getString(3);
			String soDT= rs.getString(4);
			String diaChi = rs.getString(5);
			String chucVu = rs.getString(6);
			
			ds = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return ds;
}
public int demSoNV() throws SQLException, ClassNotFoundException {
	int soKH = 0;
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "select COUNT(MANV) as 'Tong' from NHANVIEN";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while(rs.next()) {
			soKH = rs.getInt(1);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return soKH;
}
public TreeMap<String, Integer> thongkeNhanVien(int month,int year) throws ClassNotFoundException, SQLException{
	TreeMap<String, Integer> dsThongKe = new TreeMap<String,Integer>();
	try {
		Connection con = ConnectDB.getConnection();
		String sql = " select  nv.TENNV,SUM(cthd.SOLUONG) as 'So_luong'\r\n"
				+ "  from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join NHANVIEN nv on nv.MANV = hd.MANV\r\n"
				+ "  where MONTH(NGAYTAO)="+month+" and YEAR(NGAYTAO)="+year+"\r\n"
				+ "  group by TENNV";

		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String tenNV = rs.getString(1);
			int soLuong = rs.getInt(2);
			dsThongKe.put(tenNV, soLuong);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return dsThongKe;
}

public TreeMap<String, Integer> thongkeNhanVienTOP1(int month,int year) throws ClassNotFoundException, SQLException{
	TreeMap<String, Integer> dsThongKe = new TreeMap<String,Integer>();
	try {
		Connection con = ConnectDB.getConnection();
		String sql = " select top 1 nv.TENNV,SUM(cthd.SOLUONG) as 'So_luong'\r\n"
				+ "  from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join NHANVIEN nv on nv.MANV = hd.MANV\r\n"
				+ "  where MONTH(NGAYTAO)="+month+" and YEAR(NGAYTAO)="+year+"\r\n"
				+ "  group by nv.TENNV"
				+ "  order by So_luong desc";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String tenNV = rs.getString(1);
			int soLuong = rs.getInt(2);
			dsThongKe.put(tenNV, soLuong);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return dsThongKe;
}

public TreeMap<String, Integer> thongkeNhanVienTOP5(int month,int year) throws ClassNotFoundException, SQLException{
	TreeMap<String, Integer> dsThongKe = new TreeMap<String,Integer>();
	try {
		Connection con = ConnectDB.getConnection();
		String sql = " select top 5 nv.TENNV,SUM(cthd.SOLUONG) as 'So_luong'\r\n"
				+ "  from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join NHANVIEN nv on nv.MANV = hd.MANV\r\n"
				+ "  where MONTH(NGAYTAO)="+month+" and YEAR(NGAYTAO)="+year+"\r\n"
				+ "  group by nv.TENNV"
				+ "  order by So_luong desc";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String tenNV = rs.getString(1);
			int soLuong = rs.getInt(2);
			System.out.println(tenNV);
			System.out.println(soLuong);
			dsThongKe.put(tenNV, soLuong);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return dsThongKe;
}
public NhanVien getNVTheoID(String maNVCanTim) throws Exception{
	NhanVien s = null;
//	LoaiSanPhamDao dmDao = new LoaiSanPhamDao();
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "  Select * from NHANVIEN where MANV='"+maNVCanTim+"'";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while(rs.next()) {
			
			String maNV = rs.getString(1);
			String tenNV = rs.getString(2);
			String cMND = rs.getString(3);
			String soDT= rs.getString(4);
			String diaChi = rs.getString(5);
			String chucVu = rs.getString(6);
			
			s = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return s;
}
public ArrayList<NhanVien> getalltbNhanVien() throws ClassNotFoundException{
	ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NHANVIEN ";
		java.sql.Statement statement = con.createStatement();
		ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String maNV = rs.getString(1);
			String tenNV = rs.getString(2);
			String cMND = rs.getString(3);
			String soDT= rs.getString(4);
			String diaChi = rs.getString(5);
			String chucVu = rs.getString(6);
		
			NhanVien ds = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
			dsnv.add(ds);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsnv;
}
public ArrayList<NhanVien> getLichLam(String txtmaNV,String txtTenNV,String txtSoDT,String txtCaLam,Date ngayLam) throws ClassNotFoundException{
	ArrayList<NhanVien> dskh = new ArrayList<NhanVien>();
	try {
		Connection con = ConnectDB.getConnection();
        String sql = "select nv.MANV,TENNV,CMND,SDT,DIACHI,CHUCVU,l.MACA,NGAYLAM, CALAM from \r\n"
        		+ "NHANVIEN nv join LICHLAM l on nv.MANV = l.MANV join CA ca on ca.MACA = l.MACA \r\n"
        		+ "where nv.MANV LIKE '%"+txtmaNV+"%' "
        		+ "AND nv.TENNV LIKE N'%"+txtTenNV+"%' "
        		+ "AND SDT LIKE '%"+txtSoDT+"%' "
        		+ "AND CALAM LIKE N'%"+txtCaLam+"%'\r\n"
        		+ "AND NGAYLAM LIKE '%"+ngayLam+"%'";
        java.sql.Statement statement = con.createStatement();
        ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
        while (rs.next()) {
             String maNV = rs.getString(1);
             String tenNV = rs.getString(2);
             String CMND = rs.getString(3);
             String soDT = rs.getString(4);
             String diaChi = rs.getString(5);
             String chucVu = rs.getString(6);
             NhanVien nv = new NhanVien(maNV, tenNV, CMND, soDT, diaChi, chucVu);          
             dskh.add(nv);
        }            
    } catch (SQLException e) {
		e.printStackTrace();
	}
	return dskh;
}
public boolean create(NhanVien nv) throws ClassNotFoundException, SQLException {
	Connection con = ConnectDB.getConnection();
	PreparedStatement statement = null;
	int n = 0;
	try {
		statement = con.prepareStatement("insert into NHANVIEN([MANV],[TENNV],[CMND],[SDT],[DIACHI],[CHUCVU])  values(?, ?, ?, ?, ?, ?)");
		statement.setString(1, nv.getMaNV());
		statement.setString(2, nv.getTenNV());
		statement.setString(3, nv.getcMND());
		statement.setString(4, nv.getsDT());
		statement.setString(5, nv.getDiaChi());
		statement.setString(6, nv.getChuVu());
		n = statement.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return n>0;
}

public String layTenNV(String maNV) throws ClassNotFoundException {
		String tenNV="";
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "Select TENNV from NHANVIEN WHERE MANV ='"+maNV+"'";
		java.sql.Statement statement = con.createStatement();
		ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
		rs.next();
		tenNV = rs.getString(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return tenNV;
}

public String laySDTNV(String maNV) throws ClassNotFoundException {
	String sDTNV="";
try {
	Connection con = ConnectDB.getConnection();
	String sql = "Select SDT from NHANVIEN WHERE MANV ='"+maNV+"'";
	java.sql.Statement statement = con.createStatement();
	ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
	rs.next();
	sDTNV = rs.getString(1);
} catch (SQLException e) {
	e.printStackTrace();
}
return sDTNV;
}

public String layDiaNV(String maNV) throws ClassNotFoundException {
	String diaChiNV="";
try {
	Connection con = ConnectDB.getConnection();
	String sql = "Select DIACHI from NHANVIEN WHERE MAKH ='"+maNV+"'";
	java.sql.Statement statement = con.createStatement();
	ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
	rs.next();
	diaChiNV = rs.getString(1);
} catch (SQLException e) {
	e.printStackTrace();
}
return diaChiNV;
}

public static boolean delete(String tenNV) throws ClassNotFoundException, SQLException {
	Connection con = ConnectDB.getConnection();
	PreparedStatement stmt = null;
	int n = 0;
	try {
		stmt = con.prepareStatement("DELETE FROM NHANVIEN WHERE TENNV LIKE '"+tenNV+"'");
		n = stmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return n > 0;
}  
   public boolean update(NhanVien nv) throws ClassNotFoundException, SQLException {
	Connection con = ConnectDB.getConnection();
	PreparedStatement stmt = null;
	int n = 0;
	try {
		stmt = con.prepareStatement("UPDATE NHANVIEN SET TENNV=?,CMND=?,SDT=?,DIACHI=?,CHUCVU=? WHERE MANV=?");
		
		
		stmt.setString(1, nv.getTenNV());
		stmt.setString(2, nv.getcMND());
		stmt.setString(3, nv.getsDT());
		stmt.setString(4, nv.getDiaChi());
		stmt.setString(5, nv.getChuVu());
		stmt.setString(6, nv.getMaNV());
		n = stmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return n > 0;
}
public List<NhanVien> getNVTenSDT(String tenNVCanTim,String sdt) throws SQLException, ClassNotFoundException{
	List<NhanVien> dsnv = null;
	NhanVien nv;
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NHANVIEN where TENNV like N'%"+tenNVCanTim+"%' and SDT like N'%"+sdt+"%'";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		dsnv = new ArrayList<NhanVien>();
		while (rs.next()) {
			String maNV = rs.getString(1);
			String tenNV = rs.getString(2);
			String cMND = rs.getString(3);
			String soDT = rs.getString(4);
			String diaChi = rs.getString(5);
			String chucVu = rs.getString(6);
			nv = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
			dsnv.add(nv);
	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return dsnv;
}
public List<NhanVien> getMaNVTenNV(String tenNVCanTim) throws SQLException, ClassNotFoundException{
	List<NhanVien> ds = null;
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NHANVIEN where TENNV like N'%"+tenNVCanTim+"%'";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		ds = new ArrayList<NhanVien>();
		while (rs.next()) {
			String maNV = rs.getString(1);
			String tenNV = rs.getString(2);
			String cMND = rs.getString(3);
			String soDT = rs.getString(4);
			String diaChi = rs.getString(5);
			String chucVu = rs.getString(6);
			NhanVien nv = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
			ds.add(nv);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return ds;
}
}
