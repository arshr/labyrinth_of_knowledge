import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {
	Application app;
	Timer timer;
	JLabel[] greenFlashPics = new JLabel[8];
	JLabel[] blueFlashPics = new JLabel[9];
	JLabel[] characterPics = new JLabel[8];
	int iteration = 0;
	int step = 50;
	int num;
	int xPos = 0;
	JLabel monster;
	JLabel key;
	int keyStep = 0;
	
	boolean animation1Finished = false;

	public Animation(Application app, int num, JLabel monster) {
		super(null);
		this.app = app;
		this.num = num;
		this.monster = monster;
		init();
	}

	public void init() {
		key = new JLabel(new ImageIcon("key.png"));
		this.add(key);

		for (int i = 0; i < 8; i++) {
			greenFlashPics[i] = new JLabel(new ImageIcon("greenFlash\\greenFlash" + (i + 1) + ".png"));
			this.add(greenFlashPics[i]);
			greenFlashPics[i].setBounds(i*50, 200, 250, 146);
			greenFlashPics[i].setVisible(false);
			greenFlashPics[i].setBackground(new Color(0, 0, 0, 0));
		}

		for (int i = 0; i < 8; i++) {
			characterPics[i] = new JLabel(new ImageIcon("character\\char" + (i + 1) + ".png"));
			this.add(characterPics[i]);
			characterPics[i].setBounds(0, 0, 150, 208);
			characterPics[i].setVisible(false);
			characterPics[i].setBackground(new Color(0, 0, 0, 0));
		}

		for (int i = 0; i < 9; i++) {
			blueFlashPics[i] = new JLabel(new ImageIcon("blueFlash\\blueFlash" + (i + 1) + ".png"));
			this.add(blueFlashPics[i]);
			blueFlashPics[i].setBounds(450-i*50, 0, 250, 208);
			blueFlashPics[i].setVisible(false);
			blueFlashPics[i].setBackground(new Color(0, 0, 0, 0));
		}

		this.setBackground(new Color(0, 0, 0, 0));
		this.setVisible(false);
	}
	
	public void reset(){
		step = 0;
		keyStep = 0;
		xPos = 0;
		key.setVisible(false);
		repaint();
		iteration = 0;
	}

	public void startAnimation() {
		int delay;
		if (timer == null) {
			if (num == 1)
				delay = 60;
			else
				delay = 70;

			timer = new Timer(delay, this);
			timer.setRepeats(true);
			timer.start();
			this.setVisible(true);
			this.repaint();
		}
	}

	public void actionPerformed(ActionEvent evt) {
		this.setVisible(true);
		// Green attack & monster runs away
		if (num == 1) {
			if (iteration < 8) {
				if (iteration > 0) {
					greenFlashPics[iteration - 1].setVisible(false);
				}
				greenFlashPics[iteration].setVisible(true);
			}
			if (iteration >= 7) {
				greenFlashPics[7].setVisible(false);
				//Monster animation
				monster.setBounds(700 + xPos, 300, 200, 200);
				xPos += 12;
				if (iteration >= 40) {
					//Key animation
					ImageIcon imageIcon = new ImageIcon("key.png");
					Image image = imageIcon.getImage(); // transform it
					Image newImg = image.getScaledInstance(0 + 10*keyStep, 0 + 5*keyStep, java.awt.Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(newImg); // transform it back
					key.setIcon(imageIcon);
					key.setVisible(true);
					key.setBounds(300-5*keyStep, 130-2*keyStep, 0 + 10*keyStep, 0 + 5*keyStep);
					//stop timer and exit
					if (iteration >=60){
						timer.stop();
						timer = null;
						key.setVisible(false);
						try {
						Thread.sleep(500);
						} catch (InterruptedException e) {
						}
						app.goToLastSlide();
					}
				}
			}
			keyStep ++;
			step += 50;
		}
		// character appears
		if (num == 2) {
			if (iteration < 8) {
				if (iteration > 0) {
					characterPics[iteration - 1].setVisible(false);
					this.repaint();
				}

				characterPics[iteration].setVisible(true);
			}
			if (iteration == 9) {
				timer.stop();
				timer = null;
			}
		}
		// Blue flash
		if (num == 3) {
			if (iteration < 9) {
				if (iteration > 0) {
					blueFlashPics[iteration - 1].setVisible(false);
					this.repaint();
				}
				blueFlashPics[iteration].setVisible(true);
			}
			if (iteration >= 8) {
				blueFlashPics[8].setVisible(false);
				if (iteration == 20) {
					timer.stop();
					timer = null;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					if (app.livesCount == 0){
						app.goToSlide(1);
					}		
				}
			}
			step += 50;
		}
		this.getParent().getParent().repaint();
		iteration++;
	}

	public void animation1() {

	}

}
