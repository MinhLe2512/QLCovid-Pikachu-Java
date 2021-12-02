package Login;

import java.awt.EventQueue;

import org.jcp.xml.dsig.internal.MacOutputStream;
public class main {
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						//LoginFrame window = new LoginFrame();
						//window.frame.setVisible(true);
						System.out.println(Hashing.getHash("dangleminh"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}
