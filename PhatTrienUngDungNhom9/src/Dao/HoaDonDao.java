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
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;

public class HoaDonDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public HoaDonDao() {
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
	}
	public int demSoHD() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MAHD) as 'Tong' from HOADON Where MAHD like '%HD%'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				soDH = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return soDH;
	}
	
	public List<HoaDon> getallHD(Date from, Date end) throws Exception{
		List<HoaDon> dsHD = null;
		HoaDon hd;
		NhanVienDao nvDao = new NhanVienDao();
		KhachHangDao khDao = new KhachHangDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where TRANGTHAI=0 and NGAYTAO BETWEEN '"+from.toString()+"' AND '"+end.toString()+"'  order by [NGAYTAO]";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = nvDao.getNVTheoID(maNV);
				KhachHang kh = khDao.getKHTheoMa(maKH);
				hd = new HoaDon(maHD, ngayTao, kh, nv, tongTien, tienMat);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsHD;
	}
	
	public HoaDon getHDTheoID(String maHDCanTim) throws Exception{
		HoaDon hd = null ;
		NhanVienDao nvDao = new NhanVienDao();
		KhachHangDao khDao = new KhachHangDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MAHD='"+maHDCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = nvDao.getNVTheoID(maNV);
				KhachHang kh = khDao.getKHTheoMa(maKH);
				hd = new HoaDon(maHD, ngayTao, kh, nv, tongTien, tienMat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return hd;
	}
	
	public List<HoaDon> getallHDTheoKH(String maKHCanTim) throws SQLException, ClassNotFoundException{
		List<HoaDon> dsHD = null;
		HoaDon hd;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MAKH='"+maKHCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				hd = new HoaDon(maHD, ngayTao, kh, nv, tongTien, tienMat);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsHD;
	}
	
	public List<HoaDon> getallHDTheoNV(String maNVCanTim) throws SQLException, ClassNotFoundException{
		List<HoaDon> dsHD = null;
		HoaDon HD;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MANV='"+maNVCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				HD = new HoaDon(maHD, ngayTao, kh, nv, tongTien, tienMat);
				dsHD.add(HD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsHD;
	}
	
	public int thongkeDoanhThu(Date from,Date and) throws ClassNotFoundException, SQLException{
		int tongtien=0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select SUM(TONGTHANHTIEN)\r\n"
					+ " from HOADON \r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' AND '"+and.toString()+"'  and TRANGTHAI =0";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				tongtien = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return tongtien;
	}
	
	public int thongkeSLDH(Date from,Date and) throws ClassNotFoundException, SQLException{
		int tongtien=0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select Count(MAHD)\r\n"
					+ " from HOADON \r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' AND '"+and.toString()+"'  and TRANGTHAI =0";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				tongtien = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return tongtien;
	}
	
	public boolean themHD(HoaDon dto) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Insert into HOADON (MAHD,NGAYTAO, MAKH, MANV, TONGTHANHTIEN, TIENMAT, TIENDU, TRANGTHAI) values(?,?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, dto.getMaHD());
			statement.setDate(2, dto.getNgayTao());
			statement.setString(3, dto.getKhachHang().getMaKH());
			statement.setString(4, dto.getNhanVien().getMaNV());
			statement.setInt(5, dto.getTongThanhTien());
			statement.setInt(6, dto.getTienMat());
			statement.setInt(7, dto.getTienDu());
			statement.setInt(8, 0);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
