package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Dao.DungCuHocTapDao;
import Dao.LoaiSanPhamDao;
import Dao.NhaCungCapDao;
import Entity.DungCuHocTap;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class FrameTimKiemDCHT extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private DefaultTableModel tableModel1;
	private JTable table;
	private JTextField txtTenSP;
	private DungCuHocTapDao spsDao = new DungCuHocTapDao();
	private LoaiSanPhamDao lspDao = new LoaiSanPhamDao();
	private NhaCungCapDao nccDao = new NhaCungCapDao();
	private JComboBox<String> cbbNCC,cbbLSP,cbbGT;
	@SuppressWarnings("unused")
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTimKiemDCHT frame = new FrameTimKiemDCHT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrameTimKiemDCHT() throws Exception {
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

		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm dụng cụ học tập");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 1253, 40);
		pTK_DH.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 62, 1253, 203);
		pTK_DH.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên dụng cụ :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(228, 18, 150, 20);
		panel_1.add(lblNewLabel);

		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenSP.setBounds(388, 14, 500, 30);
		panel_1.add(txtTenSP);
		txtTenSP.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Loại cụng cụ :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(228, 113, 150, 20);
		panel_1.add(lblNewLabel_1);

		cbbLSP = new JComboBox();
		cbbLSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbLSP.setBounds(388, 106, 500, 34);
		panel_1.add(cbbLSP);

		cbbGT = new JComboBox();
		cbbGT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbGT.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Dưới 70.000 đ", "Từ 70.000 đ đến 300.000 đ", "Trên 300.000 đ"}));
		cbbGT.setBounds(388, 58, 500, 34);
		panel_1.add(cbbGT);

		JLabel lblNewLabel_1_1 = new JLabel("Giá thành :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_1.setBounds(228, 65, 150, 20);
		panel_1.add(lblNewLabel_1_1);

		JButton btn = new JButton("Tìm");
		btn.setIcon(new ImageIcon(FrameTimKiemDCHT.class.getResource("/image/btnTim.png")));
		btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateLSP()) {
					String tenSach = txtTenSP.getText().trim();
					int selGiaThanh = cbbGT.getSelectedIndex();
					int selLSP = cbbLSP.getSelectedIndex();
					int selNCC = cbbNCC.getSelectedIndex();
					if (selGiaThanh == 0) {
						String maLSP = "", maNXB = "";
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNCC != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNCC.getItemAt(selNCC));
						}
						try {
							loadSPALL(tenSach, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					if (selGiaThanh == 1) {
						String maLSP = "", maNXB = "";
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNCC != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNCC.getItemAt(selNCC));
						}
						try {
							loadSPMIN(tenSach, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					if (selGiaThanh == 2) {
						String maLSP = "", maNXB = "";
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNCC != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNCC.getItemAt(selNCC));
						}
						try {
							loadSPFromTo(tenSach, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					if (selGiaThanh == 3) {
						String maLSP = "", maNXB = "";
						if (selLSP != 0) {
							maLSP = loadMaTheoTenLSP((String) cbbLSP.getItemAt(selLSP));
						}
						if (selNCC != 0) {
							maNXB = loadMaTheoTenNXB((String) cbbNCC.getItemAt(selNCC));
						}
						try {
							loadSPMAX(tenSach, maLSP, maNXB);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn.setBounds(939, 151, 150, 40);
		panel_1.add(btn);
		
		JLabel lblNewLabel_1_3 = new JLabel("Nhà cung cấp :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1_3.setBounds(228, 161, 150, 20);
		panel_1.add(lblNewLabel_1_3);
		
		cbbNCC = new JComboBox();
		cbbNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbNCC.setBounds(388, 154, 500, 34);
		panel_1.add(cbbNCC);

		String col1[] = { "Mã sản phẩm", "Tên sản phẩm", "Giá cả", "Số lượng", "Loại sản phẩm", "Nhà cung cấp"};
		tableModel1 = new DefaultTableModel(col1, 0);
		table = new JTable(tableModel1);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 276, 1253, 354);
		pTK_DH.add(scrollPane);

	}

	public String loadMaTheoTenNXB(String tenNXBCanTim) {
		return nccDao.getMaNCCTheoTen(tenNXBCanTim);
	}

	public String loadMaTheoTenLSP(String tenLSPCanTim) {
		return lspDao.getMaLSPTheoTen(tenLSPCanTim);
	}

	public void loadLSP() throws Exception {
		cbbLSP.removeAllItems();
		cbbLSP.addItem("Tất cả");
		for (LoaiSanPham dm : lspDao.getAllDMCungLoai(0)) {
			cbbLSP.addItem(dm.getTenDM());
		}
	}

	public void loadNXB() throws ClassNotFoundException, SQLException {
		cbbNCC.removeAllItems();
		cbbNCC.addItem("Tất cả");
		for (NhaCungCap dm : nccDao.getallNCC()) {
			cbbNCC.addItem(dm.getTenNCC());
		}
	}

	public void loadSPALL(String ten,String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (DungCuHocTap spk : spsDao.getallSPTheoTen(ten, maLSP, maNXB)) {
			tableModel1.addRow(new Object[] { spk.getMaSP(), spk.getTenSP(), currencyVN.format(spk.getDonGia()), spk.getSoLuong(),
					spk.getDanhMuc().getTenDM(), spk.getNhaCungCap().getTenNCC()});
		}
	}

	public void loadSPMIN(String ten, String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (DungCuHocTap spk : spsDao.getSPDGBe(ten, maLSP, maNXB)) {
			tableModel1.addRow(new Object[] { spk.getMaSP(), spk.getTenSP(), currencyVN.format(spk.getDonGia()), spk.getSoLuong(),
					spk.getDanhMuc().getTenDM(), spk.getNhaCungCap().getTenNCC()});
		}
	}

	public void loadSPMAX(String ten, String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (DungCuHocTap spk : spsDao.getSPDGLon(ten, maLSP, maNXB)) {
			tableModel1.addRow(new Object[] { spk.getMaSP(), spk.getTenSP(), currencyVN.format(spk.getDonGia()), spk.getSoLuong(),
					spk.getDanhMuc().getTenDM(), spk.getNhaCungCap().getTenNCC()});
		}
	}

	public void loadSPFromTo(String ten, String maLSP, String maNXB) throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (DungCuHocTap spk : spsDao.getSPDGTu(ten, maLSP, maNXB)) {
			tableModel1.addRow(new Object[] { spk.getMaSP(), spk.getTenSP(),currencyVN.format(spk.getDonGia()), spk.getSoLuong(),
					spk.getDanhMuc().getTenDM(), spk.getNhaCungCap().getTenNCC()});
		}
	}

	public void loadSP() throws Exception {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (DungCuHocTap spk : spsDao.getallSP()) {
			tableModel1.addRow(new Object[] { spk.getMaSP(), spk.getTenSP(), currencyVN.format(spk.getDonGia()), spk.getSoLuong(),
					spk.getDanhMuc().getTenDM(), spk.getNhaCungCap().getTenNCC()});
		}
	}

	public boolean validateLSP() {
		String tenLSP = txtTenSP.getText();
		if (!(tenLSP.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Tên loại sản phẩm không được có kí tự đặt biệt VD:Sách", txtTenSP);
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
