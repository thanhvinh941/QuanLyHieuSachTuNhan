package GUI;

import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.CategoryDataset;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.data.category.IntervalCategoryDataset;
//import org.jfree.data.gantt.Task;
//import org.jfree.data.gantt.TaskSeries;
//import org.jfree.data.gantt.TaskSeriesCollection;
//import org.jfree.data.time.SimpleTimePeriod;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import Connect.ConnectDB;
import Dao.CTHoaDonDao;
import Dao.HoaDonDao;
import Entity.CTHoaDon;
import Entity.HoaDon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class FrameThongKeDT extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4892192612269040617L;
	private JTable table;
	private DefaultTableModel tableModel;
	private HoaDonDao hdDao = new HoaDonDao();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private LocalDate fromDay = LocalDate.of(2021, 11, 13);
	private LocalDate endDay = LocalDate.now();
	private JDateChooser dateEnd, dateStart;
	private JLabel lblNewLabel_4,lblNewLabel_4_1;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameThongKeDT frame = new FrameThongKeDT();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FrameThongKeDT() throws ClassNotFoundException, SQLException {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pTKDT = new JPanel();
		pTKDT.setBackground(Color.WHITE);
		pTKDT.setBounds(0, 0, 1186, 632);
		getContentPane().add(pTKDT);
		pTKDT.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 70, 400, 308);
		pTKDT.add(panel);
		panel.setLayout(null);

		JButton btnInThongKe = new JButton("In thống kê");
		btnInThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date dateUtilStart = dateStart.getDate();
				Date dateSQLStart = new Date(dateUtilStart.getTime());
				java.util.Date dateUtilEnd = dateEnd.getDate();
				Date dateSQLEnd = new Date(dateUtilEnd.getTime());
				if (checkNgay(dateSQLStart, dateSQLEnd)) {
					xuatHD(dateSQLStart, dateSQLEnd);
				} else {
					showMessage("Hãy chọn ngày lại đi!!!", dateEnd);
				}
			}
		});
		btnInThongKe.setIcon(new ImageIcon(FrameThongKeDT.class.getResource("/image/btnPrint.png")));
		btnInThongKe.setBounds(216, 248, 150, 40);
		panel.add(btnInThongKe);

		JButton btnXemthongKe = new JButton("Xem thống kê");
		btnXemthongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXemthongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date dateUtilStart = dateStart.getDate();
				Date dateSQLStart = new Date(dateUtilStart.getTime());
				java.util.Date dateUtilEnd = dateEnd.getDate();
				Date dateSQLEnd = new Date(dateUtilEnd.getTime());
				if (checkNgay(dateSQLStart, dateSQLEnd)) {
					try {
						loadHD();
						khoiTaoTop();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} else {
					showMessage("Hãy chọn ngày lại đi!!!", dateEnd);
				}
			}
		});
		btnXemthongKe.setBounds(33, 248, 150, 40);
		panel.add(btnXemthongKe);

		dateEnd = new JDateChooser();
		dateEnd.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateEnd.setDateFormatString("d MMM,y");
		dateEnd.setBounds(60, 158, 280, 30);
		dateEnd.setDate(Date.valueOf(endDay));
		panel.add(dateEnd);

		dateStart = new JDateChooser();
		dateStart.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		dateStart.setDateFormatString("d MMM,y");
		dateStart.setBounds(60, 61, 280, 30);
		dateStart.setDate(Date.valueOf(fromDay));
		panel.add(dateStart);

		JLabel lblNewLabel = new JLabel("Từ ngày :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 30, 100, 20);
		panel.add(lblNewLabel);

		JLabel lblnNgy = new JLabel("Đến ngày :");
		lblnNgy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnNgy.setHorizontalAlignment(SwingConstants.CENTER);
		lblnNgy.setBounds(150, 127, 100, 20);
		panel.add(lblnNgy);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		
		String col[] = { "Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Ngày tạo", "Tổng thành tiền"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				panel_2.setVisible(true);
				int tabSelec = table.getSelectedRow();
				String maHD = (String) table.getValueAt(tabSelec, 0);
				FrameCTHD frame = new FrameCTHD(maHD);
				try {
					frame.khoiTaoHoaDon(maHD);
					frame.setVisible(true);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(25);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(420, 70, 843, 560);
		pTKDT.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Thống kê Doanh thu\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 1253, 40);
		pTKDT.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 389, 400, 241);
		pTKDT.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng doanh thu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 380, 50);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Số lượng hóa đơn bán được:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(10, 116, 250, 30);
		panel_1.add(lblNewLabel_3);
		
		lblNewLabel_4  = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(270, 116, 120, 30);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3_1 = new JLabel("Doanh thu:");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(10, 184, 250, 30);
		panel_1.add(lblNewLabel_3_1);
		
		lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(270, 184, 120, 30);
		panel_1.add(lblNewLabel_4_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.RED);
		panel_2.setBounds(10, 72, 380, 5);
		panel_1.add(panel_2);
	}
	
//	void khoiTaoHoaDon(String maHD) throws Exception {
//		HoaDonDao hdDao= new HoaDonDao();
//		CTHoaDonDao cthdDao = new CTHoaDonDao();
//		HoaDon hd = hdDao.getHDTheoID(maHD);
//		List<CTHoaDon> listCTHD = cthdDao.getallCTHD(maHD);
//		lbltitle.setText("Hóa đơn:"+hd.getMaHD());
//		txtMaHD.setText(hd.getMaHD());txtNgayTao.setText(sdf.format(hd.getNgayTao()));
//		txtMaNV.setText(hd.getNhanVien().getMaNV());txtTenNV.setText(hd.getNhanVien().getTenNV());txtSDTNV.setText(hd.getNhanVien().getsDT());txtChucVu.setText(hd.getNhanVien().getChuVu());
//		txtMaKH.setText(hd.getKhachHang().getMaKH());txtTenKH.setText(hd.getKhachHang().getTenKH());txtSDTKH.setText(hd.getKhachHang().getSoDT());txtDiaChi.setText(hd.getKhachHang().getDiaChi());
//		int tblRow = tableModel1.getRowCount();
//		for (int i = tblRow - 1; i >= 0; i--) {
//			tableModel1.removeRow(i);
//		}
//		for(CTHoaDon cthd : listCTHD) {
//			tableModel1.addRow(new Object[] {
//					cthd.getSanPham().getMaSP(),
//					cthd.getSanPham().getTenSP(),
//					currencyVN.format(cthd.getSanPham().getDonGia()),
//					cthd.getSoLuong(),
//					currencyVN.format(cthd.getThanhTien())
//			});
//		}
//		lblTongThanhTien.setText(currencyVN.format(hd.getTongThanhTien()));
//	}
	
	void loadHD() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		int tblRow = tableModel.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (HoaDon hd : hdDao.getallHD(dateSQLStart, dateSQLEnd)) {
			tableModel.addRow(new Object[] { hd.getMaHD(), hd.getKhachHang().getTenKH(), hd.getNhanVien().getTenNV(),
					sdf.format(hd.getNgayTao()), currencyVN.format(hd.getTongThanhTien())});
		}
	}

	boolean checkNgay(Date from, Date end) {
		if (end.compareTo(from) > 0 && from.compareTo(end) < 0 && from.compareTo(Date.valueOf(endDay)) < 0)
			return true;
		return false;
	}

	void khoiTaoTop() throws ClassNotFoundException, SQLException {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		int doanhThu = hdDao.thongkeDoanhThu(dateSQLStart, dateSQLEnd);
		int soLuong = hdDao.thongkeSLDH(dateSQLStart, dateSQLEnd);
		lblNewLabel_4_1.setText(currencyVN.format(doanhThu));lblNewLabel_4.setText(String.valueOf(soLuong));
	}
	
	public void xuatHD(Date from, Date end) {
		try {
			String sql = "SELECT \"MAHD\",\r\n" + "	\"NGAYTAO\",\r\n" + "	\"MANV\",\r\n" + "	\"MAKH\",\r\n"
					+ "	\"TONGTHANHTIEN\",\r\n" + "	\"TIENMAT\",\r\n" + "	\"TIENDU\"\r\n" + "FROM \"HOADON\"\r\n"
					+ "WHERE \r\n" + "	 \"HOADON\".\"TRANGTHAI\" = '0' \r\n"
					+ "	 AND \"HOADON\".\"NGAYTAO\" BETWEEN '" + from.toString() + "' AND '" + end.toString() + "' order by [NGAYTAO]";

			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/ThongKeRepoter.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			Map data = new HashMap();
			JasperPrint p = JasperFillManager.fillReport(jasperReport, data, ConnectDB.getConnection());
			JasperViewer Jviewer = new JasperViewer(p, false);
			Jviewer.setFitPageZoomRatio();
			Jviewer.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void showMessage(String message, JDateChooser txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
