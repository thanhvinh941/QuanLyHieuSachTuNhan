package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

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

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameCTHD extends JFrame {
	private DefaultTableModel tableModel1;
	private JPanel contentPane;
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private JPanel panel;
	private JLabel lbltitle;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField txtMaHD;
	private JLabel lblNewLabel_3;
	private JTextField txtNgayTao;
	private JLabel lblNewLabel_3_1;
	private JTextField txtMaKH;
	private JLabel lblNewLabel_3_2;
	private JTextField txtTenKH;
	private JLabel lblNewLabel_3_3;
	private JTextField txtSDTKH;
	private JLabel lblNewLabel_3_4;
	private JTextField txtDiaChi;
	private JTextField txtMaNV;
	private JLabel lblNewLabel_3_2_1;
	private JLabel lblNewLabel_3_3_1;
	private JTextField txtTenNV;
	private JLabel lblNewLabel_3_4_1;
	private JTextField txtSDTNV;
	private JLabel lblNewLabel_3_5;
	private JTextField txtChucVu;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1,lblTongThanhTien;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCTHD frame = new FrameCTHD("HD1");
					frame.khoiTaoHoaDon("HD1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameCTHD(String maHD) {
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(350, 50, 543, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbltitle = new JLabel("");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltitle.setBounds(10, 11, 500, 35);
		panel.add(lbltitle);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 57, 500, 150);
		panel.add(panel_1);
		
		lblNewLabel = new JLabel("Mã hóa đơn:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 13, 100, 14);
		panel_1.add(lblNewLabel);
		
		txtMaHD = new JTextField();
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(120, 8, 125, 20);
		panel_1.add(txtMaHD);
		
		lblNewLabel_3 = new JLabel("Ngày tạo:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(255, 13, 100, 14);
		panel_1.add(lblNewLabel_3);
		
		txtNgayTao = new JTextField();
		txtNgayTao.setColumns(10);
		txtNgayTao.setBounds(365, 8, 125, 20);
		panel_1.add(txtNgayTao);
		
		lblNewLabel_3_1 = new JLabel("Mã khách hàng:");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_1.setBounds(10, 40, 100, 14);
		panel_1.add(lblNewLabel_3_1);
		
		txtMaKH = new JTextField();
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(120, 36, 125, 20);
		panel_1.add(txtMaKH);
		
		lblNewLabel_3_2 = new JLabel("Tên khách hàng:");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_2.setBounds(10, 67, 100, 14);
		panel_1.add(lblNewLabel_3_2);
		
		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(120, 64, 125, 20);
		panel_1.add(txtTenKH);
		
		lblNewLabel_3_3 = new JLabel("Số điện thoại:");
		lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_3.setBounds(10, 94, 100, 14);
		panel_1.add(lblNewLabel_3_3);
		
		txtSDTKH = new JTextField();
		txtSDTKH.setColumns(10);
		txtSDTKH.setBounds(120, 92, 125, 20);
		panel_1.add(txtSDTKH);
		
		lblNewLabel_3_4 = new JLabel("Địa chỉ :");
		lblNewLabel_3_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_4.setBounds(10, 121, 100, 14);
		panel_1.add(lblNewLabel_3_4);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(120, 120, 125, 20);
		panel_1.add(txtDiaChi);
		
		txtMaNV = new JTextField();
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(365, 36, 125, 20);
		panel_1.add(txtMaNV);
		
		lblNewLabel_3_2_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_3_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_2_1.setBounds(255, 40, 100, 14);
		panel_1.add(lblNewLabel_3_2_1);
		
		lblNewLabel_3_3_1 = new JLabel("Tên nhân viên:");
		lblNewLabel_3_3_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_3_1.setBounds(255, 67, 100, 14);
		panel_1.add(lblNewLabel_3_3_1);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(365, 64, 125, 20);
		panel_1.add(txtTenNV);
		
		lblNewLabel_3_4_1 = new JLabel("Số điện thoại:");
		lblNewLabel_3_4_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_4_1.setBounds(255, 94, 100, 14);
		panel_1.add(lblNewLabel_3_4_1);
		
		txtSDTNV = new JTextField();
		txtSDTNV.setColumns(10);
		txtSDTNV.setBounds(365, 92, 125, 20);
		panel_1.add(txtSDTNV);
		
		lblNewLabel_3_5 = new JLabel("Chức vụ:");
		lblNewLabel_3_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3_5.setBounds(255, 121, 100, 14);
		panel_1.add(lblNewLabel_3_5);
		
		txtChucVu = new JTextField();
		txtChucVu.setColumns(10);
		txtChucVu.setBounds(365, 120, 125, 20);
		panel_1.add(txtChucVu);
		
		String[] col = {"Mã sản phẩm","Tên sản phẩm","Đơn giá","Số lượng","Thành tiền"};
		tableModel1 = new DefaultTableModel(col, 0);
		table = new JTable(tableModel1);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 218, 500, 195);
		panel.add(scrollPane);
		
		
		btnNewButton = new JButton("In hóa đơn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatHD(txtMaHD.getText());
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(FrameCTHD.class.getResource("/image/btnPrint.png")));
		btnNewButton.setBounds(390, 459, 120, 30);
		panel.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Tổng thành tiền");
		lblNewLabel_1.setBounds(300, 424, 100, 14);
		panel.add(lblNewLabel_1);
		
		lblTongThanhTien = new JLabel("");
		lblTongThanhTien.setBounds(410, 424, 100, 14);
		panel.add(lblTongThanhTien);
	}

	public void xuatHD(String maHD) {
		try {
			String sql = "SELECT \"LOAISANPHAM\".\"TENLSP\",\r\n" + "	\"SANPHAM\".\"MASP\",\r\n"
					+ "	\"SANPHAM\".\"DONGIA\",\r\n" + "	\"SANPHAM\".\"TENSP\",\r\n"
					+ "	\"CTHOADON\".\"SOLUONG\",\r\n" + "	\"CTHOADON\".\"THANHTIEN\",\r\n"
					+ "	\"HOADON\".\"NGAYTAO\",\r\n" + "	\"HOADON\".\"MAHD\",\r\n"
					+ "	\"HOADON\".\"TONGTHANHTIEN\",\r\n" + "	\"HOADON\".\"TIENMAT\",\r\n"
					+ "	\"HOADON\".\"TIENDU\",\r\n" + "	\"NHANVIEN\".\"TENNV\",\r\n" + "	\"NHANVIEN\".\"SDT\",\r\n"
					+ "	\"KHACHHANG\".\"DIACHI\",\r\n" + "	\"KHACHHANG\".\"TENKH\",\r\n"
					+ "	\"KHACHHANG\".\"GIOITINH\"\r\n" + "FROM \"CTHOADON\"\r\n" + "	INNER JOIN \"HOADON\" ON \r\n"
					+ "	 \"CTHOADON\".\"MAHD\" = \"HOADON\".\"MAHD\" \r\n" + "	INNER JOIN \"KHACHHANG\" ON \r\n"
					+ "	 \"HOADON\".\"MAKH\" = \"KHACHHANG\".\"MAKH\" \r\n" + "	INNER JOIN \"NHANVIEN\" ON \r\n"
					+ "	 \"HOADON\".\"MANV\" = \"NHANVIEN\".\"MANV\" \r\n" + "	INNER JOIN \"SANPHAM\" ON \r\n"
					+ "	 \"CTHOADON\".\"MASP\" = \"SANPHAM\".\"MASP\" \r\n" + "	INNER JOIN \"LOAISANPHAM\" ON \r\n"
					+ "	 \"SANPHAM\".\"MALSP\" = \"LOAISANPHAM\".\"MALSP\" \r\n" + "WHERE \r\n"
					+ "	 \"HOADON\".\"MAHD\" = '" + maHD + "'";

			JasperDesign jasperDesign = JRXmlLoader.load("src/Report/HoaDonRepoter.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint p = JasperFillManager.fillReport(jasperReport, null, ConnectDB.getConnection());
			JasperViewer.viewReport(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	void khoiTaoHoaDon(String maHD) throws Exception {
		HoaDonDao hdDao= new HoaDonDao();
		CTHoaDonDao cthdDao = new CTHoaDonDao();
		HoaDon hd = hdDao.getHDTheoID(maHD);
		List<CTHoaDon> listCTHD = cthdDao.getallCTHD(maHD);
		lbltitle.setText("Hóa đơn:"+hd.getMaHD());
		txtMaHD.setText(hd.getMaHD());txtNgayTao.setText(sdf.format(hd.getNgayTao()));
		txtMaNV.setText(hd.getNhanVien().getMaNV());txtTenNV.setText(hd.getNhanVien().getTenNV());txtSDTNV.setText(hd.getNhanVien().getsDT());txtChucVu.setText(hd.getNhanVien().getChuVu());
		txtMaKH.setText(hd.getKhachHang().getMaKH());txtTenKH.setText(hd.getKhachHang().getTenKH());txtSDTKH.setText(hd.getKhachHang().getSoDT());txtDiaChi.setText(hd.getKhachHang().getDiaChi());
		lblTongThanhTien.setText(currencyVN.format(hd.getTongThanhTien()));
		int tblRow = tableModel1.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for(CTHoaDon cthd : listCTHD) {
			tableModel1.addRow(new Object[] {
					cthd.getSanPham().getMaSP(),
					cthd.getSanPham().getTenSP(),
					currencyVN.format(cthd.getSanPham().getDonGia()),
					cthd.getSoLuong(),
					currencyVN.format(cthd.getThanhTien())
			});
		}
		System.out.println(currencyVN.format(hd.getTongThanhTien()));
	}
}
