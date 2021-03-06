import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Highscores extends Slide {
	JLabel background;
	JButton backButton;

	public Highscores(Application app) {
		super(app);
		init();
	}

	public void init() {
		background = new JLabel(new ImageIcon("highScores.png"));
		background.setBounds(0, 0, 1024, 650);
		
		backButton = new JButton (new ImageIcon("back button.png"));
		backButton.setBounds(880, 555, 136, 80);
		backButton.setBackground(new Color (120,79,37));
		this.add(backButton);
		this.add(background);
		
		backButton.addActionListener(new ActionListener (){
			  public void actionPerformed (ActionEvent e){
				  app.goToSlide(1);
			  }
		});
		
	}
}
