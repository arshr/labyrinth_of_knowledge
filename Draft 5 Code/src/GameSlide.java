import javax.swing.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Instructions class class
 * 
 * Daniel Arakcheev
 * ICS4U0
 * Ms. Krasteva
 * */
public class GameSlide extends Slide implements ActionListener, KeyListener {
	JLabel background;
	JButton backButton;
	JLabel upPos;
	JLabel downPos;
	JLabel leftPos;
	JLabel rightPos;

	JPanel characterPanel;
	Trapdoor[] trapdoors; // trapdoors in this room
	String fileName;
	
	int startX;
	int startY;
	int exitX;
	int exitY;

	int dir;
	int x;
	int y;
	int i;
	int speed = 5;
	int startDir;// 1-Up, 2-Right, 3-Down, 4-Left

	public GameSlide(Application app, String fileName, int startX, int startY, int exitX, int exitY, int startDir,
			TrapdoorInfo[] trapdoorInfos) {
		super(app);
		this.x = startX;
		this.y = startY;
		this.startX = startX;
		this.startY = startY;
		this.exitX = exitX;
		this.exitY = exitY;
		this.startDir = startDir;
		this.fileName = fileName;
		init(trapdoorInfos);
	}

	public void init(TrapdoorInfo[] trapdoorInfos) {
		setupCharacter();

		setupTrapdoors(trapdoorInfos);

		// button --------------------------------------------------
		backButton = new JButton(new ImageIcon("back button.png"));
		backButton.setBounds(880, 5, 136, 80);
		backButton.setBackground(new Color(120, 79, 37));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.goToSlide(1);
			}
		});
		this.add(backButton);

		// background
		background = new JLabel(new ImageIcon(fileName+".png"));
		background.setBounds(0, 0, 1024, 650);
		this.add(background);

		addKeyListener(this);
	}

	private void setupCharacter() {
		upPos = new JLabel(new ImageIcon("moveUp.png"));
		downPos = new JLabel(new ImageIcon("moveDown.png"));
		leftPos = new JLabel(new ImageIcon("moveLeft.png"));
		rightPos = new JLabel(new ImageIcon("moveRight.png"));
		upPos.setBounds(0, 0, 83, 83);
		downPos.setBounds(0, 0, 83, 83);
		leftPos.setBounds(0, 0, 83, 83);
		rightPos.setBounds(0, 0, 83, 83);

		characterPanel = new JPanel(null);
		characterPanel.add(upPos);
		characterPanel.add(downPos);
		characterPanel.add(leftPos);
		characterPanel.add(rightPos);
		characterPanel.setBounds(x, y, 83, 83);
		characterPanel.setBackground(new Color(0, 0, 0, 0));
		characterPanel.setVisible(true);

		this.add(characterPanel);
	}

	public void setupTrapdoors(TrapdoorInfo[] trapdoorInfos) {
		trapdoors = new Trapdoor[trapdoorInfos.length];
		for (int i = 0; i < trapdoorInfos.length; i++) {
			trapdoors[i] = new Trapdoor(trapdoorInfos[i].x, trapdoorInfos[i].y, trapdoorInfos[i].dungeonIndex);
			this.add(trapdoors[i]);
			trapdoors[i].setVisible(true);
			trapdoors[i].setBounds(trapdoorInfos[i].x, trapdoorInfos[i].y, 51, 80);
		}
	}

	public void activate() {
		int closedCount = 0;
		for (int i = 0;i<trapdoors.length;i++){
			if (!trapdoors[i].isOpen)
			{
				closedCount ++;
			}
		}
		if (closedCount == trapdoors.length && fileName!="room6")
			background.setIcon(new ImageIcon(fileName+"Open.png"));
		turnCharacter(startDir);
		this.requestFocus();
	}

	public void deactivate(){
		x=startX;
		y=startY;
		characterPanel.setBounds(x, y, 83, 83);
	}

	public void reset(){
		for (int i = 0; i<trapdoors.length;i++){
			trapdoors[i].reset();
		}
		
	}
	
	public boolean isFocusable() {
		return true;
	}

	private void turnCharacter(int direction) {
		if (this.dir != direction) {
			downPos.setVisible(direction == 3);
			upPos.setVisible(direction == 1);
			rightPos.setVisible(direction == 2);
			leftPos.setVisible(direction == 4);
			repaint();

			this.dir = direction;
		}
	}

	// dir determines up down or left right (0 = up/down, 1 = left/right)
	// step determines when up and when down (for up/down movement) (step>0 =
	// downwards, step<0 = upwards)
	// and when right and when left (for left/right movement)
	// x and y are the starting position
	public void move(int dir) {
		// down
		if (dir == 3 && y < 440) {
			y += speed;
		}
		// up
		if (dir == 1 && y > 130) {
			y -= speed;
		}
		// right
		if (dir == 2 && x < 795) {
			x += speed;
		}
		// left
		if (dir == 4 && x > 150) {
			x -= speed;
		}
		characterPanel.setBounds(x, y, 83, 83);
	}

	public void actionPerformed(ActionEvent evt) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			startMove(3);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			startMove(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			startMove(4);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			startMove(2);
		}
	}

	private void startMove(int direction) {
		turnCharacter(direction);
		move(direction);
		if (detectExit()) {
			exit();
		}
		Trapdoor trap = detectTrap();
		if (trap != null) {
			enterTrapdoor(trap);
		}

	}

	private boolean detectExit() {
		for (int i = 0; i<trapdoors.length;i++){
			if (trapdoors[i].isOpen){
				return false;
			}
		}
		return Math.abs(x - exitX) < 30 && Math.abs(y - exitY) < 30;
	}

	private void exit() {
		if (app.currentSlideIndex == 9)
			app.goToSlide(1);
		else
			app.goToSlide(app.currentSlideIndex + 1);
	}

	public Trapdoor detectTrap() {
		for (int i = 0; i < trapdoors.length; i++) {
			Trapdoor trap = trapdoors[i];
			Rectangle r1 = trap.getBounds();
			Rectangle r2 = characterPanel.getBounds();
			if (r1.intersects(r2) && trap.isOpen)
				return trap;
		}
		return null;
	}

	public void enterTrapdoor(Trapdoor trap) {
		trap.enter();
		app.goToSlide(trap.dungeonPaneIndex + 10);
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}