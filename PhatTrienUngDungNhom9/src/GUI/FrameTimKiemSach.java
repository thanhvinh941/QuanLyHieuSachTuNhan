package GUI;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.TacGiaDao;
import Dao.LoaiSanPhamDao;
import Dao.NhaXuatBanDao;
import Dao.SachDao;
import Entity.TacGia;
import Entity.LoaiSanPham;
import Entity.NhaXuatBan;
import Entity.Sach;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class FrameTimKiemSach extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtTenSach;
	private JComboBox<String> cbbLSP, cbbNXB, cbbTG, cbbGiaThanh;
	private TacGiaDao tgDao = new TacGiaDao();
	private NhaXuatBanDao nxbDao = new NhaXuatBanDao();
	private LoaiSanPhamDao lspDao = new LoaiSanPhamDao();
	private SachDao spsDao = new SachDao();
	@SuppressWarnings("unused")
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTimKiemSP frame = new FrameTimKiemSP();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrameTimKiemSach() throws Exception {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pTK_DH = new JPanel();
		pTK_DH.setBackground(Color.WHITE);
		pTK_DH.setBounds(0, 0, 1186, 654);
		getContentPane().add(pTK_DH);
		pTK_DH.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 61, 1253, 240);
		pTK_DH.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên sản phẩm :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(181, 17, 150, 20);
		panel_1.add(lblNewLabel);

		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenSach.setBounds(341, 12, 500, 30);
		panel_1.add(txtTenSach);
		txtTenSach.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Loại sách :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(181, 107, 150, 20);
		panel_1.add(lblNewLabel_1);

		cbbLSP = new JComboBox();
		cbbLSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbLSP.setBounds(341, 100, 500, 34);
		panel_1.add(cbbLSP);

		cbbGiaThanh = new JComboBox();
		cbbGiaThanh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbGiaThanh.setModel(new DefaultComboBoxModel(
				new String[] { "Tất cả", "Dưới 70.000 đ", "Từ 70.000 đ đến 300.000 đ", "Trên 300.000 đ" }));
		cbbGiaThanh.setBounds(341, 54, 500, 34);
		panel_1.add(cbbGiaThanh);

		JLabel lblNewLabel_1_1 = new JLabel("Giá thành :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setBounds(181, 61, 150, 20);
		panel_1.add(lblNewLabel_1_1);

		JButton btnTim = new JButton("Tìm");
		btnTim.setIcon(new ImageIcon(FrameTimKiemSach.class.getResource("/image/btnTim.png")));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateLSP()) {
					String tenSach = txtTenSach.getText().trim();
					int selGiaThanh = cbbGiaThanh.getSelectedIndex();
					int selTacGia = cbbTG.getSelectedIndex();
					int selLSP = cbbLSP.getSelectedIndex();
					int selNXB = cbbNXB.getSelectedIndex();
					if (selGiaThanh == 0) {
						String maTG = "", maLSP = "", maNXB = "";
						if (selTacGia != 0) {
							maTG = loadMaTheoTenTG((String) cbbTG.getItemAt(selTacGia));
						}
						System.out.println(maTG);
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNXB != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNXB.getItemAt(selNXB));
						}
						try {
							loadSPALL(tenSach, maTG, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					if (selGiaThanh == 1) {
						String maTG = "", maLSP = "", maNXB = "";
						if (selTacGia != 0) {
							maTG = loadMaTheoTenTG((String) cbbTG.getItemAt(selTacGia));
						}
						System.out.println(maTG);
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNXB != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNXB.getItemAt(selNXB));
						}
						try {
							loadSPMIN(tenSach, maTG, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					if (selGiaThanh == 2) {
						String maTG = "", maLSP = "", maNXB = "";
						if (selTacGia != 0) {
							maTG = loadMaTheoTenTG((String) cbbTG.getItemAt(selTacGia));
						}
						System.out.println(maTG);
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNXB != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNXB.getItemAt(selNXB));
						}
						try {
							loadSPFromTo(tenSach, maTG, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					if (selGiaThanh == 3) {
						String maTG = "", maLSP = "", maNXB = "";
						if (selTacGia != 0) {
							maTG = loadMaTheoTenTG((String) cbbTG.getItemAt(selTacGia));
						}
						System.out.println(maTG);
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNXB != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNXB.getItemAt(selNXB));
						}
						try {
							loadSPMAX(tenSach, maTG, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnTim.setBounds(880, 186, 150, 40);
		panel_1.add(btnTim);

		JLabel lblNewLabel_1_2 = new JLabel("Tác giả :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_2.setBounds(181, 153, 150, 20);
		panel_1.add(lblNewLabel_1_2);

		cbbTG = new JComboBox();
		cbbTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbTG.setBounds(341, 146, 500, 34);
		panel_1.add(cbbTG);

		JLabel lblNewLabel_1_3 = new JLabel("Nhà xuất bản :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_3.setBounds(181, 196, 150, 20);
		panel_1.add(lblNewLabel_1_3);

		cbbNXB = new JComboBox();
		cbbNXB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbNXB.setBounds(341, 192, 500, 34);
		panel_1.add(cbbNXB);
		String col[] = { "Mã sách", "Tên sách", "Giá cả", "Số lượng", "Loại sách", "Tác giả", "Số trang",
				"Nhà xuất bản" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBounds(0, 0, 1, 1);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		table.getColumnModel().getColumn(5).setPreferredWidth(45);
		table.getColumnModel().getColumn(6).setPreferredWidth(25);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 309, 1253, 321);
		pTK_DH.add(scrollPane);

		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm sách");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 10, 1253, 40);
		pTK_DH.add(lblNewLabel_2);

	}

	public String loadMaTheoTenTG(String tenTGCanTim) {
		return tgDao.getMaTGTheoTen(tenTGCanTim);
	}

	public String loadMaTheoTenNXB(String tenNXBCanTim) {
		return nxbDao.getMaNXBTheoTen(tenNXBCanTim);
	}

	public String loadMaTheoTenLSP(String tenLSPCanTim) {
		return lspDao.getMaLSPTheoTen(tenLSPCanTim);
	}

	public void loadTG() throws ClassNotFoundException, SQLException {
		cbbTG.removeAllItems();
		cbbTG.addItem("Tất cả");
		for (TacGia tg : tgDao.getallTG()) {
			cbbTG.addItem(tg.getTenTG());
		}
	}

	public void loadLSP() throws Exception {
		cbbLSP.removeAllItems();
		cbbLSP.addItem("Tất cả");
		for (LoaiSanPham dm : lspDao.getAllDMCungLoai(1)) {
			cbbLSP.addItem(dm.getTenDM());
		}
	}

	public void loadNXB() throws ClassNotFoundException {
		cbbNXB.removeAllItems();
		cbbNXB.addItem("Tất cả");
		for (NhaXuatBan dm : nxbDao.getallNXB()) {
			cbbNXB.addItem(dm.getTenNXB());
		}
	}

	public void loadSPALL(String ten, String matg, String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (Sach sps : spsDao.getallSPTheoTen(ten, matg, maLSP, maNXB)) {
			tableModel.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), currencyVN.format(sps.getDonGia()), sps.getSoLuong(),
					sps.getDanhMuc().getTenDM(), sps.getTacGia().getTenTG(), sps.getSoTrang(),
					sps.getNhaXuatBan().getTenNXB() });
		}
	}

	public void loadSPMIN(String ten, String matg, String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (Sach sps : spsDao.getSPDGBe(ten, matg, maLSP, maNXB)) {
			tableModel.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), currencyVN.format(sps.getDonGia()), sps.getSoLuong(),
					sps.getDanhMuc().getTenDM(), sps.getTacGia().getTenTG(), sps.getSoTrang(),
					sps.getNhaXuatBan().getTenNXB() });
		}
	}

	public void loadSPMAX(String ten, String matg, String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (Sach sps : spsDao.getSPDGLon(ten, matg, maLSP, maNXB)) {
			tableModel.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), currencyVN.format(sps.getDonGia()), sps.getSoLuong(),
					sps.getDanhMuc().getTenDM(), sps.getTacGia().getTenTG(), sps.getSoTrang(),
					sps.getNhaXuatBan().getTenNXB() });
		}
	}

	public void loadSPFromTo(String ten, String matg, String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (Sach sps : spsDao.getSPDGTu(ten, matg, maLSP, maNXB)) {
			tableModel.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), currencyVN.format(sps.getDonGia()), sps.getSoLuong(),
					sps.getDanhMuc().getTenDM(), sps.getTacGia().getTenTG(), sps.getSoTrang(),
					sps.getNhaXuatBan().getTenNXB() });
		}
	}

	public void loadSP() throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (Sach sps : spsDao.getallSP()) {
			tableModel.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), currencyVN.format(sps.getDonGia()), sps.getSoLuong(),
					sps.getDanhMuc().getTenDM(), sps.getTacGia().getTenTG(), sps.getSoTrang(),
					sps.getNhaXuatBan().getTenNXB() });

		}
	}

	public boolean validateLSP() {
		String tenLSP = txtTenSach.getText();
		if (!(tenLSP.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Tên loại sản phẩm không được có kí tự đặt biệt VD:Sách", txtTenSach);
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