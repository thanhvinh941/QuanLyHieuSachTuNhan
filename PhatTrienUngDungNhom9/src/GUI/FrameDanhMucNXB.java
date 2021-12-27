package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDB;
import Dao.NhaXuatBanDao;
import Dao.SanPhamDao;
import Entity.NhaXuatBan;
import javax.swing.SwingConstants;

public class FrameDanhMucNXB extends JInternalFrame {
	private JPanel contentPane;
	private JTable tableNXB;
	private JTable tableNXB_1;
	private DefaultTableModel dftableNXB;
	private ArrayList<NhaXuatBan> listNXB;
	NhaXuatBanDao NXB_dao;
	private JTextField txtMaNXB;
	private JTextField txtTenNXB;
	private JTextField txtDiaChi;
	private JButton btnThem, btnSua, btnXoaTrang, btnLuu;
	private NhaXuatBanDao spsDao = new NhaXuatBanDao();
	private JButton btnXa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDanhMucNXB frame = new FrameDanhMucNXB();
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
	 * @throws ClassNotFoundException
	 */
	public FrameDanhMucNXB() throws ClassNotFoundException {
		NXB_dao = new NhaXuatBanDao();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pNXB = new JPanel();
		pNXB.setBackground(Color.WHITE);
		pNXB.setBounds(0, 0, 1186, 654);
		getContentPane().add(pNXB);
		pNXB.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 212, 1253, 418);
		pNXB.add(scrollPane);
		
		String[] table = { "Mã nhà xuất bản", "Tên nhà xuất bản", "Địa chỉ" };
		dftableNXB = new DefaultTableModel(table, 0);
		tableNXB_1 = new JTable(dftableNXB);
		tableNXB_1.setFillsViewportHeight(true);
		tableNXB_1.setColumnSelectionAllowed(true);
		tableNXB_1.setCellSelectionEnabled(true);
		tableNXB_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableNXB_1.setRowHeight(25);
		tableNXB_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableNXB_1.getSelectedRow();
				txtMaNXB.setText((String) tableNXB_1.getValueAt(rowSelect, 0));
				txtTenNXB.setText((String) tableNXB_1.getValueAt(rowSelect, 1));
				txtDiaChi.setText((String) tableNXB_1.getValueAt(rowSelect, 2));
				btnSua.setEnabled(true);btnLuu.setEnabled(false);
				btnXa.setEnabled(true);btnThem.setEnabled(false);
			}
		});
		scrollPane.setViewportView(tableNXB_1);

		JPanel pTTDM = new JPanel();
		pTTDM.setLayout(null);
		pTTDM.setBackground(Color.WHITE);
		pTTDM.setBounds(10, 61, 1253, 140);
		pNXB.add(pTTDM);

		txtMaNXB = new JTextField();
		txtMaNXB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaNXB.setEditable(false);
		txtMaNXB.setColumns(10);
		txtMaNXB.setBounds(170, 12, 350, 30);
		pTTDM.add(txtMaNXB);

		JLabel lblNewLabel_11 = new JLabel("Mã nhà xuất bản :");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11.setBounds(10, 17, 150, 20);
		pTTDM.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Tên nhà xuất bản :");
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11_1.setBounds(9, 59, 150, 20);
		pTTDM.add(lblNewLabel_11_1);

		txtTenNXB = new JTextField();
		txtTenNXB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenNXB.setColumns(10);
		txtTenNXB.setBounds(170, 54, 350, 30);
		pTTDM.add(txtTenNXB);

		JLabel lblNewLabel_12 = new JLabel("Địa chỉ :");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_12.setBounds(10, 101, 150, 20);
		pTTDM.add(lblNewLabel_12);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(FrameDanhMucNXB.class.getResource("/image/btnThem.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
				txtMaNXB.setText("NXB" + (soSach() + 1));
				btnLuu.setEnabled(true);
			}
		});
		btnThem.setBounds(773, 91, 150, 40);
		pTTDM.add(btnThem);

		btnSua = new JButton("Cập nhật");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valiDataNXB()) {
					String maNXB = txtMaNXB.getText().trim();
					String tenNXB = txtTenNXB.getText().trim();
					String diaChi = txtDiaChi.getText().trim();

					NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, diaChi);
					NhaXuatBanDao nhaXuatBanDao = new NhaXuatBanDao();
					try {
						nhaXuatBanDao.update(nxb,maNXB);
						showTableNXB();
						xoaTrang();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSua.setIcon(new ImageIcon(FrameDanhMucNXB.class.getResource("/image/btnCapNhat.png")));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setEnabled(false);
		btnSua.setBounds(933, 91, 150, 40);
		pTTDM.add(btnSua);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucNXB.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoaTrang.setBounds(613, 91, 150, 40);
		pTTDM.add(btnXoaTrang);

		btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon(FrameDanhMucNXB.class.getResource("/image/btnSave.png")));
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (valiDataNXB()) {
					String maNXB = txtMaNXB.getText().trim();
					String tenNXB = txtTenNXB.getText().trim();
					String diaChi = txtDiaChi.getText().trim();

					NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, diaChi);

					try {
						spsDao.create(nxb);
						showMessage("ThemThanhCong", txtTenNXB);
						dftableNXB.addRow(new Object[] { nxb.getMaNXB(), nxb.getTenNXB(), nxb.getDiaChi() });
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnLuu.setEnabled(false);
		btnLuu.setBounds(1093, 39, 150, 40);
		pTTDM.add(btnLuu);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(170, 96, 350, 30);
		pTTDM.add(txtDiaChi);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(400, 200, 430, 211);
		pNXB.add(panel);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maNXB = txtMaNXB.getText().trim();
				int n= JOptionPane.showConfirmDialog(
                        panel, 
                        "Bạn có chắc muốn XÓA NHÀ XUẤT BẢN này?", 
                        "Thông báo xác nhận XÓA NHÀ XUẤT BẢN này", 
                        JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {		
					try {
						NhaXuatBanDao nhaXuatBanDao = new NhaXuatBanDao();
						nhaXuatBanDao.delete(maNXB);
						showTableNXB();
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnXa.setIcon(new ImageIcon(FrameDanhMucNXB.class.getResource("/image/btnXoa.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXa.setEnabled(false);
		btnXa.setBounds(1093, 91, 150, 40);
		pTTDM.add(btnXa);

		JLabel lblDanhMcNh = new JLabel("Danh mục nhà xuất bản");
		lblDanhMcNh.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhMcNh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDanhMcNh.setBounds(9, 11, 1254, 39);
		pNXB.add(lblDanhMcNh);
	}

	public void showTableNXB() throws ClassNotFoundException {

		listNXB = new NhaXuatBanDao().getallNXB();

		dftableNXB = (DefaultTableModel) tableNXB_1.getModel();
		if (tableNXB_1.getRowCount() > 0) {
			for (int i = tableNXB_1.getRowCount() - 1; i > -1; i--) {
				dftableNXB.removeRow(i);
			}
		}
		dftableNXB.setColumnIdentifiers(new Object[] { "Mã xuất bản", "Tên nhà xuất bản", "Địa chỉ" });
		for (NhaXuatBan nxb : listNXB) {
			dftableNXB.addRow(new Object[] { nxb.getMaNXB(), nxb.getTenNXB(), nxb.getDiaChi() });
		}
	}

	public boolean valiDataNXB() {
		String ma = txtMaNXB.getText().trim();
		String ten = txtTenNXB.getText().trim();
		String diaChi = txtDiaChi.getText().trim();

		if (!(ma.length() > 0 && ma.matches("^(NXB)\\d{1}$"))) {
			showMessage("Error: Ma Nha Xuat Ban Theo Mau: KH\\d{3,4}\n VD:NXB001", txtMaNXB);
			return false;
		}
		if (!(ten.length() > 0 && ten.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Ten  Nha Xuat Ban	Phai Theo Mau:[A-Z][a-zA-Z' ]+\n VD:Ha Noi", txtTenNXB);
			return false;
		}
		if (!(diaChi.length() > 0 && diaChi.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage(
					"Error: Dia Chi  Nha Xuat Ban Phai Theo Mau: ^[a-z0-9_\\.-]+$\n VD:12 Nguyá»…n VÄƒn Báº£o, PhÆ°á»�ng 4, GÃ² Váº¥p, ThÃ nh phá»‘ Há»“ ChÃ­ Minh",
					txtDiaChi);
			return false;
		}
		return true;
	}

	public int soSach() {
		try {
			return spsDao.demSoSach();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	void xoaTrang() {
		txtMaNXB.setText("");
		txtTenNXB.setText("");
		txtDiaChi.setText("");
		btnLuu.setEnabled(false);
		btnSua.setEnabled(false);
	}

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
}
