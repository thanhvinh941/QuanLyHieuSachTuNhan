package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.NumberFormatter;

import Dao.DungCuHocTapDao;
import Dao.LoaiSanPhamDao;
import Dao.NhaCungCapDao;
import Dao.SanPhamDao;
import Entity.DungCuHocTap;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.SanPham;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFormattedTextField;

public class FrameDanhMucDCHT extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JComboBox<String> cbbDanhMuc, cbbNCC;
	private JTable table_1;
	private DefaultTableModel tbtDSSPModel1;
	private JTextField txtMaLSP;
	private JTextField txtMaNCC;
	private JButton btnThemSP, btnSuaSP, btnXoaTrangSP, btnLuu, btnXa;
	private DungCuHocTapDao cuHocTapDao = new DungCuHocTapDao();
	private LoaiSanPhamDao loaiSanPhamDao = new LoaiSanPhamDao();
	private NhaCungCapDao nhaCungCapDao = new NhaCungCapDao();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDanhMucDCHT frame = new FrameDanhMucDCHT();
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
	public FrameDanhMucDCHT() throws Exception {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pQL_SP = new JPanel();
		pQL_SP.setBackground(Color.WHITE);
		pQL_SP.setBounds(0, 0, 1186, 654);
		getContentPane().add(pQL_SP);
		pQL_SP.setLayout(null);
		String[] col1 = { "Mã dụng cụ", "Tên dụng cụ", "Đơn giá", "Số lượng", "Loại dụng cụ", "Nhà cung cấp" };
		tbtDSSPModel1 = new DefaultTableModel(col1, 0);
		table_1 = new JTable(tbtDSSPModel1) ;
		table_1.setPreferredScrollableViewportSize(table_1.getPreferredSize());
		table_1.getColumnModel().getColumn(1).setPreferredWidth(300);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setFillsViewportHeight(true);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = table_1.getSelectedRow();
//				String maDC = (String) table_1.getValueAt(rowSelect, 0);
//				String tenDC = ;
				int soLuong = (int) table_1.getValueAt(rowSelect, 3);
				int donGia = (int) table_1.getValueAt(rowSelect, 2);
				String tenLSP = (String) table_1.getValueAt(rowSelect, 4);
				String tenNCC = (String) table_1.getValueAt(rowSelect, 5);				
				txtMaSP.setText((String) table_1.getValueAt(rowSelect, 0));
				txtTenSP.setText((String) table_1.getValueAt(rowSelect, 1));
				txtDonGia.setText(String.valueOf(donGia));
				txtSoLuong.setText(String.valueOf(soLuong));
				for (int i = 0; i < cbbDanhMuc.getItemCount(); i++) {
					if (tenLSP.equalsIgnoreCase((String) cbbDanhMuc.getItemAt(i))) {
						cbbDanhMuc.setSelectedIndex(i);
					}
				}
				for (int i = 0; i < cbbNCC.getItemCount(); i++) {
					if (tenNCC.equalsIgnoreCase((String) cbbNCC.getItemAt(i))) {
						cbbNCC.setSelectedIndex(i);
					}
				}
				btnSuaSP.setEnabled(true);
				btnLuu.setEnabled(false);
				btnXa.setEnabled(true);
				btnThemSP.setEnabled(false);
			}
		});
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_1.setRowHeight(25);
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(10, 307, 1253, 323);
		pQL_SP.add(scrollPane);

		JPanel pTTSP = new JPanel();
		pTTSP.setBounds(10, 61, 1253, 247);
		pQL_SP.add(pTTSP);
		pTTSP.setBackground(Color.WHITE);
		pTTSP.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Mã sản phẩm :");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14.setBounds(20, 30, 150, 20);
		pTTSP.add(lblNewLabel_14);

		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setBounds(180, 25, 350, 30);
		pTTSP.add(txtMaSP);
		txtMaSP.setColumns(10);

		txtTenSP = new JTextField();
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(180, 80, 350, 30);
		pTTSP.add(txtTenSP);

		JLabel lblNewLabel_14_1 = new JLabel("Tên sản phẩm :");
		lblNewLabel_14_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_1.setBounds(20, 85, 150, 20);
		pTTSP.add(lblNewLabel_14_1);

		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(180, 135, 350, 30);
		pTTSP.add(txtDonGia);

		JLabel lblNewLabel_14_2 = new JLabel("Đơn giá :");
		lblNewLabel_14_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_2.setBounds(20, 140, 150, 20);
		pTTSP.add(lblNewLabel_14_2);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(180, 190, 350, 30);
		pTTSP.add(txtSoLuong);

		JLabel lblNewLabel_14_3 = new JLabel("Số lượng :");
		lblNewLabel_14_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_3.setBounds(20, 195, 150, 20);
		pTTSP.add(lblNewLabel_14_3);

		JLabel lblNewLabel_14_4 = new JLabel("Loại sản phẩm :");
		lblNewLabel_14_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_4.setBounds(570, 84, 150, 20);
		pTTSP.add(lblNewLabel_14_4);

		cbbDanhMuc = new JComboBox<>();
		cbbDanhMuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenLSP = (String) cbbDanhMuc.getSelectedItem();
				txtMaLSP.setText(loadMaTheoTenLSP(tenLSP));
			}
		});

		cbbDanhMuc.setBounds(730, 76, 350, 34);
		pTTSP.add(cbbDanhMuc);
		btnThemSP = new JButton("Thêm");
		btnThemSP.setBounds(1093, 153, 150, 35);
		pTTSP.add(btnThemSP);
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
				txtMaSP.setText("DCHT" + (soDCHT() + 1));
				btnLuu.setEnabled(true);
			}
		});
		btnThemSP.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnThem.png")));
		btnThemSP.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnSuaSP = new JButton("Cập nhật");
		btnSuaSP.setEnabled(false);
		btnSuaSP.setBounds(1093, 59, 150, 35);
		pTTSP.add(btnSuaSP);
		btnSuaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateLSP()) {
					String maSach = txtMaSP.getText();
					String tenSach = txtTenSP.getText().trim();
					int soLuong = Integer.valueOf(txtSoLuong.getText()).intValue();
					int donGia = Integer.valueOf(txtDonGia.getText()).intValue();
					LoaiSanPham loaiSanPham = null;
					NhaCungCap nhaCungCap = null;
					try {
						loaiSanPham = loaiSanPhamDao.getDMTheoID(txtMaLSP.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						nhaCungCap = nhaCungCapDao.getNCCTheoID(txtMaNCC.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DungCuHocTap dungCuHocTap = new DungCuHocTap(maSach, tenSach, donGia, soLuong, loaiSanPham,
							nhaCungCap);
					DungCuHocTapDao dungCuHocTapDao = new DungCuHocTapDao();
					try {
						dungCuHocTapDao.capnhatNCCSPK(dungCuHocTap, maSach);
						loadSP();
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSuaSP.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnCapNhat.png")));
		btnSuaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnXoaTrangSP = new JButton("Xóa trắng");
		btnXoaTrangSP.setBounds(1093, 200, 150, 35);
		pTTSP.add(btnXoaTrangSP);
		btnXoaTrangSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrangSP.setIcon(new ImageIcon(FrameDanhMucDCHT.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrangSP.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblNewLabel_14_8 = new JLabel("Nhà cung cấp :");
		lblNewLabel_14_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_8.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_8.setBounds(570, 194, 150, 20);
		pTTSP.add(lblNewLabel_14_8);

		cbbNCC = new JComboBox<>();
		cbbNCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenNCC = (String) cbbNCC.getSelectedItem();
				txtMaNCC.setText(loadMaTheotenNCC(tenNCC));
			}
		});

		cbbNCC.setBounds(730, 186, 350, 34);
		pTTSP.add(cbbNCC);

		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon(FrameDanhMucDCHT.class.getResource("/image/btnSave.png")));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateLSP()) {
					String maSach = txtMaSP.getText();
					String tenSach = txtTenSP.getText().trim();
					int soLuong = Integer.valueOf(txtSoLuong.getText()).intValue();
					int donGia = Integer.valueOf(txtDonGia.getText()).intValue();
					LoaiSanPham loaiSanPham = null;
					NhaCungCap nhaCungCap = null;
					try {
						loaiSanPham = loaiSanPhamDao.getDMTheoID(txtMaLSP.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						nhaCungCap = nhaCungCapDao.getNCCTheoID(txtMaNCC.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DungCuHocTap sps = new DungCuHocTap(maSach, tenSach, donGia, soLuong, loaiSanPham, nhaCungCap);
					try {
						cuHocTapDao.themSPK(sps);
						showMessage("ThemThanhCong", txtTenSP);
						tbtDSSPModel1.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), sps.getDonGia(),
								sps.getSoLuong(), sps.getDanhMuc().getTenDM(), sps.getNhaCungCap().getTenNCC() });
						xoaTrang();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLuu.setBounds(1093, 12, 150, 35);
		pTTSP.add(btnLuu);

		JLabel lblNewLabel_14_1_1 = new JLabel("Mã loại sản phẩm :");
		lblNewLabel_14_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_1_1.setBounds(570, 28, 150, 20);
		pTTSP.add(lblNewLabel_14_1_1);

		txtMaLSP = new JTextField();
		txtMaLSP.setEditable(false);
		txtMaLSP.setColumns(10);
		txtMaLSP.setBounds(730, 23, 350, 30);
		pTTSP.add(txtMaLSP);

		JLabel lblNewLabel_14_1_2 = new JLabel("Mã nhà cung cấp :");
		lblNewLabel_14_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_1_2.setBounds(570, 138, 150, 20);
		pTTSP.add(lblNewLabel_14_1_2);

		txtMaNCC = new JTextField();
		txtMaNCC.setEditable(false);
		txtMaNCC.setColumns(10);
		txtMaNCC.setBounds(730, 133, 350, 30);
		pTTSP.add(txtMaNCC);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(400, 200, 430, 211);
		pQL_SP.add(panel);

		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDungCu = txtMaSP.getText();
				int n = JOptionPane.showConfirmDialog(panel, "Bạn có chắc muốn XÓA DỤNG CỤ HỌC TẬP này?",
						"Thông báo xác nhận XÓA DỤNG CỤ HỌC TẬP này", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					try {
						SanPhamDao sanPhamDao = new SanPhamDao();
						sanPhamDao.delete(maDungCu);
						loadSP();
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnXa.setIcon(new ImageIcon(FrameDanhMucDCHT.class.getResource("/image/btnXoa.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXa.setEnabled(false);
		btnXa.setBounds(1093, 106, 150, 35);
		pTTSP.add(btnXa);

		JLabel lblNewLabel = new JLabel("Danh mục dụng cụ học tập");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 1253, 39);
		pQL_SP.add(lblNewLabel);
	}

	public boolean validateLSP() {
		String tenLSP = txtTenSP.getText().trim();
		if (!(tenLSP.length() > 0 && tenLSP.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtTenSP);
			return false;
		}
		int donGia = Integer.valueOf(txtDonGia.getText());
		if (donGia <= 0) {
			return false;
		}
		int soLuong = Integer.valueOf(txtDonGia.getText());
		if (soLuong <= 0) {
			return false;
		}
		return true;
	}

	public void xoaTrang() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		btnSuaSP.setEnabled(false);
		btnLuu.setEnabled(false);
		btnXa.setEnabled(false);
	}

	public void loadSP() throws Exception {
		SanPhamDao spDao = new SanPhamDao();
		DungCuHocTapDao spkDao = new DungCuHocTapDao();
		int tblRow = table_1.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tbtDSSPModel1.removeRow(i);
		}
		for (SanPham sp : spDao.getallSP()) {
			DungCuHocTap spk = spkDao.getSPTheoID(sp.getMaSP());
			if (spk != null) {
				tbtDSSPModel1.addRow(new Object[] { 
						spk.getMaSP(), 
						spk.getTenSP(),
						spk.getDonGia(),
						spk.getSoLuong(),
						spk.getDanhMuc().getTenDM(), 
						spk.getNhaCungCap().getTenNCC() });
			}
		}
	}

	public void loadLSP() throws Exception {
		cbbDanhMuc.removeAllItems();
		for (LoaiSanPham dm : loaiSanPhamDao.getAllDMCungLoai(0)) {
			cbbDanhMuc.addItem(dm.getTenDM());
		}
	}

	public void loadNCC() throws Exception {
		cbbNCC.removeAllItems();
		for (NhaCungCap dm : nhaCungCapDao.getallNCC()) {
			cbbNCC.addItem(dm.getTenNCC());
		}
	}

	public int soDCHT() {
		try {
			return cuHocTapDao.demSoDCHT();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public String loadMaTheotenNCC(String tenTGCanTim) {
		return nhaCungCapDao.getMaNCCTheoTen(tenTGCanTim);
	}

	public String loadMaTheoTenLSP(String tenLSPCanTim) {
		return loaiSanPhamDao.getMaLSPTheoTen(tenLSPCanTim);
	}

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
