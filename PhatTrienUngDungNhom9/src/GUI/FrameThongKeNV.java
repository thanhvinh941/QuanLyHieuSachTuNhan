package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class FrameThongKeNV extends JInternalFrame {

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameThongKeNV frame = new FrameThongKeNV();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrameThongKeNV() {
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pTKDT = new JPanel();
		pTKDT.setBackground(Color.WHITE);
		pTKDT.setBounds(0, 0, 1186,632);
		getContentPane().add(pTKDT);
		pTKDT.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 260, 290);
		pTKDT.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 240, 140);
		panel.add(lblNewLabel);
		
		JComboBox cbbThang = new JComboBox();
//		cbbThang.setSelectedIndex(10);
		cbbThang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbbThang.setBounds(110, 162, 90, 24);
		panel.add(cbbThang);
		
		JLabel lblNewLabel_1 = new JLabel("Tháng :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(40, 167, 60, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Năm :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(40, 201, 60, 14);
		panel.add(lblNewLabel_2);
		
		JComboBox cbbNam = new JComboBox();
		cbbNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbbNam.setBounds(110, 196, 90, 24);
		panel.add(cbbNam);
		
		JButton btnNewButton = new JButton("Xem báo cáo");
		btnNewButton.setBounds(50, 249, 160, 30);
		panel.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(280, 11, 339, 290);
		pTKDT.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 11, 319, 100);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sản phẩm có doanh thu cao nhất");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 122, 319, 32);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tên sản phẩm :");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(20, 190, 100, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Loại sản phẩm :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setBounds(20, 215, 100, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Tổng doanh thu :");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_7.setBounds(20, 265, 100, 14);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblbMaSP = new JLabel("");
		lblbMaSP.setBounds(149, 165, 180, 14);
		panel_2.add(lblbMaSP);
		
		JLabel lblNewLabel_9 = new JLabel("Mã sản phẩm :");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_9.setBounds(20, 165, 100, 14);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Giá thành :");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_10.setBounds(20, 240, 100, 14);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblTenSP = new JLabel("");
		lblTenSP.setBounds(149, 190, 180, 14);
		panel_2.add(lblTenSP);
		
		JLabel lblLoaiSP = new JLabel("");
		lblLoaiSP.setBounds(149, 215, 180, 14);
		panel_2.add(lblLoaiSP);
		
		JLabel lblGiaSP = new JLabel("");
		lblGiaSP.setBounds(149, 240, 180, 14);
		panel_2.add(lblGiaSP);
		
		JLabel lblTongDT = new JLabel("");
		lblTongDT.setBounds(149, 265, 180, 14);
		panel_2.add(lblTongDT);
		
		JPanel pPieChar = new JPanel();
		pPieChar.setBounds(629, 11, 634, 619);
		pTKDT.add(pPieChar);
		pPieChar.setLayout(new BorderLayout(0, 0));
		
		JPanel pChart = new JPanel();
		pChart.setBounds(10, 312, 609, 318);
		pTKDT.add(pChart);
		pChart.setLayout(new BorderLayout(0, 0));
	}
}
