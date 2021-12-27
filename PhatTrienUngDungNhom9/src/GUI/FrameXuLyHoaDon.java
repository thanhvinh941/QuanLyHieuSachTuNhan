package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDB;
import Dao.CTHoaDonDao;
import Dao.HoaDonDao;
import Dao.KhachHangDao;
import Dao.LoaiSanPhamDao;
import Dao.NhanVienDao;
import Dao.SanPhamDao;
import Dao.TacGiaDao;
import Entity.CTHoaDon;
import Entity.HoaDon;
import Entity.KhachHang;
import Entity.LoaiSanPham;
import Entity.NhanVien;
import Entity.SanPham;
import Entity.TacGia;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

public class FrameXuLyHoaDon extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JTextField txtTenKH;
	private JTextField txtMaKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtMaSP;
	private JTextField txtTienKhachTra;
	private JTextField txtSoLuong;
	private JTextField txtGiaThanh;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtThanhTien;
	private JButton btnXoaSP;
	private JButton btnThemSach;
	private JButton btnTaoHD;
	private JButton btnThemDungCu;
	private JButton btnXoaTrang;
	private JButton btnIn;
	private JButton btnThanhToan;
	private JButton btnLuu, btnCapNhatSP;
	private JComboBox<String> cbbLoaiSP, cbbTG;
	private JLabel lblThongBao;
	private JLabel lblTienDu;
	private JLabel lblThongBaoSoLuong;
	private JLabel lblHD;
	private JTable tableTimKH;
	private DefaultTableModel tableModel1;
	private JScrollPane scpTimKH;
	private JCheckBox chckbxNewCheckBox;
	private static int Enter, soLuongCuaSP;
	public static int ThanhTien = 0;
	private CTHoaDonDao ctdhDao = new CTHoaDonDao();
	private SanPhamDao spDao = new SanPhamDao();
	private HoaDonDao hdDao = new HoaDonDao();
	private KhachHangDao khDao = new KhachHangDao();
	private NhanVienDao nvDao = new NhanVienDao();
	private LocalDate day = LocalDate.now();
	private JTextField txtTongTien;
	@SuppressWarnings("unused")
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private DefaultListModel<String> listModel;
	private JList<String> list;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameXuLyHoaDon frame = new FrameXuLyHoaDon();
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
	 * @throws Exception
	 */
	public FrameXuLyHoaDon(String maNV) throws Exception {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pQL_HD = new JPanel();
		pQL_HD.setBackground(Color.WHITE);
		pQL_HD.setBounds(0, 0, 1186, 654);
		getContentPane().add(pQL_HD);
		pQL_HD.setLayout(null);

		String col1[] = { "Mã KH", "Tên KH", "SDT", "Địa chỉ" };
		tableModel1 = new DefaultTableModel(col1, 0);
		tableTimKH = new JTable(tableModel1);
		tableTimKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableTimKH.setRowHeight(25);
		tableTimKH.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableTimKH.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableTimKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableTimKH.getSelectedRow();
				String maKH = (String) tableTimKH.getValueAt(rowSelect, 0);
				String tenKH = (String) tableTimKH.getValueAt(rowSelect, 1);
				String soDT = (String) tableTimKH.getValueAt(rowSelect, 2);
				String diaChi = (String) tableTimKH.getValueAt(rowSelect, 3);
				txtTenKH.setText(tenKH);
				txtMaKH.setText(maKH);
				txtSDT.setText(soDT);
				txtDiaChi.setText(diaChi);
				scpTimKH.setVisible(false);
				txtTenKH.setEditable(false);
			}
		});
		scpTimKH = new JScrollPane(tableTimKH);
		scpTimKH.setVisible(false);
		scpTimKH.setBounds(180, 59, 514, 163);
		pQL_HD.add(scpTimKH);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 430, 211);
		pQL_HD.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên khách hàng :");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 23, 150, 20);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng :\r\n");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(10, 71, 150, 20);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại :");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(10, 119, 150, 20);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Địa chỉ :");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(10, 170, 150, 20);
		panel.add(lblNewLabel_3);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtTenKH.setEditable(false);
		txtTenKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scpTimKH.setVisible(true);
				Enter = 1;
			}
		});
		txtTenKH.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Enter == 0) {
					String tenKH = txtTenKH.getText();
					try {
						showTimKiemKH(tenKH);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					Enter = 0;
			}
		});
		txtTenKH.setBounds(170, 18, 250, 30);
		panel.add(txtTenKH);
		txtTenKH.setColumns(10);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtMaKH.setEditable(false);
		txtMaKH.setBounds(170, 66, 250, 30);
		panel.add(txtMaKH);
		txtMaKH.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtSDT.setEditable(false);
		txtSDT.setBounds(170, 114, 250, 30);
		panel.add(txtSDT);
		txtSDT.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtDiaChi.setEditable(false);
		txtDiaChi.setBounds(170, 162, 250, 30);
		panel.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		String col[] = { "Mã sản phẩm", "Tên sản phẩm", "Danh mục", "Giá thành", "Số lượng", "Thành tiền" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = table.getSelectedRow();
				String maSP = (String) table.getValueAt(rowSelect, 0);
				String tenSP = (String) table.getValueAt(rowSelect, 1);
				String loaiSP = (String) table.getValueAt(rowSelect, 2);
				String donGia = (String) table.getValueAt(rowSelect, 3);
				String soLuong = (String) table.getValueAt(rowSelect, 4);
				String thanhTien = (String) table.getValueAt(rowSelect, 5);
				LoaiSanPhamDao loaiSanPhamDao = new LoaiSanPhamDao();
				try {
					LoaiSanPham loaiSanPham = loaiSanPhamDao.getDMTheoTen(loaiSP);
					loadLoaiSPCungLoai(loaiSanPham.isLoaiDM());
					for (int i = 0; i < cbbLoaiSP.getItemCount(); i++) {
						if (loaiSP.equalsIgnoreCase((String) cbbLoaiSP.getItemAt(i))) {
							cbbLoaiSP.setSelectedIndex(i);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < list.getModel().getSize(); i++) {
					if (tenSP.equalsIgnoreCase(list.getModel().getElementAt(i))) {
						list.setSelectedIndex(i);
					}
				}
				txtMaSP.setText(maSP);
				txtSoLuong.setText(soLuong);
				txtGiaThanh.setText(donGia);
				txtThanhTien.setText(thanhTien);
				cbbLoaiSP.setEditable(false);
				list.setEnabled(false);
				btnCapNhatSP.setEnabled(true);
				btnXoaSP.setEnabled(true);
				btnLuu.setEnabled(false);
				btnThemDungCu.setEnabled(false);
				btnThemSach.setEnabled(false);
				txtSoLuong.setEditable(true);
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 376, 1238, 220);
		pQL_HD.add(scrollPane);

		JPanel pSP = new JPanel();
		pSP.setBorder(new LineBorder(new Color(0, 0, 0)));
		pSP.setBackground(Color.WHITE);
		pSP.setBounds(450, 11, 813, 211);
		pQL_HD.add(pSP);
		pSP.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Loại sản phẩm :");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setBounds(10, 18, 120, 20);
		pSP.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Mã sản phẩm :\r\n");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(10, 94, 120, 20);
		pSP.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Giá thành :\r\n");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setBounds(10, 132, 120, 20);
		pSP.add(lblNewLabel_6);

		txtMaSP = new JTextField();
		txtMaSP.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtMaSP.setEditable(false);
		txtMaSP.setBounds(140, 92, 294, 30);
		pSP.add(txtMaSP);
		txtMaSP.setColumns(10);

		JLabel lblNewLabel_6_1 = new JLabel("Số lượng :");
		lblNewLabel_6_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6_1.setBounds(274, 132, 100, 20);
		pSP.add(lblNewLabel_6_1);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtSoLuong.setEditable(false);
		txtSoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkSoLuong()) {
					int soLuong = Integer.valueOf(txtSoLuong.getText());
					int giaThanh = 0;
					try {
						giaThanh = currencyVN.parse(txtGiaThanh.getText()).intValue();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txtThanhTien.setText(currencyVN.format(soLuong * giaThanh));
					if (soLuong < soLuongCuaSP) {
						btnLuu.setEnabled(true);
					} else {
						setThongBaoSL("Số lượng sản phẩm không đủ");
						btnLuu.setEnabled(false);
					}
				}
			}
		});
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(384, 130, 50, 30);
		pSP.add(txtSoLuong);

		txtGiaThanh = new JTextField();
		txtGiaThanh.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtGiaThanh.setEditable(false);
		txtGiaThanh.setColumns(10);
		txtGiaThanh.setBounds(140, 130, 120, 30);
		pSP.add(txtGiaThanh);

		cbbLoaiSP = new JComboBox<String>();
		cbbLoaiSP.setBackground(SystemColor.control);
		cbbLoaiSP.setFont(new Font("Dialog", Font.PLAIN, 15));
		cbbLoaiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSoLuong.setText("");
				txtGiaThanh.setText("");
				txtThanhTien.setText("");
				txtMaSP.setText("");
				String tenLoai = (String) cbbLoaiSP.getSelectedItem();
				try {
					if (chckbxNewCheckBox.isSelected()) {
						loadTG();
						String tenTg = (String) cbbTG.getSelectedItem();
						loadSPCungLoai(tenLoai, tenTg);
					} else {
						loadSPCungLoai(tenLoai);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				xoaTrangSP();
				list.setBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"Danh sách " + tenLoai + " ", TitledBorder.LEADING, TitledBorder.TOP, null,
								new Color(0, 0, 0)));
			}
		});
		cbbLoaiSP.setBounds(140, 8, 294, 34);
		pSP.add(cbbLoaiSP);

		JLabel lblNewLabel_6_2 = new JLabel("Thành tiền :");
		lblNewLabel_6_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6_2.setBounds(10, 170, 120, 20);
		pSP.add(lblNewLabel_6_2);

		txtThanhTien = new JTextField();
		txtThanhTien.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtThanhTien.setEditable(false);
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(140, 168, 294, 30);
		pSP.add(txtThanhTien);

		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setFont(new Font("Dialog", Font.PLAIN, 15));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SanPham s = null;
				try {
					s = loadThongTinSPTheoTen(list.getSelectedValue().toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (s != null) {
					txtMaSP.setText(s.getMaSP());
					txtGiaThanh.setText(currencyVN.format(s.getDonGia()));
					soLuongCuaSP = s.getSoLuong();
					lblThongBaoSoLuong.setText("Còn lại : " + s.getSoLuong() + " " + s.getDanhMuc().getTenDM() + "");
				} else {
					lblThongBaoSoLuong.setText("");
				}
//				System.out.println(list.getSelectedValue().toString()); 
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(444, 14, 359, 190);
		pSP.add(scrollPane_1);

		JLabel lblNewLabel_5_1 = new JLabel("Tác giả :");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5_1.setBounds(10, 56, 120, 20);
		pSP.add(lblNewLabel_5_1);

		cbbTG = new JComboBox<String>();
		cbbTG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbbTG.getItemCount() > 0) {
					String tenLoai = (String) cbbLoaiSP.getSelectedItem();
					String tenTG = (String) cbbTG.getSelectedItem();
					try {
						loadSPCungLoai(tenLoai, tenTG);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		cbbTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbTG.setBackground(SystemColor.menu);
		cbbTG.setBounds(140, 50, 254, 34);
		pSP.add(cbbTG);

		chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					cbbTG.setEditable(true);
					try {
						loadTG();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					String tenLoai = (String) cbbLoaiSP.getSelectedItem();
					try {
						loadSPCungLoai(tenLoai);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cbbTG.removeAllItems();
					cbbTG.setEditable(false);
				}
			}
		});
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setBounds(400, 50, 34, 34);
		chckbxNewCheckBox.setIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/circle.png")));
		chckbxNewCheckBox.setSelectedIcon(new ImageIcon(FrameXuLyDonHang.class.getResource("/image/check.png")));
		pSP.add(chckbxNewCheckBox);

		JLabel lblNewLabel_7 = new JLabel("Hóa đơn");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Leelawadee UI", Font.ITALIC, 20));
		lblNewLabel_7.setBounds(10, 233, 130, 40);
		pQL_HD.add(lblNewLabel_7);

		lblHD = new JLabel("");
		lblHD.setForeground(Color.GREEN);
		lblHD.setFont(new Font("Leelawadee UI Semilight", Font.BOLD | Font.ITALIC, 20));
		lblHD.setBounds(150, 233, 100, 40);
		pQL_HD.add(lblHD);

		JLabel lblNewLabel_9 = new JLabel("Tổng tiền hóa đơn :");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.TRAILING);
//		lblNewLabel_9.setVisible(false);
		lblNewLabel_9.setBounds(38, 340, 170, 20);
		pQL_HD.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Tiền khách hàng trả :");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.TRAILING);
//		lblNewLabel_10.setVisible(false);
		lblNewLabel_10.setBounds(454, 340, 170, 20);
		pQL_HD.add(lblNewLabel_10);

		txtTienKhachTra = new JTextField();
		txtTienKhachTra.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtTienKhachTra.setEditable(false);
		txtTienKhachTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tienKhachTra = txtTienKhachTra.getText();
				txtTienKhachTra.setText(currencyVN.format(Float.valueOf(tienKhachTra)));
				btnThanhToan.setEnabled(true);
				try {
					Number tongTienNumber = currencyVN.parse(txtTongTien.getText());
					float tienDu = Float.valueOf(tienKhachTra) - tongTienNumber.floatValue();
					lblTienDu.setText(currencyVN.format(tienDu));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtTienKhachTra.setBounds(647, 335, 200, 30);
//		textField_7.setVisible(false);
		pQL_HD.add(txtTienKhachTra);
		txtTienKhachTra.setColumns(10);

		JLabel lblNewLabel_9_1 = new JLabel("Tiền dư của khách :");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.TRAILING);
//		lblNewLabel_9_1.setVisible(false);
		lblNewLabel_9_1.setBounds(870, 340, 170, 20);
		pQL_HD.add(lblNewLabel_9_1);

		lblTienDu = new JLabel("");
		lblTienDu.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTienDu.setBounds(1063, 340, 200, 20);
		pQL_HD.add(lblTienDu);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkTrungSP(txtMaSP.getText(), Integer.valueOf(txtSoLuong.getText()).intValue())) {

					tableModel.addRow(new Object[] { txtMaSP.getText(), list.getSelectedValue().toString(),
							(String) cbbLoaiSP.getSelectedItem(), txtGiaThanh.getText(), txtSoLuong.getText(),
							txtThanhTien.getText() });
					try {
						txtTongTien.setText(tongThanhTien());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				txtTienKhachTra.setEditable(true);
				list.setBorder(null);
				btnLuu.setEnabled(false);
				lblThongBaoSoLuong.setText("");
				setThongBao("Đã thêm " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))");
				showMessage("Đã thêm " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))", txtDiaChi);
				enabledPSP(false);
				xoaTrangPanelSP();
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnSave.png")));
		btnLuu.setBounds(1143, 233, 120, 40);
		pQL_HD.add(btnLuu);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThanhToan.setEnabled(false);
		btnThanhToan.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnPay.png")));
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int slRow = tableModel.getRowCount();
				if (checkHoaD()) {
					String maDH = lblHD.getText();
					String maKh = txtMaKH.getText();
					KhachHang kh = null;
					int tongThanhTien = 0;
					try {
						tongThanhTien = currencyVN.parse(txtTongTien.getText()).intValue();
					} catch (ParseException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					int tienMat = 0;
					try {
						tienMat = currencyVN.parse(txtTienKhachTra.getText()).intValue();
					} catch (ParseException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					try {
						kh = khDao.getKHTheoMa(maKh);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					NhanVien nv = null;
					try {
						nv = nvDao.getKHTheoMa(maNV);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					HoaDon hd = new HoaDon(maDH, Date.valueOf(day), kh, nv, tongThanhTien, tienMat);
					try {
						hdDao.themHD(hd);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					for (int i = slRow - 1; i >= 0; i--) {
						SanPham sp = null;
						try {
							sp = spDao.getSPTheoID((String) tableModel.getValueAt(i, 0));
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						HoaDon getHD = null;
						try {
							getHD = hdDao.getHDTheoID(maDH);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						String slSPCTHD = (String) tableModel.getValueAt(i, 4);
						CTHoaDon cthd = new CTHoaDon(sp, getHD, Integer.valueOf(slSPCTHD).intValue());
						try {
							ctdhDao.themCTHD(cthd);
						} catch (ClassNotFoundException | SQLException e2) {
							e2.printStackTrace();
						}
						int spCN = sp.getSoLuong() - (Integer.valueOf(slSPCTHD));
						try {
							spDao.capNhatSoLuongSP(spCN, (String) tableModel.getValueAt(i, 0));
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}
					btnIn.setEnabled(true);
					setThongBao("Thanh toán hoàn tất. Bạn có thể in đơn hàng rồi");
					showMessage("Thanh toán hoàn tất. Bạn có thể in đơn hàng rồi", txtDiaChi);
					enabledBtnThemSP(false);
					btnThanhToan.setEnabled(false);
				}
			}
		});
		btnThanhToan.setBounds(920, 284, 150, 40);
		pQL_HD.add(btnThanhToan);

		btnIn = new JButton("In hóa đơn");
		btnIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIn.setEnabled(false);
		btnIn.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnPrint.png")));
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatHD(lblHD.getText());
//				xuatHD("HD1");
			}
		});
		btnIn.setBounds(1113, 284, 150, 40);
		pQL_HD.add(btnIn);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoaTrang.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				troVeBanDau();
			}
		});
		btnXoaTrang.setBounds(78, 284, 150, 40);
		pQL_HD.add(btnXoaTrang);

		btnThemDungCu = new JButton("Thêm dụng cụ");
		btnThemDungCu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemDungCu.setEnabled(false);
		btnThemDungCu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enabledPSP(true);
				chckbxNewCheckBox.setEnabled(false);
				try {
					loadLoaiSPCungLoai(0);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThemDungCu.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnAddTool.png")));
		btnThemDungCu.setBounds(464, 284, 200, 40);
		pQL_HD.add(btnThemDungCu);

		btnTaoHD = new JButton("Tạo hóa đơn");
		btnTaoHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTaoHD.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnThem.png")));
		btnTaoHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(panel, "Bạn có chắc muốn tạo mới hóa đơn?",
						"Thông báo xác nhận tạo hóa đơn", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					troVeBanDau();
					try {
						int soDH = DemHD() + 1;
						lblHD.setText("HD" + soDH);
						enabledPKH(true);
						enabledBtnThemSP(true);
						setThongBao("Đã tạo hóa đơn mới");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnTaoHD.setBounds(271, 284, 150, 40);
		pQL_HD.add(btnTaoHD);

		btnThemSach = new JButton("Thêm sách");
		btnThemSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemSach.setEnabled(false);
		btnThemSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enabledPSP(true);
				chckbxNewCheckBox.setEnabled(true);
				try {
					loadLoaiSPCungLoai(1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThemSach.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnAddBook.png")));
		btnThemSach.setBounds(707, 284, 170, 40);
		pQL_HD.add(btnThemSach);

		btnXoaSP = new JButton("Xóa sản phẩm");
		btnXoaSP.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelect = table.getSelectedRow();
				tableModel.removeRow(rowSelect);
				try {
					txtTongTien.setText(tongThanhTien());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnThemDungCu.setEnabled(true);
				btnThemSach.setEnabled(true);
				btnThanhToan.setEnabled(true);
				btnCapNhatSP.setEnabled(false);
				btnXoaSP.setEnabled(false);
				btnLuu.setEnabled(false);
				lblThongBaoSoLuong.setText("");
				showMessage("Đã thêm " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))", txtDiaChi);
				xoaTrangPanelSP();
			}
		});
		btnXoaSP.setEnabled(false);
		btnXoaSP.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnXoa.png")));
		btnXoaSP.setBounds(763, 233, 160, 40);
		pQL_HD.add(btnXoaSP);

		btnCapNhatSP = new JButton("Cập nhật sản phẩm");
		btnCapNhatSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkSoLuong()) {
					int soLuong = Integer.valueOf(txtSoLuong.getText());
					String thanhTien = txtThanhTien.getText();
					int rowSelect = table.getSelectedRow();
					table.setValueAt(soLuong, rowSelect, 4);
					table.setValueAt(thanhTien, rowSelect, 5);
					try {
						txtTongTien.setText(tongThanhTien());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnThemDungCu.setEnabled(true);
					btnThemSach.setEnabled(true);
					btnThanhToan.setEnabled(true);
					btnCapNhatSP.setEnabled(false);
					btnXoaSP.setEnabled(false);
					btnLuu.setEnabled(false);
					lblThongBaoSoLuong.setText("");
					showMessage("Đã cập nhật " + (String) cbbLoaiSP.getSelectedItem() + " thành công :))", txtDiaChi);
					xoaTrangPanelSP();
				}
			}
		});
		btnCapNhatSP.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnCapNhatSP.setIcon(new ImageIcon(FrameXuLyHoaDon.class.getResource("/image/btnCapNhat.png")));
		btnCapNhatSP.setEnabled(false);
		btnCapNhatSP.setBounds(933, 233, 200, 40);
		pQL_HD.add(btnCapNhatSP);

		lblThongBao = new JLabel("");
		lblThongBao.setForeground(Color.GREEN);
		lblThongBao.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongBao.setBounds(10, 607, 1253, 23);
		pQL_HD.add(lblThongBao);

		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(231, 335, 200, 30);
		pQL_HD.add(txtTongTien);
		txtTongTien.setColumns(10);

		lblThongBaoSoLuong = new JLabel("");
		lblThongBaoSoLuong.setForeground(Color.RED);
		lblThongBaoSoLuong.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblThongBaoSoLuong.setBounds(440, 234, 313, 39);
		pQL_HD.add(lblThongBaoSoLuong);

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
			JasperViewer.viewReport(p, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	boolean checkTrungSP(String maSP, int soLuong) {
		int tblRow1 = tableModel.getRowCount();
		int n = 0;
		for (int i = tblRow1 - 1; i >= 0; i--) {
			if (maSP.equalsIgnoreCase((String) tableModel.getValueAt(i, 0))) {
				String sl = (String) tableModel.getValueAt(i, 4);
				int soLuongbang = Integer.valueOf(sl);
				int soLuongU = soLuong + soLuongbang;
				tableModel.setValueAt(soLuongU, i, 4);
				n++;
			}
		}
		return n > 0;
	}

	public String tongThanhTien() throws ParseException {
		int tblRow1 = tableModel.getRowCount();
		int tongThanhTien = 0;
		for (int i = tblRow1 - 1; i >= 0; i--) {
			int row = currencyVN.parse((String) tableModel.getValueAt(i, 5)).intValue();
			tongThanhTien += row;
		}
		return currencyVN.format(tongThanhTien);
	}

	public void showTimKiemKH(String tenKH) throws ClassNotFoundException, SQLException {
		KhachHangDao khDao = new KhachHangDao();
		int tblRow = tableModel1.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (KhachHang kh : khDao.getMaKHTenKD(tenKH)) {
			tableModel1.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSoDT(), kh.getDiaChi() });
		}
	}

	public void troVeBanDau() {
		enabledBtnThemSP(false);
		xoaTrangPanelSP();
		lblHD.setText("");
		xoaTrangKH();
		xoaTrangTT();
		enabledPKH(false);
		enabledPSP(false);
		int tblRow1 = tableModel.getRowCount();
		for (int i = tblRow1 - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		setThongBao("");
		setThongBaoSL("");
	}

	public void xoaTrangPanelSP() {
		cbbLoaiSP.removeAllItems();
		xoaTrangSP();
	}

	public void xoaTrangSP() {
		txtSoLuong.setText("");
		txtGiaThanh.setText("");
		txtThanhTien.setText("");
		txtMaSP.setText("");
	}

	public void xoaTrangKH() {
		txtTenKH.setText("");
		txtMaKH.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		lblThongBaoSoLuong.setText("");
	}

	public void xoaTrangTT() {
		txtTongTien.setText("");
		txtTienKhachTra.setText("");
		lblTienDu.setText("");
	}

	public void enabledPKH(boolean b) {
		txtTenKH.setEditable(b);
	}

	public void enabledPSP(boolean b) {
		cbbLoaiSP.setEditable(b);
		txtSoLuong.setEditable(b);
	}

	public void enabledBtnThemSP(boolean b) {
		btnThemSach.setEnabled(b);
		btnThemDungCu.setEnabled(b);
	}

	public void setThongBao(String s) {
		lblThongBao.setText(s);
	}

	public void setThongBaoSL(String s) {
		lblThongBaoSoLuong.setText(s);
	}

	public void loadSPCungLoai(String loai, String tenTg) throws Exception {
		listModel.removeAllElements();
		SanPhamDao spDao = new SanPhamDao();
		listModel.addAll(spDao.getallSPTheoTenLSPTenTG(loai, tenTg));
	}

	public void loadTG() throws Exception {
		String tenLSP = (String) cbbLoaiSP.getSelectedItem();
		TacGiaDao tgDao = new TacGiaDao();
		cbbTG.removeAllItems();
		for (TacGia tg : tgDao.getTGTheoTenLSP(tenLSP)) {
			cbbTG.addItem(tg.getTenTG());
		}
	}

	public void loadLoaiSPCungLoai(int i) throws Exception {
		LoaiSanPhamDao lspDao = new LoaiSanPhamDao();
		cbbLoaiSP.removeAllItems();
		for (LoaiSanPham lsp : lspDao.getAllDMCungLoai(i)) {
			cbbLoaiSP.addItem(lsp.getTenDM());
		}
	}

	public void loadSPCungLoai(String loai) throws Exception {
		listModel.removeAllElements();
		SanPhamDao spDao = new SanPhamDao();
		listModel.addAll(spDao.getallSPTheoLoaiDM(loai));
	}

	public SanPham loadThongTinSPTheoTen(String ten) throws Exception {
		SanPhamDao spDao = new SanPhamDao();
		return spDao.getMaSPTenSP(ten);
	}

	public int DemHD() throws ClassNotFoundException, SQLException {
		HoaDonDao hdDao = new HoaDonDao();
		return hdDao.demSoHD();
	}

	public boolean checkSoLuong() {
		String soLuong = txtSoLuong.getText().trim();
		if (!(soLuong.length() > 0 && soLuong.matches("[1-9][0-9]*"))) {
			showMessage("Error Hãy nhập số lượng !!", txtSoLuong);
			btnLuu.setEnabled(false);
			return false;
		}
		return true;
	}

	public boolean checkHoaD() {
		String maKH = txtMaKH.getText();
		if (!(maKH.length() > 0)) {
			showMessage("Error Hãy chọn khách hàng !!", txtMaKH);
			return false;
		}
		int slRow = tableModel.getRowCount();
		if (!(slRow > 0)) {
			showMessage("Error Hình như chưa có sản phẩm trong hóa đơn, hãy chọn sản phẩm ngay nào!!!", txtMaSP);
			return false;
		}
		return true;
	}

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
