package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import Connect.ConnectDB;
import Entity.NhanVien;
import Entity.TacGia;

public class TaiKhoanDao {
	
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public TaiKhoanDao() {
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
	
	public String checkNV(String userName,String password) {
		String maTG="";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select MANV from TAIKHOAN where TENTK='"+userName+"' and MATKHAU='"+password+"'";
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
	public boolean create(NhanVien nxb) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into TAIKHOAN ( [MANV],[TENTK],[MATKHAU]) values(?, ?, ?)");
			statement.setString(1, nxb.getMaNV());
			statement.setNString(2, nxb.getMaNV());
			statement.setNString(3, "1111");
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n>0;
	}
}
