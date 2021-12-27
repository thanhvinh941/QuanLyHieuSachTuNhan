package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Dao.CaDao;
import Dao.KhachHangDao;
import Dao.LichLamDao;
import Dao.NhanVienDao;
import Entity.Ca;
import Entity.KhachHang;
import Entity.Lich;
import Entity.NhanVien;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameTimKiemNV extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private JTextField txtTimMaNV;
	private JTextField txtTimTenNV;
	private JTextField txtTimSoDT;

	private JTable tableNV;
	private LichLamDao lichLam = new LichLamDao();
	private CaDao ca = new CaDao();
	private NhanVienDao nvDao = new NhanVienDao();
	private JComboBox cmbTimCa;
	private ArrayList<Lich> listCa;
	private JTable tableCA;
	private DefaultTableModel dftableCA;
	private JDateChooser dateNgayLam;
	private JTextField textField;
	private JDateChooser dateTimNgay;
	private LocalDate endDay = LocalDate.now();

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTimKiemNV frame = new FrameTimKiemNV();
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
	public FrameTimKiemNV() throws ClassNotFoundException {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pTKNV = new JPanel();
		pTKNV.setBackground(Color.WHITE);
		pTKNV.setBounds(0, 0, 1186, 632);
		getContentPane().add(pTKNV);
		pTKNV.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm thông tin nhân viên");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 1253, 61);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pTKNV.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(225, 112, 150, 20);
		pTKNV.add(lblNewLabel_1);

		txtTimMaNV = new JTextField();
		txtTimMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimMaNV.setBounds(383, 107, 400, 30);
		pTKNV.add(txtTimMaNV);
		txtTimMaNV.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Tên nhân viên");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(225, 153, 150, 20);
		pTKNV.add(lblNewLabel_1_1);

		txtTimTenNV = new JTextField();
		txtTimTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimTenNV.setColumns(10);
		txtTimTenNV.setBounds(383, 148, 400, 30);
		pTKNV.add(txtTimTenNV);

		txtTimSoDT = new JTextField();
		txtTimSoDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTimSoDT.setColumns(10);
		txtTimSoDT.setBounds(383, 189, 400, 30);
		pTKNV.add(txtTimSoDT);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(225, 194, 150, 20);
		pTKNV.add(lblNewLabel_1_1_1);

		JLabel lblCaLm = new JLabel("Ca làm");
		lblCaLm.setForeground(Color.BLACK);
		lblCaLm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCaLm.setBounds(225, 238, 150, 20);
		pTKNV.add(lblCaLm);

		cmbTimCa = new JComboBox();
		cmbTimCa.setModel(
				new DefaultComboBoxModel(new String[] {"9 giờ sáng đến 4 giờ chiều", "4 giờ chiều đến 10 giờ tối"}));
		cmbTimCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbTimCa.setBounds(383, 230, 400, 34);
		pTKNV.add(cmbTimCa);

		dateTimNgay = new JDateChooser(Date.valueOf(endDay));
		dateTimNgay.setBounds(383, 275, 400, 30);
		pTKNV.add(dateTimNgay);

		JLabel lblNgyLm = new JLabel("Ngày làm");
		lblNgyLm.setForeground(Color.BLACK);
		lblNgyLm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgyLm.setBounds(225, 280, 150, 20);
		pTKNV.add(lblNgyLm);

		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableCA.getSelectedRow();
				String maNV = txtTimMaNV.getText();
				String tenNV = txtTimTenNV.getText();
				String soDT = txtTimSoDT.getText();
				String caLam = (String) cmbTimCa.getSelectedItem();
				java.util.Date dateUtilStart = dateTimNgay.getDate();
				Date dateSQLStart = new Date(dateUtilStart.getTime());
				NhanVienDao nvdao = new NhanVienDao();
				try {
					ArrayList<NhanVien> list = nvdao.getLichLam(maNV, tenNV, soDT, caLam, dateSQLStart);
					System.out.println(list);
					if (list.size() == 0)
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
					else {
						dftableCA.getDataVector().removeAllElements();
						for (NhanVien nv : list) {
							String[] rowData = { nv.getMaNV(), nv.getTenNV(),nv.getcMND(),nv.getDiaChi(),nv.getsDT(),nv.getChuVu(),caLam,dateSQLStart.toString() };

							dftableCA.addRow(rowData);
						}
					}
					tableCA.setModel(dftableCA);

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTim.setIcon(new ImageIcon(FrameTimKiemNV.class.getResource("/image/btnTim.png")));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.setBounds(831, 265, 150, 40);
		pTKNV.add(btnTim);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 329, 1253, 301);
		pTKNV.add(scrollPane);

		tableCA = new JTable();
		tableCA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		String[] table = { "Mã nhân viên", "Tên nhân viên", "CMND", "Địa chỉ", "Số điện thoại", "Chức vụ"};
		dftableCA = new DefaultTableModel(table, 0);
		tableCA = new JTable(dftableCA);
		tableCA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTimMaNV.setText(tableCA.getValueAt(tableCA.getSelectedRow(), 0).toString());
				txtTimTenNV.setText(tableCA.getValueAt(tableCA.getSelectedRow(), 1).toString());
				txtTimSoDT.setText(tableCA.getValueAt(tableCA.getSelectedRow(), 4).toString());

				cmbTimCa.setSelectedItem(tableCA.getValueAt(tableCA.getSelectedRow(), 6).toString());
				((JTextField) dateTimNgay.getDateEditor().getUiComponent())
						.setText(tableCA.getValueAt(tableCA.getSelectedRow(), 7).toString());

			}
		});
		scrollPane.setViewportView(tableCA);
		
		JButton btnXaTrng = new JButton("Xóa trắng");
		btnXaTrng.setIcon(new ImageIcon(FrameTimKiemNV.class.getResource("/image/btnXoaTrang.png")));
		btnXaTrng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputKH();
				try {
					showTableCA();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXaTrng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXaTrng.setBounds(1003, 265, 150, 40);
		pTKNV.add(btnXaTrng);

		showTableCA();

	}

	public void emityiuputKH(){
	    txtTimMaNV.setText("");
	    txtTimTenNV.setText("");
	    txtTimSoDT.setText("");
	}
	
	public void showTableCA() throws ClassNotFoundException {

//	    this.setLocationRelativeTo(null);
		listCa = new LichLamDao().getalltbLich();
		dftableCA = (DefaultTableModel) tableCA.getModel();
		if (tableCA.getRowCount() > 0) {
			for (int i = tableCA.getRowCount() - 1; i > -1; i--) {
				dftableCA.removeRow(i);
			}
		}
		dftableCA.setColumnIdentifiers(new Object[] { "Mã nhân viên", "Tên nhân viên", "CMND", "Địa chỉ",
				"Số điện thoại", "Chức vụ","Ca lam","Ngay lam"});
		for (Lich lichLam : listCa) {
			dftableCA.addRow(new Object[] { lichLam.getNv().getMaNV(), lichLam.getNv().getTenNV(),lichLam.getNv().getcMND(), lichLam.getNv().getDiaChi(), lichLam.getNv().getsDT(), lichLam.getNv().getChuVu(),lichLam.getCa().getCaLam(), lichLam.getNgayLam() });
		}
	}
}
