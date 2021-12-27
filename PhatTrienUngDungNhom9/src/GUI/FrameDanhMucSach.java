package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dao.LoaiSanPhamDao;
import Dao.NhaXuatBanDao;
import Dao.SanPhamDao;
import Dao.TacGiaDao;
import Dao.SachDao;
import Entity.LoaiSanPham;
import Entity.NhaXuatBan;
import Entity.SanPham;
import Entity.TacGia;
import Entity.Sach;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FrameDanhMucSach extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtSoTrang;
	private JTextField txtMaLSP;
	private JTextField txtMaNXB;
	private JTextField txtTenTG;
	private JTable tblDSSP;
	private DefaultTableModel tbtDSSPModel;
	private JComboBox<String> cbbDanhMuc, cbbNXB, cbbTG;
	private JButton btnThem, btnSua, btnXoaTrang, btnLuu;
	private TacGiaDao tgDao = new TacGiaDao();
	private NhaXuatBanDao nxbDao = new NhaXuatBanDao();
	private LoaiSanPhamDao lspDao = new LoaiSanPhamDao();
	private SanPhamDao spDao = new SanPhamDao();
	private SachDao spsDao = new SachDao();
	private JButton btnXa;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameQLSP frame = new FrameQLSP();
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
	public FrameDanhMucSach() throws Exception {
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

		String[] col = { "Mã sách", "Tên sách", "Đơn giá", "Số lượng", "Loại sách", "Tác giả", "Số trang",
				"Nhà xuất bản" };
		tbtDSSPModel = new DefaultTableModel(col, 0);
		tblDSSP = new JTable(tbtDSSPModel);
		tblDSSP.setFillsViewportHeight(true);
		tblDSSP.setColumnSelectionAllowed(true);
		tblDSSP.setCellSelectionEnabled(true);
		tblDSSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tblDSSP.getSelectedRow();
				String maS =  (String) tblDSSP.getValueAt(rowSelect, 0);
				String tenS = (String) tblDSSP.getValueAt(rowSelect, 1);
				int donGia = (int) tblDSSP.getValueAt(rowSelect, 2);
				int soLuong = (Integer) tblDSSP.getValueAt(rowSelect, 3);
				String tenLSP = (String) tblDSSP.getValueAt(rowSelect, 4);
				String tenTg = (String) tblDSSP.getValueAt(rowSelect, 5);
				int soTrang = (int) tblDSSP.getValueAt(rowSelect, 6);
				String tenNXB = (String) tblDSSP.getValueAt(rowSelect, 7);
				txtMaSP.setText(maS);
				txtTenSP.setText(tenS);
				txtDonGia.setText(String.valueOf(donGia));
				txtSoLuong.setText(String.valueOf(soLuong));
				txtSoTrang.setText(String.valueOf(soTrang));
				for(int i=0;i<cbbDanhMuc.getItemCount();i++) {
					if(tenLSP.equalsIgnoreCase((String) cbbDanhMuc.getItemAt(i))) {
						cbbDanhMuc.setSelectedIndex(i);
					}
				}
				for(int i=0;i<cbbNXB.getItemCount();i++) {
					if(tenNXB.equalsIgnoreCase((String) cbbNXB.getItemAt(i))) {
						cbbNXB.setSelectedIndex(i);
					}
				}
				for(int i=0;i<cbbTG.getItemCount();i++) {
					if(tenTg.equalsIgnoreCase((String) cbbTG.getItemAt(i))) {
						cbbTG.setSelectedIndex(i);
					}
				}
				btnSua.setEnabled(true);btnLuu.setEnabled(false);
				btnXa.setEnabled(true);btnThem.setEnabled(false);
			}
		});
		tblDSSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblDSSP.setRowHeight(25);
		tblDSSP.setBounds(10, 25, 951, 248);
		tblDSSP.getColumnModel().getColumn(0).setPreferredWidth(25);
		tblDSSP.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblDSSP.getColumnModel().getColumn(2).setPreferredWidth(25);
		tblDSSP.getColumnModel().getColumn(3).setPreferredWidth(20);
		tblDSSP.getColumnModel().getColumn(4).setPreferredWidth(25);
		tblDSSP.getColumnModel().getColumn(6).setPreferredWidth(20);
		JScrollPane pJScrollPane = new JScrollPane(tblDSSP);
		pJScrollPane.setBounds(10, 319, 1253, 311);
		pQL_SP.add(pJScrollPane);

		JPanel pTTSP = new JPanel();
		pTTSP.setBounds(10, 61, 1253, 247);
		pQL_SP.add(pTTSP);
		pTTSP.setBackground(Color.WHITE);
		pTTSP.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Mã sản phẩm :");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14.setBounds(10, 20, 150, 20);
		pTTSP.add(lblNewLabel_14);

		txtMaSP = new JTextField();
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaSP.setEditable(false);
		txtMaSP.setBounds(180, 15, 350, 30);
		pTTSP.add(txtMaSP);
		txtMaSP.setColumns(10);

		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(180, 60, 350, 30);
		pTTSP.add(txtTenSP);

		JLabel lblNewLabel_14_1 = new JLabel("Tên sản phẩm :");
		lblNewLabel_14_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_1.setBounds(10, 65, 150, 20);
		pTTSP.add(lblNewLabel_14_1);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(180, 105, 150, 30);
		pTTSP.add(txtDonGia);

		JLabel lblNewLabel_14_2 = new JLabel("Đơn giá :");
		lblNewLabel_14_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_2.setBounds(10, 110, 150, 20);
		pTTSP.add(lblNewLabel_14_2);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(450, 105, 80, 30);
		pTTSP.add(txtSoLuong);

		JLabel lblNewLabel_14_3 = new JLabel("Số lượng :");
		lblNewLabel_14_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_3.setBounds(340, 110, 100, 20);
		pTTSP.add(lblNewLabel_14_3);

		JLabel lblNewLabel_14_4 = new JLabel("Loại sách :");
		lblNewLabel_14_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_4.setBounds(570, 66, 150, 20);
		pTTSP.add(lblNewLabel_14_4);

		txtSoTrang = new JTextField();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoTrang.setColumns(10);
		txtSoTrang.setBounds(730, 198, 350, 30);
		pTTSP.add(txtSoTrang);

		JLabel lblNewLabel_14_5 = new JLabel("Tác giả :");
		lblNewLabel_14_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_5.setBounds(570, 157, 150, 20);
		pTTSP.add(lblNewLabel_14_5);

		JLabel lblNewLabel_14_6 = new JLabel("Số trang :");
		lblNewLabel_14_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_6.setBounds(570, 203, 150, 20);
		pTTSP.add(lblNewLabel_14_6);

		JLabel lblNewLabel_14_7 = new JLabel("Nhà xuất bản :");
		lblNewLabel_14_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_7.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_7.setBounds(10, 203, 150, 20);
		pTTSP.add(lblNewLabel_14_7);

		cbbDanhMuc = new JComboBox<>();
		cbbDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDanhMuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenLSP = (String) cbbDanhMuc.getSelectedItem();
				txtMaLSP.setText(loadMaTheoTenLSP(tenLSP));
			}
		});
		cbbDanhMuc.setBounds(730, 58, 350, 34);
		pTTSP.add(cbbDanhMuc);
		cbbNXB = new JComboBox<>();
		cbbNXB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbNXB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenNXB = (String) cbbNXB.getSelectedItem();
				txtMaNXB.setText(loadMaTheoTenNXB(tenNXB));
			}
		});
		cbbNXB.setBounds(180, 195, 350, 34);
		pTTSP.add(cbbNXB);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(1093, 153, 150, 35);
		pTTSP.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
				txtMaSP.setText("Sach"+(soSach()+1));
				btnLuu.setEnabled(true);
			}
		});
		btnThem.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnThem.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnSua = new JButton("Cập nhật");
		btnSua.setEnabled(false);
		btnSua.setBounds(1093, 59, 150, 35);
		pTTSP.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String maSach = txtMaSP.getText();
					String tenSach = txtTenSP.getText().trim();
					int soLuong = Integer.valueOf(txtSoLuong.getText()).intValue();
					int soTrang = Integer.valueOf(txtSoTrang.getText()).intValue();
					int donGia = Integer.valueOf(txtDonGia.getText()).intValue();
					LoaiSanPham loaiSanPham = null;
					TacGia tacGia = null;
					NhaXuatBan nhaXuatBan = null;
					try {
						 loaiSanPham = lspDao.getDMTheoID(txtMaLSP.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tacGia =tgDao.getTGTheoMaTg(txtTenTG.getText());
					try {
						nhaXuatBan = nxbDao.getNXBTheoID(txtMaNXB.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Sach sps = new Sach(maSach, tenSach, donGia, soLuong, loaiSanPham, tacGia, soTrang, nhaXuatBan);
					SachDao sachDao = new SachDao();
					try {
						sachDao.capnhat(sps, maSach);
						loadSP();
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSua.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnCapNhat.png")));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setBounds(1093, 200, 150, 35);
		pTTSP.add(btnXoaTrang);
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnSave.png")));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String maSach = txtMaSP.getText();
					String tenSach = txtTenSP.getText().trim();
					int soLuong = Integer.valueOf(txtSoLuong.getText()).intValue();
					int soTrang = Integer.valueOf(txtSoTrang.getText()).intValue();
					int donGia = Integer.valueOf(txtDonGia.getText()).intValue();
					LoaiSanPham loaiSanPham = null;
					TacGia tacGia = null;
					NhaXuatBan nhaXuatBan = null;
					try {
						 loaiSanPham = lspDao.getDMTheoID(txtMaLSP.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tacGia =tgDao.getTGTheoMaTg(txtTenTG.getText());
					try {
						nhaXuatBan = nxbDao.getNXBTheoID(txtMaNXB.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Sach sps = new Sach(maSach, tenSach, donGia, soLuong, loaiSanPham, tacGia, soTrang, nhaXuatBan);
					try {
						spsDao.themSPS(sps);
						showMessage("ThemThanhCong", txtTenSP);
						tbtDSSPModel.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), sps.getDonGia(), sps.getSoLuong(),
								sps.getDanhMuc().getTenDM(), sps.getTacGia().getTenTG(), sps.getSoTrang(),
								sps.getNhaXuatBan().getTenNXB()});
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					xoaTrang();
				}
			}
		});
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLuu.setBounds(1093, 12, 150, 35);
		pTTSP.add(btnLuu);

		cbbTG = new JComboBox<>();
		cbbTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbTG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenTG = (String) cbbTG.getSelectedItem();
				txtTenTG.setText(loadMaTheoTenTG(tenTG));
			}
		});
		
		cbbTG.setBounds(730, 150, 350, 34);
		pTTSP.add(cbbTG);

		JLabel lblNewLabel_14_1_1 = new JLabel("Mã loại sách :");
		lblNewLabel_14_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_1_1.setBounds(570, 19, 150, 20);
		pTTSP.add(lblNewLabel_14_1_1);

		txtMaLSP = new JTextField();
		txtMaLSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaLSP.setEditable(false);
		txtMaLSP.setColumns(10);
		txtMaLSP.setBounds(730, 14, 350, 30);
		pTTSP.add(txtMaLSP);

		JLabel lblNewLabel_14_1_2 = new JLabel("Mã nhà xuất bản :");
		lblNewLabel_14_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_1_2.setBounds(0, 155, 160, 20);
		pTTSP.add(lblNewLabel_14_1_2);

		txtMaNXB = new JTextField();
		txtMaNXB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaNXB.setEditable(false);
		txtMaNXB.setColumns(10);
		txtMaNXB.setBounds(180, 150, 350, 30);
		pTTSP.add(txtMaNXB);

		JLabel lblNewLabel_14_1_3 = new JLabel("Mã tác giả :");
		lblNewLabel_14_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14_1_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14_1_3.setBounds(570, 110, 150, 20);
		pTTSP.add(lblNewLabel_14_1_3);

		txtTenTG = new JTextField();
		txtTenTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenTG.setEditable(false);
		txtTenTG.setColumns(10);
		txtTenTG.setBounds(730, 105, 350, 30);
		pTTSP.add(txtTenTG);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(400, 200, 430, 211);
		pQL_SP.add(panel);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDungCu= txtMaSP.getText();
				int n= JOptionPane.showConfirmDialog(
                        panel, 
                        "Bạn có chắc muốn XÓA SÁCH này?", 
                        "Thông báo xác nhận XÓA SÁCH này", 
                        JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {		
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
		btnXa.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnXoa.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXa.setEnabled(false);
		btnXa.setBounds(1093, 106, 150, 35);
		pTTSP.add(btnXa);

		JLabel lblDanhMcSch = new JLabel("Danh mục sách");
		lblDanhMcSch.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhMcSch.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDanhMcSch.setBounds(10, 11, 1253, 39);
		pQL_SP.add(lblDanhMcSch);
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
		for (TacGia tg : tgDao.getallTG()) {
			cbbTG.addItem(tg.getTenTG());
		}
	}

	public void loadLSP() throws Exception {
		cbbDanhMuc.removeAllItems();
		for (LoaiSanPham dm : lspDao.getAllDMCungLoai(1)) {
			cbbDanhMuc.addItem(dm.getTenDM());
		}
	}

	public void loadNXB() throws ClassNotFoundException {
		cbbNXB.removeAllItems();
		for (NhaXuatBan dm : nxbDao.getallNXB()) {
			cbbNXB.addItem(dm.getTenNXB());
		}
	}

	public void loadSP() throws Exception {
		int tblRow = tblDSSP.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tbtDSSPModel.removeRow(i);
		}
		for (SanPham sp : spDao.getallSP()) {
			Sach sps = spsDao.getSPTheoID(sp.getMaSP());
			if (sps != null) {
				tbtDSSPModel.addRow(new Object[] { sps.getMaSP(), sps.getTenSP(), sps.getDonGia(), sps.getSoLuong(),
						sps.getDanhMuc().getTenDM(), sps.getTacGia().getTenTG(), sps.getSoTrang(),
						sps.getNhaXuatBan().getTenNXB() });
			}
		}
	}

	public int soSach(){
		try {
			return spsDao.demSoSach();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void xoaTrang() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		txtSoTrang.setText("");
		btnSua.setEnabled(false);
		btnLuu.setEnabled(false);
		btnThem.setEnabled(true);
	}
	

	public boolean validateLSP() {
		String tenLSP = txtTenSP.getText().trim();
		if (!(tenLSP.length() > 0 && tenLSP.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtTenSP);
			return false;
		}
		int donGia = Integer.valueOf(txtDonGia.getText());
		if(donGia <=0) {
			return false;
		}
		int soLuong = Integer.valueOf(txtDonGia.getText());
		if(soLuong <=0) {
			return false;
		}
		int soTrang = Integer.valueOf(txtSoTrang.getText());
		if(soTrang <=0) {
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
