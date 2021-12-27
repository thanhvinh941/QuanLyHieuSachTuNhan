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

import javax.swing.ButtonGroup;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDB;
import Dao.KhachHangDao;
import Entity.KhachHang;

public class FrameDanhMucKH extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dftableKH;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtSoDT;
	private JTextField txtCMND;
	private JComboBox cmbGioiTinh;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable tableKH;
	private ArrayList<KhachHang> listKH;
	private JButton btnThem, btnLuu, btnCapNhat, btnXoaTrang;

	KhachHangDao kh_dao;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameQLKH frame = new FrameQLKH();
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
	 * @throws ClassNotFoundException
	 */
	public FrameDanhMucKH() throws ClassNotFoundException {

		kh_dao = new KhachHangDao();
		cmbGioiTinh = new JComboBox<String>();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel panelKH = new JPanel();
		panelKH.setBounds(0, 0, 1186, 632);
		getContentPane().add(panelKH);
		panelKH.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Kh\u00E1ch h\u00E0ng", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("Khách hàng");
		panel.setBounds(10, 11, 1253, 275);
		panelKH.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng", TitledBorder.LEFT, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_2.setBounds(10, 23, 1233, 185);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã khách hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(10, 22, 116, 26);
		panel_2.add(lblNewLabel);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaKH.setBounds(147, 22, 443, 26);
		panel_2.add(txtMaKH);
		txtMaKH.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(629, 22, 116, 26);
		panel_2.add(lblNewLabel_1);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(746, 22, 477, 26);
		panel_2.add(txtTenKH);

		JLabel lblNewLabel_2 = new JLabel("Địa chỉ");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 69, 106, 26);
		panel_2.add(lblNewLabel_2);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(147, 69, 443, 26);
		panel_2.add(txtDiaChi);

		JLabel lblNewLabel_3 = new JLabel("Số điện thoại");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(629, 69, 116, 26);
		panel_2.add(lblNewLabel_3);

		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(746, 69, 477, 26);
		panel_2.add(txtSoDT);

		JLabel lblNewLabel_4_1 = new JLabel("Giới tính");
		lblNewLabel_4_1.setForeground(Color.BLACK);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4_1.setBounds(10, 123, 131, 26);
		panel_2.add(lblNewLabel_4_1);

		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cmbGioiTinh.setBounds(371, 123, 219, 26);
		panel_2.add(cmbGioiTinh);

		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputKH();
				txtMaKH.setText("KH" + (soKH() + 1));
				btnLuu.setEnabled(true);
			}
		});
		btnThem.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnThem.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(699, 219, 126, 32);
		panel.add(btnThem);

		btnLuu = new JButton(" Lưu");
		btnLuu.setEnabled(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (valiDataKH()) {
					int row = tableKH.getSelectedRow();
					String maKH = txtMaKH.getText();
					System.out.println(maKH);
					String tenKH = txtTenKH.getText();
					String diaChi = txtDiaChi.getText();
					String soDT = txtSoDT.getText();
					String gioiTinh = (String) cmbGioiTinh.getSelectedItem();
					KhachHang kh = new KhachHang(maKH, tenKH, soDT, diaChi, gioiTinh);
					try {
						Connection con = ConnectDB.getConnection();
						String sql = "Select maKH from KHACHHANG WHERE maKH ='" + maKH + "'";
						java.sql.Statement statement = con.createStatement();
						ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
						if (rs.next()) {
							showMessage("Mã bị trùng", txtMaKH);
						} else {
							if (kh_dao.create(kh)) {
								showMessage("Thêm thành công", txtMaKH);
								dftableKH.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSoDT(),
										kh.getDiaChi(), kh.getGioiTinh() });
								emityiuputKH();
							} else {
								showMessage("THAT BAI", txtMaKH);
							}
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnLuu.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnSave.png")));
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLuu.setBounds(149, 219, 126, 32);
		panel.add(btnLuu);

//	JButton btnThem = new JButton("Thêm");
//	btnThem.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			emityiuputKH();
//			txtMaKH.setText("KH"+(soKH()+1));
//			btnLuu.setEnabled(true);
//		}
//	});
//	btnThem.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnThem.png")));
//	btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
//	btnThem.setBounds(699, 219, 126, 32);
//	panel.add(btnThem);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMaKH.getText().trim().toString().isEmpty()) {
					showMessage("Vui chon khach hang de chinh sua", txtMaKH);
				} else {
					if (valiDataKH()) {
						int row = tableKH.getSelectedRow();
						if (row > 0) {
							String maKH = txtMaKH.getText();
							String tenKH = txtTenKH.getText();
							String diaChi = txtDiaChi.getText();
							String soDT = txtSoDT.getText();
							String gioiTinh = (String) cmbGioiTinh.getSelectedItem();
							KhachHang kh = new KhachHang(maKH, tenKH, soDT, diaChi, gioiTinh);
							try {
								if (kh_dao.update(kh)) {
									tableKH.setValueAt(txtMaKH.getText(), row, 0);
									tableKH.setValueAt(txtTenKH.getText(), row, 1);
									tableKH.setValueAt(txtDiaChi.getText(), row, 2);
									tableKH.setValueAt(txtSoDT.getText(), row, 3);
									tableKH.setValueAt(cmbGioiTinh.getSelectedItem().toString(), row, 4);
									showMessage("Cập nhật thành công", txtMaKH);
								}
							} catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		btnCapNhat.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnCapNhat.png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapNhat.setBounds(424, 219, 126, 32);
		panel.add(btnCapNhat);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputKH();
			}
		});
//	btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucKH.class.getResource("/image/CLEAR.png")));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrang.setBounds(974, 219, 126, 32);
		panel.add(btnXoaTrang);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 297, 1253, 333);
		panelKH.add(scrollPane);

		tableKH = new JTable();
		tableKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		String[] table = { "Mã khách hàng", "Tên Khách hàng", "Số điện thoại", "Địa chỉ", "Giới tính" };
		dftableKH = new DefaultTableModel(table, 0);
		tableKH = new JTable(dftableKH);
		tableKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaKH.setText(tableKH.getValueAt(tableKH.getSelectedRow(), 0).toString());
				txtTenKH.setText(tableKH.getValueAt(tableKH.getSelectedRow(), 1).toString());
				txtSoDT.setText(tableKH.getValueAt(tableKH.getSelectedRow(), 2).toString());
				txtDiaChi.setText(tableKH.getValueAt(tableKH.getSelectedRow(), 3).toString());
				cmbGioiTinh.setSelectedItem(tableKH.getValueAt(tableKH.getSelectedRow(), 4).toString());

			}
		});
		scrollPane.setViewportView(tableKH);
		showTableKH();
	}

	public void emityiuputKH() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtSoDT.setText("");

		cmbGioiTinh.setSelectedItem(null);
		txtMaKH.requestFocus();
	}

	public void showTableKH() throws ClassNotFoundException {

//    this.setLocationRelativeTo(null);
		listKH = new KhachHangDao().getalltbKhachHang();
		dftableKH = (DefaultTableModel) tableKH.getModel();
		if (tableKH.getRowCount() > 0) {
			for (int i = tableKH.getRowCount() - 1; i > -1; i--) {
				dftableKH.removeRow(i);
			}
		}
		dftableKH.setColumnIdentifiers(
				new Object[] { "Mã khách hàng", "Tên Khách hàng", "Số điện thoại", "Địa chỉ", "Giới tính" });
		for (KhachHang kh : listKH) {
			dftableKH.addRow(
					new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSoDT(), kh.getDiaChi(), kh.getGioiTinh() });
		}
	}

	public int soKH() {
		try {
			return kh_dao.demSoKH();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public boolean valiDataKH() {

		String ten = txtTenKH.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String sdt = txtSoDT.getText().trim();

		if (!(ten.length() > 0 && ten.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Ten  Khach Hang Phai Theo Mau : Nguyen Van Anh", txtTenKH);
			return false;
		}
		if (!(sdt.length() > 0 && sdt.matches("^[0-9]{10}$"))) {
			showMessage("Error: SDT  Khach Hang Phai Theo Mau:^[0-9]{10}$\n VD:0329324401", txtSoDT);
			return false;
		}
		if (!(diaChi.length() > 0 && diaChi.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage(
					"Error: Dia Chi  Khach Hang Phai Theo Mau: VD:12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành Phố Hồ Chí Minh",
					txtDiaChi);
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
