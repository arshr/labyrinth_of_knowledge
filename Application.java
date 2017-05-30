import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Application extends JFrame {
	JPanel [] panes;
	int currentPaneIndex;
	
	public Application (){
		super("Labyrinth of Knowledge");
		panes = new JPanel [] {new SplashScreen(this),new MainMenu(this),new Instructions(this),new Highscores(this)};
		for (int i = 0; i<panes.length;i++){
			this.add(panes[i]);
			panes[i].setVisible(false);
		}
		currentPaneIndex = 0;
		this.setSize(1042,690);
	    this.setVisible(true);
		goToPane(currentPaneIndex);
	}
		
	public void goToPane (int nextPaneIndex){
		JPanel nextPane;
		panes [currentPaneIndex].setVisible(false);
		nextPane = panes [nextPaneIndex];
		nextPane.setVisible(true);
		nextPane.setSize(1024,650);
		nextPane.setBounds(0, 0, 1024, 690);
		nextPane.repaint();
		currentPaneIndex = nextPaneIndex;
    }
	
	public static void main(String[] args)
	{
		Application a = new Application ();
	}
	
}
