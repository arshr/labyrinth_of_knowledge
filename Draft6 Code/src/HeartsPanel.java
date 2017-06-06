import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeartsPanel extends JPanel{
	Application app;
	JLabel [] hearts;
	
	public HeartsPanel (Application app){
		super (null);
		this.app = app;
		hearts = new JLabel[3];
		for (int i = 0;i<3;i++){
			hearts[i]=new JLabel(new ImageIcon("heart.png"));
			hearts[i].setBounds (0,50*i,50,47);
			this.add(hearts[i]);
		}
		this.setBackground(new Color(21,21,21));
	}
	
	public void update(){
		for(int i = 0;i<3;i++){
			if (i<app.livesCount)
				hearts[i].setVisible(true);
			else 
				hearts[i].setVisible(false);
		}
	}
}
