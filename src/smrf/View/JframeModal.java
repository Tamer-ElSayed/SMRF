package smrf.View;

import javax.swing.UIManager;

public class JframeModal {
	public static smrf.View.FirstGUI f1 = new FirstGUI();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		f1.show();

	}

}
