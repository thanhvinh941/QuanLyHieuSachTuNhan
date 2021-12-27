package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDB;
import Entity.NhaXuatBan;
import Entity.SanPham;
import Entity.TacGia;
import Entity.Sach;

public class SachDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public SachDao() {
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
	
	public List<Sach> getallSP() throws Exception{
		List<Sach> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM SACH dc join SANPHAM s on s.MASP=dc.MASP join LOAISANPHAM lsp on lsp.MALSP = s.MALSP join NHAXUATBAN nxb on nxb.MANXB = dc.MANXB join TACGIA tg on tg.MATG = dc.MATG where s.TRANGTHAI=1 and lsp.TRANGTHAI=1 and nxb.TRANGTHAI=1 and tg.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tacGia = rs.getString(2);
				int soTrang = rs.getInt(3);
				String maNXB = rs.getString(4);
				NhaXuatBanDao nxbDao = new NhaXuatBanDao();
				NhaXuatBan nxb = nxbDao.getNXBTheoID(maNXB);
				SanPhamDao spDao = new SanPhamDao();
				SanPham sp = spDao.getSPTheoID(maSP);
				TacGiaDao tacGiaDao = new TacGiaDao();
				TacGia gia = tacGiaDao.getTGTheoMaTg(tacGia);
				Sach s = new Sach(maSP, sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), gia, soTrang, nxb);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<Sach> getallSPTheoTen(String tenSachCanTim,String maTGCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		List<Sach> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,sp.[MALSP]\r\n"
					+ "      ,s.[MATG]\r\n"
					+ "      ,[SOTRANG]\r\n"
					+ "      ,s.[MANXB]\r\n"
					+ "   FROM [SACH] s join SANPHAM sp on s.MASP = sp.MASP join LOAISANPHAM lsp on lsp.MALSP = sp.MALSP join NHAXUATBAN nxb on nxb.MANXB = s.MANXB join TACGIA tg on tg.MATG = s.MATG\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   s.MATG like '%"+maTGCanTim+"%' AND \r\n"
					+ "   s.MANXB like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   sp.MALSP like '%"+maLCanTim+"%' and sp.TRANGTHAI=1 and lsp.TRANGTHAI=1 and nxb.TRANGTHAI=1 and tg.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maTG = rs.getString(6);
				int soTrang = rs.getInt(7);
				String maNXB = rs.getString(8);
				SanPhamDao spDao = new SanPhamDao();
				SanPham sp = spDao.getSPTheoID(maSP);
				NhaXuatBanDao nxbDao = new NhaXuatBanDao();
				NhaXuatBan nxb = nxbDao.getNXBTheoID(maNXB);
				TacGiaDao tacGiaDao = new TacGiaDao();
				TacGia gia = tacGiaDao.getTGTheoMaTg(maTG);
				Sach s = new Sach(maSP, sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), gia, soTrang, nxb);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<Sach> getSPDGBe(String tenSachCanTim,String maTGCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		List<Sach> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,sp.[MALSP]\r\n"
					+ "      ,s.[MATG]\r\n"
					+ "      ,[SOTRANG]\r\n"
					+ "      ,s.[MANXB]\r\n"
					+ "   FROM [SACH] s join SANPHAM sp on s.MASP = sp.MASP join LOAISANPHAM lsp on lsp.MALSP = sp.MALSP join NHAXUATBAN nxb on nxb.MANXB = s.MANXB join TACGIA tg on tg.MATG = s.MATG\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   DONGIA < 70000 AND\r\n"
					+ "   s.MATG like '%"+maTGCanTim+"%' AND \r\n"
					+ "   s.MANXB like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   sp.MALSP like '%"+maLCanTim+"%' and sp.TRANGTHAI=1 and lsp.TRANGTHAI=1 and nxb.TRANGTHAI=1 and tg.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maTG = rs.getString(6);
				int soTrang = rs.getInt(7);
				String maNXB = rs.getString(8);
				SanPhamDao spDao = new SanPhamDao();
				SanPham sp = spDao.getSPTheoID(maSP);
				NhaXuatBanDao nxbDao = new NhaXuatBanDao();
				NhaXuatBan nxb = nxbDao.getNXBTheoID(maNXB);
				TacGiaDao tacGiaDao = new TacGiaDao();
				TacGia gia = tacGiaDao.getTGTheoMaTg(maTG);
				Sach s = new Sach(maSP, sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), gia, soTrang, nxb);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<Sach> getSPDGLon(String tenSachCanTim,String maTGCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		List<Sach> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,sp.[MALSP]\r\n"
					+ "      ,s.[MATG]\r\n"
					+ "      ,[SOTRANG]\r\n"
					+ "      ,s.[MANXB]\r\n"
					+ "   FROM [SACH] s join SANPHAM sp on s.MASP = sp.MASP join LOAISANPHAM lsp on lsp.MALSP = sp.MALSP join NHAXUATBAN nxb on nxb.MANXB = s.MANXB join TACGIA tg on tg.MATG = s.MATG\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   DONGIA > 300000 AND\r\n"
					+ "   s.MATG like '%"+maTGCanTim+"%' AND \r\n"
					+ "   s.MANXB like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   sp.MALSP like '%"+maLCanTim+"%' and sp.TRANGTHAI=1 and lsp.TRANGTHAI=1 and nxb.TRANGTHAI=1 and tg.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maTG = rs.getString(6);
				int soTrang = rs.getInt(7);
				String maNXB = rs.getString(8);
				SanPhamDao spDao = new SanPhamDao();
				SanPham sp = spDao.getSPTheoID(maSP);
				NhaXuatBanDao nxbDao = new NhaXuatBanDao();
				NhaXuatBan nxb = nxbDao.getNXBTheoID(maNXB);
				TacGiaDao tacGiaDao = new TacGiaDao();
				TacGia gia = tacGiaDao.getTGTheoMaTg(maTG);
				Sach s = new Sach(maSP, sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), gia, soTrang, nxb);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public List<Sach> getSPDGTu(String tenSachCanTim,String maTGCanTim,String maLCanTim,String maNXBCanTim) throws Exception{
		List<Sach> dsSPS = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  SELECT s.[MASP]\r\n"
					+ "      ,[TENSP]\r\n"
					+ "      ,[DONGIA]\r\n"
					+ "      ,[SOLUONG]\r\n"
					+ "      ,sp.[MALSP]\r\n"
					+ "      ,s.[MATG]\r\n"
					+ "      ,[SOTRANG]\r\n"
					+ "      ,s.[MANXB]\r\n"
					+ "   FROM [SACH] s join SANPHAM sp on s.MASP = sp.MASP join LOAISANPHAM lsp on lsp.MALSP = sp.MALSP join NHAXUATBAN nxb on nxb.MANXB = s.MANXB join TACGIA tg on tg.MATG = s.MATG\r\n"
					+ "   WHERE TENSP like N'%"+tenSachCanTim+"%' AND \r\n"
					+ "   DONGIA >= 70000 AND DONGIA <= 300000 AND \r\n"
					+ "   s.MATG like '%"+maTGCanTim+"%' AND \r\n"
					+ "   s.MANXB like '%"+maNXBCanTim+"%' AND \r\n"
					+ "   sp.MALSP like '%"+maLCanTim+"%' and sp.TRANGTHAI=1 and lsp.TRANGTHAI=1 and nxb.TRANGTHAI=1 and tg.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSPS = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String maTG = rs.getString(6);
				int soTrang = rs.getInt(7);
				String maNXB = rs.getString(8);
				SanPhamDao spDao = new SanPhamDao();
				SanPham sp = spDao.getSPTheoID(maSP);
				NhaXuatBanDao nxbDao = new NhaXuatBanDao();
				NhaXuatBan nxb = nxbDao.getNXBTheoID(maNXB);
				TacGiaDao tacGiaDao = new TacGiaDao();
				TacGia gia = tacGiaDao.getTGTheoMaTg(maTG);
				Sach s = new Sach(maSP, sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), gia, soTrang, nxb);
				dsSPS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSPS;
	}
	
	public Sach getSPTheoID(String maSPCanTim) throws Exception{
		Sach s = null;
		SanPhamDao spDao = new SanPhamDao();
		NhaXuatBanDao nxbDao = new NhaXuatBanDao(); 
		TacGiaDao tacGiaDao = new TacGiaDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  Select * from SACH dc join SANPHAM s on s.MASP=dc.MASP where TRANGTHAI=1 and dc.MASP='"+maSPCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				String tacGia = rs.getString(2);
				int soTrang = rs.getInt(3);
				String maNXB = rs.getString(4);
				NhaXuatBan nxb = nxbDao.getNXBTheoID(maNXB);
				SanPham sp = spDao.getSPTheoID(maSPCanTim);
				TacGia gia = tacGiaDao.getTGTheoMaTg(tacGia);
				s = new Sach(sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getDanhMuc(), gia, soTrang, nxb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return s;
	}
	
	public boolean themSPS(Sach dto) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			SanPhamDao spDao = new SanPhamDao();
			spDao.themSP(dto);
			String sql = "Insert into SACH(MASP,MATG, SOTRANG, MANXB) values(?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, dto.getMaSP());
			statement.setString(2, dto.getTacGia().getMaTG());
			statement.setInt(3, dto.getSoTrang());
			statement.setString(4, dto.getNhaXuatBan().getMaNXB());
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capnhat(Sach sach, String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		SanPhamDao spDao = new SanPhamDao();
		spDao.capNhat(sach, maSP);
		try {
			String sql = "Update SACH set MATG=N'"+sach.getTacGia().getMaTG()+"',SOTRANG=N'"+sach.getSoTrang()+"',MANXB=N'"+sach.getNhaXuatBan().getMaNXB()+"' where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public int demSoSach() throws SQLException, ClassNotFoundException {
		int soDH = 0;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select COUNT(MASP) as 'Tong' from SACH";
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
}
