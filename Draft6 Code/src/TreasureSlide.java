import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.*;
/*
 * Instructions class class
 * 
 * Daniel Arakcheev
 * ICS4U0
 * Ms. Krasteva
 * */
public class TreasureSlide extends Slide implements ActionListener{
	

	JLabel background;
	JButton submitButton;
	JLabel congratsLabel;
	JLabel enterNameLabel;
	JLabel timeLabel;
	JTextField textField;
	Highscore highscore;
	
	Timer timer;
	int iteration = 0;
	
	
	public TreasureSlide(Application app) {
		super(app);
		
		submitButton = new JButton (new ImageIcon ("submitButton.png"));
		submitButton.setBounds(852,440,130,73);
		submitButton.setBackground(new Color(120,79,37));
		submitButton.setVisible(false);
		this.add(submitButton);
		
		submitButton.addActionListener(new ActionListener (){
			  public void actionPerformed (ActionEvent e){
				  app.playerName = textField.getText();
				  app.highscores.add(new Highscore(app.playerName,(int)app.gameTime));				  
				  app.highscores.sort(new Comparator<Highscore>(){
		                @Override
		                public int compare(Highscore one, Highscore two) {
		                	if (one.score < two.score) return -1;
		                	if (one.score > two.score) return 1;
		                	return 0;
		                }
		            });
				  app.saveHighscores();
				  app.goToSlide(1);
			  }
		});
		
		Font timeFont = new Font ("Papyrus", Font.PLAIN, 36);
		timeLabel = new JLabel ();
		timeLabel.setFont(timeFont);
		timeLabel.setBounds(670,330,300,100);
		timeLabel.setBackground(new Color (0,0,0,0));
		timeLabel.setForeground(new Color (253,229,14));
		timeLabel.setVisible(false);
		this.add(timeLabel);
		
		textField = new JTextField();
		textField.setBounds(560,450,250,50);
		textField.setColumns(20);
		textField.setFont(timeFont);
		textField.setBackground(new Color (253,229,14));
		textField.setVisible(false);
		this.add(textField);
		
		enterNameLabel = new JLabel (new ImageIcon("getNameScreen.png"));
		enterNameLabel.setBounds(0, 0, 1024, 650);
		enterNameLabel.setVisible(false);
		this.add(enterNameLabel);
		
		congratsLabel = new JLabel (new ImageIcon("congratsScreen.png"));
		congratsLabel.setBounds(0, 0, 1024, 650);
		congratsLabel.setVisible(false);
		this.add(congratsLabel);		
		
		// background
		background = new JLabel(new ImageIcon("room6.png"));
		background.setBounds(0, 0, 1024, 650);
		this.add(background);
	}
	
	public void activate() {
			app.gameEndTime = System.currentTimeMillis();
			//app.gameTime = (app.gameEndTime-app.gameStartTime)/1000;
			app.gameTime = (int)(Math.random()*1000);
			textField.setText("");
			iteration = 0;
			this.repaint();
			timer = new Timer(500, this);
			timer.setRepeats(true);
			timer.start();
	}
	
	public void actionPerformed(ActionEvent evt) {
		if (iteration == 0)
			congratsLabel.setVisible(true);
		if (iteration == 2){
			enterNameLabel.setVisible(true);
			timer.stop();
			timer = null;
			showHighscorePrompt();
		}
		iteration++;
	}

	
	public void reset(){
	
	}
	
	public void showHighscorePrompt(){		
		timeLabel.setText(app.gameTime/60+"min "+app.gameTime%60+"sec");
		timeLabel.setVisible(true);
		textField.setVisible(true);
		textField.requestFocus();
		submitButton.setVisible(true);
		repaint();
	}



}