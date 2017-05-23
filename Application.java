import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Application {
	
	public static void main(String[] args)
	  {
	    JFrame frame = new JFrame("Labyrinth of Knowledge");
	    SplashScreen sc = new SplashScreen();
	    frame.add(sc);
	    
	    frame.setSize(1040,623);
	    frame.setVisible(true);
	    
	    
	  }
}
