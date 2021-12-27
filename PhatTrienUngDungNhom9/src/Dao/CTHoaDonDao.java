package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connect.ConnectDB;
import Entity.CTHoaDon;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.LoaiSanPham;
import Entity.NhanVien;
import Entity.SanPham;

public class CTHoaDonDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public CTHoaDonDao() {
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

	public CTHoaDon getCTHD(String maHD,String maSP) {
		CTHoaDon cthd = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM CTHOADON where MAHD='" + maHD + "' AND MASP='"+maSP+"'";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				int soLuong = rs.getInt(3);
				int thanhtien = rs.getInt(4);
				SanPhamDao spDao = new SanPhamDao();
				HoaDon hd = new HoaDon(maHD);
				SanPham sp = spDao.getSPTheoID(maSP);
				cthd = new CTHoaDon(sp, hd, soLuong);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cthd;
	}
	
	public List<CTHoaDon> getallCTHD(String maHDCanTim) throws Exception {
		List<CTHoaDon> dsHD = null;
		CTHoaDon cthd;
		SanPhamDao spDao = new SanPhamDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM CTHOADON where MAHD='" + maHDCanTim + "'";
			java.sql.Statement statement = con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				HoaDon hd = new HoaDon(maHD);
				SanPham sp = spDao.getSPTheoID(maSP);
				cthd = new CTHoaDon(sp, hd, soLuong);
				dsHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return dsHD;
	}

	public boolean themCTHD(CTHoaDon dto) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Insert into CTHOADON (MAHD,MASP, SOLUONG,THANHTIEN) values(?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, dto.getHoaDon().getMaHD());
			statement.setString(2, dto.getSanPham().getMaSP());
			statement.setInt(3, dto.getSoLuong());
			statement.setInt(4, dto.getThanhTien());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean checkCTHD(CTHoaDon ctHoaDon) throws Exception {
		CTHoaDonDao ctHoaDonDao = new CTHoaDonDao();
		Connection con = ConnectDB.getConnection();
		int n = 0;
		SanPhamDao dao = new SanPhamDao();
		dao.capNhatSoLuongSP(ctHoaDon.getSanPham().getSoLuong()-ctHoaDon.getSoLuong(), ctHoaDon.getSanPham().getMaSP());
		String sql = "SELECT * FROM CTHOADON where MAHD='" + ctHoaDon.getHoaDon().getMaHD() + "' AND MASP='"
				+ ctHoaDon.getSanPham().getMaSP() + "'";
		java.sql.Statement statement = con.createStatement();
		rs = ((java.sql.Statement) statement).executeQuery(sql);
		while (rs.next()) {
			String maHD = rs.getString(1);
			String maSP = rs.getString(2);
			int soLuong = rs.getInt(3);
			ctHoaDonDao.capNhat((ctHoaDon.getSoLuong() + soLuong), maHD, maSP);
			n++;
		}
		return n > 0;
	}

	public boolean capNhat(int soLuong, String maHD, String maSP) throws Exception {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		SanPhamDao sanPhamDao = new SanPhamDao();
		SanPham pham = sanPhamDao.getSPTheoID(maSP);
		int thanhTien = soLuong * pham.getDonGia();
		try {
			String sql = "Update CTHOADON set SOLUONG=" + soLuong + ",THANHTIEN=" + thanhTien + " where MAHD='" + maHD
					+ "' AND MASP='" + maSP + "'";
			statement = con.prepareStatement(sql);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean delete(String maHD, String maSP) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "DELETE from CTHOADON where MAHD='" + maHD + "' AND MASP='" + maSP + "'";
			statement = con.prepareStatement(sql);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public List<CTHoaDon> getallSP(Date from, Date end) throws Exception{
		List<CTHoaDon> dsHD = null;
		CTHoaDon cthd;
		SanPhamDao spDao = new SanPhamDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',SUM(cthd.THANHTIEN) as 'Thanh_tien'\r\n"
					+ " from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "  group by sp.MASP, TENSP\r\n"
					+ " order by So_Luong desc,'Thanh_tien'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
//				String maSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int thanhTien = rs.getInt(4);
				SanPham sp = spDao.getSPTheoID(maSP);
				cthd = new CTHoaDon(sp, soLuong, thanhTien);
				dsHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsHD;
	}
	public List<CTHoaDon> getallSPTOP1(Date from, Date end) throws Exception{
		List<CTHoaDon> dsHD = null;
		CTHoaDon cthd;
		SanPhamDao spDao = new SanPhamDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select top 1 sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',SUM(cthd.THANHTIEN) as 'Thanh_tien'\r\n"
					+ " from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "  group by sp.MASP, TENSP\r\n"
					+ " order by So_Luong desc,'Thanh_tien'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
//				String maSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int thanhTien = rs.getInt(4);
				SanPham sp = spDao.getSPTheoID(maSP);
				cthd = new CTHoaDon(sp, soLuong, thanhTien);
				dsHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsHD;
	}
//	public List<CTHoaDon> getallSPTOP1(Date from, Date end) throws Exception{
//		List<CTHoaDon> dsHD = null;
//		CTHoaDon cthd;
//		SanPhamDao spDao = new SanPhamDao();
//		try {
//			Connection con = ConnectDB.getConnection();
//			String sql = " select top 1 sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',cthd.THANHTIEN\r\n"
//					+ " from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
//					+ " where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
//					+ "  group by sp.MASP, TENSP,THANHTIEN\r\n"
//					+ " order by So_Luong desc";;
//			java.sql.Statement statement =  con.createStatement();
//			rs = ((java.sql.Statement) statement).executeQuery(sql);
//			dsHD = new ArrayList<>();
//			while (rs.next()) {
//				String maSP = rs.getString(1);
////				String maSP = rs.getString(2);
//				int soLuong = rs.getInt(3);
//				int thanhTien = rs.getInt(4);
//				SanPham sp = spDao.getSPTheoID(maSP);
//				cthd = new CTHoaDon(sp, soLuong, thanhTien);
//				dsHD.add(cthd);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			closeConnection();
//		}
//		return dsHD;
//	}
	public List<CTHoaDon> getallSPTTTOP1(Date from, Date end) throws Exception{
		List<CTHoaDon> dsHD = null;
		CTHoaDon cthd;
		SanPhamDao spDao = new SanPhamDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql =" select top 1 sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',SUM(cthd.THANHTIEN) as'Thanh_tien'\r\n"
					+ " from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "  group by sp.MASP, TENSP\r\n"
					+ "  order by Thanh_tien desc";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsHD = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
//				String maSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				int thanhTien = rs.getInt(4);
				SanPham sp = spDao.getSPTheoID(maSP);
				cthd = new CTHoaDon(sp, soLuong, thanhTien);
				dsHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsHD;
	}
}
