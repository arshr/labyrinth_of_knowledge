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
 JLabel [] nameLabels = new JLabel [10];
 JLabel [] scoreLabels = new JLabel [10];
 Font font = new Font ("Papyrus", Font.BOLD, 36);

 public Highscores(Application app) {
  super(app);
  init();
 }

 public void init() {  
  for (int i = 0;i<7;i++){
   nameLabels[i]=new JLabel();
   nameLabels[i].setBounds(300,250+45*i,250,50);
   nameLabels[i].setFont(font);
   nameLabels[i].setBackground(new Color (0,0,0,0));
   nameLabels[i].setForeground(Color.BLACK);
   nameLabels[i].setVisible(true);
   this.add(nameLabels[i]);
   
   scoreLabels[i]=new JLabel();
   scoreLabels[i].setBounds(600,250+45*i,250,50);
   scoreLabels[i].setFont(font);
   scoreLabels[i].setBackground(new Color (0,0,0,0));
   scoreLabels[i].setForeground(Color.BLACK);
   scoreLabels[i].setVisible(true);
   this.add(scoreLabels[i]);
  }
  
  
  backButton = new JButton (new ImageIcon("Images\\back button.png"));
  backButton.setBounds(880, 555, 136, 80);
  backButton.setBackground(new Color (120,79,37));
  this.add(backButton);
  
  background = new JLabel(new ImageIcon("Images\\highScores1.png"));
  background.setBounds(0, 0, 1024, 650);
  this.add(background);
  
  backButton.addActionListener(new ActionListener (){
     public void actionPerformed (ActionEvent e){
      app.goToPane(1);
     }
  });
 }
 
 public void activate(){
  backButton.setVisible(true);
  printHighscores();
 }
 
 public void deactivate(){
  backButton.setVisible(false);
 }
 
 private void printHighscores(){
  for (int i = 0;i<7;i++){
   if (i<app.highscores.size()){
    nameLabels[i].setText(app.highscores.get(i).name);
    scoreLabels[i].setText(app.highscores.get(i).scoreString);
   }
  }
 }
 
 
 
}
