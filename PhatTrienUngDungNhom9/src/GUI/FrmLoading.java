package GUI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;

public class FrmLoading extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblImage;
	public JProgressBar progressBar;
	/**
	 * Create the frame.
	 */
	public FrmLoading() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 588);
		setFocusCycleRoot(true);
		setFocusableWindowState(true);
//		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.LEFT);
		lblImage.setBounds(0, 0, 800, 533);
		lblImage.setIcon(new ImageIcon(FrmLoading.class.getResource("/image/Loading.jpg")));
		contentPane.add(lblImage);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(SystemColor.textHighlightText);
		progressBar.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		progressBar.setStringPainted(true);
		progressBar.setForeground(SystemColor.textHighlight);
		progressBar.setBounds(0, 533, 800, 20);
		contentPane.add(progressBar);
	}
}
