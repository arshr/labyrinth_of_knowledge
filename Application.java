import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Application extends JFrame {
	Slide [] panes;
	int currentSlideIndex;
	
	public Application (){
		super("Labyrinth of Knowledge");
		panes = new Slide [] {new SplashScreen(this),
							new MainMenu(this),
							new Instructions(this),
							new Highscores(this),
							new GameSlide(this,"room1.png",485,470,480,135,3),//bottom top
							new GameSlide(this,"room2.png",485,470,800,300,3),//bottom right
							new GameSlide(this,"room3.png",145,300,480,135,4),//left top
							new GameSlide(this,"room4.png",485,470,145,300,3),//bottom left
							new GameSlide(this,"room5.png",800,300,480,135,1),//right top
							new GameSlide(this,"room6.png",485,470,480,135,3)//bottom
							};
		for (int i = 0; i<panes.length;i++){
			this.add(panes[i]);
			panes[i].setVisible(false);
		}
		currentSlideIndex = 0;
		this.setSize(1042,690);
	    this.setVisible(true);
		goToPane(currentSlideIndex);
	}
		
	public void goToPane (int nextPaneIndex){
		Slide nextPane;
		panes [currentSlideIndex].setVisible(false);
		nextPane = panes [nextPaneIndex];
		nextPane.setVisible(true);
		nextPane.setSize(1024,650);
		nextPane.setBounds(0, 0, 1024, 690);
		nextPane.repaint();
		nextPane.activate();
		currentSlideIndex = nextPaneIndex;
    }
	
	public static void main(String[] args)
	{
		Application a = new Application ();
	}
	
}
