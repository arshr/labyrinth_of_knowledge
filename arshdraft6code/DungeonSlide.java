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
 
 JLabel [] hearts;
 JPanel heartsPanel = new JPanel();
 
 Application app;
 
 public DungeonSlide(Application app) {
  super(app);
  this.app = app;
  this.question = app.question;
  this.monsterNumber = (int)(Math.random()*10)+1;
  init();
 }

 public void init() {
  hearts = new JLabel[3];
  int x = 0;
  for (int i = 0;i<3;i++){
   hearts[i]=new JLabel(new ImageIcon("Images\\pixel_heart.png"));
   hearts[i].setBounds (0,50*x,50,47);
   heartsPanel.add(hearts[i]);
   x++;
  }
  heartsPanel.setBackground(new Color(21,21,21));
  heartsPanel.setBounds(10,10,50,160);
  heartsPanel.setVisible(true);
  this.add(heartsPanel);
  
  questionLabel = new JLabel(question.question);
  questionLabel.setFont(new Font("Papyrus", Font.PLAIN, 40));
  questionLabel.setBounds(120, 42, 800, 55);
  setText();
  questionLabel.setVisible(true);
  this.add(questionLabel);

  Font buttonFont = new Font("Papyrus", Font.PLAIN, 38);

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
    checkAnswer (0);
   }
  });
  choice2.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    checkAnswer (1);
   }
  });
  choice3.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    checkAnswer (2);   
   }
  });
  choice4.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    checkAnswer (3);   
   }
  });

  //Monster label created
  monsterLabel = new JLabel (new ImageIcon ("monsters\\monster" +monsterNumber+ ".png"));
  monsterLabel.setBounds(700, 300, 200, 200);
  
  //Blue attack
  blueFlash = new Animation(this.app,3,null);
  blueFlash.setBounds(150,300,700,208);
  this.add(blueFlash);
  
  //Green Attack & monster run away, and key animation 
  greenFlash = new Animation(this.app,1,monsterLabel);
  greenFlash.setBounds(200,150,700,450);
  this.add(greenFlash);
  
  //Character animation
  character = new Animation(this.app,2,null);
  character.setBounds(200,300,150,208);
  this.add(character);
  
  //Adding monster label
  this.add(monsterLabel);   
  
  // background
  background = new JLabel(new ImageIcon("Images\\pixel_dungeon.jpg"));
  background.setBounds(0, 0, 1024, 650);
  this.add(background); 
 }
 
 public void activate(){
  character.startAnimation();
  for(int i = 0;i<3;i++){
   if (i<app.livesCount)
    hearts[i].setVisible(true);
   else 
    hearts[i].setVisible(false);
  }
 }
 
 private void checkAnswer(int playerAnswer){
   if (playerAnswer == question.correct){
   winAnimation();
  app.pg.keys++;
 }
  else {
   loseAnimation();
   app.pg.lives--;
   app.livesCount--;
  }
 }

 private void winAnimation(){
  greenFlash.reset();
  greenFlash.startAnimation();
 }
 
 private void loseAnimation(){
  blueFlash.reset();
  blueFlash.startAnimation();
  hearts[app.livesCount-1].setVisible(false);
 }
 
 private void gameOver(){
  try{
   Thread.sleep(1000);
  } catch (InterruptedException e) {
  }
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
 
 public void reset(){  
  //greenFlash.setVisible(false);
  for(int i = 0;i<3;i++){
   hearts[i].setVisible(true);
  }  
  monsterLabel.setBounds(700, 300, 200, 200);
 }

}
