import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DungeonSlide extends Slide {
	Question question;
	JLabel background;
	JLabel questionLabel;
	JButton backButton;

	Animation greenFlash;
	Animation blueFlash;
	Animation character;

	int monsterNumber;
	JLabel monsterLabel;

	JButton choice1;
	JButton choice2;
	JButton choice3;
	JButton choice4;

	HeartsPanel heartsPanel;

	public DungeonSlide(Application app, Question question, int monsterNumber) {
		super(app);
		this.question = question;
		this.monsterNumber = monsterNumber;
		init();
	}

	public void init() {

		heartsPanel = new HeartsPanel(app);
		heartsPanel.setBounds(10, 10, 50, 160);
		heartsPanel.setVisible(true);
		this.add(heartsPanel);

		// Monster label created
		monsterLabel = new JLabel(new ImageIcon("monsters\\monster" + monsterNumber + ".png"));
		monsterLabel.setBounds(700, 300, 200, 200);

		// Green Attack & monster run away, and key animation
		greenFlash = new Animation(this.app, 1, monsterLabel);
		greenFlash.setBounds(200, 150, 700, 450);
		this.add(greenFlash);
		
		// Blue attack
		blueFlash = new Animation(this.app, 3, null);
		blueFlash.setBounds(0, 0, 900, 700);
		this.add(blueFlash);
		
		// Character animation
		character = new Animation(this.app, 2, null);
		character.setBounds(200, 300, 150, 208);
		this.add(character);

		// Adding monster label
		this.add(monsterLabel);

		

		questionLabel = new JLabel(question.questionString);
		questionLabel.setFont(new Font("Papyrus", Font.PLAIN, 40));
		questionLabel.setBounds(120, 42, 800, 55);
		setText();
		questionLabel.setVisible(true);
		this.add(questionLabel);

		Font buttonFont = new Font("Papyrus", Font.PLAIN, 36);

		choice1 = new JButton(question.answers.get(0));
		choice1.setBackground(new Color(205, 161, 112));
		choice1.setBounds(25, 568, 226, 55);
		choice1.setFont(buttonFont);
		choice1.setVisible(true);

		choice2 = new JButton(question.answers.get(1));
		choice2.setBackground(new Color(205, 161, 112));
		choice2.setBounds(274, 568, 226, 55);
		choice2.setFont(buttonFont);
		choice2.setVisible(true);

		choice3 = new JButton(question.answers.get(2));
		choice3.setBackground(new Color(205, 161, 112));
		choice3.setBounds(523, 568, 226, 55);
		choice3.setFont(buttonFont);
		choice3.setVisible(true);

		choice4 = new JButton(question.answers.get(3));
		choice4.setBackground(new Color(205, 161, 112));
		choice4.setBounds(772, 568, 226, 55);
		choice4.setFont(buttonFont);
		choice4.setVisible(true);

		this.add(choice1);
		this.add(choice2);
		this.add(choice3);
		this.add(choice4);

		choice1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(0);
			}
		});
		choice2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(1);
			}
		});
		choice3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(2);
			}
		});
		choice4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer(3);
			}
		});

		// background
		background = new JLabel(new ImageIcon("dungeon.jpg"));
		background.setBounds(0, 0, 1024, 650);
		this.add(background);
	}

	public void activate() {
		character.startAnimation();
		heartsPanel.update();
	}

	private void checkAnswer(int playerAnswer) {
		if (playerAnswer == question.correct) {
			app.keyCount++;
			winAnimation();
		} else {
			app.livesCount--;
			loseAnimation();
		}
	}

	private void winAnimation() {
		greenFlash.reset();
		greenFlash.startAnimation();
	}

	private void loseAnimation() {
		blueFlash.reset();
		blueFlash.startAnimation();
		heartsPanel.update();
	}

	private void gameOver() {

	}

	private void setText() {
		Font labelFont = questionLabel.getFont();
		String labelText = questionLabel.getText();

		int stringWidth = questionLabel.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = questionLabel.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double) componentWidth / (double) stringWidth;

		int newFontSize = (int) (labelFont.getSize() * widthRatio);
		int componentHeight = questionLabel.getHeight();

		// Pick a new font size so it will not be larger than the height of
		// label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight - 5);

		// Set the label's font size to the newly determined size.
		questionLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
	}

	public void reset() {
		monsterLabel.setBounds(700, 300, 200, 200);
	}

}
