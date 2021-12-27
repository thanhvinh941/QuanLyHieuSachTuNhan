package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDB;
import Dao.LoaiSanPhamDao;
import Entity.LoaiSanPham;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class FrameDanhMucDM extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTextField txtTenDM;
	private JTextField txtMaDM;
	private JTable table;
	private JButton btnThemDM;
	private JButton btnSuaDM;
	private JButton btnXoaTrangDM,btnLuu;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel lblDanhMcLoi;
	private LoaiSanPhamDao loaiSanPhamDao = new LoaiSanPhamDao();
	private JButton btnXa;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameDanhMucDM frame = new FrameDanhMucDM();
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
	public FrameDanhMucDM() throws Exception {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pQL_LoaiSanPham = new JPanel();
		pQL_LoaiSanPham.setBackground(Color.WHITE);
		pQL_LoaiSanPham.setBounds(0, 0, 1186, 654);
		getContentPane().add(pQL_LoaiSanPham);
		pQL_LoaiSanPham.setLayout(null);

		String col[] = { "Mã loại sản phẩm", "Tên loại sản phẩm", "Loại sản phẩm" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(25);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = table.getSelectedRow();
				String maLSP = (String) table.getValueAt(rowSelect, 0);
				String tenLSP = (String) table.getValueAt(rowSelect, 1);
				String loaiLSP = (String) table.getValueAt(rowSelect, 2);

				txtMaDM.setText(maLSP);
				txtMaDM.setEditable(false);
				txtTenDM.setText(tenLSP);
				if (loaiLSP.equalsIgnoreCase("Sách")) {
					comboBox.setSelectedIndex(1);
				} else {
					comboBox.setSelectedIndex(0);
				}
				btnThemDM.setEnabled(false);
				btnSuaDM.setEnabled(true);
				btnXa.setEnabled(true);btnLuu.setEnabled(false);
			}
		});
		table.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 302, 1253, 332);
		pQL_LoaiSanPham.add(scrollPane);

		JPanel pTTDM = new JPanel();
		pTTDM.setBounds(10, 61, 1253, 230);
		pQL_LoaiSanPham.add(pTTDM);
		pTTDM.setBackground(Color.WHITE);
		pTTDM.setLayout(null);

		txtMaDM = new JTextField();
		txtMaDM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaDM.setEditable(false);
		txtMaDM.setBounds(461, 34, 500, 30);
		pTTDM.add(txtMaDM);
		txtMaDM.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Mã loại sản phẩm :");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11.setBounds(258, 39, 170, 20);
		pTTDM.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Tên loại sản phẩm :");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11_1.setBounds(258, 103, 170, 20);
		pTTDM.add(lblNewLabel_11_1);

		txtTenDM = new JTextField();
		txtTenDM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenDM.setColumns(10);
		txtTenDM.setBounds(461, 98, 500, 30);
		pTTDM.add(txtTenDM);

		JLabel lblNewLabel_12 = new JLabel("Loại sản phẩm :");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_12.setBounds(258, 170, 170, 20);
		pTTDM.add(lblNewLabel_12);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] {"Dụng cụ học tập","Sách"}));
		comboBox.setBounds(461, 162, 500, 34);
		pTTDM.add(comboBox);

		btnThemDM = new JButton("Thêm");
		btnThemDM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemDM.setBounds(10, 140, 150, 32);
		pTTDM.add(btnThemDM);
		btnThemDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
				txtMaDM.setText("LSP"+(getSoLSP()+1));
				btnLuu.setEnabled(true);
			}
		});
		btnThemDM.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnThem.png")));

		btnSuaDM = new JButton("Cập nhật");
		btnSuaDM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSuaDM.setBounds(10, 54, 150, 32);
		pTTDM.add(btnSuaDM);
		btnSuaDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String maLSP = txtMaDM.getText();
					String tenLSP = txtTenDM.getText().trim();
					int selectCBB = comboBox.getSelectedIndex();
					LoaiSanPham loaiSanPham = new LoaiSanPham(maLSP, tenLSP, selectCBB);
					System.out.println(loaiSanPham);
					try {
						loaiSanPhamDao.capNhat(loaiSanPham, maLSP);
						loadDM();
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSuaDM.setEnabled(false);
		btnSuaDM.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnCapNhat.png")));

		btnXoaTrangDM = new JButton("Xóa trắng");
		btnXoaTrangDM.setBounds(10, 183, 150, 32);
		pTTDM.add(btnXoaTrangDM);
		btnXoaTrangDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrangDM.setIcon(new ImageIcon(FrameDanhMucDM.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrangDM.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String maLSP = txtMaDM.getText();
					String tenLSP = txtTenDM.getText().trim();
					int selectCBB = comboBox.getSelectedIndex();
					LoaiSanPham loaiSanPham = new LoaiSanPham(maLSP, tenLSP, selectCBB);
					try {
						loaiSanPhamDao.themDM(loaiSanPham);
						showMessage("Thêm thành công", txtMaDM);
						String danhMuc="Sách";
						if(loaiSanPham.isLoaiDM()==0) {
							danhMuc="Dụng cụ học tập";
						}
						tableModel.addRow(new Object[] {
							loaiSanPham.getMaDM(),loaiSanPham.getTenDM(),danhMuc
						});
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				xoaTrang();
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.setIcon(new ImageIcon(FrameDanhMucDM.class.getResource("/image/btnSave.png")));
		btnLuu.setBounds(10, 11, 150, 32);
		pTTDM.add(btnLuu);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(400, 200, 430, 211);
		pQL_LoaiSanPham.add(panel);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maLSP= txtMaDM.getText();
				int n= JOptionPane.showConfirmDialog(
                        panel, 
                        "Bạn có chắc muốn XÓA LOẠI SẢN PHẨM này?", 
                        "Thông báo xác nhận XÓA LOẠI SẢN PHẨM này", 
                        JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {		
					try {
						loaiSanPhamDao.delete(maLSP);
						loadDM();
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		panel.setLayout(null);
		btnXa.setIcon(new ImageIcon(FrameDanhMucDM.class.getResource("/image/btnXoa.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXa.setEnabled(false);
		btnXa.setBounds(10, 97, 150, 32);
		pTTDM.add(btnXa);
		
		lblDanhMcLoi = new JLabel("Danh mục loại sản phẩm");
		lblDanhMcLoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhMcLoi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDanhMcLoi.setBounds(9, 11, 1254, 39);
		pQL_LoaiSanPham.add(lblDanhMcLoi);
		loadDM();
	}
	
	public void xoaTrang() {
		txtMaDM.setText("");
		txtTenDM.setText("");
		comboBox.setSelectedIndex(0);
		btnThemDM.setEnabled(true);
		btnSuaDM.setEnabled(false);
		btnXa.setEnabled(false);
	}

	public void loadDM() throws Exception {
		LoaiSanPhamDao dmDao = new LoaiSanPhamDao();
 		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (LoaiSanPham dm : dmDao.getAllDM()) {
			String loaiDM = "Sách";
			if (dm.isLoaiDM() == 0) {
				loaiDM = "Dụng cụ học tập";
			}
			tableModel.addRow(new Object[] { dm.getMaDM(), dm.getTenDM(), loaiDM });
		}
	}

	public boolean validateLSP() {
		String tenLSP = txtTenDM.getText().trim();
		if (!(tenLSP.length() > 0 && tenLSP.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtTenDM);
			return false;
		}
		return true;
	}

	public int getSoLSP() {
		try {
			return loaiSanPhamDao.demLSP();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
