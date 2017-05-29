import javax.swing.*;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Splash Screen class
 * 
 * Daniel Arakcheev
 * ICS4U0
 * Ms. Krasteva
 * */
public class SplashScreen extends JPanel implements ActionListener {
	Application app;
	JLabel background;
	JLabel scroll;
	JLabel leftScroll;
	JLabel rightScroll;
	JLabel rolledScroll;
	JLabel sun;
	JPanel panel1;

	Timer timer;
	int animationStep = 0;
	int yPos = 0;
	int xPos = 0;
	int a = 0;
	int sunInc;

	public SplashScreen(Application app) {
		super(null);
		this.app = app;
		init();
	}

	public void init() {
		background = new JLabel(new ImageIcon("pyramids.png"));
		background.setBounds(0, 0, 1024, 650);

		sun = new JLabel(new ImageIcon("sun.png"));

		scroll = new JLabel(new ImageIcon("Scroll.png"));

		leftScroll = new JLabel(new ImageIcon("LeftScroll.png"));

		rightScroll = new JLabel(new ImageIcon("RightScroll.png"));

		rolledScroll = new JLabel(new ImageIcon("RolledScroll.png"));
		panel1 = new JPanel();

		this.add(panel1);
		this.add(leftScroll);
		this.add(rightScroll);
		this.add(scroll);
		this.add(rolledScroll);
		this.add(sun);
		this.add(background);
		panel1.setBackground(new Color(0, 0, 0, 0));
		panel1.setBounds(0, 0, 1024, 690);
		panel1.setVisible(true);
		animate();
	}

	private void animate() {
		int delay = 48;
		a = 255;

		timer = new Timer(delay, this);
		timer.setRepeats(true);
		timer.start();
	}

	public void actionPerformed(ActionEvent evt) {
		if (animationStep == 0) {
			panel1.setBackground(new Color(0, 0, 0, a));
			repaint();
			sun.setBounds(750, 200 - sunInc, 104, 104);
			a -= 4;
			sunInc += 2;
			if (a <= 0) {
				animationStep = 1;
			}
		} else if (animationStep == 1) {
			rolledScroll.setBounds(470, 580 - yPos, 94, 190);
			yPos += 10;
			if (yPos == 160) {
				animationStep = 2;
			}
		} else if (animationStep == 2) {
			scroll.setBounds(115, 420, 800, 190);
			leftScroll.setBounds(120 - xPos, 426, 403, 180);
			rightScroll.setBounds(506 + xPos, 420, 476, 190);
			xPos += 10;
			rolledScroll.setVisible(false);
			if (xPos == 370) {
				timer.stop();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				app.goToPane(1);
			}
		}

	}

}