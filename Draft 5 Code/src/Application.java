import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Application extends JFrame {
	ArrayList<Slide> slides;
	int livesCount = 3;
	int currentSlideIndex;
	int lastSlideIndex;

	public Application() {
		super("Labyrinth of Knowledge");
		QuestionSet [] questionSets = {new QuestionSet(0),new QuestionSet(1),new QuestionSet(2)};

		slides = new ArrayList<Slide>();
		slides.add(new SplashScreen(this));
		slides.add(new MainMenu(this));
		slides.add(new Instructions(this));
		slides.add(new Highscores(this));
		slides.add(new GameSlide(this, "room1", 485, 440, 480, 135, 1,
				new TrapdoorInfo[] { new TrapdoorInfo(200, 200, 0), new TrapdoorInfo(400, 400, 5) }));// bottom
																										// top
		slides.add(new GameSlide(this, "room2", 485, 440, 800, 300, 1,
				new TrapdoorInfo[] { new TrapdoorInfo(300, 300, 1), new TrapdoorInfo(200, 200, 6) }));// bottom
																										// right
		slides.add(new GameSlide(this, "room3", 145, 300, 480, 135, 2,
				new TrapdoorInfo[] { new TrapdoorInfo(500, 500, 2), new TrapdoorInfo(300, 400, 7) }));// left
																										// top
		slides.add(new GameSlide(this, "room4", 485, 440, 145, 300, 1,
				new TrapdoorInfo[] { new TrapdoorInfo(350, 250, 3), new TrapdoorInfo(100, 400, 8) }));// bottom
																										// left
		slides.add(new GameSlide(this, "room5", 800, 300, 480, 135, 1,
				new TrapdoorInfo[] { new TrapdoorInfo(150, 400, 4), new TrapdoorInfo(500, 300, 9) }));// right
																										// top
		slides.add(new GameSlide(this, "room6", 485, 440, 480, 135, 1,new TrapdoorInfo[] {}));// bottom
		
		for (int i = 0; i<10;i++){
			slides.add(new DungeonSlide(this, questionSets[0].questions.get(i),i+1));
		}	
		
		for (int i = 0; i < slides.size(); i++) {
			this.add(slides.get(i));
			slides.get(i).setVisible(false);
		}
		currentSlideIndex = 0;
		this.setSize(1042, 690);
		this.setVisible(true);

		goToSlide(currentSlideIndex);
	}

	public void goToSlide(int nextSlideIndex) {
		lastSlideIndex = currentSlideIndex;
		
		Slide previousPane = slides.get(currentSlideIndex);
		previousPane.deactivate();
		previousPane.setVisible(false);

		Slide nextPane = slides.get(nextSlideIndex);
		nextPane.setVisible(true);
		nextPane.setSize(1024, 650);
		nextPane.setBounds(0, 0, 1024, 690);
		nextPane.repaint();
		nextPane.activate();
		currentSlideIndex = nextSlideIndex;
	}
	
	public void goToLastSlide(){
		goToSlide(lastSlideIndex);
	}

	public static void main(String[] args) {
		Application a = new Application();
	}

}
