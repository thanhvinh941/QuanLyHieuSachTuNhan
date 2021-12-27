package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDB;
import Entity.LoaiSanPham;
import Entity.DonHang;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;

public class DonHangDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public DonHangDao() {
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
	public List<DonHang> getallDH() throws SQLException, ClassNotFoundException{
		List<DonHang> dsDH = null;
		DonHang dh;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsDH = new ArrayList<>();
			while (rs.next()) {
				String maDM = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				dh = new DonHang(maDM, ngayTao, kh, nv, tongTien, tienMat);
				dsDH.add(dh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsDH;
	}
	
	public DonHang getDHTheoID(String maDHCanTim) throws Exception{
		DonHang dh = null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MAHD='"+maDHCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				
				String maDM = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				dh = new DonHang(maDM, ngayTao, kh, nv, tongTien, tienMat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dh;
	}
	
	public List<DonHang> getallDHTheoKH(String maKHCanTim) throws SQLException, ClassNotFoundException{
		List<DonHang> dsDH = null;
		DonHang dh;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MAKH='"+maKHCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsDH = new ArrayList<>();
			while (rs.next()) {
				String maDM = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				dh = new DonHang(maDM, ngayTao, kh, nv, tongTien, tienMat);
				dsDH.add(dh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsDH;
	}
	
	public List<DonHang> getallDHTheoNV(String maNVCanTim) throws SQLException, ClassNotFoundException{
		List<DonHang> dsDH = null;
		DonHang dh;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM HOADON where MANV='"+maNVCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsDH = new ArrayList<>();
			while (rs.next()) {
				String maDM = rs.getString(1);
				Date ngayTao = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				int tongTien = rs.getInt(5);
				int tienMat = rs.getInt(6);
				NhanVien nv = new NhanVien(maNV);
				KhachHang kh = new KhachHang(maKH);
				dh = new DonHang(maDM, ngayTao, kh, nv, tongTien, tienMat);
				dsDH.add(dh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsDH;
	}
	
	public int demSoDH() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MAHD) as 'Tong' from HOADON Where MAHD like '%DH%'";
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
	
	public boolean themDH(HoaDon dto) throws ClassNotFoundException, SQLException {
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
			statement.setInt(8, 1);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capnhatTongTien(int tongTien, String maDH) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update HOADON set TONGTHANHTIEN="+tongTien+",TIENMAT ="+tongTien+" where MAHD='"+maDH+"'";
			statement = con.prepareStatement(sql);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean capnhatTrangThai(String maDH) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update HOADON set TRANGTHAI="+0+" where MAHD='"+maDH+"'";
			statement = con.prepareStatement(sql);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
