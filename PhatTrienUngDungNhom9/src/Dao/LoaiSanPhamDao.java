package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDB;
import Entity.LoaiSanPham;

public class LoaiSanPhamDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public LoaiSanPhamDao() {
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
	
	public int demLSP() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MALSP) as 'Tong' from LOAISANPHAM";
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
	
	public String getMaLSPTheoTen(String tenTGCanTim) {
		String maTG="";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MALSP FROM LOAISANPHAM WHERE TENLSP=N'"+tenTGCanTim+"' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				maTG=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maTG;
	}
	
	public List<LoaiSanPham> getAllDM() throws Exception{
		List<LoaiSanPham> dsDM = null ; 
		LoaiSanPham dm ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LOAISANPHAM where [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsDM = new ArrayList<>();
			while (rs.next()) {
				String maDM = rs.getString(1);
				String tenDM = rs.getString(2);
				int loaiDM = rs.getInt(3);
				dm = new LoaiSanPham(maDM, tenDM, loaiDM);
				dsDM.add(dm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsDM;
	}
	
	public LoaiSanPham getDMTheoID(String maDMCanTim) throws Exception{
		LoaiSanPham dm = null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LOAISANPHAM where MALSP='"+maDMCanTim+"' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				
				String maDM = rs.getString(1);
				String tenDM = rs.getString(2);
				int loaiDM = rs.getInt(3);
				dm = new LoaiSanPham(maDM, tenDM, loaiDM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dm;
	}
	
	public LoaiSanPham getDMTheoTen(String tenDMNeed) throws Exception{
		LoaiSanPham dm = null ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LOAISANPHAM where TENLSP=N'"+tenDMNeed+"' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				
				String maDM = rs.getString(1);
				String tenDM = rs.getString(2);
				int loaiDM = rs.getInt(3);
				dm = new LoaiSanPham(maDM, tenDM, loaiDM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dm;
	}
	
	public List<LoaiSanPham> getAllDMCungLoai(int loaiDMCanTim) throws Exception{
		List<LoaiSanPham> dsDM = null ; 
		LoaiSanPham dm ;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LOAISANPHAM where LOAILSP ='"+loaiDMCanTim+"' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsDM = new ArrayList<>();
			while (rs.next()) {
				String maDM = rs.getString(1);
				String tenDM = rs.getString(2);
				int loaiDM = rs.getInt(3);
				dm = new LoaiSanPham(maDM, tenDM, loaiDM);
				dsDM.add(dm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsDM;
	}
	
	public boolean themDM(LoaiSanPham dto) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Insert into LOAISANPHAM(MALSP,TENLSP, LOAILSP,TRANGTHAI) values(?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, dto.getMaDM());
			statement.setString(2, dto.getTenDM());
			statement.setInt(3, dto.isLoaiDM());
			statement.setInt(4, 1);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhat(LoaiSanPham loaiSanPham,String maDM) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update LOAISANPHAM set TENLSP=N'"+loaiSanPham.getTenDM()+"',LOAILSP="+loaiSanPham.isLoaiDM()+" where MALSP='"+maDM+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean delete(String maDM) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update LOAISANPHAM set TRANGTHAI=0 where MALSP='"+maDM+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
