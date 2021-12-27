package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Dao.SanPhamDao;
import Dao.TacGiaDao;
import Entity.NhaCungCap;
import Entity.TacGia;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameDanhMucTG extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtMaTG;
	private JTextField txtTenTG;
	private JTextField txtQT;
	private JButton btnThem, btnSua, btnXoaTrang, btnLuu;
	private TacGiaDao spsDao = new TacGiaDao();
	private JButton btnXa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDanhMucTG frame = new FrameDanhMucTG();
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
	public FrameDanhMucTG() {
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

		String col[] = { "Mã tác giả", "Tên tác giả", "Quốc tịch" };
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = table.getSelectedRow();
				txtTenTG.setText((String) table.getValueAt(rowSelect, 1));
				txtMaTG.setText((String) table.getValueAt(rowSelect, 0));
				txtQT.setText((String) table.getValueAt(rowSelect, 2));
				
				btnSua.setEnabled(true);btnXa.setEnabled(true);btnThem.setEnabled(false);btnLuu.setEnabled(false);
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(25);
		table.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 302, 1253, 328);
		pQL_LoaiSanPham.add(scrollPane);

		JPanel pTTDM = new JPanel();
		pTTDM.setLayout(null);
		pTTDM.setBackground(Color.WHITE);
		pTTDM.setBounds(10, 61, 1253, 230);
		pQL_LoaiSanPham.add(pTTDM);

		txtMaTG = new JTextField();
		txtMaTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaTG.setEditable(false);
		txtMaTG.setColumns(10);
		txtMaTG.setBounds(469, 35, 400, 30);
		pTTDM.add(txtMaTG);

		JLabel lblNewLabel_11 = new JLabel("Mã tác giả :");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11.setBounds(309, 40, 150, 20);
		pTTDM.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Tên tác giả :");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11_1.setBounds(309, 105, 150, 20);
		pTTDM.add(lblNewLabel_11_1);

		txtTenTG = new JTextField();
		txtTenTG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenTG.setColumns(10);
		txtTenTG.setBounds(469, 100, 400, 30);
		pTTDM.add(txtTenTG);

		JLabel lblNewLabel_12 = new JLabel("Quốc tịch :");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_12.setBounds(309, 170, 150, 20);
		pTTDM.add(lblNewLabel_12);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.setIcon(new ImageIcon(FrameDanhMucTG.class.getResource("/image/btnThem.png")));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
				txtMaTG.setText("TG" + (soSach() + 1));
				btnLuu.setEnabled(true);
			}
		});
		btnThem.setBounds(10, 140, 150, 32);
		pTTDM.add(btnThem);

		btnSua = new JButton("Cập nhật");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateLSP()) {
					String maTG = txtMaTG.getText();
					String tenTG = txtTenTG.getText().trim();
					String quocTich = txtQT.getText().trim();

					TacGia tg = new TacGia(maTG, tenTG, quocTich);
					try {
						spsDao.update(tg,maTG);
						loadTG();
						xoaTrang();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setIcon(new ImageIcon(FrameDanhMucTG.class.getResource("/image/btnCapNhat.png")));
		btnSua.setEnabled(false);
		btnSua.setBounds(10, 54, 150, 32);
		pTTDM.add(btnSua);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucTG.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
			}
		});
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoaTrang.setBounds(10, 183, 150, 32);
		pTTDM.add(btnXoaTrang);

		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLuu.setIcon(new ImageIcon(FrameDanhMucTG.class.getResource("/image/btnSave.png")));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateLSP()) {
					String maTG = txtMaTG.getText();
					String tenTG = txtTenTG.getText().trim();
					String quocTich = txtQT.getText().trim();

					TacGia tg = new TacGia(maTG, tenTG, quocTich);
					try {
						spsDao.create(tg);
						showMessage("Them Thanh Cong", txtTenTG);
						tableModel.addRow(new Object[] { tg.getMaTG(), tg.getTenTG(), tg.getQuocTich() });
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					xoaTrang();
				}
			}
		});
		btnLuu.setBounds(10, 11, 150, 32);
		pTTDM.add(btnLuu);

		txtQT = new JTextField();
		txtQT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQT.setColumns(10);
		txtQT.setBounds(469, 165, 400, 30);
		pTTDM.add(txtQT);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(400, 200, 430, 211);
		pQL_LoaiSanPham.add(panel);
		
		btnXa = new JButton("Xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maTG = txtMaTG.getText();
				int n= JOptionPane.showConfirmDialog(
                        panel, 
                        "Bạn có chắc muốn XÓA TÁC GIẢ này?", 
                        "Thông báo xác nhận XÓA TÁC GIẢ này", 
                        JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {		
					try {
						spsDao.delete(maTG);
						loadTG();
						xoaTrang();;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnXa.setIcon(new ImageIcon(FrameDanhMucTG.class.getResource("/image/btnXoa.png")));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXa.setEnabled(false);
		btnXa.setBounds(10, 97, 150, 32);
		pTTDM.add(btnXa);

		JLabel lblDanhMcTc = new JLabel("Danh mục tác giả");
		lblDanhMcTc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhMcTc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDanhMcTc.setBounds(9, 11, 1254, 39);
		pQL_LoaiSanPham.add(lblDanhMcTc);
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
	public void loadTG() throws ClassNotFoundException, SQLException {
		int tblRow = table.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (TacGia sp : spsDao.getallTG()) {
				tableModel.addRow(new Object[] { 
						sp.getMaTG(),sp.getTenTG(),sp.getQuocTich()
				});
		}
	}
	public void xoaTrang() {
		txtMaTG.setText("");
		txtTenTG.setText("");
		txtQT.setText("");
		btnSua.setEnabled(false);
		btnLuu.setEnabled(false);
		btnXa.setEnabled(false);
		btnThem.setEnabled(true);
	}

	public boolean validateLSP() {
		String TenTG = txtTenTG.getText().trim();
		if (!(TenTG.length() > 0 && TenTG.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtTenTG);
			return false;
		}
		String quocGia = txtQT.getText().trim();
		if (!(quocGia.length() > 0 && quocGia.matches(
				"[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]+"))) {
			showMessage("Error: Tên loại sản phẩm không được trống hoặc có kí tự đặt biệt VD:Sách", txtQT);
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
