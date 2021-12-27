package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.KhachHangDao;
import Entity.KhachHang;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class FrameTimKiemKH extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtSoDT;
	private JComboBox cmbGioiTinh;
	private JComboBox cmbGioiTinh_1;
	private JTable tableKH;
	private JButton btnXaTrng, btnTim;
	private DefaultTableModel dftableKH;
	private ArrayList<KhachHang> listKH;
	
	KhachHangDao kh_dao;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameTimKiemKH frame = new FrameTimKiemKH();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public FrameTimKiemKH() throws ClassNotFoundException {
		 kh_dao = new KhachHangDao();
		cmbGioiTinh  = new JComboBox<String>();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel pTKKH = new JPanel();
		pTKKH.setBackground(Color.WHITE);
		pTKKH.setBounds(-5 ,-25, 1000, 570);
		getContentPane().add(pTKKH);
		pTKKH.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm thông tin khách hàng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 0, 970, 57);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pTKKH.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(152, 60, 140, 20);
		pTKKH.add(lblNewLabel_1);
		
		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(302, 56, 600, 30);
		pTKKH.add(txtMaKH);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên khách hàng :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(152, 101, 140, 20);
		pTKKH.add(lblNewLabel_1_1);
		
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(302, 95, 600, 30);
		pTKKH.add(txtTenKH);
		
		JLabel lblNewLabel_1_2 = new JLabel("Địa chỉ :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(152, 142, 140, 20);
		pTKKH.add(lblNewLabel_1_2);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(302, 136, 600, 30);
		pTKKH.add(txtDiaChi);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số điện thoại :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(152, 183, 140, 20);
		pTKKH.add(lblNewLabel_1_3);
		
		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(302, 177, 600, 30);
		pTKKH.add(txtSoDT);
		
		btnTim = new JButton("Tìm");
		btnTim .addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(valiDataKH()) {
					 int row = tableKH.getSelectedRow();
			         String maKH = txtMaKH.getText();
			         String tenKH = txtTenKH.getText();
			         String diaChi = txtDiaChi.getText();
			         String soDT = txtSoDT.getText();
			         String gioiTinh = (String) cmbGioiTinh.getSelectedItem();
			         KhachHangDao kh = new KhachHangDao();
					try {
						ArrayList<KhachHang> list = kh.getitemKhachHang(maKH, tenKH, soDT, diaChi, gioiTinh);
						if(list.size() ==0) 
								JOptionPane.showMessageDialog(null, "Không tìm thấy");
						else {
							dftableKH.getDataVector().removeAllElements();
							for(KhachHang s : list) {
								String[] rowData = {s.getMaKH(),s.getTenKH(),s.getSoDT(),s.getDiaChi(),s.getGioiTinh()};
								dftableKH.addRow(rowData);
							}
						}
						tableKH.setModel(dftableKH);
			
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//			}
			}    
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTim.setIcon(new ImageIcon(FrameTimKiemKH.class.getResource("/image/btnTim.png")));
		btnTim.setBounds(932, 210, 150, 40);
		pTKKH.add(btnTim );
		
	
		JLabel lblNewLabel_1_3_1 = new JLabel("Giới tính :");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(152, 218, 140, 20);
		pTKKH.add(lblNewLabel_1_3_1);
		
		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cmbGioiTinh.setBounds(670, 218, 232, 30);
		pTKKH.add(cmbGioiTinh);
		
		btnXaTrng = new JButton("Xóa trắng");
		btnXaTrng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputKH();
				try {
					showTableKH();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXaTrng.setIcon(new ImageIcon(FrameTimKiemKH.class.getResource("/image/btnXoaTrang.png")));
		btnXaTrng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXaTrng.setBounds(1113, 210, 150, 40);
		pTKKH.add(btnXaTrng);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 297, 1253, 333);
		pTKKH.add(scrollPane);
		
		tableKH = new JTable();
		tableKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		String[] table = {  "Mã khách hàng","Tên Khách hàng", "Số điện thoại","Địa chỉ","Giới tính"} ;
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
	public void emityiuputKH(){
	    txtMaKH.setText("");
	    txtTenKH.setText("");
	    txtDiaChi.setText("");
	    txtSoDT.setText("");

	 	cmbGioiTinh.setSelectedItem(null);
	    txtMaKH.requestFocus();
	}
	public  void showTableKH() throws ClassNotFoundException{
		
//	    this.setLocationRelativeTo(null);
	    listKH = new KhachHangDao().getalltbKhachHang();
	    dftableKH = (DefaultTableModel) tableKH.getModel();
	    if(tableKH.getRowCount() > 0){
	        for (int i = tableKH.getRowCount() - 1; i > -1; i--) {
	            dftableKH.removeRow(i);
	        }
	    }
	    dftableKH.setColumnIdentifiers(new Object[]{
	    		 "Mã khách hàng","Tên Khách hàng", "Số điện thoại","Địa chỉ","Giới tính"
	    });
	   for(KhachHang kh : listKH){
	       dftableKH.addRow(new Object[]{
	          kh.getMaKH(),kh.getTenKH(),kh.getSoDT(),kh.getDiaChi(),kh.getGioiTinh()
	       });
	   }
	}
//	public int soKH(){
//		try {
//			return kh_dao.demSoKH();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 0;
//	}
	public boolean valiDataKH() {

		String ten=txtTenKH.getText().trim();
		String diaChi=txtDiaChi.getText().trim();
		String sdt=txtSoDT.getText().trim();
	
		if(!(ten.length()>0 && ten.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Ten  Khach Hang Phai Theo Mau:[A-Z][a-zA-Z' ]+\n VD:Nguyen Van Anh",txtTenKH);
			return false;
		}
		if(!(sdt.length()>0 && sdt.matches("^[0-9]{10}$"))) {
			showMessage("Error: SDT  Khach Hang Phai Theo Mau:^[0-9]{10}$\n VD:0329324401",txtSoDT);
			return false;
		}
		if(!(diaChi.length()>0 && diaChi.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Dia Chi  Khach Hang Phai Theo Mau: VD:12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành Phố Hồ Chí Minh",txtDiaChi);
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
