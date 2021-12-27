package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDB;
import Entity.NhaCungCap;
import Entity.SanPham;
import Entity.DungCuHocTap;

public class DungCuHocTapDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public DungCuHocTapDao() {
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
	
	public int demSoDCHT() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MASP) as 'Tong' from DUNGCUHOCTAP";
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
	
	public List<DungCuHocTap> getallSP() throws Exception{
		List<DungCuHocTap> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM DUNGCUHOCTAP dc join SANPHAM s on s.MASP=dc.MASP where TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maNXB = rs.getString(2);
				NhaCungCapDao nccDao = new NhaCungCapDao();
				NhaCungCap ncc = nccDao.getNCCTheoID(maNXB);
				SanPhamDao spDao = new SanPhamDao();
				SanPham sp = spDao.getSPTheoID(maSP);
				DungCuHocTap s = new DungCuHocTap(maSP, sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), ncc);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<DungCuHocTap> getallSPTheoTen(String tenSachCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		DungCuHocTap s = null;
		List<DungCuHocTap> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,[MALSP]\r\n"
					+ "      ,[MANCC]\r\n"
					+ "   FROM [DUNGCUHOCTAP] s join SANPHAM sp on s.MASP = sp.MASP\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   MANCC like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   MALSP like '%"+maLCanTim+"%' and TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maNCC = rs.getString(6);
				SanPhamDao spDao = new SanPhamDao();
				NhaCungCapDao nccDao = new NhaCungCapDao();
				NhaCungCap ncc = nccDao.getNCCTheoID(maNCC);
				SanPham sp = spDao.getSPTheoID(maSP);
				s = new DungCuHocTap(sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), ncc);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<DungCuHocTap> getSPDGBe(String tenSachCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		DungCuHocTap s = null;
		List<DungCuHocTap> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,[MALSP]\r\n"
					+ "      ,[MANCC]\r\n"
					+ "   FROM [DUNGCUHOCTAP] s join SANPHAM sp on s.MASP = sp.MASP\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   DONGIA < 70000 AND\r\n"
					+ "   MANCC like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   MALSP like '%"+maLCanTim+"%'  and TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maNCC = rs.getString(6);
				SanPhamDao spDao = new SanPhamDao();
				NhaCungCapDao nccDao = new NhaCungCapDao();
				NhaCungCap ncc = nccDao.getNCCTheoID(maNCC);
				SanPham sp = spDao.getSPTheoID(maSP);
				s = new DungCuHocTap(sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), ncc);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<DungCuHocTap> getSPDGLon(String tenSachCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		DungCuHocTap s = null;
		List<DungCuHocTap> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,[MALSP]\r\n"
					+ "      ,[MANCC]\r\n"
					+ "   FROM [DUNGCUHOCTAP] s join SANPHAM sp on s.MASP = sp.MASP\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   DONGIA > 300000 AND\r\n"
					+ "   MANCC like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   MALSP like '%"+maLCanTim+"%'  and TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maNCC = rs.getString(6);
				SanPhamDao spDao = new SanPhamDao();
				NhaCungCapDao nccDao = new NhaCungCapDao();
				NhaCungCap ncc = nccDao.getNCCTheoID(maNCC);
				SanPham sp = spDao.getSPTheoID(maSP);
				s = new DungCuHocTap(sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), ncc);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<DungCuHocTap> getSPDGTu(String tenSachCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		DungCuHocTap s = null;
		List<DungCuHocTap> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,[MALSP]\r\n"
					+ "      ,[MANCC]\r\n"
					+ "   FROM [DUNGCUHOCTAP] s join SANPHAM sp on s.MASP = sp.MASP\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   DONGIA > 70000 AND DONGIA <300000 AND \r\n"
					+ "   MANCC like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   MALSP like '%"+maLCanTim+"%'  and TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maNCC = rs.getString(6);
				SanPhamDao spDao = new SanPhamDao();
				NhaCungCapDao nccDao = new NhaCungCapDao();
				NhaCungCap ncc = nccDao.getNCCTheoID(maNCC);
				SanPham sp = spDao.getSPTheoID(maSP);
				s = new DungCuHocTap(sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), ncc);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	
	public DungCuHocTap getSPTheoID(String maSPCanTim) throws Exception{
		DungCuHocTap s = null;
		SanPhamDao spDao = new SanPhamDao();
		NhaCungCapDao nccDao = new NhaCungCapDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  Select * from DUNGCUHOCTAP dc join SANPHAM s on s.MASP=dc.MASP where dc.MASP='"+maSPCanTim+"'  and TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String maSP = rs.getString(1);
				String maNCC = rs.getString(2);
				NhaCungCap ncc = nccDao.getNCCTheoID(maNCC);
				SanPham sp = spDao.getSPTheoID(maSP);
				s = new DungCuHocTap(sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return s;
	}
	public boolean themSPK(DungCuHocTap dto) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			SanPhamDao spDao = new SanPhamDao();
			spDao.themSP(dto);
			String sql = "Insert into DUNGCUHOCTAP (MASP, MANCC) values(?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, dto.getMaSP());
			statement.setString(2, dto.getNhaCungCap().getMaNCC());
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capnhatNCCSPK(DungCuHocTap dungCu, String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			SanPhamDao spDao = new SanPhamDao();
			spDao.capNhat(dungCu, maSP);
			String sql = "Update DUNGCUHOCTAP set MANCC=N'"+dungCu.getNhaCungCap().getMaNCC()+"' where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
