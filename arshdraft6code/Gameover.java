import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Gameover extends Slide {
 JLabel background;
 JButton backButton;
 
 public Gameover(Application app) {
  super(app);
  backButton = new JButton (new ImageIcon("Images\\back button.png"));
  backButton.setBounds(880, 555, 136, 80);
  backButton.setBackground(new Color (120,79,37));
  this.add(backButton);
  
 
  backButton.addActionListener(new ActionListener (){
     public void actionPerformed (ActionEvent e){
      app.goToPane(1);
     }
  });
  
  // background
  background = new JLabel(new ImageIcon("Images\\gameOverScreen.png"));
  background.setBounds(0, 0, 1024, 650);
  this.add(background);
 }
}
