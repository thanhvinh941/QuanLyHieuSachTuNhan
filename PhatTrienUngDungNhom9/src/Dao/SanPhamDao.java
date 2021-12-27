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
import java.util.TreeMap;

import Connect.ConnectDB;
import Entity.LoaiSanPham;
import Entity.SanPham;

public class SanPhamDao {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;
	public SanPhamDao() {
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
	
	public List<SanPham> getallSP() throws Exception{
		List<SanPham> dsSP = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM SANPHAM where [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSP = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maDm = rs.getString(5);
				LoaiSanPham dm = new LoaiSanPham(maDm);
				SanPham s = new SanPham(maSP, tenSP, donGia, soLuong, dm);
				dsSP.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSP;
	}
	
	public List<String> getallSPTheoLoaiDM(String loaiDMCanTim) throws Exception{
		List<String> dsSP = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " Select [TENSP] FROM [SANPHAM] sp join LOAISANPHAM lsp on sp.MALSP = lsp.MALSP Where lsp.TENLSP=N'"+loaiDMCanTim+"' and sp.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSP = new ArrayList<>();
			while (rs.next()) {
				String tenSP = rs.getString(1);
				dsSP.add(tenSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSP;
	}
	
	public List<String> getallSPTheoTenLSPTenTG(String loaiDMCanTim,String tenTGCanTim) throws Exception{
		List<String> dsSP = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT  SANPHAM.TENSP\r\n"
					+ "FROM      SACH INNER JOIN\r\n"
					+ "                 SANPHAM ON SACH.MASP = SANPHAM.MASP INNER JOIN\r\n"
					+ "                 TACGIA ON SACH.MATG = TACGIA.MATG INNER JOIN\r\n"
					+ "                 LOAISANPHAM ON SANPHAM.MALSP = LOAISANPHAM.MALSP\r\n"
					+ "WHERE TENLSP=N'"+loaiDMCanTim+"' AND TENTG=N'"+tenTGCanTim+"' AND SANPHAM.TRANGTHAI=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSP = new ArrayList<>();
			while (rs.next()) {
				String tenSP = rs.getString(1);
				System.out.println(tenSP);
				dsSP.add(tenSP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSP;
	}
	
	public SanPham getMaSPTenSP(String tenSPCanTim) throws Exception{
		SanPham ds = null ;
		LoaiSanPhamDao lspDao = new LoaiSanPhamDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "select * from SANPHAM where TENSP like N'%"+tenSPCanTim+"%' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maLSP = rs.getString(5);
				LoaiSanPham lsp = lspDao.getDMTheoID(maLSP);
				ds = new SanPham(maSP, tenSP, donGia, soLuong, lsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return ds;
	}
	
	public TreeMap<String, Integer> thongkeSanPham(int month,int year) throws ClassNotFoundException, SQLException{
		TreeMap<String, Integer> dsThongKe = new TreeMap<String,Integer>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select sp.TENSP,SUM(cthd.THANHTIEN) as 'So Luong'\r\n"
					+ "  from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ "  where MONTH(NGAYTAO)="+month+" and YEAR(NGAYTAO)="+year+" and sp.TRANGTHAI=1\r\n"
					+ "  group by TENSP";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String tenSP = rs.getString(1);
				int tongtien = rs.getInt(2);
				dsThongKe.put(tenSP, tongtien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsThongKe;
	}
	
	public TreeMap<String, Integer> thongkeSanPhamTOP1(int month,int year) throws ClassNotFoundException, SQLException{
		TreeMap<String, Integer> dsThongKe = new TreeMap<String,Integer>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select top 1 sp.MASP,SUM(cthd.THANHTIEN) as 'Thanh_Tien'\r\n"
					+ "  from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ "  where MONTH(NGAYTAO)="+month+" and YEAR(NGAYTAO)="+year+" and sp.[TRANGTHAI]=1\r\n"
					+ "  group by sp.MASP"
					+ "  order by Thanh_Tien desc";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String tenSP = rs.getString(1);
				int tongtien = rs.getInt(2);
				dsThongKe.put(tenSP, tongtien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsThongKe;
	}
	
	public TreeMap<String, Integer> thongkeSanPham1(int month,int year) throws ClassNotFoundException, SQLException{
		TreeMap<String, Integer> dsThongKe = new TreeMap<String,Integer>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select top 5 sp.TENSP,SUM(cthd.THANHTIEN) as 'Thanh_Tien'\r\n"
					+ "  from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ "  where MONTH(NGAYTAO)="+month+" and YEAR(NGAYTAO)="+year+" and sp.[TRANGTHAI]=1\r\n"
					+ "  group by sp.TENSP"
					+ "  order by Thanh_Tien desc";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				String tenSP = rs.getString(1);
				int tongtien = rs.getInt(2);
				dsThongKe.put(tenSP, tongtien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsThongKe;
	}
	
	public SanPham getSPTheoTen(String tenSPCanTim) throws Exception{
		SanPham s = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  Select * from SANPHAM where TENSP=N'"+tenSPCanTim+"' and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maDm = rs.getString(5);
				LoaiSanPham dm = new LoaiSanPham(maDm);
				s = new SanPham(maSP, tenSP, donGia, soLuong, dm);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return s;
	}
	
	public SanPham getSPTheoID(String maSPCanTim) throws Exception{
		SanPham s = null;
		LoaiSanPhamDao dmDao = new LoaiSanPhamDao();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  Select * from SANPHAM where MASP='"+maSPCanTim+"'";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while(rs.next()) {
				
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maDm = rs.getString(5);
				LoaiSanPham dm = dmDao.getDMTheoID(maDm);
				s = new SanPham(maSP, tenSP, donGia, soLuong, dm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return s;
	}
	
	public List<SanPham> getSPTheoTenVaCacSPCungLoai(String tenSPCanTim) throws Exception{
		List<SanPham> dsSP = null;
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  Select * from SANPHAM where MALSP = (Select MALSP from SANPHAM where TENSP=N'"+tenSPCanTim+"') and [TRANGTHAI]=1";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			dsSP = new ArrayList<>();
			while(rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				int donGia = rs.getInt(3);
				int soLuong = rs.getInt(4);
				String maDm = rs.getString(5);
				LoaiSanPham dm = new LoaiSanPham(maDm);
				SanPham s = new SanPham(maSP, tenSP, donGia, soLuong, dm);
				dsSP.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return dsSP;
	}
	
	public boolean themSP(SanPham dto) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Insert into SANPHAM(MASP,TENSP, DONGIA, SOLUONG, MALSP,TRANGTHAI) values(?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, dto.getMaSP());
			statement.setString(2, dto.getTenSP());
			statement.setInt(3, dto.getDonGia());
			statement.setInt(4, dto.getSoLuong());
			statement.setString(5, dto.getDanhMuc().getMaDM());
			statement.setInt(6, 1);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhat(SanPham sp,String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update SANPHAM set TENSP=N'"+sp.getTenSP()+"',DONGIA="+sp.getDonGia()+",SOLUONG="+sp.getSoLuong()+",MALSP=N'"+sp.getDanhMuc().getMaDM()+"' where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatDonGiaSP(Double giaSPNew,String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update SANPHAM set DONGIA="+giaSPNew+" where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean capNhatSoLuongSP(int soLuongSPNew,String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update SANPHAM set SOLUONG="+soLuongSPNew+" where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean delete(String maSP) throws ClassNotFoundException, SQLException {
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "Update SANPHAM set TRANGTHAI=0 where MASP='"+maSP+"'";
			statement = con.prepareStatement(sql);

			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
