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
import Entity.Ca;
import Entity.KhachHang;
import Entity.Lich;
import Entity.NhanVien;


public class CaDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public CaDao() {
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
	public ArrayList<Ca> getalltbCa() throws ClassNotFoundException {
		ArrayList<Ca> dsCA = new ArrayList<Ca>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from CA";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maCA = rs.getString(1);
				String caLam = rs.getString(2);

				Ca ca = new Ca(maCA, caLam);
				dsCA.add(ca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCA;
	}
	public Ca getCatheoMaCa(String maCaCanTIm) throws SQLException, ClassNotFoundException {
		Ca kh = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select * from CA where MACA like N'"+maCaCanTIm+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maCa = rs.getString(1);
				String caLam = rs.getString(2);
	
				kh = new Ca(maCa, caLam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return kh;
	}
	public String getMaCaTheoTen(String maCaCanTim) {
		String maCA = "";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select MACA from CA WHERE CALAM=N'"+maCaCanTim+"'";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				maCA=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maCA;
	}
	
}
