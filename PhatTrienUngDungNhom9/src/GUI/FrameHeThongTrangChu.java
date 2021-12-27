package GUI;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import Dao.NhanVienDao;
import Entity.NhanVien;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class FrameHeThongTrangChu extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTenNhanVien,lblChucVu;
	private NhanVienDao nvDao = new NhanVienDao();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTrangChu frame = new FrameTrangChu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public FrameHeThongTrangChu(String maNV) throws ClassNotFoundException, SQLException {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pTrangCHu = new JPanel();
		pTrangCHu.setBackground(Color.WHITE);
		pTrangCHu.setBounds(0, 0, 1186, 654);
		getContentPane().add(pTrangCHu);
		pTrangCHu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/lblTrangChu.jpg")));
		lblNewLabel.setBounds(251, 0, 1022, 641);
		pTrangCHu.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/lblLogo.png")));
		lblNewLabel_1.setBounds(0, 201, 251, 188);
		pTrangCHu.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/lblVS.png")));
		lblNewLabel_2.setBounds(0, 390, 251, 251);
		pTrangCHu.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(FrameHeThongTrangChu.class.getResource("/image/user1.png")));
		lblNewLabel_4.setBounds(0, 0, 252, 96);
		pTrangCHu.add(lblNewLabel_4);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 107, 228, 90);
		pTrangCHu.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Tên nhân viên :");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(0, 10, 95, 14);
		panel.add(lblNewLabel_5);
		
		lblTenNhanVien = new JLabel("");
		lblTenNhanVien.setBounds(100, 10, 118, 14);
		panel.add(lblTenNhanVien);
		
		JLabel lblNewLabel_6 = new JLabel("Chức vụ :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setBounds(0, 50, 95, 14);
		panel.add(lblNewLabel_6);
		
		lblChucVu = new JLabel("");
		lblChucVu.setBounds(100, 50, 118, 14);
		panel.add(lblChucVu);
		loadNhanVien(maNV);
	}
	void loadNhanVien(String maMaNV) throws ClassNotFoundException, SQLException {
		NhanVien nv = nvDao.getKHTheoMa(maMaNV);
		lblChucVu.setText(nv.getChuVu());
		lblTenNhanVien.setText(nv.getTenNV());
	}
}
