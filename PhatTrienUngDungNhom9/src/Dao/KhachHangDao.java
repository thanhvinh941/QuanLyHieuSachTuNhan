package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Connect.ConnectDB;
import Entity.DonHang;
import Entity.KhachHang;
import Entity.NhanVien;

public class KhachHangDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public KhachHangDao() {
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
	
	public ArrayList<KhachHang> getalltbKhachHang() throws ClassNotFoundException{
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from KHACHHANG";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(3);
				String diaChi = rs.getString(4);
				String gioiTinh = rs.getString(5);
				KhachHang kh = new KhachHang(maKH, tenKH, sdt, diaChi, gioiTinh);
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}
	public int demSoKH() throws SQLException, ClassNotFoundException {
		int soKH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MAKH) as 'Tong' from KHACHHANG";
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
	public ArrayList<KhachHang> getitemKhachHang(String txtmaKH,String txtTenKH,String txtSoDT,String txtDIACHI,String txtGioiTinh) throws ClassNotFoundException{
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			Connection con = ConnectDB.getConnection();
            String sql = "Select * from KHACHHANG WHERE maKH LIKE '%"+txtmaKH+"%' AND "
            		+ "TENKH LIKE N'%"+txtTenKH+"%' AND "
            		+ "SDT LIKE '%"+txtSoDT+"%' \r\n"
            		+ "AND DIACHI LIKE N'%"+txtDIACHI+"%' AND"
            		+ " GIOITINH LIKE N'%"+txtGioiTinh+"%' ";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
            while (rs.next()) {
                 String maKH = rs.getString(1);
                 String tenKH = rs.getString(2);
                 String sdt = rs.getString(3);
                 String diaCHi = rs.getString(4);
                 String gioiTinh = rs.getString(5);
                 KhachHang kh = new KhachHang(maKH, tenKH, sdt, diaCHi, gioiTinh);
                 dskh.add(kh);
            }            
        } catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public boolean create(KhachHang kh) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into KHACHHANG values(?, ?, ?, ?, ?)");
			statement.setString(1, kh.getMaKH());
			statement.setString(2, kh.getTenKH());
			statement.setString(3, kh.getSoDT());
			statement.setString(4, kh.getDiaChi());
			statement.setString(5, kh.getGioiTinh());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n>0;
	}
	
	public String layTenKH(String maKH) throws ClassNotFoundException {
			String tenKH="";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select TENKH from KhachHang WHERE maKH ='"+maKH+"'";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
			rs.next();
			tenKH = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenKH;
	}
	
	public String laySDTKH(String maKH) throws ClassNotFoundException {
		String sDTKH="";
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "Select SDT from KhachHang WHERE maKH ='"+maKH+"'";
		java.sql.Statement statement = con.createStatement();
		ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
		rs.next();
		sDTKH = rs.getString(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return sDTKH;
	}
	
	public String layDiaKH(String maKH) throws ClassNotFoundException {
		String diaChiKH="";
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "Select DIACHI from KHACHHANG WHERE MAKH ='"+maKH+"'";
		java.sql.Statement statement = con.createStatement();
		ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
		rs.next();
		diaChiKH = rs.getString(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return diaChiKH;
	}
	
	public static boolean delete(String maKH) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM KhachHang WHERE MAKH LIKE '"+maKH+"'");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}  
	   public boolean update(KhachHang kh) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE KHACHHANG SET TENKH=?,SDT=?,DIACHI=?,GIOITINH=? WHERE MAKH=?");
			
			
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getDiaChi());
			stmt.setString(3, kh.getSoDT());
			stmt.setString(4, kh.getGioiTinh());
			stmt.setString(5, kh.getMaKH());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public List<KhachHang> getKHTenSDT(String tenKHCanTim,String sdt) throws SQLException, ClassNotFoundException{
		List<KhachHang> dskh = null;
		KhachHang kh;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KHACHHANG where TENKH like N'%"+tenKHCanTim+"%' and SDT like N'%"+sdt+"%'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dskh = new ArrayList<KhachHang>();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sDT = rs.getString(3);
				String diaChi = rs.getString(4);
				String gioiTinh = rs.getString(5);
				kh = new KhachHang(maKH, tenKH,diaChi ,sDT,gioiTinh);
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dskh;
	}
	public KhachHang getKHTheoMa(String maKHCanTim) throws SQLException, ClassNotFoundException{
		KhachHang kh = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KHACHHANG where MAKH like N'"+maKHCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sDT = rs.getString(3);
				String diaChi = rs.getString(4);
				String gioiTinh = rs.getString(5);
				kh = new KhachHang(maKH, tenKH, sDT,  diaChi, gioiTinh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return kh;
	}
	public List<KhachHang> getMaKHTenKD(String tenKHCanTim) throws SQLException, ClassNotFoundException{
		List<KhachHang> ds = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KHACHHANG where TENKH like N'%"+tenKHCanTim+"%'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			ds = new ArrayList<KhachHang>();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sDT = rs.getString(3);
				String diaChi = rs.getString(4);
				String gioiTinh = rs.getString(5);
				KhachHang kh = new KhachHang(maKH, tenKH, sDT,  diaChi, gioiTinh);
				ds.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return ds;
	}

	public Map<KhachHang, Integer> thongkeKH(Date from, Date end) throws ClassNotFoundException, SQLException{
	Map<KhachHang, Integer> dsKH = new HashMap<KhachHang, Integer>();
	try {
		Connection con = ConnectDB.getConnection();
		String sql = "select kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH,COUNT(hd.MAHD) as 'So_luong_hoa_don'\r\n"
				+ " from HOADON hd join KHACHHANG kh on kh.MAKH = hd.MAKH\r\n"
				+ "where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
				+ "group by kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH\r\n"
				+ "order by So_luong_hoa_don desc";
		java.sql.Statement statement =  con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String maKH = rs.getString(1);
			String tenKH = rs.getString(2);
			String soDT = rs.getString(3);
			String diaChi = rs.getString(4);
			String gioiTinh = rs.getString(5);
			KhachHang kh = new KhachHang(maKH, tenKH, soDT, diaChi, gioiTinh);
			int soLuong = rs.getInt(6);
			dsKH.put(kh, soLuong);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		closeConnection();
	}
	return dsKH;
	}
	public Map<KhachHang, Integer> thongkeKHTOP1(Date from, Date end) throws ClassNotFoundException, SQLException{
		Map<KhachHang, Integer> dsKH = new HashMap<KhachHang, Integer>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select top 1 kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH,COUNT(hd.MAHD) as 'So_luong_hoa_don'\r\n"
					+ " from HOADON hd join KHACHHANG kh on kh.MAKH = hd.MAKH\r\n"
					+ "where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "group by kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH\r\n"
					+ "order by So_luong_hoa_don desc";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String soDT = rs.getString(3);
				String diaChi = rs.getString(4);
				String gioiTinh = rs.getString(5);
				KhachHang kh = new KhachHang(maKH, tenKH, soDT, diaChi, gioiTinh);
				int soLuong = rs.getInt(6);
				dsKH.put(kh, soLuong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsKH;
	}
}
