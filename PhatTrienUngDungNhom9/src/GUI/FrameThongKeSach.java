package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.toedter.calendar.JDateChooser;

import Connect.ConnectDB;
import Dao.CTHoaDonDao;
import Dao.HoaDonDao;
import Dao.SanPhamDao;
import Entity.CTHoaDon;
import Entity.SanPham;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class FrameThongKeSach extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private static final long serialVersionUID = -4892192612269040617L;
	private JTable table;
	private DefaultTableModel tableModel;
	private CTHoaDonDao ctHDDao = new CTHoaDonDao();
	private HoaDonDao hdDao = new HoaDonDao();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private LocalDate fromDay = LocalDate.of(2021, 11, 13);
	private LocalDate endDay = LocalDate.now();
	private JDateChooser dateEnd, dateStart;
	private JComboBox cmbChon;
	private JTextField txtSLSPDB;
	private JTextField txtTong;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameThongKeSP frame = new FrameThongKeSP();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FrameThongKeSach() throws Exception {
		cmbChon = new JComboBox<String>();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pTKDT = new JPanel();
		pTKDT.setBounds(0, 0, 1186, 632);
		getContentPane().add(pTKDT);
		pTKDT.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 61, 1253, 52);
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
				String chon = (String) cmbChon.getSelectedItem();
				if (checkNgay(dateSQLStart, dateSQLEnd)) {
					if(chon =="Sản phẩm đã bán") {
						xuatHD(dateSQLStart, dateSQLEnd);
					}else if(chon == "Sản phẩm bán nhiều nhất") {
						xuatHDTOP1(dateSQLStart, dateSQLEnd);
					}else {
						xuatHDSPCDTTOP1(dateSQLStart, dateSQLEnd);
					}
				} else {
					showMessage("Hãy chọn ngày lại đi!!!", dateEnd);
				}
			}
		});
		btnInThongKe.setIcon(new ImageIcon(FrameThongKeDT.class.getResource("/image/btnPrint.png")));
		btnInThongKe.setBounds(1077, 6, 150, 40);
		panel.add(btnInThongKe);

		JButton btnXemthongKe = new JButton("Xem thống kê");
		btnXemthongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXemthongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date dateUtilStart = dateStart.getDate();
				Date dateSQLStart = new Date(dateUtilStart.getTime());
				java.util.Date dateUtilEnd = dateEnd.getDate();
				Date dateSQLEnd = new Date(dateUtilEnd.getTime());
				String chon = (String) cmbChon.getSelectedItem();
				if (checkNgay(dateSQLStart, dateSQLEnd)) {
					try {
						if(chon =="Sản phẩm đã bán") {
								loadSPBC();
								loadTONGSOSP();
						}
						else if(chon == "Sản phẩm bán nhiều nhất") {
								loadSPBCTOP1();
								loadTONGSOSPTOP1();
						}else {
							loadSPBCDTTOP1();
							loadTONGSOSPCODTTOP1();
						}
					} catch (ClassNotFoundException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else {
					showMessage("Hãy chọn ngày lại đi!!!", dateEnd);
				}
			}
		});
		btnXemthongKe.setBounds(903, 6, 150, 40);
		panel.add(btnXemthongKe);

		dateEnd = new JDateChooser();
		dateEnd.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateEnd.setDateFormatString("d MMM,y");
		dateEnd.setBounds(451, 6, 224, 35);
		dateEnd.setDate(Date.valueOf(endDay));
		panel.add(dateEnd);

		dateStart = new JDateChooser();
		dateStart.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateStart.setDateFormatString("d MMM,y");
		dateStart.setBounds(98, 6, 236, 35);
		dateStart.setDate(Date.valueOf(fromDay));
		panel.add(dateStart);

		JLabel lblNewLabel = new JLabel("Từ ngày :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 6, 78, 35);
		panel.add(lblNewLabel);

		JLabel lblnNgy = new JLabel("Đến ngày :");
		lblnNgy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnNgy.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnNgy.setBounds(355, 11, 85, 30);
		panel.add(lblnNgy);
		
		cmbChon = new JComboBox();
		cmbChon.setModel(new DefaultComboBoxModel(new String[] {"Sản phẩm đã bán", "Sản phẩm bán nhiều nhất","Sản phẩm có danh thu cao nhất"}));
		cmbChon.setBounds(685, 6, 208, 35);
		panel.add(cmbChon);
		String col[] = { "Mã sản phẩm","Tên sản phẩm","Số lượng đã bán", "Thành Tiền" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(25);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 124, 1253, 450);
		scrollPane.setEnabled(false);
		pTKDT.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Tổng số lượng sản phẩm đã bán :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(785, 585, 254, 36);
		pTKDT.add(lblNewLabel_1);
		
		txtSLSPDB = new JTextField();
		txtSLSPDB.setBackground(new Color(255, 255, 255));
		txtSLSPDB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSLSPDB.setColumns(10);
		txtSLSPDB.setBounds(1048, 586, 215, 36);
		pTKDT.add(txtSLSPDB);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tổng tiền :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(443, 585, 94, 36);
		pTKDT.add(lblNewLabel_1_1);
		
		txtTong = new JTextField();
		txtTong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTong.setColumns(10);
		txtTong.setBackground(Color.WHITE);
		txtTong.setBounds(546, 586, 215, 36);
		pTKDT.add(txtTong);
		
		JLabel lblNewLabel_2 = new JLabel("Thống kê sản phẩm");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 11, 1253, 42);
		pTKDT.add(lblNewLabel_2);
		loadSPBC();
	}
	boolean checkNgay(Date from, Date end) {
		if (end.compareTo(from) > 0 && from.compareTo(end) < 0 && from.compareTo(Date.valueOf(endDay)) < 0)
			return true;
		return false;
	}
	void loadSPBC() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for(CTHoaDon hd : ctHDDao.getallSP(dateSQLStart, dateSQLEnd)) {
			tableModel.addRow(new Object[] {
					hd.getSanPham().getMaSP(),hd.getSanPham().getTenSP(),hd.getSoLuong(),hd.getThanhTien()
			});


		}
	}
	void loadTONGSOSP() throws Exception{
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		int soLuong = 0;
		long tong = 0;
		for(CTHoaDon hd : ctHDDao.getallSP(dateSQLStart, dateSQLEnd)) {
				soLuong += hd.getSoLuong();
				tong += hd.getThanhTien();
		}
		txtSLSPDB.setText(String.valueOf(soLuong));
		txtTong.setText(String.valueOf(tong));
	}
	void loadTONGSOSPTOP1() throws Exception{
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		int soLuong = 0;
		float tong =0;
		for(CTHoaDon hd : ctHDDao.getallSPTOP1(dateSQLStart, dateSQLEnd)) {
				soLuong += hd.getSoLuong();
				tong+=hd.getThanhTien();
		}
		txtSLSPDB.setText(String.valueOf(soLuong));
		txtTong.setText(String.valueOf(tong));
	}
	
	void loadTONGSOSPCODTTOP1() throws Exception{
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		int soLuong = 0;
		long tong =0;
		for(CTHoaDon hd : ctHDDao.getallSPTTTOP1(dateSQLStart, dateSQLEnd)) {
				soLuong += hd.getSoLuong();
				tong+=hd.getThanhTien();
		}
		txtSLSPDB.setText(String.valueOf(soLuong));
		txtTong.setText(String.valueOf(tong));
	}
	void loadSPBCTOP1() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for(CTHoaDon hdTOP1 : ctHDDao.getallSPTOP1(dateSQLStart, dateSQLEnd)) {
			tableModel.addRow(new Object[] {
					hdTOP1.getSanPham().getMaSP(),hdTOP1.getSanPham().getTenSP(),hdTOP1.getSoLuong(),hdTOP1.getThanhTien()
			});


		}
	}
	
	void loadSPBCDTTOP1() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for(CTHoaDon hdTOP1 : ctHDDao.getallSPTTTOP1(dateSQLStart, dateSQLEnd)) {
			tableModel.addRow(new Object[] {
					hdTOP1.getSanPham().getMaSP(),hdTOP1.getSanPham().getTenSP(),hdTOP1.getSoLuong(),hdTOP1.getThanhTien()
			});


		}
	}

	public void xuatHD(Date from, Date end) {
		try {
			String sql = "select sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',SUM(cthd.THANHTIEN) as'Thanh_tien'\r\n"
					+ "from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ "where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "group by sp.MASP, TENSP\r\n"
					+ "order by So_Luong,Thanh_tien desc";
			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/ThongKeSangPham.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint p = JasperFillManager.fillReport(jasperReport, null, ConnectDB.getConnection());
			JasperViewer.viewReport(p, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void xuatHDTOP1(Date from, Date end) {
		try {
			String sql = "select top 1 sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',SUM(cthd.THANHTIEN) as'Thanh_tien'\r\n"
					+ "from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ "where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "group by sp.MASP, TENSP\r\n"
					+ "order by So_Luong desc";
			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/ThongKeSangPham.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint p = JasperFillManager.fillReport(jasperReport, null, ConnectDB.getConnection());
			JasperViewer.viewReport(p, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void xuatHDSPCDTTOP1(Date from, Date end) {
		try {
			String sql = "select top 1 sp.MASP, sp.TENSP,SUM(cthd.SOLUONG) as 'So_Luong',SUM(cthd.THANHTIEN) as'Thanh_tien'\r\n"
					+ "from HOADON hd join CTHOADON cthd on hd.MAHD=cthd.MAHD join SANPHAM sp on sp.MASP = cthd.MASP\r\n"
					+ "where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ "group by sp.MASP, TENSP\r\n"
					+ "order by Thanh_tien desc";
			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/ThongKeSPDoanhThuCaoNhat.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint p = JasperFillManager.fillReport(jasperReport, null, ConnectDB.getConnection());
			JasperViewer.viewReport(p, false);
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
