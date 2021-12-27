package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDB;
import Entity.NhaCungCap;
import Entity.NhaXuatBan;

public class NhaCungCapDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	
	public NhaCungCapDao() {
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
	
	public List<NhaCungCap> getallNCC() throws ClassNotFoundException, SQLException{
		List<NhaCungCap> dsNCC = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NHACUNGCAP where [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsNCC = new ArrayList<>();
			while (rs.next()) {
				String maNXB = rs.getString(1);
				String tenNXB = rs.getString(2);
				String diaChi = rs.getString(3);
				String quocTich = rs.getString(4);
				NhaCungCap ncc = new NhaCungCap(maNXB, tenNXB, diaChi, quocTich);
				dsNCC.add(ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsNCC;
	}
	
	public NhaCungCap getNCCTheoID(String maNCCCanTim) throws ClassNotFoundException, SQLException {
		NhaCungCap ncc = null;
		try {
			Connection con = ConnectDB.getConnection(); 
			String sql = "SELECT * FROM NHACUNGCAP where MANCC='"+maNCCCanTim+"' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String diaChi = rs.getString(3);
				String quocTich = rs.getString(3);
				ncc = new NhaCungCap(maNCC, tenNCC, diaChi, quocTich);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return ncc;
	}

	public String getMaNCCTheoTen(String tenTGCanTim) {
		String maTG="";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MANCC FROM NHACUNGCAP WHERE TENNCC=N'"+tenTGCanTim+"' and [TRANGTHAI]=1";
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
	
	public int demSoSach() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MANCC) as 'Tong' from NHACUNGCAP";
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
	
	public boolean create(NhaCungCap nxb) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into NHACUNGCAP(MANCC,TENNCC,DIACHI,QUOCTICH,TRANGTHAI) values(?, ?, ?, ?, ?)");
			statement.setString(1, nxb.getMaNCC());
			statement.setNString(2, nxb.getTenNCC());
			statement.setNString(3, nxb.getDiaChi());
			statement.setNString(4, nxb.getQuocTich());
			statement.setInt(5, 1);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n>0;
	}
	
	public boolean update(NhaCungCap nxb ,String maNXB) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE NHACUNGCAP SET TENNCC=?,DIACHI=?,QUOCTICH=? WHERE MANCC=?");

			stmt.setString(1, nxb.getTenNCC());
			stmt.setString(2, nxb.getDiaChi());
			stmt.setString(3, nxb.getQuocTich());
			stmt.setString(4, maNXB);
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
			stmt = con.prepareStatement("UPDATE NHACUNGCAP SET TRANGTHAI=0 WHERE MANCC=?");

			stmt.setString(1, maNXB);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;		
	}
}
