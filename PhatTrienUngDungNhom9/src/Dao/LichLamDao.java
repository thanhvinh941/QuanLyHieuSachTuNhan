package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connect.ConnectDB;
import Entity.Ca;
import Entity.KhachHang;
import Entity.Lich;
import Entity.NhanVien;

public class LichLamDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public LichLamDao() {
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
	
	public ArrayList<Lich> getalltbLich() throws ClassNotFoundException{
		ArrayList<Lich> dsCaLam = new ArrayList<Lich>();
		CaDao caDao = new CaDao();
		NhanVienDao nhanVienDao = new NhanVienDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from LICHLAM";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maCa = rs.getString(1);
				String maNV = rs.getString(2);
				Ca ca = caDao.getCatheoMaCa(maCa);
				NhanVien nhanVien = nhanVienDao.getKHTheoMa(maNV); 
				Date ngaLam = rs.getDate(3);
				
				Lich lichLam = new Lich(nhanVien, ca, ngaLam);
				dsCaLam.add(lichLam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCaLam;
	}
//	public ArrayList<Lich> getLichLam(String txtmaNV,String txtTenNV,String txtSoDT,String txtCaLam,Date ngayLam) throws ClassNotFoundException{
//		ArrayList<Lich> dskh = new ArrayList<Lich>();
//		try {
//			Connection con = ConnectDB.getConnection();
//            String sql = "select ca.MACA,nv.MANV,NGAYLAM from \r\n"
//            		+ "NHANVIEN nv join LICHLAM l on nv.MANV = l.MANV join CA ca on ca.MACA = l.MACA \r\n"
//            		+ "where nv.MANV LIKE '%"+txtmaNV+"%' "
//            		+ "AND nv.TENNV LIKE N'%"+txtTenNV+"%' "
//            		+ "AND SDT LIKE '%"+txtSoDT+"%' "
//            		+ "AND CALAM LIKE N'%"+txtCaLam+"%'\r\n";
////            		+ "AND NGAYLAM LIKE '%"+ngayLam+"%'";
//            java.sql.Statement statement = con.createStatement();
//            ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
//            while (rs.next()) {
//                 String maNV = rs.getString(1);
//                 String maCA = rs.getString(2);
//                 Date ngaLam = rs.getDate(3);
//                 
//                 Lich lich = new Lich(new NhanVien(maNV), new Ca(maCA), ngaLam);
//                
//                 dskh.add(lich);
//            }            
//        } catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return dskh;
//	}
	

	public boolean create(Lich lich) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("insert into LICHLAM (MACA,MANV,NGAYLAM) values(?, ?, ? )");
			
			statement.setString(1, lich.getCa().getMaCa());
			statement.setString(2, lich.getNv().getMaNV());
			statement.setDate(3,lich.getNgayLam());
		

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n>0;
	}
	  public boolean update(Lich lich) throws ClassNotFoundException, SQLException {
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = null;
			int n = 0;
			try {
				statement = con.prepareStatement("update LICHLAM set MACA=?,MANV=? where NGAYLAM=?");
				statement.setString(1, lich.getCa().getMaCa());
				statement.setString(2,lich.getNv().getMaNV());
				statement.setDate(3, lich.getNgayLam());
				n = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return n > 0;
		}
	public static boolean delete(String maCA) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM LICHLAM WHERE MACA like N'"+maCA+"'");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}  
	
}
