import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyPanel extends JPanel{
	Application app;
	JLabel [] keys;
	
	public KeyPanel (Application app){
		super (null);
		this.app = app;
		keys = new JLabel[10];
		for (int i = 0;i<10;i++){
			keys[i]=new JLabel(new ImageIcon("smallKey.png"));
			keys[i].setBounds (0,25*i,50,47);
			this.add(keys[i]);
		}
		this.setBackground(new Color(21,21,21));
	}
	
	public void update(){
		for(int i = 0;i<10;i++){
			if (i<app.keyCount)
				keys[i].setVisible(true);
			else 
				keys[i].setVisible(false);
		}
	}
}
