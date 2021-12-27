package App;

import GUI.FrmLoading;
import GUI.FrmLogin;

public class app {
	public static void main(String[] args) throws InterruptedException {
		try {
			FrmLoading formLoading = new FrmLoading();
			formLoading.setVisible(true);
			formLoading.setLocationRelativeTo(null);
			for (int i = 0; i < 50; i++) {
				Thread.sleep(20);
				formLoading.progressBar.setValue(i*2);
			}
			formLoading.setVisible(false);
			FrmLogin frmLogin = new FrmLogin();
			frmLogin.setVisible(true);
			frmLogin.setLocationRelativeTo(null);			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
