package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import Connect.ConnectDB;
import Dao.CTHoaDonDao;
import Dao.HoaDonDao;
import Dao.KhachHangDao;
import Entity.CTHoaDon;
import Entity.HoaDon;
import Entity.KhachHang;
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
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class FrameThongKeKH extends JInternalFrame {
	

	private static final long serialVersionUID = -4892192612269040617L;
	private JTable table;
	private DefaultTableModel tableModel;
//	private CTHoaDonDao ctHDDao = new CTHoaDonDao();
	private KhachHangDao kh = new KhachHangDao();
	private HoaDonDao hdDao = new HoaDonDao();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private LocalDate fromDay = LocalDate.of(2021, 11, 13);
	private LocalDate endDay = LocalDate.now();
	private JDateChooser dateEnd, dateStart;
	private JComboBox cmbChon;
	private JTextField txtSOLDH;
	private JTextField txtSLKH;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameThongKeKH frame = new FrameThongKeKH();
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
	public FrameThongKeKH() throws Exception {
		cmbChon = new JComboBox<String>();
		setTitle("Thống kê khách hàng");
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
		panel.setBounds(10, 62, 1253, 52);
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
					if(chon =="Khách hàng mua hàng") {
						xuatKH(dateSQLStart, dateSQLEnd);
					}else {
						xuatKHTOP1(dateSQLStart, dateSQLEnd);
					}
				} else {
					showMessage("Hãy chọn ngày lại đi!!!", dateEnd);
				}
			}
		});
		btnInThongKe.setIcon(new ImageIcon(FrameThongKeDT.class.getResource("/image/btnPrint.png")));
		btnInThongKe.setBounds(1086, 6, 150, 40);
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
						if(chon =="Khách hàng mua hàng") {
							loadKH();
							loadTONGSOHOADON();
					}
					else {
							loadKHTOP1();
							loadTONGSOHOADONTOP1();
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
		btnXemthongKe.setBounds(921, 6, 150, 40);
		panel.add(btnXemthongKe);

		dateEnd = new JDateChooser();
		dateEnd.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateEnd.setDateFormatString("d MMM,y");
		dateEnd.setBounds(457, 6, 224, 40);
		dateEnd.setDate(Date.valueOf(endDay));
		panel.add(dateEnd);

		dateStart = new JDateChooser();
		dateStart.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateStart.setDateFormatString("d MMM,y");
		dateStart.setBounds(102, 6, 236, 40);
		dateStart.setDate(Date.valueOf(fromDay));
		panel.add(dateStart);

		JLabel lblNewLabel = new JLabel("Từ ngày :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(15, 6, 72, 40);
		panel.add(lblNewLabel);

		JLabel lblnNgy = new JLabel("Đến ngày :");
		lblnNgy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnNgy.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnNgy.setBounds(353, 6, 89, 40);
		panel.add(lblnNgy);
		
		cmbChon = new JComboBox();
		cmbChon.setModel(new DefaultComboBoxModel(new String[] {"Khách hàng mua hàng", " Khách hàng quay lại nhiều nhất"}));
		cmbChon.setBounds(696, 10, 210, 37);
		panel.add(cmbChon);
		String col[] = { "Mã khách hàng","Tên khách hàng","Số DT" ,"Địa chỉ","Giới tính", "Số lượng đơn hàng" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(25);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 125, 1253, 450);
		scrollPane.setEnabled(false);
		pTKDT.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Tổng số lượng đơn hàng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(831, 585, 195, 36);
		pTKDT.add(lblNewLabel_1);
		
		txtSOLDH = new JTextField();
		txtSOLDH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSOLDH.setBounds(1035, 586, 215, 36);
		pTKDT.add(txtSOLDH);
		txtSOLDH.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tổng số lượng khách hàng :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(385, 585, 195, 36);
		pTKDT.add(lblNewLabel_1_1);
		
		txtSLKH = new JTextField();
		txtSLKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSLKH.setColumns(10);
		txtSLKH.setBounds(606, 586, 195, 36);
		pTKDT.add(txtSLKH);
		
		JLabel lblNewLabel_2 = new JLabel("Thống kê khách hàng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 1253, 42);
		pTKDT.add(lblNewLabel_2);
		loadKH();
		loadTONGSOHOADON();
	}
	boolean checkNgay(Date from, Date end) {
		if (end.compareTo(from) > 0 && from.compareTo(end) < 0 && from.compareTo(Date.valueOf(endDay)) < 0)
			return true;
		return false;
	}
	void loadKH() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for(Entry<KhachHang, Integer> dsKH : kh.thongkeKH(dateSQLStart, dateSQLEnd).entrySet()) {
			KhachHang kh = dsKH.getKey();
			tableModel.addRow(new Object[] {
				kh.getMaKH(),kh.getTenKH(),kh.getSoDT(),kh.getDiaChi(),kh.getGioiTinh(),dsKH.getValue()
			});	


		}
	}
	void loadTONGSOHOADON() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		int soluongDH = 0;
		int count =0;
		for(Entry<KhachHang, Integer> dsKH : kh.thongkeKH(dateSQLStart, dateSQLEnd).entrySet()) {
			KhachHang kh = dsKH.getKey();
			soluongDH += dsKH.getValue();
			count++;
		}
		txtSOLDH.setText(String.valueOf(soluongDH));
		txtSLKH.setText(String.valueOf(count));
	}
	void loadTONGSOHOADONTOP1() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		int soluong = 0;
		int count =0;
		for(Entry<KhachHang, Integer> dsKH : kh.thongkeKHTOP1(dateSQLStart, dateSQLEnd).entrySet()) {
			soluong += dsKH.getValue();
			count++;
		}
		txtSOLDH.setText(String.valueOf(soluong));
		txtSLKH.setText(String.valueOf(count));
	}
	void loadKHTOP1() throws Exception {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableModel.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for(Entry<KhachHang, Integer> dsKH : kh.thongkeKHTOP1(dateSQLStart, dateSQLEnd).entrySet()) {
			KhachHang kh = dsKH.getKey();
			tableModel.addRow(new Object[] {
				kh.getMaKH(),kh.getTenKH(),kh.getSoDT(),kh.getDiaChi(),kh.getGioiTinh(),dsKH.getValue()
			});	


		}
	}

	public void xuatKH(Date from, Date end) {
		try {
			String sql = " select kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH,COUNT(hd.MAHD) as 'So_luong_hoa_don'\r\n"
					+ " from HOADON hd join KHACHHANG kh on kh.MAKH = hd.MAKH\r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ " group by kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH\r\n"
					+ " order by So_luong_hoa_don desc";
			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/ThongKeKhachHang.jrxml");
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
	
	public void xuatKHTOP1(Date from, Date end) {
		try {
			String sql = " select top 1 kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH,COUNT(hd.MAHD) as 'So_luong_hoa_don'\r\n"
					+ " from HOADON hd join KHACHHANG kh on kh.MAKH = hd.MAKH\r\n"
					+ " where NGAYTAO BETWEEN '"+from.toString()+"' and '"+end.toString()+"'\r\n"
					+ " group by kh.MAKH,kh.TENKH,kh.SDT,kh.DIACHI,kh.GIOITINH\r\n"
					+ " order by So_luong_hoa_don desc";
			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/ThongKeKhachHang.jrxml");
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
