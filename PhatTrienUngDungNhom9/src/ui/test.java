package ui;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Connect.ConnectDB;
import Dao.HoaDonDao;
import Dao.SanPhamDao;

public class test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SanPhamDao dao = new SanPhamDao();
		for (Entry<String, Integer> entry : dao.thongkeSanPham(11, 2021).entrySet()) {
			System.out.println(entry.getKey());
		}
	}
}
