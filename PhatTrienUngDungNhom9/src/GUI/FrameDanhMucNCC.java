package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.LoaiSanPhamDao;
import Dao.NhaCungCapDao;
import Dao.SanPhamDao;
import Entity.LoaiSanPham;
import Entity.NhaCungCap;
import Entity.Sach;
import Entity.SanPham;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameDanhMucNCC extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private DefaultTableModel tableModel;
	private JTextField txtTenNCC;
	private JTextField txtMaNCC;
	private JTable table;
	private JTextField txtDiaChi;
	private JTextField txtQuocTich;
	private JButton btnThem, btnSua, btnXoaTrang, btnLuu;
	private NhaCungCapDao spsDao = new NhaCungCapDao();
	private JButton btnXa;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDanhMucNCC frame = new FrameDanhMucNCC();
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
	public FrameDanhMucNCC() {
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

		String col[] = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Quốc tịch" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = table.getSelectedRow();
				txtMaNCC.setText((String) table.getValueAt(rowSelect, 0));
				txtTenNCC.setText((String) table.getValueAt(rowSelect, 1));
				txtDiaChi.setText((String) table.getValueAt(rowSelect, 2));
				txtQuocTich.setText((String) table.getValueAt(rowSelect, 3));
				btnThem.setEnabled(false);btnLuu.setEnabled(false);
				btnSua.setEnabled(true);btnXa.setEnabled(true);
			}
		});
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 319, 1253, 311);
		pQL_LoaiSanPham.add(scrollPane);

		JPanel pTTNCC = new JPanel();
		pTTNCC.setBounds(10, 61, 1253, 247);
		pQL_LoaiSanPham.add(pTTNCC);
		pTTNCC.setBackground(Color.WHITE);
		pTTNCC.setLayout(null);

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaNCC.setEditable(false);
		txtMaNCC.setBounds(497, 25, 400, 30);
		pTTNCC.add(txtMaNCC);
		txtMaNCC.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Mã nhà cung cấp :");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11.setBounds(337, 30, 150, 20);
		pTTNCC.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Tên nhà cung cấp :");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11_1.setBounds(337, 85, 150, 20);
		pTTNCC.add(lblNewLabel_11_1);

		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(497, 80, 400, 30);
		pTTNCC.add(txtTenNCC);

		JLabel lblNewLabel_11_2 = new JLabel("Địa chỉ :");
		lblNewLabel_11_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11_2.setBounds(337, 140, 150, 20);
		pTTNCC.add(lblNewLabel_11_2);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(497, 135, 400, 30);
		pTTNCC.add(txtDiaChi);

		txtQuocTich = new JTextField();
		txtQuocTich.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuocTich.setColumns(10);
		txtQuocTich.setBounds(497, 190, 400, 30);
		pTTNCC.add(txtQuocTich);

		JLabel lblNewLabel_11_1_1 = new JLabel("Quốc tịch :");
		lblNewLabel_11_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11_1_1.setBounds(337, 195, 150, 20);
		pTTNCC.add(lblNewLabel_11_1_1);

		btnSua = new JButton("Cập nhật");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setEnabled(false);
		btnSua.setBounds(10, 60, 150, 32);
		pTTNCC.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String tenNCC= txtTenNCC.getText().trim();
					String maNCC= txtMaNCC.getText().trim();
					String daiChi= txtDiaChi.getText().trim();
					String quocGia= txtQuocTich.getText().trim();				
					NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, daiChi, quocGia);
					try {
						spsDao.update(nhaCungCap, maNCC);
						loadNCC();
						xoaTrang();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSua.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnCapNhat.png")));

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaNCC.setText("NCC"+(soSach()+1));
				btnLuu.setEnabled(true);
			}
		});
		btnThem.setBounds(10, 152, 150, 32);
		pTTNCC.add(btnThem);
		btnThem.setIcon(new ImageIcon(FrameDanhMucSach.class.getResource("/image/btnThem.png")));

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBounds(10, 198, 150, 32);
		pTTNCC.add(btnXoaTrang);
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucNCC.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon(FrameDanhMucNCC.class.getResource("/image/btnSave.png")));
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String tenNCC= txtTenNCC.getText().trim();
					String maNCC= txtMaNCC.getText().trim();
					String daiChi= txtDiaChi.getText().trim();
					String quocGia= txtQuocTich.getText().trim();
					
					NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, daiChi, quocGia);
					
					try {
						spsDao.create(nhaCungCap);
						showMessage("ThemThanhCong", txtTenNCC);
						tableModel.addRow(new Object[] {
								nhaCungCap.getMaNCC(),nhaCungCap.getTenNCC(),nhaCungCap.getDiaChi(),nhaCungCap.getQuocTich()
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
		btnLuu.setBounds(10, 14, 150, 32);
		pTTNCC.add(btnLuu);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(400, 200, 430, 211);
		pQL_LoaiSanPham.add(panel);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDungCu= txtMaNCC.getText();
				int n= JOptionPane.showConfirmDialog(
                        panel, 
                        "Bạn có chắc muốn XÓA NHÀ CUNG CẤP này?", 
                        "Thông báo xác nhận XÓA NHÀ CUNG CẤP này", 
                        JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {		
					try {
						spsDao.delete(maDungCu);
						loadNCC();
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnXa.setIcon(new ImageIcon(FrameDanhMucNCC.class.getResource("/image/btnXoa.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXa.setEnabled(false);
		btnXa.setBounds(10, 106, 150, 32);
		pTTNCC.add(btnXa);

		JLabel lblDanhMcNh = new JLabel("Danh mục nhà cung cấp");
		lblDanhMcNh.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhMcNh.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDanhMcNh.setBounds(10, 11, 1253, 39);
		pQL_LoaiSanPham.add(lblDanhMcNh);
	}
	public void loadNCC() throws ClassNotFoundException, SQLException {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (NhaCungCap sp : spsDao.getallNCC()) {
				tableModel.addRow(new Object[] { 
						sp.getMaNCC(),sp.getTenNCC(),sp.getDiaChi(),sp.getQuocTich()
				});
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
	
	public boolean validateLSP() {
		String tenNCC = txtTenNCC.getText().trim();
		if (!(tenNCC.length() > 0 && tenNCC.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtTenNCC);
			return false;
		}
		String diaChi = txtDiaChi.getText().trim();
		if (!(diaChi.length() > 0 && diaChi.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtDiaChi);
			return false;
		}
		String quocGia = txtQuocTich.getText().trim();
		if (!(quocGia.length() > 0 && quocGia.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtQuocTich);
			return false;
		}
		return true;
	}
	void xoaTrang() {
		txtMaNCC.setText("");txtTenNCC.setText("");txtDiaChi.setText("");txtQuocTich.setText("");
		btnLuu.setEnabled(false);btnSua.setEnabled(false);btnXa.setEnabled(false);btnThem.setEnabled(true);
	}
	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
