package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDB;
import Entity.KhachHang;
import Entity.NhaXuatBan;

public class NhaXuatBanDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public NhaXuatBanDao() {
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

	public String getMaNXBTheoTen(String tenTGCanTim) {
		String maTG = "";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MANXB FROM NHAXUATBAN WHERE TENNXB=N'" + tenTGCanTim + "' and [TRANGTHAI]=1";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				maTG = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maTG;
	}

	public ArrayList<NhaXuatBan> getallNXB() throws ClassNotFoundException {
		ArrayList<NhaXuatBan> dsNXB = new ArrayList<NhaXuatBan>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHAXUATBAN where [TRANGTHAI]=1";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maNXB = rs.getString(1);
				String tenNXB = rs.getString(2);
				String diaChi = rs.getString(3);
				NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, diaChi);
				dsNXB.add(nxb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNXB;
	}

	public NhaXuatBan getNXBTheoID(String maNXBCanTim) throws SQLException, ClassNotFoundException {
		NhaXuatBan nxb = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHAXUATBAN where MANXB='" + maNXBCanTim + "'and [TRANGTHAI]=1";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {

				String maNXB = rs.getString(1);
				String tenNXB = rs.getString(2);
				String diaChi = rs.getString(3);
				nxb = new NhaXuatBan(maNXB, tenNXB, diaChi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return nxb;
	}

	public boolean create(NhaXuatBan nxb) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into NHAXUATBAN values(?, ?, ?, ?)");
			statement.setString(1, nxb.getMaNXB());
			statement.setString(2, nxb.getTenNXB());
			statement.setString(3, nxb.getDiaChi());
			statement.setInt(4, 1);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	public String layTenNXB(String maNXB) throws ClassNotFoundException {
		String tenNXB = "";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select MANXB from NHAXUATBAN WHERE MANXB ='" + maNXB + "' and [TRANGTHAI]=1";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			rs.next();
			tenNXB = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenNXB;
	}

	public int demSoSach() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MANXB) as 'Tong' from NHAXUATBAN";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				soDH = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return soDH;
	}

	public String layDiaNXB(String maNXB) throws ClassNotFoundException {
		String diaChiNXB = "";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select DIACHI from NHAXUATBAN WHERE MANXB ='" + maNXB + "' and [TRANGTHAI]=1";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			rs.next();
			diaChiNXB = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diaChiNXB;
	}

	public boolean update(NhaXuatBan nxb ,String maNXB) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE NHAXUATBAN SET TENNXB=?,DIACHI=? WHERE MANXB=?");

			stmt.setString(1, nxb.getTenNXB());
			stmt.setString(2, nxb.getDiaChi());
			stmt.setString(3, maNXB);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean delete(String maNXB) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE NHAXUATBAN SET TRANGTHAI=0 WHERE MANXB=?");

			stmt.setString(1, maNXB);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;		
	}

}
