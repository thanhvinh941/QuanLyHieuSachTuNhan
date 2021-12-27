package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

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

import com.toedter.calendar.JDateChooser;

import Dao.CaDao;
import Dao.LichLamDao;
import Dao.NhanVienDao;
import Entity.Ca;
import Entity.Lich;
import Entity.NhanVien;
import java.awt.Font;

public class FrameHeThongDangKyCaLam extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3667234515727894528L;
	private DefaultTableModel tableModel1;
	private JTable tableTimNV;
	private JTextField txtTenNV;
	private JTextField txtMaNV;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JScrollPane scpTimNV;
	private static int Enter;
	private JDateChooser dateNgayLam;
	private LichLamDao lichLam = new LichLamDao();
	private CaDao ca = new CaDao();
	private JTable tableCA;
	private DefaultTableModel dftableCA;
	private JComboBox<String> cmbCaLam;
	private ArrayList<Lich> listCa;
	private JTextField txtMaCa;
	private LocalDate endDay = LocalDate.now();
	private JButton btnCapNhat,btnXoaTrang,btnThem,btnXoa;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameHeThongDangKyCaLam frame = new FrameHeThongDangKyCaLam();
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
	public FrameHeThongDangKyCaLam() throws ClassNotFoundException {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pCA = new JPanel();
		pCA.setBackground(Color.WHITE);
		pCA.setBounds(0, 0, 1186, 654);
		getContentPane().add(pCA);
		pCA.setLayout(null);

		String col1[] = { "Mã NV", "Tên NV", "SDT", "Địa chỉ" };
		tableModel1 = new DefaultTableModel(col1, 0);
		tableTimNV = new JTable(tableModel1);
		tableTimNV.setRowHeight(24);
		tableTimNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableTimNV.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableTimNV.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableTimNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableTimNV.getSelectedRow();
				String maNV = (String) tableTimNV.getValueAt(rowSelect, 0);
				String tenNV = (String) tableTimNV.getValueAt(rowSelect, 1);
				String soDT = (String) tableTimNV.getValueAt(rowSelect, 2);
				String diaChi = (String) tableTimNV.getValueAt(rowSelect, 3);
				txtTenNV.setText(tenNV);
				txtMaNV.setText(maNV);
				txtSDT.setText(soDT);
				txtDiaChi.setText(diaChi);
				scpTimNV.setVisible(false);
				txtTenNV.setEditable(false);
				txtMaNV.setEditable(true);
			}
		});
		scpTimNV = new JScrollPane(tableTimNV);
		scpTimNV.setVisible(false);
		scpTimNV.setBounds(141, 52, 557, 137);
		pCA.add(scpTimNV);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 688, 165);
		pCA.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên nhân viên :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 16, 115, 20);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên :\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(10, 55, 115, 20);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Số điện thoại :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(10, 94, 115, 20);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Địa chỉ :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(10, 133, 115, 20);
		panel.add(lblNewLabel_3);

		txtTenNV = new JTextField();
//		txtTenKH.setEditable(false);
		txtTenNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scpTimNV.setVisible(true);
				Enter = 1;
			}
		});
		txtTenNV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Enter == 0) {
					String tenKH = txtTenNV.getText();
					try {
						showTimKiemNV(tenKH);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					Enter = 0;
			}
		});
		txtTenNV.setBounds(132, 11, 546, 30);
		panel.add(txtTenNV);
		txtTenNV.setColumns(10);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setBounds(132, 50, 546, 30);
		panel.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setBounds(132, 89, 546, 30);
		panel.add(txtSDT);
		txtSDT.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setBounds(132, 128, 546, 30);
		panel.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(708, 11, 560, 165);
		pCA.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCaLm = new JLabel("Ca làm :");
		lblCaLm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCaLm.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCaLm.setBounds(10, 71, 120, 20);
		panel_1.add(lblCaLm);

		cmbCaLam = new JComboBox<String>();
		cmbCaLam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbCaLam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String caLam = (String) cmbCaLam.getSelectedItem();
				txtMaCa.setText(loadCaTheoTenCa(caLam));

			}
		});

		cmbCaLam.setBounds(142, 66, 408, 30);
		panel_1.add(cmbCaLam);

		JLabel lblNewLabel_4_1 = new JLabel("Ngày làm :");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4_1.setBounds(10, 119, 120, 20);
		panel_1.add(lblNewLabel_4_1);

		dateNgayLam = new JDateChooser();
		dateNgayLam.setDate(Date.valueOf(endDay));
		dateNgayLam.setBounds(142, 114, 408, 30);
		panel_1.add(dateNgayLam);

		JLabel lblNewLabel_4 = new JLabel("Mã ca  :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_4.setBounds(10, 23, 120, 20);
		panel_1.add(lblNewLabel_4);

		txtMaCa = new JTextField();
		txtMaCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaCa.setColumns(10);
		txtMaCa.setBounds(142, 18, 408, 30);
		panel_1.add(txtMaCa);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valiDataCALAM()) {
				String maCA = txtMaCa.getText();
				String maNV = txtMaNV.getText();
				String caLam = (String) cmbCaLam.getSelectedItem();
				java.util.Date dateUtilStart = dateNgayLam.getDate();
				Date dateSQLStart = new Date(dateUtilStart.getTime());
					CaDao caDao = new CaDao();
					Ca ca = null;
					try {
						ca = caDao.getCatheoMaCa(maCA);
					} catch (ClassNotFoundException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Lich lich = new Lich(new NhanVien(maNV), ca, dateSQLStart);
					try {
						if (lichLam.create(lich)) {
							showMessage("Thêm thành công", txtMaCa);
							dftableCA.addRow(new Object[] { maCA, maNV, caLam,
									dateSQLStart });
							txtTenNV.setEditable(true);
							txtMaNV.setEditable(false);
							emityiuputCALAM();
						} else {
							showMessage("THAT BAI", txtMaCa);
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btnThem.setIcon(new ImageIcon(FrameHeThongDangKyCaLam.class.getResource("/image/btnThem.png")));
		btnThem.setBounds(130, 200, 150, 40);
		pCA.add(btnThem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 251, 1258, 371);
		pCA.add(scrollPane);

		tableCA = new JTable();
		tableCA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableCA.setRowHeight(24);
		tableCA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaCa.setText(tableCA.getValueAt(tableCA.getSelectedRow(), 0).toString());
				txtMaNV.setText(tableCA.getValueAt(tableCA.getSelectedRow(), 1).toString());
				cmbCaLam.setSelectedItem(tableCA.getValueAt(tableCA.getSelectedRow(), 2).toString());
				NhanVienDao nvDao = new NhanVienDao();
				try {
					NhanVien nv = nvDao.getNVTheoID(tableCA.getValueAt(tableCA.getSelectedRow(), 1).toString());
					txtTenNV.setText(nv.getTenNV());
					txtSDT.setText(nv.getsDT());
					txtDiaChi.setText(nv.getDiaChi());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				((JTextField) dateNgayLam.getDateEditor().getUiComponent())
						.setText(tableCA.getValueAt(tableCA.getSelectedRow(), 3).toString());
				txtTenNV.setEditable(false);
				txtMaNV.setEditable(true);

			}
		});
		String[] table = { "Mã ca", "Mã nhân viên", "Ca làm", "Ngày làm" };
		dftableCA = new DefaultTableModel(table, 0);
		scrollPane.setViewportView(tableCA);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableCA.getSelectedRow();
				if (row >= 0) {
					String ma = tableCA.getValueAt(row, 0).toString();
					int loinhac = JOptionPane.showConfirmDialog(null, "Bạn có muốn XÓA Khách Hàng này không", "Xóa",
							JOptionPane.YES_NO_OPTION);
					if (loinhac == JOptionPane.YES_OPTION) {
						try {
							lichLam.delete(ma);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Xóa thành công");

						dftableCA.removeRow(row);

						txtTenNV.setEditable(true);
						txtMaNV.setEditable(false);
						emityiuputCALAM();
					}
				}
			}

		});
		btnXoa.setIcon(new ImageIcon(FrameHeThongDangKyCaLam.class.getResource("/image/btnXoa.png")));
		btnXoa.setBounds(418, 200, 150, 40);
		pCA.add(btnXoa);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMaCa.getText().trim().toString().isEmpty()){
	                showMessage("Vui chon khach hang de chinh sua", txtMaCa);
	            }else{
	            	if(valiDataCALAM()) {
		            	int row = tableCA.getSelectedRow();
		            	if(row>0) {
			            	String maCA = txtMaCa.getText();
							String maNV = txtMaNV.getText();
							String caLam = (String) cmbCaLam.getSelectedItem();
							java.util.Date dateUtilStart = dateNgayLam.getDate();
							Date dateSQLStart = new Date(dateUtilStart.getTime());
							CaDao caDao = new CaDao();
							Ca ca = null;
							try {
								ca = caDao.getCatheoMaCa(maCA);
							} catch (ClassNotFoundException | SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							Lich lich = new Lich(new NhanVien(maNV), ca, dateSQLStart);
							try {
								if (lichLam.update(lich)) {
										tableCA.setValueAt(txtMaCa.getText(), row, 0);
										tableCA.setValueAt(txtMaNV.getText(), row, 1);
										tableCA.setValueAt(caLam, row, 2);
										tableCA.setValueAt(dateSQLStart, row, 3);
										showMessage("Cập nhật thành công", txtMaCa);
										txtTenNV.setEditable(true);
										txtMaNV.setEditable(false);
										emityiuputCALAM();
								} else {
									showMessage("THAT BAI", txtMaCa);
								}
		
							} catch (Exception e1) {
								e1.printStackTrace();
							}

	
					}
	            }
			}
			}
		});
		btnCapNhat.setIcon(new ImageIcon(FrameHeThongDangKyCaLam.class.getResource("/image/btnCapNhat.png")));
		btnCapNhat.setBounds(702, 200, 150, 40);
		pCA.add(btnCapNhat);

		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenNV.setEditable(true);
				txtMaNV.setEditable(false);
				emityiuputCALAM();
			}
		});
		btnXoaTrang.setIcon(new ImageIcon(FrameHeThongDangKyCaLam.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setBounds(986, 200, 150, 40);
		pCA.add(btnXoaTrang);
		UpdateCombobox();
		showTableCA();

	}

	public void showTimKiemNV(String tenNV) throws ClassNotFoundException, SQLException {
		NhanVienDao nvDao = new NhanVienDao();
		int tblRow = tableModel1.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (NhanVien nv : nvDao.getMaNVTenNV(tenNV)) {
			tableModel1.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getsDT(), nv.getDiaChi() });
		}
	}
	public boolean valiDataCALAM() {

		java.util.Date dateUtilStart = dateNgayLam.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
	
		if((dateUtilStart.compareTo(Date.valueOf(endDay))<0) ) {
			showMessage("Error: Ngay Phai Lon Ngay Hien tai !!!",dateNgayLam);
			return false;
		}
		
		return true;
	}
	public void showTableCA() throws ClassNotFoundException {

		listCa = new LichLamDao().getalltbLich();
		dftableCA = (DefaultTableModel) tableCA.getModel();
		if (tableCA.getRowCount() > 0) {
			for (int i = tableCA.getRowCount() - 1; i > -1; i--) {
				dftableCA.removeRow(i);
			}
		}
		dftableCA.setColumnIdentifiers(new Object[] { "Mã ca", "Mã nhân viên", "Ca làm", "Ngày làm" });
		for (Lich lichLam : listCa) {
			Vector<String> row = new Vector<String>();
			row.add(lichLam.getCa().getMaCa());
			row.add(lichLam.getNv().getMaNV());
			row.add(lichLam.getCa().getCaLam());
			row.add(lichLam.getNgayLam().toString());
			dftableCA.addRow(row);
		}
	}

	public void emityiuputCALAM() {
		txtTenNV.setText("");
		txtMaCa.setText("");
		txtMaNV.setText("");
		cmbCaLam.setSelectedItem(null);
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtMaCa.requestFocus();
	}

	public String loadCaTheoTenCa(String tenCaCamTim) {
		return ca.getMaCaTheoTen(tenCaCamTim);
	}

	private void UpdateCombobox() throws ClassNotFoundException {

		CaDao ca = new CaDao();

		ArrayList<Ca> list = ca.getalltbCa();
		for (Ca s : list) {

			cmbCaLam.addItem(s.getCaLam());
		}

	}

	private void showMessage(String message, JTextField txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}
	private void showMessage(String message, JDateChooser txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(null, message);
	}

}
