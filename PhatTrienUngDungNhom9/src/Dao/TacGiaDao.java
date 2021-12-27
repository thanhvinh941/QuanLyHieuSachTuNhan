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
import Entity.TacGia;

public class TacGiaDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public TacGiaDao() {
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
	
	public boolean create(TacGia nxb) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into TACGIA (MATG,TENTG,QUOCTICH,TRANGTHAI) values(?, ?, ?, ?)");
			statement.setString(1, nxb.getMaTG());
			statement.setNString(2, nxb.getTenTG());
			statement.setNString(3, nxb.getQuocTich());
			statement.setInt(4, 1);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n>0;
	}
	public int demSoSach() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MATG) as 'Tong' from TACGIA";
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
	public List<TacGia> getallTG() throws ClassNotFoundException, SQLException{
		List<TacGia> dsNXB = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM TACGIA where [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsNXB = new ArrayList<>();
			while (rs.next()) {
				String maTG = rs.getString(1);
				String tenTG = rs.getString(2);
				String quocTich = rs.getString(3);
				TacGia tg = new TacGia(maTG, tenTG, quocTich);
				dsNXB.add(tg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsNXB;
	}
	
	public String getMaTGTheoTen(String tenTGCanTim) {
		String maTG="";
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MATG FROM TACGIA WHERE TENTG=N'"+tenTGCanTim+"' and [TRANGTHAI]=1";
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
	
	public ArrayList<TacGia> getTGTheoTenLSP(String tenLSP) {
		ArrayList<TacGia> ds = new ArrayList<>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT MATG, TENTG, QUOCTICH\r\n"
					+ "from TACGIA \r\n"
					+ "Where MATG in (SELECT TACGIA.MATG\r\n"
					+ "FROM      SACH INNER JOIN\r\n"
					+ "                 TACGIA ON SACH.MATG = TACGIA.MATG INNER JOIN\r\n"
					+ "                 SANPHAM ON SACH.MASP = SANPHAM.MASP INNER JOIN\r\n"
					+ "                 LOAISANPHAM ON SANPHAM.MALSP = LOAISANPHAM.MALSP\r\n"
					+ "WHERE TENLSP=N'"+tenLSP+"' AND SANPHAM.TRANGTHAI=1\r\n"
					+ "GROUP BY TACGIA.MATG)";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maTG = rs.getString(1);
				String tenTG = rs.getString(2);
				String quocTich = rs.getString(3);
				TacGia tg = new TacGia(maTG, tenTG, quocTich);
				ds.add(tg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}
	
	public TacGia getTGTheoMaTg(String maTGCanTim) {
		TacGia tg = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM TACGIA WHERE MATG='"+maTGCanTim+"' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maTG = rs.getString(1);
				String tenTG = rs.getString(2);
				String quocTich = rs.getString(3);
				tg = new TacGia(maTG, tenTG, quocTich);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tg;
	}
	
	public boolean update(TacGia nxb ,String maNXB) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE TACGIA SET TENTG=?,QUOCTICH=? WHERE MATG=?");

			stmt.setString(1, nxb.getTenTG());
			stmt.setString(2, nxb.getQuocTich());
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
			stmt = con.prepareStatement("UPDATE TACGIA SET TRANGTHAI=0 WHERE MATG=?");

			stmt.setString(1, maNXB);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;		
	}
}
