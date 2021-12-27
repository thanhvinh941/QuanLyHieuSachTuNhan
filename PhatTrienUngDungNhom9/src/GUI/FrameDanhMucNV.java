package GUI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import Connect.ConnectDB;
import Dao.KhachHangDao;
import Dao.NhanVienDao;
import Dao.TaiKhoanDao;
import Entity.KhachHang;
import Entity.NhanVien;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameDanhMucNV extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtCMND;
	private JTextField txtSoDT;
	private JTextField txtDiaChi;
	private JComboBox cmbChucVu;
	private JTable tableNV;
	private DefaultTableModel dftableNV;
	private JButton btnLuu,btnThem,btnXoaTrang,btnCapNhat;
	private ArrayList<NhanVien> listNV;
	
	NhanVienDao nv_dao;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameQLNV frame = new FrameQLNV();
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
	public FrameDanhMucNV() throws ClassNotFoundException {
		
		nv_dao = new NhanVienDao();
		cmbChucVu  = new JComboBox<String>();
		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		setResizable(true);
		setBounds(-5, -26, 1292, 670);
		JPanel panelNV = new JPanel();
		panelNV.setBounds(0, 0, 1186, 639);
		getContentPane().add(panelNV);
		panelNV.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Nh\u00E2n vi\u00EAn", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, null));
		panel.setBounds(10, 11, 1253, 230);
		panelNV.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Qu\u1EA3n l\u00FD nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 25, 1233, 146);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên");
		lblMNhnVin.setForeground(Color.BLACK);
		lblMNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMNhnVin.setBounds(10, 15, 116, 26);
		panel_1.add(lblMNhnVin);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(149, 15, 422, 26);
		panel_1.add(txtMaNV);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setForeground(Color.BLACK);
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTnNhnVin.setBounds(628, 15, 116, 26);
		panel_1.add(lblTnNhnVin);
		
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(757, 15, 451, 26);
		panel_1.add(txtTenNV);
		
		JLabel lblChngMinhNhn = new JLabel("Chứng minh nhân dân");
		lblChngMinhNhn.setForeground(Color.BLACK);
		lblChngMinhNhn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChngMinhNhn.setBounds(10, 56, 131, 26);
		panel_1.add(lblChngMinhNhn);
		
		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCMND.setColumns(10);
		txtCMND.setBounds(149, 56, 422, 26);
		panel_1.add(txtCMND);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setForeground(Color.BLACK);
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSinThoi.setBounds(628, 56, 116, 26);
		panel_1.add(lblSinThoi);
		
		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(757, 56, 451, 26);
		panel_1.add(txtSoDT);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setForeground(Color.BLACK);
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblaCh.setBounds(10, 97, 116, 26);
		panel_1.add(lblaCh);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(149, 97, 422, 26);
		panel_1.add(txtDiaChi);
		
		JLabel lblNewLabel = new JLabel("Chức vụ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(628, 97, 105, 26);
		panel_1.add(lblNewLabel);
		
		cmbChucVu = new JComboBox();
		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] {"Quản lý", "Nhân viên"}));
		cmbChucVu.setBounds(1056, 93, 152, 26);
		panel_1.add(cmbChucVu);
		
		btnLuu = new JButton("  Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valiDataNV()){
		            int row = tableNV.getSelectedRow();
		            String maNV = txtMaNV.getText();
		            String tenNV = txtTenNV.getText();
		            String cMND = txtCMND.getText();
		            String soDT = txtSoDT.getText();
		            String diaChi = txtDiaChi.getText();
		            String chucVu =  (String) cmbChucVu.getSelectedItem();
		            NhanVien nv = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);

		            try {
		                Connection con = ConnectDB.getConnection();
		                String sql = "Select maNV from NHANVIEN WHERE maNV ='"+maNV+"'";
		                java.sql.Statement statement = con.createStatement();
		                ResultSet rs =  ((java.sql.Statement) statement).executeQuery(sql);
		                if(rs.next()){
		                    showMessage("Mã bị trùng", txtMaNV);
		                }else{
		                    if(nv_dao.create(nv)){
		                        showMessage("Thêm thành công", txtMaNV);
		                        dftableNV.addRow(new Object[]{
		                        		nv.getMaNV(),nv.getTenNV(),nv.getcMND(),nv.getsDT(),
		                        		nv.getDiaChi(),nv.getChuVu()
		                        });
		                        emityiuputNV();
		                    }else{
		                        showMessage("THAT BAI", txtMaNV);
		                    }
		                }
		                
		             } catch (Exception e1) {
		                 e1.printStackTrace();
		            }
		             
		       }
		    }
		});
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLuu.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnSave.png")));
		btnLuu.setBounds(165, 182, 111, 30);
		panel.add(btnLuu);
		
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputNV();
				txtMaNV.setText("NV"+(soNV()+2));
				btnLuu.setEnabled(true);
			}
		});
		btnThem.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnThem.png")));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(441, 182, 89, 30);
		panel.add(btnThem);
		
		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMaNV.getText().trim().toString().isEmpty()){
	                showMessage("Vui chon khach hang de chinh sua", txtMaNV);
	            }else{
	                if(valiDataNV()){
	                	int row = tableNV.getSelectedRow();
	                	if(row > 0) {
		                	String maNV = txtMaNV.getText();
				            String tenNV = txtTenNV.getText();
				            String cMND = txtCMND.getText();
				            String soDT = txtSoDT.getText();
				            String diaChi = txtDiaChi.getText();
				            String chucVu = (String) cmbChucVu.getSelectedItem();
				            NhanVien nv = new NhanVien(maNV, tenNV, cMND, soDT, diaChi, chucVu);
				            try {
								if(nv_dao.update(nv)) {
									tableNV.setValueAt(txtMaNV.getText(), row, 0);
									tableNV.setValueAt(txtTenNV.getText(), row, 1);
									tableNV.setValueAt(txtCMND.getText(), row, 2);
									tableNV.setValueAt(txtSoDT.getText(), row, 3);
									tableNV.setValueAt(txtDiaChi.getText(), row, 4);
									tableNV.setValueAt(cmbChucVu.getSelectedItem().toString(), row, 5);
								}
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

	                	}
			            
	                }
	            }
			}
		});
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapNhat.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnCapNhat.png")));
		btnCapNhat.setBounds(695, 182, 111, 30);
		panel.add(btnCapNhat);
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emityiuputNV();
			}
		});
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrang.setIcon(new ImageIcon(FrameDanhMucNV.class.getResource("/image/btnXoaTrang.png")));
		btnXoaTrang.setBounds(971, 182, 115, 30);
		panel.add(btnXoaTrang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 252, 1253, 378);
		panelNV.add(scrollPane);
		
		tableNV = new JTable();
		tableNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaNV.setText(tableNV.getValueAt(tableNV.getSelectedRow(), 0).toString());
		        txtTenNV.setText(tableNV.getValueAt(tableNV.getSelectedRow(), 1).toString());
		        txtCMND.setText(tableNV.getValueAt(tableNV.getSelectedRow(), 2).toString());
		        txtSoDT.setText(tableNV.getValueAt(tableNV.getSelectedRow(), 3).toString());
		        txtDiaChi.setText(tableNV.getValueAt(tableNV.getSelectedRow(), 4).toString());
		        cmbChucVu.setSelectedItem(tableNV.getValueAt(tableNV.getSelectedRow(), 5).toString());
			}
		});
		String[] table = {  "Mã nhân viên","Tên nhân viên","CMND", "Số điện thoại","Địa chỉ","Chức vụ" } ;
		dftableNV = new DefaultTableModel(table, 0);
		scrollPane.setViewportView(tableNV);
		showTableNV();
	}
	public void emityiuputNV(){
	    txtMaNV.setText("");
	    txtTenNV.setText("");
	    txtDiaChi.setText("");
	    txtSoDT.setText("");

	 	cmbChucVu.setSelectedItem(null);
	 	txtMaNV.requestFocus();
	}
	public int soNV(){
		try {
			return nv_dao.demSoNV();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public  void showTableNV() throws ClassNotFoundException{
		
//	    this.setLocationRelativeTo(null);
	    listNV = new NhanVienDao().getalltbNhanVien();
	    dftableNV = (DefaultTableModel) tableNV.getModel();
	    if(tableNV.getRowCount() > 0){
	        for (int i = tableNV.getRowCount() - 1; i > -1; i--) {
	        	dftableNV.removeRow(i);
	        }
	    }
	    dftableNV.setColumnIdentifiers(new Object[]{
	    		"Mã nhân viên","Tên nhân viên","CMND", "Số điện thoại","Địa chỉ","Chức vụ"
	    });
	   for(NhanVien kh : listNV){
		   dftableNV.addRow(new Object[]{
	          kh.getMaNV(),kh.getTenNV(),kh.getcMND(),kh.getsDT(), kh.getDiaChi(),kh.getChuVu()
	       });
	   }
	}
	
	public boolean valiDataNV() {
		String ten=txtTenNV.getText().trim();
		String sdt=txtSoDT.getText().trim();
		String diaChi=txtDiaChi.getText().trim();
	
		if(!(ten.length()>0 && ten.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Ten  Nha Xuat Ban Phai Theo Mau : Nguyen Van Anh",txtTenNV);
			return false;
		}
		if(!(sdt.length()>0 && sdt.matches("^[0-9]{10}$"))) {
			showMessage("Error: SDT  Khach Hang Phai Theo Mau:^[0-9]{10}$\n VD:0329324401",txtSoDT);
			return false;
		}
		if(!(diaChi.length()>0 && diaChi.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w]*"))) {
			showMessage("Error: Dia Chi  Nha Xuat Ban Phai Theo Mau: VD:12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành Phố Hồ Chí Minh",txtDiaChi);
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
